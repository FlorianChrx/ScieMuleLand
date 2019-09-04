package principal;

import java.util.Comparator;

public class JoueurComparator implements Comparator<Joueur> {

	@Override
	public int compare(Joueur j1, Joueur j2) {
		return j1.compareTo(j2);
	}

}
