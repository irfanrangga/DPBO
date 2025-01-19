/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

/**
 *
 * @author Naufal
 */
public abstract class User {

    private int id;
    private String nama;
    private String email;
    private String alamat;
    private String password;
    private String kontak;
    private String role;

    public User(String nama, String email, String alamat, String password, String kontak, String role) {
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.password = password;
        this.kontak = kontak;
        this.role = role;
    }
    
    // Constructor
    public User(int id, String nama, String email, String alamat, String password, String kontak, String role) {
        this.id = id; // Simpan ID ke atribut kelas
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.password = password;
        this.kontak = kontak;
        this.role = role;
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }
}
