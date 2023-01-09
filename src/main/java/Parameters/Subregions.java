package Parameters;

import com.opencsv.bean.CsvBindByPosition;
import javax.persistence.*;

@Entity
@Table(name = "Subregions")
public class Subregions {
    @Id
    @CsvBindByPosition(position = 1)
    @Column(name = "subregion")
    private String subregion;

    @CsvBindByPosition(position = 2)
    @Column(name = "region")
    private String region;

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
