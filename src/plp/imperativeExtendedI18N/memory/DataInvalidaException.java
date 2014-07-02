package plp.imperativeExtendedI18N.memory;

import plp.imperativeExtendedI18N.expression.ValorDataL;

public class DataInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataInvalidaException(ValorDataL data) {
		super("Data " + data.toString() + " inválida.");
	}

	public DataInvalidaException() {
		super();
	}
}
