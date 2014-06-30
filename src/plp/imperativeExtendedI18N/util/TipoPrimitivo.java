package plp.imperativeExtendedI18N.util;

/**
 * Enum que representa os possiveis tipos primitivo de uma expressao.
 * Objetos desta classe sao imutaveis, portanto as vezes as instancias sao
 * compartilhadas.
 * 
 * Modificado em 11/07/2005 por Leonardo Lucena para usar tipos enumerados
 */

public enum TipoPrimitivo implements Tipo {

	INTEIRO("INTEIRO"),
	FLOAT("FLOAT"),
	BOOLEANO("BOOLEANO"),
	STRING("STRING"),
	STRINGL("STRINGL"),
	DATAL("DATAL");

	protected String nome;

	private TipoPrimitivo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public boolean eInteiro() {
		return this.eIgual(INTEIRO);
	}
	
	public boolean eFloat() {
		return this.eIgual(FLOAT);
	}

	public boolean eBooleano() {
		return this.eIgual(BOOLEANO);
	}

	public boolean eString() {
		return this.eIgual(STRING);
	}
	
	public boolean eStringL() {
		return this.eIgual(STRINGL);
	}
	
	public boolean eDataL() {
		return this.eIgual(DATAL);
	}

	public boolean eIgual(Tipo tipo) {
		boolean ret = false;
		if (eValido()) {
			if (tipo.eValido()) {
				ret = this.nome.equals(tipo.getNome());
			} else {
				ret = tipo.eIgual(this);
			}
		}
		return ret;
	}

	public boolean eValido() {
		return this.nome != null && nome.length() > 0;
	}

	public TipoPrimitivo intersecao(Tipo outroTipo) {
		if (outroTipo.eIgual(this)) {
			return this;
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
