package com.dsc.test.atomic;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.dsc.test.api.TransactionXid;
import com.dsc.test.transaction.Transaction;
public interface TransactionRepository {
	   int create(Transaction transaction);

	    int update(Transaction transaction,TransactionRepository transactionRepository);
//
        int delete(Transaction transaction);
//
//	    Transaction findByXid(TransactionXid xid);
//
//	    List<Transaction> findAllUnmodifiedSince(Date date);
}
