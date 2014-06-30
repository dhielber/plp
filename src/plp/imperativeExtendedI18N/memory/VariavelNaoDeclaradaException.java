package plp.imperativeExtendedI18N.memory;

import plp.imperativeExtendedI18N.expression.Id;

public class VariavelNaoDeclaradaException extends IdentificadorNaoDeclaradoException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VariavelNaoDeclaradaException(Id id){
		super("Variável " + id + " não declarada.");
	}
}
