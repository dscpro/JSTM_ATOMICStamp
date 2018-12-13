package com.dsc.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.dsc.Atomic.AtomicStamp;
import com.dsc.Transaction.Transaction;

public class TraDemo {
	AtomicStamp asd = new AtomicStamp("董士程");

	@Test
	public void test() {
		
		
		
		
	}
}
class T1 {
	public void test(AtomicStamp asd){
	Transaction t1 = new Transaction(1);
	t1.setAtomicS(asd);
	t1.startTransaction();
	AtomicStamp asd1=asd;	
	
	boolean flag = asd1.getAtomicStampedRef().compareAndSet("董士程", "邵帅", 0,
			asd1.getAtomicStampedRef().getStamp() + 1);
	 
	try {
		TimeUnit.SECONDS.sleep(5);
	} catch (InterruptedException e) {
	}
	t1.commitTransaction(flag);

	}
	
}

class T2 {
	public void test(AtomicStamp asd){
		
		Transaction t2 = new Transaction(2);
		t2.setAtomicS(asd);
		t2.startTransaction();
		AtomicStamp asd2=asd;
		boolean flag1 = asd2.getAtomicStampedRef().compareAndSet("董士程", "刘欢", 0,
				asd2.getAtomicStampedRef().getStamp() + 1);				
		
		System.out.println("T2" + flag1);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
		}
		t2.commitTransaction(flag1);

		try {
			TimeUnit.SECONDS.sleep(8);
			System.out.println(
					asd.getAtomicStampedRef().getReference() + "---------" + asd.getAtomicStampedRef().getStamp());

		} catch (InterruptedException e) {
		}
	}
}