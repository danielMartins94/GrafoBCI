package view;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import Utilidades.Calculos;

public class Testes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new SingleGraph("Betweenness Test");

//	    E----D  AB=1, BC=5, CD=3, DE=2, BE=6, EA=4  
	//   /|    |  Cb(A)=4
	//  / |    |  Cb(B)=2
	// A  |    |  Cb(C)=0
	//  \ |    |  Cb(D)=2
	//   \|    |  Cb(E)=4
//	    B----C

	Node A = graph.addNode("A");
	Node B = graph.addNode("B");
	Node E = graph.addNode("E");
	Node C = graph.addNode("C");
	Node D = graph.addNode("D");

	graph.addEdge("AB", "A", "B");
	graph.addEdge("BE", "B", "E");
	graph.addEdge("BC", "B", "C");
	graph.addEdge("ED", "E", "D");
	graph.addEdge("CD", "C", "D");
	graph.addEdge("AE", "A", "E");

	BetweennessCentrality bcb = new BetweennessCentrality();
	
	bcb.init(graph);
	bcb.compute();

	System.out.println("A="+ A.getAttribute("Cb"));
	System.out.println("B="+ B.getAttribute("Cb"));
	System.out.println("C="+ C.getAttribute("Cb"));
	System.out.println("D="+ D.getAttribute("Cb"));
	System.out.println("E="+ E.getAttribute("Cb"));
		
	}

}
