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
				//tm.getCurrentTransaction().setNewRef("邵帅");
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
				//tm.getCurrentTransaction().setNewRef("刘欢");
				System.out.println("刘欢" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
 
				tm.commit(false);
				System.out.println("刘欢" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());

			}
		});
		
		
	
		Thread refT3 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				//tm.getCurrentTransaction().setNewRef("宋成龙");
				System.out.println("宋成龙" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("宋成龙" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});
		Thread refT4 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				//tm.begin();
				//tm.getCurrentTransaction().setNewRef("宋成龙1");
				System.out.println("宋成龙1" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("宋成龙1" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});

		Thread refT5 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				//tm.getCurrentTransaction().setNewRef("宋成龙2");
				System.out.println("宋成龙2" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("宋成龙2" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});

		Thread refT6 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				//tm.getCurrentTransaction().setNewRef("宋成龙3");
				System.out.println("宋成龙3" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("宋成龙3" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
						+ atomicStamp.getAtomicStampedRef().getStamp());
			}
		});

		Thread refT7 = new Thread(new Runnable() {
			@Override
			public void run() {

				tm.setTransactionRepository(atomicStamp);
				tm.begin();
				//tm.getCurrentTransaction().setNewRef("宋成龙4");
				System.out.println("宋成龙4" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				tm.commit(false);
				System.out.println("宋成龙4" + "事务版本号：" + tm.getCurrentTransaction().getVersion() + "事务状态："
						+ tm.getCurrentTransaction().getStatus());
				System.out.println("修改后Ref值" + atomicStamp.getAtomicStampedRef().getReference() + "+++Stamp:"
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