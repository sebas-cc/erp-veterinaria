/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Mascota;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Zeth
 */
public class MascotaDAO {

    public List<Mascota> getAllMascotas() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Mascota> listMascotas = new ArrayList<>();
        String sql = "SELECT m.mas_id, m.mas_nombre, m.mas_edad, m.mas_propietario, m.mas_sexo, r.raza_descripcion FROM mascota AS m INNER JOIN raza AS r ON m.raza_id = r.raza_id;";

        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Obteniendo lista de Mascotas...");

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Mascota mas = new Mascota();
                mas.setMas_id(rs.getInt("mas_id"));
                mas.setMas_nombre(rs.getString("mas_nombre"));
                mas.setMas_edad(rs.getInt("mas_edad"));
                mas.setMas_propietario(rs.getString("mas_propietario"));
                mas.setMas_sexo(rs.getString("mas_sexo"));
                mas.setRaza(rs.getString("raza_descripcion"));

                listMascotas.add(mas);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener mascotas: " + e.getMessage());
        } finally {
            //con.desconectar();
        }

        return listMascotas;
    }

}
