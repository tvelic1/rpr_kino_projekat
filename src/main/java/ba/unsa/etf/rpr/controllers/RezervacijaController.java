package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.FilmoviManager;
import ba.unsa.etf.rpr.business.RezervacijaManager;
import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Controller for rezervacija.fxml
 */

public class RezervacijaController {
    public ListView<filmovi> lista;
    public TableView<Rezervacija> vieww;
    public TableColumn<Rezervacija,Integer> id2;
    public TableColumn<Rezervacija,String> ime2;
    public TableColumn<Rezervacija,String> prezime2;
    public TextField imetekst;
    public TextField prezimetekst;
    public ComboBox<filmovi> cmb;
    public Button registerButton;
    FilmoviManager fm=new FilmoviManager();
    RezervacijaManager manager=new RezervacijaManager();
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }



    @FXML
    public void initialize() throws filmoviException {
        lista.setItems(FXCollections.observableList(fm.getAll()));
        cmb.setItems(FXCollections.observableList(fm.getAll()));
        id2.setCellValueFactory(cellData->{Rezervacija r=cellData.getValue(); return new SimpleIntegerProperty(r.getId()).asObject();
        });
        ime2.setCellValueFactory(cellData->{Rezervacija filmovi=cellData.getValue(); return new SimpleStringProperty(filmovi.getImee());});
        prezime2.setCellValueFactory(cellData->{Rezervacija filmovi=cellData.getValue(); return new SimpleStringProperty(filmovi.getPrezime());});
        vieww.setItems(FXCollections.observableList(manager.getAll()));
        lista.getSelectionModel().selectedItemProperty().addListener((obs,o,n)->{
            if(n!=null){
                try {
                    vieww.setItems(FXCollections.observableList(manager.getFiltered2(n.getIme())));
                } catch (filmoviException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void regist(ActionEvent actionEvent) throws filmoviException {
        Window owner = registerButton.getScene().getWindow();
        filmovi a=cmb.getSelectionModel().getSelectedItem();
        if(!prezimetekst.getText().isEmpty() && !imetekst.getText().isEmpty() && a!=null){
        Rezervacija r=new Rezervacija();
        r.setIdfilm(a);
        r.setPrezime(prezimetekst.getText());
        r.setImee(imetekst.getText());
        manager.add(r);
        vieww.setItems(FXCollections.observableList(manager.getAll()));
        imetekst.setText("");
        prezimetekst.setText("");
            cmb.setValue(null);
        }
        else{
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Invalid data");
            imetekst.setText("");
            prezimetekst.setText("");
            cmb.setValue(null);

        }
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goback(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/kino.fxml"));
        stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void dell(ActionEvent actionEvent) throws filmoviException {
        Rezervacija f=vieww.getSelectionModel().getSelectedItem();
        manager.delete(f.getId());
        vieww.setItems(FXCollections.observableList(manager.getAll()));
    }

    public void load(ActionEvent actionEvent) throws filmoviException {
        vieww.setItems(FXCollections.observableList(manager.getAll()));
    }
}
