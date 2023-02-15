package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.FilmoviManager;
import ba.unsa.etf.rpr.domain.filmovi;
import ba.unsa.etf.rpr.domain.vrstafilma;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ba.unsa.etf.rpr.exceptions.filmoviException;

import java.util.ArrayList;
import java.util.List;

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
                if(vf.getZanr().equals("Horor"))
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
    void deletiontest(){
        vrstafilma f=new vrstafilma();
        boolean ima=false;
        try {
            List<vrstafilma> lista = cm.getAll();
            for(vrstafilma vm:lista){
                if(vm.getZanr().equals("Horor")){ f=vm; ima=true;break;}
            }
            if(ima){
      assertThrows(filmoviException.class,()->{cm.delete1("Horor");});
            }
        }catch(filmoviException ff){
            throw new RuntimeException(ff);
        }
    }
    @Test
    void newTest(){
        assertThrows(filmoviException.class,()->{
            cm.add(new vrstafilma());
        });
    }


    }

