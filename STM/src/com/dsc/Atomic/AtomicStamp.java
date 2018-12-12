package com.dsc.Atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStamp implements AtomicFactory{
	/**
	 * 邮标值，ref改变时改变
	 */
	int stampValue=0;
	
	/**
	 * ref
	 */
	Object initialRef;
	/**
	 * 当前类事务状态  0 初始  1  更改   2已提交
	 */
    int state;
	
    /**
	 *  父结点  未更改前的
	 */
	AtomicStamp atomicStampPar;
	
	
	public int getStampValue() {
		return stampValue;
	}
	public void setStampValue(int stampValue) {
		this.stampValue = stampValue;
	}
	public Object getInitialRef() {
		return initialRef;
	}
	public void setInitialRef(Object initialRef) {
		this.initialRef = initialRef;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public AtomicStamp getAtomicStampPar() {
		return atomicStampPar;
	}
	public void setAtomicStampPar(AtomicStamp atomicStampPar) {
		this.atomicStampPar = atomicStampPar;
	}
	
	public AtomicStamp() {
		 
	}
	public AtomicStamp(Object initialRef) {
		 this.initialRef=initialRef;
	}
	private  AtomicStampedReference atomicStamped; 
	//= new AtomicStampedReference(initValue,initStamp);
	// private static AtomicStampedReference atomicStampedStr = new
	// AtomicStampedReference(initString,initStamp);
	//
	
	@Override
	public void setAtomicStamp(Object initialRef) {
		
		atomicStamped= new AtomicStampedReference(initialRef, stampValue);
		//return this;
	}
	@Override
	public AtomicStampedReference getAtomicStamp() {
		// TODO Auto-generated method stub
		return this.atomicStamped;
	}

	 
}
