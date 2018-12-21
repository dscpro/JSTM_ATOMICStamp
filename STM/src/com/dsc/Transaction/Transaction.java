package com.dsc.Transaction;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

import com.dsc.Atomic.AtomicStamp;

public class Transaction {
	protected int number;
	protected final Transaction parent;
	protected ConcurrentHashMap<AtomicInteger,Object> localMap=new ConcurrentHashMap<AtomicInteger,Object>();
	protected static final AtomicInteger version=new AtomicInteger(0);
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
	public static AtomicStamp atomicS;

	public AtomicStamp getAtomicS() {
		return atomicS;
	}

	public void setAtomicS(AtomicStamp atomicS) {
		this.atomicS = atomicS;
	}

	protected static final ThreadLocal<Transaction> current = new ThreadLocal<Transaction>();

	public static Transaction getCurrent() {
		return current.get();
	}
	public static void beginTransaction(){
//		Transaction tx=Transaction.getCurrent();
//		if(tx==null){
			Transaction tx=new Transaction(1);
			current.set(tx);
//		}else{
//			
//			current.set(this);
//		}
	}

	/**
	 * ��������
	 */
	public void startTransaction() {

		Transaction parent = current.get();
		
		if (parent == null) {
			current.set(this);
		}

	}

	/**
	 * �ύ����
	 */
	public void commitTransaction(boolean flag) {

		 Transaction tx = current.get();
		 
		 
//		 boolean flag = atomicS.getAtomicStampedRef().compareAndSet("��ʿ��", "��˧", 0,
//				 atomicS.getAtomicStampedRef().getStamp() + 1);
//		 
		 		 
		 if(atomicS.getState()==2||!flag)
			 rollBackTransaction();
		 		
		 else{
			 atomicS.setState(2);
			 System.out.println("����---"+tx.getNumber()+"---�ύ�ɹ���ֵΪ��"+atomicS.getAtomicStampedRef().getReference()
					 +"Stampֵ��"+atomicS.getAtomicStampedRef().getStamp());
		 }
	}
	//д����
	public void commitTransaction(AtomicStamp num) {
		 boolean flag;
		 Transaction tx = current.get();
		 atomicS=num;
		 if(atomicS.getState()==2){
			 rollBackTransaction();
		 }
		 flag=num.getAtomicStampedRef().compareAndSet(num.getAtomicStampedRef().getReference(), tx.getTail(),
				num.getAtomicStampedRef().getStamp(), num.getAtomicStampedRef().getStamp()+1);
		 num.setState(2);
		 //System.out.println(flag); 
//		 if(flag){
//			 num.setState(2);
//		 }else{
//			 tx.rollBackTransaction();
//		 }
		 
	}
	
	public static void commitTransaction(){
		atomicS.setState(1);
	}
	/**
	 * ��������
	 */

	public void updateTransaction(Object newReference) {
		

	}

	/**
	 * �ع�����
	 */

	public void rollBackTransaction() {
		 
		 Transaction tx = current.get();
//		 System.out.println("����---"+tx.getNumber()+"---�ύʧ�ܣ�ֵΪ��"+atomicS.getAtomicStampedRef().getReference()
//				 +"Stampֵ��"+atomicS.getAtomicStampedRef().getStamp());
//		
		
	}
	
	public void setStampValue(Object newE){
		
		localMap.put(version, newE);
		version.incrementAndGet();
		
	}
	//��ȡ����ύ��ֵ
	protected Object getTail(){
		Iterator<Entry<AtomicInteger, Object>> iterator = localMap.entrySet().iterator();
	    Entry<AtomicInteger, Object> tail = null;
	    while (iterator.hasNext()) {
	        tail = iterator.next();
	    }
	    return tail.getValue();
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
	
}
