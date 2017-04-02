package ku.util;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String choice= "" ;
		String word = "" ;
		Scanner input = new  Scanner(System.in);
		while (!choice.equals("q")){
			System.out.print("(e)nter (q)uit : ");
			choice = input.nextLine();
			if(choice.equals("e"))
			System.out.print("input word : ");
			word = input.nextLine();
			System.out.println(SimpleSyllableCounter.countSyllables(word));
		}
	}
}
 