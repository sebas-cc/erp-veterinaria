/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Zeth
 */
public class OrdenServicio {

    int os_id;
    String os_fecha;
    String os_mas_name;
    int os_valor_total;
    String os_estado;

    public OrdenServicio(int os_id, String os_fecha, String os_mas_name, int os_valor_total, String os_estado) {
        this.os_id = os_id;
        this.os_fecha = os_fecha;
        this.os_mas_name = os_mas_name;
        this.os_valor_total = os_valor_total;
        this.os_estado = os_estado;
    }

    public OrdenServicio() {
    }

    public int getOs_id() {
        return os_id;
    }

    public void setOs_id(int os_id) {
        this.os_id = os_id;
    }

    public String getOs_fecha() {
        return os_fecha;
    }

    public void setOs_fecha(String os_fecha) {
        this.os_fecha = os_fecha;
    }

    public String getOs_mas_name() {
        return os_mas_name;
    }

    public void setOs_mas_name(String os_mas_name) {
        this.os_mas_name = os_mas_name;
    }

    public int getOs_valor_total() {
        return os_valor_total;
    }

    public void setOs_valor_total(int os_valor_total) {
        this.os_valor_total = os_valor_total;
    }

    public String getOs_estado() {
        return os_estado;
    }

    public void setOs_estado(String os_estado) {
        this.os_estado = os_estado;
    }

}
