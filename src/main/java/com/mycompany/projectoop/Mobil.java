package com.mycompany.projectoop;

public class Mobil extends Kendaraan implements DapatDicuci, DapatGaransi {
    private int jumlahPintu;

    public Mobil(String platNomor, String pemilik, int tahun, int jumlahPintu) throws Exception {
        super(platNomor, pemilik, tahun);
        if (jumlahPintu <= 0) {
            throw new Exception("Jumlah pintu harus lebih dari 0!");
        }
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() {
        return this.jumlahPintu;
    }

    public void setJumlahPintu(int jumlahPintu) {
        this.jumlahPintu = jumlahPintu;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[MOBIL] ");
        super.tampilkanInfo();
        System.out.printf(" | Pintu: %d%n", this.jumlahPintu);
    }

    @Override
    public void layananServis() {
        System.out.println("-> Layanan: Ganti oli mesin, tune-up 4 roda, dan cek sistem AC.");
    }

    @Override
    public void hitungEstimasiBiayaServis() {
        System.out.println("   [Biaya] Estimasi Servis Mobil: Rp 750.000");
    }

    @Override
    public void prosesCuci() {
        System.out.println("   [Cuci] Mobil mendapatkan layanan cuci salju hidrolik gratis.");
    }

    @Override
    public void klaimGaransi(int hari) {
        System.out.println("   [Garansi] Kategori Mobil mendapatkan garansi servis selama " + hari + " hari.");
    }
}