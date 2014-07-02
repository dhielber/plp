package plp.imperativeExtendedI18N.memory;

import plp.imperativeExtendedI18N.expression.ValorStringL;

public class RecursoNaoDeclaradoException extends IdentificadorNaoDeclaradoException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecursoNaoDeclaradoException(ValorStringL recurso){
		super("Recurso " + recurso.toString() + " não declarado.");
	}
}
