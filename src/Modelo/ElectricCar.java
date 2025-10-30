/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author rubco
 */
public class ElectricCar extends Thread{
    
    int id;
    char type;
    public ElectricCar(int id){
        this.id = id;
        type = 'E';
    }
    
    public ElectricCar(){
        id = -1;
        type = 'E';
    }
    
    
    public int getIdent(){
        return id;
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
       
        final ElectricCar other = (ElectricCar) obj;
        
        return this.id == other.id;
        
    }
}
