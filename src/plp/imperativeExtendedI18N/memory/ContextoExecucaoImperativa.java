package plp.imperativeExtendedI18N.memory;

import java.util.HashMap;
import java.util.Stack;

import plp.imperativeExtendedI18N.expression.Id;
import plp.imperativeExtendedI18N.expression.Valor;
import plp.imperativeExtendedI18N.expression.ValorDataL;
import plp.imperativeExtendedI18N.expression.ValorFloat;
import plp.imperativeExtendedI18N.expression.ValorString;
import plp.imperativeExtendedI18N.expression.ValorStringL;

public class ContextoExecucaoImperativa extends ContextoExecucao implements
		AmbienteExecucaoImperativa {

	private String idioma;

	/**
	 * A pilha de blocos de contexto.
	 */
	private ListaValor entrada;

	/**
	 * A pilha de blocos de contexto.
	 */
	private ListaValor saida;

	/**
	 * Construtor da classe.
	 */
	public ContextoExecucaoImperativa(String idioma, ListaValor entrada) {
		super();
		this.idioma = idioma;
		this.entrada = entrada;
		this.saida = new ListaValor();
	}

	public Valor read() throws EntradaVaziaException {
		if (entrada == null || entrada.getHead() == null) {
			throw new EntradaVaziaException();
		}
		Valor aux = entrada.getHead();
		entrada = (ListaValor) entrada.getTail();
		return aux;
	}

	public ListaValor getSaida() {
		return saida;
	}

	public void write(Valor v) {

		if (v instanceof ValorStringL) {
			ValorStringL recurso = (ValorStringL) v;
			String conteudo = this.getRecursosString().buscarRecursoPorIdioma(recurso.valor(), this.idioma);
			saida.write(new ValorString(conteudo));
		} else if (v instanceof ValorFloat) {
			ValorFloat valorFloat = (ValorFloat) v;
			String conteudo = this.getRecursosFloat().gerarValorFloatPorIdioma(valorFloat.valor(), this.idioma);
			saida.write(new ValorString(conteudo));
		} else if (v instanceof ValorDataL){
			ValorDataL data = (ValorDataL) v;
			String conteudo = this.getRecursosData().gerarData(data, this.idioma, EnumPadraoData.SHORT);
			saida.write(new ValorString(conteudo));
		}else{
			saida.write(v);
		}
	}
	
	public void writeD(ValorDataL v, ValorString padraoData) {

		ValorDataL data = (ValorDataL) v;
		
		EnumPadraoData enumPadraoData = EnumPadraoData.SHORT; 
		
		if(padraoData.valor().equalsIgnoreCase(EnumPadraoData.SHORT.toString())){
			enumPadraoData = EnumPadraoData.SHORT;
		}else if(padraoData.valor().equalsIgnoreCase(EnumPadraoData.MEDIUM.toString())){
			enumPadraoData = EnumPadraoData.MEDIUM;
		}else if(padraoData.valor().equalsIgnoreCase(EnumPadraoData.LONG.toString())){
			enumPadraoData = EnumPadraoData.LONG;
		}
		
		String conteudo = this.getRecursosData().gerarData(data, this.idioma, enumPadraoData);
		saida.write(new ValorString(conteudo));
	}

	/**
	 * Altera o valor mapeado do id dado.
	 * 
	 * @exception VariavelNaoDeclaradaException
	 *                se não existir nenhum valor mapeado ao id dado nesta
	 *                tabela.
	 */
	public void changeValor(Id idArg, Valor valorId)
			throws VariavelNaoDeclaradaException {

		Object result = null;
		Stack<HashMap<Id, Valor>> auxStack = new Stack<HashMap<Id, Valor>>();
		Stack<HashMap<Id, Valor>> stack = this.getPilha();

		while (result == null && !stack.empty()) {
			HashMap<Id, Valor> aux = stack.pop();
			auxStack.push(aux);
			result = aux.get(idArg);
			if (result != null) {
				aux.put(idArg, valorId);
			}
		}
		while (!auxStack.empty()) {
			stack.push(auxStack.pop());
		}
		if (result == null) {
			throw new VariavelNaoDeclaradaException(idArg);
		}
	}

	public void avaliarSeRecursoExiste(ValorStringL recurso)
			throws RecursoNaoDeclaradoException {

		if (recurso == null || (recurso != null && this.getRecursosString().buscarRecursoPorIdioma(recurso.toString(), this.idioma) == null)) {
			throw new RecursoNaoDeclaradoException(recurso);
		}
	}

	public void avaliarData(ValorDataL data) throws DataInvalidaException {

		boolean dataValida = true;

		int dia = data.getDia();
		int mes = data.getMes();
		int ano = data.getAno();
				
		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			if (dia < 1 || dia > 31) {
				dataValida = false;
			}
		} else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			if (dia < 1 || dia > 30) {
				dataValida = false;
			}
		} else if (mes == 2) {

			boolean anoBissexto = (ano % 4 == 0 || ano % 100 == 0 || ano % 400 == 0);
			if (anoBissexto) {
				if (dia < 1 || dia > 29) {
					dataValida = false;
				}
			} else {
				if (dia < 1 || dia > 28) {
					dataValida = false;
				}
			}
		}else{
			dataValida = false;
		}
		
		if(ano < 1900 && ano > 2100){
			dataValida = false;
		}

		if (!dataValida) {
			throw new DataInvalidaException(data);
		}
	}
}
