package plp.imperative2.declaration;

import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.memory.AmbienteCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.VariavelJaDeclaradaException;
import plp.imperativeExtendedI18N.memory.VariavelNaoDeclaradaException;
import plp.imperativeExtendedI18N.util.Tipo;

public class DeclaracaoParametro {

	private Id id;

	private Tipo tipo;

	public DeclaracaoParametro(Id id, Tipo tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	public Id getId() {
		return id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente) {
		return tipo.eValido();
	}

	/**
	 * Cria um mapeamento do identificador para o tipo do parametro desta
	 * declaração no AmbienteCompilacaoImperativa2
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificador e seu
	 *            tipo.
	 * 
	 * @return o ambiente modificado pela declaração do parametro.
	 */
	public AmbienteCompilacaoImperativa elabora(
			AmbienteCompilacaoImperativa ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ambiente.map(id, tipo);
		return ambiente;
	}

}
