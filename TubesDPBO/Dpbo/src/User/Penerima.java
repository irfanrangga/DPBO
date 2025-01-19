/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;
import Book.*;
import java.util.*;

/**
 *
 * @author Irfan Rangga
 */
public class Penerima extends User{
    private String ulasan;
    private int nilai;

    public Penerima(int id, String nama, String email, String alamat, String password, String kontak, String role) {
        super(id, nama, email, alamat, password, kontak, role);
    }
    
    public void lihatSemuaBuku(BookService bookService) {
        System.out.println("=== Daftar Buku yang Tersedia ===");
        List<Book> books = bookService.listBuku(0); // 0 untuk mengambil semua buku
        System.out.println("=================================");
        if (books.isEmpty()) {
            System.out.println("Tidak ada buku yang tersedia saat ini.");
        } else {
            for (Book book : books) {
                System.out.println("ID: " + book.getIdBuku() +
                                   ", Judul: " + book.getJudul() +
                                   ", Penulis: " + book.getPenulis() +
                                   ", Penerbit: " + book.getPenerbit() +
                                   ", Kondisi: " + book.getKondisi());
            }
        }
    }
    
    public void cariBuku(BookService bookService) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan judul buku yang ingin dicari: ");
        String judulBuku = sc.nextLine();

        System.out.println("=== Hasil Pencarian ===");
        HashMap<String, Book> books = bookService.getAllBooksByDonatur(0); // Ambil semua buku
        boolean found = false;
        Book buku = books.get(judulBuku);
        System.out.println("Buku dengan judul \"" + judulBuku + "\" ditemukan.");
        if (buku == null) {
            System.out.println("Buku dengan judul \"" + judulBuku + "\" tidak ditemukan.");
        }
    }
    
    public void pesanBuku(BookService bookService) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan ID buku yang ingin dipesan: ");
        int idBuku = sc.nextInt();
        sc.nextLine(); // Consume newline

        Book book = bookService.getBookById(idBuku);
        if (book != null) {
            System.out.println("=== Invoice ===");
            System.out.println("User ID: " + getId());
            System.out.println("Nama Pemesan: " + getNama());
            System.out.println("Judul Buku: " + book.getJudul());
            System.out.println("Penulis: " + book.getPenulis());
            System.out.println("Penerbit: " + book.getPenerbit());
            System.out.println("=================================");
            System.out.println("Buku berhasil dipesan.");
        } else {
            System.out.println("Buku dengan ID " + idBuku + " tidak ditemukan.");
        }
    }
 public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
      
    public String getUlasan() {
        return ulasan;
    }

    public int getNilai() {
        return nilai;
    }
    
   

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }
  
    public void beriUlasan(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bagaimana pendapatmu tentang buku ini?");
        String review = sc.nextLine();
        if(review == null){
            System.out.println("Masukkan minimal 3 kata!");
            return;
        }
        setUlasan(review);
        System.out.println("Berikan penilaianmu disini");
        
        int rating = sc.nextInt();
        setNilai(rating);
        System.out.println("Terima Kasih sudah memberikan ulasan");
    }

    @Override
    public String toString() {
        return "Data Penerima{" +
                "\nUser ID:\t\t" + getId() +
                "\nNama Penerima:\t" + getNama() +
                "\nEmail:\t\t" + getEmail() +
                "\nAlaamt:\t\t" + getAlamat() +
                "\nNo. HP:\t\t" + getKontak() +
                '}';
    }
    
    
}
