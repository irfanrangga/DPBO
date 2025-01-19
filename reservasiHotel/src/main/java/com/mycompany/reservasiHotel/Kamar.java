package com.mycompany.reservasiHotel;

public class Kamar extends Pemesan implements Reservasi{
    private String tipeKamar;
    private int jumlahOrang;
    protected int lamaInap;
    protected int hargaKamar;
    private int stokKamar;

    public Kamar(String Nama, String NIK, int Umur, String noHP, String tipeKamar, int jumlahOrang, int lamaInap) {
        super(Nama, NIK, Umur, noHP);
        this.tipeKamar = tipeKamar;
        this.jumlahOrang = jumlahOrang;
        this.lamaInap = lamaInap;
    }

    public int getJumlahOrang() {
        return jumlahOrang;
    }

    @Override
    public void pesanKamar() {
        if(cekKetersediaan() && super.getUmur() > 18){
            System.out.println("Kamar berhasil dipesan.");
        } else if(super.getUmur() <= 18){
            System.out.println("Usia Minimal 19 tahun untuk melakukan reservasi.");
        } else {
            System.out.println("Mohon maaf kamar sedang diisi.");
        }
    }

    @Override
    public int hitungBiaya() {
        this.hargaKamar = 0;
        return lamaInap * hargaKamar;
    }

    @Override
    public void fasilitasKamar() {
        System.out.println("pilih tipe kamar");
    }

    @Override
    public boolean cekKetersediaan() {
        return this.stokKamar > 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
