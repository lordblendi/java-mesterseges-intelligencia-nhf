import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MIHFProgram {
	         static ParseHTML parseHTML = new ParseHTML();
	public static void main(String[] args) {
		try {
			SimilarityFinder.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			parseHTML.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Window w = new Window();
		//egy adott targy alapjan a tobbihez valo hasonlosag szamitasa (a targyak szamat a SimilarityFinder initjeben lehet allitani!!!):
		/*try {
			List<Targy> test = new ArrayList<>();
			test.add(parseHTML.parseTargy(parseHTML.mTargyak.get(1)));
			test.add(parseHTML.parseTargy(parseHTML.mTargyak.get(2)));
			SimilarityFinder.findSimilar(test, new Suly(1.0,1.0,1.0,1.0, 2.0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
