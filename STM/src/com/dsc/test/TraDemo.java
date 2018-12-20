package com.dsc.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.dsc.Atomic.AtomicStamp;
import com.dsc.Transaction.Transaction;

public class TraDemo {
	static AtomicStamp asd = new AtomicStamp("董士程");

	public static void main(String[] args) {
		
		Thread refT1 = new Thread(new Runnable() {
			@Override
			public void run() {
				T1 t1= new T1();
				t1.run(asd);
				
			}
		});
		Thread refT2= new Thread(new Runnable() {
			@Override
			public void run() {
				
			   T2 t2= new T2();
			   t2.run(asd);
			}
		});
		refT1.start();
		refT2.start();
		

	}
}
class T1 {
	
	
	public void run(AtomicStamp asd){
	Transaction t1 = new Transaction(1);
	
	//t1.setAtomicS(asd);
	//System.out.println(asd.getAtomicStampedRef().getReference().toString());
	AtomicStamp asd1=new AtomicStamp(asd,t1);
	//System.out.println(asd.getAtomicStampedRef().getReference().toString());
	System.out.println("开启事务1");
	t1.startTransaction();
	try {
		TimeUnit.SECONDS.sleep(5);
	} catch (InterruptedException e) {
	}
	Transaction currentT1 = Transaction.getCurrent();
	System.out.println("事务1  值："+currentT1.getAtomicS().getAtomicStampedRef().getReference());
	
	//AtomicStamp asd1=asd;	
	
	boolean flag = asd1.getAtomicStampedRef().compareAndSet("董士程", "邵帅", 0,
			asd1.getAtomicStampedRef().getStamp() + 1);
	System.out.println("T1" + flag);
	
	t1.commitTransaction(flag);

	}
	
}

class T2 {
	 
	public void run(AtomicStamp asd){
		
		Transaction t2 = new Transaction(2);
		//t2.setAtomicS(asd);
		
		System.out.println("开启事务2");
		t2.startTransaction();
		AtomicStamp asd2=new AtomicStamp(asd,t2);
		//System.out.println("sds"+asd.getAtomicStampedRef().getReference());
		//System.out.println("ss"+asd2.getAtomicStampedRef().getReference());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
		}
		
		Transaction currentT2 = Transaction.getCurrent();
		System.out.println("事务2  值："+currentT2.getAtomicS().getAtomicStampedRef().getReference());
		//AtomicStamp asd2=asd;
		boolean flag1 = asd2.getAtomicStampedRef().compareAndSet("董士程", "刘欢", 0,
				asd2.getAtomicStampedRef().getStamp() + 1);				
		
		System.out.println("T2" + flag1);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		}
		t2.commitTransaction(flag1);	
	}
}