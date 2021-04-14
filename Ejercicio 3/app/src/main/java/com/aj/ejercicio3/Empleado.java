package com.aj.ejercicio3;
import java.io.Serializable;

public class Empleado implements Serializable {

    private String nombre;
    private String cargo;
    private Double sueldoNeto;
    private Double horas;
    private Double descuentoAFP;
    private Double descuentoSeguro;
    private Double descuentoRenta;
    private Double sueldoLiquido;

    public static Double DESC_AFP = 0.0688;
    public static Double DESC_RENTA = 0.1;
    public static Double DESC_ISSS = 0.0525;


    public Empleado(String nombre, Double horas, String cargo) {
        this.nombre =nombre;
        this.horas = horas;
        this.cargo = cargo;
    }

    public void setSueldoNeto(Double sueldoNeto){
        this.sueldoNeto = sueldoNeto;

    }

    protected void calcularSueldoLiquido(){
        this.descuentoAFP = this.sueldoNeto * DESC_AFP;
        this.descuentoRenta = this.sueldoNeto * DESC_RENTA;
        this.descuentoSeguro = this.sueldoNeto * DESC_ISSS;
        this.sueldoLiquido = this.sueldoNeto - this.descuentoSeguro - this.descuentoRenta - this.descuentoAFP;

    }

    public void setBono(Double bono){
        this.sueldoNeto = this.sueldoNeto + this.sueldoNeto * bono;
        this.calcularSueldoLiquido();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public Double getHoras(){
        return this.horas;
    }

    public Double getSueldoLiquido(){
        return this.sueldoLiquido;
    }

    public String getDescuentos(){
        String descuentos = " -$".concat(this.descuentoSeguro.toString())
                .concat(" ISSS, -$")
                .concat(this.descuentoAFP.toString())
                .concat(" AFP, -$")
                .concat(this.descuentoRenta.toString())
                .concat(" Renta");
        return descuentos;
    }




}



