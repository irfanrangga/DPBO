package Book;

public class Book {
    private int idBuku;
    private String judul;
    private String penerbit;
    private String genre;
    private String penulis;
    private String kategori;
    private String kondisi;
    private int jumlahBuku;
    private int donaturId;
    private boolean isVerified;
    private String ulasan;
    private int rating;

    // Constructor
    public Book(String judul, String penerbit, String genre, String penulis, String kategori, String kondisi, int jumlahBuku, int donaturId,boolean isVerified) {
        this.judul = judul;
        this.penerbit = penerbit;
        this.genre = genre;
        this.penulis = penulis;
        this.kategori = kategori;
        this.kondisi = kondisi;
        this.jumlahBuku = jumlahBuku;
        this.donaturId = donaturId;
        this.isVerified = isVerified;
    }

    // Getter dan Setter
    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public String getGenre() {
        return genre;
    }

    public String getPenulis() {
        return penulis;
    }

    public String getKategori() {
        return kategori;
    }

    public String getKondisi() {
        return kondisi;
    }

    public int getJumlahBuku() {
        return jumlahBuku;
    }
    

    public int getDonaturId() {
        return donaturId;
    }
    
    public boolean isVerified() {
    return isVerified;
}

public void setVerified(boolean verified) {
    isVerified = verified;
}
}
