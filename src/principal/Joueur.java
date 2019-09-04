package principal;

import java.io.Serializable;

public class Joueur implements Serializable, Comparable<Joueur> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String pseudo;
	public int temps;
	public int budget;
	public int productivite;
	public int satisfactionClient;
	
	
	public Joueur(String pseudo) {
		this.pseudo=pseudo;
		temps=50;
		budget=50;
		productivite=50;
		satisfactionClient=50;
	}
	
	public String toString() {
		return(	pseudo +" :\n"+
				"| Temps : "+temps+"% |\t"+
				"Budget : "+budget+"% |\t"+
				"Productivité : "+productivite+"% |\t"+
				"Satisfaction Client : "+satisfactionClient+"% |\n");
	}
	
	public boolean aPerdu() {
		return (this.budget<=0 || this.productivite<=0 || this.satisfactionClient<=0 || this.temps<=0);
	}
	
	@Override
	public int compareTo(Joueur j) {
		if(this.getTotal() < j.getTotal()) return 1;
		else if(this.getTotal() == j.getTotal()) return 0;
		return -1;
	}
	
	public int getTotal() {
		return temps+budget+satisfactionClient+productivite;
	}
}