package plp.imperativeExtendedI18N.command;

import plp.imperativeExtendedI18N.expression.Expressao;
import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.expression.ValorDataL;
import plp.imperativeExtendedI18N.expression.ValorString;
import plp.imperativeExtendedI18N.memory.AmbienteCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.AmbienteExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.EnumPadraoData;
import plp.imperativeExtendedI18N.memory.ImpressaoDataException;
import plp.imperativeExtendedI18N.memory.VariavelJaDeclaradaException;
import plp.imperativeExtendedI18N.memory.VariavelNaoDeclaradaException;

public class WriteD implements IO {
	
	private Expressao expressaoData;
	private ValorString formatoData;

	public WriteD(Expressao expressaoData, ValorString formatoData) {
		this.expressaoData = expressaoData;
		this.formatoData = formatoData;
	}

	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		ambiente.writeD((ValorDataL) this.expressaoData.avaliar(ambiente),
				this.formatoData);
		return ambiente;
	}

	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ImpressaoDataException {

		if (!(this.expressaoData instanceof ValorDataL) && (this.expressaoData instanceof Id && !this.expressaoData.getTipo(ambiente).eDataL())) {

			throw new ImpressaoDataException(
					"A expressão passada não é do tipo DataL.");
		}
		
		if (!this.formatoData.valor().equalsIgnoreCase(EnumPadraoData.SHORT.toString())
				&& !this.formatoData.valor().equalsIgnoreCase(EnumPadraoData.MEDIUM.toString())
				&& !this.formatoData.valor().equalsIgnoreCase(EnumPadraoData.LONG.toString())) {
			throw new ImpressaoDataException("Digite o comando corretamente (short, medium, long).");
		}

		return this.expressaoData.checaTipo(ambiente);
	}

}
