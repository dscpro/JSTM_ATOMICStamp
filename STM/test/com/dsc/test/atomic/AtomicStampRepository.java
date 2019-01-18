package com.dsc.test.atomic;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicStampedReference;

import javax.transaction.xa.Xid;

import com.dsc.test.transaction.Transaction;

public class AtomicStampRepository extends CachableTransactionRepository {
	/**
	 * �ʱ�ֵ��ref�ı�ʱ�ı�
	 */
	int stampValue = 0;
	/**
	 * ref
	 */
	private Object initialRef;
	private Object oldwRef;
	private Object newRef;

	public Object getNewRef() {
		return newRef;
	}

	public void setNewRef(Object newRef) {
		this.newRef = newRef;
	}

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
		this.initialAtomicStampedRef();
	}

	/**
	 * AtomicStampedReference
	 */
	private AtomicStampedReference<Object> atomicStampedRef;

	/**
	 * ��ȡAtomicStampedReference
	 * 
	 * @return
	 */
	public AtomicStampedReference<Object> getAtomicStampedRef() {
		return atomicStampedRef;
	}

	public void initialAtomicStampedRef() {
		this.atomicStampedRef = new AtomicStampedReference<Object>(initialRef, stampValue);
	}

	@Override
	protected int doCreate(Transaction transaction) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int doUpdate(Transaction transaction,TransactionRepository transactionRepository) {
		AtomicStampRepository atmoicstamp  = (AtomicStampRepository) transactionRepository;
				
		boolean flag = atmoicstamp.getAtomicStampedRef().compareAndSet(atmoicstamp.getOldRef(), atmoicstamp.getNewRef(), atmoicstamp.getStampValue(),
				atmoicstamp.getAtomicStampedRef().getStamp() + 1);		
		if (flag) {
			transaction.updateVersion();
			transaction.updateTime();
			// this.setOldRef(atomicStampedRef.getReference());
			atmoicstamp.setStampValue(atomicStampedRef.getStamp());
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
