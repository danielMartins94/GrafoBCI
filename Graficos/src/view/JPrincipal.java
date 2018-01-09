package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.jfree.chart.ChartPanel;

import Utilidades.ImagePanel;
import controller.GrafoController;
import graficos.GraficoLinha;
import model.Configuracoes;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JPrincipal extends JFrame {

	private JPanel contentPane;
	private GrafoController grafoController;
	private JTextField txtTempoAtualizacao;
	private JTextField txtRegistroPorGrafo;
	private JTextField txtLimiarPearson;
	private Graph graph;
	private JTextField txtRegistrosAtualizacao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JPrincipal() {
		grafoController = new GrafoController();
		Configuracoes config = new Configuracoes();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel pnOpcoes = new JPanel();
		pnOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnOpcoes.setBackground(UIManager.getColor("Button.light"));
		
		JPanel pnGrafo = new JPanel(); 
		pnGrafo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnGrafo.setBackground(Color.WHITE);
		pnGrafo.setLayout(new BorderLayout(0, 0));
		pnOpcoes.setLayout(null);
		
		JLabel lblOpes = new JLabel("OP\u00C7\u00D5ES");
		lblOpes.setBackground(Color.WHITE);
		lblOpes.setBounds(2, 11, 292, 14);
		lblOpes.setHorizontalAlignment(SwingConstants.CENTER);
		pnOpcoes.add(lblOpes);
		
		
		
		
		
		
		graph = new SingleGraph("Tutorial 1");

		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.disableAutoLayout();
		ViewPanel view = viewer.addDefaultView(false);   // false indicates "no JFrame".
	    pnGrafo.add(view, BorderLayout.CENTER);
	   
	    grafoController.getGrafoZerado(graph, config);


	    JButton btnIniciar = new JButton("INICIAR");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					config.setRegistrosPorAtualizacao(Integer.parseInt(txtRegistrosAtualizacao.getText()));
					config.setTempoAtualizacao(Integer.parseInt(txtTempoAtualizacao.getText()));
					config.setRegistrosPorGrafo(Integer.parseInt(txtRegistroPorGrafo.getText()));
					config.setLimiar(Double.parseDouble(txtLimiarPearson.getText()));
					config.setPararGrafo(false);
					grafoController.iniciarFonteDeDados(config);
					grafoController.atualizarGrafoTempoExecucao(graph, config);
			}
		});
		btnIniciar.setBounds(2, 286, 292, 23);
		pnOpcoes.add(btnIniciar);
		
		txtTempoAtualizacao = new JTextField();
		txtTempoAtualizacao.setText("500");
		txtTempoAtualizacao.setBounds(198, 36, 81, 20);
		pnOpcoes.add(txtTempoAtualizacao);
		txtTempoAtualizacao.setColumns(10);
		
		JLabel lblAtualizaACada = new JLabel("Atualiza a cada (ms)");
		lblAtualizaACada.setBounds(10, 42, 178, 14);
		pnOpcoes.add(lblAtualizaACada);
		
		txtRegistroPorGrafo = new JTextField();
		txtRegistroPorGrafo.setText("115");
		txtRegistroPorGrafo.setColumns(10);
		txtRegistroPorGrafo.setBounds(198, 75, 81, 20);
		pnOpcoes.add(txtRegistroPorGrafo);
		
		JLabel lblRegistrosPorGrafo = new JLabel("Registros/grafo");
		lblRegistrosPorGrafo.setBounds(10, 78, 178, 14);
		pnOpcoes.add(lblRegistrosPorGrafo);
		
		JButton btnParar = new JButton("PARAR");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				config.setPararGrafo(true);
			}
		});
		btnParar.setBounds(2, 320, 292, 23);
		pnOpcoes.add(btnParar);
		
		txtLimiarPearson = new JTextField();
		txtLimiarPearson.setText("0.8");
		txtLimiarPearson.setColumns(10);
		txtLimiarPearson.setBounds(198, 132, 81, 20);
		pnOpcoes.add(txtLimiarPearson);
		
		JLabel lblLimiarPearson = new JLabel("Limiar Pearson");
		lblLimiarPearson.setBounds(12, 135, 176, 14);
		pnOpcoes.add(lblLimiarPearson);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(2, 27, 292, 2);
		pnOpcoes.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pnOpcoes, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnGrafo, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(pnOpcoes, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(pnGrafo, GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
		);
		
		JLabel lblLayoutDoGrafo = new JLabel("Layout do grafo");
		lblLayoutDoGrafo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLayoutDoGrafo.setBounds(10, 160, 269, 14);
		pnOpcoes.add(lblLayoutDoGrafo);
		
		JComboBox cbTipoGrafo = new JComboBox();
		cbTipoGrafo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				config.setTipoGrafo(cbTipoGrafo.getSelectedIndex());
				grafoController.getGrafoZerado(graph, config);
			}
		});
		cbTipoGrafo.setModel(new DefaultComboBoxModel(new String[] {"Posi\u00E7\u00E3o dos eletrodos", "Circular"}));
		cbTipoGrafo.setToolTipText("");
		cbTipoGrafo.setBounds(10, 181, 269, 20);
		pnOpcoes.add(cbTipoGrafo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				config.setTipoDeMetrica(comboBox.getSelectedIndex());
				grafoController.getGrafoZerado(graph, config);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Grau", "Coeficiente de clusteriza\u00E7\u00E3o", "Centralidade de autovetor", "Centralidade de intermedia\u00E7\u00E3o"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(10, 233, 269, 20);
		pnOpcoes.add(comboBox);
		
		JLabel lblMtricaDoGrafo = new JLabel("M\u00E9trica do grafo");
		lblMtricaDoGrafo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMtricaDoGrafo.setBounds(10, 212, 269, 14);
		pnOpcoes.add(lblMtricaDoGrafo);
		
		txtRegistrosAtualizacao = new JTextField();
		txtRegistrosAtualizacao.setText("115");
		txtRegistrosAtualizacao.setColumns(10);
		txtRegistrosAtualizacao.setBounds(198, 101, 81, 20);
		pnOpcoes.add(txtRegistrosAtualizacao);
		
		JLabel lblRegistrosatualizao = new JLabel("Registros/atualiza\u00E7\u00E3o");
		lblRegistrosatualizao.setBounds(10, 104, 178, 14);
		pnOpcoes.add(lblRegistrosatualizao);
		
		JPanel pnGrafico01 = new JPanel();
		
		JPanel pnGrafico02 = new JPanel();
		pnGrafico02.setLayout(new BorderLayout(0, 0));
		
		JPanel pnGrafico03 = new JPanel();
		pnGrafico03.setLayout(new BorderLayout(0, 0));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(pnGrafico03, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnGrafico01, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
						.addComponent(pnGrafico02, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(pnGrafico01, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnGrafico02, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnGrafico03, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	  
		GraficoLinha gl = new GraficoLinha("Grau");
		pnGrafico01.setLayout(new BorderLayout(0, 0));
		pnGrafico01.add(new ChartPanel(gl.getGrafico()));
		
		GraficoLinha gl2 = new GraficoLinha("Coeficiente de Clusterização");
		pnGrafico02.setLayout(new BorderLayout(0, 0));
		pnGrafico02.add(new ChartPanel(gl2.getGrafico()));
		
		GraficoLinha gl3 = new GraficoLinha("Centralidade de Intermediação");
		pnGrafico03.setLayout(new BorderLayout(0, 0));
		pnGrafico03.add(new ChartPanel(gl3.getGrafico()));
		
		
		
		 //ImagePanel panelImage = new ImagePanel("images/background.png");
		 //pnGrafo.add(panelImage);
	
	}
}
