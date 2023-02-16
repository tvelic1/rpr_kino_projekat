package ba.unsa.etf.rpr.domain;

public class Rezervacija implements Idable{
    private int id;
    private String Imee;
    private  String Prezime;
    private filmovi idfilm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImee() {
        return Imee;
    }

    public void setImee(String imee) {
        Imee = imee;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    public filmovi getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(filmovi idfilm) {
        this.idfilm = idfilm;
    }
    public int getIdfilma(filmovi idfilm){
        return idfilm.getId();
    }


}
