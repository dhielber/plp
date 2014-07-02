package plp.imperativeExtendedI18N.memory;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Stack;

import plp.imperativeExtendedI18N.expression.Id;

/**
 * Classe abstrata que representa um contexto
 * 
 * @author eagt
 * 
 */
public class Contexto<T> {
		
	protected Stack<HashMap<Id, T>> pilha;
	
	protected RecursosString recursosString;
	
	protected RecursosFloat recursosFloat;	

	protected RecursosData recursosData;

	public Contexto() {
		pilha = new Stack<HashMap<Id, T>>();
		recursosString = new RecursosString();
		recursosFloat = new RecursosFloat();
		recursosData = new RecursosData();
	}
	
	public void incrementa() {
		pilha.push(new HashMap<Id, T>());
	}

	public void restaura() {
		pilha.pop();
	}

	public void map(Id idArg, T valorId) throws VariavelJaDeclaradaException {
		try {
			HashMap<Id, T> aux = pilha.peek();
			if (aux.put(idArg, valorId) != null)
				throw new IdentificadorJaDeclaradoException();
		} catch (IdentificadorJaDeclaradoException e) {
			throw new VariavelJaDeclaradaException(idArg);
		}
	}

	public T get(Id idArg) throws VariavelNaoDeclaradaException {
		try {
			T result = null;
			Stack<HashMap<Id, T>> auxStack = new Stack<HashMap<Id, T>>();
			while (result == null && !pilha.empty()) {
				HashMap<Id, T> aux = pilha.pop();
				auxStack.push(aux);
				result = aux.get(idArg);
			}
			while (!auxStack.empty()) {
				pilha.push(auxStack.pop());
			}
			if (result == null)
				throw new IdentificadorNaoDeclaradoException();

			return result;
		} catch (IdentificadorNaoDeclaradoException e) {
			throw new VariavelNaoDeclaradaException(idArg);
		}
	}

	protected Stack<HashMap<Id, T>> getPilha() {
		return pilha;
	}

	protected void setPilha(Stack<HashMap<Id, T>> pilha) {
		this.pilha = pilha;
	}

	public RecursosString getRecursosString() {
		return recursosString;
	}

	public RecursosFloat getRecursosFloat() {
		return recursosFloat;
	}
	
	public RecursosData getRecursosData() {
		return recursosData;
	}
}
