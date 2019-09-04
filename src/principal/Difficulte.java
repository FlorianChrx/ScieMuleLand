package principal;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Difficulte {

	public int niveau = 1;

	public Difficulte() {
		this.niveau = selection();
	}

	private int selection() {
		System.out.println("\t\t Difficulté \n\n"+
				"1. Facile\n"+
				"2. Moyen\n"+
				"3. Difficile\n");

		Scanner sc = new Scanner(System.in);
		System.out.println("Entre le numéro de ton choix ici :");

		String entree = sc.nextLine();
		Pattern p = Pattern .compile("1|2|3");
		Matcher m = p.matcher(entree);

		while (!m.matches()) {
			System.out.println("Entre le numéro de ton choix ici :");
			entree = sc.nextLine();
			m = p.matcher(entree);
		}
		
		return Integer.valueOf(entree);
	}
}
