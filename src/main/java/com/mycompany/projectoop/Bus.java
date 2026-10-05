package com.mycompany.projectoop;

public class Bus extends Kendaraan {
    private int kapasitasPenumpang;

    public Bus(String platNomor, String pemilik, int tahun, int kapasitasPenumpang) {
        super(platNomor, pemilik, tahun);
        this.kapasitasPenumpang = kapasitasPenumpang;
    }

    public int getKapasitasPenumpang() {
        return this.kapasitasPenumpang;
    }

    public void setKapasitasPenumpang(int kapasitasPenumpang) {
        this.kapasitasPenumpang = kapasitasPenumpang;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[BUS]   ");
        super.tampilkanInfo();
        System.out.printf(" | Kapasitas: %d Penumpang%n", this.kapasitasPenumpang);
    }

    @Override
    public void layananServis() {
        System.out.println("-> Layanan: Cek rem angin, kuras oli mesin diesel, dan inspeksi kelayakan jalan.");
    }
}