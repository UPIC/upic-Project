package com.upic.common.exception;

public class NeedRollBackException extends Exception {
	public NeedRollBackException() {
	}

	public NeedRollBackException(String paramString) {
		super(paramString);
	}

	public NeedRollBackException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}

	public NeedRollBackException(Throwable paramThrowable) {
		super(paramThrowable);
	}

	protected NeedRollBackException(String paramString, Throwable paramThrowable, boolean paramBoolean1,
			boolean paramBoolean2) {
		super(paramString, paramThrowable, paramBoolean1, paramBoolean2);
	}
}
