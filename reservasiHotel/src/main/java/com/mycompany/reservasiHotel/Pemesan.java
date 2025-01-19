package com.mycompany.reservasiHotel;

public class Pemesan {
    private String Nama;
    private String NIK;
    private int Umur;
    private String noHP;

    public Pemesan(String Nama, String NIK, int Umur, String noHP) {
        this.Nama = Nama;
        this.NIK = NIK;
        this.Umur = Umur;
        this.noHP = noHP;
    }

    public String getNama() {
        return Nama;
    }

    public int getUmur() {
        return Umur;
    }

    @Override
    public String toString() {
        return "Nama: " + Nama +
                " | NIK: " + NIK +
                " | Umur: " + Umur +
                " | Nomor HP: " + noHP;
    }
}
