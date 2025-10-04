/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Zeth
 */
public class TipoExamen {

    private int tipoExaId;
    private String tipoExaDescripcion;
    private String tipoExaEstado;

    public TipoExamen() {
    }

    public TipoExamen(int tipoExaId, String tipoExaDescripcion, String tipoExaEstado) {
        this.tipoExaId = tipoExaId;
        this.tipoExaDescripcion = tipoExaDescripcion;
        this.tipoExaEstado = tipoExaEstado;
    }

    public int getTipoExaId() {
        return tipoExaId;
    }

    public void setTipoExaId(int tipoExaId) {
        this.tipoExaId = tipoExaId;
    }

    public String getTipoExaDescripcion() {
        return tipoExaDescripcion;
    }

    public void setTipoExaDescripcion(String tipoExaDescripcion) {
        this.tipoExaDescripcion = tipoExaDescripcion;
    }

    public String getTipoExaEstado() {
        return tipoExaEstado;
    }

    public void setTipoExaEstado(String tipoExaEstado) {
        this.tipoExaEstado = tipoExaEstado;
    }

    @Override
    public String toString() {
        return tipoExaDescripcion;
    }

}
