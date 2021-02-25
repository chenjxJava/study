![1580816471576](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1580816471576.png)



## 0.首页

![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c056c12f64.png)
### 0.1 登入接口
### 0.2 忘记密码，手机验证

## 一、个人中心
#### 1.1 管理员
![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c1626d2030.png)
#### 1.1.1 用户统计，总数，在线数
#### 1.1.2 工单统计，工单总数，进行中的工单数
#### 1.1.3 场景总数
#### 1.1.4 按场景统计7天的工单数
#### 1.1.5 工单列表（所有）

### 1.2 单位管理员
![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c1639c1db9.png)
#### 1.2.1 单位用户统计，总数，在线数
#### 1.2.2 单位工单统计，工单总数，进行中的工单数
#### 1.2.3 单位当月完成数
#### 1.2.4 按事项统计7天的工单数
#### 1.2.5 工单列表（按单位）

### 1.3 普通用户
![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c1645af9e3.png)
#### 1.3.1 个人用户 月度目标统计，总数，完成数
#### 1.3.2 个人工单统计，工单总数，进行中的工单数
#### 1.3.3 个人当日新增工单数
#### 1.3.4 按个人统计7天的工单数
#### 1.3.5 工单列表（按个人）

## 2.用户设置
### 2.1 管理员
![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c16554c22f.png)
### 2.2 普通用户
![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c1666e2b5f.png)
#### 2.2.1 用户详情
#### 2.2.2 用户修改
#### 2.2.3 手机号换绑

## 二、事项管理
![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c15720ddc8.png)
### 1.场景管理(“/api/scene/list”）
![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c167b965e2.png)
### 2.事项管理(“/api/item/list”）
![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c16956fb74.png)

## 三、工单池

#### 1.流程

Process(流程实例)

| 序号 | 字段名称  |    类型     | 特定 |                             备注                             |
| :--: | :-------: | :---------: | :--: | :----------------------------------------------------------: |
|  1   |    id     | varchar(32) |  pk  |                                                              |
|  2   | form_item |    jsonb    |      | 表单设计,格式：[{"type":"input","value":"用户","name":"user"}] |
|  3   |  item_id  | varchar(32) |      |                            事项Id                            |
|  4   | scene_id  | varchar(32) |      |                            场景Id                            |


Node（节点）

| 序号 |   字段名称    |     类型     | 特定 |                             备注                             |
| :--: | :-----------: | :----------: | :--: | :----------------------------------------------------------: |
|  1   |      id       | varchar(32)  |  pk  |                             主键                             |
|  2   |  process_id   | varchar(32)  |      |                          流程实例Id                          |
|  3   |   parent_id   | varchar(32)  |      |                             父id                             |
|  4   |     type      |  varchar(1)  |      |                     节点类型。处置、转派                     |
|  5   |     name      | varchar(128) |      |                           节点名称                           |
|  6   |  handle_time  |    jsonb     |      | {"total":"1h","check":"20min","handle":"20min","audit":"20min"} |
|  7   |   is_check    |  varchar(1)  |      |                           是否核验                           |
|  8   | check_person  | varchar(32)  |      |                           核验人员                           |
|  9   |  check_param  |    jsonb     |      |                         审核枚举类型                         |
|  10  |   is_handle   |  varchar(1)  |      |                           是否处置                           |
|  11  | handle_person | varchar(32)  |      |                           处置人员                           |
|  12  | handle_param  |     int      |      |                      处置反馈，图片附件                      |
|  13  |   is_audit    |  varchar(1)  |      |                           是否审核                           |
|  14  | audit_person  | varchar(32)  |      |                           审核人员                           |
|  15  |  audit_param  |    jsonb     |      |                         审核枚举类型                         |


Order（工单）

| 序号 |   字段名称    |     类型     | 特定 |                             备注                             |
| :--: | :-----------: | :----------: | :--: | :----------------------------------------------------------: |
|  1   |      id       | varchar(32)  |  pk  |                             主键                             |
|  2   |  process_id   | varchar(32)  |      |                          流程实例Id                          |
|  3   |   parent_id   | varchar(32)  |      |                             父id                             |
|  4   |     type      |  varchar(1)  |      |                     节点类型。处置、转派                     |
|  5   |     name      | varchar(128) |      |                           节点名称                           |
|  6   |  handle_time  |    jsonb     |      | {"total":"1h","check":"20min","handle":"20min","audit":"20min"} |
|  7   |   is_check    |  varchar(1)  |      |                           是否核验                           |
|  8   | check_person  | varchar(32)  |      |                           核验人员                           |
|  9   |  check_param  |    jsonb     |      |                         审核枚举类型                         |
|  10  |   is_handle   |  varchar(1)  |      |                           是否处置                           |
|  11  | handle_person | varchar(32)  |      |                           处置人员                           |
|  12  | handle_param  |     int      |      |                      处置反馈，图片附件                      |
|  13  |   is_audit    |  varchar(1)  |      |                           是否审核                           |
|  14  | audit_person  | varchar(32)  |      |                           审核人员                           |
|  15  |  audit_param  |    jsonb     |      |                         审核枚举类型  |

系统管理员：

![1580962481186](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1580962481186.png)



单位管理员：

![1580962442935](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1580962442935.png)





注意：表单设计

```json
[{
	"type": "input",
	"value": "用户",
	"name": "user"
}, {
	"type": "input",
	"value": "密码",
	"name": "password"
}]
```



#### 2.工单列表

## 四、消息管理

## 五、审计
![](http://bigai.youlishu.com:4999/server/../Public/Uploads/2020-01-13/5e1c17335ceeb.png)