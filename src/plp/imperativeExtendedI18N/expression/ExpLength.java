package plp.imperativeExtendedI18N.expression;

import plp.imperativeExtendedI18N.memory.AmbienteCompilacao;
import plp.imperativeExtendedI18N.memory.AmbienteExecucao;
import plp.imperativeExtendedI18N.memory.VariavelJaDeclaradaException;
import plp.imperativeExtendedI18N.memory.VariavelNaoDeclaradaException;
import plp.imperativeExtendedI18N.util.Tipo;
import plp.imperativeExtendedI18N.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma Expressao de tamanho de String.
 */
public class ExpLength extends ExpUnaria {
 
	/**
	 * Controi uma Expressao de tamanho  com a expressao especificada
	 * assume-se que <code>exp</code> &eacute; uma expressao cuja avaliacao
	 * resulta num <code>ValorString</code>
	 * 
	 * @param exp a express�o em quest�o.
	 */
	public ExpLength(Expressao exp) {
		super(exp, "length");
	}

	/**
	 * Retorna o valor da Expressao de tamanho.
	 * 
	 * @param amb o ambiente de execu��o.
	 * @return o valor da express�o avaliada.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          declarado mais de uma vez no mesmo bloco do ambiente.
	 */
	public Valor avaliar(AmbienteExecucao amb) throws VariavelJaDeclaradaException,
			VariavelNaoDeclaradaException {
		return new ValorInteiro (((ValorString)getExp().avaliar(amb)).valor().length());
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param amb o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *          <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          declarado mais de uma vez no mesmo bloco do ambiente.
	 */
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb) throws VariavelJaDeclaradaException,
			VariavelNaoDeclaradaException {
		return (getExp().getTipo(amb).eString());
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @param amb o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.INTEIRO;
	}
	
	@Override
	public ExpUnaria clone() {
		return new ExpLength(exp.clone());
	}
}