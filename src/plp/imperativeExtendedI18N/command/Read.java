package plp.imperativeExtendedI18N.command;

import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.expression.Valor;
import plp.imperativeExtendedI18N.memory.AmbienteCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.AmbienteExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.EntradaVaziaException;
import plp.imperativeExtendedI18N.memory.ErroTipoEntradaException;
import plp.imperativeExtendedI18N.memory.VariavelJaDeclaradaException;
import plp.imperativeExtendedI18N.memory.VariavelNaoDeclaradaException;

public class Read implements IO {

	private Id id;

	public Read(Id id) {
		this.id = id;
	}

	/**
	 * L� da entrada padr�o.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * 
	 * @return o ambiente depois de modificado pela execu��o do comando read.
	 * @throws ErroTipoEntradaException 
	 * 
	 */
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			EntradaVaziaException, ErroTipoEntradaException {
				
		Valor valorID = ambiente.get(id);
		Valor valorRead =ambiente.read();
		if (valorID.getTipo(null).eIgual(valorRead.getTipo(null))) {
			ambiente.changeValor(id, valorRead );
		}else{
			throw new ErroTipoEntradaException("Tipo do valor de entrada lido incomp�tivel" +
					" com tipo da vari�vel (" + id.getIdName() + ")");
		}
		
		
		
		
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos da entrada
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se a express�o da entrada est� bem tipada;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws VariavelNaoDeclaradaException, EntradaVaziaException,
			VariavelJaDeclaradaException {
		//return id.getTipo(ambiente).equals(ambiente.getTipoEntrada());
		return true;
	}

}
