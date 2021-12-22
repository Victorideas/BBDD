/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareasbbdd;

/**
 *
 * @author instalador
 */
public class TareasBBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        conexion.crearTabla();
        conexion.insertar();
        conexion.mostrar();
        conexion.modificar();
        conexion.nuevosRegistros();
        conexion.cambiarEstado();

        conexion.cambiarFechas();
        conexion.mostrarFinal();
    }

}
