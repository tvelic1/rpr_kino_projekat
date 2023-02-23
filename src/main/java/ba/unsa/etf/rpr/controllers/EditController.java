package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.FilmoviManager;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author tvelic1
 *
 */

public class EditController {
    Parent root;
    Stage stage;
    Scene scene;

    public ComboBox editbox;
    public TextField ime;
    public TextField ocjena;
    public TextField trajanje;
    public TextField zanr;

    int a;
    FilmoviManager fm=new FilmoviManager();
    EditModel model=new EditModel();

    public void initialize() throws filmoviException{
        editbox.setItems(FXCollections.observableList(fm.getAll()));
        editbox.getSelectionModel().selectedItemProperty().addListener((obs,o,n)->{
            if(o!=null){ filmovi x=(filmovi) o;
                a=x.getId();

                ime.textProperty().unbindBidirectional(model.ime);
                ocjena.textProperty().unbindBidirectional((model.ocjena));
                trajanje.textProperty().unbindBidirectional(model.trajanje);
                zanr.textProperty().unbindBidirectional(model.zanr);
            }
            filmovi x=(filmovi) n;
            a=x.getId();
            try {
                model.fromFilmovi((filmovi) n);
            } catch (filmoviException e) {
                throw new RuntimeException(e);
            }
            ime.textProperty().bindBidirectional(model.ime);
            ocjena.textProperty().bindBidirectional(model.ocjena);
            trajanje.textProperty().bindBidirectional(model.trajanje);
            zanr.textProperty().bindBidirectional(model.zanr);
            zanr.setEditable(false);



        });
    }

    /**
     *
     * @param event
     * @throws Exception
     */


    public void updateMovie(ActionEvent event) throws filmoviException, IOException {

     filmovi  f=new filmovi();
     f.setId(a);
     f.setIme(model.ime.get());
     f.setTrajanje(Integer.parseInt(model.trajanje.get()));
     f.setOcjena(model.ocjena.get());
        fm.update(f);
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/kino.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

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

        return f;
    }

    }
}
