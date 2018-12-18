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
		
//		try {
//			TimeUnit.SECONDS.sleep(5);
//			System.out.println(
//					asd.getAtomicStampedRef().getReference() + "---------" + asd.getAtomicStampedRef().getStamp());
//
//		} catch (InterruptedException e) {
//		}
	}
}
class T1 {
	
	
	public void run(AtomicStamp asd){
	Transaction t1 = new Transaction(1);
	
	t1.setAtomicS(asd);
 
	System.out.println("开启事务1");
	t1.startTransaction();
//	try {
//		TimeUnit.SECONDS.sleep(4);
//	} catch (InterruptedException e) {
//	}
//	ThreadLocal<Transaction> currentT1 = Transaction.getCurrent();
//	//System.out.println("事务1  值："+currentT1.get().getAtomicS().getAtomicStampedRef().getReference().toString());
//	
//	AtomicStamp asd1=asd;	
////	asd.setAtomicStampPar(asd1);
//	boolean flag = asd1.getAtomicStampedRef().compareAndSet("董士程", "邵帅", 0,
//			asd1.getAtomicStampedRef().getStamp() + 1);
//	System.out.println("T1" + flag);
//	
	boolean flag =	t1.updateTransaction("刘欢");
	t1.commitTransaction(flag);

	}
	
}

class T2 {
	 
	public void run(AtomicStamp asd){
		
		Transaction t2 = new Transaction(2);
		t2.setAtomicS(asd);
		
		System.out.println("开启事务2");
		t2.startTransaction();
		
		//ThreadLocal<Transaction> currentT2 = Transaction.getCurrent();
		boolean flag =	t2.updateTransaction("邵帅");
		t2.commitTransaction(flag);

	}
}