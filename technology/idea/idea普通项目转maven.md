### IntelliJ IDEA普通项目添加pom.xml，转变为Maven项目

参考：[IntelliJ IDEA普通项目添加pom.xml，转变为Maven项目](https://blog.csdn.net/IT_model/article/details/88815258)

#### 方法一：

- 添加一个xml文件，然后 Add as Maven Project。

#### 方法二：（推荐）

 一、点击我的项目， 右键，找到Add Framework Support，点击 

![1625190927414](E:\codeRepository\study\technology\DesignMode\pic\normar_project_convert_maven.png)

 二、弹出以下页面，若没有maven，一直下拉，若还没有，检查你的项目中是否已有pom.xml文件，勾选Maven，点击Ok 

![1625191027248](E:\codeRepository\study\technology\DesignMode\pic\normar_project_convert_maven_2.png)

 三、pom.xml成功创建(已转变成maven项目)，可以添加你的依赖了 