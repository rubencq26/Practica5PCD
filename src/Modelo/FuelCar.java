/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author rubco
 */
public class FuelCar implements Runnable{
    
    int id;
    char type;
    public FuelCar(int id){
        this.id = id;
        type = 'F';
    }
    
    public FuelCar(){
        this.id = -1;
        type = 'F';
    }
    
    @Override
    public void run(){
        
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
