package algoritmos;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.measure.AbstractCentrality.NormalizationMode;
import org.graphstream.algorithm.measure.EigenvectorCentrality;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class AlgoritmoController {
	private Graph grafo;
	public AlgoritmoController(Graph grafo) {
		this.grafo = grafo;
	}
	public int calcularGrau(Node n) {
		return n.getDegree();
	}
	public double calcularCoeficienteDeClusterizacao(Node n) {
		return Toolkit.clusteringCoefficient(n);
	}
	public double calcularCentralidadeDeIntermediacao(int index) {
		BetweennessCentrality bcb = new BetweennessCentrality("Cb");
		bcb.init(grafo);
		bcb.compute();
		return grafo.getNode(index).getAttribute("Cb");
	}
	public double calcularCentralidadeDeAltoVetor(int index) {
		EigenvectorCentrality ece = new EigenvectorCentrality("eigenvector", NormalizationMode.NONE);
		ece.init(grafo);
		ece.compute();
		return grafo.getNode(index).getAttribute("eigenvector");
	}
	
	
	public double calcularGrauMedio() {
		return Toolkit.averageDegree(this.grafo);	
	}
	public double calcularDensidade() {
		return Toolkit.density(grafo);
	}
	public double calcularCoeficienteDeClusterizacaoMedio() {
		return Toolkit.averageClusteringCoefficient(grafo);
	}
	public double calcularCentralidadeDeIntermediacaoMedia() {
		BetweennessCentrality bcb = new BetweennessCentrality("Cb");
		bcb.init(grafo);
		bcb.compute();
		double soma = 0.0;
		for(int coluna = 0; coluna <grafo.getNodeCount();coluna++) {
			System.out.println(coluna+"-----"+(Double)grafo.getNode(coluna).getAttribute("Cb"));
			soma += (Double)grafo.getNode(coluna).getAttribute("Cb");
		}
		return soma/grafo.getNodeCount();
	}
	public double calcularCentralidadeDeAltoVetorMedia() {
		EigenvectorCentrality ece = new EigenvectorCentrality("eigenvector", NormalizationMode.NONE);
		ece.init(grafo);
		ece.compute();
		double soma = 0.0;
		for(int coluna = 0; coluna <grafo.getNodeCount();coluna++) {
			System.out.println(coluna+"-----"+(Double)grafo.getNode(coluna).getAttribute("eigenvector"));
			soma = soma + (Double)grafo.getNode(coluna).getAttribute("eigenvector");
		}
		return soma/grafo.getNodeCount();
	}
	public void gerarTodosOsCalculos() {
		//System.out.println("tesste"+calcularCentralidadeDeIntermediacaoMedia());
		//System.out.println(calcularCoeficienteDeClusterizacaoMedio()+"/"+calcularGrauMedio());
		for(int coluna = 0; coluna <grafo.getNodeCount();coluna++) {
			System.out.println(coluna+"-----"+Toolkit.clusteringCoefficient(grafo.getNode(coluna)));
		}
	}
}
