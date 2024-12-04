package das.hw2.service.impl;

import das.hw2.model.Stock;
import das.hw2.repository.StockRepository;
import das.hw2.service.IssuerService;
import das.hw2.service.StockService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import scraper.DataScraper;
import scraper.NamesScraper;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    // Repositories
    private final StockRepository stockRepository;
    private final DataScraper dataScraper;
    // Services
    private final NamesScraper namesScraper;

    public StockServiceImpl(StockRepository stockRepository, DataScraper dataScraper, NamesScraper namesScraper) {
        this.stockRepository = stockRepository;
        this.dataScraper = dataScraper;
        this.namesScraper = namesScraper;
    }

    @Override
    public List<Stock> listAll() {
        return stockRepository.findAll();
    }

    @Override
    public List<Stock> getStocksByIssuer(String name) {
        return stockRepository.findAllByName(name);
    }

    @Override
    public void scrapeAndSaveStocks() throws IOException {
        WebDriver driver = new ChromeDriver();
        List<String> issuerNames = namesScraper.fetchNames("https://www.mse.mk/en/stats/symbolhistory/mpt");

        for (String issuerName : issuerNames) {
            driver.get(dataScraper.getUrl() + issuerName);
            List<String> issuersData = dataScraper.getTenYearsData(driver, issuerName);

            issuersData.forEach(line -> {
                try {
                    Stock stock = Stock.create(line);
                    stockRepository.save(stock);
                } catch (ParseException e) {
                    e.printStackTrace(System.out);
                }
            });
        }

        driver.quit();
    }
}
