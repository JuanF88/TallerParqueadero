
package co.unicauca.solid.dip.domain.main;
import co.unicauca.solid.dip.domain.access.*;
import co.unicauca.solid.dip.domain.Vehiculo;
import co.unicauca.solid.dip.domain.service.Parqueadero;
import java.util.Scanner;

/**
 *
 * @author camilo
 */
public class ClientMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Service service = new Service();
        Parqueadero objP= new Parqueadero();
        Scanner sc=new Scanner(System.in);
        int iterador=1;
        int opcion=0;
        
        do{
            menu();
            System.out.println("Ingrese una opcion ");
            opcion=sc.nextInt();
            switch(opcion){
                case 1:
                    int idVehiculo=0;
                    int tipo=0;
                    System.out.println("Ingrese el ID del vehiculo ");
                    idVehiculo=sc.nextInt();
                    System.out.println("Tipo de Vehiculo: 1(moto), 2(carr0), 3(camion)");
                    tipo=sc.nextInt();
                    if(tipo>0 && tipo<4){
                        Vehiculo newVehiculo = new Vehiculo(idVehiculo,tipo);
                        objP.saveVehiculo(newVehiculo,iterador);
                        iterador++;
                    }else{
                        System.out.println(" Error, tipo  no válido!!\n ");
                    }
                    break;
                case 2:
                    int identificador=0;
                    System.out.println("Ingrese el ID del vehiculo a retirar ");
                    identificador=sc.nextInt();
                    objP.retirarVehiculo(identificador);
                    break;
                case 3:
                    objP.listarVehiculos();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                System.out.println("Elija un aopción válida ");
                    break;
            }
        }while(opcion!=4);
    }

    public static void menu(){
        System.out.println("1.. Registrar vehiculo");
        System.out.println("2.. Retirar vehiculo");
        System.out.println("3.. Imprimir estado de parqueadero");
        System.out.println("4.. salir");
    }
    
}
