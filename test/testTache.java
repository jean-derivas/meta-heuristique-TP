import junit.framework.TestCase;
import org.junit.Test;

public class testTache extends TestCase {

    @Test
    public final void testTache() throws Exception{

        Tache zeTache = new Tache(5) ;
        MachineCout couple = new MachineCout(1,10);
        zeTache.addCouple(couple);

        assertEquals(5,zeTache.numero);
     //   assertEquals("(numMachine,coutMachine)=(1,10)",zeTache.coupleMachineCout);
    }

}
