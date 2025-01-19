package main;

import Book.BookService;
import User.Penerima;
import User.User;
import User.UserService;
import User.Donatur;
import User.Volunteer;
import book_donation.DonaturDashboard;
import book_donation.PenerimaDashboard;
import book_donation.VolunteerDashboard;
import java.util.Scanner;

public class Main {

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Tidak dapat membersihkan layar: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        while (true) {

            System.out.println("=== Program Donasi Buku Bekas ===");
            System.out.println("1. Registrasi");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (pilihan == 1) {
                // Registrasi
                System.out.println("\n=== Registrasi ===");
                System.out.print("Nama: ");
                String nama = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Alamat: ");
                String alamat = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                System.out.print("Kontak: ");
                String kontak = scanner.nextLine();

                System.out.print("Role (penerima/donatur/volunteer): ");
                String role = scanner.nextLine();

                User user = new User(nama, email, alamat, password, kontak, role) {
                };
                boolean success = userService.registerUser(user);

                if (success) {
                    System.out.println("Registrasi berhasil!");
                } else {
                    System.out.println("Registrasi gagal.");
                }
            } else if (pilihan == 2) {
                // Login
                System.out.println("\n=== Login ===");
                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                User user = userService.login(email, password);
                if (user != null) {
                    clearConsole();
                    System.out.println("\nLogin berhasil!");
                    System.out.println("Halo, " + user.getNama() + " (" + user.getRole() + ").");

                    // Arahkan ke dashboard sesuai role
                    if (user instanceof Donatur donatur) {
                        // Mengarahkan ke Donatur Dashboard dengan ID donatur yang benar
                        DonaturDashboard.donaturMenu(donatur.getId());
                    } else if (user instanceof Penerima penerima) {
                        // Mengarahkan ke Penerima Dashboard
                        PenerimaDashboard.penerimaMenu(penerima);
                    } else if (user instanceof Volunteer) {
                        // Mengarahkan ke Volunteer Dashboard
                        VolunteerDashboard.volunteerMenu();
                    }
                } else {
                    System.out.println("\nLogin gagal. Email atau password salah.");
                }
            } else if (pilihan == 3) {
                System.out.println("Terima kasih telah menggunakan program ini. Sampai jumpa!");
                break;
            } else {
                System.out.println("Opsi tidak valid.");
            }
        }

        scanner.close();
    }
}