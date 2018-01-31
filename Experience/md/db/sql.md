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

