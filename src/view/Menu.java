package view;

import javax.swing.JOptionPane;

public class Menu {

	private int opcao;

	public int getOpcao() {
		return opcao;
	}

	public void showMenu() {
		opcao = Integer.parseInt(JOptionPane.showInputDialog(
				"Sistema de Votação\n01. Carregar seção/eleitor\n02. Classificar seções\n03. Mostrar Indicadores\n04. Gravar dados\n05. Ler dados\n00. Finalizar"));
	}

	public int showIndicadores() {
		int opc = Integer.parseInt(JOptionPane.showInputDialog(
				"Indicadores\n01. Eleitores por secao\n02. Seção com maior e menor número de eleitores\n03. Votos por candidato\n04. 10 Primeiros colocados\n00. Voltar"));
		return opc;
	}


}
