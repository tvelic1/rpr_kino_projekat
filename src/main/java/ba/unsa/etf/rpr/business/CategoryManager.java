package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import ba.unsa.etf.rpr.domain.vrstafilma;

import java.util.List;

public class CategoryManager {
    public void validateCategoryName(String name ) throws filmoviException{
        if(name == null || name.length()>45 || name.length()<3){
            throw new filmoviException("Kategorija mora imati izmedju 3 i 45 slova.");
        }

    }
public vrstafilma add(vrstafilma v) throws filmoviException{
        if(v.getId()!=0){
            throw new filmoviException("Ne možete dodati kategorije preko ID-a, ID se automatski dodjeljuje");
        }validateCategoryName(v.getZanr());

        try{
            return DaoFactory.vrstaaDao().add(v);
        }catch(filmoviException e){
            if(e.getMessage().contains("UQ_NAME"))
                throw new filmoviException("Već postoji taj žanr");
            throw e;
        }

        }

        public void delete(int catId) throws filmoviException{
        try{
            DaoFactory.vrstaaDao().delete(catId);
        }catch(filmoviException e){
            if(e.getMessage().contains("FOREIGN KEY"))
                throw new filmoviException("Ne možete obrisati kategoriju koje je povezana sa filmovima, prvo morate obrisati filmove");
            throw e;
        }

        }
        public vrstafilma update (vrstafilma v) throws filmoviException{
        validateCategoryName(v.getZanr());
        return DaoFactory.vrstaaDao().update(v);

        }
        public List<vrstafilma>getAll() throws filmoviException {
        return DaoFactory.vrstaaDao().getAll();

        }

}
