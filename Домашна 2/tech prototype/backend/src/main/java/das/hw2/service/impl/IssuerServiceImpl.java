package das.hw2.service.impl;

import das.hw2.model.Issuer;
import das.hw2.repository.IssuerRepository;
import das.hw2.service.IssuerService;
import org.springframework.stereotype.Service;
import scraper.NamesScraper;

import java.io.IOException;
import java.util.List;

@Service
public class IssuerServiceImpl implements IssuerService {
    private final IssuerRepository issuerRepository;
    private final NamesScraper namesScraper;

    public IssuerServiceImpl(IssuerRepository issuerRepository, NamesScraper namesScraper) {
        this.issuerRepository = issuerRepository;
        this.namesScraper = namesScraper;
    }

    @Override
    public List<String> fetchAllNames() throws IOException {
        return namesScraper.fetchNames("https://www.mse.mk/en/stats/symbolhistory/mpt");
    }

    @Override
    public void storeAllNames(List<String> names) {
        names.forEach(name -> issuerRepository.save(new Issuer(name)));
    }
}
