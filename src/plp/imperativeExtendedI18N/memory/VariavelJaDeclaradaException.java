package plp.imperativeExtendedI18N.memory;

import plp.imperativeExtendedI18N.expression.Id;

public class VariavelJaDeclaradaException extends IdentificadorJaDeclaradoException{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VariavelJaDeclaradaException(Id id){
		super("Variável " + id + " já declarada.");
	}
}
