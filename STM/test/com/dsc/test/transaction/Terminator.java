package com.dsc.test.transaction;

import java.io.Serializable;
import java.lang.reflect.Method;
import com.dsc.test.api.TransactionContext;
import com.dsc.test.api.TransactionContextEditor;
import com.dsc.test.exception.SystemException;
import com.dsc.test.util.StringUtils;

 
public class Terminator implements Serializable {

    private static final long serialVersionUID = -164958655471605778L;


    public Terminator() {

    }

    public Object invoke(TransactionContext transactionContext, InvocationContext invocationContext, Class<? extends TransactionContextEditor> transactionContextEditorClass) {


        if (StringUtils.isNotEmpty(invocationContext.getMethodName())) {

            try {

                Object target = FactoryBuilder.factoryOf(invocationContext.getTargetClass()).getInstance();

                Method method = null;

                method = target.getClass().getMethod(invocationContext.getMethodName(), invocationContext.getParameterTypes());

                FactoryBuilder.factoryOf(transactionContextEditorClass).getInstance().set(transactionContext, target, method, invocationContext.getArgs());

                return method.invoke(target, invocationContext.getArgs());

            } catch (Exception e) {
                throw new SystemException(e);
            }
        }
        return null;
    }
}
