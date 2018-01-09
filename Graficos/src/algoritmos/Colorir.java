package algoritmos;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import model.Configuracoes;

public class Colorir implements Algorithm{
	private Graph grafo;
	private Configuracoes config;
	private AlgoritmoController ag;
	public Colorir(Configuracoes config) {
		super();
		this.config = config;
		
	}
	@Override
	public void compute() {
		// TODO Auto-generated method stub
		if(config.getTipoDeMetrica()==1)
			for(int i = 0;i< grafo.getNodeCount();i++) {
				Node node = grafo.getNode(i);
				double c = ag.calcularCoeficienteDeClusterizacao(node);
				node.addAttribute("ui.label", (i+1)+" - "+c);
				if(c == 0.0) {
				node.addAttribute("ui.class", "zero");
				}else if(c <= 0.1) {
					node.addAttribute("ui.class", "um");
				}else if(c<=0.25) {
					node.addAttribute("ui.class", "dois");
				}else if(c<=0.4) {
					node.addAttribute("ui.class", "tres");
				}else if(c<=0.55) {
					node.addAttribute("ui.class", "quatro");
				}else if(c<=0.7) {
					node.addAttribute("ui.class", "cinco");
				}else if(c<=0.85) {
					node.addAttribute("ui.class", "seis");
				}else if(c<=1) {
					node.addAttribute("ui.class", "sete");
				}
			}	
		else if(config.getTipoDeMetrica()==2)
			for(int i = 0;i< grafo.getNodeCount();i++) {
				Node node = grafo.getNode(i);
				double c = ag.calcularCentralidadeDeAltoVetor(i);
				node.addAttribute("ui.label", (i+1)+" - "+c);
				if(c == 0.0) {
				node.addAttribute("ui.class", "zero");
				}else if(c  <= 0.1) {
					node.addAttribute("ui.class", "um");
				}else if(c <= 0.2) {
					node.addAttribute("ui.class", "dois");
				}else if(c <= 0.3) {
					node.addAttribute("ui.class", "tres");
				}else if(c <= 0.4) {
					node.addAttribute("ui.class", "quatro");
				}else if(c <= 0.5) {
					node.addAttribute("ui.class", "cinco");
				}else if(c <= 0.6) {
					node.addAttribute("ui.class", "seis");
				}else if(c > 0.6) {
					node.addAttribute("ui.class", "sete");
				}
			}	
		else if(config.getTipoDeMetrica()==3)
			for(int i = 0;i< grafo.getNodeCount();i++) {
				Node node = grafo.getNode(i);
				double c = ag.calcularCentralidadeDeIntermediacao(i);
				node.addAttribute("ui.label", (i+1)+" - "+c);
				if(c == 0.0) {
				node.addAttribute("ui.class", "zero");
				}else if(c <= 1) {
					node.addAttribute("ui.class", "um");
				}else if(c<= 2) {
					node.addAttribute("ui.class", "dois");
				}else if(c<= 3) {
					node.addAttribute("ui.class", "tres");
				}else if(c<= 4) {
					node.addAttribute("ui.class", "quatro");
				}else if(c<= 5) {
					node.addAttribute("ui.class", "cinco");
				}else if(c<= 6) {
					node.addAttribute("ui.class", "seis");
				}else if(c>6) {
					node.addAttribute("ui.class", "sete");
				}
			}	
		else
			for(int i = 0;i< grafo.getNodeCount();i++) {
				Node node = grafo.getNode(i);
				int c = node.getDegree();
				node.addAttribute("ui.label", (i+1)+" - "+c);
				if(c == 0) {
				node.addAttribute("ui.class", "zero");
				}else if(c == 1) {
					node.addAttribute("ui.class", "um");
				}else if(c==2) {
					node.addAttribute("ui.class", "dois");
				}else if(c==3) {
					node.addAttribute("ui.class", "tres");
				}else if(c==4) {
					node.addAttribute("ui.class", "quatro");
				}else if(c==5) {
					node.addAttribute("ui.class", "cinco");
				}else if(c==6) {
					node.addAttribute("ui.class", "seis");
				}else if(c==7) {
					node.addAttribute("ui.class", "sete");
				}
			}
	}

	@Override
	public void init(Graph grafo) {
		// TODO Auto-generated method stub
		this.grafo = grafo;
		ag = new AlgoritmoController(grafo);
	}

}
