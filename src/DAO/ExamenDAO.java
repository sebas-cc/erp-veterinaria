/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Examen;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Zeth
 */
public class ExamenDAO extends MySQLConnection {
    public List<Examen> getAllExamenes() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Examen> listExamenes = new ArrayList<>();
        String sql = "SELECT e.exa_id, e.exa_descripcion, e.exa_valor, te.tipoExa_descripcion AS tip_exa, e.exa_estado FROM examen e JOIN tipo_examen te ON e.exa_tipo = te.tipoExa_id;";
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Obteniendo Examenes...");
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Examen objExamen = new Examen();
                objExamen.setId(rs.getInt("exa_id"));
                objExamen.setDescripcion(rs.getString("exa_descripcion"));
                objExamen.setValor(rs.getInt("exa_valor"));
                objExamen.setTipo(rs.getString("tip_exa"));
                objExamen.setEstado(rs.getString("exa_estado"));
                listExamenes.add(objExamen);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            //con.desconectar();
        }
        return listExamenes;
    }

    public boolean add(Examen examen) {
        PreparedStatement ps = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Agregando Examen...");
        String sql = "insert into examen(exa_id, exa_descripcion, exa_valor, exa_tipo, exa_estado) values(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, examen.getId());
            ps.setString(2, examen.getDescripcion());
            ps.setInt(3, examen.getValor());
            ps.setString(4, examen.getTipo());
            ps.setString(5, examen.getEstado());
            ps.execute();

            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            //con.desconectar();
        }
    }

    public boolean update(Examen examen) {
        PreparedStatement ps = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Actualizando Examen...");
        String sql = "UPDATE examen SET exa_descripcion=?, exa_valor=?, exa_tipo=?, exa_estado=? WHERE exa_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, examen.getDescripcion());
            ps.setInt(2, examen.getValor());
            ps.setString(3, examen.getTipo());
            ps.setString(4, examen.getEstado());
            ps.setInt(5, examen.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean delete(Examen examen) {
        PreparedStatement ps = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Anulando Examen...");
        //String sql = "DELETE FROM examen WHERE exa_id=? ";
        String sql = "UPDATE examen SET exa_estado='Anulado' WHERE exa_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, examen.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean compareDescripcion(Examen examen) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        String sql = "SELECT exa_descripcion FROM `examen` WHERE exa_descripcion=? AND exa_id!=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, examen.getDescripcion());
            ps.setInt(2, examen.getId());
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean getExamParamById(Examen examen) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        String sql = "SELECT ep_exa_id FROM `examen_parametros` WHERE ep_exa_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, examen.getId());
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}
