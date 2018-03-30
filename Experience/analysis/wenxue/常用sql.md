# 常用sql

### 1.查询定时任务待发布的草稿
<pre>
select * from DRAFTS where time < SYSDATE;
</pre>

# 存储过程
### 1.全勤修数据 备份
<pre>
CREATE OR REPLACE 
PROCEDURE PUBLISH_SITUATION_UPDATE
AS
   TIME_DAY   DATE;
   END_DAY    DATE;
   THIS_ID    NUMBER;                                                  -- 某一本书
BEGIN
   TIME_DAY := TO_DATE ('2017-02-10', 'yyyy-mm-dd');
   END_DAY := TO_DATE ('2017-11-09', 'yyyy-mm-dd');

      THIS_ID := 14875531761776;

   BEGIN
      WHILE TIME_DAY < END_DAY
      LOOP
         DELETE FROM PUBLISH_SITUATION
               WHERE TIME = TRUNC (TIME_DAY - 1)
               AND ID = THIS_ID;

         COMMIT;

         INSERT INTO PUBLISH_SITUATION
            SELECT *
              FROM (SELECT A.ID,
                           A.BOOK_NAME,
                           A.PEN_NAME,
                           TRUNC (TIME_DAY - 1)         AS TIME,
                           A.IS_VERIFY,
                           NVL (B.DAY_SUM_WORDS, 0)     AS DAY_SUM_WORDS,
                           NVL (B.DAY_CHAPT_COUNT, 0)   AS DAY_CHAPT_COUNT,
                           NVL (C.MONTH_SUM_WORDS, 0)   AS MONTH_SUM_WORDS,
                           NVL (C.MONTH_CHAPT_COUNT, 0) AS MONTH_CHAPT_COUNT,
                           NVL (D.STOPUP_DAY_COUNT, 0)  AS STOPUP_DAY_COUNT,
                           SYSDATE,
                           A.SEX_TYPE,
                           E.NAME_TYPE                  AS TYPE_NAME,
                           0,
                           NULL
                      FROM BOOKLIST A
                           LEFT JOIN
                           (  SELECT BOOKID,
                                     SUM (CHAPTERNUMBER) AS DAY_SUM_WORDS,
                                     COUNT (1)         AS DAY_CHAPT_COUNT
                                FROM BOOKCHAPTER
                               WHERE     create_dt >= TRUNC (TIME_DAY - 1)
                                     AND create_dt < TRUNC (TIME_DAY)
                                     AND ISPASS = 1
                                      AND bookid = this_id
                            GROUP BY BOOKID) B
                              ON A.ID = B.BOOKID
                           LEFT JOIN
                           (  SELECT BOOKID,
                                     SUM (CHAPTERNUMBER) AS MONTH_SUM_WORDS,
                                     COUNT (1)         AS MONTH_CHAPT_COUNT
                                FROM BOOKCHAPTER
                               WHERE     create_dt >=
                                            TO_DATE (
                                                  TO_CHAR (
                                                     TRUNC (TIME_DAY - 1),
                                                     'yyyy-mm')
                                               || '-01',
                                               'yyyy-mm-dd')
                                     AND create_dt < TRUNC (TIME_DAY)
                                     AND ISPASS = 1
                                      AND bookid = this_id
                            GROUP BY BOOKID) C
                              ON A.ID = C.BOOKID
                           LEFT JOIN
                           (  SELECT BOOKID,
                                     TRUNC (TRUNC (TIME_DAY) - MAX (create_dt))
                                        AS STOPUP_DAY_COUNT
                                FROM BOOKCHAPTER
                               WHERE     create_dt < TRUNC (TIME_DAY)
                                     AND ISPASS = 1
                                      AND BOOKID = THIS_ID
                            GROUP BY BOOKID) D
                              ON A.ID = D.BOOKID
                           LEFT JOIN BOOKTYPE E ON A.BOOK_TYPE = E.ID
						   WHERE a.id = THIS_ID
                   );


         COMMIT;

         TIME_DAY := TIME_DAY + 1;
      END LOOP;
   END;
END PUBLISH_SITUATION_UPDATE;

</pre>

<pre>
// 电子合同
  SELECT
            booklist.book_name  AS book_name,
            booklist.pen_name   AS pseudonym,
						booklist.book_type_name AS typeName,
            userreal.name       AS author_name,
            u.username          AS editor,
            ac.crad             AS card_no,
            ac.address          AS bank,
            ac.identitycrad     AS identity_id,
            author.tel          AS phone_no,
            SYS_ROLES.ROLE_NAME AS ROLE_NAME
        FROM booklist
            JOIN ditchbook ON booklist.id = ditchbook.BOOKID
            JOIN AUTHOR ON booklist.AUTHORID = AUTHOR.ID
            JOIN USERREAL ON AUTHOR.USERID = USERREAL.USERID
            JOIN BOOKSWITHEDITORS b ON booklist.id = b.BOOKID
            JOIN userinfo u ON b.EDITORUSERID = u.id
            JOIN authorcrad ac ON booklist.authorid = ac.authorid
            JOIN SYS_USERS_ROLES ON U.ID = SYS_USERS_ROLES.USER_ID
            JOIN SYS_ROLES ON SYS_USERS_ROLES.ROLE_ID = SYS_ROLES.ID
--        WHERE ditchbook.DITCHBOOKID = #{bookId} AND rownum <= 1
        WHERE booklist.id = #{bookId} AND rownum <= 1;

select * from booklist;

select * from booktype;



// 主编确认
select * from BILL_FOR_AUTHOR;

update BILL_FOR_AUTHOR set bu = '原创', MODEL = 1;

select * from SALARY where SALARYMONTH = '2018年01月';

select * from SALARY ORDER BY SALARYMONTH nulls FIRST;

DELETE from SALARY where SALARYMONTH is null;
</pre>