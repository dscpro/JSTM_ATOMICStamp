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
				//tm.getCurrentTransaction().setNewRef("��˧");
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
				//tm.getCurrentTransaction().setNewRef("����");
				System.out.println("����" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
 
				tm.commit(false);
				System.out.println("����" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());

			}
		});
		
		
	
		Thread refT3 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				//tm.getCurrentTransaction().setNewRef("�γ���");
				System.out.println("�γ���" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("�γ���" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});
		Thread refT4 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				//tm.begin();
				//tm.getCurrentTransaction().setNewRef("�γ���1");
				System.out.println("�γ���1" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("�γ���1" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});

		Thread refT5 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				//tm.getCurrentTransaction().setNewRef("�γ���2");
				System.out.println("�γ���2" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("�γ���2" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});

		Thread refT6 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				//tm.getCurrentTransaction().setNewRef("�γ���3");
				System.out.println("�γ���3" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("�γ���3" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});

		Thread refT7 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				//tm.getCurrentTransaction().setNewRef("�γ���4");
				System.out.println("�γ���4" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("�γ���4" + "����汾�ţ�" + tm.getCurrentTransaction().getVersion() + "����״̬��"
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("�޸ĺ�Refֵ" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});

	
		refT1.start();
		refT2.start();
		refT3.start();
		refT4.start();
		refT5.start(); 
		refT6.start();
		refT7.start(); 
	}
}