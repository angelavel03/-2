package das.hw2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import scraper.DataScraper;
import scraper.NamesScraper;

@Configuration
public class AppConfig {
    @Bean
    public NamesScraper namesScraper() {
        return new NamesScraper();
    }
    @Bean
    public DataScraper dataScraper() { return new DataScraper(); }
}