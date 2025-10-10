/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ExamenDAO;
import Model.Examen;
import View.frmExamen;
import DAO.ExamenParametrosDAO;
import Model.ExamenParametros;
import Model.Parametros;
import DAO.ParametrosDAO;
import View.frmParametros;
import Model.TipoExamen;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zeth
 */
public class CtrlExamen implements ActionListener {

    private final Examen model;
    private final ExamenDAO consult;
    private final frmExamen vista;

    public CtrlExamen(Examen model, ExamenDAO consult, frmExamen vista) {
        this.model = model;
        this.consult = consult;
        this.vista = vista;
        this.vista.btnAdd.addActionListener(this);
        this.vista.btnUpdate.addActionListener(this);
        this.vista.btnDelete.addActionListener(this);
        this.vista.btnAddParam.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Examenes");
        vista.setLocationRelativeTo(null);
        getJTable();
        getOptionTipo();
        getOptionParams();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int id = Integer.parseInt(vista.txtId.getText().trim());
            int valor = Integer.parseInt(vista.txtValue.getText().trim());
            String descripcion = vista.txtNombre.getText().trim();
            String tipo = vista.cmbType.getSelectedItem().toString();
            String estado = vista.cmbState.getSelectedItem().toString();

            if (e.getSource() == vista.btnAdd) {
                model.setId(id);
                model.setDescripcion(descripcion);
                model.setValor(valor);
                model.setTipo(tipo);
                model.setEstado("Activo");
                vista.cmbState.setEnabled(false);

                //Validaciones
                if (!addValidation(id, valor, descripcion, tipo, estado)) {
                    return;
                }

                //Ingreso de datos
                if (consult.add(model)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado.");
                    getJTable();
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible realizar el registro.");
                }
                vista.cmbState.setEnabled(true);
            }
            if (e.getSource() == vista.btnUpdate) {
                model.setId(id);
                model.setDescripcion(descripcion);
                model.setValor(valor);
                model.setTipo(tipo);
                model.setEstado(estado);

                //Validaciones
                if (!updateValidation(id, valor, descripcion, tipo, estado)) {
                    return;
                }

                //Ingreso de datos
                if (consult.update(model)) {
                    JOptionPane.showMessageDialog(null, "Registro actualizado.");
                    getJTable();
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible actualizar el registro.");
                }
            }
            if (e.getSource() == vista.btnDelete) {
                model.setId(id);

                //Validaciones
                if (!deleteValidation(estado)) {
                    return;
                }

                //Ingreso de datos
                if (consult.delete(model)) {
                    JOptionPane.showMessageDialog(null, "Registro anulado.");
                    getJTable();
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible eliminar el registro.");
                }
            }
            if (e.getSource() == vista.btnAddParam) {
                Parametros objParam = vista.cmbParam.getItemAt(vista.cmbParam.getSelectedIndex());
                addParam(id, objParam);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El campo ID y valor deben ser un número entero válido.");
            return;
        }
    }

    private boolean addValidation(int id, int valor, String descripcion, String tipo, String estado) {
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía.");
            return false;
        }
        if (valor <= 0) {
            JOptionPane.showMessageDialog(null, "El valor no puede ser menor a cero.");
            return false;
        }
        if (tipo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El tipo no puede estar vacío.");
            return false;
        }
        if (consult.compareDescripcion(model)) {
            JOptionPane.showMessageDialog(null, "La descripción no puede repetirse.");
            return false;
        }

        return true;
    }

    private boolean updateValidation(int id, int valor, String descripcion, String tipo, String estado) {
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía.");
            return false;
        }
        if (valor < 0) {
            JOptionPane.showMessageDialog(null, "El valor no puede ser menor a cero.");
            return false;
        }
        if (tipo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El tipo no puede estar vacío.");
            return false;
        }
        if (estado == "Anulado") {
            JOptionPane.showMessageDialog(null, "El estado debe ser Activo para ser actualizado.");
            return false;
        }
        if (consult.compareDescripcion(model)) {
            JOptionPane.showMessageDialog(null, "La descripción no puede repetirse.");
            return false;
        }

        return true;
    }

    private boolean deleteValidation(String estado) {
        if (estado == "Anulado") {
            JOptionPane.showMessageDialog(null, "El registro ya se encuetra anulado.");
            return false;
        }
        if (consult.getExamParamById(model)) {
            JOptionPane.showMessageDialog(null, "El examen esta siendo usado en Examen Parametros.");
            return false;
        }

        return true;
    }

    public void getOptionTipo() {
        CtrlTipoExamen ctrlObject = new CtrlTipoExamen();
        for (TipoExamen tipoItem : ctrlObject.getOptionTipo()) {
            vista.cmbType.addItem(new TipoExamen(tipoItem.getTipoExaId(), tipoItem.getTipoExaDescripcion(), tipoItem.getTipoExaEstado()));
        }
    }

    public void getOptionParams() {
        Parametros modelParam = new Parametros();
        ParametrosDAO consultParam = new ParametrosDAO();
        frmParametros viewParam = new frmParametros();
        CtrlParametros ctrlObject = new CtrlParametros(modelParam, consultParam, viewParam);
        for (Parametros parametroItem : ctrlObject.getOptionParam()) {
            vista.cmbParam.addItem(new Parametros(parametroItem.getPara_id(), parametroItem.getPara_descripcion()));
        }
    }

    public void addParam(int exaId, Parametros objParam) {
        ExamenParametros paramExam = new ExamenParametros(exaId, objParam.getPara_id());
        ExamenParametros modelExamParam = new ExamenParametros();
        ExamenParametrosDAO consultExamParam = new ExamenParametrosDAO();
        CtrlExamenParametros ctrlObject = new CtrlExamenParametros(modelExamParam, consultExamParam);
        ctrlObject.add(paramExam);
        getParamJTable(exaId);
    }

    public void getParamJTable(int examId) {
        ExamenParametros modelExamParam = new ExamenParametros();
        ExamenParametrosDAO consultExamParam = new ExamenParametrosDAO();
        CtrlExamenParametros ctrlObject = new CtrlExamenParametros(modelExamParam, consultExamParam);
        List<Parametros> parametros = ctrlObject.getParamByExamId(examId);
        DefaultTableModel model = (DefaultTableModel) vista.jtbParam.getModel();
        model.setRowCount(0);
        for (Parametros paramItem : parametros) {
            model.addRow(new Object[]{paramItem.getPara_id(), paramItem.getPara_descripcion()});
        }
    }

    public void getJTable() {
        List<Examen> examenes = consult.getAllExamenes();
        DefaultTableModel model = (DefaultTableModel) vista.jtbList.getModel();
        model.setRowCount(0);

        for (Examen examenItem : examenes) {
            model.addRow(new Object[]{examenItem.getId(), examenItem.getDescripcion(), examenItem.getValor(), examenItem.getTipo(), examenItem.getEstado()});
        }
    }
}
