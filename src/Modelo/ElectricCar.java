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
public class ElectricCar extends Thread{
    
    int id;
    char type;
    Parking parking;
    public ElectricCar(int id, Parking park){
        this.id = id;
        type = 'E';
        parking = park;
    }
    
    public ElectricCar(){
        id = -1;
        type = 'E';
        parking= null;
    }
    
    
    public int getIdent(){
        return id;
    }
    
    @Override
    public void run(){
        Random rd = new Random(System.nanoTime());
        try {
            parking.entraElectrico(this);
            
            sleep(rd.nextInt(60000));
            
            parking.saleElectrico(this);
            
        } catch (InterruptedException ex) {
            System.getLogger(ElectricCar.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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
       
        final ElectricCar other = (ElectricCar) obj;
        
        return this.id == other.id;
        
    }
}
