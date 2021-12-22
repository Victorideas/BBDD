/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareasbbdd;

import static java.awt.Event.INSERT;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import static java.sql.JDBCType.BOOLEAN;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.accessibility.AccessibleRole.TABLE;

/**
 * @author Víctor
 */
public class Conexion {

    String ruta = "jdbc:mysql://localhost:3306/tareas";
    String nombre = "root";
    String contraseña = "";
    Connection conexion;

    //ResultSet rs;
    //Statement st;
    /**
     * Genera la conexion con la BBDD
     */
    public Conexion() {
        try {
            conexion = DriverManager.getConnection(ruta, nombre, contraseña);
            System.out.println("1. Conexion establecida.");
            // st = conexion.createStatement();
            //String consulta = "select * from usuario";         
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Creamos la tabla
     */
    public void crearTabla() {
        PreparedStatement stmt = null;
        try {
            stmt = conexion.prepareStatement("CREATE TABLE `tareas`.`tarea` (\n"
                    + "  `Id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Descripcion` VARCHAR(45) NOT NULL,\n"
                    + "  `Fecha_Inicio` DATE NOT NULL,\n"
                    + "  `Fecha_Fin` DATE NOT NULL,\n"
                    + "  `Finalizada` BOOLEAN NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`));");
            stmt.execute();
            System.out.println("2. Tabla creada");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Insertamos los registros
     */
    public void insertar() {

        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`) VALUES ('Comprar manzanas', '       2021-02-15', '2021-02-16', '1')");
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`) VALUES ('Estudiar examen', '        2021-05-02', '2021-05-18', '1')");
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`) VALUES ('Comprar billete Mexico', '2021-02-15', '2021-05-20', '1')");
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`) VALUES ('Ir a Mexico    ', '           2021-06-01', '2021-08-25', '1')");
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`) VALUES ('Revision del coche', '2021-04-08', '2021-04-16', '1')");
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`) VALUES ('Cunpleaños mama ', '       2021-05-01', '2021-05-01', '1')");
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`) VALUES ('Practica JDBC   ', '         2021-04-15', '2021-05-16', '1')");
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`) VALUES ('FCT             ', '2021-03-01', '2021-05-24', '1')");
            System.out.println("3. Datos insertados");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Muestra la tabla inicial
     */
    public void mostrar() {
        try {
            String consulta = "SELECT * FROM tareas.tarea";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            System.out.println("Id  Descripcion            Fecha_Inicio      Fecha_Fin      Finalizada");
            while (rs.next()) {

                int id = rs.getInt("id");
                String descripcion = rs.getString("Descripcion");
                Date inicio = rs.getDate("Fecha_Inicio");
                Date fin = rs.getDate("Fecha_Fin");
                boolean finalizada = rs.getBoolean("Finalizada");
                System.out.println(id + "   " + descripcion + "       " + inicio + "       " + fin + "      " + finalizada + "\n");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muestra la tabla final
     */
    public void mostrarFinal() {
        try {
            String consulta = "SELECT * FROM tareas.tarea";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            System.out.println("Id  Descripcion            Fecha_Inicio      Fecha_Fin      Finalizada      Anular tareas       Fecha real");
            while (rs.next()) {

                int id = rs.getInt("id");
                String descripcion = rs.getString("Descripcion");
                Date inicio = rs.getDate("Fecha_Inicio");
                Date fin = rs.getDate("Fecha_Fin");
                boolean finalizada = rs.getBoolean("Finalizada");
                boolean anular = rs.getBoolean("Anular_tarea");
                Date real = rs.getDate("Fecha_real");

                System.out.println(id + "   " + descripcion + "       " + inicio + "       " + fin + "      " + finalizada + "    " + anular + "   " + real + "\n");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Modificamos la tabla para añadir nuevas columnas
     */
    public void modificar() {
        Statement stmt = null;
        try {
            stmt = conexion.createStatement();
            stmt.execute("ALTER TABLE tareas.tarea ADD COLUMN Anular_Tarea BOOLEAN NOT NULL;");
            stmt.execute("ALTER TABLE tareas.tarea ADD COLUMN Fecha_real DATE;");
            System.out.println("5. Columnas añadidas");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Añadimos nuevos registros a la tabla
     */
    public void nuevosRegistros() {
        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`, `Anular_tarea`, `Fecha_real`) VALUES ('Vacaciones', '       2021-12-25', '2022-01-10', '0', '     0', '    2022-01-15');");
            stmt.executeUpdate("INSERT INTO `tareas`.`tarea` (`Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Finalizada`, `Anular_tarea`, `Fecha_real`) VALUES ('Repasar', '       2021-12-26', '2022-01-16', '0', '     0', '    2022-01-19');");
            System.out.println("6. Nuevos registros añadidos");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Modificamos mediante UPDATE un valor de la tabla
     */
    public void cambiarEstado() {

        String consulta = "UPDATE tareas.tarea SET Anular_tarea = '1' WHERE Id = '7';";
        PreparedStatement pr;
        try {
            pr = conexion.prepareStatement(consulta);
            pr.executeUpdate();
            System.out.println("7. Valor modificado");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Todas las tareas finalizadas añaden en la ultima columna la fecha de hoy.
     */
    public void cambiarFechas() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(dtf.format(LocalDateTime.now()));

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT Id FROM `tareas`.`tarea` WHERE Fecha_Fin < '2021-12-21';");
            while (rs.next()) {
                int codigo = rs.getInt("id");
                String consulta = "UPDATE tareas.tarea SET Fecha_real = '2021-12-21' WHERE Id = '" + codigo + "';";
                PreparedStatement pr;
                try {
                    pr = conexion.prepareStatement(consulta);
                    pr.executeUpdate();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            System.out.println("8. Fechas actualizadas");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
