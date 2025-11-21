/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.VeterinarioDAO;
import Model.Veterinario;
import java.util.List;

/**
 *
 * @author Zeth
 */
public class CtrlVeterinario {
    public List<Veterinario> getAllVeterinarios() {
        VeterinarioDAO objVeterinario = new VeterinarioDAO();
        return objVeterinario.getAllVeterinarios();
    }
}
