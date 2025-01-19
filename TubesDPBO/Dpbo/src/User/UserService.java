package User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Naufal
 */
import java.sql.*;

public class UserService implements IUserService{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/book_donation";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yourpassword";

    private Connection connection;

    public UserService() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Gagal terhubung ke database: " + e.getMessage());
        }
    }

    // Registrasi pengguna baru
    @Override
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (nama, email, alamat, password, kontak, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getNama());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getAlamat());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getKontak());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal registrasi: " + e.getMessage());
            return false;
        }
    }

    // Login pengguna
    @Override
   public User login(String email, String password) {
    String query = "SELECT * FROM users WHERE email = ? AND password = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            //buat kondisi per role
            String role = resultSet.getString("role");
            switch(role.toLowerCase()){
                case "donatur":
                    return new Donatur(
                            resultSet.getInt("id"),
                            resultSet.getString("nama"),
                            resultSet.getString("email"),
                            resultSet.getString("alamat"),
                            resultSet.getString("password"),
                            resultSet.getString("kontak"),
                            role
                    );
                case "penerima":
                    return new Penerima(
                            resultSet.getInt("id"),
                            resultSet.getString("nama"),
                            resultSet.getString("email"),
                            resultSet.getString("alamat"),
                            resultSet.getString("password"),
                            resultSet.getString("kontak"),
                            role
                    );
                case "volunteer":
                    return new Volunteer(
                            resultSet.getInt("id"),
                            resultSet.getString("nama"),
                            resultSet.getString("email"),
                            resultSet.getString("alamat"),
                            resultSet.getString("password"),
                            resultSet.getString("kontak"),
                            role
                    );
                default:
                    System.out.println("Role tidak ditemukan");
                    return null;
            }
        }
    } catch (SQLException e) {
        System.out.println("Gagal login: " + e.getMessage());
    }
    return null;
}
   
   
}

