package com.domos.parcial2.datos;

public class Item {

    public String ID;
    private String nombreMedicamento;
    private double costo;
    private int unidades;

    //Constructor
    public Item(String ID, String nombreMedicamento, double costo, int unidades){
        this.ID = ID;
        this.nombreMedicamento=nombreMedicamento;
        this.costo=costo;
        this.unidades=unidades;
    }

    //Set y Get

    public void setNombre(String nombre) {
        this.nombreMedicamento = nombre;
    }

    public String getNombre() {
        return nombreMedicamento;
    }

    public void setCosto(float costo) { this.costo = costo; }

    public double getCosto() {
        return costo;
    }

    public void setUnidades(int unidades) { this.unidades = unidades; }

    public int getUnidades() {
        return unidades;
    }

}
