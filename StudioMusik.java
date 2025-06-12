/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

public class StudioMusik {
    private int studioId;
    private String namaStudio;
    private String lokasi;
    private int kapasitas;

    public StudioMusik(String namaStudio, String lokasi, int kapasitas) {
        this.namaStudio = namaStudio;
        this.lokasi = lokasi;
        this.kapasitas = kapasitas;
    }

    public StudioMusik(int studioId, String namaStudio, String lokasi, int kapasitas) {
        this.studioId = studioId;
        this.namaStudio = namaStudio;
        this.lokasi = lokasi;
        this.kapasitas = kapasitas;
    }

    // Getter & Setter
    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getNamaStudio() {
        return namaStudio;
    }

    public void setNamaStudio(String namaStudio) {
        this.namaStudio = namaStudio;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
}

