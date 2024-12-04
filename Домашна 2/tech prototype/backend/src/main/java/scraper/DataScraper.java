package scraper;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataScraper {
    private final String url;

    public DataScraper() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/das/hw2/service/impl/chromedriver.exe");
        this.url = "https://www.mse.mk/en/stats/symbolhistory/";
    }

    public List<String> getTenYearsData(WebDriver driver, String name) {
       List<String> data = new ArrayList<>();
        for (int i = 2024; i >= 2014; i--) {
            WebElement startDateField = driver.findElement(By.id("FromDate"));
            startDateField.clear();
            startDateField.sendKeys("1/1/" + i);

            WebElement endDateField = driver.findElement(By.id("ToDate"));
            endDateField.clear();
            endDateField.sendKeys("12/31/" + i);

            WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Find']"));
            submitButton.click();

            String html = driver.getPageSource();
            Document document = Jsoup.parse(html);
            Elements rows = document.select("table#resultsTable tbody tr");

            for (Element row : rows) {
                String[] parts = row.text().split("\\s");
                String formatedLine = String.join(";", parts).replaceAll("\\s", "null");
                data.add(String.format("%s;%s", name, formatedLine));
            }
        }
        return data;
    }
}