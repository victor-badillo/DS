package e2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class RealityTest {
    private static final TVRealityList<String> listRebound1 = new TVRealityList<>(true);
    private static final TVRealityList<String> listRebound2 = new TVRealityList<>(true);
    private static final TVRealityList<String> listRebound3 = new TVRealityList<>(true);
    private static final TVRealityList<String> listCircle1 = new TVRealityList<>(false);
    private static final TVRealityList<String> listCircle2 = new TVRealityList<>(false);
    private static final TVRealityList<String> listCircle3 = new TVRealityList<>(false);
    private static final TVRealityList<String> listShort1 = new TVRealityList<>(true);
    private static final TVRealityList<String> listShort2 = new TVRealityList<>(false);
    private static final TVRealityList<String> emptyList1 = new TVRealityList<>(true);
    private static final TVRealityList<String> emptyList2 = new TVRealityList<>(false);

    @BeforeAll
    static void insertNames(){
        listRebound1.addPerson("Oscar"); listRebound1.addPerson("Esteban"); listRebound1.addPerson("Laura"); listRebound1.addPerson("Manu"); listRebound1.addPerson("Maria");

        listRebound2.addPerson("Lucia"); listRebound2.addPerson("Lucas"); listRebound2.addPerson("Pepe"); listRebound2.addPerson("Paco");

        listRebound3.addPerson("Laura"); listRebound3.addPerson("Martin"); listRebound3.addPerson("Luis");

        listCircle1.addPerson("Juan"); listCircle1.addPerson("Javi"); listCircle1.addPerson("Alonso");

        listCircle2.addPerson("Oscar"); listCircle2.addPerson("Esteban"); listCircle2.addPerson("Laura");

        listCircle3.addPerson("Alex"); listCircle3.addPerson("Rafa"); listCircle3.addPerson("Alan");

        listShort1.addPerson("Victor");

        listShort2.addPerson("Pablo");
    }

    @Test
    void testSelectCandidates(){
        assertEquals("Oscar",listRebound1.selectCandidates(listRebound1,3)); //1st position rebound list
        assertEquals("Pepe",listRebound2.selectCandidates(listRebound2,2)); //middle position rebound list
        assertEquals("Luis",listRebound3.selectCandidates(listRebound3,2)); //last position rebound list
        assertEquals("Juan",listCircle1.selectCandidates(listCircle1,5)); //1st position circular list
        assertEquals("Esteban",listCircle2.selectCandidates(listCircle2,5)); //middle position circular list
        assertEquals("Alan",listCircle3.selectCandidates(listCircle3,1)); //last position circular list
        assertThrows(IllegalArgumentException.class,() -> emptyList1.selectCandidates(emptyList1,2)); //use selectCandidates with an empty list
        assertThrows(IllegalArgumentException.class,() -> emptyList2.selectCandidates(emptyList2,2)); //use selectCandidates with an empty list
    }

    @Test
    void testNext(){
        assertThrows(NoSuchElementException.class,() -> listShort1.iterator().next()); //1 element rebound list
        assertThrows(NoSuchElementException.class,() -> listShort2.iterator().next()); //1 element circular list
        assertThrows(NoSuchElementException.class,() -> emptyList1.iterator().next()); //empty rebound list
        assertThrows(NoSuchElementException.class,() -> emptyList2.iterator().next()); //empty circular list

    }

    @Test
    void testHasnext(){
        assertFalse(listShort1.iterator().hasNext()); //1 element rebound list
        assertFalse(listShort2.iterator().hasNext()); //1 element circular list
        assertFalse(emptyList1.iterator().hasNext()); //empty rebound list
        assertFalse(emptyList2.iterator().hasNext()); //empty circular list
    }

    @Test
    void testRemove(){
        assertThrows(IllegalStateException.class,() -> listShort1.iterator().remove()); //1 element rebound list
        assertThrows(IllegalStateException.class,() -> listShort2.iterator().remove()); //1 element circular list
        assertThrows(IllegalStateException.class,() -> emptyList1.iterator().remove()); //empty rebound list
        assertThrows(IllegalStateException.class,() -> emptyList2.iterator().remove()); //empty circular list
    }




}

