package com.domos.parcial2.datos;

public class Orden {
    public String ID;
    private String fechaOrden;
    private double costo;
    private int totalItems;


    //Constructor
    public Orden( double costo, int totalItems){
        this.costo=costo;
        this.totalItems=totalItems;
    }

    //Set y Get

    public void setCosto(double costo) {
        this.costo=costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setTotalItems(int items) { this.totalItems = items; }

    public int getTotalItems() {
        return totalItems;
    }



}
