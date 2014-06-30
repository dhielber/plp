package plp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JTextArea;

import plp.imperative2.memory.ContextoExecucaoImperativa2;
import plp.imperative2.parser.Imp2Parser;
import plp.imperativeExtendedI18N.expression.ValorBooleano;
import plp.imperativeExtendedI18N.expression.ValorInteiro;
import plp.imperativeExtendedI18N.expression.ValorString;
import plp.imperativeExtendedI18N.memory.ContextoCompilacaoImperativa;
import plp.imperativeExtendedI18N.memory.ContextoExecucaoImperativa;
import plp.imperativeExtendedI18N.memory.ListaValor;
import plp.imperativeExtendedI18N.parser.ImperativeExtendedI18NParser;

public class MultiInterpretador {

	private static final int EXP1 = 0;

	private static final int EXP2 = 1;

	private static final int FUNC1 = 2;

	private static final int FUNC2 = 3;

	private static final int FUNC3 = 4;

	private static final int IMP1 = 5;

	private static final int IMP2 = 6;

	private static final int OO1 = 7;

	private static final int OO2 = 8;

	private JTextArea messageBoard;

	private ImperativeExtendedI18NParser imp1Parser = null;
	private Imp2Parser imp2Parser = null;

	public MultiInterpretador(JTextArea textAreaMensagens) {
		super();
		messageBoard = textAreaMensagens;
	}

	public void interpretarCodigo(String sourceCode, String listaEntrada,
			int selectedIndex) {
		try {
			ByteArrayInputStream fis = new ByteArrayInputStream(sourceCode
					.getBytes());

			switch (selectedIndex) {
			case IMP1:
				interpretarImp1(fis, listaEntrada);
				break;
			case IMP2:
				interpretarImp2(fis, listaEntrada);
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

	private void interpretarImp1(InputStream fis, String entradaStr)
			throws Exception {
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
					+ prog.executar(new ContextoExecucaoImperativa(entrada))
							.toString());
		} else {
			messageBoard.append("erro de tipos!");
		}
	}

	private void interpretarImp2(InputStream fis, String entradaStr)
			throws Exception {
		plp.imperative2.Programa prog;
		if (imp2Parser == null) {
			imp2Parser = new Imp2Parser(fis);
		} else {
			Imp2Parser.ReInit(fis);
		}

		prog = Imp2Parser.Input();

		messageBoard.setText("sintaxe verificada com sucesso!\n");
		ListaValor entrada = obterListaEntrada(entradaStr);
		if (prog.checaTipo(new ContextoCompilacaoImperativa(entrada))) {
			messageBoard.append("resultado = "
					+ prog.executar(new ContextoExecucaoImperativa2(entrada))
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

}