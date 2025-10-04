/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.TipoExamenDAO;
import Model.TipoExamen;
import java.util.List;

/**
 *
 * @author Zeth
 */
public class CtrlTipoExamen {
    public List<TipoExamen> getOptionTipo() {
        TipoExamenDAO objPTipo = new TipoExamenDAO();
        return objPTipo.getAllTipos();
    }
}
