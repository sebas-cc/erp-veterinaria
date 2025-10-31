/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ExamenParametrosDAO;
import Model.ExamenParametros;
import Model.Parametros;
import java.util.List;
/**
 *
 * @author Zeth
 */
public class CtrlExamenParametros {

    private final ExamenParametros model = new ExamenParametros();
    private final ExamenParametrosDAO consult = new ExamenParametrosDAO();

    public CtrlExamenParametros() {
        
    }
    
    public boolean add(List<ExamenParametros> listExamParam) {
        return consult.add(listExamParam);
    }
    
    public List<Parametros> getParamByExamId(int examenId) {
        return consult.getParamByExamId(examenId);
    }

}
