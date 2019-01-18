package com.dsc.test.transaction;

import java.util.ArrayList;
import java.util.Deque;

import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

import com.dsc.test.api.TransactionStatus;
import com.dsc.test.api.TransactionType;
import com.dsc.test.atomic.TransactionRepository;
import com.dsc.test.exception.CancellingException;
import com.dsc.test.exception.ConfirmingException;

import com.dsc.test.exception.SystemException;

public class TransactionManager {

	static final Logger logger = Logger.getLogger(TransactionManager.class.getSimpleName());

	private List<TransactionRepository> transactionRepositoryList = new ArrayList<TransactionRepository>();

	private static final ThreadLocal<Deque<Transaction>> CURRENT = new ThreadLocal<Deque<Transaction>>();

	private ExecutorService executorService;

	public void setTransactionRepository(TransactionRepository transactionRepository) {		
		this.transactionRepositoryList.add(transactionRepository);
		
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public TransactionManager() {
	}

	public Transaction begin() {

		Transaction transaction = new Transaction(TransactionType.ROOT);
		// transactionRepository.create(transaction);
		registerTransaction(transaction);
		return transaction;
	}

	public void commit(boolean asyncCommit) {

		final Transaction transaction = getCurrentTransaction();
		
		int flag=0;
		for (TransactionRepository transactionRepository : transactionRepositoryList) {
			flag = transactionRepository.update(transaction,transactionRepository);
		}
	
		if (flag == 1) {
			transaction.changeStatus(TransactionStatus.COMMITTED);

			if (asyncCommit) {
				try {
					executorService.submit(new Runnable() {
						@Override
						public void run() {
							commitTransaction(transaction);
						}
					});
				} catch (Throwable commitException) {
					throw new ConfirmingException(commitException);
				}
			} else {
				commitTransaction(transaction);
			}
		} else {
			rollback(false);
		}
	}

	public void rollback(boolean asyncRollback) {

		final Transaction transaction = getCurrentTransaction();
		transaction.changeStatus(TransactionStatus.CANCELLING);

		//transactionRepositoryList.get(0).update(transaction,transactionRepository);

		if (asyncRollback) {

			try {
				executorService.submit(new Runnable() {
					@Override
					public void run() {
						rollbackTransaction(transaction);
					}
				});
			} catch (Throwable rollbackException) {
				throw new CancellingException(rollbackException);
			}
		} else {

			rollbackTransaction(transaction);
		}
	}

	private void commitTransaction(Transaction transaction) {
		try {
			transaction.commit();
			transactionRepositoryList.get(0).delete(transaction);
		} catch (Throwable commitException) {
			throw new ConfirmingException(commitException);
		}
	}

	private void rollbackTransaction(Transaction transaction) {
		try {
			transaction.rollback();
			transactionRepositoryList.get(0).delete(transaction);
		} catch (Throwable rollbackException) {
			throw new CancellingException(rollbackException);
		}
	}

	public Transaction getCurrentTransaction() {
		if (isTransactionActive()) {
			return CURRENT.get().peek();
		}
		return null;
	}

	public boolean isTransactionActive() {
		Deque<Transaction> transactions = CURRENT.get();
		return transactions != null && !transactions.isEmpty();
	}

	private void registerTransaction(Transaction transaction) {

		if (CURRENT.get() == null) {
			CURRENT.set(new LinkedList<Transaction>());
		}

		CURRENT.get().push(transaction);
	}

	public void cleanAfterCompletion(Transaction transaction) {
		if (isTransactionActive() && transaction != null) {
			Transaction currentTransaction = getCurrentTransaction();
			if (currentTransaction == transaction) {
				CURRENT.get().pop();
				if (CURRENT.get().size() == 0) {
					CURRENT.remove();
				}
			} else {
				throw new SystemException("Illegal transaction when clean after completion");
			}
		}
	}


}
