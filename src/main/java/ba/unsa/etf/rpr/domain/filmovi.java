package ba.unsa.etf.rpr.domain;
import java.util.*;

public class filmovi implements Idable {
    private int idfilma;
    private String ocjena;

    private int trajanje;
    private String ime;
    private vrstafilma id_vrsta_filma;

    public int getId() {
        return idfilma;
    }

    public void setId(int idfilma) {
        this.idfilma = idfilma;
    }

    public String getOcjena() {
        return ocjena;
    }

    public void setOcjena(String ocjena) {
        this.ocjena = ocjena;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public vrstafilma getId_vrsta_filma() {
        return id_vrsta_filma;
    }

    public void setId_vrsta_filma(vrstafilma id_vrsta_filma) {
        this.id_vrsta_filma = id_vrsta_filma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof filmovi)) return false;
        filmovi filmovi = (filmovi) o;
        return getId() == filmovi.getId() && getTrajanje() == filmovi.getTrajanje() && getOcjena().equals(filmovi.getOcjena()) && getIme().equals(filmovi.getIme()) && getId_vrsta_filma().equals(filmovi.getId_vrsta_filma());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOcjena(), getTrajanje(), getIme(), getId_vrsta_filma());
    }

    @Override
    public String toString() {
        return "filmovi{" +
                "idfilma=" + idfilma +
                ", ocjena='" + ocjena + '\'' +
                ", trajanje=" + trajanje +
                ", ime='" + ime + '\'' +
                ", id_vrsta_filma=" + id_vrsta_filma +
                '}';
    }
}
