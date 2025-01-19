package com.mycompany.reservasiHotel;

public class Standard extends Kamar{
    protected int hargaKamar;
    private int charge;
    private int stokKamar = 20;

    public Standard(String Nama, String NIK, int Umur, String noHP, int jumlahOrang, int lamaInap) {
        super(Nama, NIK, Umur, noHP, "Standard", jumlahOrang, lamaInap);
    }

    @Override
    public int hitungBiaya() {
        if(super.getJumlahOrang() > 2){
            this.charge = 500000 * 10/100;
            hargaKamar = super.lamaInap * 500000 + this.charge;
        } else if (super.getUmur() < 19){
            hargaKamar = 0;
        } else {
            hargaKamar = super.lamaInap * 3000000;
        }
        return hargaKamar;
    }

    @Override
    public void fasilitasKamar() {
        System.out.println("=== Fasilitas Kamar Standard ===");
        System.out.println("96.52 x 190.5 cm - 1 Queen Size Bed - Wifi - AC - TV - Shower - Air Minum");
    }

    @Override
    public void pesanKamar() {
        if(cekKetersediaan() || super.getUmur() > 18){
            System.out.println("=== Berhasil Memesan Kamar Standard ===");
            System.out.println("Kamar dipesan oleh: " + super.getNama());
            System.out.println("Tipe Kamar: Standard");
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
        return "Kamar terisi: " + (20 - this.stokKamar)
                + "\nKamar tersedia: " + this.stokKamar;
    }
}
