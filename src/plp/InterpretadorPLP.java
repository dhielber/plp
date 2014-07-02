package plp;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import plp.imperativeExtendedI18N.memory.Contexto;
import plp.imperativeExtendedI18N.memory.Recursos;

public class InterpretadorPLP extends JFrame {

	private static final long serialVersionUID = -7647474898074642286L;
	private static final Font COURIER_NEW = new Font("Courier New", Font.PLAIN, 12);

	private JPanel jContentPane = null;
	JTextArea jTextAreaCodigo = null;
	private JScrollPane jScrollPaneMensagens = null;
	private JTextArea jTextAreaMensagens = null;
	private JLabel jLabelCodigo = null;
	private JLabel jLabelMasg = null;
	private JScrollPane jScrollPaneCodigo = null;
	JComboBox jComboBoxLinguagens = null;
	private JComboBox jComboBoxIdiomas = null;
	private JLabel jLabelExecutar = null;

	MultiInterpretador interpreter;

	private JButton jButtonExecutar = null;
	JTextField jTextFieldListaEntrada = null;
	private JLabel jLabelListaEntrada = null;
	private InterpreterKeyListener listener;

	public InterpretadorPLP() {
		super();
		initialize();
	}

	private void initialize() {		
		this.setContentPane(getJContentPane());
		this.setTitle("Interpretador PLP");
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.listener = new InterpreterKeyListener(this);
		this.addKeyListener(this.listener);
		this.jTextAreaCodigo.addKeyListener(this.listener);
		this.jTextAreaMensagens.addKeyListener(this.listener);
		this.jTextFieldListaEntrada.addKeyListener(this.listener);
		this.jTextFieldListaEntrada.setEnabled(true);

		interpreter = new MultiInterpretador(this.jTextAreaMensagens, (String)jComboBoxIdiomas.getSelectedItem());
		Dimension d;
		int w, h;

		w = 390;
		h = 480;

		d = Toolkit.getDefaultToolkit().getScreenSize();
		d.height /= 2;
		d.width /= 2;
		d.height -= h / 2 + 15;
		d.width -= w / 2;

		this.setBounds(d.width, d.height, w, h);
	}

	private JPanel getJContentPane() {
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
			jContentPane.add(getJScrollPaneMensagens(), null);
			jContentPane.add(jLabelCodigo, null);
			jContentPane.add(jLabelMasg, null);
			jContentPane.add(getJScrollPaneCodigo(), null);
			jContentPane.add(getJTextFieldListaEntrada(), null);
			jContentPane.add(getJComboBoxLinguagens(), null);
			jContentPane.add(getJComboBoxIdiomas(), null);
			jContentPane.add(jLabelExecutar, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(jLabelListaEntrada, null);
		}
		
		return jContentPane;
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
			jScrollPaneMensagens.setBounds(new java.awt.Rectangle(20, 267, 350,
					160));
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
			jButtonExecutar.setBounds(new java.awt.Rectangle(290, 8, 86, 19));
			jButtonExecutar.setText("executar");
			jButtonExecutar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String sourceCode = jTextAreaCodigo.getText();
					String listaEntrada = jTextFieldListaEntrada.getText();
					interpreter.interpretarCodigo(sourceCode,listaEntrada, jComboBoxLinguagens.getSelectedIndex());
				}
			});
		}
		return jButtonExecutar;
	}

	private JTextField getJTextFieldListaEntrada() {
		if (jTextFieldListaEntrada == null) {
			jTextFieldListaEntrada = new JTextField();
			jTextFieldListaEntrada.setBounds(new java.awt.Rectangle(20, 218,
					350, 20));
			jTextFieldListaEntrada.setToolTipText("informe os valores da lista de entrada separados por espaços");
		}
		return jTextFieldListaEntrada;
	}

	public static void main(String[] args) {
		(new InterpretadorPLP()).setVisible(true);
	}

}

class InterpreterKeyListener implements KeyListener {

	InterpretadorPLP frame;

	public InterpreterKeyListener(InterpretadorPLP frm) {
		super();
		this.frame = frm;
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			String sourceCode = this.frame.jTextAreaCodigo.getText();
			String listaEntrada = this.frame.jTextFieldListaEntrada.getText();
			this.frame.interpreter.interpretarCodigo(sourceCode, listaEntrada, this.frame.jComboBoxLinguagens.getSelectedIndex());
		}
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}
