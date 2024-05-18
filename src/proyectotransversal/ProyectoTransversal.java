/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectotransversal;

import accesoDatos.AlumnoData;
import entidades.Alumno;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Pablo
 */
public class ProyectoTransversal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlumnoData alumno=new AlumnoData();
        Alumno josias=new Alumno(4222333, "Torres", "Josías Ramón", LocalDate.of(2023, Month.DECEMBER, 10), true);
//        alumno.guardarAlumno(josias);
//        Alumno fenando=new Alumno(45231456, "López", "Fernando Omar", LocalDate.of(2022, Month.MARCH, 15), true);
//        alumno.guardarAlumno(fenando);
//        Alumno fernado=new Alumno(10, 46000313, "López", "Carlos Fernando", LocalDate.of(2002, Month.MARCH, 15), true);
//        alumno.modificarAlumno(fernado);
          Alumno fernando= alumno.buscarAlumnoPorDni(46000313);
          System.out.println("El alumno buscado por dni es: "+fernando.toString());
          alumno.buscarAlumno(1);
          System.out.println("El alumno buscado por id es: "+josias.toString());
          
          System.out.print("Lista de alumnos= ");
          alumno.listarAlumnos();
          
    }
    
}
