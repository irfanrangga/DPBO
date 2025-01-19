package com.mycompany.reservasiHotel;

public class Suite extends Kamar{
    protected int hargaKamar;
    private int charge;
    private int stokKamar = 8;

    public Suite(String Nama, String NIK, int Umur, String noHP, int jumlahOrang, int lamaInap) {
        super(Nama, NIK, Umur, noHP, "Suite", jumlahOrang, lamaInap);
    }

    @Override
    public int hitungBiaya() {
        if(super.getJumlahOrang() > 5){
            this.charge = 3000000 * 25/100;
            hargaKamar = super.lamaInap * 3000000 + this.charge;
        } else if (super.getUmur() < 19){
            hargaKamar = 0;
        } else {
            hargaKamar = super.lamaInap * 3000000;
        }
        return hargaKamar;
    }

    @Override
    public void fasilitasKamar() {
        System.out.println("=== Fasilitas Kamar Suite ===");
        System.out.println("800 x 1200 cm - 1 King Size Bed - AC - Wifi - Smart TV - Bathtub & Shower - Mini Kitchen - Mini Living Room - Meeting Room - Private Service");
    }

    @Override
    public void pesanKamar() {
        if(cekKetersediaan() || super.getUmur() > 18){
            System.out.println("=== Berhasil Memesan Kamar Suite ===");
            System.out.println("Kamar dipesan oleh: " + super.getNama());
            System.out.println("Tipe Kamar: Suite");
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
        return "Kamar terisi: " + (8 - this.stokKamar)
                + "\nKamar tersedia: " + this.stokKamar;
    }
}
