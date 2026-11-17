package com.mycompany.projectoop;

import java.io.FileWriter;
import java.io.IOException;

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

        try (FileWriter pipaTulis = new FileWriter("log_servis.txt", true)) {
            String log = "Pelanggan: " + pelanggan.getNama() + " | Plat: " + kendaraan.getPlatNomor() + " | Pemilik: " + kendaraan.getPemilik() + "\n";
            pipaTulis.write(log);
        } catch (IOException e) {
            System.out.println("Peringatan: Gagal menulis log transaksi ke file. " + e.getMessage());
        }
    }
}