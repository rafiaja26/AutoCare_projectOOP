package com.mycompany.projectoop;

public class Motor extends Kendaraan {
    private String tipeTransmisi;

    public Motor(String platNomor, String pemilik, int tahun, String tipeTransmisi) {
        super(platNomor, pemilik, tahun);
        this.tipeTransmisi = tipeTransmisi;
    }

    public String getTipeTransmisi() {
        return this.tipeTransmisi;
    }

    public void setTipeTransmisi(String tipeTransmisi) {
        this.tipeTransmisi = tipeTransmisi;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[MOTOR] ");
        super.tampilkanInfo();
        System.out.printf(" | Transmisi: %s%n", this.tipeTransmisi);
    }

    @Override
    public void layananServis() {
        System.out.println("-> Layanan: Servis CVT/Rantai, ganti oli mesin, dan pembersihan karbu/injeksi.");
    }
}