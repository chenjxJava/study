# oracle DB

### 一、安装

### 二、命令行

1、运行SQLPLUS工具

>　C:\Users\wd-pc>sqlplus

2、直接进入SQLPLUS命令提示符

>　C:\Users\wd-pc>sqlplus /nolog

3、以OS身份连接 

>　C:\Users\wd-pc>sqlplus / as sysdba   或
>
>　SQL>connect / as sysdba

4、普通用户登录

>　C:\Users\wd-pc>sqlplus scott/123456 　或<br>
>　SQL>connect scott/123456  或<br>
>　SQL>connect scott/123456@servername

5、以管理员登录

>　C:\Users\wd-pc>sqlplus sys/123456 as sysdba　或
>
>　SQL>connect sys/123456 as sysdba

6、切换用户

>　SQL>conn hr/123456 
>
>　注：conn同connect

7、退出

>　exit

8、创建用户

> 　SQL>create user chenjx identified by oracle; 

9、修改用户密码
> 　SQL>alter user zzg identified by unis; 

10、查看用户所在的表空间
> 　SQL>select username,default_tablespace from dba_users;   

11、一般在开发情况下，我们当然不会使用用户的默认表空间,所以这时我们需要创建一个表空间.
> 　SQL>create tablespace ts_chenjx datafile 'G:\location\db\chenjx_data.dbf' size 200M;   

12、创建好表空间,还需要将表空间分配给用户.
> 　SQL>alter user chenjx default tablespace ts_chenjx;

13、给用户分配了表空间,用户还不能登陆（没有登录权限）,因此还需要为用户分配权限
> 　SQL>grant create session,create table,create view,create sequence,unlimited tablespace to chenjx; 

14、最后我们也可以删除用户及其相关对象 
> 　SQL>drop user zzg cascade; <br> 

<hr>
参考:<br>
　　[Oracle命令（一）：Oracle登录命令](https://www.cnblogs.com/NaughtyBoy/p/3181052.html)<br>
　　[Oracle一个创建用户、创建表空间、授权、建表的完整过程](http://skyuck.iteye.com/blog/847859#)

### 三、PLSQL连接oracle
[　　参考百度经验“如何用PLSQL登录Oracle数据库”](https://jingyan.baidu.com/article/9c69d48fa3a80d13c9024ea0.html)

### 四、tnsname.ora设置
[　　参考百度经验“Oracle的tnsnames.ora配置(PLSQL Developer)”](https://jingyan.baidu.com/article/b0b63dbfcd34834a4930704a.html)

### FAQ
1. [oracle 用Navicat创建的表的查询问题](https://www.cnblogs.com/baby123/p/4808969.html)
<pre>
navicat可视化创建了表，可是就是不能查到！这个为什么呢？

select * from user;

我们如果给user加上双引号才能查到

select * from "user";

结论：

　　1、oracle表和字段是有大小写的区别。oracle默认是大写，如果我们用双引号括起来的就区分大小写，如果没有，系统会自动转成大写。

　　2、我们在使用navicat使用可视化创建数据库时候，navicat自动给我们加上了“”，在创建数据库时实际的代码是这样的：　
<pre>
	DROP TABLE "ROOT"."user";
	CREATE TABLE "ROOT"."user" (
	"userid" NUMBER(2) NOT NULL 
	)
</pre>
　　3、我用sql语句进行创建表和字段

　　　　①不加双引号创建变大写

　　　　②加双引号，跟我们使用navicat可视化操作一样 

因此建议：

1.还是养成手写sql语句的习惯，在创建的时候就不要使用了双引号！这样我们就可以到达像mysql等一样不区分大小写了。

2.尽量养成大写操作数据库的习惯。
</pre>

##### [2.Navicat for Oracle基本用法图文教程](https://www.2cto.com/kf/201604/497696.html)

### oracle sql
<pre>
// 
SELECT m.*,ROWNUM FROM (
	SELECT a.*,r.REJECT_REASON as reason FROM BOOKCHAPTER_AUDIT a
					 LEFT JOIN BOOKCHAPTER_REJECT_RECODE r ON a.AUDIT_ID = r.AUDIT_ID
					 WHERE
							 a.CHAPTER_ID = 1938602739453441
					 ORDER BY a.AUDIT_LAST_SUBMIT_TIME DESC
) m WHERE ROWNUM = 1
</pre>

<pre>
 <select id="getNotContributeBook" resultMap="BaseResultMap" parameterType="map">
        <include refid="OracleDialectPrefix"/>
        SELECT B.ID NEWCHAPTERID, B.CHAPTERTITLE NEWCHAPTER, B.CREATE_DT, BL.*, DB.DITCHBOOKID
        FROM BOOKLIST BL
        LEFT JOIN (
         SELECT * FROM ( SELECT BOOKID,ID,CREATE_DT,CHAPTERTITLE,Row_Number() OVER (partition by BOOKID ORDER BY chapter_number desc) recordnum FROM BOOKCHAPTER ) WHERE recordnum = 1 
        ) B ON B.BOOKID = BL.ID
        LEFT JOIN DITCHBOOK DB ON DB.BOOKID = BL.ID
        WHERE BL.AUTHORID = #{authorId} AND CONTRIBUTEBOOK = 0
        ORDER BY SENDTIME DESC
        <include refid="OracleDialectSuffix"/>
    </select>
</pre>