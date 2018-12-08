package com.dsc.Atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStamp implements AtomicFactory{
	
	
	int initStamp=0;
	Object initialRef;
	public AtomicStamp() {
		 
	}
	public AtomicStamp(Object initialRef) {
		 this.initialRef=initialRef;
	}
	private  AtomicStampedReference atomicStamped; //= new AtomicStampedReference(initValue,initStamp);
	// private static AtomicStampedReference atomicStampedStr = new
	// AtomicStampedReference(initString,initStamp);
	//
	
 
	@Override
	public AtomicStamp setAtomicStamp(Object initialRef) {
		
		atomicStamped= new AtomicStampedReference(initialRef, initStamp);
		return this;
	}
	@Override
	public AtomicStampedReference getAtomicStamp() {
		// TODO Auto-generated method stub
		return this.atomicStamped;
	}

	 
}
