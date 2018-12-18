package com.dsc.Atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStamp  {
	/**
	 * 邮标值，ref改变时改变
	 */
	int stampValue=0;
	
	/**
	 * ref
	 */
	Object initialRef;
	/**
	 * 当前类   事务状态  0 初始  1  更改中    2已提交  
	 */
    int state=0;
    public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
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
	
	public AtomicStamp getAtomicStampPar() {
		return atomicStampPar;
	}
	public void setAtomicStampPar( ) {
		this.atomicStampPar = this;
	}
	
	public AtomicStamp() {
		 
	}
	public AtomicStamp(Object initialRef) {
		 this.initialRef=initialRef;
		 this.setAtomicStampedRef();
		 this.setAtomicStampPar();
	}
	/**
	 * AtomicStampedReference
	 */
	private  AtomicStampedReference<Object> atomicStampedRef ;
	/**
	 * 获取AtomicStampedReference
	 * @return
	 */
	public AtomicStampedReference<Object> getAtomicStampedRef() {
		return atomicStampedRef;
	}
	public void setAtomicStampedRef() {
		this.atomicStampedRef = new AtomicStampedReference<Object>(initialRef, stampValue);
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	//= new AtomicStampedReference(initValue,initStamp);
	// private static AtomicStampedReference atomicStampedStr = new
	// AtomicStampedReference(initString,initStamp);
	//
	
 

	 
}
