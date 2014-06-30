package plp.imperative1.parser;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import plp.imperativeExtendedI18N.Programa;
import plp.imperativeExtendedI18N.expression.ValorBooleano;
import plp.imperativeExtendedI18N.expression.ValorInteiro;
import plp.imperativeExtendedI18N.memory.AmbienteCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.AmbienteExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.ContextoCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.ContextoExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.ListaValor;
import plp.imperativeExtendedI18N.parser.ImperativeExtendedI18NParser;

@RunWith(Parameterized.class)
public class ProgramasAceitosTest {

	static ImperativeExtendedI18NParser parser;
	String input;
	private String saidaExperada;
	private ListaValor entrada;

	public ProgramasAceitosTest(String input, ListaValor entrada,
			String resultado) {
		super();
		this.input = input;
		this.saidaExperada = resultado;
		this.entrada = entrada;
	}

	@Before
	public void setup() {
		ImperativeExtendedI18NParser.disable_tracing();
		ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
		if (parser == null)
			parser = new ImperativeExtendedI18NParser(bis);
		else
			ImperativeExtendedI18NParser.ReInit(bis);

	}

	@Test
	public void testInput() throws Exception {

		Programa programa = ImperativeExtendedI18NParser.Input();

		AmbienteCompilacaoImperativa ambComp = new ContextoCompilacaoImperativa(
				entrada);
		assertThat("Erro de Tipo no programa: \n" + input, programa
				.checaTipo(ambComp), is(true));

		AmbienteExecucaoImperativa ambExec = new ContextoExecucaoImperativa(
				entrada);
		assertThat("Resultado errado para a avaliação de:\n" + input, programa
				.executar(ambExec).toString(), is(saidaExperada));
	}

	@Parameters
	public static List<Object[]> data() {
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		String prog =

		"{\n" + " var a = 3, \n" + " var c = 0; \n" + " read(c);\n"
				+ " c := c +1; \n" + " write(a);\n" + " write(c);\n" + " {\n"
				+ "  var a = 2,\n" + "  var b = 5,\n" + "  var c = false,\n"
				+ "  var d = \"oi\";\n" + "  read(c);\n" + "  write(a);\n"
				+ "  write(b+a);\n" + "  write(c);\n" + "  write(d)\n" + "};\n"
				+ " write(a)\n" + "}";

		ValorInteiro UM = new ValorInteiro(1);
		ValorBooleano TRUE = new ValorBooleano(true);

		data.add(new Object[] { prog, new ListaValor(UM, new ListaValor(TRUE)),
				"3 2 2 7 true \"oi\" 3 " });

		return data;

	}
}
