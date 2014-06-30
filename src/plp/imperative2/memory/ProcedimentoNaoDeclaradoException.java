package plp.imperative2.memory;

import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.memory.IdentificadorNaoDeclaradoException;

public class ProcedimentoNaoDeclaradoException extends
		IdentificadorNaoDeclaradoException {

	private static final long serialVersionUID = -5023666514074530307L;

	public ProcedimentoNaoDeclaradoException(Id id) {
		super("Procedimento " + id + " nao declarado.");
	}

}
