package plp.imperativeExtendedI18N.declaration;

import java.util.ArrayList;
import java.util.List;

import plp.imperativeExtendedI18N.expression.Expressao;
import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.memory.AmbienteCompilacao;
import plp.imperativeExtendedI18N.memory.VariavelJaDeclaradaException;
import plp.imperativeExtendedI18N.memory.VariavelNaoDeclaradaException;
import plp.imperativeExtendedI18N.util.DefFuncao;
import plp.imperativeExtendedI18N.util.Tipo;
import plp.imperativeExtendedI18N.util.TipoFuncao;
import plp.imperativeExtendedI18N.util.TipoPolimorfico;
import plp.imperativeExtendedI18N.util.ToStringProvider;

public class DecFuncao implements DeclaracaoFuncional {

	private Id id;

	private DefFuncao funcao;

	public DecFuncao(Id idFun, List<Id> argsId, Expressao exp) {
		this.id = idFun;
		this.funcao = new DefFuncao(argsId, exp);
	}

	public Id getId() {
		return id;
	}

	public List<Id> getListaId() {
		return funcao.getListaId();
	}

	public Expressao getExpressao() {
		return funcao.getExp();
	}

	/**
	 * Retorna a aridade da funcao declarada. Variaveis tem aridade 0.
	 * 
	 * @return a aridade da funcao declarada.
	 */
	public int getAridade() {
		return funcao.getAridade();
	}

	public DefFuncao getFuncao() {
		return funcao;
	}

	/**
	 * Retorna uma representacao String desta expressao. Util para depuracao.
	 * 
	 * @return uma representacao String desta expressao.
	 */
	@Override
	public String toString() {
		return String.format("fun %s (%s) = %s", id, ToStringProvider.listToString(funcao
				.getListaId(), ","), funcao.getExp());
	}

	/**
	 * Realiza a verificacao de tipos desta declaração.
	 * 
	 * @param amb
	 *            o ambiente de compilação.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *         <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ambiente.incrementa();

		List<Tipo> params = new ArrayList<Tipo>(getAridade());
		for (int i = 0; i < getAridade(); i++) {
			params.add(new TipoPolimorfico());
		}
		Tipo tipo = new TipoFuncao(params, new TipoPolimorfico());
		// Mapeia a própria função no ambiente para permitir recursão.
		ambiente.map(id, tipo);

		boolean result = funcao.checaTipo(ambiente);
		ambiente.restaura();
		return result;
	}

	/**
	 * Retorna os tipos possiveis da função declarada.
	 * 
	 * @param amb
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return os tipos possiveis desta declaração.
	 * @exception VariavelNaoDeclaradaException
	 *                se houver uma vari&aacute;vel n&atilde;o declarada no
	 *                ambiente.
	 * @exception VariavelJaDeclaradaException
	 *                se houver uma mesma vari&aacute;vel declarada duas vezes
	 *                no mesmo bloco do ambiente.
	 * @precondition this.checaTipo();
	 */
	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		amb.incrementa();

		List<Tipo> params = new ArrayList<Tipo>(getAridade());
		for (int i = 0; i < getAridade(); i++) {
			params.add(new TipoPolimorfico());
		}
		Tipo tipo = new TipoFuncao(params, new TipoPolimorfico());
		amb.map(id, tipo);

		Tipo result = funcao.getTipo(amb);
		amb.restaura();
		return result;
	}

	public DecFuncao clone() {
		DefFuncao aux = this.funcao.clone();
		return new DecFuncao(this.id.clone(), aux.getListaId(), aux.getExp());
	}
}
