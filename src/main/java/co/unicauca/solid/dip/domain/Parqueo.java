package co.unicauca.solid.dip.domain;
/**
 *
 * @author Libardo, Julio
 */
public class Parqueo {

    private int ParqueoId;
    private int VehiculoId;
    private String HoraLlegada;
    private String HoraSalida;

    public Parqueo(int ParqueoId, int VehiculoId, String HoraLlegada, String HoraSalida) {
        this.ParqueoId = ParqueoId;
        this.VehiculoId = VehiculoId;
        this.HoraLlegada = HoraLlegada; 
        this.HoraSalida = HoraSalida;
    }

    public Parqueo() {
    }


    public int getParqueoId() {
        return ParqueoId;
    }
    public void setParqueoId(int prParqueoId) {
        this.ParqueoId = prParqueoId;
    }


    public int getVehiculoId() {
        return VehiculoId;
    }
    public void setVehiculoId(int VehiculoId) {
        this.VehiculoId = VehiculoId;
    }


    public String getHoraLlegada() {
        return HoraLlegada;
    }
    public void setHoraLlegada(String HoraLlegada) {
        this.HoraLlegada = HoraLlegada;
    }


    public String getHoraSalida() {
        return HoraSalida;
    }
    public void setHoraSalida(String HoraSalida) {
        this.HoraSalida = HoraSalida;
    }


    public String toString(){
        return "[Id Parqueo: "+ParqueoId+" Id Vehiculo: "+VehiculoId+" Hora de Llegada: "+HoraLlegada+" Hora de Salida: "+HoraSalida+"]";
    }


}
