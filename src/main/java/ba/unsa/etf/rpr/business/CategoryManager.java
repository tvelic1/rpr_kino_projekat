package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.exceptions.filmoviException;

public class CategoryManager {
    public void validateCategoryName(String name ) throws filmoviException{
        if(name == null || name.length()>45 || name.length()<3){
            throw new filmoviException("Kategorija mora imati izmedju 3 i 45 slova.");
        }

    }

}
