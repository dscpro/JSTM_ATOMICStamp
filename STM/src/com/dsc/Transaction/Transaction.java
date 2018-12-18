package com.dsc.Transaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
 

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
	public void commitTransaction(boolean flag) {

		 Transaction tx = current.get();
	     
  
		 if(tx.getAtomicS().getState()==2 || !flag)
			 
			 rollBackTransaction();
		 	 	
		 else{
			 tx.getAtomicS().setState(2);
			 System.out.println("����---"+tx.getNumber()+"---�ύ�ɹ���ֵΪ��"+tx.getAtomicS().getAtomicStampedRef().getReference()
					 +"Stampֵ��"+tx.getAtomicS().getAtomicStampedRef().getStamp());
		 }
	}

	/**
	 * ��������
	 */

	public boolean updateTransaction(Object newReference) {

		 Transaction tx = current.get();
 	 
		 boolean flag = tx.getAtomicS().getAtomicStampedRef().compareAndSet(tx.getAtomicS().getAtomicStampedRef().getReference(), newReference.toString(), 0,
				 tx.getAtomicS().getAtomicStampedRef().getStamp() + 1);
		
		 
		 return flag;
		 
	}

	/**
	 * �ع�����
	 */

	public void rollBackTransaction() {
		 
		 Transaction tx = current.get();
		 
		 //tx.setAtomicS(atomicS.getAtomicStampPar()); 
		 
		 System.out.println("����---"+tx.getNumber()+"---�ύʧ�ܣ�ֵΪ��"+tx.getAtomicS().getAtomicStampPar().getAtomicStampedRef().getReference()	
				 +"Stampֵ��"+tx.getAtomicS().getAtomicStampPar().getAtomicStampedRef().getStamp());
		
		
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
