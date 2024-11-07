package Model;

public class DocumentoDet {

    private String descripcion;
    private double valorUnitario;
    private double descuento;
    private double subTotal;
    private double igvValor;
    private double igvPorcentaje;
    private int cantidad;
    private int mcanCodigo;
    private String cmacCodigo;

    public void calcularValorPorcentaje() {
        this.igvValor = ((cantidad * valorUnitario) - descuento) * (igvPorcentaje / 100.0);
    }

    public void calcularValorSubTotal() {
        this.subTotal = (cantidad * valorUnitario) + igvValor - descuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIgvValor() {
        return igvValor;
    }

    public void setIgvValor(double igvValor) {
        this.igvValor = igvValor;
    }

    public double getIgvPorcentaje() {
        return igvPorcentaje;
    }

    public void setIgvPorcentaje(double igvPorcentaje) {
        this.igvPorcentaje = igvPorcentaje;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMcanCodigo() {
        return mcanCodigo;
    }

    public void setMcanCodigo(int mcanCodigo) {
        this.mcanCodigo = mcanCodigo;
    }

    public String getCmacCodigo() {
        return cmacCodigo;
    }

    public void setCmacCodigo(String cmacCodigo) {
        this.cmacCodigo = cmacCodigo;
    }

}
