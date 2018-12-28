package com.dsc.test.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.dsc.test.atomic.AtomicStampRepository;
import com.dsc.test.transaction.TransactionManager;

public class ConTest2 {
	static AtomicStampRepository atomicStamp = new AtomicStampRepository("��ʿ��");
	static TransactionManager tm = new TransactionManager();

	public static void main(String[] args) {
		Thread refT[] = new Thread[100];
		int i ;
		Random ra =new Random();
		for ( i = 0; i < 100; i++) {
			refT[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					int y=(ra.nextInt(1000)+1);
					tm.setTransactionRepository(atomicStamp);
					tm.begin();
					tm.getCurrentTransaction().setNewRef("��˧"+y);
					System.out.println("��˧"+y+ "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
							+ tm.getCurrentTransaction().getStatus());
					tm.commit(false);
					System.out.println("��˧"+y+ "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
							+ tm.getCurrentTransaction().getStatus());
					
				}
			});
		}

		for (int j = 0; j < 100; j++) {

			refT[j].start();
		}
		try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
		}
		System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
				+ atomicStamp.getAtomicStampedRef().getStamp());
	}
}
 