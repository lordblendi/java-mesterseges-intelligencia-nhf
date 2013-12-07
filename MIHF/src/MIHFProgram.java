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
		System.out.println(parseHTML.mTargyak.get(1).mNev);
		
	}

}
