package principal;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Leaderboard implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Joueur> leaderbrd;
    ObjectInputStream in;
    ObjectOutputStream out;
    private String res;

    public Leaderboard() throws IOException {
        try {
            in = new ObjectInputStream(new FileInputStream("res/brd.json"));
            leaderbrd =  (ArrayList<Joueur>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            leaderbrd = new ArrayList<Joueur>();
            save();
        }
    }

    public void save() throws FileNotFoundException, IOException {
        out = new ObjectOutputStream(new FileOutputStream("res/brd.json"));
        out.writeObject(leaderbrd);
        out.close();
    }

    public String toString() {
    	leaderbrd.sort(new JoueurComparator());
    	String nom1 = "          ";
    	String nom2 = "          ";
    	String nom3 = "          ";
    	if(leaderbrd.size() == 1) {
    		nom1 = leaderbrd.get(0).pseudo+"           ";
    	}else if(leaderbrd.size() == 2) {
    		nom1 = leaderbrd.get(0).pseudo+"           ";
    		nom2 = leaderbrd.get(1).pseudo+"           ";
    	}else if(leaderbrd.size() >= 3) {
    		nom1 = leaderbrd.get(0).pseudo+"           ";
    		nom2 = leaderbrd.get(1).pseudo+"           ";
    		nom3 = leaderbrd.get(2).pseudo+"           ";
    	}
    	
    	res =   "                    "+nom1.substring(0, 10)+"                   \n"+
    			"               ╔═════════════════╗\r\n" + 
    			"   "+nom2.substring(0, 10)+"  ║        1        ║\r\n" + 
    			"╔══════════════╝	         ║  "+nom3.substring(0, 10)+"   \r\n" + 
    			"║       2                        ╚═══════════════╗\r\n" + 
    			"║                                        3       ║\r\n" + 
    			"╚════════════════════════════════════════════════╝";
        res += "\n--- Leaderboard ---\n";
        int idx = 1;
        for (Joueur joueur : leaderbrd) {
        	String tmp = joueur.pseudo+"          ";
            res += idx + ". " + tmp.substring(0,10) +"\t | Budget : "+joueur.budget+" | Temps : "+joueur.temps+" | Satisfaction client : "+joueur.satisfactionClient+" | Productivite : "+joueur.productivite+ " | Total : "+joueur.getTotal()+ "\n";
            idx++;
            if (idx > 5) break;
        }
        return res;
    }

    public boolean addPlayer(Joueur j) throws FileNotFoundException, IOException {
        boolean res = leaderbrd.add(j);
        save();
        return res;
    }
}