package com.mycompany.projectoop;

public class TransaksiServis {
    public static void prosesTransaksi(Pelanggan pelanggan, Kendaraan kendaraan) {
        System.out.println("\n===== STRUK TRANSAKSI SERVIS AUTOCARE =====");
        System.out.println("Pelanggan : " + pelanggan.getNama() + " (" + pelanggan.getIdKtp() + ")");
        System.out.print("Kendaraan : ");
        kendaraan.tampilkanInfo();
        System.out.println("Status    : Berhasil dicatat di sistem servis.");
        
        kendaraan.layananServis();
        kendaraan.hitungEstimasiBiayaServis();

        if (kendaraan instanceof DapatDicuci) {
            ((DapatDicuci) kendaraan).prosesCuci();
        }
        if (kendaraan instanceof DapatGaransi) {
            ((DapatGaransi) kendaraan).klaimGaransi(30);
        }
        System.out.println("===========================================");
    }
}