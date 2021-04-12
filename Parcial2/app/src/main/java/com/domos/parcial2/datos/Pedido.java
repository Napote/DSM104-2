package com.domos.parcial2.datos;

import android.util.Log;

public class Pedido {

    private String ID;
    private double costo;
    private Long totalItems;

    public Pedido(){

    }

    public Pedido(String ID, double costo, Long totalItems){
        this.ID = ID;
        this.costo = costo;
        this.totalItems = totalItems;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Long getTotalItems() {
        return totalItems;
    }
}
