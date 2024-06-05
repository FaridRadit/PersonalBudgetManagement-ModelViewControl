package mvcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ModelContact {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/student_db7?useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "";

    Connection koneksi;
    Statement statement;

    public ModelContact() {
        try {
            Class.forName(JDBC_DRIVER);
            koneksi = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = koneksi.createStatement();
            System.out.println("Koneksi Berhasil");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection failed: " + e.getMessage());
        }
    }

    public String[][] readContact() {
        try {
            int jmlData = 0;
            String data[][] = new String[10][4];
            String query = "SELECT * FROM contact";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data[jmlData][0] = resultSet.getString("Nama");
                data[jmlData][1] = resultSet.getString("NoHp");
                data[jmlData][2] = resultSet.getString("Umur");
                data[jmlData][3] = resultSet.getString("Email");
                jmlData++;
            }
            return data;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }

    public String[][] cariContact(String nama) {
        try {
            int jmlData = 0;
            String query = "SELECT * FROM contact WHERE Nama LIKE '%" + nama + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }

            String data[][] = new String[jmlData][4];

            if (jmlData == 0) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
            } else {
                jmlData = 0;
                query = "SELECT * FROM contact WHERE Nama LIKE '%" + nama + "%'";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    data[jmlData][0] = resultSet.getString("Nama");
                    data[jmlData][1] = resultSet.getString("NoHp");
                    data[jmlData][2] = resultSet.getString("Umur");
                    data[jmlData][3] = resultSet.getString("Email");
                    jmlData++;
                }
            }
            return data;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }

    public void insertData(String Nama, String NoHp, String Umur, String Email) {
        int jmlData = 0;
        try {
            String query = "SELECT * FROM contact WHERE NoHp='" + NoHp + "'";
            System.out.println(Nama + " " + NoHp + " " + Umur + " " + Email);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                jmlData++;
            }

            if (jmlData == 0) {
                query = "INSERT INTO contact VALUES('" + Nama + "','" + NoHp + "','" + Umur + "','" + Email + "')";
                statement.executeUpdate(query);
                System.out.println("Berhasil ditambahkan");
                JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
            } else {
                JOptionPane.showMessageDialog(null, "Data sudah ada");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
    }

    public void deleteData(String Nama) {
        try {
            String query = "DELETE FROM contact WHERE Nama='" + Nama + "'";
            statement.executeUpdate(query);
            System.out.println("Delete Success");
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getMessage());
        }
    }

    public void updateData(String Nama, String NoHp, String Umur, String Email) {
        try {
            String query = "UPDATE contact SET NoHp = '" + NoHp + "', Umur = '" + Umur + "', Email = '" + Email + "' WHERE Nama = '" + Nama + "'";
            statement.executeUpdate(query);
            System.out.println("Data berhasil diperbarui");
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
        } catch (SQLException e) {
            System.out.println("Update Data Gagal: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Update Data Gagal: " + e.getMessage());
        }
    }
}
