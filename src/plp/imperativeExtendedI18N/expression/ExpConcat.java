package plp.imperativeExtendedI18N.expression;

import plp.imperativeExtendedI18N.memory.AmbienteCompilacao;
import plp.imperativeExtendedI18N.memory.AmbienteExecucao;
import plp.imperativeExtendedI18N.memory.VariavelJaDeclaradaException;
import plp.imperativeExtendedI18N.memory.VariavelNaoDeclaradaException;
import plp.imperativeExtendedI18N.util.Tipo;
import plp.imperativeExtendedI18N.util.TipoPrimitivo;

/**
* Um objeto desta classe representa uma Expressao de Concatenacao entre
* objetos <code>ValorString</code>
*/
public class ExpConcat extends ExpBinaria{
  

	/**
	 * Controi uma Expressao de Concatenacao com as sub-expressoes especificadas.
	 * Estas sub-expressoes devem ser tais que a avaliacao das mesmas resulta
	 * em <code>ValorString</code>
	 *
	 * @param esq Expressao da esquerda
	 * @param dir Expressao da direita
	 */
	public ExpConcat(Expressao esq, Expressao dir){
		super(esq, dir, "++");
	} 

	/**
	 * Retorna o valor da Expressao de Concatenacao
	 */
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return new ValorString(
					( (ValorString) getEsq().avaliar(amb)).valor() +
					( (ValorString) getDir().avaliar(amb)).valor()
		);
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 *
	 * @param ambiente o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *          <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          declarado mais de uma vez no mesmo bloco do ambiente.
	 */
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return (getEsq().getTipo(ambiente).eString() && getDir().getTipo(ambiente).eString());
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @param ambiente o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente) {
		return TipoPrimitivo.STRING;
	}
	
	@Override
	public ExpBinaria clone() {
		return new ExpConcat(esq.clone(), dir.clone());
	}
}