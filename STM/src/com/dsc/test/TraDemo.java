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
		
		//System.out.println(c2+"���ñ��"+atomicStamped.getStamp()+" ֵ:"+atomicStamped.getReference());
		//Transaction.commitTransaction();
		
		Thread refT1 = new Thread(new Runnable() {
			@Override
			public void run() {
				
				System.out.println("��ʼ���"+atomicStampedRef.getStamp()+" ֵ:"+atomicStampedRef.getReference());
				boolean c1 =atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
				System.out.println(c1+"���һ�α��"+atomicStampedRef.getStamp()+" ֵ:"+atomicStampedRef.getReference());
				
				
			}
		});

		Thread refT2 = new Thread(new Runnable() {
			@Override
			public void run() {
				
				System.out.println("��ǰ��־: "+atomicStampedRef.getStamp()+" ֵ:"+atomicStampedRef.getReference());
				boolean c3 = atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+ 1);
				
				System.out.println(c3+"��ǰ��־"+atomicStampedRef.getStamp()+" ֵ:"+atomicStampedRef.getReference()); // false
			}
		});

		refT1.start();
		refT2.start();
					
		//System.out.println(asd.getAtomicStamp().getStamp()+"---"+asd.getAtomicStamp().getReference());
	}
}
