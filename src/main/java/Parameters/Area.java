package Parameters;

import com.opencsv.bean.CsvBindByPosition;
import javax.persistence.*;

@Entity
@Table(name = "Area")
public class Area {

    @CsvBindByPosition(position = 0)
    @Column(name = "country_or_area")
    @Id
    private String country_or_area;

    @CsvBindByPosition(position = 1)
    @Column(name = "subregion")
    private String subregion;

    @CsvBindByPosition(position = 3)
    @Column(name = "internet_users")
    private String internet_users;

    @CsvBindByPosition(position = 4)
    @Column(name = "population")
    private String population;

    public String getCountry_or_area() {
        return country_or_area;
    }

    public void setCountry_or_area(String country_or_area) {
        this.country_or_area = country_or_area;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getInternet_users() {
        return internet_users;
    }

    public void setInternet_users(String internet_users) {
        this.internet_users = internet_users;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

}
