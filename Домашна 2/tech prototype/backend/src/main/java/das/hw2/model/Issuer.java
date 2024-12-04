package das.hw2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "issuer")
public class Issuer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issuer_id", unique = true, nullable = false)
    private Long issuerId;
    @Column(name = "name", unique = true)
    private String name;
//    @OneToMany(mappedBy = "issuer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Stock> stocks;

    public Issuer(String name) {
        this.name = name;
    }
}
