package sv.edu.udb.mispartes.datos;

public class Medicamento {
    public String ID;
    private String nombre;
    private double precio;
    private String descripcionCorta;
    private String descripcionLarga;
    private int foto;

    //Constructor
    public Medicamento(String ID, String nombre, double precio, String descripcionCorta, String descripcionLarga, int foto){
        this.ID = ID;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcionCorta = descripcionCorta;
        this.descripcionLarga = descripcionLarga;
        this.foto = foto;
    }

    //Set y Get
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getFoto() {
        return foto;
    }

    //Métodos
    public float calcularCosto(int numeroUnidades){
        return (float) (this.precio*numeroUnidades);
    }
}
