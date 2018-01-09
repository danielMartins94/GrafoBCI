package controller;

import javax.swing.JOptionPane;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import algoritmos.AlgoritmoController;
import algoritmos.Colorir;
import dao.GrafoDao;
import model.Configuracoes;
import model.Eletrodo;
import model.MatrizAdjacencia;

public class GrafoController {
	private GrafoDao dao;
	
	public GrafoController(){
	}
	public void iniciarFonteDeDados(Configuracoes config) {
		 dao = new GrafoDao(config);

	}
	public void getGrafoZerado(Graph graph, Configuracoes config) { // retorna um grafo zerado
		graph.clear();
	    graph.addAttribute("ui.stylesheet", "graph { fill-mode: image-scaled; fill-image: url('data/images/escalpo2.png'); } node { text-style: bold; text-alignment: above; fill-color: #008080; size: 30px; } node.zero { fill-color: #ecf0f1; size: 30px;} node.um{fill-color: #2c0; size: 40px;} node.dois{fill-color: #4a0; size: 50px;} node.tres{fill-color: #680;size: 60px;} node.quatro{fill-color: #860;size:70px;} node.cinco{fill-color: #a40;size: 80px;} node.seis{fill-color: #c20;size: 90px;} node.sete{fill-color: #f00;size: 100px;} edge { shape: angle; arrow-shape: none; size: 3px; fill-color: #444; }");
		
	    
	    
	    graph.addNode("A");
	    graph.addNode("B");
	    graph.addNode("C");
		graph.addNode("D");
		graph.addNode("E");
		graph.addNode("F");
		graph.addNode("G");
		graph.addNode("H");
		
		if(config.getTipoGrafo()==1) {
			Node node = graph.getNode("A");
			node.addAttribute("ui.label", "1");
			node.setAttribute("xy", 0, 1);	
			node = graph.getNode("B");
			node.addAttribute("ui.label", "2");
			node.setAttribute("xy", -0.75, 0.75);
			node = graph.getNode("C");
			node.addAttribute("ui.label", "3");
			node.setAttribute("xy", 0.75, 0.75);
			node = graph.getNode("D");
			node.addAttribute("ui.label", "4");
			node.setAttribute("xy", -1, 0);
			node = graph.getNode("E");
			node.addAttribute("ui.label", "5");
			node.setAttribute("xy", 1, 0);
			node = graph.getNode("F");
			node.addAttribute("ui.label", "6");
			node.setAttribute("xy", -0.75, -0.75);
			node = graph.getNode("G");
			node.addAttribute("ui.label", "7");
			node.setAttribute("xy", 0.75, -0.75);
			node = graph.getNode("H");
			node.addAttribute("ui.label", "8");
			node.setAttribute("xy", 0, -1);
		}else {
			Node node = graph.getNode("A");
			node.addAttribute("ui.label", "1");
			node.setAttribute("xy", -0.25, 0.8);	
			node = graph.getNode("B");
			node.addAttribute("ui.label", "2");
			node.setAttribute("xy", 0.25, 0.8);
			node = graph.getNode("C");
			node.addAttribute("ui.label", "3");
			node.setAttribute("xy", -0.4, 0);
			node = graph.getNode("D");
			node.addAttribute("ui.label", "4");
			node.setAttribute("xy", 0.4, 0);
			node = graph.getNode("E");
			node.addAttribute("ui.label", "5");
			node.setAttribute("xy", -0.75, -0.60);
			node = graph.getNode("F");
			node.addAttribute("ui.label", "6");
			node.setAttribute("xy", 0.75, -0.60);
			node = graph.getNode("G");
			node.addAttribute("ui.label", "7");
			node.setAttribute("xy", -0.25, -0.8);
			node = graph.getNode("H");
			node.addAttribute("ui.label", "8");
			node.setAttribute("xy", 0.25, -0.8);
		}
	}
	public void calcularMetricas(Graph graph) {
		
	}
	public void proximoGrafo(Graph graph, Configuracoes config) throws IndexOutOfBoundsException{// cria Grafo a partir de matriz de adjacencia
		getGrafoZerado(graph, config);//deixa o grafo zerado
		MatrizAdjacencia matriz = dao.geMatrizAdjacencia();//pega a proxima matriz de adjacencia
		for(int linha = 0; linha<graph.getNodeCount();linha++) {
			for(int coluna = 0; coluna <graph.getNodeCount();coluna++) {
				if(coluna > linha) {
					if(matriz.getDados()[linha][coluna]==1) {
					graph.addEdge(graph.getNode(linha).toString()+graph.getNode(coluna).toString(), (Node)graph.getNode(linha), (Node)graph.getNode(coluna));
					}
				}
			}
		}
		
	}
	public void atualizarGrafoTempoExecucao(Graph graph, Configuracoes config) { // cria trhead para executar em paralelo
		new Thread() {
			@Override
			public void run() {
				try {
					proximoGrafo(graph, config);// atualiza grafo
					
					//colorindo o grafo
					Algorithm alg= new Colorir(config);
					alg.init(graph);
					alg.compute();
					AlgoritmoController ac = new AlgoritmoController(graph);
					ac.gerarTodosOsCalculos();
					// espera para atualizar mais uma vez
					sleep(config.getTempoAtualizacao());
					
					//se alguém apertar em parar
					if(!config.isPararGrafo()) {
					atualizarGrafoTempoExecucao(graph, config); // repete o metodo
					}else {
						getGrafoZerado(graph, config);	
					}
				} catch (InterruptedException e) {
					System.out.println("Termino de execução2");
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e) {
					// TODO: handle exception
					System.out.println("Termino de execução");
				}
			}
		}.start();

	}	
}
