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
        System.out.println("Thanks..1 moment please...");

        String google = "http://www.google.com/search?q=";
        String charset = "UTF-8";
        List<String> links = new ArrayList<String>();

        Document query_results = Jsoup.connect(google + URLEncoder.encode(query, charset)).get();
        Elements results = query_results.select( "div.yuRUbf > a");
        for (Element result : results) {
            String linkHref = result.attr("href");
            links.add(linkHref);
        }
        for (String site : links){
            System.out.println(site);
            Document page = Jsoup.connect(site).get();
            System.out.println(page.title());
        }

    }


}







