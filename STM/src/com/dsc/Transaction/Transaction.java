package com.dsc.Transaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicStampedReference;

import com.dsc.Atomic.AtomicStamp;

public class Transaction {
	protected int number;
	protected final Transaction parent;

	protected Transaction getParent() {
		return parent;
	}

	public Transaction(Transaction parent, int number) {
		this.parent = parent;
		this.number = number;
	}

	public Transaction(int number) {
		this(null, number);
	}

	public Transaction(Transaction parent) {
		this(parent, parent.getNumber());
	}

	public int getNumber() {
		return number;
	}

	protected void setNumber(int number) {
		this.number = number;
	}

	/**
	 * AtomicStamp��
	 */
	public AtomicStamp atomicS;

	public AtomicStamp getAtomicS() {
		return atomicS;
	}

	public void setAtomicS(AtomicStamp atomicS) {
		this.atomicS = atomicS;
	}

	protected static final ThreadLocal<Transaction> current = new ThreadLocal<Transaction>();

	public static ThreadLocal<Transaction> getCurrent() {
		return current;
	}

	/**
	 * ��������
	 */
	public void startTransaction() {

		Transaction parent = current.get();
		
		if (parent == null) {
			current.set(this);
		}
		current.set(this);

		// setAtomicS(atomicS);

	}

	/**
	 * �ύ����
	 */
	public void commitTransaction(Object x) {

		 Transaction tx = current.get();
	     
		 
		  
//		 
//		 boolean flag = atomicS.getAtomicStampedRef().compareAndSet("��ʿ��", "��˧", 0,
//				 atomicS.getAtomicStampedRef().getStamp() + 1);
//		 
		 		 
		 if(atomicS.getState()==2)
			 rollBackTransaction();
		 else{
			 atomicS.setState(2);
			 System.out.println("����---"+tx.getNumber()+"---�ύ�ɹ���ֵΪ��"+atomicS.getAtomicStampedRef().getReference()
					 +"Stampֵ��"+atomicS.getAtomicStampedRef().getStamp());
		 }
	}

	/**
	 * ��������
	 */

	public void updateTransaction(Object newReference) {

		
		
		//
		// AtomicStampedReference<Object> atomicStampedRef
		// =atomicS.getAtomicStampedRef();
		// boolean flag=
		// atomicStampedRef.compareAndSet(atomicStampedRef.getReference(),
		// newReference,
		// atomicStampedRef.getStamp(), atomicStampedRef.getStamp());
		// if(flag){
		// commitTransaction();
		// //�����ύ״̬
		// atomicS.setState(2);
		// }else{
		//
		// rollBackTransaction();
		// }

	}

	/**
	 * �ع�����
	 */

	public void rollBackTransaction() {

		
		
		
	}

	protected static ExecutorService threadPool = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2, new ThreadFactory() {
				@Override
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				}
			});

	public void initThreadPool(int numberThreads) {
		threadPool = Executors.newFixedThreadPool(numberThreads, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				return t;
			}
		});
	}
	// protected static final ThreadLocal<Transaction> current = new
	// ThreadLocal<Transaction>();
	//
	// protected final Transaction parentTra;
	//
	// protected int number;
	//
	// public Transaction(int number) {
	// this(null, number);
	// }
	// public Transaction(Transaction parentTra, int number) {
	// this.parentTra = parentTra;
	// this.number = number;
	// }
	//
	// /** ��ȡ��ǰ����
	// */
	// public static Transaction getCurrentTransaction() {
	//
	// return current.get();
	// }
	//
	// /**��������
	// *
	// *
	// */
	// public static Transaction startTransaction() {
	//
	// Transaction tx = null;
	//
	// tx = new UpdateTransaction(1);
	//
	// tx.start();
	// return tx;
	// }
	//
	// /** �ύ����
	// *
	// */
	// public static void commitTransaction() {
	//
	// Transaction tx = current.get();
	// tx.commitTx(true);
	// }
	// public void commitTx(boolean finishAlso) {
	// //doCommit();
	//
	// if (finishAlso) {
	// finishTx();
	// }
	// }
	// private void finishTx() {
	// finish();
	//
	// current.set(this.getParent());
	// }
	// protected static void finish() {
	// // intentionally empty
	// }
	// /**�ر�����
	// *
	// */
	// public static void closeTransaction() {
	//
	// }
	//
	// /**
	// * �ع�
	// *
	// */
	// public static void rollBackTransaction() {
	//
	// }
	//
	// public void start() {
	// current.set(this);
	// }
	// protected Transaction getParent() {
	// return parentTra;
	// }
}
