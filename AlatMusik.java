/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studiomusikreservasi;

public class AlatMusik {
    private int alatId;
    private String namaAlat;
    private int studioId;

    public AlatMusik(String namaAlat, int studioId) {
        this.namaAlat = namaAlat;
        this.studioId = studioId;
    }

    public AlatMusik(int alatId, String namaAlat, int studioId) {
        this.alatId = alatId;
        this.namaAlat = namaAlat;
        this.studioId = studioId;
    }

    // Getter & Setter
    public int getAlatId() {
        return alatId;
    }

    public void setAlatId(int alatId) {
        this.alatId = alatId;
    }

    public String getNamaAlat() {
        return namaAlat;
    }

    public void setNamaAlat(String namaAlat) {
        this.namaAlat = namaAlat;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }
}

