package co.unicauca.solid.dip.domain.access;

import co.unicauca.solid.dip.domain.Vehiculo;
import co.unicauca.solid.dip.domain.Parqueo;
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
public class ParqueaderoRepository {
private Connection conn;

private List<Parqueo> parqueos;
    public ParqueaderoRepository() {
        initDatabase();
        parqueos=new ArrayList<>();
    }

    public boolean saveParqueo(Parqueo newParqueo) {

        try {
            //Validate product
            if (newParqueo == null || newParqueo.getVehiculoId() < 0 || newParqueo.getHoraLlegada() == "" || newParqueo.getHoraSalida() == "" || newParqueo.getParqueoId() < 0) {
                return false;
            }
            String sql = "INSERT INTO Parqueo ( ParqueoId, VehiculoId, HoraLlegada, HoraSalida ) "
                  + "VALUES ( ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, newParqueo.getParqueoId());
            pstmt.setInt(2, newParqueo.getVehiculoId());
            pstmt.setString(3, newParqueo.getHoraLlegada());
            pstmt.setString(4, newParqueo.getHoraSalida());

            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Parqueadero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean updateParqueo(String hora, int prId ){
        try{
           // int id=newParqueo.getVehiculoId();
            String sql = "UPDATE Parqueo SET  HoraSalida = ? "
                         + "WHERE  ParqueoId = ?";
                  

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, hora);
            pstmt.setInt(2, prId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Parqueadero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;




    }

    public List<Parqueo> listParqueos() {
        //List<Vehiculo> vehiculos = new ArrayList<>();
        try {

            String sql = "SELECT ParqueoId, VehiculoID, HoraLlegada, HoraSalida FROM Parqueo";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Parqueo newParqueo = new Parqueo();
                newParqueo.setParqueoId(rs.getInt("ParqueoId"));
                newParqueo.setVehiculoId(rs.getInt("VehiculoId"));
                newParqueo.setHoraLlegada(rs.getString("HoraLlegada"));
                newParqueo.setHoraSalida(rs.getString("HoraSalida"));
                parqueos.add(newParqueo);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Parqueadero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parqueos;
    }

    public List<Parqueo> getLista(){
        return parqueos;
    }

    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Parqueo (\n"
                + "	ParqueoId integer PRIMARY KEY,\n"
                + "	VehiculoId integer,\n"
                + "	HoraLlegada text,\n"
                + "	HoraSalida text\n"
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
