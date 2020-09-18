
package co.unicauca.solid.dip.domain.access;

import co.unicauca.solid.dip.domain.Vehiculo;
import co.unicauca.solid.dip.domain.service.Parqueadero;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilo
 */
public class VehiculoRepository {
private Connection conn;
private List<Vehiculo> vehiculos;
    public VehiculoRepository() {
        initDatabase();
        vehiculos=new ArrayList<>();
    }

    public boolean saveVehiculo(Vehiculo newVehiculo) {

        try {
            //Validate product
            if (newVehiculo == null || newVehiculo.getVehiculoId() < 0 || newVehiculo.getTipo() == 0) {
                return false;
            }
            String sql = "INSERT INTO Vehiculo ( VehiculoId, Tipo ) "
                  + "VALUES ( ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newVehiculo.getVehiculoId());
            pstmt.setInt(2, newVehiculo.getTipo());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Parqueadero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Vehiculo> listVehiculos() {
        //List<Vehiculo> vehiculos = new ArrayList<>();
        try {

            String sql = "SELECT VehiculoId, tipo FROM Vehiculo";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vehiculo newVehiculo = new Vehiculo();
                newVehiculo.setVehiculoId(rs.getInt("VehiculoId"));
                newVehiculo.setTipo(rs.getInt("Tipo"));
                vehiculos.add(newVehiculo);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Parqueadero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehiculos;
    }

    public List<Vehiculo> getLista(){
        return vehiculos;
    }

    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Vehiculo (\n"
                + "	VehiculoId integer PRIMARY KEY,\n"
                + "	Tipo integer\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Parqueadero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() {
        // SQLite connection string
        //String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(Parqueadero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
