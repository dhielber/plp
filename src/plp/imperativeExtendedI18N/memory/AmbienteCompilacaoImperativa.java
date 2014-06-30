package plp.imperativeExtendedI18N.memory;

import plp.imperativeExtendedI18N.util.Tipo;

public interface AmbienteCompilacaoImperativa extends AmbienteCompilacao {

	public Tipo getTipoEntrada() throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
		EntradaVaziaException;

}
