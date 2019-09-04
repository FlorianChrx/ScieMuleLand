package principal;

public class Proposition {
	
	private String libelle;
	private int valueTemps;
	private int valueBudget;
	private int valueProductivite;
	private int valueSatisfaction;
	
	public Proposition(String s,int temps,int budget,int productivite,int satisfaction) {
		this.libelle = s;
		this.valueTemps = temps;
		this.valueBudget = budget;
		this.valueProductivite = productivite;
		this.valueSatisfaction = satisfaction;
	}
	
	public int getValueTemps() {return this.valueTemps;}
	public int getValueBudget() {return this.valueBudget;}
	public int getValueProductivite() {return this.valueProductivite;}
	public int getValueSatisfaction() {return this.valueSatisfaction;}
	
	public String getLibelle() {return this.libelle;}
	
	public void effect(Joueur j) {
		j.budget += getValueBudget();
		j.productivite += getValueProductivite();
		j.satisfactionClient += getValueSatisfaction();
		j.temps += getValueTemps();
	}

}
