package plp.imperativeExtendedI18N.memory;

public enum EnumPadraoData {

	SHORT("short"), MEDIUM("medium"), LONG("long");

	protected String padrao;

	private EnumPadraoData(String padrao) {
		this.padrao = padrao;
	}
}
