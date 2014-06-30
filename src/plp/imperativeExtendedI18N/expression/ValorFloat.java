package plp.imperativeExtendedI18N.expression;

import plp.imperativeExtendedI18N.memory.AmbienteCompilacao;
import plp.imperativeExtendedI18N.util.Tipo;
import plp.imperativeExtendedI18N.util.TipoPrimitivo;

public class ValorFloat extends ValorConcreto<Double> {

	public ValorFloat(Double valor) {
		super(valor);
	}

	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.FLOAT;
	}

	public ValorFloat clone(){
		return new ValorFloat(this.valor());
	}
}
