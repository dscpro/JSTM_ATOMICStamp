package com.dsc.test.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.dsc.test.atomic.AtomicStampRepository;
import com.dsc.test.transaction.TransactionManager;

public class ConTest2 {
	static AtomicStampRepository atomicStamp = new AtomicStampRepository("董士程");
	static AtomicStampRepository atomicStamp1 = new AtomicStampRepository("董士程1");
	static AtomicStampRepository atomicStamp2 = new AtomicStampRepository("董士程2");
	static AtomicStampRepository atomicStamp3 = new AtomicStampRepository("董士程3");
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
					atomicStamp.setNewRef("邵帅"+x);
					atomicStamp1.setNewRef("邵帅"+x);
					atomicStamp2.setNewRef("邵帅"+x);
					atomicStamp3.setNewRef("邵帅"+x);					
					tm.commit(false);
					System.out.println(x+"事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
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
		System.out.println("0修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference());
		System.out.println("1修改后Ref值" + atomicStamp1.getAtomicStampedRef().getReference());
		System.out.println("2修改后Ref值" + atomicStamp2.getAtomicStampedRef().getReference());
		System.out.println("3修改后Ref值" + atomicStamp3.getAtomicStampedRef().getReference());
	}
}
 