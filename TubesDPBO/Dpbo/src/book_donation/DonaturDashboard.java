
package book_donation;

import Book.Book;
import Book.IBookService;
import Book.BookService;
import java.util.List;
import java.util.Scanner;
import User.Donatur;

public class DonaturDashboard {

    public static void donaturMenu(int donaturId) {
        Scanner scanner = new Scanner(System.in);
        IBookService bookService = new BookService(); // Gunakan interface

        while (true) {
            System.out.println("\n=== Halaman Utama Donatur ===");
            System.out.println("1. Ajukan Buku Donasi");
            System.out.println("2. Hapus Buku Donasi");
            System.out.println("3. Lihat Semua Buku Donasi");
            System.out.println("4. Logout");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if (pilihan == 1) {
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

                Book book = new Book(judul, penerbit, genre, penulis, kategori, kondisi, jumlahBuku, donaturId,false);
                boolean success = bookService.addBook(book);

                if (success) {
                    System.out.println("Buku berhasil diajukan untuk donasi!");
                } else {
                    System.out.println("Gagal mengajukan buku.");
                }

            } else if (pilihan == 2) {
                // Hapus buku donasi
                System.out.println("\n=== Hapus Buku Donasi ===");
                System.out.print("Masukkan ID buku yang ingin dihapus: ");
                int bookId = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                boolean success = bookService.deleteBook(bookId, donaturId);

                if (success) {
                    System.out.println("Buku dengan ID " + bookId + " berhasil dihapus.");
                } else {
                    System.out.println("Gagal menghapus buku. Pastikan ID benar dan buku adalah milik Anda.");
                }
            } else if (pilihan == 3) {
                System.out.println("\n=== Daftar Buku Donasi ===");
                List<Book> books = bookService.listBuku(donaturId);
                if (books.isEmpty()) {
                    System.out.println("Anda belum mendonasikan buku.");
                } else {
                    for (Book book : books) {

                        System.out.println("-----------------------------------");
                        System.out.println("ID Buku: " + book.getIdBuku());
                        System.out.println("Judul: " + book.getJudul());
                        System.out.println("Penerbit: " + book.getPenerbit());
                        System.out.println("Genre: " + book.getGenre());
                        System.out.println("Penulis: " + book.getPenulis());
                        System.out.println("Kategori: " + book.getKategori());
                        System.out.println("Kondisi: " + book.getKondisi());
                        System.out.println("Jumlah Buku: " + book.getJumlahBuku());
                        System.out.println("---------------------------");
                    }
                }
            } else if (pilihan == 4) {
                System.out.println("Logout berhasil. Kembali ke menu utama.");
                break;
            } else {
                System.out.println("Opsi tidak valid.");
            }
        }
    }
}
