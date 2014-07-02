package plp.imperativeExtendedI18N.memory;

import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.expression.Valor;
import plp.imperativeExtendedI18N.expression.ValorDataL;
import plp.imperativeExtendedI18N.expression.ValorString;
import plp.imperativeExtendedI18N.expression.ValorStringL;

public interface AmbienteExecucaoImperativa extends AmbienteExecucao {

	public void changeValor(Id idArg, Valor valorId)
			throws VariavelNaoDeclaradaException;

	public Valor read() throws EntradaVaziaException;

	public void write(Valor v);

	public void writeD(ValorDataL v, ValorString tipoData);

	public ListaValor getSaida();

	public void avaliarSeRecursoExiste(ValorStringL recurso);

	public void avaliarData(ValorDataL data);
}
