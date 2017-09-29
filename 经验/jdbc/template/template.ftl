<#list root?values as table>
   ${table.tableName},${table.tableDescption}
   <#list table.coloums as coloum>
	${coloum.cloumnsName!"null"},
	${coloum.type!"null"},
	${coloum.isNull!"null"},
	${coloum.isAutoIncrement!"null"},
	${coloum.comment!"null"},
	${coloum.defaults!"null"},
	${coloum.isPrimaryKey !"null"},
	${coloum.isAutoIncrement!"null"},
   </#list>
</#list>