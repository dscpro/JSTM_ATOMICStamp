package com.dsc.test.atomic;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicStampedReference;

import javax.transaction.xa.Xid;

import com.dsc.test.transaction.Transaction;

public class AtomicStampRepository extends CachableTransactionRepository {
	/**
	 * 邮标值，ref改变时改变
	 */
	int stampValue = 0;
	/**
	 * ref
	 */
	Object initialRef;
	Object oldwRef;

	public Object getOldRef() {
		if (oldwRef == null)
			return initialRef;
		return oldwRef;
	}

	public void setOldRef(Object oldwRef) {
		this.oldwRef = oldwRef;
	}

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

	public AtomicStampRepository() {

	}

	public AtomicStampRepository(Object initialRef) {
		this.initialRef = initialRef;
		this.setAtomicStampedRef();
	}

	/**
	 * AtomicStampedReference
	 */
	private AtomicStampedReference<Object> atomicStampedRef;

	/**
	 * 获取AtomicStampedReference
	 * 
	 * @return
	 */
	public AtomicStampedReference<Object> getAtomicStampedRef() {
		return atomicStampedRef;
	}

	public void setAtomicStampedRef() {
		this.atomicStampedRef = new AtomicStampedReference<Object>(initialRef, stampValue);
	}

	@Override
	protected int doCreate(Transaction transaction) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int doUpdate(Transaction transaction) {
	
		boolean flag = atomicStampedRef.compareAndSet(this.getOldRef(), transaction.getNewRef(),
				atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
		
	    if (flag) {
			transaction.updateVersion();
			transaction.updateTime();
		 	//this.setOldRef(atomicStampedRef.getReference());
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	protected int doDelete(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

}
