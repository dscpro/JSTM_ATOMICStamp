package com.dsc.test.test;

import com.dsc.test.atomic.AtomicStampRepository;
import com.dsc.test.atomic.CachableTransactionRepository;
import com.dsc.test.atomic.TransactionRepository;
import com.dsc.test.transaction.TransactionManager;

public class UnitTest {

	@org.junit.Test
	public void test() {
		TransactionManager tm = new TransactionManager();
		AtomicStampRepository atomicStamp= new AtomicStampRepository("��ʿ��");		 
		tm.setTransactionRepository(atomicStamp);		
		tm.begin();		
		System.out.println("����汾�ţ�"+tm.getCurrentTransaction().getVersion()+"����״̬��"+tm.getCurrentTransaction().getStatus());
		tm.commit(false);
		System.out.println("����汾�ţ�"+tm.getCurrentTransaction().getVersion()+"����״̬��"+tm.getCurrentTransaction().getStatus());
		System.out.println("�޸ĺ�Refֵ"+atomicStamp.getAtomicStampedRef().getReference()+"+++Stamp:"+atomicStamp.getAtomicStampedRef().getStamp());
	}
	

}
