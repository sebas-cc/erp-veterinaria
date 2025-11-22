/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.OrdenServicio;
import java.awt.Frame;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Zeth
 */
public class OrdenServicioDAO extends MySQLConnection {

    private static final String RUTA_REPORTE = "./src/reporte/ordenServicio.jasper";

    public List<OrdenServicio> getAllOrdenServicio() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrdenServicio> listOrdServ = new ArrayList<>();
        String sql = "SELECT os_id, os_fecha, os_mas_id, os_valor_total, os_estado FROM orden_servicio;";
        MySQLConnection con = new MySQLConnection();
        con.conectar();
        System.out.println("Obteniendo Ordenes de Servicio...");
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdenServicio objOrdeServ = new OrdenServicio();
                objOrdeServ.setOs_id(rs.getInt("os_id"));
                objOrdeServ.setOs_fecha(rs.getString("os_fecha"));
                objOrdeServ.setOs_mas_name(rs.getString("os_mas_id"));
                objOrdeServ.setOs_valor_total(rs.getInt("os_valor_total"));
                objOrdeServ.setOs_estado(rs.getString("os_estado"));
                listOrdServ.add(objOrdeServ);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            //con.desconectar();
        }
        return listOrdServ;
    }

    public List<OrdenServicio> search(String fechaIni, String fechaFin, int idVet, int idMasc, int idExamen, String estado, int noOrden) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrdenServicio> listOrdServ = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT os.os_id, os.os_fecha, m.mas_nombre, os.os_valor_total, os.os_estado FROM orden_servicio AS os JOIN mascota AS m ON os.os_mas_id = m.mas_id LEFT JOIN orden_servicio_detalle AS d ON d.dos_os_id = os.os_id WHERE os.os_fecha BETWEEN ? AND ? ");

        if (idVet != 0) {
            sql.append("AND os.vet_id = ? ");
        }
        if (idMasc != 0) {
            sql.append("AND os.os_mas_id = ? ");
        }
        if (idExamen != 0) {
            sql.append("AND d.dos_exa_id = ? ");
        }
        if (!estado.equals("Todo")) {
            sql.append("AND os.os_estado = ? ");
        }
        if (noOrden != 0) {
            sql.append("AND os.os_id = ? ");
        }

        MySQLConnection con = new MySQLConnection();
        con.conectar();

        System.out.println("Filtrando Ordenes de Servicio...");

        try {
            ps = con.prepareStatement(sql.toString());

            int index = 1;

            Date fechaInicioSQL = java.sql.Date.valueOf(fechaIni);
            Date fechaFinSQL = java.sql.Date.valueOf(fechaFin);

            ps.setDate(index++, fechaInicioSQL);
            ps.setDate(index++, fechaFinSQL);

            if (idVet != 0) {
                ps.setInt(index++, idVet);
            }
            if (idMasc != 0) {
                ps.setInt(index++, idMasc);
            }
            if (idExamen != 0) {
                ps.setInt(index++, idExamen);
            }
            if (!estado.equals("Todo")) {
                ps.setString(index++, estado);
            }
            if (noOrden != 0) {
                ps.setInt(index++, noOrden);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                OrdenServicio obj = new OrdenServicio();
                obj.setOs_id(rs.getInt("os_id"));
                obj.setOs_fecha(rs.getString("os_fecha"));
                obj.setOs_mas_name(rs.getString("mas_nombre"));
                obj.setOs_valor_total(rs.getInt("os_valor_total"));
                obj.setOs_estado(rs.getString("os_estado"));
                listOrdServ.add(obj);
            }

        } catch (SQLException e) {
            System.err.println("Error búsqueda OS: " + e.getMessage());
        }

        return listOrdServ;
    }

    public void llamarInforme(int id_orden) {
        MySQLConnection conexion = new MySQLConnection();
        Connection con = null;
        try {
            con = conexion.conectar(); // Debe devolver java.sql.Connection

            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("id_orden", id_orden);

            JasperPrint jp = JasperFillManager.fillReport(RUTA_REPORTE, parametros, con);

            JasperViewer view = new JasperViewer(jp, false);
            view.setTitle("Factura " + id_orden);
            view.setExtendedState(Frame.MAXIMIZED_BOTH);
            view.setVisible(true);

        } catch (Exception e) {
            System.err.println("Error al generar informe de factura: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Ocurrió un error al generar la factura.\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception ex) {
                System.err.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
    }

}
