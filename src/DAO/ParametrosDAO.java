/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Parametros;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Zeth
 */
public class ParametrosDAO extends MySQLConnection {

    public List<Parametros> getAllParametros() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Parametros> listParametros = new ArrayList<>();
        String sql = "SELECT para_id, para_descripcion, para_unidad, para_estado, usu_crea, fecha_crea, para_usu_anu, para_fecha_anu, para_formula, para_referencia1, para_referencia2 FROM parametros";
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Obteniendo Parametros...");
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Parametros objParametros = new Parametros();
                objParametros.setPara_id(rs.getInt("para_id"));
                objParametros.setPara_descripcion(rs.getString("para_descripcion"));
                objParametros.setPara_unidad(rs.getString("para_unidad"));
                objParametros.setPara_estado(rs.getString("para_estado"));
                objParametros.setUsu_crea(rs.getString("usu_crea"));
                objParametros.setFecha_crea(rs.getString("fecha_crea"));
                objParametros.setPara_usu_anu(rs.getString("para_usu_anu"));
                objParametros.setPara_fecha_anu(rs.getString("para_fecha_anu"));
                objParametros.setPara_formula(rs.getString("para_formula"));
                objParametros.setPara_referencia1(rs.getString("para_referencia1"));
                objParametros.setPara_referencia2(rs.getString("para_referencia2"));
                listParametros.add(objParametros);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            //con.desconectar();
        }
        return listParametros;
    }

    public boolean add(Parametros parametro) {
        PreparedStatement ps = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Agregando Parametro...");
        String sql = "INSERT INTO parametros(para_id, para_descripcion, para_unidad, para_estado, para_formula, para_referencia1, para_referencia2) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, parametro.getPara_id());
            ps.setString(2, parametro.getPara_descripcion());
            ps.setString(3, parametro.getPara_unidad());
            ps.setString(4, parametro.getPara_estado());
            ps.setString(5, parametro.getPara_formula());
            ps.setString(6, parametro.getPara_referencia1());
            ps.setString(7, parametro.getPara_referencia2());

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            //con.desconectar();
        }
    }

    public boolean update(Parametros parametro) {
        PreparedStatement ps = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Actualizando Parametro...");
        String sql = "UPDATE parametros SET para_descripcion=?, para_unidad=?, para_estado=?, usu_crea=?, para_usu_anu=?, para_fecha_anu=?, para_formula=?, para_referencia1=?, para_referencia2=? WHERE para_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, parametro.getPara_descripcion());
            ps.setString(2, parametro.getPara_unidad());
            ps.setString(3, parametro.getPara_estado());
            ps.setString(4, parametro.getPara_formula());
            ps.setString(5, parametro.getPara_referencia1());
            ps.setString(6, parametro.getPara_referencia2());
            ps.setInt(7, parametro.getPara_id());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean delete(Parametros parametro) {
        PreparedStatement ps = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Eliminando Parametro...");
        String sql = "DELETE FROM parametros WHERE para_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, parametro.getPara_id());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}
