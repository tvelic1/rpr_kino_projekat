package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class GLEDATELJI implements Idable {
    private int jmbg;
    private String ime;
    private filmovi id_film;

    public int getId() {
        return jmbg;
    }

    public void setId(int jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public filmovi getId_film() {
        return id_film;
    }

    public void setId_film(filmovi id_film) {
        this.id_film = id_film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GLEDATELJI)) return false;
        GLEDATELJI that = (GLEDATELJI) o;
        return getId() == that.getId() && getIme().equals(that.getIme()) && getId_film().equals(that.getId_film());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIme(), getId_film());
    }

    @Override
    public String toString() {
        return "GLEDATELJI{" +
                "jmbg=" + jmbg +
                ", ime='" + ime + '\'' +
                ", id_film=" + id_film +
                '}';
    }
}
