import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;


public class SiteAPI {
    private String a,b;
    private String URL = "https://api.meteostat.net/v1/history/daily?station=27612&start="+a.toString()+"&end="+b.toString()+"&key=L23FNnUI";;
    private Document doc;
    private final Currency curr;


    SiteAPI()throws IOException{
        curr = new Currency();
    }
    public void setPeriod(String a, String b) throws IOException {
        this.a = a;
        this.b = b;
        doc = Jsoup.connect(URL).get();
    }

    public void initCurr() throws Exception{
        Elements elements = doc.select("td");
        for(Element a: elements){
            if(a.text().matches("\\d*\\.\\d*\\.\\d*")) {
                String tmp = a.text().replaceAll("\\.","-");
                curr.addDate(tmp);
            }
            else if(a.text().matches("\\d*,\\d*")) {
                curr.addValue(Double.parseDouble(a.text().replaceAll(",", ".")));
            }
            else{
                continue;
            }
        }
    }

    public Currency getCurr() {
        return curr;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }
}


