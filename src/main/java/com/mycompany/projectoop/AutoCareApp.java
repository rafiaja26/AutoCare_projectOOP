package com.mycompany.projectoop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AutoCareApp {

    public static void cariKendaraan(String platNomor, ArrayList<Kendaraan> daftarKendaraan) throws KendaraanTidakDitemukanException {
        System.out.println("Mencari Kendaraan dengan Plat: " + platNomor);
        boolean ditemukan = false;

        for (int i = 0; i < daftarKendaraan.size(); i++) {
            if (daftarKendaraan.get(i).getPlatNomor().equalsIgnoreCase(platNomor)) {
                daftarKendaraan.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            throw new KendaraanTidakDitemukanException("Gagal: Kendaraan dengan plat '" + platNomor + "' tidak terdaftar di sistem.");
        }
    }

    public static void simulasiServis(Kendaraan item) {
        item.layananServis();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
        HashMap<String, String> dataPelanggan = new HashMap<>();

        boolean isRunning = true;

        System.out.println("==============================================");
        System.out.println("   SELAMAT DATANG DI AUTOCARE SERVICE SYSTEM  ");
        System.out.println("==============================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Antrean Servis (ArrayList)");
            System.out.println("2. Lihat Daftar Kendaraan & Servis");
            System.out.println("3. Cari Kendaraan (Demo Custom Exception)");
            System.out.println("4. Manajemen Pelanggan (Demo HashMap)");
            System.out.println("5. Keluar");
            System.out.print("Pilih Menu (1-5): ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
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

                        if (jenis == 1) {
                            System.out.print("Masukkan Jumlah Pintu: ");
                            int pintu = scanner.nextInt();
                            scanner.nextLine();
                            daftarKendaraan.add(new Mobil(plat, pemilik, tahun, pintu));
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Tipe Transmisi (Matic/Manual): ");
                            String transmisi = scanner.nextLine();
                            daftarKendaraan.add(new Motor(plat, pemilik, tahun, transmisi));
                        } else if (jenis == 3) {
                            System.out.print("Masukkan Kapasitas Penumpang: ");
                            int kapasitas = scanner.nextInt();
                            scanner.nextLine();
                            daftarKendaraan.add(new Bus(plat, pemilik, tahun, kapasitas));
                        }

                        System.out.println("Sukses! Kendaraan berhasil ditambahkan. (Total Antrean: " + daftarKendaraan.size() + ")");
                        break;

                    case 2:
                        System.out.println("\n--- Daftar Kendaraan di Bengkel ---");
                        if (daftarKendaraan.isEmpty()) {
                            System.out.println("Belum ada kendaraan yang tersimpan.");
                        } else {
                            for (int i = 0; i < daftarKendaraan.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                daftarKendaraan.get(i).tampilkanInfo();
                                simulasiServis(daftarKendaraan.get(i));
                                daftarKendaraan.get(i).hitungEstimasiBiayaServis();

                                if (daftarKendaraan.get(i) instanceof DapatDicuci) {
                                    DapatDicuci itemBisaDicuci = (DapatDicuci) daftarKendaraan.get(i);
                                    itemBisaDicuci.prosesCuci();
                                }
                                if (daftarKendaraan.get(i) instanceof DapatGaransi) {
                                    DapatGaransi itemBisaGaransi = (DapatGaransi) daftarKendaraan.get(i);
                                    itemBisaGaransi.klaimGaransi(30);
                                }
                                System.out.println();
                            }
                            System.out.println("* Total Antrean Terdaftar: " + Kendaraan.totalServisBerhasilDibuat);
                        }
                        break;

                    case 3:
                        System.out.println("\n-- Fitur Cari Kendaraan --");
                        System.out.print("Masukkan Plat Nomor: ");
                        String kataKunci = scanner.nextLine();

                        try {
                            cariKendaraan(kataKunci, daftarKendaraan);
                        } catch (KendaraanTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        } finally {
                            System.out.println("-> Sesi pencarian selesai dieksekusi.");
                        }
                        break;

                    case 4:
                        System.out.println("\n-- Kelola Pelanggan (HashMap) --");
                        System.out.println("1. Tambah Pelanggan Baru");
                        System.out.println("2. Cari Pelanggan via No KTP/ID");
                        System.out.println("3. Tampilkan Semua Pelanggan");
                        System.out.print("Pilih: ");
                        int menuPelanggan = scanner.nextInt();
                        scanner.nextLine();

                        if (menuPelanggan == 1) {
                            System.out.print("Masukkan No KTP/ID (sebagai Key): ");
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap (sebagai Value): ");
                            String nama = scanner.nextLine();

                            dataPelanggan.put(id, nama);
                            System.out.println("Berhasil menambahkan pelanggan!");
                        } else if (menuPelanggan == 2) {
                            System.out.print("Masukkan No KTP/ID yang dicari: ");
                            String searchId = scanner.nextLine();

                            if (dataPelanggan.containsKey(searchId)) {
                                System.out.println("Ditemukan! Nama: " + dataPelanggan.get(searchId));
                            } else {
                                System.out.println("Pelanggan dengan No KTP/ID tersebut tidak terdaftar.");
                            }
                        } else if (menuPelanggan == 3) {
                            System.out.println("Total Pelanggan Terdaftar: " + dataPelanggan.size());
                            for (String keyId : dataPelanggan.keySet()) {
                                System.out.println("- No KTP/ID: " + keyId + " | Nama: " + dataPelanggan.get(keyId));
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Terima kasih telah menggunakan AutoCare System!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-5.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("\n[FATAL ERROR] Input tidak valid! Anda harus memasukkan angka.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERROR SISTEM] Terjadi kegagalan: " + e.getMessage());
            }
        }
        scanner.close();
    }
}