/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.OrdenServicioDAO;
import Model.Examen;
import Model.Mascota;
import Model.OrdenServicio;
import Model.Veterinario;
import View.frmOrdenServicio;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Zeth
 */
public class CtrlOrdenServicio implements ActionListener {

    private final OrdenServicio model;
    private final OrdenServicioDAO consult;
    private final frmOrdenServicio vista;

    public CtrlOrdenServicio(OrdenServicio model, OrdenServicioDAO consult, frmOrdenServicio vista) {
        this.model = model;
        this.consult = consult;
        this.vista = vista;
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnExportar.addActionListener(this);
        this.vista.btnResultados.addActionListener(this);
    }

    public void iniciar() {
        getOptionVeterinario();
        getOptionMascota();
        getOptionExamen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String fechaIni = vista.txtFechaIni.getText().trim();
        String fechaFin = vista.txtFechaFin.getText().trim();
        int idVet = vista.cmbVeterinario.getItemAt(vista.cmbVeterinario.getSelectedIndex()).getVet_id();
        int idMasc = vista.cmbMascota.getItemAt(vista.cmbMascota.getSelectedIndex()).getMas_id();
        int idExamen = vista.cmbExamen.getItemAt(vista.cmbExamen.getSelectedIndex()).getId();
        String estado = vista.cmbEstado.getItemAt(vista.cmbEstado.getSelectedIndex());
        String textoNoOrden = vista.txtOrden.getText().trim();
        int noOrden = 0; // valor por defecto
        if (!textoNoOrden.isEmpty()) {
            noOrden = Integer.parseInt(textoNoOrden);
        }

        if (e.getSource() == vista.btnBuscar) {

            if (fechaIni.isEmpty() || fechaFin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fecha Inicio y Fecha Fin son campos obligatorios.");
                return;
            }
            List<OrdenServicio> listOrdServ = consult.search(fechaIni, fechaFin, idVet, idMasc, idExamen, estado, noOrden);
            getJTable(listOrdServ);
        }
        if (e.getSource() == vista.btnExportar) {
            exportarPDF(fechaIni, fechaFin, idVet, idMasc, idExamen, estado, noOrden);
        }
        if (e.getSource() == vista.btnResultados) {
            int row = vista.jtbList.getSelectedRow();
            int ordenId = Integer.parseInt(vista.jtbList.getValueAt(row, 0).toString());
            consult.llamarInforme(ordenId);
        }
    }

    public void exportarPDF(String fechaIni, String fechaFin, int idVet, int idMasc, int idExamen, String estado, int noOrden) {
        try {
            // Consultar las órdenes según filtros
            List<OrdenServicio> ordenes = consult.search(fechaIni, fechaFin, idVet, idMasc, idExamen, estado, noOrden);

            if (ordenes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay datos para exportar según los filtros seleccionados.");
                return;
            }

            // Preparar ruta y nombre del archivo
            String nombreArchivo = "OrdenesServicio";
            if (noOrden != 0) {
                nombreArchivo += "_" + noOrden;
            }
            String ruta = System.getProperty("user.home") + "\\Downloads\\" + nombreArchivo + ".pdf";

            // Crear documento PDF
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();

            // Encabezado
            documento.add(new Paragraph("REPORTE DE ORDENES DE SERVICIO"));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("Periodo de consulta:"));
            documento.add(new Paragraph("Desde: " + fechaIni + "  -  Hasta: " + fechaFin));
            documento.add(new Paragraph(" "));

            // Tabla
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.addCell("No Orden");
            tabla.addCell("Fecha");
            tabla.addCell("Mascota");
            tabla.addCell("Valor Total");
            tabla.addCell("Estado");

            for (OrdenServicio os : ordenes) {
                tabla.addCell(String.valueOf(os.getOs_id()));
                tabla.addCell(String.valueOf(os.getOs_fecha()));
                tabla.addCell(String.valueOf(os.getOs_mas_name()));
                tabla.addCell(String.valueOf(os.getOs_valor_total()));
                tabla.addCell(String.valueOf(os.getOs_estado()));
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null,
                    "El archivo se descargó con éxito.\nRuta: " + ruta,
                    "Operación Exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No fue posible descargar el PDF.\nError: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void getOptionVeterinario() {
        CtrlVeterinario ctrlObject = new CtrlVeterinario();
        Veterinario allVet = new Veterinario();
        allVet.setVet_id(0);
        allVet.setVet_nombre("Todos");
        vista.cmbVeterinario.addItem(allVet);
        for (Veterinario veterinariosItem : ctrlObject.getAllVeterinarios()) {
            vista.cmbVeterinario.addItem(new Veterinario(veterinariosItem.getVet_id(), veterinariosItem.getVet_nombre(), veterinariosItem.getVet_email(), veterinariosItem.getVet_tel(), veterinariosItem.getVet_veterinaria(), veterinariosItem.getVet_direccion()));
        }
    }

    public void getOptionMascota() {
        CtrlMascota ctrlObject = new CtrlMascota();
        Mascota allPet = new Mascota();
        allPet.setMas_id(0);
        allPet.setMas_nombre("Todos");
        vista.cmbMascota.addItem(allPet);
        for (Mascota mascotaItem : ctrlObject.getAllMascotas()) {
            vista.cmbMascota.addItem(new Mascota(mascotaItem.getMas_id(), mascotaItem.getMas_nombre(), mascotaItem.getMas_edad(), mascotaItem.getMas_propietario(), mascotaItem.getMas_sexo(), mascotaItem.getRaza()));
        }
    }

    public void getOptionExamen() {
        CtrlExamen ctrlObject = new CtrlExamen();
        Examen allExam = new Examen();
        allExam.setId(0);
        allExam.setDescripcion("Todos");
        vista.cmbExamen.addItem(allExam);
        for (Examen examenItem : ctrlObject.getAllExamenes()) {
            vista.cmbExamen.addItem(new Examen(examenItem.getId(), examenItem.getDescripcion(), examenItem.getValor(), examenItem.getTipo(), examenItem.getEstado()));
        }
    }

    public void getJTable(List<OrdenServicio> listOrdServ) {
        DefaultTableModel model = (DefaultTableModel) vista.jtbList.getModel();
        model.setRowCount(0);

        for (OrdenServicio ordServ : listOrdServ) {
            model.addRow(new Object[]{ordServ.getOs_id(), ordServ.getOs_fecha(), ordServ.getOs_mas_name(), ordServ.getOs_valor_total(), ordServ.getOs_estado()});
        }
    }
}
