package plp.imperativeExtendedI18N.declaration;

import plp.imperativeExtendedI18N.expression.Expressao;
import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.expression.ValorDataL;
import plp.imperativeExtendedI18N.expression.ValorStringL;
import plp.imperativeExtendedI18N.memory.AmbienteCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.AmbienteExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.DataInvalidaException;
import plp.imperativeExtendedI18N.memory.IdentificadorJaDeclaradoException;
import plp.imperativeExtendedI18N.memory.IdentificadorNaoDeclaradoException;
import plp.imperativeExtendedI18N.memory.RecursoNaoDeclaradoException;
import plp.imperativeExtendedI18N.util.Tipo;

public class DeclaracaoVariavel extends Declaracao {

	private Tipo tipo;
	private Id id;
	private Expressao expressao;

	public DeclaracaoVariavel(Tipo tipo, Id id, Expressao expressao) {
		super();
		this.tipo = tipo;
		this.id = id;
		this.expressao = expressao;
	}

	/**
	 * Cria um mapeamento do identificador para o valor da express�o desta
	 * declara��o no AmbienteExecucao
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * 
	 * @return o ambiente modificado pela inicializa��o da vari�vel.
	 */
	@Override
	public AmbienteExecucaoImperativa elabora(AmbienteExecucaoImperativa ambiente) 
			throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, RecursoNaoDeclaradoException, DataInvalidaException {		

		if(getExpressao() instanceof ValorStringL){
			ValorStringL chaveRecurso = (ValorStringL) getExpressao().avaliar(ambiente);
			ambiente.avaliarSeRecursoExiste(chaveRecurso);
		}else if(getExpressao() instanceof ValorDataL){
			ValorDataL data = (ValorDataL) getExpressao().avaliar(ambiente);
			ambiente.avaliarData(data);			
		}
		
		ambiente.map(getId(), getExpressao().avaliar(ambiente));			
		return ambiente;
	}

	public Expressao getExpressao() {
		return this.expressao;
	}

	public Id getId() {
		return this.id;
	}

	/**
	 * Verifica se a declara��o est� bem tipada, ou seja, se a express�o de
	 * inicializa��o est� bem tipada, e cria o mapeamento da variavel para o seu
	 * tipo correspondente
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * 
	 * @return <code>true</code> se os tipos da declara��o s�o v�lidos;
	 *         <code>false</code> caso contrario.
	 * 
	 */
	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException {
		boolean result = getExpressao().getTipo(ambiente).equals(tipo);
		
		if (result) {
			ambiente.map(getId(), getExpressao().getTipo(ambiente));
		}
		
		return result;
	}
}
