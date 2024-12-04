package das.hw2.service;

import das.hw2.model.Issuer;

import java.io.IOException;
import java.util.*;

public interface IssuerService {
    List<String> fetchAllNames() throws IOException;
    void storeAllNames(List<String> names);
}
