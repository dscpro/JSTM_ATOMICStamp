package com.dsc.test.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.dsc.test.atomic.AtomicStampRepository;
import com.dsc.test.transaction.TransactionManager;

public class ConTest2 {
	static AtomicStampRepository atomicStamp = new AtomicStampRepository("��ʿ��");
	static AtomicStampRepository atomicStamp1 = new AtomicStampRepository("��ʿ��1");
	static AtomicStampRepository atomicStamp2 = new AtomicStampRepository("��ʿ��2");
	static AtomicStampRepository atomicStamp3 = new AtomicStampRepository("��ʿ��3");
	static Integer N=50;
	public static void main(String[] args) {		
		Thread refT[] = new Thread[N];
		int i ;
		//Random ra =new Random();
		for ( i = 0; i < N; i++) {
			refT[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					//int y=(ra.nextInt(1000)+1);
					TransactionManager tm = new TransactionManager();					
					tm.begin();
					int x = tm.getCurrentTransaction().getXid().hashCode();
					tm.setTransactionRepository(atomicStamp);
					tm.setTransactionRepository(atomicStamp1);
					tm.setTransactionRepository(atomicStamp2);
					tm.setTransactionRepository(atomicStamp3);
					atomicStamp.setNewRef("��˧"+x);
					atomicStamp1.setNewRef("��˧"+x);
					atomicStamp2.setNewRef("��˧"+x);
					atomicStamp3.setNewRef("��˧"+x);					
					tm.commit(false);
					System.out.println(x+"����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
							+ tm.getCurrentTransaction().getStatus());
					}
			});
		}
		for (int j = 0; j < N; j++) {
			refT[j].start();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
		}
		System.out.println("0�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference());
		System.out.println("1�޸ĺ�Refֵ" + atomicStamp1.getAtomicStampedRef().getReference());
		System.out.println("2�޸ĺ�Refֵ" + atomicStamp2.getAtomicStampedRef().getReference());
		System.out.println("3�޸ĺ�Refֵ" + atomicStamp3.getAtomicStampedRef().getReference());
	}
}
 