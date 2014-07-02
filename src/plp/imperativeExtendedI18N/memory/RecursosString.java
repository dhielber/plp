package plp.imperativeExtendedI18N.memory;

import java.util.HashMap;

public class RecursosString extends Recursos {

	protected HashMap<String, HashMap<String, String>> recursos;

	public RecursosString() {
		this.recursos = new HashMap<String, HashMap<String, String>>();
		cadastrarRecursos();
	}
	
	public String buscarRecursoPorIdioma(String recurso, String idioma){
		
		HashMap<String, String> recursosIdioma = obterRecursosPorIdioma(idioma);
		
		if(recursosIdioma != null && recursosIdioma.containsKey(recurso)){		
			return recursosIdioma.get(recurso);
		}
		
		return null; 
	}

	private void cadastrarRecursos() {
		
		recursos.put(IDIOMA_PORTUGUES, new HashMap<String, String>());
		recursos.put(IDIOMA_INGLES, new HashMap<String, String>());
		
		HashMap<String, String> recursosPT = obterRecursosPorIdioma(IDIOMA_PORTUGUES);
		HashMap<String, String> recursosEN = obterRecursosPorIdioma(IDIOMA_INGLES);
		
		recursosPT.put("_key1", "chave1");
		recursosPT.put("_key2", "chave2");
		recursosPT.put("_key3", "chave3");
		recursosPT.put("_key4", "chave4");
		recursosPT.put("_key5", "chave5");
		
		recursosEN.put("_key1", "key1");
		recursosEN.put("_key2", "key2");
		recursosEN.put("_key3", "key3");
		recursosEN.put("_key4", "key4");
		recursosEN.put("_key5", "key5");
		
	}
	
	private HashMap<String, String> obterRecursosPorIdioma(String idioma){
	
		if(recursos.containsKey(idioma)){
			return recursos.get(idioma);
		}
		
		return null;
	}

}
