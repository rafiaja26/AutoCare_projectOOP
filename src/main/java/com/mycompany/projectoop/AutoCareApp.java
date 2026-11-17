package com.mycompany.projectoop;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AutoCareApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Bengkel autoCareStudio = new Bengkel("AutoCare Service Studio");
        autoCareStudio.bukaBengkel();

        autoCareStudio.muatDataDariFile();

        boolean isRunning = true;

        System.out.println("==============================================");
        System.out.println("   SELAMAT DATANG DI AUTOCARE SERVICE SYSTEM  ");
        System.out.println("==============================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Lihat Daftar Kendaraan");
            System.out.println("3. Cari Kendaraan");
            System.out.println("4. Kelola Pelanggan");
            System.out.println("5. Peminjaman / Servis (Demo Association)");
            System.out.println("6. Baca File Log Servis (Demo File I/O Teks)");
            System.out.println("7. Simpan & Keluar");
            System.out.print("Pilih Menu: 1-7: ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        System.out.println("\n-- Pilih Jenis Kendaraan --");
                        System.out.println("1. Mobil | 2. Motor | 3. Bus");
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

                        if (jenis == 1) {
                            System.out.print("Masukkan Jml Pintu: ");
                            autoCareStudio.tambahKendaraan(new Mobil(plat, pemilik, tahun, scanner.nextInt()));
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Transmisi: ");
                            autoCareStudio.tambahKendaraan(new Motor(plat, pemilik, tahun, scanner.nextLine()));
                        } else if (jenis == 3) {
                            System.out.print("Masukkan Kapasitas: ");
                            autoCareStudio.tambahKendaraan(new Bus(plat, pemilik, tahun, scanner.nextInt()));
                        }
                    }

                    case 2 -> {
                        System.out.println("\n--- Daftar Kendaraan di Bengkel ---");
                        autoCareStudio.lihatSemuaKendaraan();
                    }

                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Kendaraan --");
                        System.out.print("Masukkan Plat Nomor: ");
                        String kataKunci = scanner.nextLine();

                        try {
                            autoCareStudio.cariKendaraan(kataKunci);
                        } catch (KendaraanTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        }
                    }

                    case 4 -> {
                        System.out.println("\n-- Kelola Pelanggan --");
                        System.out.println("1. Tambah Pelanggan");
                        System.out.println("2. Cari Pelanggan");
                        System.out.println("3. Lihat Semua");
                        System.out.print("Pilih: ");
                        int menuP = scanner.nextInt();
                        scanner.nextLine();

                        if (menuP == 1) {
                            System.out.print("Masukkan No KTP/ID: ");
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama     : ");
                            String nama = scanner.nextLine();
                            autoCareStudio.tambahPelanggan(new Pelanggan(id, nama));
                        } else if (menuP == 2) {
                            System.out.print("No KTP/ID yg dicari: ");
                            autoCareStudio.cariPelanggan(scanner.nextLine());
                        } else if (menuP == 3) {
                            autoCareStudio.lihatSemuaPelanggan();
                        }
                    }

                    case 5 -> {
                        System.out.println("\n-- Sistem Servis (Asosiasi) --");
                        System.out.print("Masukkan No KTP/ID Pelanggan: ");
                        String inputId = scanner.nextLine();

                        Pelanggan pelanggan = autoCareStudio.getPelanggan(inputId);

                        if (pelanggan == null) {
                            System.out.println("Gagal: Pelanggan belum terdaftar!");
                        } else if (autoCareStudio.getJumlahKendaraan() == 0) {
                            System.out.println("Gagal: Belum ada kendaraan yang bisa diservis.");
                        } else {
                            autoCareStudio.lihatSemuaKendaraan();
                            System.out.print("Pilih Nomor Kendaraan yg ingin diservis: ");
                            int noKendaraan = scanner.nextInt() - 1;
                            scanner.nextLine();

                            if (noKendaraan >= 0 && noKendaraan < autoCareStudio.getJumlahKendaraan()) {
                                Kendaraan itemServis = autoCareStudio.getKendaraan(noKendaraan);
                                TransaksiServis.prosesTransaksi(pelanggan, itemServis);
                            } else {
                                System.out.println("Nomor kendaraan tidak valid!");
                            }
                        }
                    }

                    case 6 -> {
                        autoCareStudio.bacaLogServis();
                    }

                    case 7 -> {
                        System.out.println("Menyimpan sesi ke database internal...");
                        autoCareStudio.simpanDataKeFile();
                        System.out.println("Terima kasih telah menggunakan AutoCare Service System!");
                        isRunning = false;
                    }

                    default -> System.out.println("Pilihan tidak valid.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\n[FATAL ERROR] Input tidak valid! Masukkan angka.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERROR SISTEM] " + e.getMessage());
            }
        }
        scanner.close();
    }
}