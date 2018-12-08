package com.dsc.test;

 
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

import org.junit.Test;

import com.dsc.Atomic.AtomicFactory;
import com.dsc.Atomic.AtomicStamp;


public class TraDemo {
	static AtomicFactory asd= new AtomicStamp();
	AtomicStampedReference atomicStampedRef;
	
	@Test
	public  void test() {
		
		
		//Transaction.startTransaction();
		asd.setAtomicStamp(100);
		atomicStampedRef= asd.getAtomicStamp();
		
		//System.out.println(c2+"重置标记"+atomicStamped.getStamp()+" 值:"+atomicStamped.getReference());
		//Transaction.commitTransaction();
		
		Thread refT1 = new Thread(new Runnable() {
			@Override
			public void run() {
				
				System.out.println("初始标记"+atomicStampedRef.getStamp()+" 值:"+atomicStampedRef.getReference());
				boolean c1 =atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
				System.out.println(c1+"变更一次标记"+atomicStampedRef.getStamp()+" 值:"+atomicStampedRef.getReference());
				
				
			}
		});

		Thread refT2 = new Thread(new Runnable() {
			@Override
			public void run() {
				
				System.out.println("当前标志: "+atomicStampedRef.getStamp()+" 值:"+atomicStampedRef.getReference());
				boolean c3 = atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+ 1);
				
				System.out.println(c3+"当前标志"+atomicStampedRef.getStamp()+" 值:"+atomicStampedRef.getReference()); // false
			}
		});

		refT1.start();
		refT2.start();
					
		//System.out.println(asd.getAtomicStamp().getStamp()+"---"+asd.getAtomicStamp().getReference());
	}
}
