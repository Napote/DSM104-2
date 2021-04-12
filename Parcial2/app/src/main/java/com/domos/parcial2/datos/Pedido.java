package com.domos.parcial2.datos;

import android.util.Log;

public class Pedido {

    private String ID;
    private double costo;
    private Long totalItems;
    private String fechaOrden;

    public Pedido(){

    }

    public Pedido(String ID, double costo, Long totalItems, String fechaOrden){
        this.ID = ID;
        this.costo = costo;
        this.totalItems = totalItems;
        this.fechaOrden = fechaOrden;
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

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }
}
