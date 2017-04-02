package ku.util;
/**
 * A class that can count syllable in many case
 * @author Theeruth Borisuth
 *
 */
public class SimpleSyllableCounter extends SyllableCounter  {
	//Sylable alphabets
	Character [] clist = {'a','e','i','o','u'} ;
	
	/**
	 * Checking case and count syllable.
	 * @param oldword
	 * @return syllables
	 */
	 public static int countSyllables(String oldword){
		 String word = oldword.toLowerCase();
		int syllables = 0 ;
		char c = ' ' ;
		State state = State.START;
		for(int k = 0 ; k <  word.length(); k++) {
			c = word.charAt(k);
		
		if (c == '\'')  continue ;
		//checking state.
		switch (state) {
		 
		//Check at start
		case START :
			if (Character.isWhitespace(c))  ;
			else if ( isVowelOrY(c)) {state = State.SINGLE_VOWEL ; }
			else if (Character.isLetter(c)) state = State.CONSONANT ;
			else state = State.NONWORD;
			break;
		
		//Check the consonant.
		case CONSONANT :
			if( isVowelOrY(c)) {state = State.SINGLE_VOWEL ;}
			else if (isLetter(c)) /* stay in consonant state */;
			else if (c == '-') state = State.HYPHEN ;
			else state = State.NONWORD;
			break;
		
		//Check single vowel.
		case SINGLE_VOWEL : 
			syllables++;
			if (isVowel(c)) state = State.MULTIVOWEL ;
			else if ( c == '-') state = State.HYPHEN ;
			else if ( c == 'y') {state = State.SINGLE_VOWEL ; }
			else if (Character.isLetter(c)) state = State.CONSONANT ;
			else state = State.NONWORD ;
			break ;
		
		//Check multivowel
		case MULTIVOWEL :
			if ( c == '-') state = State.HYPHEN ;
			else if ( c == 'y' ) {state = State.SINGLE_VOWEL ; }
			else if (isVowel(c)) state = State.MULTIVOWEL ;
			else if (Character.isLetter(c)) state = State.CONSONANT ;
			else state = State.NONWORD ;
			break ;
		
		//Check Hyphen
		case HYPHEN :
			if ( c == '-') state = State.NONWORD ;
			else if ( c == 'y' ) {state = State.SINGLE_VOWEL ; }
			else if ( isVowelOrY(c)) {state = State.SINGLE_VOWEL ; }
			else if (Character.isLetter(c)) state = State.CONSONANT ;
			else state = State.NONWORD ;
			break ;
		
		//In case if there is no word in it.
		case NONWORD :
			syllables = 0 ;
				break ;
		
			}
		
		}
		if (state == State.SINGLE_VOWEL){
			if (c != 'e' || syllables == 0){
				syllables += 1 ;
			}
		}
		return syllables;		
	}
	 
	 /**
	  * Checking is it a vowel or 'y' alphabet.
	  * @param c
	  * @return true, false
	  */
	 static boolean isVowelOrY(char c){
		switch (c){
		case 'a': return true ;
		case 'e': return true ;
		case 'i': return true ;
		case 'o' : return true ;
		case 'u' : return true ;
		case 'y' : return true ;
		default: return false ;
		}
	}
}
