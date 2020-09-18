/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.unicauca.solid.dip.domain.access;

/**
 *
 * @author camilo
 */
public class CalcularCostos {
    private long tiempo;
    private int tipoVehiculo;
    private  double valorAlterado=0;
    private double valorFijo=0;

    public CalcularCostos(long  prmTiempo, int prtipoVehiculo){
        tiempo=prmTiempo;
        tipoVehiculo=prtipoVehiculo;
    }

    private double facturar(){
        VerificarTipo();
        double montoApagar=0;
        
        System.out.println("Tiempo: "+tiempo);
        if(tipoVehiculo != 3){
            if(tiempo<=60){
                montoApagar=valorFijo;
            }else{
                for(int i=0; tiempo<60; i++){
                    tiempo=tiempo-60;
                    montoApagar=montoApagar+valorAlterado;
                }    
                montoApagar = CalcularAux();
            }
        }else{
            if(tiempo<=720){
                montoApagar=valorFijo;
            }
            if(tiempo >= 720 && tiempo <= 1440){
                montoApagar=valorAlterado;
            }
            if(tiempo > 1440){
                montoApagar=CalcularAux();
            }
        }
        //float total=montoApagar+auxIncremento;
        return montoApagar;
    }
    public double CalcularAux(){
        double montoApagar=0;
        double subTotal = 0;

        double varAux=(double)(tiempo);
        double auxIncremento=(double)((valorAlterado/60)*varAux);
        subTotal=(double)(montoApagar+auxIncremento);
        montoApagar = 0;
        montoApagar = subTotal;
        return montoApagar;
    }

    public int valorFactura(){
        double valorCorrelativo=facturar();
        int valEntero=(int)valorCorrelativo;
        int residuo=valEntero%100;
        if(residuo!=0){
            valEntero+=100-residuo;
        }
        
        return valEntero;
    }

    public void VerificarTipo(){

        if(tipoVehiculo==1){
            valorFijo=1000;
            valorAlterado=500;
        }
        if(tipoVehiculo==2){
            valorFijo=2000;
            valorAlterado=1000;
        }
        if(tipoVehiculo==3){
            valorFijo=10000;
            valorAlterado=15000;
        }
    
    }

}
