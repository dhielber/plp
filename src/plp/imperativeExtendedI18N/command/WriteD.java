package plp.imperativeExtendedI18N.command;

import plp.imperativeExtendedI18N.expression.ValorDataL;
import plp.imperativeExtendedI18N.expression.ValorString;
import plp.imperativeExtendedI18N.memory.AmbienteCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.AmbienteExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.VariavelJaDeclaradaException;
import plp.imperativeExtendedI18N.memory.VariavelNaoDeclaradaException;

public class WriteD implements IO {

	private ValorDataL expressionData;
	private ValorString formatoData;

	
	public WriteD(ValorDataL val, ValorString formatoData) {
		this.expressionData = val;
		this.formatoData = formatoData;		
	}

	public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		ambiente.write(this.expressionData.avaliar(ambiente));
		return ambiente;
	}
	
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		return this.expressionData.checaTipo(ambiente);
	}

}
