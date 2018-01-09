package dao;

import java.util.List;

import Utilidades.Calculos;
import model.Configuracoes;
import model.Eletrodo;
import model.MatrizAdjacencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GrafoDao {
	private List listaMatrizAdjacencia;
	private List<Eletrodo> eletrodos;
	public GrafoDao(Configuracoes config) {
		eletrodos = new ArrayList<Eletrodo>();
		for(int i=0;i<8;i++)
		eletrodos.add(new Eletrodo());
		carregarEletrodosDeArquivo();
		listaMatrizAdjacencia = converterDadosEmMatrizAdjacencia(config);
		/*MatrizAdjacencia matriz = new MatrizAdjacencia();
		matriz.setDados(new double[][] {{0,1,1,1,0,1,1,1},
										{1,0,1,1,1,1,1,1},
										{1,1,0,1,1,0,1,1},
										{1,1,1,0,0,1,1,1},
										{0,1,1,0,0,1,1,0},
										{1,1,0,1,1,0,1,1},
										{1,1,1,1,1,1,0,1},
										{1,1,1,1,0,1,1,0}});
		listaMatrizAdjacencia.add(matriz);
		matriz = new MatrizAdjacencia();
		matriz.setDados(new double[][] {{0,0,1,1,1,1,1,1},
										{0,0,0,1,1,0,1,1},
										{1,0,0,0,1,1,1,1},
										{1,1,0,0,0,1,1,1},
										{1,1,1,0,0,0,1,1},
										{1,0,1,1,0,0,0,1},
										{1,1,1,1,1,0,0,0},
										{1,1,1,1,1,1,0,0}});
		listaMatrizAdjacencia.add(matriz);
		matriz = new MatrizAdjacencia();
		matriz.setDados(new double[][] {{0,1,1,1,1,1,1,1},
										{1,0,1,1,1,1,1,1},
										{1,1,0,1,1,1,1,1},
										{1,1,1,0,1,1,1,1},
										{1,1,1,1,0,1,1,1},
										{1,1,1,1,1,0,1,1},
										{1,1,1,1,1,1,0,1},
										{1,1,1,1,1,1,1,0}});
		listaMatrizAdjacencia.add(matriz);
		matriz = new MatrizAdjacencia();
		matriz.setDados(new double[][] {{0,1,1,0,0,1,1,0},
										{1,0,1,1,1,1,1,0},
										{1,1,0,1,1,1,1,1},
										{0,1,1,0,1,1,1,1},
										{0,1,1,1,0,0,1,0},
										{1,1,1,1,0,0,1,1},
										{1,1,1,1,1,1,0,1},
										{0,0,1,1,0,1,1,0}});
		listaMatrizAdjacencia.add(matriz);
		matriz = new MatrizAdjacencia();
		matriz.setDados(new double[][] {{0,1,1,1,1,1,1,1},
										{1,0,1,1,1,1,1,1},
										{1,1,0,1,1,1,1,1},
										{1,1,1,0,1,1,1,0},
										{1,1,1,1,0,1,1,0},
										{1,1,1,1,1,0,1,0},
										{1,1,1,1,1,1,0,0},
										{1,1,1,0,0,0,0,0}});
		listaMatrizAdjacencia.add(matriz);
		matriz = new MatrizAdjacencia();
		matriz.setDados(new double[][] {{0,0,0,1,1,1,1,1},
										{0,0,1,1,1,1,1,1},
										{0,1,0,1,1,1,1,1},
										{1,1,1,0,0,1,1,1},
										{1,1,1,0,0,0,0,1},
										{1,1,1,1,0,0,0,0},
										{1,1,1,1,0,0,0,0},
										{1,1,1,1,1,0,0,0}});
		listaMatrizAdjacencia.add(matriz);
		matriz = new MatrizAdjacencia();
		matriz.setDados(new double[][] {{0,1,1,1,1,1,1,0},
										{1,0,1,1,1,1,0,1},
										{1,1,0,0,1,0,1,1},
										{1,1,0,0,0,1,1,1},
										{1,1,1,0,0,0,1,1},
										{1,1,0,1,0,0,1,1},
										{1,0,1,1,1,1,0,1},
										{0,1,1,1,1,1,1,0}});
		listaMatrizAdjacencia.add(matriz);*/
	}
	public List converterDadosEmMatrizAdjacencia(Configuracoes config) {
		List listaMatrizAd = new ArrayList();
		Calculos c = new Calculos();
		for(int inicial=0; inicial< eletrodos.get(0).getValores().size();inicial=inicial+config.getRegistrosPorAtualizacao()) {
			int fim = (inicial+(config.getRegistrosPorGrafo()-1) >= eletrodos.get(0).getValores().size())?eletrodos.get(0).getValores().size():inicial+(config.getRegistrosPorGrafo()-1);
		//inicia matriz
			double [][] matriz = new double[][]{{0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0}};
			System.out.println("Inicio Matriz");
			for(int i = 0; i< eletrodos.size();i++) {
				// inicia linha
				double[] linha = new double[] {0,0,0,0,0,0,0,0};
				System.out.println("Inicio Linha");
				for(int z = 0;z < eletrodos.size();z++) {
					linha[z] = c.correlacaoDePearson(eletrodos.get(i).pegarValores(inicial, fim), eletrodos.get(z).pegarValores(inicial, fim), config);
				}
				matriz[i]=linha;
				System.out.println("Fim Linha");
			}
			MatrizAdjacencia matrizAd = new MatrizAdjacencia();
			matrizAd.setDados(matriz);
			listaMatrizAd.add(matrizAd);
			System.out.println("Fim Matriz");
		}
		return listaMatrizAd;
	}
	public void carregarEletrodosDeArquivo() {
		try {
		      FileReader arq = new FileReader("data.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = lerArq.readLine(); // lê a primeira linha
		// a variável "linha" recebe o valor "null" quando o processo
		// de repetição atingir o final do arquivo texto
		     while (linha != null) {
		        String[] arrayLinha = linha.split(",");
		        System.out.println(linha);
		        eletrodos.get(0).adicionarValor(Double.parseDouble(arrayLinha[1]));
		        eletrodos.get(1).adicionarValor(Double.parseDouble(arrayLinha[2]));
		        eletrodos.get(2).adicionarValor(Double.parseDouble(arrayLinha[3]));
		        eletrodos.get(3).adicionarValor(Double.parseDouble(arrayLinha[4]));
		        eletrodos.get(4).adicionarValor(Double.parseDouble(arrayLinha[5]));
		        eletrodos.get(5).adicionarValor(Double.parseDouble(arrayLinha[6]));
		        eletrodos.get(6).adicionarValor(Double.parseDouble(arrayLinha[7]));
		        eletrodos.get(7).adicionarValor(Double.parseDouble(arrayLinha[8]));
		        linha = lerArq.readLine(); // lê da segunda até a última linha
		      }
		      //System.out.println(linha);
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
	}
	public MatrizAdjacencia geMatrizAdjacencia() throws IndexOutOfBoundsException{	
	MatrizAdjacencia matriz =  (MatrizAdjacencia) this.listaMatrizAdjacencia.get(0);
	this.listaMatrizAdjacencia.remove(0);
	return matriz;
	}
}
