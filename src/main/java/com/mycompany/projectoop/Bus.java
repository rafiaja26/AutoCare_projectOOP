package com.mycompany.projectoop;

public class Bus extends Kendaraan implements DapatDicuci {
    private int kapasitasPenumpang;

    public Bus(String platNomor, String pemilik, int tahun, int kapasitasPenumpang) throws Exception {
        super(platNomor, pemilik, tahun);
        if (kapasitasPenumpang <= 0) {
            throw new Exception("Kapasitas penumpang harus lebih dari 0!");
        }
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

    @Override
    public void hitungEstimasiBiayaServis() {
        System.out.println("   [Biaya] Estimasi Servis Bus: Rp 1.800.000");
    }

    @Override
    public void prosesCuci() {
        System.out.println("   [Cuci] Bus mendapatkan layanan cuci armada otomatis.");
    }
}