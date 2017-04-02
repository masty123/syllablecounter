
package ku.util;

/**
 * Counting syllables but using o-o state machine.
 * @author Theeruth Borisuth

 *
 */
public class OOSyllableCounter extends SyllableCounter {
	//state of object .
	private State state;
	//Char at the start of word .
	private final State START = new StartState();
	//Char is a consonant.
	private final State CONSONANT = new ConsonantState();
	//Is a vowel.
	private final State SINGLE_VOWEL = new SingleVowelState();
	private final State MULTI_VOWEL = new MultiVowelState();
	/** Hyphen state : current char is hyphen */
	private final State HYPHEN = new HyphenState();
	/** Nonword state : current char is nonword */
	private final State NONWORD = new NonwordState();
	
	//for counting syllables
	private int syllables;
	//for counting index.
	private int index;
	//indicate length of word
	private int length;
	
	/**
	 * count the syllables of words. 
	 * @param word
	 * @return syllables
	 */
	public int countSyllables(String word) {
		syllables = 0;
		setState(START);
		length = word.length();
		for (index = 0 ; index < length ; index++) {
			state.handleChar( Character.toLowerCase( word.charAt(index) ) );
		}
		return syllables;
	}
	
	/**
	 * Set state of OOSyllableCounter.
	 * @param state 
	 */
	private void setState(State state) {
		this.state = state;
	}
	
	/** 
	 * State for OOSyllableCounter. 
	 */
	abstract class State {
		/**
		 *	check if this char is syllable.
		 * @param c 
		 */
		public abstract void handleChar(char c);
		
		/** Event when entered this state. */
		public void enterState() { };
	}
	
	/** Behavior for count syllable in Start state. */
	class StartState extends State {

		@Override
		public void handleChar(char c) {
			if ( isLetter(c) && !isVowel(c) ) setState(CONSONANT);
			else if ( isVowel(c) || c == 'y' ) {
				syllables++;
				setState(SINGLE_VOWEL);
			}
			else if ( !isLetter(c) ) setState(NONWORD);
			else if ( c == ' ' ) ; // remain start
		}
		
	}
	
	/** 
	 * Behavior for count syllable in Consonant state. 
	 */
	class ConsonantState extends State {

		@Override
		public void handleChar(char c) {
			if ( index == length-1 &&  c == 'e' && syllables != 0 ) ;
			else if ( isVowel(c) || c == 'y' ) {
				syllables++;
				setState(SINGLE_VOWEL);
			}
			else if ( isLetter(c) && !isVowel(c) || isIgnore(c) ) ; //remain consonant
			else if ( isHyphen(c) ) setState(HYPHEN);
			else if ( !isLetter(c) ) setState(NONWORD);
		}
		
	}
	
	class SingleVowelState extends State {

		@Override
		public void handleChar(char c) {
			if ( isLetter(c) && !isVowel(c) || isIgnore(c) ) setState(CONSONANT);
			else if ( isVowel(c) ) setState(MULTI_VOWEL);
			else if ( isHyphen(c) ) setState(HYPHEN);
			else if ( !isLetter(c) ) setState(NONWORD);
		}
	}
	
	/** 
	 * Behavior for counting syllable in Multi Vowel state. 
	 **/
	class MultiVowelState extends State {

		@Override
		public void handleChar(char c) {
			if ( isLetter(c) && !isVowel(c) || isIgnore(c) ) setState(CONSONANT);
			else if ( isVowel(c) ) ; //remain multivowel
			else if ( isHyphen(c) ) setState(HYPHEN);
			else if ( !isLetter(c) ) setState(NONWORD);
		}
		
	}
	
	/**
	 *  Behavior for counting syllable in Hyphen state. 
	 */
	class HyphenState extends State {

		@Override
		public void handleChar(char c) {
			if ( c == 'y' && index == length-1 ) syllables++;
			else if ( isVowel(c) || c == 'y' ) {
				syllables++;
				setState(SINGLE_VOWEL);
			}
			else if ( isLetter(c) && !isVowel(c) || isIgnore(c) ) setState(CONSONANT);
			else if ( c == 'e' && index == length-1 && syllables != 0) ;
			else if ( isHyphen(c) ) setState(NONWORD);
			else if ( !isLetter(c) ) setState(NONWORD);
		}
		
	}
	
	/**
	 *  Behavior for counting syllable in Nonword state. 
	 */
	class NonwordState extends State {

		@Override
		public void handleChar(char c) {
			enterState();
		}
		
		@Override
		public void enterState() {
			syllables = 0;
		}
		
	}
}
