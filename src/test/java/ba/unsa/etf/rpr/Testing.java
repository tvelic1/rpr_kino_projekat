package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.FilmoviManager;
import ba.unsa.etf.rpr.business.GledateljiManager;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.domain.vrstafilma;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author tvelic1
 *
 */

public class Testing {
    private FilmoviManager filmm=new FilmoviManager();
    private CategoryManager cm=new CategoryManager();
    @Test
    void testforGetAll(){
   assertDoesNotThrow(()->filmm.getAll());
        };
    @Test
    void testforAdd(){
        filmovi fil=new filmovi();
        fil.setTrajanje(100);
        fil.setOcjena("5");
        fil.setIme("con");
        boolean x=false;
        try{ List<vrstafilma> vr=cm.getAll();
            for(vrstafilma vf:vr){
                if(vf.getZanr().equals("Komedija"))
                    fil.setId_vrsta_filma(vf); break;
            }
        filmm.add(fil);
            List<filmovi> l=filmm.getAll();
            for(filmovi g:l){
                if(g.getIme().equals("con")) x=true;
            } assertTrue(x);
        }
        catch (filmoviException fii){
            throw new RuntimeException(fii);

        }


    } @Test
    void deletionconstraintTest(){
        vrstafilma f=new vrstafilma();
        boolean ima=false;
        try {
            List<vrstafilma> lista = cm.getAll();
            for(vrstafilma vm:lista){
                if(vm.getZanr().equals("Komedija")){ f=vm; ima=true;break;}
            }
            if(ima){
      assertThrows(filmoviException.class,()->{cm.deleteByName("Komedija");});
            }
        }catch(filmoviException ff){
            throw new RuntimeException(ff);
        }
    }
    @Test
    void noParametersAddTest(){
        assertThrows(filmoviException.class,()->{
            cm.add(new vrstafilma());
        });
    }
   /* @Test
    void successfulDeletion(){
        filmovi f=new filmovi();
        boolean z = false;
        try {
            List<filmovi> lista = filmm.getAll();
            for (filmovi fil : lista) {
                if (fil.getId()==8) {
                    f = fil;
                    z=true;
                    break;
                }
            }
            filmm.delete(f.getId());
            List<filmovi> listaa = filmm.getAll();

            for (filmovi fil : listaa) {
                if (fil.getId()==8) z=false;
            }
        }catch(filmoviException ff){
            throw new RuntimeException(ff);
        }
        assertTrue(z);


    }*/
    @Test
    void getByIdTest(){
        boolean z=false;
        try{
       vrstafilma vm=cm.getById(1);

        if(vm.getZanr().equals("Komedija")) z=true;} catch(filmoviException e){
        throw new RuntimeException(e);
    }

       assertTrue(z);

    }
    @Test
    void getFilteredTest(){ int n;
        try{
        List<filmovi> lista=filmm.getFiltered("Autobiografski");
   n= lista.size();}
    catch(filmoviException film){
        throw new RuntimeException(film);
    } assertEquals(1,n);
    }
    @Test
    void searchTest(){ int a=1;
        try {
            List<filmovi> lista = filmm.search("Lord");
            for(filmovi f:lista){
               a=f.getId();
               break;
            }
        }catch(filmoviException fi){
            throw new RuntimeException(fi);
        } assertEquals(13,a);
    }
    @Test
    void insertValidateTest(){
        GledateljiManager g=new GledateljiManager();
        try{
        g.insertRecord("abcde","abcde","abcde");
        assertTrue(g.validate("abcde","abcde"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

