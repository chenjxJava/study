# Design Mode(设计模式)
### 一、单例设计模式

> 二、三参考：[java设计模式--观察者模式和事件监听器模式](http://blog.csdn.net/dongnan591172113/article/details/8771441)<hr>
### 二、观察者模式
> 　观察者模式又称为订阅—发布模式，在此模式中，一个目标对象管理所有相依于它的观察者对象，并且在它本身的状态改变时主动发出通知。这通常透过呼叫各观察者所提供的方法来实现。此种模式通常被用来事件处理系统。。<br>　
基于事件驱动机制的系统或语言，比如node.js、nio等，不难发现其最终的基础模式就是观察者模式，只是不同的应用场景，也会有各自不同的侧重。

1. 被观察者需要继承Observable，具有被观察性
2. 观察者需要实现Observer接口，观察被观察者，被观察者改变，进行对应的update操作

<pre>
// 测试
public class ObserverDemo {
	public static void main(String[] args) {
		BeingWatched beingWatched = new BeingWatched();//受查者
		Watcher watcher = new Watcher();//观察者
		beingWatched.addObserver(watcher);
		beingWatched.counter(10);
	}
}

// 观察者
class Watcher implements Observer{

	public void update(Observable o, Object arg) {
		System.out.println("Update() called, count is "
			+ ((Integer) arg).intValue());
	}
}

// 被观察者
class BeingWatched extends java.util.Observable {
	void counter(int period) {
		for(; period>=0; period-- ) {
			setChanged();
			notifyObservers(new Integer(period));
			try {
				Thread.sleep(100);
			} catch( InterruptedException e) {
				System.out.println("Sleep interrupeted" );
			}
		}
	}
}
</pre>
<pre>
总结：
public class Observable {
    	private boolean changed = false;
    	private Vector<Observer> obs;
}

public interface Observer {
    void update(Observable o, Object arg);
}

被观察者
1.继承Observable
2.执行notifyObservers()方法
3.当changed属性=true时，调用Observer接口的update方法。
</pre>
ps:vector相关<br>
[　1.Vector菜鸟教程](http://www.runoob.com/java/java-vector-class.html)<br>
[　2.Java中vector的使用详解](https://www.cnblogs.com/zhaoyan001/p/6077492.html)

### 三、监听模式
> 事件源经过事件的封装传给监听器，当事件源触发事件后，监听器接收到事件对象可以回调事件的方法。<br>

##### 1.首要定义事件源对象（事件源相当于单击按钮事件当中的按钮对象、属于被监听者）：
<pre>public class DemoSource {     
    private Vector repository = new Vector();//监听自己的监听器队列     
    public DemoSource(){}     
    public void addDemoListener(DemoListener dl) {     
           repository.addElement(dl);     
    }     
    public void notifyDemoEvent() {//通知所有的监听器     
           Enumeration enum = repository.elements();     
           while(enum.hasMoreElements()) {     
                   DemoListener dl = (DemoListener)enum.nextElement();     
                 dl.handleEvent(new DemoEvent(this));     
           }     
    }     
} 
</pre>

##### 2.其次定义事件（状态）对象（该事件对象包装了事件源对象、作为参数传递给监听器、很薄的一层包装类）：
<pre>public class DemoEvent extends java.util.EventObject {     
    public DemoEvent(Object source) {     
      super(source);//source—事件源对象—如在界面上发生的点击按钮事件中的按钮     
       //所有 Event 在构造时都引用了对象 "source"，在逻辑上认为该对象是最初发生有关 Event 的对象     
    }     
    public void say() {     
           System.out.println("This is say method...");     
    }     
}     
</pre>

##### 3.最后定义我们的事件侦听器接口如下
<pre>public interface DemoListener extends java.util.EventListener {     
    //EventListener是所有事件侦听器接口必须扩展的标记接口、因为它是无内容的标记接口、     
    //所以事件处理方法由我们自己声明如下：     
    public void handleEvent(DemoEvent dm);     
}    
</pre>

##### 4.测试代码
<pre>public class TestDemo {     
   DemoSource ds;     
   public TestDemo(){     
      try{     
         ds = new DemoSource();     
         //将监听器在事件源对象中登记：     
         DemoListener1 listener1 = new DemoListener1();     
         ds.addDemoListener(listener1);     
         ds.addDemoListener(new DemoListener() {     
            public void handleEvent(DemoEvent event) {     
            System.out.println("Method come from 匿名类...");     
          }     
        });     
       ds.notifyDemoEvent();//触发事件、通知监听器     
     }catch(Exception ex){  
       ex.printStackTrace();  
       }     
    }     
    
    public static void main(String args[]) {     
           new TestDemo();     
    }     
}     
</pre>