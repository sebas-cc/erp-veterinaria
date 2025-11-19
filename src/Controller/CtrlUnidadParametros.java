/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.UnidadParametros;
import DAO.UnidadParametrosDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zeth
 */
public class CtrlUnidadParametros {
    
    private final UnidadParametrosDAO consult = new UnidadParametrosDAO();

    public CtrlUnidadParametros() {
    }    
    
    public List<UnidadParametros> getAllUnidadParametroses() {
        return consult.getAllUnidadParametroses();
    }
}
