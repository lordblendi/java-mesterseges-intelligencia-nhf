import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SimilarityFinder {
	
	public static List<Targy> mFullTargyak = new ArrayList<Targy>();
	private static void init() throws Exception {
		int letoltesek = 0; //a targyletoltesek szamanak korlatozasa debughoz
		for (Targy t : ParseHTML.mTargyak) {
			if(letoltesek == 50){	//DEBUG!
				break;
			}
			mFullTargyak.add(ParseHTML.parseTargy(t));
			letoltesek++;	//DEBUG!
		}
	}
	private static List<String> kovKodParse(String kod) {
		List<String> splits = new ArrayList<String>();
		if(kod.contains("/")){
			splits = Arrays.asList(kod.split("/"));
		} else if(kod.contains("+")){
			splits = Arrays.asList(kod.split("\\+"));
			for(String t : splits){
				t.replace("(", "");
				t.replace(")", "");
			}
		} else {
			splits.add(kod);
		}
		
		return splits;
	}
	public static int stringToInt(String value, int _default) {
	    try {
	        return Integer.parseInt(value);
	    } catch (NumberFormatException e) {
	        return _default;
	    }
	}
	public static List<Targy> findSimilar(Targy targy, Suly suly) throws Exception {
		init();
		List<Targy> SulyozottTargyak = new ArrayList<Targy>();
		List<String> kovetelmenyek = kovKodParse(targy.mKovKod);

		for (Targy t : mFullTargyak) {
			double sum = 0;
			//---------------név súlyozása-------------
			double nevNum = 0;
			List<String> kivTargyNev = Arrays.asList(targy.mNev.split(" "));
			List<String> tmpTargyNev = Arrays.asList(t.mNev.split(" "));
			for (String s1 : kivTargyNev) {
				for (String s2 : tmpTargyNev) {
					if(!s1.equalsIgnoreCase("és")){
						if(s1.equalsIgnoreCase(s2)){
							nevNum += 1/kivTargyNev.size();
						}
					}
				}
			}
			sum += suly.mSNev * nevNum;
			//---------------Szemeszter sulyozasa-------------------
			if(t.mSemester != null && !t.mSemester.equals("") && targy.mSemester!=null && !t.mSemester.equals("")){
				if(t.mSemester.equals(targy.mSemester)){
					sum += suly.mSSemester;
				} 
				
			}
			//---------------Követelmények sulyozasa-----------------
			List<String> tmpKod = kovKodParse(t.mKovKod);
			if(kovetelmenyek.size() == tmpKod.size()){
				double kovSim = 0;
				for (String k : tmpKod) {
					for (String k2 : kovetelmenyek) {
						if(k.equals(k2)) {
							kovSim += 1/kovetelmenyek.size();
						}
					}
					
				}
				sum += suly.mSKovKod * (kovSim);
			}
			//-----------------Kredit sulyozasa--------------------
			int targyKredit = stringToInt(targy.mKredit, -10);
			int tKredit = stringToInt(t.mKredit, -100);
			if(targyKredit == tKredit){
				sum += suly.mSKredit;
			} else if(targyKredit + 1 == tKredit || targyKredit-1 == tKredit) {
				sum += suly.mSKredit * 0.1;
			}
			//---------------sulyozas vege------------------------
			t.mSumSuly = sum;
			SulyozottTargyak.add(t);
			
		}
		Collections.sort(SulyozottTargyak);
		return SulyozottTargyak;
	}
}
