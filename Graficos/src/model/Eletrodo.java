package model;

import java.util.ArrayList;
import java.util.List;

public class Eletrodo {
private List<Double> valores;
public List<Double> getValores() {
	return valores;
}
public Eletrodo() {
	this.valores = new ArrayList<Double>();
}
public void adicionarValor (Double valor) {
	this.valores.add(valor);
}
public Double pegarValor(int index) {
	return this.valores.get(index);
}

public List<Double> pegarValores(int indexInicio, int indexFim) {
	return valores.subList(indexInicio, indexFim);
}
public void AdicionarValores (String valores) {
	for(String valor:valores.split(",")) {
		this.adicionarValor(Double.parseDouble(valor));
	}
}
}
