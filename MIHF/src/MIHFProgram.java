import java.io.IOException;


public class MIHFProgram {
	
	private static ParseHTML parseHTML;
	public static void main(String[] args) {
		ParseHTML parseHTML = new ParseHTML();
		try {
			parseHTML.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(parseHTML.mTargyak);

	}

}
