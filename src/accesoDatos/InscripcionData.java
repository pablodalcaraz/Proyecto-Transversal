
package accesoDatos;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {
        private Connection con = null;
        private MateriaData matData;
        private AlumnoData aluData;

    public  InscripcionData() {
        con = Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion insc){
        String sql = "INSERT INTO inscripcion (nota) VALUES ( ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setDouble(1, insc.getNota());
          
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insc.setIdInscripcion(rs.getInt(1));;
                JOptionPane.showMessageDialog(null, "Inscripción añadida con exito.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción" + ex.getMessage());
        }

    }
//    public List<Inscripcion> obtenerInscripcion(){
//      
//    }
//    public List<Inscripcion> obtenerInscripcionPorAlumno(int id){
//        
//    }
//    public List<Materia> obtenerMateriasCursadas(int id){
//        
//    }
//    public List<Materia> obtenerMateriasNoCursadas(int id){
//        
//    }
//    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
//        
//    }
//    public void actualizarNota(int idAlumno, int idMateria,double nota){
//    
//    }
//    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
//        
//    }
}
