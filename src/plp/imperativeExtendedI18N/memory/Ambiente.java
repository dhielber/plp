package plp.imperativeExtendedI18N.memory;

import plp.imperativeExtendedI18N.expression.Id;


public interface Ambiente<T> {

	public void incrementa();

	public void restaura();

	public void map(Id idArg, T tipoId) throws VariavelJaDeclaradaException;

	public T get(Id idArg) throws VariavelNaoDeclaradaException;

}
