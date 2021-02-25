# data_sync

### 一、自定义数据结构

```json
{
	"name": "任务名称",
	"description": "任务描述",
    "":"",
	"setting": {
        
    },
	"datasource": {
    },
	"job": {},
	"task": {}
}
```

### 二、所需支持数据库

```
mysqlreader、mysqlwriter
postgresqlreader、postgresqlwriter
sqlserverreader、sqlserverwriter
oraclereader、oraclewriter
mongodbreader、mongdbwriter
```

#### 1.mysql
##### 1.1 mysqlreader
##### 1.2 mysqlwriter




#### 2.postgresql
##### 2.1 postgresqlreader
##### 2.2 postgresqlwriter



#### 3.sqlserver
##### 3.1 sqlserverreader
##### 3.2 sqlserverwriter



#### 4.odsp

##### 4.1 odspreader

##### 4.2 odspwriter



#### 5.mongdb

##### 5.1 mongodbreader

```json
{
	"reader": {
		"name": "mongodbreader",
		"parameter": {
            // 地址，【必填】
			"address": ["127.0.0.1:27017"], 
            // MongoDB的用户名。【选填】
			"userName": "",	
            // MongoDB的密码。【选填】
			"userPassword": "",
			"dbName": "tag_per_data",
            // MonogoDB的集合名。【必填】
			"collectionName": "tag_data12",  
            // MongoDB的文档列名。【必填】
			"column": [ 
                // MongoDB的文档列名。【必填】
				{
                    // Column的名字。【必填】
					"name": "unique_id", 
                    // Column的类型。【选填】
					"type": "string"     
				},
				{
					"name": "sid",
					"type": "string"
				},
				{
					"name": "user_id",
					"type": "string"
				}
			]
		}
	}
}
```



##### 5.2 mongodbreader

```json
{
	"writer": {
		"name": "mongodbwriter",
		"parameter": {
			"address": [
				"127.0.0.1:27017"
			],
			"userName": "",
			"userPassword": "",
			"dbName": "tag_per_data",
			"collectionName": "tag_data",
			"column": [{
					"name": "unique_id",
					"type": "string"
				},
				{
					"name": "sid",
					"type": "string"
				},
				{
					"name": "user_id",
					"type": "string"
				}
			],
            // upsertInfo：指定了传输数据时更新的信息。【选填】
			"upsertInfo": {
                // 当设置为true时，表示针对相同的upsertKey做更新操作。【选填】
				"isUpsert": "true",
                // 指定了没行记录的业务主键。用来做更新时使用。【选填】
				"upsertKey": "unique_id"
			}
		}
	}
}
```
### 三、API请求实例
#### 1.mysql
##### 1.1 save方法

-  /api/config/save 

``` json
{
    "name": "任务test3",
    "description": "这是描述",
    "email":"maidong@bigdata-x.com",
  	"reader": {
  		"source_id":"c9a7a5b0-d91d-4a17-98ce-1b0a24a448c4",
  		"type": "mysql",
  		"database": "Maidong",
  		"tablename": "test_source",
  		"column": [
  			"id", "name"
  		]
  	},
  	"writer": {
  		"source_id":"c9a7a5b0-d91d-4a17-98ce-1b0a24a448c4",
  		"type": "mysql",
  		"database": "Maidong",
  		"tablename": "test_result",
  		"writeMode": "insert",
  		"preSql": "delete from test_result",
  		"column": [
  			"id", "name"
  		]
  	},
  	"task": {
  		"cron": "0 0 0 * *"
  	},
  	"setting": {
  		"speed": {
            "channel": 16
          }
  	}
}
```

#### 2.postgresql

##### 2.1 save方法

-  /api/config/save 

``` json
{
    "name": "任务test4",
    "description": "这是描述",
    "email":"maidong@bigdata-x.com",
	"reader": {
		"source_id":"0c723385-2b45-464c-bce6-78d2a3b65d6f",
		"type": "postgresql",
		"database": "dev_lingmao",
		"tablename": "test_source",
		"column": [
			"id", "name"
		]
	},
	"writer": {
		"source_id":"0c723385-2b45-464c-bce6-78d2a3b65d6f",
		"type": "postgresql",
		"database": "dev_lingmao",
		"tablename": "test_result",
		"writeMode": "insert",
		"preSql": "delete from test_result",
		"column": [
			"id", "name"
		]
	},
	"task": {
		"cron": "0 0 0 * *"
	},
	"setting": {
		"speed": {
          "channel": 16
        }
	}
}
```



### 附录：datawork数据



```json
{
	"core":{
		"container":{
			"job":{
				"env":"prod",
				"id":668304,
				"name":"渣土车GPS数据同步",
				"projectId":"32528",
				"reportInterval":10000,
				"requestId":"9013572810",
				"sleepInterval":10000,
				"tenantId":"10006",
				"traceId":"3d851386-7944-491c-9a15-2877a140813e"
			}
		},
		"metric":{
			"eventLoglevel":"Basic",
			"eventMaxline":5000,
			"report":false,
			"reportbid":2,
			"reporturl":""
		}
	},
	"job":{
		"content":[
			{
				"reader":{
					"name":"mysqlreader",  // 根据type,获取reader的name值
					"parameter":{
						"column":[
							"uid",
							"clean_speed",
							"driver_id",
							"oil_wear",
							"purpose",
							"end_longitude",
							"emergency",
							"car_type1",
							"type",
							"end_latitude",
							"max_clean_ability",
							"car_type",
							"crew",
							"vid",
							"communication_number",
							"update_time",
							"rated_load",
							"clean_width",
							"use_dept_name",
							"displacement",
							"id",
							"purchase_time",
							"satellite_device_type",
							"brand",
							"use_dept_id",
							"plate_color",
							"create_time",
							"crane_tonnage",
							"dept_name",
							"weight",
							"gps",
							"type1",
							"end_location_time",
							"driver_name",
							"volume",
							"brush_count",
							"work_height",
							"name",
							"creator_id",
							"online",
							"max_work_speed",
							"plate_number",
							"dept_id",
							"image_id",
							"v_key",
							"status",
							"load_time"
						],
						"connection":[
							{
								"datasource":"dq_muck_internet",
								"envType":"1",
								"envTypeName":"prod",
								"gmtCreate":"2019-11-23 21:56:31",
								"jdbcUrl":[
									"jdbc:mysql://120.55.202.89:3306/muck?allowLoadLocalInfile=false"
								],
								"subType":"",
								"table":[
									"ods_zhzfj_realtime_gps_whole"
								],
								"tag":"public",
								"type":"mysql",
								"username":"maidong"
							}
						],
						"gmtCreate":"2019-11-23 21:56:31",
						"password":"********",
						"projectId":"32528",
						"username":"maidong",
						"where":"DATE_FORMAT(end_location_time,\"%Y%m%d\")=20191126"
					}
				},
				"writer":{
					"name":"odpswriter",
					"parameter":{
						"accessId":"ARvZYpi4TwBKi3Hl",
						"accessKey":"******************************",
						"authType":"1",
						"column":[
							"uid",
							"clean_speed",
							"driver_id",
							"oil_wear",
							"purpose",
							"end_longitude",
							"emergency",
							"car_type1",
							"type",
							"end_latitude",
							"max_clean_ability",
							"car_type",
							"crew",
							"vid",
							"communication_number",
							"update_time",
							"rated_load",
							"clean_width",
							"use_dept_name",
							"displacement",
							"id",
							"purchase_time",
							"satellite_device_type",
							"brand",
							"use_dept_id",
							"plate_color",
							"create_time",
							"crane_tonnage",
							"dept_name",
							"weight",
							"gps",
							"type1",
							"end_location_time",
							"driver_name",
							"volume",
							"brush_count",
							"work_height",
							"name",
							"creator_id",
							"online",
							"max_work_speed",
							"plate_number",
							"dept_id",
							"image_id",
							"v_key",
							"status",
							"load_time"
						],
						"datasource":"odps_first",
						"emptyAsNull":true,
						"endpoint":"http://service.cn-deqing-dqgov-d01.odps.ops.deqing.gov.cn/api",
						"envType":"1",
						"envTypeName":"prod",
						"gmtCreate":"2019-08-29 14:43:07",
						"odpsServer":"http://service.cn-deqing-dqgov-d01.odps.ops.deqing.gov.cn/api",
						"partition":"dt=20191126",
						"project":"shukongkeji",
						"projectId":"32528",
						"subType":"",
						"table":"ods_zhzfj_realtime_gps_whole",
						"truncate":true,
						"type":"odps"
					}
				}
			}
		],
		"setting":{
			"errorLimit":{},
			"keyVersion":"201505261918",
			"speed":{
				"channel":1,
				"concurrent":"1",
				"dmu":2,
				"throttle":false
			}
		}
	}
}
```



