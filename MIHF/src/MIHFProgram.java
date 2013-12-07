import java.io.IOException;


public class MIHFProgram {
	
	public static void main(String[] args) {
		ParseHTML parseHTML = new ParseHTML();
		try {
			parseHTML.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//egy adott t�rgy r�szletes adatainak a lek�r�se:
		try {
			parseHTML.parseTargy(parseHTML.mTargyak.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
