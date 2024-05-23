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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {

    private Connection con = null;
    private MateriaData matData;
    private AlumnoData aluData;

    public InscripcionData() {
        con = Conexion.getConexion();
    }

    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion (nota,idAlumno, idMateria) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());

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

    public List<Inscripcion> obtenerInscripcion() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inscripcion  ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripcion"));
                insc.getAlumno().setIdAlumno(rs.getInt("idAlumno"));
                insc.getMateria().setIdMateria(rs.getInt("idMateria"));
                insc.setNota(rs.getDouble("nota"));

                inscripciones.add(insc);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla inscripcion " + ex.getMessage());
        }
        return inscripciones;
    }

    public List<Inscripcion> obtenerInscripcionPorAlumno(int id) {
        List<Inscripcion> insc = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion JOIN alumno ON inscripcion.idAlumno == alumno.IdAlumno "
                + "WHERE inscripcion.idAlumno =?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion insc1 = new Inscripcion();
                insc1.setIdInscripcion(rs.getInt("idInscripcion"));
                insc1.getAlumno().setIdAlumno(rs.getInt("idAlumno"));
                insc1.getMateria().setIdMateria(rs.getInt("idMateria"));
                insc1.setNota(rs.getDouble("nota"));

                insc.add(insc1);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción" + ex.getMessage());
        }
        return insc;
        }
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
