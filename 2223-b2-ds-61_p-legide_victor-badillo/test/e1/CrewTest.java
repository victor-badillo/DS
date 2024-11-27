package e1;

import e1.Artist.*;
import e1.Technicians.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrewTest {
    private static final Film Cars = new Film("Cars", 1000000);
    private static final Film Vacaciones = new Film("Vacaciones", 500000);
    private static final Actor Rayo = new Actor("Rayo", "McQueen", "22222222J", 999999999, "Radiator Springs", 200, "protagonist");
    private static final Actor Mate = new Actor("Mate", "Tractor", "11111111J", 111111111, "Radiator Springs", 220, "secondary");
    private static final Actor Sallie = new Actor("Sallie", "Porsche", "33333333J", 555555555, "Radiator Springs", 150, "figurant");
    private static final Dubber Iago = new Dubber("Iago", "Dominguez", "98765432M", 654654654, "Ourense", 92);
    private static final StuntPerformer Victor = new StuntPerformer("Victor", "Miñambres", "76657675Y", 199009876, "Lugo", 15, true);
    private static final StuntPerformer Samuel = new StuntPerformer("Samuel", "Fernandez", "67321459G", 768321009, "Amsterdam", 6, false);
    private static final Director Pelayo = new Director("Pelayo", "Huerta", "86743223Y", 654321780, "Gijon", 300, 0);
    private static final Director Rafa = new Director("Rafael", "Iglesias", "76584392P", 765999129, "Pais Vasco", 320, 5);
    private static final Musician Vizoso = new Musician("Pablo", "Vizoso", "65789910T", 655433982, "Santiago", 20);
    private static final Producer David = new Producer("David", "Rodriguez", "11122233D", 987123004, "Monforte", 100);
    private static final Screenwriter Lucia = new Screenwriter("Lucia", "Patiño", "76790921R", 156734984,"Barcelona",8, true);
    private static final Screenwriter Deben = new Screenwriter("Alberto", "Deben", "12345678P", 123456789,"Coruña",10, false);

    @BeforeAll
    static void insertMovieRatings() {
        Cars.addWorker(Rayo); Cars.addWorker(Mate); Cars.addWorker(Sallie); Cars.addWorker(Rafa); Cars.addWorker(Vizoso);
    }

    @Test
    void testActor() {
        assertThrows(IllegalArgumentException.class, () -> new Actor("Nano", "Alonso", "99999992H", 654237876, "Oviedo", 180, "Campeon"));
        assertEquals(120000, Rayo.calculatePayment());
        assertEquals(44000, Mate.calculatePayment());
        assertEquals("protagonist", Rayo.getRole());
        assertEquals("secondary", Mate.getRole());
    }

    @Test
    void testDubber(){
        assertEquals(1840, Iago.calculatePayment());
    }

    @Test
    void testStuntPerformer(){
        assertEquals(1600, Victor.calculatePayment());
        assertEquals(240, Samuel.calculatePayment());
        assertTrue(Victor.getHasDangerousScenes());
        assertFalse(Samuel.getHasDangerousScenes());
        assertEquals("Victor Miñambres (Stunt performer with extra for danger): ",Victor.toString());
        assertEquals("Samuel Fernandez (Stunt performer with no extra for danger): ",Samuel.toString());
    }

    @Test
    void testDirector(){
        assertThrows(IllegalArgumentException.class, () -> new Director("Juan", "Illo", "99999992H", 654237876, "Fuengirola", 180, -1));
        assertEquals(30000,Pelayo.calculatePayment());
        assertEquals(5,Rafa.getSeniority());
        assertEquals("Rafael Iglesias (Director, 5 years of experience): ",Rafa.toString());
    }

    @Test
    void testMusician(){
        assertEquals(1200, Vizoso.calculatePayment());
    }

    @Test
    void testProducer(){
        assertEquals(9000,David.calculatePayment());
    }

    @Test
    void testScreenwriter(){
        assertEquals(4560,Lucia.calculatePayment());
        assertEquals(700,Deben.calculatePayment());
        assertTrue(Lucia.getIsOriginalScreenplay());
        assertFalse(Deben.getIsOriginalScreenplay());
        assertEquals("Lucia Patiño (Screenwriter, is original screenplay): ",Lucia.toString());
        assertEquals("Alberto Deben (Screenwriter, not original screenplay): ",Deben.toString());
    }

    @Test
    void testCrew(){
        assertThrows(IllegalArgumentException.class, () -> new Actor("Cuen", "Kro", "998035992H", -654211176, "Kanto", 80, "protagonist"));
        assertEquals("Iago Dominguez (Dubber): ",Iago.toString());
        assertEquals(0.04,Vizoso.getRoyalties());
    }

    @Test
    void testFilm(){
        assertEquals(1000000, Cars.getRevenue());
    }

    @Test
    void printSalaries(){
        assertEquals("There are no crew members for movie: Vacaciones\n",Vacaciones.printSalaries());
        assertEquals("""
                Rayo McQueen (Actor): 120000.0 euro
                Mate Tractor (Actor): 44000.0 euro
                Sallie Porsche (Actor): 30000.0 euro
                Rafael Iglesias (Director, 5 years of experience): 87000.0 euro
                Pablo Vizoso (Musician): 41200.0 euro
                The total payroll for Cars is 322200.0 euro
                """
                ,Cars.printSalaries());
    }

    @Test
    void printRoyalties() {
        assertEquals("There are no crew members for movie: Vacaciones\n",Vacaciones.printRoyalties());
        assertEquals("""
                Rafael Iglesias (Director, 5 years of experience): 50000.0 euros
                Pablo Vizoso (Musician): 40000.0 euros
                """
                ,Cars.printRoyalties());
    }
}