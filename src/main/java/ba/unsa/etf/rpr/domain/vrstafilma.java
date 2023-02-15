package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class vrstafilma implements Idable{


    private int id;
    private String zanr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof vrstafilma)) return false;
        vrstafilma that = (vrstafilma) o;
        return getId() == that.getId() && getZanr().equals(that.getZanr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getZanr());
    }

    @Override
    public String toString() {
        return zanr;
    }
}
