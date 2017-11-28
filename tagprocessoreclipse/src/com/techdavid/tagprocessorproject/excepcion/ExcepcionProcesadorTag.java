package com.techdavid.tagprocessorproject.excepcion;

public class ExcepcionProcesadorTag extends Exception {
	public ExcepcionProcesadorTag() {
		super();
	}
	public ExcepcionProcesadorTag(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcepcionProcesadorTag(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcepcionProcesadorTag(String message) {
		super(message);
	}

	public ExcepcionProcesadorTag(Throwable cause) {
		super(cause);
	}
}
