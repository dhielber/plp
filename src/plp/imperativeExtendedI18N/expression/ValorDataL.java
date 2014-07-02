package plp.imperativeExtendedI18N.expression;

import plp.imperativeExtendedI18N.memory.AmbienteCompilacao;
import plp.imperativeExtendedI18N.util.Tipo;
import plp.imperativeExtendedI18N.util.TipoPrimitivo;

public class ValorDataL extends ValorConcreto<ValorInteiro[]> {

	private ValorInteiro dia;
	private ValorInteiro mes;
	private ValorInteiro ano;

	private ValorDataL(ValorInteiro[] data) {
		super(data);
	}

	public ValorDataL(ValorInteiro dia, ValorInteiro mes, ValorInteiro ano) {		
		super(new ValorInteiro[] { dia, mes, ano });
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public int getDia() {
		return dia.valor();
	}

	public void setDia(int dia) {
		this.dia = new ValorInteiro(dia);
	}

	public int getMes() {
		return mes.valor();
	}

	public void setMes(int mes) {
		this.mes = new ValorInteiro(mes);
	}

	public int getAno() {
		return ano.valor();
	}

	public void setAno(int ano) {
		this.ano = new ValorInteiro(ano);
	}

	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.DATAL;
	}

	@Override
	public String toString() {
		return String.format("\"%s.%s.%s\"", this.dia.valor(),this.mes.valor(),this.ano.valor());
	}

	public ValorDataL clone() {
		return new ValorDataL(this.valor());
	}
}
