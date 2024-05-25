package proyectotransversal;

import accesoDatos.AlumnoData;
import accesoDatos.InscripcionData;
import accesoDatos.MateriaData;
import entidades.Alumno;
import entidades.Materia;
import entidades.Inscripcion;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ProyectoTransversal {

    public static void main(String[] args) {
        AlumnoData alumno = new AlumnoData();
        Alumno josias = new Alumno(1, 4222333, "Torres", "Josías Ramón", LocalDate.of(2023, Month.DECEMBER, 10), true);
//        alumno.guardarAlumno(josias);
        Alumno fernando = new Alumno(2, 45231456, "López", "Fernando Omar", LocalDate.of(2022, Month.MARCH, 15), false);
//        alumno.guardarAlumno(fernando);

//        Alumno fernando=new Alumno(10, 46000313, "López", "Carlos Fernando", LocalDate.of(2002, Month.MARCH, 15), true);
//        alumno.modificarAlumno(fernando);
//        Alumno fernando = alumno.buscarAlumnoPorDni(46000313);
//        System.out.println("El alumno buscado por dni es: " + fernando.toString());
//        alumno.buscarAlumno(1);
//        System.out.println("El alumno buscado por id es: " + josias.toString());
//        System.out.print("Lista de alumnos= ");
//            List<Alumno> a = new ArrayList<>();
//            a = alumno.listarAlumnos();
//            for (Alumno alumnos : a) {
//                    System.out.println(alumnos.toString());
//            }
//        System.out.println("ELIMINAR ALUMNO");
//        alumno.eliminarAlumno(2);
        MateriaData materia = new MateriaData();
//        Materia mat1=new Materia(1,"laboratorio", 2024, true);
//        materia.guardarMateria(mat1);
//        materia.buscarMateria(1);
        Materia mat1 = new Materia(1, "matematica", 2023, true);
//        materia.modificarMateria(mat1);
//        materia.eliminarMateria(1);
//        List<Materia> listamateria = new ArrayList<>();
//        listamateria = materia.listarMaterias();
//        for(Materia materia1 : listamateria){
//            System.out.println(materia1.toString());
//        }
        InscripcionData inscripcion = new InscripcionData();
        Inscripcion insc1 = new Inscripcion(2, josias, mat1, 5);
//        inscripcion.guardarInscripcion(insc1);
        List<Inscripcion> listainscripcion = new ArrayList<>();
//        listainscripcion = inscripcion.obtenerInscripcion();
//        for (Inscripcion inscripcion1 : listainscripcion) {
//            System.out.println(inscripcion1.getIdInscripcion());
//            System.out.println(inscripcion1.getAlumno().getNombre());
//            System.out.println(inscripcion1.getMateria().getNombre());
//            System.out.println(inscripcion1.getNota());
//        }
        List<Materia> listamateria = new ArrayList<>();
//        listamateria = inscripcion.obtenerMateriasCursadas(1);
//        for(Materia materia1 : listamateria){
//            System.out.println(materia1.toString());
//        }
//        listamateria = inscripcion.obtenerMateriasNoCursadas(1);
//        for(Materia materia1 : listamateria){
//            System.out.println(materia1.toString());
//        }

        inscripcion.borrarInscripcionMateriaAlumno(1, 1);

    }

}
