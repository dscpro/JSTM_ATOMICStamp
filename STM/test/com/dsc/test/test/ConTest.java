package com.dsc.test.test;

import java.util.concurrent.TimeUnit;

import com.dsc.test.atomic.AtomicStampRepository;
import com.dsc.test.transaction.TransactionManager;

public class ConTest {
	static AtomicStampRepository atomicStamp = new AtomicStampRepository("��ʿ��");
	static TransactionManager tm = new TransactionManager();

	public static void main(String[] args) {

		Thread refT1 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				tm.getCurrentTransaction().setNewRef("��˧");
				System.out.println("��˧" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				 
				tm.commit(false);
				System.out.println("��˧" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});
		Thread refT2 = new Thread(new Runnable() {
			@Override
			public void run() {
				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				tm.getCurrentTransaction().setNewRef("����");
				System.out.println("����" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
 
				tm.commit(false);
				System.out.println("����" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());

			}
		});
		
		refT1.start();
		refT2.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
		}
	    System.out.println(atomicStamp.getOldRef().toString()+atomicStamp.getAtomicStampedRef().getReference());

//		Thread refT3 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//
//				tm.setTransactionRepository(atomicStamp);
//				tm.begin();
//				tm.getCurrentTransaction().setNewRef("�γ���");
//				System.out.println("�γ���" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
//						+ tm.getCurrentTransaction().getStatus());
//				tm.commit(false);
//				System.out.println("�γ���" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
//						+ tm.getCurrentTransaction().getStatus());
//				System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
//						+ atomicStamp.getAtomicStampedRef().getStamp());
//			}
//		});
//
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//		}
//		refT3.start();
//		 
	}
}