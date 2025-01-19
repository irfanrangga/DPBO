/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import java.util.Scanner;
import Book.*;

/**
 *
 * @author Irfan Rangga
 */
public class Donatur extends User {

    public Donatur(int id, String nama, String email, String alamat, String password, String kontak, String role) {
        super(id, nama, email, alamat, password, kontak, role);
    }
    
    public void donasiBuku(){
        Scanner scanner = new Scanner(System.in);
        IBookService bookService = new BookService();
        // Ajukan buku donasi
                System.out.println("\n=== Ajukan Buku Donasi ===");
                System.out.print("Judul: ");
                String judul = scanner.nextLine();

                System.out.print("Penerbit: ");
                String penerbit = scanner.nextLine();

                System.out.print("Genre: ");
                String genre = scanner.nextLine();

                System.out.print("Penulis: ");
                String penulis = scanner.nextLine();

                System.out.print("Kategori: ");
                String kategori = scanner.nextLine();

                System.out.print("Kondisi (Baru/Bekas/Rusak): ");
                String kondisi = scanner.nextLine();

                System.out.print("Jumlah Buku: ");
                int jumlahBuku = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Book book = new Book(judul, penerbit, genre, penulis, kategori, kondisi, jumlahBuku, super.getId(),false);
                boolean success = bookService.addBook(book);

                if (success) {
                    System.out.println("Buku berhasil diajukan untuk donasi!");
                } else {
                    System.out.println("Gagal mengajukan buku.");
                }
    }

    @Override
    public String toString() {
        return "Data Donatur {" +
                "Nama: " + super.getNama() + 
                "\nEmail: " + super.getEmail() +
                "\nAlamat: " + super.getAlamat() +
                "\nNo.Hp: " + super.getKontak() +
                '}';
    }
 
    
}
