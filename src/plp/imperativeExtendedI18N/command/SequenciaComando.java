package plp.imperativeExtendedI18N.command;

import plp.imperativeExtendedI18N.memory.AmbienteCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.AmbienteExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.EntradaVaziaException;
import plp.imperativeExtendedI18N.memory.ErroTipoEntradaException;
import plp.imperativeExtendedI18N.memory.IdentificadorJaDeclaradoException;
import plp.imperativeExtendedI18N.memory.IdentificadorNaoDeclaradoException;

public class SequenciaComando implements Comando {

	private Comando comando1;
	private Comando comando2;

	public SequenciaComando(Comando comando1, Comando comando2) {
		this.comando1 = comando1;
		this.comando2 = comando2;
	}

	/**
	 * Executa os comandos sequencialmente.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * 
	 * @return o ambiente depois de modificado pela execu��o dos comandos.
	 * @throws ErroTipoEntradaException 
	 * 
	 */
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException, ErroTipoEntradaException {
		return comando2.executar(comando1.executar(ambiente));
	}

	/**
	 * Realiza a verificacao de tipos dos comandos
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se os comandos s�o bem tipados;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return comando1.checaTipo(ambiente) && comando2.checaTipo(ambiente);
	}
}
