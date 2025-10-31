/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Controlador.Parking;
import Modelo.ElectricCar;
import Modelo.FuelCar;
import Vista.CarsPanel;
import Vista.Ventana;

import java.util.Random;

/**
 *
 * @author rubco
 */
public class Generador {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

        Random rd = new Random(System.currentTimeMillis());
        Thread[] cars = new Thread[30];
        CarsPanel carsPanel = new CarsPanel();
        Parking parking = new Parking(carsPanel);
        carsPanel.setParking(parking);
        Ventana ven = new Ventana(carsPanel);

        for (int i = 0; i < 30; i++) {
            if (rd.nextInt(1000) < 500) {
                cars[i] = new ElectricCar(i + 1, parking);
                cars[i].start();

            } else {
                FuelCar fuel = new FuelCar(i + 1, parking);
                cars[i] = new Thread(fuel);
                cars[i].start();
            }
            Thread.sleep(1000 + rd.nextInt(1000));

        }

        for (int i = 0; i < 30; i++) {
            cars[i].join();
        }

        ven.dispose();
    }

}
