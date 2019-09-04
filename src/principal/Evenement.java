package principal;

public class Evenement {

	private String intitule;
	private int temps;
	private int budget;
	private int productivite;
	private int satisfactionClient;
	
	public Evenement(String i, int t, int b, int p, int s) {
		intitule=i;
		temps=t;
		budget=b;
		productivite=p;
		satisfactionClient=s;
	}
	
	public void RetirerPoint(Joueur j) {
		j.budget += budget;
		j.temps += temps;
		j.productivite += productivite;
		j.satisfactionClient += satisfactionClient;
	}
	
	public String getIntitule() {
		return intitule;
	}
}
