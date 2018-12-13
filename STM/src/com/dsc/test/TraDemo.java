package com.dsc.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

import org.junit.Test;

import com.dsc.Atomic.AtomicStamp;
import com.dsc.Transaction.Transaction;

public class TraDemo {
	AtomicStamp asd = new AtomicStamp("董士程");
	AtomicStampedReference atomicStampedRef;

	@Test
	public void test() {
		Transaction t1 = new Transaction(1);
		Transaction t2 = new Transaction(2);

		t1.setAtomicS(asd);
		t1.startTransaction();	
		
		asd.getAtomicStampedRef().compareAndSet("董士程", "邵帅", 0, asd.getAtomicStampedRef().getStamp() + 1);
		 
		t1.commitTransaction();
		
		
		
		
		
		
		
		
		
//		
//		
//		Thread refT1 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				
//				t1.startTransaction();							
//				asd.getAtomicStampedRef().compareAndSet("董士程", "邵帅", 0, asd.getAtomicStampedRef().getStamp() + 1);
//				try {
//					TimeUnit.SECONDS.sleep(5);
//				} catch (InterruptedException e) {
//				}
//				t1.commitTransaction();
//			}
//		});
//
//		Thread refT2 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				t2.startTransaction();
//				asd.getAtomicStampedRef().compareAndSet("董士程", "刘欢", 0, asd.getAtomicStampedRef().getStamp() + 1);
//				try {
//					TimeUnit.SECONDS.sleep(2);
//				} catch (InterruptedException e) {
//				}
//				t2.commitTransaction();
//			}
//		});
//		
//		
//		refT1.start();
//		refT2.start();
		

	}
}
