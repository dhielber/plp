package plp.imperativeExtendedI18N.expression;

import java.util.Calendar;
import java.util.GregorianCalendar;

import plp.imperativeExtendedI18N.memory.AmbienteCompilacao;
import plp.imperativeExtendedI18N.util.Tipo;
import plp.imperativeExtendedI18N.util.TipoPrimitivo;

public class ValorDataL extends ValorConcreto<Calendar> {

	ValorInteiro dia;
	ValorInteiro mes;
	ValorInteiro ano;
	Calendar data;	
	
	public ValorDataL(Calendar data) {
		super(data);
	}
	
	public ValorDataL(ValorInteiro dia, ValorInteiro mes, ValorInteiro ano) {
		super(new GregorianCalendar(ano.valor(), mes.valor(), dia.valor()));
	}

	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.DATAL;
	}

	@Override
	public String toString() {
		return String.format("\"%s\"", super.toString());
	}
	
	public ValorDataL clone() {
		return new ValorDataL(this.data);
	}
}
