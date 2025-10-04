/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ExamenParametrosDAO;
import Model.ExamenParametros;
import DAO.ConsecutivoDAO;
import Model.Consecutivo;
import DAO.ParametrosDAO;
import Model.Parametros;
import DAO.ExamenDAO;
import Model.Examen;
import View.frmExamenParametros;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zeth
 */
public class CtrlExamenParametros implements ActionListener {

    private final ExamenParametros model;
    private final ExamenParametrosDAO consult;
    private final frmExamenParametros vista;

    public CtrlExamenParametros(ExamenParametros model, ExamenParametrosDAO consult, frmExamenParametros vista) {
        this.model = model;
        this.consult = consult;
        this.vista = vista;
        this.vista.btnAdd.addActionListener(this);
        this.vista.btnUpdate.addActionListener(this);
        this.vista.btnDelete.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Examenes Parametros");
        vista.setLocationRelativeTo(null);
        getJTable();
        getOption();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int epId = Integer.parseInt(vista.txtId.getText().trim());
        int examId = vista.cmbExamen.getItemAt(vista.cmbExamen.getSelectedIndex());
        int paramId = vista.cmbExamen.getItemAt(vista.cmbExamen.getSelectedIndex());
        int consecId = vista.cmbExamen.getItemAt(vista.cmbExamen.getSelectedIndex());

        if (e.getSource() == vista.btnAdd) {
            model.setEp_id(epId);
            model.setEp_exa_id(examId);
            model.setEp_para_id(epId);
            model.setEp_consecutivo(consecId);

            //Ingreso de datos
            if (consult.add(model)) {
                JOptionPane.showMessageDialog(null, "Registro guardado.");
                getJTable();
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible realizar el registro.");
            }
        }
        if (e.getSource() == vista.btnUpdate) {
            model.setEp_id(epId);
            model.setEp_exa_id(examId);
            model.setEp_para_id(epId);
            model.setEp_consecutivo(consecId);

            //Ingreso de datos
            if (consult.update(model)) {
                JOptionPane.showMessageDialog(null, "Registro actualizado.");
                getJTable();
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible actualizar el registro.");
            }
        }
        if (e.getSource() == vista.btnDelete) {
            model.setEp_id(epId);

            //Ingreso de datos
            if (consult.delete(model)) {
                JOptionPane.showMessageDialog(null, "Registro anulado.");
                getJTable();
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible eliminar el registro.");
            }
        }
    }
    
    public void getOption() {
        ExamenDAO objExamen = new ExamenDAO();
        for (Examen examenItem : objExamen.getAllExamenes()) {
            vista.cmbExamen.addItem(examenItem.getId());
        }/*
        ConsecutivoDAO objConsecutivo = new ConsecutivoDAO();
        for (Consecutivo consecutivoItem : objExamen.getAllExamenes()) {
            vista.cmbExamen.addItem(consecutivoItem.getNit_empresa());
        }*/
        ParametrosDAO objParametro = new ParametrosDAO();
        for (Parametros paraemtroItem : objParametro.getAllParametros()) {
            vista.cmbParametro.addItem(paraemtroItem.getPara_id());
        }
    }
    
    public List<Parametros> getParamByExamId(int examenId) {
        return consult.getParamByExamId(examenId);
    }

    public void getJTable() {
        List<ExamenParametros> exaParametros = consult.getAllExamenParametros();
        DefaultTableModel model = (DefaultTableModel) vista.jtbList.getModel();
        model.setRowCount(0);

        for (ExamenParametros exParamItem : exaParametros) {
            model.addRow(new Object[]{exParamItem.getEp_id(), exParamItem.getEp_exa_id(), exParamItem.getEp_para_id(), exParamItem.getEp_consecutivo()});
        }
    }
}
