package com.mycompany.projectoop;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Bengkel {
    private String namaBengkel;
    private FasilitasBengkel fasilitasUtama;
    private ArrayList<Kendaraan> daftarKendaraan;
    private HashMap<String, Pelanggan> dataPelanggan;

    public Bengkel(String namaBengkel) {
        this.namaBengkel = namaBengkel;
        this.fasilitasUtama = new FasilitasBengkel("Pit Stop Utama & Hidrolik", 8);
        this.daftarKendaraan = new ArrayList<>();
        this.dataPelanggan = new HashMap<>();
    }

    public void bukaBengkel() {
        System.out.println("\n*** Menginisialisasi sistem " + this.namaBengkel + " ***");
        fasilitasUtama.tampilkanFasilitas();
    }

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
        System.out.println("Sukses! Kendaraan berhasil ditambahkan. (Total Antrean: " + daftarKendaraan.size() + ")");
    }

    public void tambahPelanggan(Pelanggan p) {
        dataPelanggan.put(p.getIdKtp(), p);
        System.out.println("Berhasil menambahkan pelanggan!");
    }

    public void lihatSemuaKendaraan() {
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada kendaraan yang tersimpan.");
            return;
        }
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarKendaraan.get(i).tampilkanInfo();
        }
    }

    public void cariKendaraan(String platNomor) throws KendaraanTidakDitemukanException {
        System.out.println("Mencari Kendaraan dengan Plat: " + platNomor);
        boolean ditemukan = false;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getPlatNomor().equalsIgnoreCase(platNomor)) {
                k.tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new KendaraanTidakDitemukanException("Gagal: Kendaraan dengan plat '" + platNomor + "' tidak terdaftar di sistem.");
        }
    }

    public void cariPelanggan(String idKtp) {
        if (dataPelanggan.containsKey(idKtp)) {
            System.out.println("Ditemukan! Nama: " + dataPelanggan.get(idKtp).getNama());
        } else {
            System.out.println("Pelanggan dengan No KTP/ID tersebut tidak terdaftar.");
        }
    }

    public void lihatSemuaPelanggan() {
        System.out.println("Total Pelanggan Terdaftar: " + dataPelanggan.size());
        for (Pelanggan p : dataPelanggan.values()) {
            System.out.println("- No KTP/ID: " + p.getIdKtp() + " | Nama: " + p.getNama());
        }
    }

    public Pelanggan getPelanggan(String idKtp) {
        return dataPelanggan.get(idKtp);
    }

    public Kendaraan getKendaraan(int index) {
        return daftarKendaraan.get(index);
    }

    public int getJumlahKendaraan() {
        return daftarKendaraan.size();
    }

    public void simpanDataKeFile() {
        try (FileOutputStream fileKeluar = new FileOutputStream("data_autocare.dat");
             ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar)) {

            mesinPembeku.writeObject(this.daftarKendaraan);
            mesinPembeku.writeObject(this.dataPelanggan);

            System.out.println("-> [SYSTEM LOG] Seluruh data berhasil disimpan (Serialization) ke dalam Harddisk!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        File cekFile = new File("data_autocare.dat");
        if (!cekFile.exists()) return;

        try (FileInputStream fileMasuk = new FileInputStream(cekFile);
             ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk)) {

            this.daftarKendaraan = (ArrayList<Kendaraan>) mesinPembangkit.readObject();
            this.dataPelanggan = (HashMap<String, Pelanggan>) mesinPembangkit.readObject();

            Kendaraan.totalServisBerhasilDibuat = this.daftarKendaraan.size();

            System.out.println("-> [SYSTEM LOG] Data sistem berhasil dimuat ulang dari penyimpanan.");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data: " + e.getMessage());
        }
    }

    public void bacaLogServis() {
        System.out.println("\n--- Riwayat Log Servis ---");
        File fileLog = new File("log_servis.txt");
        if (!fileLog.exists()) {
            System.out.println("Belum ada log servis kendaraan.");
            return;
        }
        try (Scanner mesinBaca = new Scanner(fileLog)) {
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membaca file log.");
        }
    }
}