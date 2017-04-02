package ku.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
/**
 * Testing the SyllableCounter that check from a dictionary.
 * @author Theeruth Borisuth
 */
public abstract class DictMain   {
	/**
	 * A method that import wods from the dictionary.
	 * @throws IOException
	 */
	public static void Test() throws IOException{
		int sum = 0 ;
		final String DICT_URL = "http://se.cpe.ku.ac.th/dictionary.txt";
		URL url = new URL( DICT_URL );
		InputStream input = url.openStream();
		BufferedReader reader =
				new BufferedReader( new InputStreamReader( input )); {
		while( true ) {
			String word = reader.readLine();
			// BufferedReader.readLine() returns null at end of the input
			if (word == null) break;
			
			sum += SimpleSyllableCounter.countSyllables(word) ;
		}
		System.out.println(sum);

	}
	}
	/**
	 * Run the class
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Test();
	}
}
