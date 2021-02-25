## 三、工单池

#### 1.场景

Scene(场景)

| 序号 |  字段名称   |     类型     | 特定 |   备注   |
| :--: | :---------: | :----------: | :--: | :------: |
|  1   |     id      | varchar(32)  |  pk  |          |
|  2   |    name     | varchar(128) |      | 场景名称 |
|  3   | description | varchar(128) |      | 场景描述 |
|  4   |   creator   | varchar(32)  |      |  创建人  |
|  5   | update_time |  timestamp   |      | 更新时间 |
|  6   |   is_del    |  varchar(1)  |      | 是否删除 |


#### 2.事项

Item(事项)

| 序号 |  字段名称   |     类型     | 特定 |                 备注                  |
| :--: | :---------: | :----------: | :--: | :-----------------------------------: |
|  1   |     id      | varchar(32)  |  pk  |                 主键                  |
|  2   |    name     | varchar(128) |      |               事项名称                |
|  3   | description | varchar(128) |      |               事项描述                |
|  4   | organ_name  | varchar(128) |      |               牵头单位                |
|  5   |   status    |  timestamp   |      | 事项状态 0-未发布，1-已发布，2-已生效 |
|  6   |  scene_id   | varchar(32)  |      |                场景Id                 |
|  7   |   creator   | varchar(32)  |      |                创建人                 |
|  8   | update_time |  timestamp   |      |               更新时间                |
|  9   |   is_del    |  varchar(1)  |      |               是否删除                |


#### 3.流程

Process(流程实例)

| 序号 | 字段名称  |    类型     | 特定 |                             备注                             |
| :--: | :-------: | :---------: | :--: | :----------------------------------------------------------: |
|  1   |    id     | varchar(32) |  pk  |                                                              |
|  2   | name |    varchar(256)    |      | 流程名称 |
|  3  | organ_id |    varchar(32)    |      | 责任单位id |
|  4  | form_item |    jsonb    |      | 表单设计,格式：[{"type":"input","value":"用户","name":"user"}] |
|  5  |  item_id  | varchar(32) |      |                            事项Id                            |
|  6  | item_name | varchar(32) |      |                            事项名称                            |
|  7  | scene_id  | varchar(32) |      |                            场景Id                            |
|  8  | scene_name | varchar(32) |      |                            场景名称                            |
|  9  |  other  | jsonb |      |                           预留参数                           |



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
|  16  |  steps  |    varchar     |      |                        1,2,3, 1-处置，2-审核，3-核验                         |



Order（当前工单）

| 序号 |  字段名称   |     类型     | 特定 |                             备注                             |
| :--: | :---------: | :----------: | :--: | :----------------------------------------------------------: |
|  1   |     id      | varchar(32)  |  pk  |                             主键                             |
|  2   | process_id  | varchar(32)  |      |                          流程实例Id                          |
|  3   | form_value  |    jsonb     |      |                           表单内容                           |
|  4   | scene_name  | varchar(128) |      |                             场景                             |
|  5   |  item_name  | varchar(128) |      |                             事项                             |
|  6   | current_node |    varchar(128)     |      | 当前节点 |
|  7   |  is_closed  |  varchar(1)  |      |                           是否关闭                           |
|  8   |  is_commit  |  varchar(1)  |      |                           是否上提                           |
|  9  |  is_delay   |  varchar(1)  |      |                           是否超时                           |
|  10   | description |    varchar(128)     |      |        描述       |
|  11  | creator | varchar(32) |      |                         创建人id                          |
|  12  | create_time | timestamp |      |                         创建时间                          |
|  13  | dispatch_time | timestamp |      |                         完成时间                          |
|  14  | update_time | timestamp |      |                         更新时间                          |
|  15  | other_param |    jsonb     |      |                           其他内容                           |

His_Order（历史工单）

| 序号 |  字段名称   |     类型     | 特定 |                             备注                             |
| :--: | :---------: | :----------: | :--: | :----------------------------------------------------------: |
|  1   |     id      | varchar(32)  |  pk  |                             主键                             |
|  2   | process_id  | varchar(32)  |      |                          流程实例Id                          |
|  3   | form_value  |    jsonb     |      |                           表单内容                           |
|  4   | scene_name  | varchar(128) |      |                             场景                             |
|  5   |  item_name  | varchar(128) |      |                             事项                             |
|  6   | current_node |    varchar(128)     |      | 当前节点 |
|  7   |  is_closed  |  varchar(1)  |      |                           是否关闭                           |
|  8   |  is_commit  |  varchar(1)  |      |                           是否上提                           |
|  9  |  is_delay   |  varchar(1)  |      |                           是否超时                           |
|  10   | description |    varchar(128)     |      |        描述       |
|  11  | creator | varchar(32) |      |                         创建人id                          |
|  12  | create_time | timestamp |      |                         创建时间                          |
|  13  | dispatch_time | timestamp |      |                         完成时间                          |
|  14  | update_time | timestamp |      |                         更新时间                          |
|  15  | other_param |    jsonb     |      |                           其他内容                           |

Record（流转记录）

| 序号 |  字段名称   |     类型     | 特定 |                             备注                             |
| :--: | :---------: | :----------: | :--: | :----------------------------------------------------------: |
|  1   |     id      | varchar(32)  |  pk  |                             主键                             |
|  2   | order_id  | varchar(32)  |      |                          工单Id                          |
|  3   | node_id |    varchar(32)     |      |                           节点Id                           |
|  3   | pranert_id |    varchar(32)     |      |                           父Id                           |
|  3   | type |    varchar(1)     |      |                           节点类型，0-新建，1-处置，2-核验，3-审核                          |
|  3   | pranert_id |    varchar(32)     |      |                           组织名称                           |
|  3   | name |    varchar(256)     |      |                          记录名称                          |
|  3   | creator |    varchar(32)     |      |                         操作人员id                          |
|  4   | statu  | varchar(1) |      |                            完成状态：0-进行中，1-已完成                             |
|  4   | is_delay  | varchar(1) |      |                             超时状态：0-未超时，1-已超时                            |
|  5   |  dispatch_time  | varchar(128) |      |                             派发时间                             |
|  6   | update_time |    jsonb     |      | 更新时间 |
|  7   |  create_time  |  varchar(1)  |      |                           创建时间                          |
|  8  |  dispatch_record  |  jsonb  |      |                           流转细则                         |

His_Record（流转记录）

| 序号 |  字段名称   |     类型     | 特定 |                             备注                             |
| :--: | :---------: | :----------: | :--: | :----------------------------------------------------------: |
|  1   |     id      | varchar(32)  |  pk  |                             主键                             |
|  2   | order_id  | varchar(32)  |      |                          工单Id                          |
|  3   | node_id |    varchar(32)     |      |                           节点Id                           |
|  3   | pranert_id |    varchar(32)     |      |                           父Id                           |
|  3   | type |    varchar(1)     |      |                           节点类型，0-新建，1-处置，2-核验，3-审核                          |
|  3   | pranert_id |    varchar(32)     |      |                           组织名称                           |
|  3   | name |    varchar(256)     |      |                          记录名称                          |
|  3   | creator |    varchar(32)     |      |                         操作人员id                          |
|  4   | statu  | varchar(1) |      |                            完成状态：0-进行中，1-已完成                             |
|  4   | is_delay  | varchar(1) |      |                             超时状态：0-未超时，1-已超时                            |
|  5   |  dispatch_time  | varchar(128) |      |                             派发时间                             |
|  6   | update_time |    jsonb     |      | 更新时间 |
|  7   |  create_time  |  varchar(1)  |      |                           创建时间                          |
|  8  |  dispatch_record  |  jsonb  |      |                           流转细则                         |