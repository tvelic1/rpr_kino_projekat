package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.vrstafilmaDaoSQLImpl;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCategoryController {

    public Button okbtn;
    public TextField text;
    public vrstafilma vrsta;
    public vrstafilmaDaoSQLImpl dao = new vrstafilmaDaoSQLImpl();

    public void OkbttnAction(ActionEvent actionEvent){
        vrsta=new vrstafilma();
        vrsta.setZanr(text.getText());
        try{
            dao.add(vrsta);
        }catch(filmoviException f){
            System.out.println("Problem with adding");
            throw new RuntimeException(f);
        }
        Stage stage=(Stage)okbtn.getScene().getWindow();
        stage.close();
    }
    public vrstafilma getVrstafilma(){
        return vrsta;
    }
}
