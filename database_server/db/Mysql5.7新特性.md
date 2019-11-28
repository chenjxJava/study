# Mysql5.7新特性
1.关于索引
<pre>
 •如何查看数据库中的冗余索引
  select * from sys.schema_redundant_indexes;
 •如何获取未使用的索引
  select * from schema_unused_indexes;
 •如何查看使用全表扫描的SQL语句
  select * from  statements_with_full_table_scans
</pre> 

2.关于