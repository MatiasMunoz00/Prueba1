/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author paola
 */
public class MensajeDao {

    Conexion con;

    public MensajeDao() {
        this.con = new Conexion();
    }

    public ArrayList<Mensaje> getMensajes(int id,String name) {

        ArrayList<Mensaje> mensajes = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM mensajes WHERE id_usr_receptor ='" + id + "'";
            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                int id_usuario_receptor = id;
                Usuario re = new Usuario(id,name);
                String msj = resultados.getString("mensaje");
                int id_usr_em = resultados.getInt("id_usr_emisor");
                int id_mesaje = resultados.getInt("id_mensaje");
                String nombreEM = null;
                Usuario em = new Usuario(id_usr_em, nombreEM);
                mensajes.add(new Mensaje(id_mesaje,em,re,msj));
            }
            return mensajes;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }

    }
      public boolean insertarMensaje(Mensaje m) {
        Connection accesoBD = con.getConexion();
        try {
            String sql = "INSERT INTO mensaje(contenido,id_usr_emisor,id_usr_receptor) VALUES ('" + m.getContenido() + "','" + m.getEmisor() + "','" + m.getRemitente() + "')";
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql); //Execute update es para modificar la BD y meter info

            return true;
        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
            return false;
        }
    }

}
