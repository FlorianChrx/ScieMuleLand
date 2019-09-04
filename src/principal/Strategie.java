package principal;

import java.util.ArrayList;
import java.util.List;

public class Strategie {

	public enum Marche {
		ORDINATEUR(-10, 0, 5), PLANTES(-2, 0, 3), MACHINE_A_CAFE(-6, 2, 5);

		private int prix;
		private int temps;
		private int productivite;

		Marche(int prix, int temps, int productivite) {
			this.prix = prix;
			this.temps = temps;
			this.productivite = productivite;
		}
		
		public int getPrix() {
			return this.prix;
		}
		
		public int getBonusTemps() {
			return this.temps;
		}
		
		public int getBonusProductivite() {
			return this.productivite;
		}
	}

	public enum Recrutement {

		STAGIAIRE(-2, 0, -2), DEVELOPPEUR(-3, 0, 1), FREELANCE(-5, 3, 1), SCRUM_MASTER(-10, 2, 10), EXPERT(-15, 5, 7);

		private int salaire;
		private int temps;
		private int productivite;

		Recrutement(int salaire, int temps, int productivite) {
			this.salaire = salaire;
			this.temps = temps;
			this.productivite = productivite;
		}
		
		public int getSalaire() {
			return this.salaire;
		}
		
		public int getProductivite() {
			return this.productivite;
		}
	}

	public List<Recrutement> employes = new ArrayList<>();
	public List<Marche> inventaire = new ArrayList<>();
	
	private int budget;
	private int temps;
	private int productivite;
	private Joueur joueur;

	public Strategie(Difficulte difficulte, int budget, int temps,Joueur j) {
		this.budget = budget;
		this.setTemps(temps);
		this.joueur = j;
		
		int developpeur;

		if(difficulte.niveau == 1) {
			employes.add(Recrutement.SCRUM_MASTER);
			
			developpeur = 4;
		}else if(difficulte.niveau == 2) {
			employes.add(Recrutement.FREELANCE);
			employes.add(Recrutement.SCRUM_MASTER);
			
			developpeur = 3;
		}else {
			developpeur = 5;
		}
		
		for (int i = 0; i < developpeur; i++) {
			employes.add(Recrutement.DEVELOPPEUR);
		}	
		
	}

	public void recruter(Recrutement employe) {		
		if(this.budget >= employe.salaire) {
			this.employes.add(employe);

			this.joueur.budget += employe.salaire;
			this.joueur.temps += employe.temps;
			this.joueur.productivite += employe.productivite;
		}
	}
	
	public void virer(Recrutement employe) {
		if(employes.contains(employe)) {
			employes.remove(employe);
			
			this.joueur.productivite -= employe.productivite;
		}
	}
	
	public void acheter(Marche article) {
			this.inventaire.add(article);
			
			this.joueur.budget+= article.prix;
			this.joueur.temps += article.temps;
			this.joueur.productivite += article.productivite;
	}
	
	public List<Recrutement> getEmploye(){
		return this.employes;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public int getProductivite() {
		return productivite;
	}

	public void setProductivite(int productivite) {
		this.productivite = productivite;
	}


}
