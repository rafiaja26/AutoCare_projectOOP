package com.mycompany.projectoop;

public class Pelanggan {
    private String idKtp;
    private String nama;

    public Pelanggan(String idKtp, String nama) {
        this.idKtp = idKtp;
        this.nama = nama;
    }

    public String getIdKtp() {
        return idKtp;
    }

    public String getNama() {
        return nama;
    }
}