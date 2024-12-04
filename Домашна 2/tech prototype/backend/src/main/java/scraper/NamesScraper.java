package scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class NamesScraper {
    private final List<String> issuerNames;

    public NamesScraper() {
        issuerNames = new ArrayList<>();
    }

    public List<String> fetchNames(String url) throws IOException {
        Document document = Jsoup.connect(url + "MPT").get();
        Elements issuers = document.select("select#Code > option");

        for (Element issuer : issuers) {
            String issuerName = issuer.text();
            if (!issuerContainsDigit(issuerName)) {
                issuerNames.add(issuerName);
            }
        }

        return issuerNames;
    }

    private boolean issuerContainsDigit(String issuerName) {
        for (int i = 0; i < issuerName.length(); i++) {
            if (Character.isDigit(issuerName.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
