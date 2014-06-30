package plp.imperative2.declaration;

import plp.imperative2.util.TipoProcedimento;
import plp.imperativeExtendedI18N.command.Comando;
import plp.imperativeExtendedI18N.util.Tipo;

/**
 * Uma definiçao de procedimento é uma declaraçao de comando e parametrosFormais
 * uma declaração de procedimento.
 */
public class DefProcedimento {

	/**
	 * Declaração dos parametrosFormais
	 */
	private ListaDeclaracaoParametro parametrosFormais;

	/**
	 * Declaraçao de Comando
	 */
	private Comando comando;

	/**
	 * Construtor
	 * 
	 * @param parametrosFormais
	 *            Declaração de ListaDeclaracaoParametro
	 * @param comando
	 *            Declaraçao de Comando.
	 */
	public DefProcedimento(ListaDeclaracaoParametro parametrosFormais,
			Comando comando) {
		this.parametrosFormais = parametrosFormais;
		this.comando = comando;
	}

	/**
	 * Obtém o comando do Procedimento.
	 * 
	 * @return o comando
	 */
	public Comando getComando() {
		return comando;
	}

	/**
	 * Obtém os parametrosFormais do Procedimento.
	 * 
	 * @return a ListaDeclaracaoParametro
	 */
	public ListaDeclaracaoParametro getParametrosFormais() {
		return parametrosFormais;
	}

	public Tipo getTipo() {
		return new TipoProcedimento(parametrosFormais.getTipos());
	}
}
