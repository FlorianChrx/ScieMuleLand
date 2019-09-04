package principal;

public class Constructeur {

	private static int WIDTH = 192;
	private static int HEIGHT = 42;

	private String[] lines = new String[HEIGHT];

	public Constructeur () {
		for (int i = 0; i < HEIGHT; i++) {
			StringBuilder builder = new StringBuilder();
			
			for (int k = 0; k < WIDTH; k++) {
				builder.append(' ');
			}
			
			lines[i] = builder.toString();
		}
	}

	public enum Position { TOP_LEFT_CORNER, TOP_RIGHT_CORNER, TOP, CENTER, BOTTOM, BOTTOM_LEFT_CORNER, BOTTOM_RIGHT_CORNER }

	public enum Alignement { LEFT, CENTER, RIGHT }
	
	public static void main(String[] args) {
		Constructeur test = new Constructeur();		
		test.ajouter("  ╭─────────────────────────────────────────────────────────────╮" + "\n" +
				   "  │"+ "zakoenzaoenzajnejazineji"+ "     				   │" + "\n" +
			       "  │"+ "zakoenzaoenzajnejazineji"+ " │" + "\n" +
			       "  │"+ "zakoenzaoenzajnejazineji"+ " │" + "\n" +
			       "  │"+ "zakoenzaoenzajnejazineji"+ " │" + "\n" +
				   "  ╰──┰──────────────────────────────────────────────╮           ╰──╮"+ "\n" +
			       "     ┃ Actions possibles :                          │   Dilemme    ┝━━━━━━━━━━┓" + "\n" +
				   "     ┃                                              ╰──────────────╯          ┃" + "\n" +
			       "     ┃ 1. "+"zakoenzaoenzajnejazineji"+" 				   	     ┃" + "\n" +
			       "     ┃ 2. "+"zakoenzaoenzajnejazineji"+" 					     ┃" + "\n" +
			       "     ┃ 3. "+"zakoenzaoenzajnejazineji"+" 				             ┃" + "\n" +
			       "     ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛", Position.TOP, Alignement.LEFT);

		test.ajouter("Entrez votre choix ici :", Position.BOTTOM_LEFT_CORNER, Alignement.LEFT);
		test.ajouter("Temps\n⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜\nBudget\n⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜\nProductivité\n⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜\nSatisfaction client\n⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜", Position.BOTTOM_RIGHT_CORNER, Alignement.LEFT);

		
		
		test.afficher();
	}
	
	public void ajouter(String str, Position position , Alignement alignement) {
		switch (position) {
			case TOP:
				centrer(str, 1, alignement);
				break;
			case TOP_LEFT_CORNER:
				auCoin(str, 1, alignement);
				break;
			case TOP_RIGHT_CORNER:
				auCoin(str, 2, alignement);
				break;
			case CENTER:
				centrer(str, 2, alignement);
				break;
			case BOTTOM:
				centrer(str, 3, alignement);
				break;
			case BOTTOM_LEFT_CORNER:
				auCoin(str, 3, alignement);
				break;
			case BOTTOM_RIGHT_CORNER:
				auCoin(str, 4, alignement);
				break;
		}
	}

	public void afficher() {
		StringBuilder builder = new StringBuilder();

		for(String line : lines) {
			builder.append(line).append("\n");
		}

		System.out.println(builder.toString());
	}
	
	private void centrer(String str, int position, Alignement alignement) {
		String[] line = str.split("\n");
		int pos = (position == 3 ? HEIGHT-line.length : 0);

		if(position == 2) {
			pos = HEIGHT/2;
		}
		
		for(String s : line) {
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < (WIDTH-(alignement.equals(Alignement.LEFT) ? line[0].length() : s.length()))/2; i++) {
				builder.append(" ");
			}

			builder.append(s);

			lines[pos] = builder.toString();

			if(pos < HEIGHT-1) pos++;
		}
	}
	
	private void auCoin(String str, int position, Alignement alignement) {
		String[] line = str.split("\n");
		
		int pos = 0;

		switch (position) {
		case 1:		
			for(String s : line) {
				StringBuilder builder = new StringBuilder();
				
				builder.append(s);
				
				for (int i = 0; i < WIDTH-(alignement.equals(Alignement.LEFT) ? line[0].length() : s.length()); i++) {
					builder.append(" ");
				}

				lines[pos] = builder.toString();

				if(pos < HEIGHT-1) pos++;
			}
			break;
		case 2:
			for(String s : line) {
				StringBuilder builder = new StringBuilder();
				
				for (int i = 0; i < WIDTH-(alignement.equals(Alignement.LEFT) ? line[0].length() : s.length())-20; i++) {
					builder.append(" ");
				}
				
				builder.append(s);

				lines[pos] = builder.toString();

				if(pos < HEIGHT-1) pos++;
			}
			break;
		case 3:
			pos = HEIGHT-1-line.length;
			
			for(String s : line) {
				StringBuilder builder = new StringBuilder();
				
				builder.append(s);
				
				for (int i = 0; i < WIDTH-(alignement.equals(Alignement.LEFT) ? line[0].length() : s.length()); i++) {
					builder.append(" ");
				}

				lines[pos] = builder.toString();

				if(pos < HEIGHT-1) pos++;
			}
			break;
		case 4:
			pos = HEIGHT-1-line.length;
			
			System.out.println(pos);
			
			for(String s : line) {
				StringBuilder builder = new StringBuilder();
				
				for (int i = 0; i < (WIDTH-(alignement.equals(Alignement.LEFT) ? line[0].length() : s.length()))-20; i++) {
					builder.append(" ");
				}
				
				builder.append(s);
				
				for (int i = 0; i < 20; i++) {
					builder.append(" ");
				}

				String stringFinal = builder.toString();
				StringBuilder newBuilder = new StringBuilder(lines[pos]);
				
				for (int i = 0; i < lines[pos].length(); i++) {
					char c = lines[pos].charAt(i);
					
					if(c == ' ') {
						newBuilder.setCharAt(i, stringFinal.charAt(i));
					}
				}
				
				lines[pos] = newBuilder.toString();

				if(pos < HEIGHT-1) pos++;
			}
			
			break;
		default:
			
			break;
		}
	}
}
