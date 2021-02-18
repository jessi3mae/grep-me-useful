import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SearchDoc {

    Document doc;
    String url;

    SearchDoc(String url){
        this.url = url;
    }

    public void retrieveDoc() throws IOException {
        try {
            doc = Jsoup.connect(url).get();
        }
        catch (IOException e) {
            //cannot reach site but no worries! Onto the next...
        }
    }

    public String getDocTitle(){
        return doc.title();
    }

    public Document getDoc(){
        return doc;
    }

    public int findKeywordCount(String keyword){
        Elements p_refs = doc.select("p:contains(" + keyword + ")");
        return p_refs.size();
    }


}
