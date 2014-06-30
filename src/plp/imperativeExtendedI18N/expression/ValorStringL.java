package plp.imperativeExtendedI18N.expression;

import plp.imperativeExtendedI18N.memory.AmbienteCompilacao;
import plp.imperativeExtendedI18N.util.Tipo;
import plp.imperativeExtendedI18N.util.TipoPrimitivo;

public class ValorStringL extends ValorConcreto<String> {

	public ValorStringL(String valor) {
		super(valor);
	}

	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.STRINGL;
	}

	@Override
	public String toString() {
		return String.format("\"%s\"", super.toString());
	}
	
	public ValorString clone() {
		return new ValorString(this.valor());
	}
}
