/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.TipoExamen;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Zeth
 */
public class TipoExamenDAO extends MySQLConnection {

    public List<TipoExamen> getAllTipos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TipoExamen> listTipos= new ArrayList<>();
        String sql = "SELECT tipoExa_id , tipoExa_descripcion, tipoExa_estado FROM tipo_examen";
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoExamen objTipo = new TipoExamen();
                objTipo.setTipoExaId(rs.getInt("tipoExa_id"));
                objTipo.setTipoExaDescripcion(rs.getString("tipoExa_descripcion"));
                objTipo.setTipoExaEstado(rs.getString("tipoExa_estado"));
                listTipos.add(objTipo);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            //con.desconectar();
        }
        return listTipos;
    }
}
