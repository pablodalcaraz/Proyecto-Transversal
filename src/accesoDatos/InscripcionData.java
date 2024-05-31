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
    private MateriaData matData = new MateriaData();
    private AlumnoData aluData = new AlumnoData();

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
            String sql = "SELECT * FROM inscripcion";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                insc.setNota(rs.getDouble("nota"));
                Alumno alum1 = aluData.buscarAlumno(rs.getInt("idAlumno"));
                Materia mate1 = matData.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alum1);
                insc.setMateria(mate1);
                inscripciones.add(insc);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla inscripcion " + ex.getMessage());
        }
        return inscripciones;
    }

    public List<Inscripcion> obtenerInscripcionPorAlumno(int id) {
        List<Inscripcion> inscripcion = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion JOIN alumno ON inscripcion.idAlumno = alumno.IdAlumno "
                + "WHERE inscripcion.idAlumno =?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripcion"));
                Alumno alum1 = aluData.buscarAlumno(rs.getInt("idAlumno"));
                Materia mate1 = matData.buscarMateria(rs.getInt("idMateria"));
                insc.setNota(rs.getDouble("nota"));
                insc.setAlumno(alum1);
                insc.setMateria(mate1);
                inscripcion.add(insc);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción" + ex.getMessage());
        }
        return inscripcion;
    }

    public List<Materia> obtenerMateriasCursadas(int id) {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT inscripcion.idMateria,nombre,anioMateria FROM inscripcion,materia"
                + " WHERE inscripcion.idMateria = materia.idMateria"
                + " AND inscripcion.idAlumno =?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("anioMateria"));
                materias.add(materia);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a  la tabla inscripcion " + ex.getMessage());

        }
        return materias;

    }

    public List<Materia> obtenerMateriasNoCursadas(int id) {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia WHERE estado = 1 AND idMateria not in "
                + " (SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("anioMateria"));
                materias.add(materia);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a  la tabla inscripcion " + ex.getMessage());

        }
        return materias;

    }

    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        String sql = " DELETE FROM inscripcion WHERE idalumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion borrada");

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a  la tabla inscripcion " + ex.getMessage());

        }

    }
    public void actualizarNota(int idAlumno, int idMateria,double nota){
        String sql = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
              ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Nota actualizada");

            }
            ps.close();
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a  la tabla inscripcion " + ex.getMessage());
        }
    }
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
       List<Alumno> alumnos = new ArrayList<>();
       String sql = "SELECT a.idAlumno,dni,apellido,nombre,fechaNacimiento,estado FROM inscripcion i , alumno a WHERE i.idAlumno = a.idAlumno AND i.idMateria = ? AND a.estado = 1 ";
               
                 try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
                alumnos.add(alumno);

            }  
               
                ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + ex.getMessage());
            
        }
                 return alumnos;
       
        
    }
//        
//    }
}
