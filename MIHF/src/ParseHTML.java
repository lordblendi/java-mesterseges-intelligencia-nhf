
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseHTML {
	private Document doc;
	public List<String> mTargyak = new ArrayList<String>();
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
			if(num % 2 == 0){
				mTargyak.add(link.text());
			}
		}
	}

}
