/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ParametrosDAO;
import Model.Parametros;
import View.frmParametros;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zeth
 */
public class CtrlParametros implements ActionListener {

    private final Parametros model;
    private final ParametrosDAO consult;
    private final frmParametros vista;

    public CtrlParametros (Parametros model, ParametrosDAO consult, frmParametros vista) {
        this.model = model;
        this.consult = consult;
        this.vista = vista;
        this.vista.btnAdd.addActionListener(this);
        this.vista.btnUpdate.addActionListener(this);
        this.vista.btnDelete.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Parametros");
        vista.setLocationRelativeTo(null);
        getJTable();
    }
    
    public  List<Parametros> getOptionParam(){
        ParametrosDAO objParam = new ParametrosDAO();
        return objParam.getAllParametros();
    }

    @Override
    
    
    public void actionPerformed(ActionEvent e) {
        try {
        int paraId = Integer.parseInt(vista.txtId.getText().trim());
        String paraDescripcion = vista.txtDescripcion.getText().trim();
        String paraUnidad = vista.txtUnidad.getText().trim();
        String paraEstado = vista.cmbEstado.getSelectedItem().toString();
        String paraFormula = vista.txtFormula.getText().trim();
        String paraReferencia1 = vista.txtReferencia1.getText().trim();
        String paraReferencia2 = vista.txtReferencia2.getText().trim();

        if (e.getSource() == vista.btnAdd) {
            model.setPara_id(paraId);
            model.setPara_descripcion(paraDescripcion);
            model.setPara_unidad(paraUnidad);
            model.setPara_estado(paraEstado);
            model.setPara_formula(paraFormula);
            model.setPara_referencia1(paraReferencia1);
            model.setPara_referencia2(paraReferencia2);

            //Ingreso de datos
            if (consult.add(model)) {
                JOptionPane.showMessageDialog(null, "Registro guardado.");
                getJTable();
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible realizar el registro.");
            }
        }
        if (e.getSource() == vista.btnUpdate) {
            model.setPara_id(paraId);
            model.setPara_descripcion(paraDescripcion);
            model.setPara_unidad(paraUnidad);
            model.setPara_estado(paraEstado);
            model.setPara_formula(paraFormula);
            model.setPara_referencia1(paraReferencia1);
            model.setPara_referencia2(paraReferencia2);

            //Ingreso de datos
            if (consult.update(model)) {
                JOptionPane.showMessageDialog(null, "Registro actualizado.");
                getJTable();
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible actualizar el registro.");
            }
        }
        if (e.getSource() == vista.btnDelete) {
            model.setPara_id(paraId);

            if (consult.delete(model)) {
                JOptionPane.showMessageDialog(null, "Registro anulado.");
                getJTable();
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible eliminar el registro.");
            }
        }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El campo ID y valor deben ser un número entero válido.");
            return;
        }
    }
    
    public void getJTable() {
        List<Parametros> parametrosList = consult.getAllParametros();
        DefaultTableModel model = (DefaultTableModel) vista.jtbList.getModel();
        model.setRowCount(0);

        for (Parametros parametro : parametrosList) {
            model.addRow(new Object[]{parametro.getPara_id(), parametro.getPara_descripcion(), parametro.getPara_unidad(), parametro.getPara_estado(),parametro.getUsu_crea(),parametro.getFecha_crea(), parametro.getPara_usu_anu(),parametro.getPara_fecha_anu(),parametro.getPara_formula(),parametro.getPara_referencia1(),parametro.getPara_referencia2()
            });
        }
    }

}
