package das.hw2.controller;

import das.hw2.model.Stock;
import das.hw2.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@Validated
@CrossOrigin(origins="*")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

//    @PostMapping("/api/stocks")
//    public ResponseEntity<String> fetchAllStocks() throws IOException, InterruptedException {
//        return ResponseEntity.ok("Stock data fetched successfully");
//    }

    @GetMapping("/api/{name}")
    public ResponseEntity<List<Stock>> getStock(@PathVariable(value = "name") String name) {
        return new ResponseEntity<>(stockService.getStocksByIssuer(name), HttpStatus.OK);
    }
}