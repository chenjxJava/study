package com.scm.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class RecUDP {
	public static void main(String[] args) throws Exception {

		DatagramSocket ds = new DatagramSocket(10000);  //定义服务，监视端口上面的发送端口，注意不是send本身端口

		byte[] buf = new byte[1024];//接受内容的大小，注意不要溢出

		DatagramPacket dp = new DatagramPacket(buf,0,buf.length);//定义一个接收的包

		ds.receive(dp);//将接受内容封装到包中

		String data = new String(dp.getData(), 0, dp.getLength());//利用getData()方法取出内容

		System.out.println(data);//打印内容

		ds.close();//关闭资源
	}
}
