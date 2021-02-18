import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GrepMe {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("What am I looking for please? : ");
        String query = scanner.nextLine();
        System.out.println("Deep Dive (0) or quick search (1) ? :");
        int search_type = scanner.nextInt();
        scanner.close();
        System.out.println("Thanks..1 moment please...");

        String google = "http://www.google.com/search?q=";
        String charset = "UTF-8";
        List<String> links = new ArrayList<String>();
        int search_scope = 10;
        int revelancy = 4;
        if (search_type == 0){
            search_scope = 100;
            revelancy = 20;
        }

        Document query_results = Jsoup.connect(google + URLEncoder.encode(query, charset) + "&num="+search_scope).get();
        Elements results = query_results.select( "div.yuRUbf > a");
        for (Element result : results) {
            String linkHref = result.attr("href");
            links.add(linkHref);
        }
        for (String site : links){
            SearchDoc page = new SearchDoc(site);
            page.retrieveDoc();
            if (page.getDoc() != null) {
                if (page.findKeywordCount(query) > revelancy) {
                    //Useful resource detected
                    System.out.println("TITLE: " + page.getDocTitle() + " - URL: " + site + " - References found: " + page.findKeywordCount(query) );
                }
            }
        }

         ///ADD GUI THAT RETURNS USEUL SITES IN VIEWABLE FORMAT
    }


}







