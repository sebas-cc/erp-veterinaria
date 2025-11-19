/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Zeth
 */
public class UnidadParametros {
    int un_id;
    String un_descripcion, un_estado;

    public UnidadParametros() {
    }

    public UnidadParametros(int un_id, String un_descripcion) {
        this.un_id = un_id;
        this.un_descripcion = un_descripcion;
    }

    public int getUn_id() {
        return un_id;
    }

    public void setUn_id(int un_id) {
        this.un_id = un_id;
    }

    public String getUn_descripcion() {
        return un_descripcion;
    }

    public void setUn_descripcion(String un_descripcion) {
        this.un_descripcion = un_descripcion;
    }

    public String getUn_estado() {
        return un_estado;
    }

    public void setUn_estado(String un_estado) {
        this.un_estado = un_estado;
    }
    
    @Override
    public String toString() {
        return un_descripcion;
    }
}
