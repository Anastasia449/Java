package Sqlite;

import org.apache.commons.lang3.ArrayUtils;

import java.sql.*;
public class Conn {
    public static Statement statmt;
    public static ResultSet resSet;
    private Connection connect() {
        // SQLite's connection string
        String url = "jdbc:sqlite:D:/Nastya/Java/sqliteadmin/Country.s3db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertForA(String country_or_area, String subregion, int internet_users, int population) {
        String sql = "INSERT INTO Area (country_or_area,subregion,internet_users,population) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, country_or_area);
            pstmt.setString(2, subregion);
            pstmt.setString(3, String.valueOf(internet_users));
            pstmt.setString(4, String.valueOf(population));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertForS(String subregion, String region) {
        String sql = "INSERT INTO Subregions (subregion,region) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, subregion);
            pstmt.setString(2, region);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Delete(){
        String sql = "DELETE FROM Area;";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void Task1()throws SQLException {
        String sql = "SELECT subregion FROM Subregions";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT subregion FROM Subregions");
            String[] sub = new String[22];
            int i = 0;
            while(resSet.next()){
                sub[i] = resSet.getString("subregion");
                i += 1;
            }
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT subregion,internet_users FROM Area");
            long[] users = new long[22];
            i = 0;
            while(resSet.next()){
                i = ArrayUtils.indexOf(sub, resSet.getString("subregion"));
                users[i] += resSet.getInt("internet_users");
            }
            for (i = 0; i < 22; i++){
                System.out.println("Название субрегиона: " + sub[i] + "; Кол-во пользователей: " + Long.toString(users[i]));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Task2() throws SQLException {
        String sql = "SELECT subregion,internet_users FROM Area WHERE subregion = \"Eastern Europe\" ORDER BY internet_users";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT subregion,internet_users FROM Area WHERE subregion = \"Eastern Europe\" ORDER BY internet_users");
            String name = resSet.getString("subregion");
            String num = resSet.getString("internet_users");
            System.out.println("Название субрегиона: " + name);
            System.out.println("Наименьшее кол-во зарегистрированных в интернете пользователей = " + num);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Task3() throws SQLException {
        String sql = "SELECT country_or_area,internet_users,population FROM Area";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT country_or_area,internet_users,population FROM Area");
            while(resSet.next()){
                String coa = resSet.getString("country_or_area");
                double inter_u = resSet.getDouble("internet_users");
                double pop = resSet.getDouble("population");
                if ((inter_u / pop) * 100 >= 75 && (inter_u / pop) * 100 <= 85){
                    double a = (inter_u / pop) * 100;
                    String result = String.format("%.2f",a);
                    System.out.println(coa + " = " + result + "%");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

