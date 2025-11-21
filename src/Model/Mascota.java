/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Zeth
 */
public class Mascota {

    int mas_id;
    String mas_nombre;
    int mas_edad;
    String mas_propietario;
    String mas_sexo;
    String raza;

    public Mascota() {
    }

    public Mascota(int mas_id, String mas_nombre, int mas_edad, String mas_propietario, String mas_sexo, String raza) {
        this.mas_id = mas_id;
        this.mas_nombre = mas_nombre;
        this.mas_edad = mas_edad;
        this.mas_propietario = mas_propietario;
        this.mas_sexo = mas_sexo;
        this.raza = raza;
    }

    public int getMas_id() {
        return mas_id;
    }

    public void setMas_id(int mas_id) {
        this.mas_id = mas_id;
    }

    public String getMas_nombre() {
        return mas_nombre;
    }

    public void setMas_nombre(String mas_nombre) {
        this.mas_nombre = mas_nombre;
    }

    public int getMas_edad() {
        return mas_edad;
    }

    public void setMas_edad(int mas_edad) {
        this.mas_edad = mas_edad;
    }

    public String getMas_propietario() {
        return mas_propietario;
    }

    public void setMas_propietario(String mas_propietario) {
        this.mas_propietario = mas_propietario;
    }

    public String getMas_sexo() {
        return mas_sexo;
    }

    public void setMas_sexo(String mas_sexo) {
        this.mas_sexo = mas_sexo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    @Override
    public String toString() {
        return mas_nombre;
    }

}
