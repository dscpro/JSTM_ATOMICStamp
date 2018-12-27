package com.dsc.test.transaction;

import java.io.Serializable;

import com.dsc.test.api.TransactionContext;
import com.dsc.test.api.TransactionContextEditor;
import com.dsc.test.api.TransactionStatus;
import com.dsc.test.api.TransactionXid;
 
public class Participant implements Serializable {

    private static final long serialVersionUID = 4127729421281425247L;

    private TransactionXid xid;

    private InvocationContext confirmInvocationContext;

    private InvocationContext cancelInvocationContext;

    private Terminator terminator = new Terminator();

    Class<? extends TransactionContextEditor> transactionContextEditorClass;

    public Participant() {

    }

    public Participant(TransactionXid xid, InvocationContext confirmInvocationContext, InvocationContext cancelInvocationContext, Class<? extends TransactionContextEditor> transactionContextEditorClass) {
        this.xid = xid;
        this.confirmInvocationContext = confirmInvocationContext;
        this.cancelInvocationContext = cancelInvocationContext;
        this.transactionContextEditorClass = transactionContextEditorClass;
    }

    public Participant(InvocationContext confirmInvocationContext, InvocationContext cancelInvocationContext, Class<? extends TransactionContextEditor> transactionContextEditorClass) {
        this.confirmInvocationContext = confirmInvocationContext;
        this.cancelInvocationContext = cancelInvocationContext;
        this.transactionContextEditorClass = transactionContextEditorClass;
    }

    public void setXid(TransactionXid xid) {
        this.xid = xid;
    }

    public void rollback() {
        terminator.invoke(new TransactionContext(xid, TransactionStatus.CANCELLING.getId()), cancelInvocationContext, transactionContextEditorClass);
    }

    public void commit() {
        terminator.invoke(new TransactionContext(xid, TransactionStatus.COMMITTED.getId()), confirmInvocationContext, transactionContextEditorClass);
    }

    public Terminator getTerminator() {
        return terminator;
    }

    public TransactionXid getXid() {
        return xid;
    }

    public InvocationContext getConfirmInvocationContext() {
        return confirmInvocationContext;
    }

    public InvocationContext getCancelInvocationContext() {
        return cancelInvocationContext;
    }

}
