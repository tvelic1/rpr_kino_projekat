package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.domain.filmovi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShowMoviesController {
    public TextArea text;
    public Button okBtn;
    public TextField ocjena;
    private filmovi film;


    public ShowMoviesController(filmovi film) {
        this.film = film;
    }

    @FXML
    public void initialize() {
        text.setText(film.getIme());
        ocjena.setText(filmovi.getOcjena());
    }

    public void okBtnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) text.getScene().getWindow();
        stage.close();
    }

}
