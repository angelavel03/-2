package das.hw2.service;

import das.hw2.model.Stock;

import java.io.IOException;
import java.util.*;

public interface StockService {
    List<Stock> listAll();
    List<Stock> getStocksByIssuer(String name);
    void scrapeAndSaveStocks() throws IOException, InterruptedException;
}