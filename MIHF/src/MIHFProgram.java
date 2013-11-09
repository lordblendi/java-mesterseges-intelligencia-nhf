import java.io.IOException;


public class MIHFProgram {

	private static ParseHTML parseHTML;
	private static void init() {
		parseHTML = new ParseHTML();
	}
	public static void main(String[] args) {
		init();
		try {
			parseHTML.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
