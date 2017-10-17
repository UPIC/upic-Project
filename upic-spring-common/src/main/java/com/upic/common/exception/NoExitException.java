package com.upic.common.exception;

public class NoExitException extends Exception {
	public NoExitException() {
	}

	public NoExitException(String paramString) {
		super(paramString);
	}

	public NoExitException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}

	public NoExitException(Throwable paramThrowable) {
		super(paramThrowable);
	}

	protected NoExitException(String paramString, Throwable paramThrowable, boolean paramBoolean1,
			boolean paramBoolean2) {
		super(paramString, paramThrowable, paramBoolean1, paramBoolean2);
	}
}
