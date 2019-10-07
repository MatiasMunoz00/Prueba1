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
public class UsuarioDao {
    Conexion con;

    public UsuarioDao() {
        this.con = new Conexion();
    }

    public Usuario getUsuario(String nombre) {
        Connection accesoBD = con.getConexion();
        Usuario v = null;

        try {
            String sql = "SELECT * FROM usuario WHERE nombre='" + nombre + "'";
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
            while (resultados.first()) {
                int id_usuario = resultados.getInt("id_usuario");        
                String name = resultados.getString("nombre");
                v = new Usuario(id_usuario, name);
            }
            return v;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Usuario> getUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM usuarios;";

            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                int id_usuario = resultados.getInt("id_usuario");        
                String name = resultados.getString("nombre");
                usuarios.add(new Usuario(id_usuario, name));
            }
            return usuarios;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }

    }


    
}
