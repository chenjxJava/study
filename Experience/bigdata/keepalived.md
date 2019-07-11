# keepalived
> 参考：[keepalived实现双机热备](https://www.cnblogs.com/jefflee168/p/7442127.html)
### 一、VRRP(虚拟路由冗余协议)-VirtualRouterRedundancyProtocol
> Keepalived是VRRP的完美实现，了解VRRP,最好看VRRP的RFC文档。
<pre>
// 1.VRRP协议简介
就是解决主机如何选定到大目的主机的下一跳路由。

VRRP的目的就是为了解决静态路由单点故障问题。

VRRP通过一种竞选协议来动态的将路由任务交给LAN中虚拟路由器中的某台VRRP路由器。

// 2.VRRP路由器
 VRRP路由器就是一台路由器，只不过上面运行了VRRPD这样的程序来
实现VRRP协议而已，这是物理的路由器。一台VRRP路由器可以位于多个虚拟路由器。

// 3.VRRP虚拟路由器
 所谓虚拟，不是实际存在，逻辑意义上的路由器。
 虚拟路由器用处由多台(物理的)VRRP路由器通过某种方式组成。
 就好比把这些物理的路由器丢到一个池（pool）里面去,内部多台。
 虚拟路由器的标识称为VRID。

// 4.MASTER和BACKUP
在一个VRRP虚拟路由器中，有多台物理的VRRP路由器，但是这些物理机器并不同时工作，而是由MASTER路由工作，其他的都是BACKUP。

MASTER并不是一成不变，VRRP协议让每个VRRP路由器参与竞选。

MASTER主机拥有虚拟路由器的IP地址。
</pre>

### 二、KeepAlived配置详解
> Keepalived的所有配置都在一个配置文件里面配置，支持配置项比较多。但分为三类：
<pre>
1.全局配置（Global Configuration）

2.VRRPD配置

3.LVS配置
</pre>