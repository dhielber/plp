package plp.imperative2;

import plp.imperativeExtendedI18N.command.Comando;
import plp.imperativeExtendedI18N.memory.AmbienteCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.AmbienteExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.EntradaVaziaException;
import plp.imperativeExtendedI18N.memory.ErroTipoEntradaException;
import plp.imperativeExtendedI18N.memory.IdentificadorJaDeclaradoException;
import plp.imperativeExtendedI18N.memory.IdentificadorNaoDeclaradoException;
import plp.imperativeExtendedI18N.memory.ListaValor;

public class Programa {

	private Comando comando;

	public Programa(Comando comando) {
		this.comando = comando;
	}

	/**
	 * Executa o programa.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * 
	 * @return o ambiente depois de modificado pela execu��o do programa.
	 * @throws ErroTipoEntradaException 
	 * 
	 * @exception EntradaNaoFornecidaException
	 *                se n�o for fornecida a tail de valores de entrada do
	 *                programa.
	 * 
	 */
	public ListaValor executar(AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException, ErroTipoEntradaException {
		ambiente = comando.executar(ambiente);
		return ambiente.getSaida();
	}

	/**
	 * Realiza a verificacao de tipos do programa
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se o programa est� bem tipado;
	 *         <code>false</code> caso contrario.
	 * 
	 * @exception EntradaNaoFornecidaException
	 *                se n�o for fornecida a tail de valores de entrada do
	 *                programa.
	 * 
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return comando.checaTipo(ambiente);
	}

}
