# Gitea+Drone docker化整合

参考：

[容器方式下的轻量仓库与CI 使用方案：Gitea + Drone 基础篇](https://blog.csdn.net/soulteary/article/details/114099505)

[持续发布- Drone CI / CD 集成](https://blog.csdn.net/u012547633/article/details/106526499)


## 1.什么是drone？

持续集成工具

服务端。默认端口：80。可通过gitea授权跳转。

![1640251699801](.\pic\1640251699801.png)

runner端。默认端口：3000。配置用户密码登入。

![1640251788383](.\pic\1640251788383.png)





## ps.traefik 类似nginx反向代理工具

```
CONTAINER ID   NAME                 CPU %     MEM USAGE / LIMIT     MEM %     NET I/O           BLOCK I/O         PIDS
5295526d73f5   runner.nuc.com       0.00%     6.215MiB / 31.23GiB   0.02%     30.4kB / 24.3kB   11.8MB / 0B       17
9e810f12e2b4   drone.nuc.com        0.00%     10.56MiB / 31.23GiB   0.03%     36.5kB / 25.3kB   33.9MB / 0B       13
551b2e8683ba   gitea.nuc.com        2.05%     152MiB / 31.23GiB     0.48%     104kB / 439kB     88.8MB / 459kB    18
f4606080ef23   traefik              2.40%     20.49MiB / 31.23GiB   0.06%     483kB / 282kB     58.1MB 

```