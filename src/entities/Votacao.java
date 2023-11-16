package entities;

public class Votacao {
	
	private int numeroSecao;
	private int numeroCandidato;
	
	
	
	public Votacao() {
		
	}
	
	public Votacao(int numeroSecao, int numeroCandidato) {
		this.numeroSecao = numeroSecao;
		this.numeroCandidato = numeroCandidato;
	}

	public int getNumeroSecao() {
		return numeroSecao;
	}

	public void setNumeroSecao(int numeroSecao) {
		this.numeroSecao = numeroSecao;
	}

	public int getNumeroCandidato() {
		return numeroCandidato;
	}

	public void setNumeroCandidato(int numeroCandidato) {
		this.numeroCandidato = numeroCandidato;
	}
}
