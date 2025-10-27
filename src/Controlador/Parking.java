/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.FuelCar;
import Modelo.ElectricCar;

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
    
    private Object [] parking;
    private ElectricCar [] electricQueue;
    private FuelCar [] fuelQueue;
    
    public Parking(){
        nSpaces = 6;
        filledSpaces = 0;
        nElectricQueue = 0;
        nFuelQueue = 0;
        
        parking = new Object[nSpaces];
        electricQueue = new ElectricCar[30];
        fuelQueue = new FuelCar[30];
        
    }
}
