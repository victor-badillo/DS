package e3;

import e3.LoginStrategys.*;
import e3.MFAStrategys.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginScreenTest {

    LoginScreen screen = new LoginScreen(new emailStrategy());


    @BeforeAll
    static void insertMembers() {
        User user1 = new User("paquito@udc.es","12345678A","685975306","password",new numbersAndLettersMFA());
        User user2 = new User("user1@udc.es","49339131M","981654099","hello123",new nineCharsMFA());
        User user3 = new User("joselino@udc.es","76438912P","604005719","1formula1",new threeNumbersMFA());
        assertThrows(IllegalArgumentException.class, () -> new User("mayonesa", "999999999D","987650990", "ketchup", new threeNumbersMFA()));
    }

    @Test
    void checkId() {
        assertTrue(screen.checkId("paquito@udc.es"));
        assertFalse(screen.checkId("paquito@gmail.es"));
        screen.setLoginStrategy(new idStrategy());
        assertTrue(screen.checkId("76438912P"));
        assertFalse(screen.checkId("1P238765"));
        screen.setLoginStrategy(new phoneStrategy());
        assertTrue(screen.checkId("981654099"));
        assertFalse(screen.checkId("1234567891"));
    }

    @Test
    void checkPassword() {
        assertTrue(screen.checkPassword("paquito@udc.es","password"));
        assertFalse(screen.checkPassword("paquito@udc.es","Password"));

        screen.setLoginStrategy(new idStrategy());
        assertTrue(screen.checkPassword("49339131M", "hello123"));
        assertFalse(screen.checkPassword("49339131M", "bye123"));

        screen.setLoginStrategy(new phoneStrategy());

        assertTrue(screen.checkPassword("604005719","1formula1"));
        assertFalse(screen.checkPassword("604005719","1formula2"));
    }

    @Test
    void checkProcess(){
        assertEquals("Not valid format", screen.process("paquito@gmail.es", "password", screen.getListUsers().get(0)));
        assertEquals("Incorrect password",screen.process("paquito@udc.es","Password",screen.getListUsers().get(0)));
        assertTrue(screen.process("paquito@udc.es","password",screen.getListUsers().get(0)).matches("^[0-9A-Z]{6}$"));

        screen.setLoginStrategy(new idStrategy());
        screen.getListUsers().get(0).setMFAStrategy(new threeNumbersMFA());

        assertEquals("Not valid format", screen.process("1234567AA", "password", screen.getListUsers().get(0)));
        assertEquals("Incorrect password",screen.process("12345678A","Password",screen.getListUsers().get(0)));
        assertTrue(screen.process("12345678A","password",screen.getListUsers().get(0)).matches("^[0-9]{3}$"));

        screen.setLoginStrategy(new phoneStrategy());
        screen.getListUsers().get(0).setMFAStrategy(new nineCharsMFA());

        assertEquals("Not valid format", screen.process("6859753066", "password", screen.getListUsers().get(0)));
        assertEquals("Incorrect password",screen.process("685975306","Password",screen.getListUsers().get(0)));
        assertTrue(screen.process("685975306","password",screen.getListUsers().get(0)).matches("^[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}$"));
    }

}


