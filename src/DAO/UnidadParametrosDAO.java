/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.UnidadParametros;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Zeth
 */
public class UnidadParametrosDAO extends MySQLConnection {
    public List<UnidadParametros> getAllUnidadParametroses() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UnidadParametros> listUnidadParametros = new ArrayList<>();
        String sql = "SELECT un_id, un_descripcion FROM unidad_parametro;";
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Obteniendo UnidadParametros...");
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UnidadParametros objUnidadParametros = new UnidadParametros();
                objUnidadParametros.setUn_id(rs.getInt("un_id"));
                objUnidadParametros.setUn_descripcion(rs.getString("un_descripcion"));
                listUnidadParametros.add(objUnidadParametros);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            //con.desconectar();
        }
        return listUnidadParametros;
    }
}
