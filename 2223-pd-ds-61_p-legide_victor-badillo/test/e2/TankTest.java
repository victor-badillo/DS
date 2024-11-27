package e2;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

class TankTest {
    private static final Tank tank1 = new Tank("Seals tank", "Outside");
    private static final Tank tank4 = new Tank("Baby seals tank", "Inside");
    private static final Tank tank2 = new Tank("Octopus tank", "Outside");
    private static final Tank tank3 = new Tank("Squids tank", "Inside");
    private static final Tank tank5 = new Tank("Sharks tank", "Nautilus room");

    private static final Sensor oxygenSensor1 = new Sensor("Oxygen Seals control 1","oxygen");
    private static final Sensor oxygenSensor4 = new Sensor("Oxygen Seals control 2","oxygen");
    private static final Sensor temperatureSensor1 = new Sensor("Temperature Sharks control 1","Temperature");
    private static final Sensor phSensor1 = new Sensor("PH Sharks control 1","PH");
    private static final Sensor oxygenSensor2 = new Sensor("Oxygen Octopus control 1","oxygen");
    private static final Sensor oxygenSensor3 = new Sensor("Oxygen Squids control 1","oxygen");

    private static final Alert alert1 = new Alert(5, 10.5,2,14.5);
    private static final Alert alert2 = new Alert(6,9,4,14);
    private static final Alert alert3 = new Alert(4.5,10,2,14.5);
    private static final Alert alert4 = new Alert(4.8,9.9,2.5,15);
    private static final Alert alert5 = new Alert(20, 26, 15, 28);
    private static final Alert alert6 = new Alert(4.92, 7, 2,12.06);
    private static final Personnel personnel1 = new Personnel("Maintenance Seals");
    private static final Personnel personnel2 = new Personnel("Maintenance Octopus");
    private static final Personnel personnel3 = new Personnel("Maintenance Squids");
    private static final Personnel personnel4 = new Personnel("Maintenance Sharks");
    @BeforeAll
    static void setAquarium(){
        tank1.addSensor(oxygenSensor1);
        oxygenSensor1.attach(alert1);
        alert1.subscribePersonnel(personnel1);

        tank2.addSensor(oxygenSensor2);
        oxygenSensor2.attach(alert2);
        oxygenSensor2.attach(alert3);
        alert3.subscribePersonnel(personnel2);

        tank3.addSensor(oxygenSensor3);
        oxygenSensor3.attach(alert1);
        alert1.subscribePersonnel(personnel3);

        tank4.addSensor(oxygenSensor4);
        oxygenSensor4.attach(alert4);
        alert4.subscribePersonnel(personnel1);

        tank5.addSensor(temperatureSensor1);
        tank5.addSensor(phSensor1);
        phSensor1.attach(alert6);
        temperatureSensor1.attach(alert5);
        alert5.subscribePersonnel(personnel4);
        alert6.subscribePersonnel(personnel4);

    }

    @Test
    void firedAlertTest(){
        oxygenSensor1.setParameterLevel(17, "11/12/2022", "11:00");
        oxygenSensor1.setParameterLevel(3.50, "10/12/2022","12:00");
        assertEquals("""
                Alerts of Maintenance Seals
                               
                RED Alerts:
                * RED alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 17.0
                11/12/2022, 11:00
                               
                ORANGE alerts:
                * ORANGE alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 3.5
                10/12/2022, 12:00             
                """, personnel1.getAllReports());

        oxygenSensor1.setParameterLevel(16, "10/12/2022", "13:00");
        alert1.setDeactivated();
        oxygenSensor1.setParameterLevel(17, "10/12/2022", "15:00");
        alert1.setActivated();
        oxygenSensor1.setParameterLevel(16, "10/12/2022", "20:00");
        oxygenSensor1.setParameterLevel(5.50, "11/12/2022","22:00");
        oxygenSensor1.setParameterLevel(2.50, "11/12/2022","17:00");
        assertEquals(7.75, oxygenSensor1.getParameterLevel());


        assertEquals("""
                Alerts of Maintenance Seals
                    
                RED Alerts:
                * RED alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 16.0
                10/12/2022, 13:00
                * RED alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 16.0
                10/12/2022, 20:00
                * RED alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 17.0
                11/12/2022, 11:00
                    
                ORANGE alerts:
                * ORANGE alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 3.5
                10/12/2022, 12:00
                * ORANGE alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 2.5
                11/12/2022, 17:00
                """, personnel1.getAllReports());

        oxygenSensor4.setParameterLevel(3,"12/12/2022", "04:00");
        assertEquals(7.35, oxygenSensor4.getParameterLevel());


        assertEquals("""
                Alerts of Maintenance Seals
                    
                RED Alerts:
                * RED alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 16.0
                10/12/2022, 13:00
                * RED alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 16.0
                10/12/2022, 20:00
                * RED alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 17.0
                11/12/2022, 11:00
                    
                ORANGE alerts:
                * ORANGE alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 3.5
                10/12/2022, 12:00
                * ORANGE alert
                Seals tank, Outside
                Oxygen Seals control 1: oxygen level = 2.5
                11/12/2022, 17:00
                * ORANGE alert
                Baby seals tank, Inside
                Oxygen Seals control 2: oxygen level = 3.0
                12/12/2022, 04:00
                """, personnel1.getAllReports());

        oxygenSensor2.setParameterLevel(16, "15/12/2022", "08:20");
        assertEquals(7.25, oxygenSensor2.getParameterLevel());


        assertEquals("""
                Alerts of Maintenance Octopus
                              
                RED Alerts:
                * RED alert
                Octopus tank, Outside
                Oxygen Octopus control 1: oxygen level = 16.0
                15/12/2022, 08:20
                """, personnel2.getAllReports());

        oxygenSensor2.setParameterLevel(9.5, "14/12/2022", "14:00");
        assertEquals(9.5, oxygenSensor2.getParameterLevel());


        assertEquals("""
                Alerts of Maintenance Octopus
                              
                RED Alerts:
                * RED alert
                Octopus tank, Outside
                Oxygen Octopus control 1: oxygen level = 16.0
                15/12/2022, 08:20
                """, personnel2.getAllReports());

        oxygenSensor2.setParameterLevel(11, "14/12/2022", "16:00");
        oxygenSensor2.setParameterLevel(15, "14/12/2022", "20:00");
        assertEquals(7.25, oxygenSensor2.getParameterLevel());


        assertEquals("""
                Alerts of Maintenance Octopus
                                
                RED Alerts:
                * RED alert
                Octopus tank, Outside
                Oxygen Octopus control 1: oxygen level = 15.0
                14/12/2022, 20:00
                * RED alert
                Octopus tank, Outside
                Oxygen Octopus control 1: oxygen level = 16.0
                15/12/2022, 08:20
                                
                ORANGE alerts:
                * ORANGE alert
                Octopus tank, Outside
                Oxygen Octopus control 1: oxygen level = 11.0
                14/12/2022, 16:00                                
                """, personnel2.getAllReports());

        temperatureSensor1.setParameterLevel(30, "14/12/2022", "20:00");
        temperatureSensor1.setParameterLevel(25, "14/12/2022", "20:02");
        phSensor1.setParameterLevel(2,"15/12/2022", "05:00");
        phSensor1.setParameterLevel(3.6, "14/12/2022", "09:00");
        assertEquals(5.96, phSensor1.getParameterLevel());
        assertEquals(25, temperatureSensor1.getParameterLevel());

        assertEquals("""
                Alerts of Maintenance Sharks
                               
                RED Alerts:
                * RED alert
                Sharks tank, Nautilus room
                Temperature Sharks control 1: Temperature level = 30.0
                14/12/2022, 20:00
                * RED alert
                Sharks tank, Nautilus room
                PH Sharks control 1: PH level = 2.0
                15/12/2022, 05:00
                               
                ORANGE alerts:
                * ORANGE alert
                Sharks tank, Nautilus room
                PH Sharks control 1: PH level = 3.6
                14/12/2022, 09:00                                            
                """, personnel4.getAllReports());

    }

    @Test
    void badDate(){
        assertThrows(IllegalArgumentException.class, ()-> oxygenSensor1.setParameterLevel(0, "Lunes","00:00"));
        assertThrows(IllegalArgumentException.class, ()-> oxygenSensor1.setParameterLevel(0, "01/20/2022","10"));
    }
    @Test
    void sensorObservers(){
       assertEquals(1, oxygenSensor3.getObservers().size());
       oxygenSensor3.detach(alert1);
       assertEquals(0, oxygenSensor3.getObservers().size());
    }
}