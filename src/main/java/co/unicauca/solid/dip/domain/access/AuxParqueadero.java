/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.unicauca.solid.dip.domain.access;
import java.time.Instant;
/**
 *
 * @author camilo
 */
public class AuxParqueadero {
    private float tarifa;
    private Instant horaIngreso;
    private Instant horaSalida;

    public AuxParqueadero (){
        horaIngreso=Instant.now();
        horaSalida=Instant.now();     
    }
    
    public Instant getHoraIngreso(){
    return horaIngreso;
    }
    
    public Instant getHoraSalida(){
    return horaSalida;
    }


}
