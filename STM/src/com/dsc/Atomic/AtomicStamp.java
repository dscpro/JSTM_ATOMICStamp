package com.dsc.Atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStamp  {
	/**
	 * �ʱ�ֵ��ref�ı�ʱ�ı�
	 */
	int stampValue=0;
	
	/**
	 * ref
	 */
	Object initialRef;
	/**
	 * ��ǰ��   ����״̬  0 ��ʼ  1  ������    2���ύ  
	 */
    int state=0;
    public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
    /**
	 *  �����  δ����ǰ��
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
	public void setAtomicStampPar(AtomicStamp atomicStampPar) {
		this.atomicStampPar = atomicStampPar;
	}
	
	public AtomicStamp() {
		 
	}
	public AtomicStamp(Object initialRef) {
		 this.initialRef=initialRef;
		 this.setAtomicStampedRef();
	}
	/**
	 * AtomicStampedReference
	 */
	private  AtomicStampedReference<Object> atomicStampedRef ;
	/**
	 * ��ȡAtomicStampedReference
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
