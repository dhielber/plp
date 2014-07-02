package plp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JTextArea;

import plp.imperativeExtendedI18N.expression.ValorBooleano;
import plp.imperativeExtendedI18N.expression.ValorInteiro;
import plp.imperativeExtendedI18N.expression.ValorString;
import plp.imperativeExtendedI18N.memory.ContextoCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.ContextoExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.ListaValor;
import plp.imperativeExtendedI18N.memory.Recursos;
import plp.imperativeExtendedI18N.parser.ImperativeExtendedI18NParser;

public class MultiInterpretador {
	
	private static final int IMP1_I18N = 0;

	private JTextArea messageBoard;

	private ImperativeExtendedI18NParser imp1Parser = null;
	
	private String idioma;

	public MultiInterpretador(JTextArea textAreaMensagens, String idioma) {
		super();
		messageBoard = textAreaMensagens;
		this.idioma = idioma;
	}

	public void interpretarCodigo(String sourceCode, String listaEntrada,
			int selectedIndex) {
		try {
			ByteArrayInputStream fis = new ByteArrayInputStream(sourceCode.getBytes());

			switch (selectedIndex) {
				case IMP1_I18N:
					interpretarImp1ExtendedI18N(fis, listaEntrada);
					break;
				default:
					break;
			}
			
		} catch (Exception e1) {
			messageBoard.setText(e1.getMessage());
			e1.printStackTrace();
		} catch (Throwable t) {
			messageBoard.setText(t.getMessage());
			t.printStackTrace();
		}

	}

	private void interpretarImp1ExtendedI18N(InputStream fis, String entradaStr) throws Exception {
		plp.imperativeExtendedI18N.Programa prog;
		
		if (imp1Parser == null) {
			imp1Parser = new ImperativeExtendedI18NParser(fis);
		} else {
			ImperativeExtendedI18NParser.ReInit(fis);
		}
		
		prog = ImperativeExtendedI18NParser.Input();

		messageBoard.setText("sintaxe verificada com sucesso!\n");
		ListaValor entrada = obterListaEntrada(entradaStr);
		if (prog.checaTipo(new ContextoCompilacaoImperativa(entrada))) {
			messageBoard.append("resultado = "
					+ prog.executar(new ContextoExecucaoImperativa(this.idioma, entrada))
							.toString());
		} else {
			messageBoard.append("erro de tipos!");
		}
	}
	
	private ListaValor obterListaEntrada(String texto) {
		List<plp.imperativeExtendedI18N.expression.ValorConcreto> valores = new LinkedList<plp.imperativeExtendedI18N.expression.ValorConcreto>();
		ListaValor entrada = new ListaValor();
		StringTokenizer parser = new StringTokenizer(texto);

		while (parser.hasMoreTokens()) {
			String parametro = parser.nextToken();

			try {
				Integer inteiro = Integer.valueOf(parametro);
				valores.add(new ValorInteiro(inteiro.intValue()));
				continue;
			} catch (NumberFormatException e) {

			}

			if (parametro.equalsIgnoreCase("true")
					|| parametro.equalsIgnoreCase("false")) {
				Boolean booleano = Boolean.valueOf(parametro);
				valores.add(new ValorBooleano(booleano.booleanValue()));
			} else {
				valores.add(new ValorString(parametro));
			}
		}
		entrada = ImperativeExtendedI18NParser.criaListaValor(valores);
		return entrada;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;		
	}

}