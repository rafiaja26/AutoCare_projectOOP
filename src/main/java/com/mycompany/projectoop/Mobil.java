package com.mycompany.projectoop;

public class Mobil extends Kendaraan {
    private int jumlahPintu;

    public Mobil(String platNomor, String pemilik, int tahun, int jumlahPintu) {
        super(platNomor, pemilik, tahun);
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
        System.out.printf(" | Pintu: %d Hal%n", this.jumlahPintu);
    }

    @Override
    public void layananServis() {
        System.out.println("-> Layanan: Ganti oli mesin, tune-up 4 roda, dan cek sistem AC.");
    }
}