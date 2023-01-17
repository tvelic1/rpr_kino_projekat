package ba.unsa.etf.rpr.controllers.componets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DoubleButtonCellFactory<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private final EventHandler<ActionEvent> buttonOne;

    private final EventHandler<ActionEvent> buttonTwo;

    /**
     *
     * @param buttonOne - event handler for first button (Edit)
     * @param buttonTwo - event handler for second button (Delete)
     */
    public DoubleButtonCellFactory(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo){
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    @Override
    public TableCell<T, T> call(TableColumn<T, T> quoteObjectTableColumn) {
        return new DoubleButtonTableCell<>(buttonOne, buttonTwo);
    }
}
