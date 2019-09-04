package test;

import java.util.Scanner;

import principal.Joueur;
import principal.Proposition;
import principal.Question;

public class QuestionTest {

	public static void main(String[] args) {
		Proposition p1 = new Proposition("prop 1", 1, 2, 3, 4);
		Proposition p2 = new Proposition("prop 2", 1, 2, 3, 4);
		Proposition p3 = new Proposition("prop 3", 1, 2, 3, 4);
		Question quest = null;
		try {
			quest = new Question("Question 1 ?", p1,p2,p3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		quest.repondreQuestion(new Joueur("FLorian"));
	}
	
}
