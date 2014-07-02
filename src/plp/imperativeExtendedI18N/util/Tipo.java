package plp.imperativeExtendedI18N.util;

/**
 * Classe que representa os possiveis tipos de uma expressao.
 * 
 * @author Allan Araujo
 * @author Joabe Jesus
 * @author Marcus Machado
 * @author Rafael Oliveira
 */
public interface Tipo {

	public abstract String getNome();

	public abstract boolean eInteiro();
	
	public abstract boolean eFloat();

	public abstract boolean eBooleano();

	public abstract boolean eString();
	
	public abstract boolean eStringL();
	
	public abstract boolean eDataL();

	public abstract boolean eIgual(Tipo tipo);

	public abstract boolean eValido();

	public abstract Tipo intersecao(Tipo outroTipo);

}
