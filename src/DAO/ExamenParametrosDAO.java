/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ExamenParametros;
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
public class ExamenParametrosDAO extends MySQLConnection {

    public List<ExamenParametros> getAllExamenParametros() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ExamenParametros> listExParam = new ArrayList<>();
        String sql = "SELECT ep_id, ep_exa_id, ep_para_id, ep_consecutivo FROM `examen_parametros`";
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ExamenParametros objExParam = new ExamenParametros();
                objExParam.setEp_id(rs.getInt("ep_id"));
                objExParam.setEp_exa_id(rs.getInt("ep_exa_id"));
                objExParam.setEp_para_id(rs.getInt("ep_para_id"));
                objExParam.setEp_consecutivo(rs.getInt("ep_consecutivo"));
                listExParam.add(objExParam);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            //con.desconectar();
        }
        return listExParam;
    }

    public List<Parametros> getParamByExamId(int examenId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Parametros> listParam = new ArrayList<>();
        String sql = "SELECT para_id, para_descripcion FROM parametros WHERE para_id IN ( SELECT ep_para_id FROM examen_parametros WHERE ep_exa_id = ? );";
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, examenId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Parametros objParam = new Parametros();
                objParam.setPara_id(rs.getInt("para_id"));
                objParam.setPara_descripcion(rs.getString("para_descripcion"));
                listParam.add(objParam);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            //con.desconectar();
        }
        return listParam;
    }

    public boolean add(ExamenParametros exaParametro) {
        PreparedStatement ps = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        String sql = "INSERT INTO examen_parametros (ep_id, ep_exa_id, ep_para_id, ep_consecutivo) VALUES (?, ?, ?, ?)";
        System.out.println(exaParametro);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, exaParametro.getEp_id());
            ps.setInt(2, exaParametro.getEp_exa_id());
            ps.setInt(3, exaParametro.getEp_para_id());
            ps.setInt(4, exaParametro.getEp_consecutivo());
            ps.execute();

            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            //con.desconectar();
        }
    }

    public boolean update(ExamenParametros exaParametro) {
        PreparedStatement ps = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        String sql = "UPDATE examen_parametros SET ep_exa_id=?, ep_para_id=?, ep_consecutivo=? WHERE ep_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, exaParametro.getEp_exa_id());
            ps.setInt(2, exaParametro.getEp_para_id());
            ps.setInt(3, exaParametro.getEp_consecutivo());
            ps.setInt(4, exaParametro.getEp_id());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean delete(ExamenParametros exaParametro) {
        PreparedStatement ps = null;
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        String sql = "DELETE FROM examen_parametros WHERE ep_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, exaParametro.getEp_id());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

}
