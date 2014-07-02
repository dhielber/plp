package plp.imperativeExtendedI18N.memory;

import java.util.HashMap;

import plp.imperativeExtendedI18N.expression.ValorDataL;

public class RecursosData extends Recursos {

	protected HashMap<String, HashMap<EnumPadraoData, String>> padroes;
	protected HashMap<String, HashMap<Integer, String>> mesesAbreviados;
	protected HashMap<String, HashMap<Integer, String>> meses;

	public RecursosData() {
		this.padroes = new HashMap<String, HashMap<EnumPadraoData, String>>();
		this.mesesAbreviados = new HashMap<String, HashMap<Integer, String>>();
		this.meses = new HashMap<String, HashMap<Integer, String>>();
		cadastrarRecursos();
	}

	public String gerarData(ValorDataL valorData, String idioma, EnumPadraoData enumPadraoData) {
		
		if (padroes.containsKey(idioma) && mesesAbreviados.containsKey(idioma) && meses.containsKey(idioma)) {
			String padraoData = padroes.get(idioma).get(enumPadraoData);
			String valorMesAbreviado = mesesAbreviados.get(idioma).get(
					valorData.getMes());
			String valorMes = meses.get(idioma).get(valorData.getMes());

			String conteudoDia = "";
			String padraoDia = "";

			String conteudoMes = "";
			String padraoMes = "";

			String conteudoAno = "";
			String padraoAno = "";

			if (padraoData.contains("$dd")) {
				if (valorData.getDia() < 10) {
					conteudoDia = String.format("0%s", valorData.getDia());
				} else {
					conteudoDia = String.valueOf(valorData.getDia());
				}
				padraoDia = "$dd";
			} else if (padraoData.contains("$d")) {
				conteudoDia = String.valueOf(valorData.getDia());
				padraoDia = "$d";
			}

			if (padraoData.contains("$MMMM")) {
				conteudoMes = valorMes;
				padraoMes = "$MMMM";
			} else if (padraoData.contains("$MMM")) {
				conteudoMes = valorMesAbreviado;
				padraoMes = "$MMM";
			} else if (padraoData.contains("$MM")) {
				if (valorData.getMes() < 10) {
					conteudoMes = String.format("0%s", valorData.getMes());
				} else {
					conteudoMes = String.valueOf(valorData.getMes());
				}
				padraoMes = "$MM";
			} else if (padraoData.contains("$M")) {
				conteudoMes = String.valueOf(valorData.getMes());
				padraoMes = "$M";
			}

			String valorAno = String.valueOf(valorData.getAno());

			if (padraoData.contains("$yy")) {
				conteudoAno = valorAno.substring(2, valorAno.length());
				padraoAno = "$yy";
			} else if (padraoData.contains("$y")) {
				conteudoAno = valorAno;
				padraoAno = "$y";
			}
			
			String conteudoData = padraoData.replace(padraoDia, conteudoDia);
			conteudoData = conteudoData.replace(padraoMes, conteudoMes);
			conteudoData = conteudoData.replace(padraoAno, conteudoAno);

			return conteudoData;
		}
		return null;
	}

	private void cadastrarRecursos() {

		cadastrarPadroes();
		cadastrarMesesAbreviados();
		cadastrarMeses();
	}

	private void cadastrarPadroes() {

		padroes.put(IDIOMA_PORTUGUES, new HashMap<EnumPadraoData, String>());
		padroes.put(IDIOMA_INGLES, new HashMap<EnumPadraoData, String>());

		HashMap<EnumPadraoData, String> padroesPT = padroes.get(IDIOMA_PORTUGUES);
		HashMap<EnumPadraoData, String> padroesEN = padroes.get(IDIOMA_INGLES);

		padroesPT.put(EnumPadraoData.SHORT, "$dd/$MM/$yy");
		padroesPT.put(EnumPadraoData.MEDIUM, "$dd/$MM/$y");
		padroesPT.put(EnumPadraoData.LONG, "$d de $MMMM de $y");

		padroesEN.put(EnumPadraoData.SHORT, "$M/$d/$yy");
		padroesEN.put(EnumPadraoData.MEDIUM, "$MMM $d, $y");
		padroesEN.put(EnumPadraoData.LONG, "$MMMM $d, $y");
	}

	private void cadastrarMesesAbreviados() {

		mesesAbreviados.put(IDIOMA_PORTUGUES, new HashMap<Integer, String>());
		mesesAbreviados.put(IDIOMA_INGLES, new HashMap<Integer, String>());

		HashMap<Integer, String> mesesAbreviadosPT = mesesAbreviados.get(IDIOMA_PORTUGUES);
		HashMap<Integer, String> mesesAbreviadosEN = mesesAbreviados.get(IDIOMA_INGLES);

		mesesAbreviadosPT.put(1, "Jan");
		mesesAbreviadosPT.put(2, "Fev");
		mesesAbreviadosPT.put(3, "Mar");
		mesesAbreviadosPT.put(4, "Abr");
		mesesAbreviadosPT.put(5, "Mai");
		mesesAbreviadosPT.put(6, "Jun");
		mesesAbreviadosPT.put(7, "Jul");
		mesesAbreviadosPT.put(8, "Ago");
		mesesAbreviadosPT.put(9, "Set");
		mesesAbreviadosPT.put(10, "Out");
		mesesAbreviadosPT.put(11, "Nov");
		mesesAbreviadosPT.put(12, "Dez");

		mesesAbreviadosEN.put(1, "Jan");
		mesesAbreviadosEN.put(2, "Feb");
		mesesAbreviadosEN.put(3, "Mar");
		mesesAbreviadosEN.put(4, "Apr");
		mesesAbreviadosEN.put(5, "May");
		mesesAbreviadosEN.put(6, "Jun");
		mesesAbreviadosEN.put(7, "Jul");
		mesesAbreviadosEN.put(8, "Aug");
		mesesAbreviadosEN.put(9, "Sep");
		mesesAbreviadosEN.put(10, "Oct");
		mesesAbreviadosEN.put(11, "Nov");
		mesesAbreviadosEN.put(12, "Dec");
	}

	private void cadastrarMeses() {

		meses.put(IDIOMA_PORTUGUES, new HashMap<Integer, String>());
		meses.put(IDIOMA_INGLES, new HashMap<Integer, String>());

		HashMap<Integer, String> mesesPT = meses.get(IDIOMA_PORTUGUES);
		HashMap<Integer, String> mesesEN = meses.get(IDIOMA_INGLES);

		mesesPT.put(1, "Janeiro");
		mesesPT.put(2, "Fevereiro");
		mesesPT.put(3, "Março");
		mesesPT.put(4, "Abril");
		mesesPT.put(5, "Maio");
		mesesPT.put(6, "Junho");
		mesesPT.put(7, "Julho");
		mesesPT.put(8, "Agosto");
		mesesPT.put(9, "Setembro");
		mesesPT.put(10, "Outubro");
		mesesPT.put(11, "Novembro");
		mesesPT.put(12, "Dezembro");

		mesesEN.put(1, "January");
		mesesEN.put(2, "February");
		mesesEN.put(3, "March");
		mesesEN.put(4, "April");
		mesesEN.put(5, "May");
		mesesEN.put(6, "June");
		mesesEN.put(7, "July");
		mesesEN.put(8, "August");
		mesesEN.put(9, "September");
		mesesEN.put(10, "October");
		mesesEN.put(11, "November");
		mesesEN.put(12, "December");
	}
}
