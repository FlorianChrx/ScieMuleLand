package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banque {

	List<Question> questions = new ArrayList<>();
	String introduction;
	Difficulte difficulte;

	public Banque(Difficulte difficulte) {
		this.difficulte = difficulte;
		String name = "res/";

		switch (difficulte.niveau) {
		case 1:
			name+="niveau1";
			break;
		case 2:
			name+="niveau2";
			break;
		case 3:
			name+="niveau3";
			break;
		default:
			name+="niveau1";
			break;
		}

		lire(name+".csv");
	}

	public void lire(String name) {
		String[][] lines = new String[100][4];

		try {
			BufferedReader reader = new BufferedReader(new FileReader(name));

			this.introduction = reader.readLine();
			this.introduction=this.introduction.replace("pligne", "\n");
			String line = reader.readLine();
			
			int k = 1;
			
			while(line != null) {
				lines[k] = line.split(";");
				line = reader.readLine();
				
				Proposition[] propositions = new Proposition[3];
				int i = 1;

				while(i < 4) {
					String[] prop = lines[k][i].substring(1).split(":");

					propositions[i-1] = new Proposition(prop[4], Integer.parseInt(prop[0]), Integer.parseInt(prop[1]), Integer.parseInt(prop[2]), Integer.parseInt(prop[3]));
					i++;
				}

				try {
					questions.add(new Question(lines[k][0], propositions));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				k++;
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.shuffle(questions);
	}
}
