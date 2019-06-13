/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author RLCR
 */
public class GestionBD {

    Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultados = null;
    String driver = "org.sqlite.JDBC";
    String nombrebd = "empresa";
    String url = "jdbc:sqlite:" + nombrebd;

    public void crearbd() {

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("creada");
    }

    public void crearTabla() {

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url);

            sentencia = conexion.createStatement();
            String sql = "CREATE TABLE CLIENTE("
                    + "ID           INT     PRIMARY KEY NOT NULL, "
                    + "NOMBRE       TEXTO   NOT NULL, "
                    + "APELLIDO     TEXTO   NOT NULL, "
                    + "SEXO         TEXTO   NOT NULL, "
                    + "ENTRETENCION TEXTO   NOT NULL)";

            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(" Tabla creada");
    }

    public void insert(int id, String nombre, String apellido,String sexo , 
                       String entretenimiento) {

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url);

            sentencia = conexion.createStatement();
            String sql = "INSERT INTO CLIENTE("
                    + "ID,NOMBRE,APELLIDO) VALUES("
                    + "'" + id + "','" + nombre + "','" + apellido + "','"+ sexo+"',"
                    + "'"+entretenimiento+"')";

            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
            System.out.println(" Insert creado");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void mostrarDatos(JTable tblMostrar) {
        
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url);

            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM CLIENTE";

            resultados = sentencia.executeQuery(sql);
            int fila = 0 ;
            
            while (resultados.next()) {                
                tblMostrar.setValueAt(resultados.getInt("ID"), fila, 0);
                tblMostrar.setValueAt(resultados.getString("NOMBRE"), fila, 1);
                tblMostrar.setValueAt(resultados.getString("APELLIDO"), fila, 2);
                fila++;
            }
            sentencia.close();
            conexion.close();
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        GestionBD gdb = new GestionBD();
        gdb.crearTabla();
    }
}//fin clase.
