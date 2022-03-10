package a1;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ElephantTest {

    @Test
    public void testConstuctor1() {
        // create one Elephant and check all its fields
        Elephant draf= new Elephant("leeroy", 'F', 2019, 5);
        assertEquals("leeroy", draf.name());
        assertEquals(true, draf.isFemale());
        assertEquals("5/2019", draf.date());
        assertEquals(null, draf.mom());
        assertEquals(null, draf.dad());
        assertEquals(0, draf.children());

        // create another elephant and test for its gender
        Elephant lara= new Elephant("leeroy", 'M', 2019, 5);
        assertEquals(true, !lara.isFemale());

        // precondition for one character long nickname
        assertThrows(AssertionError.class, () -> { new Elephant("", 'M', 2019, 5); });
    }

    @Test
    // tests addDad() and addMom() methods
    public void testMutator() {
        // declare and instantiate elephant objects
        Elephant daughter= new Elephant("josh", 'F', 2001, 5);
        Elephant dad= new Elephant("leeroy", 'M', 2019, 5);
        Elephant mom= new Elephant("sara", 'F', 2019, 5);
        Elephant mom2= new Elephant("sara2", 'F', 2019, 5);

        // test initial fields
        assertEquals("josh", daughter.name());
        assertEquals(true, daughter.isFemale());
        assertEquals("5/2001", daughter.date());
        assertEquals(null, daughter.mom());
        assertEquals(null, daughter.dad());

        // apply mutator methods
        daughter.addDad(dad);
        daughter.addMom(mom);

        // test for changed fields
        assertEquals(mom, daughter.mom());
        assertEquals(dad, daughter.dad());
        assertEquals(0, daughter.children());
        assertEquals(1, dad.children());
        assertEquals(1, dad.children());

        // precondition for addMom
        assertThrows(AssertionError.class, () -> { daughter.addMom(mom2); });
    }

    @Test
    public void testConstructor2() {
        Elephant sondad= new Elephant("johndad", 'M', 2002, 4);
        Elephant sonmom= new Elephant("johnmom", 'F', 2001, 3);
        Elephant son= new Elephant("johnny", 'M', 2020, 1, sonmom, sondad);
        assertEquals("johnny", son.name());
        assertEquals(false, son.isFemale());
        assertEquals("1/2020", son.date());
        assertEquals(0, son.children());
        assertEquals(son.mom(), sonmom);
        assertEquals(1, sonmom.children());
        assertEquals(son.dad(), sondad);
        assertEquals(1, sondad.children());
        assertThrows(AssertionError.class, () -> { new Elephant("abc", 'M', 2019, 5, son, null); });
    }

    @Test
    public void testComparison() {
        // isolder
        Elephant e1= new Elephant("johndad", 'M', 2002, 4);
        Elephant e2= new Elephant("johnmom", 'F', 2002, 4);
        Elephant e3= new Elephant("johnmom", 'F', 2001, 4);
        Elephant e4= new Elephant("johnmom", 'F', 2001, 3);
        Elephant e5= new Elephant("johnmom", 'F', 2002, 3);
        Elephant e6= new Elephant("johnmom", 'M', 2002, 5);
        Elephant e7= new Elephant("johnmom", 'M', 2001, 5);
        Elephant e8= new Elephant("johnmom", 'M', 2003, 5);
        Elephant e9= new Elephant("johnmom", 'M', 2003, 3);
        Elephant e10= null;

        assertEquals(false, e1.isOlder(e1));
        assertEquals(false, e1.isOlder(e2));
        assertEquals(false, e1.isOlder(e3));
        assertEquals(false, e1.isOlder(e4));
        assertEquals(false, e1.isOlder(e5));
        assertEquals(true, e1.isOlder(e6));
        assertEquals(false, e1.isOlder(e7));
        assertEquals(true, e1.isOlder(e8));
        assertEquals(true, e1.isOlder(e9));
        assertThrows(AssertionError.class, () -> { e1.isOlder(e10); });
        // assertThrows(NullPointerException.class, () -> { e10.isOlder(e1); });

        // aresibs
        Elephant son1= new Elephant("johnny", 'M', 2020, 1, e2, e1);
        Elephant son2= new Elephant("johnny", 'M', 2022, 1, e2, e1);
        Elephant son3= new Elephant("johnny", 'M', 2020, 1, e3, e1);
        Elephant son4= new Elephant("johnny", 'M', 2020, 1, e2, e6);
        Elephant son5= new Elephant("johnny", 'M', 2020, 1, e3, e6);
        Elephant son6= null;
        Elephant son7= new Elephant("johnny", 'M', 2020, 1);
        Elephant son8= new Elephant("johnny", 'M', 2020, 1);

        assertEquals(false, son1.areSibs(son1));
        assertEquals(true, son1.areSibs(son2));
        assertEquals(true, son1.areSibs(son3));
        assertEquals(true, son1.areSibs(son4));
        assertEquals(false, son1.areSibs(son5));
        assertEquals(false, son1.areSibs(son6));
        assertEquals(false, son1.areSibs(son7));
        assertEquals(false, son7.areSibs(son8));

        son7.addMom(e2);
        assertEquals(false, son7.areSibs(son8));

        son8.addMom(e2);
        assertEquals(true, son7.areSibs(son8));

    }
}
