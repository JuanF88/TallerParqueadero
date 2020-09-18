/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.unicauca.solid.dip.domain.service;
import co.unicauca.solid.dip.domain.access.*;
import co.unicauca.solid.dip.domain.Vehiculo;
import co.unicauca.solid.dip.domain.Parqueo;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
/**
 *
 * @author camilo
 */
public class Parqueadero {

    private VehiculoRepository repositoryVehiculos;
    private ParqueaderoRepository repositoryParqueos;

    private Instant horaIngreso;
    private Instant horaSalida;

    private long secondsI;
    private long secondsS;
    
    private String horaL;
    private String horaS;
    
    private long minutos = 0;
    private long horas = 0;
    
    int iterador = 0;

    public Parqueadero(){
        repositoryVehiculos = new VehiculoRepository();
        repositoryParqueos = new ParqueaderoRepository();
    }
    
    public boolean saveVehiculo(Vehiculo newVehiculo,int identificador) {
       
        if (newVehiculo == null || newVehiculo.getVehiculoId() < 0 || newVehiculo.getTipo() == 0) {
            return false;
        }  
        repositoryVehiculos.saveVehiculo(newVehiculo);
         this.horaIngreso = calcularHoraLlegada();
         horaL = horaIngreso.toString();

        Parqueo newParqueo = new Parqueo(identificador,newVehiculo.getVehiculoId(),horaL,"Esta en parqueo");
        
        repositoryParqueos.saveParqueo(newParqueo);
        return true;

    }
    
    public boolean retirarVehiculo(int identificador){
        boolean encontrado=false;
        long tiempo=0;
        int tipo=0;
        int indice=0;
        List<Parqueo> parqueo = new ArrayList<>();
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos=repositoryVehiculos.listVehiculos();
        parqueo = listarParqueos();
        for(int i=0;i<parqueo.size();i++){
            if(parqueo.get(i).getHoraSalida().equals("Esta en parqueo")){
                if(parqueo.get(i).getVehiculoId() == identificador){
                    indice=i;
                    encontrado=true;
                    Instant horaDeLlegada = Instant.parse(parqueo.get(i).getHoraLlegada());
                    secondsI = horaDeLlegada.getEpochSecond();
                    //////////////         
                if(vehiculos.get(i).getVehiculoId() == identificador){
                    tipo=vehiculos.get(i).getTipo(); 
                } 
            }else{
                encontrado=false;
            }   
        }}
        if(encontrado){
            
            this.horaSalida = calcularHoraSalida();
            String hora = horaSalida.toString();
            
            secondsS = horaSalida.getEpochSecond();
            tiempo=calcularHoras(secondsS - secondsI);
            //Aqui va tiempo en vez de (secondsS - secondsI) ---->
            CalcularCostos objcostos=new CalcularCostos((secondsS - secondsI),tipo);
            System.out.println("Valor a pagar: "+objcostos.valorFactura());
            //Parqueo objp= new Parqueo(parqueo.get(indice).getParqueoId(),parqueo.get(indice).getVehiculoId(),parqueo.get(indice).getHoraLlegada(), hora);
            repositoryParqueos.updateParqueo(hora,parqueo.get(indice).getParqueoId());
            
        }else{
            System.out.println("El vehiculo no está en el parqueadero \n ");
        }
        
        return false;
    }

    public void listarVehiculos() {
        List<Parqueo> parqueos = new ArrayList<>();
        parqueos = repositoryParqueos.listParqueos();
        ImprimirLista(parqueos);
 
    }

    public List<Parqueo> listarParqueos() {
        List<Parqueo> parqueos = new ArrayList<>();
        parqueos = repositoryParqueos.listParqueos();
        return parqueos;
    }

    public void ImprimirLista(List listaVehiculos){
        if(listaVehiculos.size()>0){
            do{ 
                System.out.println(listaVehiculos.get(iterador)+"\n");
                iterador++; 
            }while(iterador<listaVehiculos.size());
        }else{
            System.out.println("No hay vehiculos ");
        }
        
        
    }
    public Instant calcularHoraLlegada(){
        horaIngreso=Instant.now();
        return horaIngreso;
    }

    public Instant calcularHoraSalida(){
        horaSalida=Instant.now();
        return horaSalida;
    }

    public long calcularHoras(long segundos){
        minutos = 0;
        horas = 0;
        for(int i=0; i < 59; i++){
            if(segundos > 59){
                minutos = minutos+1;
                segundos = segundos - 59;
            }
        }
        
        for(int i=0; i < 59; i++){
            if(minutos > 59){
                horas = horas+1;
                minutos = minutos - 59;
            }
        }
        return minutos; 
        
    }


}
