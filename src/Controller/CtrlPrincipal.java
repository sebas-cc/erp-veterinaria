/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ExamenDAO;
import DAO.ParametrosDAO;
import Model.Examen;
import Model.Parametros;
import View.frmExamen;
import View.frmMenu;
import View.frmParametros;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Zeth
 */
public class CtrlPrincipal implements ActionListener {

    private final frmMenu vista;
    private final boolean UNDECORATED = !true;

    public CtrlPrincipal(frmMenu vista) {
        this.vista = vista;
        configurarEventosMenu();
    }

    private void configurarEventosMenu() {

        vista.btnExamen.addActionListener(this);
        vista.btnParametro.addActionListener(this);
    }

    public void iniciar() {
        vista.setSize(new Dimension(1920, 1080));
        vista.setExtendedState(Frame.MAXIMIZED_BOTH);
        vista.setLocationRelativeTo(null);

        if (UNDECORATED) {
            vista.setUndecorated(UNDECORATED);
            vista.setBackground(new Color(0, 0, 0, 0));
        } else {
            vista.getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnExamen) {
            cargarModuloExamen();
        }

        if (e.getSource() == vista.btnParametro) {
            cargarModuloParametros();
        }

    }

    private void mostrarEnPanel(javax.swing.JPanel panel) {
        // Configurar animación del contenedor
        vista.panelMostrar.putClientProperty("FlatLaf.animation", "fade");
        vista.panelMostrar.putClientProperty("FlatLaf.animationDuration", 250);

        // Configurar el panel que se va a mostrar
        panel.setVisible(true);
        panel.setSize(1655, 992);
        panel.setLocation(0, 0);
        panel.setBackground(new Color(24, 26, 32));

        // Animación de salida del contenido anterior (opcional)
        if (vista.panelMostrar.getComponentCount() > 0) {
            vista.panelMostrar.putClientProperty("FlatLaf.animation", "slideLeft");
        }

        // Cambiar contenido
        vista.panelMostrar.removeAll();
        vista.panelMostrar.add(panel, BorderLayout.CENTER);

        // Animación de entrada del nuevo panel
        panel.putClientProperty("FlatLaf.animation", "slideUp");
        panel.putClientProperty("FlatLaf.animationDuration", 500);

        // Activar las animaciones
        vista.panelMostrar.revalidate();
        vista.panelMostrar.repaint();
    }

    private void cargarModuloExamen() {
        try {
            Examen objModel = new Examen();
            frmExamen objView = new frmExamen();
            ExamenDAO objDAO = new ExamenDAO();
            CtrlExamen objCtrl = new CtrlExamen(objModel, objDAO, objView);

            objView.setControlador(objCtrl);
            objCtrl.iniciar();
            mostrarEnPanel(objView);
        } catch (Exception ex) {
            System.err.println("Error al cargar módulo de Examen: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void cargarModuloParametros() {
        try {
            Parametros objModel = new Parametros();
            frmParametros objView = new frmParametros();
            ParametrosDAO objDAO = new ParametrosDAO();
            CtrlParametros objCtrl = new CtrlParametros(objModel, objDAO, objView);

            objView.setControlador(objCtrl);
            objCtrl.iniciar();
            mostrarEnPanel(objView);
        } catch (Exception ex) {
            System.err.println("Error al cargar módulo de Parametro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
