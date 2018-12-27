package com.dsc.test.test;

import java.util.concurrent.TimeUnit;

import com.dsc.test.atomic.AtomicStampRepository;
import com.dsc.test.transaction.TransactionManager;

public class ConTest {
	static AtomicStampRepository atomicStamp = new AtomicStampRepository("董士程");
	static TransactionManager tm = new TransactionManager();

	public static void main(String[] args) {

		Thread refT1 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				tm.getCurrentTransaction().setNewRef("邵帅");
				System.out.println("邵帅" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				 
				tm.commit(false);
				System.out.println("邵帅" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});
		Thread refT2 = new Thread(new Runnable() {
			@Override
			public void run() {
				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				tm.getCurrentTransaction().setNewRef("刘欢");
				System.out.println("刘欢" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
 
				tm.commit(false);
				System.out.println("刘欢" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
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
//				tm.getCurrentTransaction().setNewRef("宋成龙");
//				System.out.println("宋成龙" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
//						+ tm.getCurrentTransaction().getStatus());
//				tm.commit(false);
//				System.out.println("宋成龙" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
//						+ tm.getCurrentTransaction().getStatus());
//				System.out.println("修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
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