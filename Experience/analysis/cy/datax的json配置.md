# 一、json配置

### 1.oracle到mysql

```json
{
	"job": {
		"content": {
			"reader": {
				"name": "oraclereader",
				"parameter": {
					"connection": [{
						"querySql": ["select * FROM CASE_INFO;"],
						"jdbcUrl": ["jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=192.68.61.159)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=lilo)(INSTANCE_NAME=sharedata)))"],
						"table": ["CASE_INFO"]
					}],
					"password": "sharedata",
					"username": "pdxq123"
				}
			},
			"writer": {
				"name": "mysqlwriter",
				"parameter": {
					"column": ["*"],
					"connection": [{
						"jdbcUrl": "jdbc:mysql://100.124.10.2:3306/",
						"table": ["CASE_INFO"]
					}],
					"password": "Sjksh123!@#",
					"username": "pdcypro"
				}
			},
			"setting": {
				"speed": {
					"channel": "1"
				}
			}
		}
	}
}
```

```json
{
    "job": {
        "setting": {
            "speed": {
            	"channel": 5
            }
        },
        "content": [
            {
                "reader": {
                    "name": "oraclereader",
                    "parameter": {
                        "username": "cyzx",
                        "password": "cyzx",
                        "where": "",
                        "connection": [
                            {
                                "querySql": [
                                    "SELECT * FROM ZHPT.CITYTOURISMFORCYZX WHERE ROUND(TO_NUMBER(CURRENT_DATE - TO_DATE(INTIME, 'yyyy-mm-dd hh24:mi:ss')) * 24 * 60) <= 1440"
                                ],
                                "jdbcUrl": [
                                    "jdbc:oracle:thin:@172.26.36.21:1521:zhptdb"
                                ]
                            }
                        ]
                    }
                },
                "writer": {
                    
                    
                    "name": "mysqlwriter",
                    "parameter": {
                        "writeMode": "replace",
                        "username": "pdcypro",
                        "password": "Sjksh123!@#",
                        "column": [
                            "CREATETIME",
                            "UNITNAME",
                            "UNITTIME",
                            "UNITNUM",
                            "COMFORT",
                            "OTHERTHING",
                            "STARTTIME",
                            "INTIME",
                            "ENDTIME",
                            "CLOSETYPE",
                            "C_STARTTIME",
                            "C_ENDTIME",
                            "ORDERID"
                        ],
                        "session": [
                        	"set session sql_mode='ANSI'"
                        ],
                        "preSql": [
                            ""
                        ],
                        "connection": [
                            {
                                "jdbcUrl": "jdbc:mysql://100.124.10.2:3306/dakeliu?useUnicode=true&characterEncoding=utf8",
                                "table": [
                                    "CITYTOURISMFORCYZX"
                                ]
                            }
                        ]
                    }
                }
            }
        ]
    }
}
```

### 2.sqlserver到mysql

```
{
    "job": {
        "setting": {
            "speed": {
                 "byte": 1048576
            }
        },
        "content": [
            {
                "reader": {
                    "name": "sqlserverreader",
                    "parameter": {
                        "username": "pd_readonly",
                        "password": "SJKSH@123",
                        "splitPk": "",
                        "connection": [
                            {
                            	"querySql": ["select * FROM EC_ELECTRONIC_CERT"],
                                "jdbcUrl": [
                                "jdbc:sqlserver://10.220.250.149:1433;DatabaseName=datashare"
                                ]
                            }
                        ]
                    }
                },
                "writer": {
                    "name": "mysqlwriter",
                    "parameter": {
                        "writeMode": "insert",
                        "username": "pdcypro",
                        "password": "Sjksh123!@#",
                        "column": [
                            "*"
                        ],
                        "session": [
                        	"set session sql_mode='ANSI'"
                        ],
                        "preSql": [
                            "delete from EC_ELECTRONIC_CERT"
                        ],
                        "connection": [
                            {
                                "jdbcUrl": "jdbc:mysql://100.124.10.2:3306/company_center?useUnicode=true&characterEncoding=utf8",
                                "table": [
                                    "EC_ELECTRONIC_CERT"
                                ]
                            }
                        ]
                    }
                }
            }
        ]
    }
}
```

### 3.mysql到mysql

```
{
    "job": {
        "setting": {
            "speed": {
                "channel": 5
            }
        },
        "content": [
            {
                "reader": {
                    "name": "mysqlreader",
                    "parameter": {
                        "username": "pdhotelread",
                        "password": "pdhotelread",
                        "connection": [
                            {
                                "querySql": [
                                    "select * from c_web_pdhotel_company;"
                                ],
                                "jdbcUrl": [
                                    "jdbc:mysql://100.124.10.2:3306/STREETANDTOWN"
                                ]
                            }
                        ]
                    }
                },
                "writer": {
                    "name": "mysqlwriter",
                    "parameter": {
                        "writeMode": "insert",
                        "username": "ksh",
                        "password": "123456",
                        "column": [
                            "id",
                            "CUR_DAY_ZDSJ_NUM",
                            "CUR_YEAR_ZDSJ_NUM",
                            "CUR_DAY_RCZF_NUM",
                            "CUR_YEAR_RCZF_NUM",
                            "ACTS_DAY_NUM",
                            "ACTS_YEAR_NUM",
                            "JDYJ_ALL_NUM",
                            "MQYJ_CJ_NUM",
                            "MQYJ_SG_NUM",
                            "WFWFWZ_ALL_NUM",
                            "XIETIAO_NUM",
                            "TINGZHENG_NUM",
                            "PINGYI_NUM",
                            "LOADATE"
                        ],
                        "session": [
                        	"set session sql_mode='ANSI'"
                        ],
                        "preSql": [
                            "delete from t_ksh_jmk"
                        ],
                        "connection": [
                            {
                                "jdbcUrl": "jdbc:mysql://100.124.82.2:3306/znvr?useUnicode=true&characterEncoding=utf-8",
                                "table": [
                                    "t_ksh_jmk"
                                ]
                            }
                        ]
                    }
                }
            }
        ]
    }
}
```

# 二、datax 虚拟机参数配置

python bin/datax.py -j"-Xms125m -Xmx125m" job/mysql_hdfs.json