
package accesoDatos;

import entidades.Materia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import  java.sql.*;

public class MateriaData {
     private Connection con = null;

    public MateriaData() {
         con = Conexion.getConexion();
    }
     
    public void guardarMateria(Materia materia){
         String sql = "INSERT INTO materia (nombre, anioMateria, estado) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isEstado());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Materia añadida con exito.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia" + ex.getMessage());
        }
    }
//    public Materia buscarMateria(int id){
//        
//    }
//    public void modificarMateria(Materia materia){
//        
//    }
//    public void eliminarMateria(int id){
//        
//    }
//    public List<Materia> listarMaterias(){
//        
//    }
}
