package com.dsc.test.atomic;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import javax.transaction.xa.Xid;

import com.dsc.test.api.TransactionXid;
import com.dsc.test.exception.OptimisticLockException;
import com.dsc.test.transaction.Transaction;

public abstract class CachableTransactionRepository implements TransactionRepository {

	private int expireDuration = 120;

	private Cache<Xid, Transaction> transactionXidCompensableTransactionCache;

	@Override
	public int create(Transaction transaction) {
		int result = doCreate(transaction);
		if (result > 0) {
			putToCache(transaction);
		}
		return result;
	}

	@Override
	public int update(Transaction transaction,TransactionRepository transactionRepository) {
		int result = 0;

		try {
			result = doUpdate(transaction,transactionRepository);
			if (result > 0) {
				putToCache(transaction);
			} else {
				//throw new OptimisticLockException();
				
			}
		} finally {
			if (result <= 0) {
				removeFromCache(transaction);
			}
		}

		return result;
	}
	  @Override
	    public int delete(Transaction transaction) {
	        int result = 0;

	        try {
	            result = doDelete(transaction);

	        } finally {
	            removeFromCache(transaction);
	        }
	        return result;
	    }


	public CachableTransactionRepository() {
		transactionXidCompensableTransactionCache = CacheBuilder.newBuilder()
				.expireAfterAccess(expireDuration, TimeUnit.SECONDS).maximumSize(1000).build();
	}

	protected void putToCache(Transaction transaction) {
		transactionXidCompensableTransactionCache.put(transaction.getXid(), transaction);
	}

	protected void removeFromCache(Transaction transaction) {
		transactionXidCompensableTransactionCache.invalidate(transaction.getXid());
	}

	protected Transaction findFromCache(TransactionXid transactionXid) {
		return transactionXidCompensableTransactionCache.getIfPresent(transactionXid);

	}

	public void setExpireDuration(int durationInSeconds) {
		this.expireDuration = durationInSeconds;
	}

	protected abstract int doCreate(Transaction transaction);

	protected abstract int doUpdate(Transaction transaction,TransactionRepository transactionRepository);

	protected abstract int doDelete(Transaction transaction);
}
