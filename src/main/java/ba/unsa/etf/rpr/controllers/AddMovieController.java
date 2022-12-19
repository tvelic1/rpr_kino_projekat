package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.dao.filmoviDaoSQLImpl;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.List;

public class AddMovieController {
    public ChoiceBox<vrstafilma> choiceCatId;
    public TextField fldAddQuote;
   // public DatePicker fldDatePickerId;
    private ObservableList<vrstafilma> observableList;
    private filmoviDaoSQLImpl filmDaoSQL;
    public AddMovieController(List<vrstafilma> categoryList, filmoviDaoSQLImpl filmDaoSQL){
        observableList = FXCollections.observableArrayList(categoryList);
        this.filmDaoSQL = filmDaoSQL;
    }

    @FXML
    public void initialize() {
        choiceCatId.setItems(observableList);
    }
    public void onActionAddMovie(ActionEvent actionEvent) {
        filmovi newFilm = new filmovi();
        try {
            newFilm.setId_vrsta_filma(choiceCatId.getSelectionModel().getSelectedItem());
            newFilm.setIme(fldAddQuote.getText());

            filmDaoSQL.add(newFilm);
            Stage stage = (Stage) fldAddQuote.getScene().getWindow();
            stage.close();
        } catch (filmoviException e) {
            System.out.println("Something went wrong in onActionAddMovie method");
            throw new RuntimeException(e);
        }
    }
}
