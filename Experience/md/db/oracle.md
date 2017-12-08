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
[　　Oracle命令（一）：Oracle登录命令](https://www.cnblogs.com/NaughtyBoy/p/3181052.html)<br>
[　　Oracle一个创建用户、创建表空间、授权、建表的完整过程](http://skyuck.iteye.com/blog/847859#)

### 三、PLSQL连接oracle
[　　参考百度经验“如何用PLSQL登录Oracle数据库”](https://jingyan.baidu.com/article/9c69d48fa3a80d13c9024ea0.html)

### 四、tnsname.ora设置
[　　参考百度经验“Oracle的tnsnames.ora配置(PLSQL Developer)”](https://jingyan.baidu.com/article/b0b63dbfcd34834a4930704a.html)

