package plp.imperativeExtendedI18N.memory;

import java.util.HashMap;

public class RecursosFloat extends Recursos {

	public static final String SEPARADOR_DECIMAL = "separador_decimal";

	protected HashMap<String, HashMap<String, String>> recursos;

	public RecursosFloat() {
		this.recursos = new HashMap<String, HashMap<String, String>>();
		cadastrarRecursos();
	}

	public String gerarValorFloatPorIdioma(Double conteudo, String idioma) {

		HashMap<String, String> recursosFloat = obterRecursosPorIdioma(idioma);

		if (recursosFloat != null
				&& recursosFloat.containsKey(SEPARADOR_DECIMAL)) {
			String separador = recursosFloat.get(SEPARADOR_DECIMAL);

			String conteudoS = String.valueOf(conteudo);

			conteudoS = conteudoS.replace(".", separador);

			return conteudoS;
		}

		return null;
	}

	private void cadastrarRecursos() {

		recursos.put(IDIOMA_PORTUGUES, new HashMap<String, String>());
		recursos.put(IDIOMA_INGLES, new HashMap<String, String>());

		HashMap<String, String> recursosPT = obterRecursosPorIdioma(IDIOMA_PORTUGUES);
		HashMap<String, String> recursosEN = obterRecursosPorIdioma(IDIOMA_INGLES);

		recursosPT.put(SEPARADOR_DECIMAL, ",");
		recursosEN.put(SEPARADOR_DECIMAL, ".");
	}

	private HashMap<String, String> obterRecursosPorIdioma(String idioma) {

		if (recursos.containsKey(idioma)) {
			return recursos.get(idioma);
		}

		return null;
	}
}
