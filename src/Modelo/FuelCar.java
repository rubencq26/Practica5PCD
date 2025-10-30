/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Parking;
import java.util.Random;

/**
 *
 * @author rubco
 */
public class FuelCar implements Runnable{
    
    private final int id;
    private final char type;
    private final Parking parking;
    public FuelCar(int id, Parking park){
        this.id = id;
        type = 'F';
        parking = park;
    }
    
    public FuelCar(){
        this.id = -1;
        type = 'F';
        parking= null;
    }
    
    @Override
    public void run(){
        Random rd = new Random(System.nanoTime());
        
        try {
            parking.entraCombustion(this);
            
            Thread.sleep(rd.nextInt(60000));
            
            parking.saleCombustion(this);
            
        } catch (InterruptedException ex) {
            System.getLogger(FuelCar.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
     @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        

        if(obj == null){
            return false;
        }
        
        if(getClass() != obj.getClass()){
            return false;
        }
       
        final FuelCar other = (FuelCar) obj;
        
        return this.id == other.id;
        
    }
}
