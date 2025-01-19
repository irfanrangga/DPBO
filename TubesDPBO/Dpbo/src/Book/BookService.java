package Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class BookService implements IBookService {

    private Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/book_donation", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addBook(Book book) {
        System.out.println("Memulai proses pengajuan buku oleh donatur ID: " + book.getDonaturId()); // Log ID donatur
        if (!isDonaturValid(book.getDonaturId())) {
            System.out.println("Donatur dengan ID " + book.getDonaturId() + " tidak ditemukan."); // Log jika donatur tidak ditemukan
            return false;
        }

        String sql = "INSERT INTO books (judul, penerbit, genre, penulis, kategori, kondisi, jumlah_buku, donatur_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getJudul());
            stmt.setString(2, book.getPenerbit());
            stmt.setString(3, book.getGenre());
            stmt.setString(4, book.getPenulis());
            stmt.setString(5, book.getKategori());
            stmt.setString(6, book.getKondisi());
            stmt.setInt(7, book.getJumlahBuku());
            stmt.setInt(8, book.getDonaturId());
            stmt.executeUpdate();
            System.out.println("Buku berhasil diajukan untuk donasi."); // Log sukses pengajuan
            return true;
        } catch (Exception e) {
            System.out.println("Gagal mengajukan buku: " + e.getMessage()); // Log error
            return false;
        }
    }

    @Override
    public boolean isDonaturValid(int donaturId) {
        System.out.println("Memeriksa validasi donatur dengan ID: " + donaturId); // Log ID yang diperiksa
        String sql = "SELECT COUNT(*) FROM users WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, donaturId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Donatur dengan ID " + donaturId + " valid."); // Log valid
                return true;
            } else {
                System.out.println("Donatur dengan ID " + donaturId + " tidak ditemukan."); // Log tidak valid
            }
        } catch (Exception e) {
            System.out.println("Error validasi donatur: " + e.getMessage()); // Log error
        }
        return false;
    }

    @Override
    public boolean deleteBook(int bookId, int donaturId) {
        // Validasi apakah buku dimiliki oleh donatur yang sedang login
        if (!isBookOwnedByDonatur(bookId, donaturId)) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan atau bukan milik donatur dengan ID " + donaturId);
            return false;
        }

        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
            System.out.println("Buku dengan ID " + bookId + " berhasil dihapus.");
            return true;
        } catch (Exception e) {
            System.out.println("Gagal menghapus buku: " + e.getMessage());
            return false;
        }
    }

    private boolean isBookOwnedByDonatur(int bookId, int donaturId) {
        String sql = "SELECT COUNT(*) FROM books WHERE id = ? AND donatur_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.setInt(2, donaturId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Buku dengan ID " + bookId + " ditemukan untuk donatur ID " + donaturId);
                return true;
            } else {
                System.out.println("Buku dengan ID " + bookId + " tidak ditemukan atau bukan milik donatur ID " + donaturId);
            }
        } catch (Exception e) {
            System.out.println("Error validasi kepemilikan buku: " + e.getMessage());
        }
        return false;
    }

    
    //fitur penerima
    @Override
    public HashMap<String, Book> getAllBooksByDonatur(int donaturId) {
        String sql = donaturId == 0 ? "SELECT * FROM books" : "SELECT * FROM books WHERE donatur_id = ?";
        HashMap<String, Book> books = new HashMap<String, Book>();

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (donaturId != 0) {
                stmt.setInt(1, donaturId);
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("judul"),
                        rs.getString("penerbit"),
                        rs.getString("genre"),
                        rs.getString("penulis"),
                        rs.getString("kategori"),
                        rs.getString("kondisi"),
                        rs.getInt("jumlah_buku"),
                        rs.getInt("donatur_id"),
                        rs.getBoolean("is_verified")
                );
                book.setIdBuku(rs.getInt("id")); // Mengisi ID buku
                books.put(book.getJudul(), book);
            }
        } catch (Exception e) {
            System.out.println("Gagal mengambil daftar buku: " + e.getMessage());
        }

        return books;
    }

  @Override
public List<Book> listBuku(int donaturId) {
    String sql = donaturId == 0 
                 ? "SELECT * FROM books WHERE is_verified = TRUE" 
                 : "SELECT * FROM books WHERE donatur_id = ? AND is_verified = TRUE";
    List<Book> books = new ArrayList<>();

    try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        if (donaturId != 0) {
            stmt.setInt(1, donaturId);
        }
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Book book = new Book(
                    rs.getString("judul"),
                    rs.getString("penerbit"),
                    rs.getString("genre"),
                    rs.getString("penulis"),
                    rs.getString("kategori"),
                    rs.getString("kondisi"),
                    rs.getInt("jumlah_buku"),
                    rs.getInt("donatur_id"),
                    rs.getBoolean("is_verified")
            );
            book.setIdBuku(rs.getInt("id")); // Mengisi ID buku
            books.add(book);
        }
    } catch (Exception e) {
        System.out.println("Gagal mengambil daftar buku: " + e.getMessage());
    }

    return books;
}

@Override
    //fitur penerima
    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getString("judul"),
                        rs.getString("penerbit"),
                        rs.getString("genre"),
                        rs.getString("penulis"),
                        rs.getString("kategori"),
                        rs.getString("kondisi"),
                        rs.getInt("jumlah_buku"),
                        rs.getInt("donatur_id"),
                        rs.getBoolean("is_verified")
                );
            }
        } catch (Exception e) {
            System.out.println("Gagal mengambil buku: " + e.getMessage());
        }
        return null;
    }

    
    @Override
public boolean verifyBook(int bookId) {
    String sql = "UPDATE books SET is_verified = TRUE WHERE id = ?";
    try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, bookId);
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (Exception e) {
        System.out.println("Gagal memverifikasi buku: " + e.getMessage());
        return false;
    }
}

@Override
public List<Book> getUnverifiedBooks() {
    String sql = "SELECT * FROM books WHERE is_verified = FALSE";
    List<Book> books = new ArrayList<>();
    try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Book book = new Book(
                    rs.getString("judul"),
                    rs.getString("penerbit"),
                    rs.getString("genre"),
                    rs.getString("penulis"),
                    rs.getString("kategori"),
                    rs.getString("kondisi"),
                    rs.getInt("jumlah_buku"),
                    rs.getInt("donatur_id"),
                    rs.getBoolean("is_verified")
            );
            book.setIdBuku(rs.getInt("id"));
            books.add(book);
        }
    } catch (Exception e) {
        System.out.println("Gagal mengambil daftar buku yang belum diverifikasi: " + e.getMessage());
    }
    return books;
}

}
