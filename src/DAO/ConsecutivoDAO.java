/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Consecutivo;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Zeth
 */
public class ConsecutivoDAO extends MySQLConnection {/*
    public List<Consecutivo> getAllConsecutivo() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Consecutivo> listConsecutivo = new ArrayList<>();
        String sql = "SELECT para_id FROM `parametros`";
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Consecutivo objConsecutivo = new Consecutivo();
                objConsecutivo.setPara_id(rs.getInt("para_id"));
                listConsecutivo.add(objConsecutivo);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            //con.desconectar();
        }
        return listConsecutivo;
    }*/
}
