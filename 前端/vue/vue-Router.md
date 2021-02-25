## Vue Router

### 1.什么是Vue Router

> Vue Router是Vue.js官方的路由管理器。包含的功能：
>
> - 嵌套的路由/视图表
> - 模块化的、基于组件的路由配置
> - 路由参数、查询、通配符
> - 基于Vue.js过渡系统的视图过渡效果
> - 细粒度的导航控制
> - 带有自动激活的CSS class的链接
> - HTML5历史模式或hash模式，在IE9自动降级
> - 自定义的滚动条行为

## 2.标签

```html
<!-- 使用 router-link 组件来导航. -->
<!-- 通过传入 `to` 属性指定链接. -->
<!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
<router-link to="/foo">Go to foo</router-link>
<!-- 路由出口 -->
<!-- 路由匹配到的组件将渲染在这里 -->
<router-view></router-view>
```

