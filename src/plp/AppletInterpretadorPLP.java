package plp;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import plp.imperativeExtendedI18N.memory.Recursos;

public class AppletInterpretadorPLP extends Applet {

	private static final long serialVersionUID = -7398656423050674702L;

	private static final Font COURIER_NEW = new Font("Courier New", Font.PLAIN, 12);

	private JPanel jContentPane = null;
	private JTextArea jTextAreaCodigo = null;
	private JScrollPane jScrollPaneMensagens = null;
	private JTextArea jTextAreaMensagens = null;
	private JLabel jLabelCodigo = null;
	private JLabel jLabelMasg = null;
	private JScrollPane jScrollPaneCodigo = null;
	private JComboBox jComboBoxLinguagens = null;
	private JComboBox jComboBoxIdiomas = null;
	private JLabel jLabelExecutar = null;

	private JButton jButtonExecutar = null;
	private JTextField jTextFieldListaEntrada = null;
	private JLabel jLabelListaEntrada = null;

	private MultiInterpretador interpreter;

	public AppletInterpretadorPLP() {
		super();
		initialize();
	}

	private void initialize() {
		getJContentPane();
		this.setBounds(new java.awt.Rectangle(300, 200, 389, 429));
		this.jTextFieldListaEntrada.setEnabled(true);
		interpreter = new MultiInterpretador(this.jTextAreaMensagens, (String)jComboBoxIdiomas.getSelectedItem());
	}

	private Panel getJContentPane() {
		if (jContentPane == null) {
			jLabelListaEntrada = new JLabel();
			jLabelListaEntrada.setBounds(new java.awt.Rectangle(20, 194, 127, 20));
			jLabelListaEntrada.setToolTipText("informe os valores da lista de entrada separados por espaços");
			jLabelListaEntrada.setText("Lista de Entrada");
			jLabelExecutar = new JLabel();
			jLabelExecutar.setBounds(new java.awt.Rectangle(19, 434, 157, 17));
			jLabelExecutar.setText("Pressione F1 para executar");
			jLabelMasg = new JLabel();
			jLabelMasg.setBounds(new java.awt.Rectangle(20, 245, 80, 16));
			jLabelMasg.setText("Mensagens");
			jLabelCodigo = new JLabel();
			jLabelCodigo.setBounds(new java.awt.Rectangle(20, 33, 70, 16));
			jLabelCodigo.setText("Código");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			this.setLayout(null);
			this.add(getJScrollPaneMensagens(), null);
			this.add(jLabelCodigo, null);
			this.add(jLabelMasg, null);
			this.add(getJScrollPaneCodigo(), null);
			this.add(getJTextFieldListaEntrada(), null);
			this.add(getJComboBoxLinguagens(), null);
			this.add(getJComboBoxIdiomas(), null);
			this.add(jLabelExecutar, null);
			this.add(getJButton(), null);
			this.add(jLabelListaEntrada, null);
		}
		
		return this;
	}

	private JTextArea getJTextAreaCodigo() {
		if (jTextAreaCodigo == null) {
			jTextAreaCodigo = new JTextArea();
			jTextAreaCodigo.setFont(COURIER_NEW);
			jTextAreaCodigo.setTabSize(2);
			UndoUtil.registerUndoManager(jTextAreaCodigo);
		}
		return jTextAreaCodigo;
	}

	private JScrollPane getJScrollPaneMensagens() {
		
		if (jScrollPaneMensagens == null) {
			jScrollPaneMensagens = new JScrollPane();
			jScrollPaneMensagens.setBounds(new java.awt.Rectangle(20, 267, 350, 160));
			jScrollPaneMensagens.setViewportView(getJTextAreaMensagens());
		}
		
		return jScrollPaneMensagens;
	}

	private JTextArea getJTextAreaMensagens() {

		if (jTextAreaMensagens == null) {
			jTextAreaMensagens = new JTextArea();
		}
		
		return jTextAreaMensagens;
	}

	private JScrollPane getJScrollPaneCodigo() {
		
		if (jScrollPaneCodigo == null) {
			jScrollPaneCodigo = new JScrollPane();
			jScrollPaneCodigo.setBounds(new java.awt.Rectangle(20, 52, 350, 134));
			jScrollPaneCodigo.setViewportView(getJTextAreaCodigo());
		}
		
		return jScrollPaneCodigo;
	}

	private JComboBox getJComboBoxLinguagens() {
		
		if (jComboBoxLinguagens == null) {
			jComboBoxLinguagens = new JComboBox();
			jComboBoxLinguagens.setBounds(new java.awt.Rectangle(20, 7, 200, 20));
			
			jComboBoxLinguagens.addItem("Imperativa 1 - Extensão i18n");
		}
		
		return jComboBoxLinguagens;
	}
	
	private JComboBox getJComboBoxIdiomas() {
		
		if (jComboBoxIdiomas == null) {
			jComboBoxIdiomas = new JComboBox();
			jComboBoxIdiomas.setBounds(new java.awt.Rectangle(225, 7, 60, 20));			
			
			jComboBoxIdiomas.addItem(Recursos.IDIOMA_INGLES);
			jComboBoxIdiomas.addItem(Recursos.IDIOMA_PORTUGUES);
			
			jComboBoxIdiomas.setSelectedIndex(0);
			
			jComboBoxIdiomas.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					String recurso = (String) jComboBoxIdiomas.getSelectedItem();					
					interpreter.setIdioma(recurso);
				}
			});
		}
		
		return jComboBoxIdiomas;
	}

	private JButton getJButton() {
		
		if (jButtonExecutar == null) {
			jButtonExecutar = new JButton();
			jButtonExecutar.setBounds(new java.awt.Rectangle(290, 7, 86, 20));
			jButtonExecutar.setText("executar");
			jButtonExecutar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String sourceCode = jTextAreaCodigo.getText();
					String listaEntrada = jTextFieldListaEntrada.getText();
					interpreter.interpretarCodigo(sourceCode, listaEntrada, jComboBoxLinguagens.getSelectedIndex());
				}
			});
		}
		
		return jButtonExecutar;
	}

	private JTextField getJTextFieldListaEntrada() {
		
		if (jTextFieldListaEntrada == null) {
			jTextFieldListaEntrada = new JTextField();
			jTextFieldListaEntrada.setBounds(new java.awt.Rectangle(20, 218, 350, 20));
			jTextFieldListaEntrada.setToolTipText("informe os valores da lista de entrada separados por espaços");
		}
		
		return jTextFieldListaEntrada;
	}

	public static void main(String[] args) {
		(new InterpretadorPLP()).setVisible(true);
	}

	@Override
	public void start() {
		super.start();
		this.setVisible(true);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		this.initialize();
	}
}

