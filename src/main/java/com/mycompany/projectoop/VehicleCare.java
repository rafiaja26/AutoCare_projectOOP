package com.mycompany.projectoop;

import java.util.Scanner;

public class VehicleCare {

    public static void cariKendaraan(String platNomor, Kendaraan[] daftar, int jumlah) {
        System.out.println("\n--- Hasil Pencarian Plat: " + platNomor + " ---");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getPlatNomor().equalsIgnoreCase(platNomor)) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                daftar[i].layananServis();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Kendaraan dengan plat tersebut tidak ditemukan.");
        }
    }

    public static void cariKendaraan(int tahun, Kendaraan[] daftar, int jumlah) {
        System.out.println("\n--- Hasil Pencarian Tahun Produksi: " + tahun + " ---");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getTahun() == tahun) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada kendaraan keluaran tahun tersebut.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kendaraan[] daftarKendaraan = new Kendaraan[10];
        int jumlahKendaraan = 0;
        boolean isRunning = true;

        System.out.println("==============================================");
        System.out.println("   SELAMAT DATANG DI VEHICLECARE SERVICE SYSTEM  ");
        System.out.println("==============================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Antrean Servis");
            System.out.println("2. Lihat Daftar Kendaraan & Servis");
            System.out.println("3. Cari Kendaraan (Fitur Overloading)");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu (1-4): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    if (jumlahKendaraan < daftarKendaraan.length) {
                        System.out.println("\n-- Pilih Jenis Kendaraan --");
                        System.out.println("1. Mobil");
                        System.out.println("2. Motor");
                        System.out.print("Pilihan (1/2): ");
                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Masukkan Plat Nomor : ");
                        String plat = scanner.nextLine();
                        System.out.print("Masukkan Nama Pemilik: ");
                        String pemilik = scanner.nextLine();
                        System.out.print("Masukkan Tahun      : ");
                        int tahun = scanner.nextInt();
                        scanner.nextLine();

                        if (jenis == 1) {
                            System.out.print("Masukkan Jumlah Pintu: ");
                            int pintu = scanner.nextInt();
                            scanner.nextLine();
                            daftarKendaraan[jumlahKendaraan] = new Mobil(plat, pemilik, tahun, pintu);
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Tipe Transmisi (Matic/Manual): ");
                            String transmisi = scanner.nextLine();
                            daftarKendaraan[jumlahKendaraan] = new Motor(plat, pemilik, tahun, transmisi);
                        }

                        jumlahKendaraan++;
                        System.out.println("Sukses! Kendaraan berhasil didaftarkan ke antrean.");
                    } else {
                        System.out.println("Maaf, kapasitas bengkel hari ini sudah penuh!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Daftar Kendaraan & Rencana Servis ---");
                    if (jumlahKendaraan == 0) {
                        System.out.println("Belum ada kendaraan dalam antrean.");
                    } else {
                        for (int i = 0; i < jumlahKendaraan; i++) {
                            System.out.print((i + 1) + ". ");
                            daftarKendaraan[i].tampilkanInfo();
                            daftarKendaraan[i].layananServis();
                            System.out.println();
                        }
                        System.out.println("* Total Antrean Terdaftar: " + Kendaraan.totalServisBerhasilDibuat);
                    }
                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("\n-- Fitur Cari Kendaraan --");
                    System.out.println("1. Cari berdasarkan Plat Nomor");
                    System.out.println("2. Cari berdasarkan Tahun Produksi");
                    System.out.print("Pilih (1/2): ");
                    int modeCari = scanner.nextInt();
                    scanner.nextLine();

                    if (modeCari == 1) {
                        System.out.print("Masukkan Plat Nomor: ");
                        String kataKunci = scanner.nextLine();
                        cariKendaraan(kataKunci, daftarKendaraan, jumlahKendaraan);
                    } else if (modeCari == 2) {
                        System.out.print("Masukkan Tahun Produksi: ");
                        int angkaKunci = scanner.nextInt();
                        scanner.nextLine();
                        cariKendaraan(angkaKunci, daftarKendaraan, jumlahKendaraan);
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }

                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan layanan AutoCare System!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
            }
        }
        scanner.close();
    }
}