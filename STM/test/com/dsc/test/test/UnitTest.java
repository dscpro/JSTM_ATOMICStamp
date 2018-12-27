package com.dsc.test.test;

import com.dsc.test.atomic.AtomicStampRepository;
import com.dsc.test.atomic.CachableTransactionRepository;
import com.dsc.test.atomic.TransactionRepository;
import com.dsc.test.transaction.TransactionManager;

public class UnitTest {

	@org.junit.Test
	public void test() {
		TransactionManager tm = new TransactionManager();
		AtomicStampRepository atomicStamp= new AtomicStampRepository("董士程");		 
		tm.setTransactionRepository(atomicStamp);		
		tm.begin();		
		System.out.println("事务版本号："+tm.getCurrentTransaction().getVersion()+"事务状态："+tm.getCurrentTransaction().getStatus());
		tm.commit(false);
		System.out.println("事务版本号："+tm.getCurrentTransaction().getVersion()+"事务状态："+tm.getCurrentTransaction().getStatus());
		System.out.println("修改后Ref值"+atomicStamp.getAtomicStampedRef().getReference()+"+++Stamp:"+atomicStamp.getAtomicStampedRef().getStamp());
	}
	

}
