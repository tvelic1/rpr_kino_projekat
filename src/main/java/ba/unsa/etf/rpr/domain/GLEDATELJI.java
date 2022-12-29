package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class GLEDATELJI implements Idable {
    private int id;
    private String ime;
    private filmovi id_film;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int jmbg) {
        this.id = jmbg;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId_film(filmovi id_film) {
        this.id_film = id_film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GLEDATELJI)) return false;
        GLEDATELJI that = (GLEDATELJI) o;
        return getId() == that.getId() && getIme().equals(that.getIme()) && getId_film().equals(that.getId_film()) && getEmail().equals(that.getEmail()) && getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIme(), getId_film(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "GLEDATELJI{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", id_film=" + id_film +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
