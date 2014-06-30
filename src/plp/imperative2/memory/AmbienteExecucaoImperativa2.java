package plp.imperative2.memory;

import plp.imperative2.declaration.DefProcedimento;
import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.memory.AmbienteExecucaoImperativa;

public interface AmbienteExecucaoImperativa2 extends AmbienteExecucaoImperativa {

	public void mapProcedimento(Id idArg, DefProcedimento procedimentoId)
			throws ProcedimentoJaDeclaradoException;

	public DefProcedimento getProcedimento(Id idArg)
			throws ProcedimentoNaoDeclaradoException;

}
