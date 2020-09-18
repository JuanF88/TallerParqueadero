package co.unicauca.solid.dip.domain;

/**
 *
 * @author camilo
 */
public class Vehiculo {

    private int vehiculoId;
    private int tipo;

    public Vehiculo(int vehiculoId, int tipo) {
        this.vehiculoId = vehiculoId;
        this.tipo = tipo;
    }

    public Vehiculo() {
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int prVehiculoId) {
        this.vehiculoId = prVehiculoId;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public String toString(){
        return "[Id: "+vehiculoId+" tipo: "+tipo+"]";
    }


}
