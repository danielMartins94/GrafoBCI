package model;

public class Configuracoes {
	private int tempoAtualizacao;
	private int registrosPorGrafo;
	private double limiar;
	private int tipoDeMetrica;
	private int tipoGrafo;
	private int registrosPorAtualizacao;
	public int getRegistrosPorAtualizacao() {
		return registrosPorAtualizacao;
	}
	public void setRegistrosPorAtualizacao(int registrosPorAtualizacao) {
		this.registrosPorAtualizacao = registrosPorAtualizacao;
	}
	public int getTipoGrafo() {
		return tipoGrafo;
	}
	public void setTipoGrafo(int tipoGrafo) {
		this.tipoGrafo = tipoGrafo;
	}
	public int getTipoDeMetrica() {
		return tipoDeMetrica;
	}
	public void setTipoDeMetrica(int tipoDeMetrica) {
		this.tipoDeMetrica = tipoDeMetrica;
	}
	public double getLimiar() {
		return limiar;
	}
	public void setLimiar(double limiar) {
		this.limiar = limiar;
	}
	private boolean pararGrafo;
	public boolean isPararGrafo() {
		return pararGrafo;
	}
	public void setPararGrafo(boolean pararGrafo) {
		this.pararGrafo = pararGrafo;
	}
	public int getTempoAtualizacao() {
		return tempoAtualizacao;
	}
	public void setTempoAtualizacao(int tempoAtualizacao) {
		this.tempoAtualizacao = tempoAtualizacao;
	}
	public int getRegistrosPorGrafo() {
		return registrosPorGrafo;
	}
	public void setRegistrosPorGrafo(int registrosPorGrafo) {
		this.registrosPorGrafo = registrosPorGrafo;
	}
}
