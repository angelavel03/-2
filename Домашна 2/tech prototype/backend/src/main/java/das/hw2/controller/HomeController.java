package das.hw2.controller;

import das.hw2.service.IssuerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.io.IOException;

@RestController
@RequestMapping(value = "/")
@Validated
@CrossOrigin(origins="*")
public class HomeController {
    private final IssuerService issuerService;

    public HomeController(IssuerService issuerService) {

        this.issuerService = issuerService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllNames() throws IOException {
        return new ResponseEntity<>(issuerService.fetchAllNames(), HttpStatus.OK);
    }
}
