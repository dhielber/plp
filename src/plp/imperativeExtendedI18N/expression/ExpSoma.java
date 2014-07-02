package plp.imperativeExtendedI18N.expression;

import plp.imperativeExtendedI18N.memory.AmbienteCompilacao;
import plp.imperativeExtendedI18N.memory.AmbienteExecucao;
import plp.imperativeExtendedI18N.memory.VariavelJaDeclaradaException;
import plp.imperativeExtendedI18N.memory.VariavelNaoDeclaradaException;
import plp.imperativeExtendedI18N.util.Tipo;
import plp.imperativeExtendedI18N.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma Expressao de Soma.
 */
public class ExpSoma extends ExpBinaria {

	public ExpSoma(Expressao esq, Expressao dir) {
		super(esq, dir, "+");
	}

	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		if(getEsq().avaliar(amb) instanceof ValorFloat && getDir().avaliar(amb) instanceof ValorFloat){		
			return new ValorFloat(
				((ValorFloat) getEsq().avaliar(amb)).valor() +
				((ValorFloat) getDir().avaliar(amb)).valor());
		}
		
		return new ValorInteiro(
				((ValorInteiro) getEsq().avaliar(amb)).valor() +
				((ValorInteiro) getDir().avaliar(amb)).valor());
	}
	
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao ambiente) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return (getEsq().getTipo(ambiente).eInteiro() && getDir().getTipo(ambiente).eInteiro()
				|| 
				getEsq().getTipo(ambiente).eFloat() && getDir().getTipo(ambiente).eFloat());
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @param ambiente o ambiente de compilação.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente) {
		
		if(getEsq().getTipo(ambiente).eIgual(TipoPrimitivo.FLOAT) && getDir().getTipo(ambiente).eIgual(TipoPrimitivo.FLOAT)){
			return TipoPrimitivo.FLOAT;
		}
		
		return TipoPrimitivo.INTEIRO;
	}
	
	@Override
	public ExpBinaria clone() {
		return new ExpSoma(esq.clone(), dir.clone());
	}
}
