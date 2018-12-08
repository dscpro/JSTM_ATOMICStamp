package com.dsc.Atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public interface AtomicFactory {
	AtomicStamp setAtomicStamp(Object initialRef);
	 
	AtomicStampedReference getAtomicStamp();

}
