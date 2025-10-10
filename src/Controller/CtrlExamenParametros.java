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

    private final ExamenParametros model;
    private final ExamenParametrosDAO consult;

    public CtrlExamenParametros(ExamenParametros model, ExamenParametrosDAO consult) {
        this.model = model;
        this.consult = consult;
    }
    
    public boolean add(ExamenParametros examParam) {
        return consult.add(examParam);
    }
    
    public List<Parametros> getParamByExamId(int examenId) {
        return consult.getParamByExamId(examenId);
    }

}
