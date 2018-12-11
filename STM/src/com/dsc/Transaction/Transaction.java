package com.dsc.Transaction;

 
public class Transaction {
	protected static final ThreadLocal<Transaction> current = new ThreadLocal<Transaction>();
	
	protected final Transaction parentTra;
	
	protected int number;
	
	public Transaction(int number) {
        this(null, number);
    }
	public Transaction(Transaction parentTra, int number) {
	        this.parentTra = parentTra;
	        this.number = number;
	}
	
	/** ��ȡ��ǰ����
	*/
	public static Transaction getCurrentTransaction() {

		return current.get();
	}

	/**��������
	 * 
	 * 
	 */
	public static Transaction startTransaction() {

		Transaction tx = null;

		tx = new UpdateTransaction(1);

		tx.start();
		return tx;
	}

	/** �ύ����
	 * 
	 */
	public static void commitTransaction() {

		Transaction tx = current.get();
        tx.commitTx(true);
	}
	public void commitTx(boolean finishAlso) {
        //doCommit();

        if (finishAlso) {
            finishTx();
        }
    }
	private void finishTx() {
        finish();

        current.set(this.getParent());
    }
	 protected static void finish() {
	        // intentionally empty
	    }
	 /**�ر�����
	  * 
	  */
	public static void closeTransaction() {
		
	}

	/**
	 * �ع�
	 * 
	 */
	public static void rollBackTransaction() {
		
	}

	public  void  start() {
		current.set(this);
	}
	protected  Transaction getParent() {
        return parentTra;
    }
}
