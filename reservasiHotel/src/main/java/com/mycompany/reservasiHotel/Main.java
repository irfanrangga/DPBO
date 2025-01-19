package com.mycompany.reservasiHotel;

public class Main {
    public static void main(String[] args) {
        Kamar[] reservasi = {
                new Standard("Mike", "21023810381", 19, "08129238129", 5, 2),
                new Superior("John", "12320381", 32, "08123938103", 2, 2, "Twin Bed"),
                new Deluxe("Tony", "123231312", 19, "0812923813", 2, 1),
                new Suite("Ruben", "123821903", 52, "0918231321", 2, 3),
                new Suite("Erik", "3781321738", 17, "08128329132", 2, 3)
        };

        Pemesan[] dataPemesan = {
                new Pemesan("Mike", "21023810381", 19, "08129238129"),
                new Pemesan("John", "12320381321", 32, "08123938103"),
                new Pemesan("Tony", "12323131421", 19, "0812923813"),
                new Pemesan("Ruben", "12382190332", 52, "0918231321"),
                new Pemesan("Erik", "3781321738", 17, "08128329132")
        };

        System.out.println("\nData Pemesan");
        for(Pemesan data: dataPemesan){
            System.out.println(data.toString());
        }
        System.out.println();

        for(Kamar info: reservasi){
            info.fasilitasKamar();
            System.out.println("--------------------------------------");
            info.pesanKamar();
            System.out.println("Total Harga: " + info.hitungBiaya());
            System.out.println("--------------------------------------");
            System.out.println(info.toString());
            System.out.println("--------------------------------------");
            System.out.println();
        }
    }
}