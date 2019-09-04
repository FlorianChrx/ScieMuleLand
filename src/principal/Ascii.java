package principal;

public class Ascii {
	
	public static String getTourAffichage(int tour) {
		String res = "";
		res += "╔══════════╗\n";
		res += "║          ║\n";
		res += "║  Tour "+tour+"  ║\n";
		res += "║          ║\n";
		res += "╚══════════╝\n";
		return res;
	}

}
