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
	 * AtomicStamp类
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
	 * 开启事务
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
	 * 提交事务
	 */
	public void commitTransaction(boolean flag) {

		 Transaction tx = current.get();
	     
  
		 if(tx.getAtomicS().getState()==2 || !flag)
			 
			 rollBackTransaction();
		 	 	
		 else{
			 tx.getAtomicS().setState(2);
			 System.out.println("事务---"+tx.getNumber()+"---提交成功，值为："+tx.getAtomicS().getAtomicStampedRef().getReference()
					 +"Stamp值："+tx.getAtomicS().getAtomicStampedRef().getStamp());
		 }
	}

	/**
	 * 操作事务
	 */

	public boolean updateTransaction(Object newReference) {

		 Transaction tx = current.get();
 	 
		 boolean flag = tx.getAtomicS().getAtomicStampedRef().compareAndSet(tx.getAtomicS().getAtomicStampedRef().getReference(), newReference.toString(), 0,
				 tx.getAtomicS().getAtomicStampedRef().getStamp() + 1);
		
		 
		 return flag;
		 
	}

	/**
	 * 回滚事务
	 */

	public void rollBackTransaction() {
		 
		 Transaction tx = current.get();
		 
		 //tx.setAtomicS(atomicS.getAtomicStampPar()); 
		 
		 System.out.println("事务---"+tx.getNumber()+"---提交失败，值为："+tx.getAtomicS().getAtomicStampPar().getAtomicStampedRef().getReference()	
				 +"Stamp值："+tx.getAtomicS().getAtomicStampPar().getAtomicStampedRef().getStamp());
		
		
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
	// /** 获取当前事务
	// */
	// public static Transaction getCurrentTransaction() {
	//
	// return current.get();
	// }
	//
	// /**开启事务
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
	// /** 提交事务
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
	// /**关闭事务
	// *
	// */
	// public static void closeTransaction() {
	//
	// }
	//
	// /**
	// * 回滚
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
