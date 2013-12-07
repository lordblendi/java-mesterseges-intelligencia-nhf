
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseHTML {
	private Document doc;
	private Document targyOldal;
	public static List<Targy> mTargyak = new ArrayList<Targy>();
	public void parse() throws IOException {
		doc = Jsoup.connect("https://www.vik.bme.hu/kepzes/targyak/").get();
		Elements subjects = doc.getElementsByAttributeValue("class", "subject_list");
		Elements links = subjects.select("a[href]");
		int num = 0;
		for (Element link : links) {
			/*
			System.out.println("\nlink : " + link.attr("href"));
			System.out.println("text : " + link.text());
			*/
			num++;
			if( num > 4){
				if(num % 2 == 0){
					Targy tmpTargy = new Targy();
					tmpTargy.mNev = link.text();
					tmpTargy.relURL = link.attr("href");
					mTargyak.add(tmpTargy);
				} else if(!mTargyak.isEmpty()) {
					
					mTargyak.get(mTargyak.size()-1).mKod = link.text();
				}
			}
			
		}
		
	}
	
	public Targy parseTargy(Targy targy) throws Exception {
		targyOldal = Jsoup.connect(targy.getURL()).get();
		Elements tmp = targyOldal.getAllElements();
		Elements datas = targyOldal.select("#main > table > tbody");
		Elements targyAdatok = datas.select("tr:gt(0) > td");
		if(targyAdatok.size() == 5) {
			targy.mSemester = targyAdatok.get(1).text();
			targy.mKovKod = targyAdatok.get(2).text();
			targy.mKredit = targyAdatok.get(3).text();
			targy.mFelev = targyAdatok.get(4).text();
		}
		else {
			throw new Exception("T�rgy adatai nem stimmelnek a r�szletes aloldalon!");
		}
		
		return targy;
	}

}
