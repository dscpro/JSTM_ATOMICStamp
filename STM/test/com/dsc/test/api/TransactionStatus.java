package com.dsc.test.api;

 
public enum TransactionStatus {

    TRYING(1), COMMITTED(2), CANCELLING(3);

    private int id;

     TransactionStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TransactionStatus valueOf(int id) {

        switch (id) {
            case 1:
                return TRYING;
            case 2:
                return COMMITTED;
            default:
                return CANCELLING;
        }
    }

}
