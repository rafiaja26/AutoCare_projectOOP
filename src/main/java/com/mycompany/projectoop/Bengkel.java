package com.mycompany.projectoop;

import java.util.ArrayList;
import java.util.HashMap;

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
}