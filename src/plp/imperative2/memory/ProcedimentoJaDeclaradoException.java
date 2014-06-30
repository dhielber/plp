package plp.imperative2.memory;

import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.memory.IdentificadorJaDeclaradoException;

public class ProcedimentoJaDeclaradoException extends
		IdentificadorJaDeclaradoException {

	private static final long serialVersionUID = -1793156786677618760L;

	public ProcedimentoJaDeclaradoException(Id id) {
		super("Procedimento " + id + " já declarado.");
	}

}
