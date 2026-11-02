package com.mycompany.projectoop;

public class Motor extends Kendaraan implements DapatGaransi {
    private String tipeTransmisi;

    public Motor(String platNomor, String pemilik, int tahun, String tipeTransmisi) throws Exception {
        super(platNomor, pemilik, tahun);
        if (tipeTransmisi.isEmpty()) {
            throw new Exception("Tipe transmisi harus punya inisialisasi!");
        }
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
        System.out.println("-> Layanan: Servis CVT/Rantai, ganti oli mesin, dan pembersihan injeksi.");
    }

    @Override
    public void hitungEstimasiBiayaServis() {
        System.out.println("   [Biaya] Estimasi Servis Motor: Rp 250.000");
    }

    @Override
    public void klaimGaransi(int hari) {
        System.out.println("   [Garansi] Kategori Motor mendapatkan garansi servis selama " + hari + " hari.");
    }
}