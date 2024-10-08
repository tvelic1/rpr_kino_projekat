package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.dao.vrstafilmaDao;
import ba.unsa.etf.rpr.domain.vrstafilma;
import ba.unsa.etf.rpr.exceptions.filmoviException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


public class MockTesting {

    private CategoryManager cm;


    @Mock
    private vrstafilmaDao vrstadao;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        cm=new CategoryManager();

    }
    @Test
    public void addTest() throws filmoviException {
        vrstafilma v=new vrstafilma();
        v.setZanr("abc");
        vrstadao.add(v);
        verify(vrstadao).add(v);

    }
    @Test
    public void deleteTest() throws filmoviException {
        vrstafilma v=new vrstafilma();
        v.setZanr("bda");
        vrstadao.add(v);
        vrstadao.deleteByName("bda");
        verify(vrstadao).deleteByName("bda");

    }
}
