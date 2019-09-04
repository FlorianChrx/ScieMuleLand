package principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EvenementAlea {
	
	private ArrayList<Evenement> liste;
	
	public EvenementAlea() throws IOException {
		liste = new ArrayList<Evenement>();
		BufferedReader br=new BufferedReader(new FileReader("res/events.csv"));
		String temp=br.readLine();
		String [] tab;
		while(temp!=null) {
			tab=temp.split(":");
			this.liste.add(new Evenement (tab[0], Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4])));
			temp=br.readLine();
		}
		Collections.shuffle(liste);
	}
	
	public Evenement getEvent(int idx) {
		return liste.get(idx);
	}
	
	public int getListeSize() {
		return liste.size();
	}
	
	
	
}
