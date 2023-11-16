package view;

import java.io.IOException;

import model.Votacao2021;

public class Principal {
	public static void main(String[] args) throws IOException {

		Menu m = new Menu();
		Votacao2021 v = new Votacao2021();

		do {
			m.showMenu();
			v.showMetodos(m.getOpcao());
		} while (m.getOpcao() != 0);
	}
}
