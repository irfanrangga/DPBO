package book_donation;

import Book.BookService;
import Book.Book;
import java.util.Scanner;
import java.util.List;

public class VolunteerDashboard {

    public static void volunteerMenu() {
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookService();

        while (true) {
            System.out.println("\n=== Dashboard Volunteer ===");
            System.out.println("1. Lihat Buku yang Belum Diverifikasi");
            System.out.println("2. Verifikasi Buku");
            System.out.println("3. Buat laporan");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if (pilihan == 1) {
                // Tampilkan buku yang belum diverifikasi
                List<Book> unverifiedBooks = bookService.getUnverifiedBooks();
                if (unverifiedBooks.isEmpty()) {
                    System.out.println("Tidak ada buku yang perlu diverifikasi.");
                } else {
                    System.out.println("\n=== Buku yang Belum Diverifikasi ===");
                    for (Book book : unverifiedBooks) {
                        System.out.println("ID: " + book.getIdBuku());
                        System.out.println("Judul: " + book.getJudul());
                        System.out.println("Donatur ID: " + book.getDonaturId());
                        System.out.println("--------------------------------");
                    }
                }
            } else if (pilihan == 2) {
                // Verifikasi buku
                System.out.print("Masukkan ID buku yang akan diverifikasi: ");
                int bookId = scanner.nextInt();
                scanner.nextLine();

                if (bookService.verifyBook(bookId)) {
                    System.out.println("Buku berhasil diverifikasi.");
                } else {
                    System.out.println("Gagal memverifikasi buku. Pastikan ID buku valid.");
                }
            } else if (pilihan == 3) {
                break;
            } else {
                System.out.println("Opsi tidak valid.");
            }
        }
    }
}
