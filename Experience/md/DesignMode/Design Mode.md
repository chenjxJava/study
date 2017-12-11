# Design Mode(设计模式)
### 一、单例设计模式

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

被观察者继承Observable，当changed属性=true时，调用update方法。
</pre>
ps:vector相关<br>
[　1.Vector菜鸟教程](http://www.runoob.com/java/java-vector-class.html)<br>
[　2.Java中vector的使用详解](https://www.cnblogs.com/zhaoyan001/p/6077492.html)

### 三、监听模式