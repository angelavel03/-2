package das.hw2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.datetime.DateFormatter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "issuers")
@NoArgsConstructor
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private String date;

    @Column(name = "last_trade_price")
    private String lastTradePrice;

    @Column(name = "max")
    private String max;

    @Column(name = "min")
    private String min;

    @Column(name = "avg_price")
    private String avgPrice;

    @Column(name = "chg")
    private String chg;

    @Column(name = "volume")
    private String volume;

    @Column(name = "turnover_best_denars")
    private String turnoverBestDenars;

    @Column(name = "total_turnover_denars")
    private String totalTurnoverDenars;

    public Stock(String issuer, String date, String lastTradePrice, String max, String min, String avgPrice, String chg, String volume, String turnoverBestDenars, String totalTurnoverDenars) {
        this.name = issuer;
        this.date = date;
        this.lastTradePrice = lastTradePrice;
        this.max = max;
        this.min = min;
        this.avgPrice = avgPrice;
        this.chg = chg;
        this.volume = volume;
        this.turnoverBestDenars = turnoverBestDenars;
        this.totalTurnoverDenars = totalTurnoverDenars;
    }

    public static Stock create(String line) throws ParseException {
        // Example line: 11/29/2024 1,650.00 1,650.00 0.00 0 0 0
        String[] parts = line.split(";");
        String[] attr = new String[11];

        for (int i = 0; i < 10; i++) {
            if(i >= parts.length) {
                attr[i] = "0";
            } else {
                attr[i] = parts[i];
            }
        }

        return new Stock(attr[0], attr[1], attr[2], attr[3], attr[4], attr[5], attr[6], attr[7], attr[8], attr[9]);
    }
}