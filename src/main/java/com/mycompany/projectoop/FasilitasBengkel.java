package com.mycompany.projectoop;

public class FasilitasBengkel {
    private String namaFasilitas;
    private int kapasitasBay;

    public FasilitasBengkel(String namaFasilitas, int kapasitasBay) {
        this.namaFasilitas = namaFasilitas;
        this.kapasitasBay = kapasitasBay;
    }

    public void tampilkanFasilitas() {
        System.out.println("-> [" + namaFasilitas + "] Siap melayani dengan kapasitas " + kapasitasBay + " bay servis.");
    }
}