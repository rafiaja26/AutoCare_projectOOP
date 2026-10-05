package com.mycompany.projectoop;

import java.util.Scanner;

public class AutoCareApp {

    // Compile-Time Polymorphism (Method Overloading)
    public static void cariKendaraan(String platNomor, Kendaraan[] daftar, int jumlah) {
        System.out.println("\n--- Hasil Pencarian Plat (Teks): " + platNomor + " ---");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getPlatNomor().equalsIgnoreCase(platNomor)) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                simulasiServis(daftar[i]); // Upcasting & Dynamic Binding
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Kendaraan tidak ditemukan.");
        }
    }

    public static void cariKendaraan(int tahun, Kendaraan[] daftar, int jumlah) {
        System.out.println("\n--- Hasil Pencarian Tahun (Angka): " + tahun + " ---");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getTahun() == tahun) {
                System.out.print("- Ditemukan: ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Kendaraan tidak ditemukan.");
        }
    }

    // Runtime Polymorphism & Dynamic Binding lewat Upcasting
    public static void simulasiServis(Kendaraan item) {
        item.layananServis();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Array bertipe Superclass (Polymorphic Array
        Kendaraan[] daftarKendaraan = new Kendaraan[10];
        int jumlahKendaraan = 0;
        boolean isRunning = true;

        System.out.println("==============================================");
        System.out.println("   SELAMAT DATANG DI AUTOCARE SERVICE SYSTEM  ");
        System.out.println("==============================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Antrean Servis");
            System.out.println("2. Lihat Daftar Kendaraan & Servis");
            System.out.println("3. Cari Kendaraan");
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
                        System.out.println("3. Bus");
                        System.out.print("Pilihan (1/2/3): ");
                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Masukkan Plat Nomor : ");
                        String plat = scanner.nextLine();
                        System.out.print("Masukkan Nama Pemilik: ");
                        String pemilik = scanner.nextLine();
                        System.out.print("Masukkan Tahun      : ");
                        int tahun = scanner.nextInt();
                        scanner.nextLine();

                        // Polymorphism
                        if (jenis == 1) {
                            System.out.print("Masukkan Jumlah Pintu: ");
                            int pintu = scanner.nextInt();
                            scanner.nextLine();
                            daftarKendaraan[jumlahKendaraan] = new Mobil(plat, pemilik, tahun, pintu);
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Tipe Transmisi (Matic/Manual): ");
                            String transmisi = scanner.nextLine();
                            daftarKendaraan[jumlahKendaraan] = new Motor(plat, pemilik, tahun, transmisi);
                        } else if (jenis == 3) {
                            System.out.print("Masukkan Kapasitas Penumpang: ");
                            int kapasitas = scanner.nextInt();
                            scanner.nextLine();
                            daftarKendaraan[jumlahKendaraan] = new Bus(plat, pemilik, tahun, kapasitas);
                        }

                        jumlahKendaraan++;
                        System.out.println("Sukses! Kendaraan berhasil ditambahkan.");
                    } else {
                        System.out.println("Maaf, kapasitas bengkel penuh!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Daftar Kendaraan di Bengkel ---");
                    if (jumlahKendaraan == 0) {
                        System.out.println("Belum ada kendaraan yang tersimpan.");
                    } else {
                        for (int i = 0; i < jumlahKendaraan; i++) {
                            System.out.print((i + 1) + ". ");
                            // Dynamic Binding
                            daftarKendaraan[i].tampilkanInfo();
                            simulasiServis(daftarKendaraan[i]); // Upcasting
                            System.out.println();
                        }
                        System.out.println("* Total Antrean Terdaftar: " + Kendaraan.totalServisBerhasilDibuat);
                    }
                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("\n-- Fitur Cari Kendaraan --");
                    System.out.println("1. Cari berdasarkan Plat Nomor (String)");
                    System.out.println("2. Cari berdasarkan Tahun Produksi (Integer)");
                    System.out.print("Pilih (1/2): ");
                    int modeCari = scanner.nextInt();
                    scanner.nextLine();

                    if (modeCari == 1) {
                        System.out.print("Masukkan Plat Nomor: ");
                        String kataKunci = scanner.nextLine();
                        cariKendaraan(kataKunci, daftarKendaraan, jumlahKendaraan); // Compile-time Polymorphism
                    } else if (modeCari == 2) {
                        System.out.print("Masukkan Tahun: ");
                        int angkaKunci = scanner.nextInt();
                        scanner.nextLine();
                        cariKendaraan(angkaKunci, daftarKendaraan, jumlahKendaraan); // Compile-time Polymorphism
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }

                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan AutoCare System!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
            }
        }
        scanner.close();
    }
}