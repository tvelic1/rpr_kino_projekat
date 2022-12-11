package ba.unsa.etf.rpr.domain;
import java.util.*;

public class filmovi {
    private int idfilma;
    private String ocjena;

    private int trajanje;
    private String ime;
    private vrstafilma id_vrsta_filma;

    public int getIdfilma() {
        return idfilma;
    }

    public void setIdfilma(int idfilma) {
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
        return getIdfilma() == filmovi.getIdfilma() && getTrajanje() == filmovi.getTrajanje() && getOcjena().equals(filmovi.getOcjena()) && getIme().equals(filmovi.getIme()) && getId_vrsta_filma().equals(filmovi.getId_vrsta_filma());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdfilma(), getOcjena(), getTrajanje(), getIme(), getId_vrsta_filma());
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
