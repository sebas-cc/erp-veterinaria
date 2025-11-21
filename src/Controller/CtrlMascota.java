/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.MascotaDAO;
import Model.Mascota;
import java.util.List;

/**
 *
 * @author Zeth
 */
public class CtrlMascota {
    public List<Mascota> getAllMascotas() {
        MascotaDAO objVeterinario = new MascotaDAO();
        return objVeterinario.getAllMascotas();
    }
}
