/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Veterinario;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Zeth
 */
public class VeterinarioDAO {

    public List<Veterinario> getAllVeterinarios() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Veterinario> listVeterinarios = new ArrayList<>();
        String sql = "SELECT vet_id, vet_nombre, vet_email, vet_tel, vet_veterinaria, vet_direccion FROM veterinario;";

        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Obteniendo lista de Veterinarios...");

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Veterinario vet = new Veterinario();
                vet.setVet_id(rs.getInt("vet_id"));
                vet.setVet_nombre(rs.getString("vet_nombre"));
                vet.setVet_email(rs.getString("vet_email"));
                vet.setVet_tel(rs.getString("vet_tel"));
                vet.setVet_veterinaria(rs.getString("vet_veterinaria"));
                vet.setVet_direccion(rs.getString("vet_direccion"));

                listVeterinarios.add(vet);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener veterinarios: " + e.getMessage());
        } finally {
            //con.desconectar();
        }

        return listVeterinarios;
    }

}
