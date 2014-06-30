package plp.imperativeExtendedI18N.memory;

import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.expression.Valor;

public interface AmbienteExecucaoImperativa extends AmbienteExecucao {

	public void changeValor(Id idArg, Valor valorId)
			throws VariavelNaoDeclaradaException;

	public Valor read() throws EntradaVaziaException;

	public void write(Valor v);

	public ListaValor getSaida();
}
