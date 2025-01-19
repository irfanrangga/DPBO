/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;
import java.util.Scanner;
import java.util.List;
import Book.*;

/**
 *
 * @author Irfan Rangga
 */
public class Volunteer extends User {

    public Volunteer(int id, String nama, String email, String alamat, String password, String kontak, String role) {
        super(id, nama, email, alamat, password, kontak, role);
    }

    public boolean verifikasiBuku(BookService bookService) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan ID Donatur yang ingin divalidasi: ");
        int donaturID = sc.nextInt();
        List<Book> listbook = bookService.listBuku(donaturID);
        //Volunteer bakal ngecek buku yang ada di list
        if (listbook.isEmpty()) {
            System.out.println("Tidak ada buku yang harus disalurkan");
            return false; //mengembalikan nilai false jika tidak ada buku
        } else {
            System.out.println("Daftar buku dari donatur:");
            for (Book book : listbook) {
                System.out.println("ID: " + book.getIdBuku()
                        + ", Judul: " + book.getJudul()
                        + ", Penulis: " + book.getPenulis()
                        + ", Penerbit: " + book.getPenerbit()
                        + ", Kondisi: " + book.getKondisi());
            }
            System.out.print("Pilih ID Buku yang ingin diverifikasi:");
            int bookID = sc.nextInt();
            Book selectedBook = null;
            for (Book book : listbook) {
                if (book.getIdBuku() == bookID) {
                    selectedBook = book;
                    break;
                }
            }
            if (selectedBook != null) {
                String kondisi = selectedBook.getKondisi();
                if (kondisi.equalsIgnoreCase("Baru") || kondisi.equalsIgnoreCase("Bekas")) {
                    System.out.println("Buku berhasil divalidasi");
                    return true;
                } else if (kondisi.equalsIgnoreCase("Rusak")) {
                    System.out.println("Buku tidak dapat didonasikan");
                    return false;
                } else {
                    System.out.println("Masukkan kondisi yang valid!");
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Data Volunteer{"
                + "\nUser ID:\t\t" + getId()
                + "\nNama Volunteer:\t" + getNama()
                + "\nEmail:\t\t" + getEmail()
                + "\nAlaamt:\t\t" + getAlamat()
                + "\nNo. HP:\t\t" + getKontak()
                + '}';
    }
}
