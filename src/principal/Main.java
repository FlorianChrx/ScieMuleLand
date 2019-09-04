package principal;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private static Jeu jeu;
	private static Scanner sc;

	public final static void clearScreen(){
		for(int i=0; i<50; i++) {
			System.out.println("\n");
		}
		/*try
		{
			final String os = System.getProperty("os.name");

			if (os.contains("Windows"))
			{
				Runtime.getRuntime().exec("cls");
			}
			else
			{
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (final Exception e)
		{ }*/
	}

	public static void main(String[] args) throws Exception {		
		jeu = new Jeu();

		menu();
	}


	static void menu() {
		int choix=0;
		while (choix!=3) {
			System.out.println("   _____      _        __  __  //\\  _        _                     _ \n" + 
					"  / ____|    (_)      |  \\/  ||/ \\|| |      | |                   | |\n" + 
					" | (___   ___ _  ___  | \\  / |_   _| | ___  | |     __ _ _ __   __| |\n" + 
					"  \\___ \\ / __| |/ _ \\ | |\\/| | | | | |/ _ \\ | |    / _` | '_ \\ / _` |\n" + 
					"  ____) | (__| |  __/ | |  | | |_| | |  __/ | |___| (_| | | | | (_| |\n" + 
					" |_____/ \\___|_|\\___| |_|  |_|\\__,_|_|\\___| |______\\__,_|_| |_|\\__,_|\n\n"+
					"1. Jouer\n"+
					"2. LeaderBoard\n"+
					"3. Quitter\n");
			sc = new Scanner(System.in);
			System.out.println("Entre le numéro de ton choix ici :");
			String entree = sc.nextLine();
			Pattern p = Pattern .compile("1|2|3");
			Matcher m = p.matcher(entree);

			while (!m.matches()) {
				System.out.println("Entre le numéro de ton choix ici :");
				entree = sc.nextLine();
				m = p.matcher(entree);
			}
			choix=Integer.valueOf(entree);

			clearScreen();

			switch (choix) {
			case 1:
				clearScreen();
				jeu.lancer(new Banque(new Difficulte()));
				break;
			case 2:
				try {
					System.out.println(new Leaderboard().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	}


	static boolean choixNonValide(String chx) {
		boolean res = true;

		Pattern pattern = Pattern.compile("^[1-3]");
		Matcher match = pattern.matcher(chx);
		if(match.matches() == false) {
			res = false;
		}
		return res;
	}

}