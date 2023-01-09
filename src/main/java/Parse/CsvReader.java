package Parse;

import Parameters.Area;
import Parameters.Subregions;
import Sqlite.Conn;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.FileReader;
import java.util.List;

public class CsvReader {

    public static void main(String[] args) throws Exception
    {
        CsvToBean csv = new CsvToBean();
        Conn conn = new Conn();
        conn.Delete();
        String csvFilename = "src/main/resources/Country.csv";
        CSVReader csvReader1 = new CSVReader(new FileReader(csvFilename));
        List list1 = csv.parse(setColumMapping1(), csvReader1);
        for (Object object : list1) {
            Area area = (Area) object;
            try {
                String a1 = area.getInternet_users().replaceAll(",","");
                String a2 = area.getPopulation().replaceAll(",","");
                conn.insertForA(area.getCountry_or_area(),area.getSubregion(),
                        Integer.parseInt(a1), Integer.parseInt(a2));
            } catch (Exception e) {
            }
        }

        CSVReader csvReader2 = new CSVReader(new FileReader(csvFilename));
        List list2 = csv.parse(setColumMapping2(), csvReader2);
        for (Object object : list2) {
            Subregions subregions = (Subregions) object;
            conn.insertForS(subregions.getSubregion(),subregions.getRegion());
        }
    }

    private static ColumnPositionMappingStrategy setColumMapping1()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Area.class);
        String[] columns = new String[] {"country_or_area","subregion", "region","internet_users","population"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
    private static ColumnPositionMappingStrategy setColumMapping2()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Subregions.class);
        String[] columns = new String[] {"country_or_area","subregion", "region","internet_users", "population"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
