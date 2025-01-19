package com.mycompany.reservasiHotel;

public class Deluxe extends Kamar {
    protected int hargaKamar;
    private int charge;
    private int stokKamar = 15;

    public Deluxe(String Nama, String NIK, int Umur, String noHP, int jumlahOrang, int lamaInap) {
        super(Nama, NIK, Umur, noHP, "Deluxe", jumlahOrang, lamaInap);
    }

    @Override
    public int hitungBiaya(){
        if(super.getJumlahOrang() > 4){
            this.charge = 1200000 * 15/100;
            hargaKamar = super.lamaInap * 1200000 + this.charge;
        } else if (super.getUmur() < 19){
            hargaKamar = 0;
        } else {
            hargaKamar = super.lamaInap * 3000000;
        }
        return hargaKamar;
    }

    @Override
    public void fasilitasKamar() {
        System.out.println("=== Fasilitas Kamar Deluxe ===");
        System.out.println("200 x 320 cm - 1 Double Bed & 2 Twin Bed - Wifi - AC - Smart TV - Shower & Bathtub - Mini Kitchen - Air Minum");
    }

    @Override
    public void pesanKamar() {
        if(cekKetersediaan() || super.getUmur() > 18){
            System.out.println("=== Berhasil Memesan Kamar Deluxe ===");
            System.out.println("Kamar dipesan oleh: " + super.getNama());
            System.out.println("Tipe Kamar: Deluxe");
            System.out.println("Jumlah Orang: " + super.getJumlahOrang());
            System.out.println("Lama Menginap: " + super.lamaInap + " Malam");
            this.stokKamar--;
        } else if(super.getUmur() <= 18){
            System.out.println("Usia Minimal 19 tahun untuk melakukan reservasi.");
        } else {
            System.out.println("Mohon maaf kamar sedang diisi");
        }
    }

    @Override
    public boolean cekKetersediaan() {
        return super.cekKetersediaan();
    }

    @Override
    public String toString(){
        return "Kamar terisi: " + (15 - this.stokKamar)
                + "\nKamar tersedia: " + this.stokKamar;
    }
}
