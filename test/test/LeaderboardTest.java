package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import principal.Ascii;
import principal.Joueur;
import principal.Leaderboard;

public class LeaderboardTest {
    @SuppressWarnings("unused")
	private static Leaderboard l;
    @SuppressWarnings("unused")
	private static Joueur j3;

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println(new Leaderboard().toString());
        
        System.out.println(Ascii.getTourAffichage(0));
        
        System.out.println("fini");
    }
}