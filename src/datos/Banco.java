/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2_johannatorres;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge Pucha
 */
public class Banco {

    static ArrayList<Cuenta> listaCuenta = new ArrayList<Cuenta>();
    static String archivo = "";

    public static void agregar(Cuenta cuenta) {
        listaCuenta.add(cuenta);
    }

    public static ArrayList<Cuenta> getListaCuenta() {
        return listaCuenta;
    }

    public static void grabar() {
        PrintWriter pw = null;
        try {
            // Examen002: La ruta y el nombre del 'archivo' debe ser 
            // establecido dinamicamente por el usuario en el lugar adecuado
            FileWriter fw = new FileWriter(archivo, true);
            pw = new PrintWriter(fw);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Cuenta cuenta : listaCuenta) {
            String linea = "";
            if (cuenta instanceof CuentaAhorro) {
                linea = "Cuenta Ahorro" + ";" + cuenta.toString();
            }
            if (cuenta instanceof CuentaHipoteca) {
                linea = "Cuenta Hipoteca" + ";" + cuenta.toString();
            }
            if (cuenta instanceof CuentaPrestamo) {
                linea = "Cuenta Prestamo" + ";" + cuenta.toString();
            }
            pw.println(linea);
        }
        pw.close();
    }

    public static Cuenta buscarCuentaAhorro() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaAhorro cuentaAH = new CuentaAhorro(nombre);
        Cuenta c = (Cuenta) cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }

    public static Cuenta buscarCuentaHipoteca() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaHipoteca cuentaAH = new CuentaHipoteca(nombre);
        Cuenta c = (Cuenta) cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }

    public static void leerCuentas() {
        /* 
	 * Lee los datos desde un archivo de texto, crea objetos 'Cuenta'
	 * y los almacena en la lista 'listaCuenta'
         */
        // Examen 002: Completar este metodo
        BufferedReader b = null;
        try {
            String s;
            b = new BufferedReader(new FileReader(archivo));
            while ((s = b.readLine()) != null) {
                String cuentas[]=s.split(";");
                String cliente = cuentas[1].split(",")[0].replace("cliente=", "");
                String tipocliente = cuentas[1].split(",")[1].replace(" tipoCliente=", "");
                double balance = Double.parseDouble(cuentas[1].split(",")[2].replace(" balance=", ""));
                double taza = Double.parseDouble(cuentas[1].split(",")[3].replace(" tasaInteres=", ""));
                if(cuentas[0].equals("Cuenta Ahorro")){
                    
                Banco.listaCuenta.add(new CuentaAhorro(cliente, tipocliente, balance, balance));
                }else if(cuentas[0].equals("Cuenta Hipoteca")){
                    
                Banco.listaCuenta.add(new CuentaHipoteca(cliente, tipocliente, balance, balance));
                }if(cuentas[0].equals("Cuenta Prestamo")){
                    
                Banco.listaCuenta.add(new CuentaPrestamo(cliente, tipocliente, balance, balance));
                }
            }
            b.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                b.close();
            } catch (IOException ex) {
                Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
