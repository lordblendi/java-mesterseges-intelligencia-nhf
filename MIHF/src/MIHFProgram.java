import java.io.IOException;


public class MIHFProgram {
	         static ParseHTML parseHTML = new ParseHTML();
	public static void main(String[] args) {

		try {
			parseHTML.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Window w = new Window();
		//egy adott targy alapjan a tobbihez valo hasonlosag szamitasa (a targyak szamat a SimilarityFinder initjeben lehet allitani!!!):
	/*	try {
			SimilarityFinder.findSimilar(parseHTML.parseTargy(parseHTML.mTargyak.get(1)), new Suly(1,1,1,1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
