# 
你觉得那条sql，查询效率更快？为什么？

<pre>
1.
SELECT
	AU.PSEUDONYM AS authorname,
	r.AUTHORID,
	r.equipmentid,
	r.bindtime
FROM
	RECORDER R
LEFT JOIN author au ON r.AUTHORID = au. ID
WHERE
	r.BINDSTATUS = 1
AND NOT EXISTS (
	SELECT
		1
	FROM
		BOOKSWITHEDITORS BE
	LEFT JOIN BOOKLIST BL ON BE.BOOKID = BL. ID
	WHERE
		BL.AUTHORID = R.AUTHORID
)
AND NOT EXISTS (
	SELECT
		1
	FROM
		bookadministrator Ba
	LEFT JOIN BOOKLIST BL2 ON Ba.BOOKID = BL2. ID
	WHERE
		BL2.AUTHORID = R.AUTHORID
)
</pre>

<pre>
2.可能会1，2s
SELECT
	au.PSEUDONYM AS authorname,
	REC.AUTHORID,
	rec.equipmentid,
	rec.bindtime
FROM
	RECORDER rec
LEFT JOIN author au ON rec.AUTHORID = au. ID
WHERE
	rec.BINDSTATUS = 1
AND NOT EXISTS (
	SELECT
		1
	FROM
		BOOKLIST BL
	LEFT JOIN (
		SELECT
			BOOKID
		FROM
			bookadministrator
		UNION
			SELECT
				BOOKID
			FROM
				bookswitheditors
	) T ON BL. ID = T .bookid
	WHERE
		REC.AUTHORID = bl.AUTHORID)
</pre>

<pre>
// 插入或者更新
<insert id="insertOrUpdateBookForBill">

        MERGE INTO books_for_bill bfb USING (
        SELECT #{bookId} as book_id from dual
        ) TMP ON (
        TMP.book_id = bfb.book_id
        )
        WHEN MATCHED THEN UPDATE
        <set>
            contract_standard = #{contractStandard}
            , identity_id = #{identityId}
            , guarantee_salary = #{guaranteeSalary}
            , book_name = #{bookName}
            , pseudonym = #{pseudonym}
            , author_name = #{authorName}
            , editor = #{editor}
            , department = #{department}
            , guarantee_start = #{guaranteeStart}
            , guarantee_length = #{guaranteeLength}
            , accounting_type = #{accountingType}
            , price_per_thousand = #{pricePerThousand}
            , contract_status = #{contractStatus}
            , ditch_contract_status = #{ditchContractStatus}
            , daily_standard = #{dailyStandard}
            , monthly_standard = #{monthlyStandard}
            , remark = #{remark}
            , is_accounting = #{isAccounting}
            , bank = #{bank}
            , card_no = #{cardNo}
            , phone_no = #{phoneNo}
            , is_institution = #{isInstitution}
            , supple_deal_status = #{suppleDealStatus}
        </set>
        WHEN NOT MATCHED THEN INSERT
        <trim prefix="(" suffix=")" suffixOverrides=",">
            id, book_id, book_Name, pseudonym, author_Name, department, editor,
            contract_Status, ditch_Contract_Status, accounting_Type, guarantee_Start,
            guarantee_Length, price_Per_Thousand, contract_Standard, daily_Standard,
            monthly_Standard, is_Accounting, remark, bank, card_No, phone_No,
            identity_Id, guarantee_Salary, is_institution, supple_deal_status
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            #{id}, #{bookId}, #{bookName}, #{pseudonym}, #{authorName}, #{department}, #{editor},
            #{contractStatus}, #{ditchContractStatus}, #{accountingType}, #{guaranteeStart},
            #{guaranteeLength}, #{pricePerThousand}, #{contractStandard}, #{dailyStandard},
            #{monthlyStandard}, #{isAccounting}, #{remark}, #{bank}, #{cardNo}, #{phoneNo},
            #{identityId}, #{guaranteeSalary}, #{isInstitution}, #{suppleDealStatus}
        </trim>
    </insert>
</pre>