/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.FuelCar;
import Modelo.ElectricCar;
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
    
    private List<Object> parking;
    private boolean [] parkingCheck;
    private Queue<ElectricCar> electricQueue;
    private Queue<FuelCar> fuelQueue;
    
    public Parking(){
        nSpaces = 6;
        filledSpaces = 0;
        nElectricQueue = 0;
        nFuelQueue = 0;
        
        parking = new ArrayList<>();
        parkingCheck = new boolean [6];
        electricQueue = new LinkedList<>();
        fuelQueue = new LinkedList<>();
        for(int i = 0; i < nSpaces; i++){
            parkingCheck[i] = false;
        }
    }
    
    public synchronized void entraElectrico(ElectricCar electricCar) throws InterruptedException{
        
        electricQueue.add(electricCar);
        nElectricQueue++;
        
        while(filledSpaces == nSpaces || !electricQueue.peek().equals(electricCar)){
            wait();
        }
        
        
        
        for(int i = 0; i < nSpaces; i++){
            if(!parkingCheck[i]){
                electricQueue.poll();
                nElectricQueue--;
                parking.set(i, electricCar);
                filledSpaces++;
                parkingCheck[i] = true;
                return;
            }
        }
        
    }
    
    public synchronized void saleElectrico(ElectricCar electricCar){
        
    }
    
    public boolean isInQueue(Object obj){
        
        if(obj.getClass() == ElectricCar.class){
            
            for(int i = 0; i < nElectricQueue; i++){
                if(electricQueue.contains(obj)){
                    return true;
                }
            }
        }else{
            for(int i = 0; i < nFuelQueue ; i++){
                if(fuelQueue.contains(obj)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isFull(){
        return (filledSpaces == 6);
    }
    
    
    
    
            
}
