#oracle DB

###一、安装
> 

###二、命令行

1、运行SQLPLUS工具

>　　C:\Users\wd-pc>sqlplus

2、直接进入SQLPLUS命令提示符

>　　C:\Users\wd-pc>sqlplus /nolog

3、以OS身份连接 

>　　C:\Users\wd-pc>sqlplus / as sysdba   或
>
>　　SQL>connect / as sysdba

4、普通用户登录

>　　C:\Users\wd-pc>sqlplus scott/123456 　或<br>
>　　SQL>connect scott/123456  或<br>
>　　SQL>connect scott/123456@servername

5、以管理员登录

>　　C:\Users\wd-pc>sqlplus sys/123456 as sysdba　或
>
>　　SQL>connect sys/123456 as sysdba

6、切换用户

>　　SQL>conn hr/123456 
>
>　　注：conn同connect

 7、退出

>　　exit

