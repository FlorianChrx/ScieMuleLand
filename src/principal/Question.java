package principal;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question {
	private String texte;
	private Proposition[] props = new Proposition[3];
	private final int length = 240;
	public Question(String texte, Proposition... props) throws Exception {
		if (props.length != 3) {
			throw new Exception("Erreur nb propositions !");
		}
		this.texte = texte;
		this.props = props;
	}
	public String toString() {
		texte = format(texte);
		return "╭─────────────────────────────────────────────────────────────╮" + "\n" +
			   "│"+ texte.substring(0, 60)+ " │" + "\n" +
		       "│"+ texte.substring(60, 120)+ " │" + "\n" +
		       "│"+ texte.substring(120, 180)+ " │" + "\n" +
		       "│"+ texte.substring(180, 240)+ " │" + "\n" +
			   "╰──┰──────────────────────────────────────────────╮           ╰──╮"+ "\n" +
		       "   ┃ Actions possibles :                          │   Dilemme    ┝━━━━━━━━━━┓" + "\n" +
			   "   ┃                                              ╰──────────────╯          ┃" + "\n" +
		       "   ┃ 1. "+format(props[0].getLibelle()).substring(0,67)+" ┃" + "\n" +
		       "   ┃ 2. "+format(props[1].getLibelle()).substring(0,67)+" ┃" + "\n" +
		       "   ┃ 3. "+format(props[2].getLibelle()).substring(0,67)+" ┃" + "\n" +
		       "   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";
				
				
				
				//texte + " \n 1." + props[0].getLibelle() + " \n 2." + props[1].getLibelle() + " \n 3." + props[2].getLibelle();
	}
	public Proposition getProposition(int idx) throws Exception {
		if (idx >= 3 && idx < 0) {
			throw new Exception("Index de proposition invalide");
		}
		return props[idx];
	}
	
	private String format(String s) {
		String res = "";
		res += s;
		for (int i = 0; i < length; i++) {
			res+=" ";
		}
		return res.substring(0, 241);
	}
	
	public void repondreQuestion(Joueur j) {
		System.out.println(toString());
		Scanner sc = new Scanner(System.in);
		String entree = sc.nextLine();
		Pattern p = Pattern .compile("1|2|3");
		Matcher m = p.matcher(entree);

		while (!m.matches()) {
			System.out.println("Proposition incorrect, recommencez : ");
			entree = sc.nextLine();
			m = p.matcher(entree);
		}
		int nbProp=Integer.valueOf(entree);
		System.out.println("");		
		
		try {
			getProposition(nbProp-1).effect(j);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
