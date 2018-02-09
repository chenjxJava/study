# vue.js
> 　Vue.js（读音 /vjuː/, 类似于 view） 是一套构建用户界面的渐进式框架。<br>
　Vue 只关注视图层， 采用自底向上增量开发的设计。<br>
　Vue 的目标是通过尽可能简单的 API 实现响应的数据绑定和组合的视图组件。<br>
　Vue 学习起来非常简单，本教程基于 Vue 2.1.8 版本测试。<br>

简单说，vue就是简化了页面绑定数据的过程。

* [vue-element-admin oschina](https://www.oschina.net/p/vue-element-admin)
* [vue-element-admin 仓库地址](https://github.com/PanJiaChen/vue-element-admin)

### 一、安装
### 1.独立版本(推荐)<br>
我们可以在[vue.js的官网上直接下载vue.min.js](http://vuejs.org/js/vue.min.js)并使用<script\>标签引入。

### 2.使用CDN方法(Content Delivery Network，即内容分发网络)<br>
　　以下推荐国外比较稳定的两个CDN，国内还没发现哪一家比较好，目前还是建议下载到本地。<br>
>
　BootCDN（国内）: https://cdn.bootcss.com/vue/2.2.2/vue.min.js<br>
　unpkg：https://unpkg.com/vue/dist/vue.js, 会保持和 npm 发布的最新的版本一致。<br>
　cdnjs : https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.8/vue.min.js<br>

### 3.npm安装
[　　参考菜鸟教程](http://www.runoob.com/vue2/vue-install.html)

### 二、简单的数据绑定(强大之处)


### 三、简写
#### v-bind 缩写
>     　<!-- 完整语法 -->
>	    <a v-bind:href="url"></a>
>	    <!-- 缩写 -->
>	    <a :href="url"></a>

#### v-on 缩写
>     　<!-- 完整语法 -->
>	    <a v-on:click="doSomething"></a>
>	    <!-- 缩写 -->
>	    <a @click="doSomething"></a>