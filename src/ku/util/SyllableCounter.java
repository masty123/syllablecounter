package ku.util;

public abstract class SyllableCounter {
	
	
	static char [] vowel = {'a','A','e','E','i','I','o','O','u','U'};

	
	 static boolean isLetter(char c){
		return Character.isLetter(c);
	}
	
	 boolean isIgnore(char c){
		if (c == '\'') return true ;
		return false;
	}
	
	 boolean isHyphen(char c){
		if (c == '-') return true ;
		return false ;
	}
	
	static boolean isVowel(char c){
		
		for (char checking : vowel){
				
			if (c == checking ) return true ;
		}
		
		return false ;
	}
}
