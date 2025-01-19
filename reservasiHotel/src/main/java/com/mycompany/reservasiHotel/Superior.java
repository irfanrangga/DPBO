package com.mycompany.reservasiHotel;

public class Superior extends Kamar{
    protected int hargaKamar;
    private int charge;
    private int stokKamar = 20;
    private String tipeKasur;

    public Superior(String Nama, String NIK, int Umur, String noHP, int jumlahOrang, int lamaInap, String tipeKasur) {
        super(Nama, NIK, Umur, noHP, "Superior", jumlahOrang, lamaInap);
        this.tipeKasur = tipeKasur;
    }

    @Override
    public int hitungBiaya() {
        if(super.getJumlahOrang() > 2){
            this.charge = 850000 * 15/100;
            hargaKamar = super.lamaInap * 850000 + this.charge;
        } else if (super.getUmur() < 19){
            hargaKamar = 0;
        } else {
            hargaKamar = super.lamaInap * 3000000;
        }
        return hargaKamar;
    }

    @Override
    public void fasilitasKamar() {
        System.out.println("=== Fasilitas Kamar Superior ===");
        System.out.println("180 x 250 cm - 2 Twin Bed / 1 Double Bed - Wifi - Smart TV - AC - Shower & Bathtub - Air Minum");
    }

    @Override
    public void pesanKamar() {
        if(cekKetersediaan() || super.getUmur() > 18){
            System.out.println("=== Berhasil Memesan Kamar Superior ===");
            System.out.println("Kamar dipesan oleh: " + super.getNama());
            System.out.println("Tipe Kamar: Superior");
            System.out.println("Tipe Kasur: " + this.tipeKasur);
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
    public String toString() {
        return "Kamar terisi: " + (20 - this.stokKamar)
                + "\nKamar tersedia: " + this.stokKamar;
    }
}
