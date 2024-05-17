/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoDatos;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Jin
 */
public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost/";
    private static final String DB = "universidadulp";
    private static final String USUARIO = "root";
    private static String PASSWORD = "";

    private static Connection connection;

    private Conexion() {
    }

    public static Connection getConexion() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL + DB + "?useLegacyDatetimeCode=false&serverTimezone=UTC" + "&user=" + USUARIO + "&password=" + PASSWORD);
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al conectarse a la Base de Datos." + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar los Drivers." + ex.getMessage());
            }
        }
        return connection;
    }
}
