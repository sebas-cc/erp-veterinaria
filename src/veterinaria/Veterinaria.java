/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package veterinaria;

import Controller.CtrlExamen;
import DAO.ExamenDAO;
import Model.Examen;
import View.frmExamen;
import Controller.CtrlParametros;
import DAO.ParametrosDAO;
import Model.Parametros;
import View.frmParametros;

/**
 *
 * @author Zeth
 */
public class Veterinaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Examen examenModel = new Examen();
        frmExamen examenView = new frmExamen();
        ExamenDAO examenDAO = new ExamenDAO();
        CtrlExamen examenController = new CtrlExamen(examenModel, examenDAO, examenView);
        examenController.iniciar();
        examenView.setVisible(true);

        Parametros parametrosModel = new Parametros();
        frmParametros parametrosView = new frmParametros();
        ParametrosDAO parametrosDAO = new ParametrosDAO();
        CtrlParametros parametrosController = new CtrlParametros(parametrosModel, parametrosDAO, parametrosView);
        parametrosController.iniciar();
        parametrosView.setVisible(true);
    }

}
