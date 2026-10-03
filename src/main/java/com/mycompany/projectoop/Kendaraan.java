package com.mycompany.projectoop;

public class Kendaraan {
    private String platNomor;
    private String pemilik;
    private int tahun;

    public static int totalServisBerhasilDibuat = 0;

    public Kendaraan(String platNomor, String pemilik, int tahun) {
        this.platNomor = platNomor;
        this.pemilik = pemilik;
        this.tahun = tahun;
        totalServisBerhasilDibuat++;
    }

    public String getPlatNomor() {
        return this.platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getPemilik() {
        return this.pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public int getTahun() {
        return this.tahun;
    }

    public void setTahun(int tahun) {
        if (tahun > 1900) {
            this.tahun = tahun;
        } else {
            System.out.println("Tahun kendaraan tidak valid!");
        }
    }

    public void tampilkanInfo() {
        System.out.printf("Plat: %-10s | Pemilik: %-12s | Tahun: %d", 
                this.platNomor, this.pemilik, this.tahun);
    }

    public void layananServis() {
        System.out.println("-> Servis standar kendaraan dilakukan.");
    }
}