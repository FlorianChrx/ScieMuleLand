package principal;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import principal.Strategie.Marche;
import principal.Strategie.Recrutement;

public class Jeu {

	private static Joueur joueur;
	private Scanner sc;
	private double probaEvent;
	private int idxEvent;
	private EvenementAlea events;
	private static int nbTourMax=10;

	public Jeu() throws Exception {
		idxEvent=0;
		events = new EvenementAlea();
	}

	public void lancer(Banque banque) {	
		
		probaEvent=0.2*banque.difficulte.niveau;
		
		double a =0;
		
		Main.clearScreen();
		System.out.println(banque.introduction);

		sc = new Scanner(System.in);
		System.out.println("Entre ton pseudo : ");

		joueur = new Joueur(sc.nextLine());
		System.out.println("");
		
		Strategie strategie = new Strategie(banque.difficulte, joueur.budget , joueur.temps,joueur);

		int idxTour = 0;
		for(Question question : banque.questions) {
			if(idxTour==nbTourMax) break;
			Main.clearScreen();
			idxTour++;
			System.out.println(	"==========================="+"\n"+
					"=======    TOUR "+idxTour+"   ======="+"\n"+
					"===========================\n\n");	

			int choix=0;
			while (choix!=4) {
				if(joueur.aPerdu()) break;
				System.out.println(joueur.toString());
				System.out.println("\t\t Choix Stratégique \n\n"+
						"1. Marché\n"+
						"2. Recrutement\n"+
						"3. Renvoyer\n"+
						"4. Continuer\n");
				sc = new Scanner(System.in);
				System.out.println("Entre le numéro de ton choix ici :");
				String entree = sc.nextLine();
				Pattern p = Pattern .compile("1|2|3|4");
				Matcher m = p.matcher(entree);

				while (!m.matches()) {
					System.out.println("Entre le numéro de ton choix ici :");
					entree = sc.nextLine();
					m = p.matcher(entree);
				}
				choix=Integer.valueOf(entree);

				Main.clearScreen();

				switch (choix) {
				case 1:
					Main.clearScreen();
					System.out.println(	"==========================="+"\n"+
							"=======    TOUR "+idxTour+"   ======="+"\n"+
							"===========================\n\n");
					System.out.println(joueur.toString());
					int i=0;
					for(Marche article : Marche.values()) {
						i++;
						System.out.println(i+"."+article.name()+" : prix : "+article.getPrix()+"% du budget");
					}
					
					System.out.println("\n\n Que voulez-vous acheter ?");
					Scanner sc = new Scanner(System.in);
					String entree2 = "";
					Pattern p2 = Pattern .compile("1|2|3");
					Matcher m2;

					do {
						System.out.println("Entre le numéro de ton choix ici :");
						entree2 = sc.nextLine();
						m2 = p2.matcher(entree2);
					} while (!m2.matches());
					
					choix=Integer.valueOf(entree2);
					
					Marche articleChoisie = Arrays.asList(Marche.values()).get(choix-1);
					Main.clearScreen();
				
					System.out.println(	"==========================="+"\n"+
							"=======    TOUR "+idxTour+"   ======="+"\n"+
							"===========================\n\n");
					System.out.println("Bravo vous avez acheter "+articleChoisie.name());
					strategie.acheter(articleChoisie);
					
					
					break;
				case 2:
					Main.clearScreen();
					System.out.println(	"==========================="+"\n"+
							"=======    TOUR "+idxTour+"   ======="+"\n"+
							"===========================\n\n");	
					System.out.println(joueur.toString());
					
					int i2=0;
					for(Recrutement employe : Recrutement.values()) {
						i2++;
						System.out.println(i2+"."+employe.name()+" : prix : "+employe.getSalaire()+"% du budget\n");
					}
					
					System.out.println("\n\n Que voulez-vous acheter ?");
					Scanner sc1 = new Scanner(System.in);
					String entree21 = "";
					Pattern p21 = Pattern .compile("1|2|3|4|5");
					Matcher m21;

					do {
						System.out.println("Entre le numéro de ton choix ici :");
						entree21 = sc1.nextLine();
						m21 = p21.matcher(entree21);
					} while (!m21.matches());
					choix=Integer.valueOf(entree21);
					
					Recrutement devChoisi = Arrays.asList(Recrutement.values()).get(choix-1);
				
					Main.clearScreen();
					System.out.println(	"==========================="+"\n"+
							"=======    TOUR "+idxTour+"   ======="+"\n"+
							"===========================\n\n");	
					System.out.println("Bravo vous avez recruter "+devChoisi.name());
					strategie.recruter(devChoisi);
					
					break;
				case 3:
					Main.clearScreen();
					System.out.println(	"==========================="+"\n"+
							"=======    TOUR "+idxTour+"   ======="+"\n"+
							"===========================\n\n");	
					System.out.println(joueur.toString());
					int idx = 0;
					for(Recrutement dev: strategie.getEmploye()){
						idx++;
						System.out.println(idx+"."+dev.name()+" : prix : -"+dev.getProductivite()+"% de la productivité\n");
					}
					
					System.out.println("\n\n Que voulez-vous renvoyer ?");
					Scanner sc2 = new Scanner(System.in);
					String entree22 = "";
					Pattern p22 = Pattern .compile("[1-"+idx+"]");
					Matcher m22;

					do {
						System.out.println("Entre le numéro de ton choix ici :");
						entree21 = sc2.nextLine();
						m22 = p22.matcher(entree21);
					} while (!m22.matches());
					choix=Integer.valueOf(entree21);
					
					Recrutement devVirer = strategie.getEmploye().get(choix-1);
					Main.clearScreen();
					System.out.println(	"==========================="+"\n"+
							"=======    TOUR "+idxTour+"   ======="+"\n"+
							"===========================\n\n");
					System.out.println("Bravo vous avez renvoyer "+devVirer.name());
					strategie.virer(devVirer);
					
					break;
				case 4:
					Main.clearScreen();
					System.out.println(	"==========================="+"\n"+
							"=======    TOUR "+idxTour+"   ======="+"\n"+
							"===========================\n\n");	
					a = Math.random();
					if(a<=probaEvent && idxEvent<events.getListeSize()) {
						Evenement e = events.getEvent(idxEvent);
						idxEvent++;
						System.out.println("\n|| EVENEMENT ||\n"+e.getIntitule()+"\n");
						e.RetirerPoint(joueur);
					}

					System.out.println(joueur.toString());
					question.repondreQuestion(joueur);	
					break;
					
				}
				//break;
			}
		}
		Main.clearScreen();

		if(joueur.aPerdu()) {
			System.out.println("Tu as perdu.");
		}else {
			System.out.println("Bien joué ! Tu as gagné.\n Tu as obtenu "+joueur.getTotal()+" points.");
		}

		try {
			new Leaderboard().addPlayer(joueur);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
