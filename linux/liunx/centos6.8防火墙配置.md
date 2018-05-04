# Centos6.8防火墙配置
参考：[Centos6.8防火墙配置](https://www.cnblogs.com/xxoome/p/6884376.html)
### 一、基本操作
<pre>
# 查看防火墙状态
service iptables status
 
# 停止防火墙
service iptables stop
 
# 启动防火墙
service iptables start
 
# 重启防火墙
service iptables restart
 
# 永久关闭防火墙
chkconfig iptables off
 
# 永久关闭后重启
chkconfig iptables on
</pre>

### 三、CentOS下/etc/sysconfig/下找不到iptables文件
本想做些防火墙策略。防火墙策略都是写在/etc/sysconfig/iptables文件里面的。可我发现我也没有这个文件。
<pre>
[root@iZ23gx7o02aZ /]# cd /etc/sysconfig/
[root@iZ23gx7o02aZ sysconfig]# ls
atd         firstboot         irqbalance  network-scripts  rhn            sysstat
auditd      grub              kdump       ntpd             rngd           sysstat.ioconf
authconfig  htcacheclean      kernel      ntpdate          rsyslog        system-config-firewall
cbq         httpd             keyboard    prelink          sandbox        system-config-firewall.old
clock       i18n              modules     quota_nld        saslauthd      udev
console     init              netconsole  raid-check       selinux
cpuspeed    ip6tables-config  network     readahead        smartmontools
crond       iptables-config   networking  readonly-root    sshd
[root@iZ23gx7o02aZ sysconfig]# 
</pre>
解决办法：<br>
1.随便写一条iptables命令配置个防火墙规则。如：iptables -P OUTPUT ACCEPT。
<pre>
[root@iZ23gx7o02aZ /]# cd /etc/sysconfig/
[root@iZ23gx7o02aZ sysconfig]# iptables -P OUTPUT ACCEPT
</pre>

2.service iptables save进行保存。
<pre>
[root@iZ23gx7o02aZ sysconfig]# service iptables save
iptables: Saving firewall rules to /etc/sysconfig/iptables:[  OK  ]
[root@iZ23gx7o02aZ sysconfig]# ls
atd         firstboot         iptables-config  networking       readonly-root  sshd
auditd      grub              irqbalance       network-scripts  rhn            sysstat
authconfig  htcacheclean      kdump            ntpd             rngd           sysstat.ioconf
cbq         httpd             kernel           ntpdate          rsyslog        system-config-firewall
clock       i18n              keyboard         prelink          sandbox        system-config-firewall.old
console     init              modules          quota_nld        saslauthd      udev
cpuspeed    ip6tables-config  netconsole       raid-check       selinux
crond       iptables          network          readahead        smartmontools
[root@iZ23gx7o02aZ sysconfig]# 
</pre>

3.service iptables restart命令重启：
<pre>
[root@iZ23gx7o02aZ sysconfig]# service iptables restart
iptables: Setting chains to policy ACCEPT: filter          [  OK  ]
iptables: Flushing firewall rules:                         [  OK  ]
iptables: Unloading modules:                               [  OK  ]
iptables: Applying firewall rules:                         [  OK  ]
[root@iZ23gx7o02aZ sysconfig]# 
</pre>

### 二、Liunx防火墙/etc/sysconfig/iptables 详解
<pre>
#头两行是注释说明  
# Firewall configuration written by system-config-securitylevel  
# Manual customization of this file is not recommended.  
#使用filter表  
*filter  
#下面四条内容定义了内建的INPUT、FORWAARD、ACCEPT链，还创建了一个被称为RH-Firewall-1-INPUT 的新链  
:INPUT ACCEPT [0:0]  
:FORWARD ACCEPT [0:0]  
:OUTPUT ACCEPT [0:0]  
:RH-Firewall-1-INPUT - [0:0]  
#将所有流入的数据写入到日志文件中  
-A INPUT -j LOG --log-level crit  
#下面这条规则将添加到INPUT链上，所有发往INPUT链上的数据包将跳转到RH-Firewall-1 //链上。  
-A INPUT -j RH-Firewall-1-INPUT  
#下面这条规则将添加到FORWARD链上，所有发往INPUT链上的数据包将跳转到RH-Firewall-1 //链上。  
-A FORWARD -j RH-Firewall-1-INPUT  
#下面这条规则将被添加到RH-Firewall-1-input链。它可以匹配所有的数据包，其中流入接口（-i）//是一个环路接口(lo)。  
#匹配这条规则的数据包将全部通过（ACCEPT），不会再使用别的规则来和它们进行比较  
-A RH-Firewall-1-INPUT -i lo -j ACCEPT  
#下面这条规则是拒绝所以的icmp包-p 后是协议如：icmp、tcp、udp。端口是在-p后面--sport源端口，--dport目的端口。-j 指定数据包发送的  
#目的地址如：ACCEPT、DROP、QUEUE等等  
-A RH-Firewall-1-INPUT -p icmp --icmp-type any -j DROP  
-A RH-Firewall-1-INPUT -p 50 -j ACCEPT  
-A RH-Firewall-1-INPUT -p 51 -j ACCEPT  
-A RH-Firewall-1-INPUT -p udp --dport 5353 -d 224.0.0.251 -j ACCEPT  
-A RH-Firewall-1-INPUT -p udp -m udp --dport 631 -j ACCEPT  
-A RH-Firewall-1-INPUT -p tcp -m tcp --dport 631 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT  
#-m state --state ESTABLISHED,RELATED这个条件表示所有处于ESTABLISHED或者  
RELATED状态的包，策略都是接受的。  
# -m state --state NEW 这个条件是当connection的状态为初始连接(NEW)时候的策略。  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 21 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 2049 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 22 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m udp -p udp --dport 137 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m udp -p udp --dport 138 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 139 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 445 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 23 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j DROP -s 222.221.7.84  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 443 -j ACCEPT  
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 25 -j ACCEPT  
-A RH-Firewall-1-INPUT -j REJECT --reject-with icmp-host-prohibited  
COMMIT  
  
iptalbes 是状态检测防火墙！ 
</pre>