package com.mycompany.projectoop;

import java.io.Serializable;

public class Pelanggan implements Serializable {
    private static final long serialVersionUID = 1L;
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