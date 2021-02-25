# vue.js
> 　Vue.js（读音 /vjuː/, 类似于 view） 是一套构建用户界面的渐进式框架。<br>
　Vue 只关注视图层， 采用自底向上增量开发的设计。<br>
　Vue 的目标是通过尽可能简单的 API 实现响应的数据绑定和组合的视图组件。<br>
　Vue 学习起来非常简单，本教程基于 Vue 2.1.8 版本测试。<br>

简单说，vue就是简化了页面绑定数据的过程。

* [vue官网教程（推荐）](https://cn.vuejs.org/v2/guide/installation.html)
* [vue-element-admin oschina](https://www.oschina.net/p/vue-element-admin)
* [vue-element-admin 仓库地址](https://github.com/PanJiaChen/vue-element-admin)
* [vue学习之路](https://github.com/PanJiaChen/vue-element-admin/wiki)

[vue Api文档](https://cn.vuejs.org/v2/api/#%E9%80%89%E9%A1%B9-%E6%95%B0%E6%8D%AE)


### 一、安装
### 1.独立版本(推荐)<br>
我们可以在[vue.js的官网上直接下载vue.min.js](http://vuejs.org/js/vue.min.js)并使用<script\>标签引入。

### 2.使用CDN方法(Content Delivery Network，即内容分发网络)<br>
　　以下推荐国外比较稳定的两个CDN，国内还没发现哪一家比较好，目前还是建议下载到本地。<br>
>　BootCDN（国内）: https://cdn.bootcss.com/vue/2.2.2/vue.min.js<br>
>　unpkg：https://unpkg.com/vue/dist/vue.js, 会保持和 npm 发布的最新的版本一致。<br>
>　cdnjs : https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.8/vue.min.js<br>

### 3.npm安装
[参考菜鸟教程](http://www.runoob.com/vue2/vue-install.html)

　　使用npm命令需要安装node环境，简单说，就是要安装[node.js](https://www.ibm.com/developerworks/cn/opensource/os-nodejs/index.html?ca=drs#ibm-pcon)。
[nodejs环境下载](https://nodejs.org/en/download/)

[Node.js安装配置](http://www.runoob.com/nodejs/nodejs-install-setup.html)

[cnpm安装]( http://www.runoob.com/nodejs/nodejs-npm.html#taobaonpm)(需要npm 3.0以上 npm -v)

```
$ npm install -g cnpm --registry=https://registry.npm.taobao.org
```

这样就可以使用 cnpm 命令来安装模块了：

```
$ cnpm install [name]
```



### 二、语法

### 1.指令

#### 1.1 v-bind（文本插值 [1], 绑定元素 attribute[2] ）
```
<!-- 完整语法 -->
<a v-bind:href="url"></a>
<!-- 缩写 -->
<a :href="url"></a>
```

#### 1.2 v-if （判断）

v-else

v-else-if

v-show(频繁改变中使用，花销比v-if小)

#### 1.3 v-for（循环遍历）



#### 1.4 v-on（ 添加一个事件监听器 ）
```
<!-- 完整语法 -->
 <a v-on:click="doSomething"></a>
 <!-- 缩写 -->
 <a @click="doSomething"></a>
```

#### 1.5 v-model （ 它能轻松实现表单输入和应用状态之间的双向绑定 ）

```
 <input v-model="message"> 
```

#### 1.6 v-once（ 一次性插值，绑定内容不会再次改变 ）

#### 1.7 v-html（动态渲染html）



### 2.组件(component)

```html
<div id='app'>
<todo-item></todo-item>
<div>
```

```vue
// 定义名为 todo-item 的新组件
Vue.component('todo-item', {
  template: '<li>这是个待办项</li>'
})

var app = new Vue({
	el:"#app"
})
```



### 3.实例

#### 3.1 创建Vue实例

```vue
var app = new Vue({
	el: '',
	data: {},
	methods:{}
})
```

#### 3.2 数据和方法

#### 3.2.1 数据是响应式

#### 3.2.2 freeze方法

```
var obj = { 
	foo: 'bar'
} 

Object.freeze(obj)
```

#### 3.2.3 实例的属性$

$data 和 data区别

#### 3.2.4 $watch方法



### 4.事件处理

@click.stop

- `.stop`
- `.prevent`
- `.capture`
- `.self`
- `.once`
- `.passive`

```
<!-- 阻止单击事件继续传播 -->
<a v-on:click.stop="doThis"></a>

<!-- 提交事件不再重载页面 -->
<form v-on:submit.prevent="onSubmit"></form>

<!-- 修饰符可以串联 -->
<a v-on:click.stop.prevent="doThat"></a>

<!-- 只有修饰符 -->
<form v-on:submit.prevent></form>

<!-- 添加事件监听器时使用事件捕获模式 -->
<!-- 即内部元素触发的事件先在此处理，然后才交由内部元素进行处理 -->
<div v-on:click.capture="doThis">...</div>

<!-- 只当在 event.target 是当前元素自身时触发处理函数 -->
<!-- 即事件不是从内部元素触发的 -->
<div v-on:click.self="doThat">...</div>
```

