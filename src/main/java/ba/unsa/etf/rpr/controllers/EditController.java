package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.FilmoviManager;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class EditController {

    public ComboBox editbox;
    public TextField prvi;
    public TextField drugi;
    public TextField treci;
    public TextField cetvrti;
    FilmoviManager fm=new FilmoviManager();
    EditModel model=new EditModel();

    public void initialize() throws filmoviException{
        editbox.setItems(FXCollections.observableList(fm.getAll()));
        editbox.getSelectionModel().selectedItemProperty().addListener((obs,o,n)->{
            if(o!=null){

                prvi.textProperty().unbindBidirectional(model.ime);
                drugi.textProperty().unbindBidirectional((model.ocjena));
                treci.textProperty().unbindBidirectional(model.trajanje);
            }
            try {
                model.fromFilmovi((filmovi) n);
            } catch (filmoviException e) {
                throw new RuntimeException(e);
            }
            prvi.textProperty().bindBidirectional(model.ime);
            drugi.textProperty().bindBidirectional(model.ocjena);
            treci.textProperty().bindBidirectional(model.trajanje);
            cetvrti.textProperty().bindBidirectional(model.zanr);
           try {
                editbox.setItems(FXCollections.observableList(fm.getAll()));
            } catch (filmoviException e) {
                throw new RuntimeException(e);
            }


        });
    }
    CategoryManager man=new CategoryManager();


    public void upd(ActionEvent actionEvent) throws filmoviException {

     filmovi  f=new filmovi();
     f.setId(fm.getIdfilma(model.ime.get()));
        System.out.println(fm.getIdfilma(model.ime.get()));
     f.setIme(model.ime.get());
        System.out.println(fm.getIdfilma(model.ime.get()));
     f.setTrajanje(Integer.parseInt(model.trajanje.get()));
        System.out.println(Integer.parseInt(model.trajanje.get()));
     f.setOcjena(model.ocjena.get());
        System.out.println(model.ocjena.get());
        fm.update(f);
       /* if(f.getIme() !=null)
        editbox.setItems(FXCollections.observableList(fm.getAll()));*/

    }

    public class EditModel{
        FilmoviManager fm=new FilmoviManager();
        public SimpleStringProperty ime=new SimpleStringProperty();
        public SimpleStringProperty ocjena=new SimpleStringProperty();
        public SimpleStringProperty trajanje=new SimpleStringProperty();
        public SimpleStringProperty zanr=new SimpleStringProperty();
        public Integer id;


    public  void fromFilmovi(filmovi f) throws filmoviException {
        this.ime=new SimpleStringProperty(f.getIme());
        this.ocjena=new SimpleStringProperty(f.getOcjena());
        this.trajanje= new SimpleStringProperty(Integer.toString(f.getTrajanje()));
        this.zanr=new SimpleStringProperty(fm.getZaanr(f.getId_vrsta_filma1()));
    }
    public filmovi toFilm(){
        filmovi f=new filmovi();
        f.setId(model.id);
        f.setTrajanje(Integer.parseInt(trajanje.get()));
        f.setOcjena(ocjena.get());
        f.setIme(ime.get());
       // f.setId_vrsta_filma();
        return f;
    }

    }
}
