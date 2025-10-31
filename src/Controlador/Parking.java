/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.FuelCar;
import Modelo.ElectricCar;
import Vista.CarsPanel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author rubco
 */
public class Parking {
    //Toolkit.getDefaultToolkit().getImage(getClass().getResource("path"));

    private int nSpaces;
    private int filledSpaces;
    private int nElectricQueue;
    private int nFuelQueue;
    private int nFuelPark;

    private List<Object> parking;

    public int getnSpaces() {
        return nSpaces;
    }

    public int getFilledSpaces() {
        return filledSpaces;
    }

    public int getnElectricQueue() {
        return nElectricQueue;
    }

    public int getnFuelQueue() {
        return nFuelQueue;
    }

    public int getnFuelPark() {
        return nFuelPark;
    }

    public List<Object> getParking() {
        return parking;
    }

    public boolean[] getParkingCheck() {
        return parkingCheck;
    }

    public Queue<ElectricCar> getElectricQueue() {
        return electricQueue;
    }

    public Queue<FuelCar> getFuelQueue() {
        return fuelQueue;
    }
    private final boolean[] parkingCheck;
    private final Queue<ElectricCar> electricQueue;
    private final Queue<FuelCar> fuelQueue;
    private final CarsPanel carsPanel;

    public Parking(CarsPanel carsPanel) {
        nSpaces = 6;
        filledSpaces = 0;
        nElectricQueue = 0;
        nFuelQueue = 0;
        nFuelPark = 0;

        parking = new ArrayList<>();
        parkingCheck = new boolean[6];
        electricQueue = new LinkedList<>();
        fuelQueue = new LinkedList<>();
        this.carsPanel = carsPanel;
        for (int i = 0; i < nSpaces; i++) {
            parkingCheck[i] = false;
            parking.add(i, new Object());
        }
    }

    public synchronized void entraElectrico(ElectricCar electricCar) throws InterruptedException {

        electricQueue.add(electricCar);
        nElectricQueue++;
        carsPanel.repaint();
        while (filledSpaces == nSpaces || !electricQueue.peek().equals(electricCar)) {
            wait();
        }

        electricQueue.poll();
        nElectricQueue--;
        for (int i = 0; i < nSpaces; i++) {
            if (!parkingCheck[i]) {

                parking.set(i, electricCar);
                filledSpaces++;
                parkingCheck[i] = true;
                carsPanel.repaint();
                return;
            }
        }

    }

    public synchronized void saleElectrico(ElectricCar electricCar) {
        for (int i = 0; i < nSpaces; i++) {
            if (parking.get(i).equals(electricCar)) {
                parking.set(i, new Object());
                filledSpaces--;
                parkingCheck[i] = false;
            }
        }
        notifyAll();
        carsPanel.repaint();
    }

    public synchronized void entraCombustion(FuelCar fuelCar) throws InterruptedException {
        fuelQueue.add(fuelCar);
        nFuelQueue++;
        carsPanel.repaint();
        while (filledSpaces == nSpaces || !fuelQueue.peek().equals(fuelCar) || (!electricQueue.isEmpty() && nFuelPark >= 2)) {
            wait();
        }

        fuelQueue.poll();
        nFuelQueue--;
        for (int i = 0; i < nSpaces; i++) {
            if (!parkingCheck[i]) {

                parking.set(i, fuelCar);
                filledSpaces++;
                parkingCheck[i] = true;
                carsPanel.repaint();
                nFuelPark++;
                return;
            }
        }
    }

    public synchronized void saleCombustion(FuelCar fuelCar) {
        for (int i = 0; i < nSpaces; i++) {
            if (parking.get(i).equals(fuelCar)) {
                parking.set(i, new Object());
                filledSpaces--;
                parkingCheck[i] = false;
                nFuelPark--;
            }
        }
        notifyAll();
        carsPanel.repaint();
    }

    public boolean isInQueue(Object obj) {

        if (obj.getClass() == ElectricCar.class) {

            for (int i = 0; i < nElectricQueue; i++) {
                if (electricQueue.contains(obj)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < nFuelQueue; i++) {
                if (fuelQueue.contains(obj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFull() {
        return (filledSpaces == 6);
    }

}
