package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import entities.Votacao;
import view.Menu;

public class Votacao2021 {

	Random random = new Random();
	Menu m = new Menu();
	Votacao[] votacao = new Votacao[200];

	public void showMetodos(int opcao) {
		switch (opcao) {

		case 0:
			JOptionPane.showMessageDialog(null, "Finalizado!");
			break;

		case 1:
			FCADASTRAVOTACAO();
			break;

		case 2:
			FCLASSIFICASECAO();
			break;

		case 3:
			int opc = m.showIndicadores();
			switch (opc) {
			case 1:
				ELEITORESPORSECAO();
				break;
			case 2:
				MAIOREMENORSECAO();
				break;
			case 3:
				VOTOSPORCANDIDATO();
				break;
			case 4:
				DEZCANDIDATOSMAISVOTADOS();
				break;
			}
			break;

		case 4:
			String nomeArq = JOptionPane.showInputDialog("Informe o nome do arquivo");
			try {
				BufferedWriter escrita = new BufferedWriter(new FileWriter(nomeArq + ".txt"));
				for (int i = 0; i < votacao.length; i++) {
					escrita.write(votacao[i].getNumeroCandidato() + "\n");
					escrita.write(votacao[i].getNumeroSecao() + "\n");
					escrita.newLine();
				}
				escrita.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erro ao salvar dados no arquivo: " + nomeArq);
			}
			break;

		case 5:
			String arq = JOptionPane.showInputDialog("Informe o nome do arquivo");
			try {
				BufferedReader leitura = new BufferedReader(new FileReader(arq + ".txt"));
				for (int i = 0; i < votacao.length; i++) {
					votacao[i] = new Votacao();
					votacao[i].setNumeroCandidato(Integer.parseInt(leitura.readLine()));
					votacao[i].setNumeroSecao(Integer.parseInt(leitura.readLine()));
					leitura.readLine();
				}
				leitura.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erro ao ler dados no arquivo: " + arq);
			}
			break;

		default:
			JOptionPane.showMessageDialog(null, "Opção inválida!");
			break;
		}
	}

	private void FCADASTRAVOTACAO() {
		for (int i = 0; i < votacao.length; i++) {
			int secao = random.nextInt(10);
			int votoCandidato = random.nextInt(301);
			votacao[i] = new Votacao(secao, votoCandidato);
		}
		System.out.println("Votação cadastrada!");
	}

	private void FCLASSIFICASECAO() {

		int n = votacao.length;
		boolean trocou;

		do {
			trocou = false;

			for (int i = 1; i < n; i++) {
				if (votacao[i - 1].getNumeroSecao() > votacao[i].getNumeroSecao()) {
					Votacao temp = votacao[i - 1];
					votacao[i - 1] = votacao[i];
					votacao[i] = temp;

					trocou = true;
				}
			}

			n--;

		} while (trocou);
		
		System.out.println("Dados classificados por Seção!");
	}

	private void ELEITORESPORSECAO() {
		int[] eleitores = new int[10];

		for (Votacao voto : votacao) {
			eleitores[voto.getNumeroSecao()]++;
		}

		for (int i = 0; i < eleitores.length; i++) {
			System.out.println("Eleitores na seção " + i + ": " + eleitores[i]);
		}
	}

	private void MAIOREMENORSECAO() {
		int[] votosSecao = new int[10];

		for (Votacao voto : votacao) {
			votosSecao[voto.getNumeroSecao()]++;
		}

		int maisVotos = Integer.MIN_VALUE;
		int secaoMais = Integer.MIN_VALUE;
		int menosVotos = Integer.MAX_VALUE;
		int secaoMenos = Integer.MAX_VALUE;

		for (int i = 0; i < votosSecao.length; i++) {
			if (votosSecao[i] > maisVotos) {
				secaoMais = i;
				maisVotos = votosSecao[i];
			}

			if (votosSecao[i] < menosVotos) {
				secaoMenos = i;
				menosVotos = votosSecao[i];
			}
		}

		System.out.println("Seção com mais votos: " + secaoMais);
		System.out.println("Seção com menos votos: " + secaoMenos);
	}

	private void VOTOSPORCANDIDATO() {
		int[] votosCandidato = new int[300];

		for (Votacao voto : votacao) {
			votosCandidato[voto.getNumeroCandidato()]++;
		}

		for (int i = 0; i < votosCandidato.length; i++) {
			System.out.println("Candidato " + i + ": " + votosCandidato[i] + " votos");
		}
	}

	private void DEZCANDIDATOSMAISVOTADOS() {
		int[] votosPorCandidato = new int[302];

		// Contar os votos por candidato
		for (Votacao voto : votacao) {
			votosPorCandidato[voto.getNumeroCandidato()]++;
		}

		// Encontrar e exibir os 10 candidatos mais votados
		System.out.println("Os 10 candidatos mais votados:");
		encontraECexibeCandidatosMaisVotados(votosPorCandidato, 10, 0);
	}

	private void encontraECexibeCandidatosMaisVotados(int[] votosPorCandidato, int n, int i) {
		if (n > 0 && i < votosPorCandidato.length) {
			int candidatoMaisVotado = encontraCandidatoMaisVotado(votosPorCandidato);
			int votos = votosPorCandidato[candidatoMaisVotado];
			System.out.println("Candidato " + candidatoMaisVotado + ": " + votos + " votos");

			// Zerar os votos desse candidato para não contá-los novamente
			votosPorCandidato[candidatoMaisVotado] = -1;

			// Chamar recursivamente para encontrar o próximo candidato mais votado
			encontraECexibeCandidatosMaisVotados(votosPorCandidato, n - 1, i + 1);
		}
	}

	private int encontraCandidatoMaisVotado(int[] votosPorCandidato) {
		int indiceMaisVotado = 0;
		int votosMaisVotado = -1;

		for (int i = 1; i < votosPorCandidato.length; i++) {
			if (votosPorCandidato[i] > votosMaisVotado && votosPorCandidato[i] != -1) {
				indiceMaisVotado = i;
				votosMaisVotado = votosPorCandidato[i];
			}
		}

		return indiceMaisVotado;
	}
}
