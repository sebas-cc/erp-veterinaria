/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Zeth
 */
public class Veterinario {

    int vet_id;
    String vet_nombre;
    String vet_email;
    String vet_tel;
    String vet_veterinaria;
    String vet_direccion;

    public Veterinario() {
    }

    public Veterinario(int vet_id, String vet_nombre, String vet_email, String vet_tel, String vet_veterinaria, String vet_direccion) {
        this.vet_id = vet_id;
        this.vet_nombre = vet_nombre;
        this.vet_email = vet_email;
        this.vet_tel = vet_tel;
        this.vet_veterinaria = vet_veterinaria;
        this.vet_direccion = vet_direccion;
    }

    public int getVet_id() {
        return vet_id;
    }

    public void setVet_id(int vet_id) {
        this.vet_id = vet_id;
    }

    public String getVet_nombre() {
        return vet_nombre;
    }

    public void setVet_nombre(String vet_nombre) {
        this.vet_nombre = vet_nombre;
    }

    public String getVet_email() {
        return vet_email;
    }

    public void setVet_email(String vet_email) {
        this.vet_email = vet_email;
    }

    public String getVet_tel() {
        return vet_tel;
    }

    public void setVet_tel(String vet_tel) {
        this.vet_tel = vet_tel;
    }

    public String getVet_veterinaria() {
        return vet_veterinaria;
    }

    public void setVet_veterinaria(String vet_veterinaria) {
        this.vet_veterinaria = vet_veterinaria;
    }

    public String getVet_direccion() {
        return vet_direccion;
    }

    public void setVet_direccion(String vet_direccion) {
        this.vet_direccion = vet_direccion;
    }

    @Override
    public String toString() {
        return vet_nombre;
    }
      
}
