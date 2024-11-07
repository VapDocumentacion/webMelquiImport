package Model;

public class DocumentoCab {

    private int ciaNCodigo;
    private int docNCodigo;
    private int serNCodigo;
    private String docCNumero;
    private String docFFecDoc;
    private String docFFecVencimiento;
    private double tcaNValorVenta;
    private int monNCodigo;
    private int venNCodigo;
    private String docCGlosa;
    private int cliNCodigo;

    public int getCiaNCodigo() {
        return ciaNCodigo;
    }

    public void setCiaNCodigo(int ciaNCodigo) {
        this.ciaNCodigo = ciaNCodigo;
    }

    public int getDocNCodigo() {
        return docNCodigo;
    }

    public void setDocNCodigo(int docNCodigo) {
        this.docNCodigo = docNCodigo;
    }

    public int getSerNCodigo() {
        return serNCodigo;
    }

    public void setSerNCodigo(int serNCodigo) {
        this.serNCodigo = serNCodigo;
    }

    public String getDocCNumero() {
        return docCNumero;
    }

    public void setDocCNumero(String docCNumero) {
        this.docCNumero = docCNumero;
    }

    public String getDocFFecDoc() {
        return docFFecDoc;
    }

    public void setDocFFecDoc(String docFFecDoc) {
        this.docFFecDoc = docFFecDoc;
    }

    public String getDocFFecVencimiento() {
        return docFFecVencimiento;
    }

    public void setDocFFecVencimiento(String docFFecVencimiento) {
        this.docFFecVencimiento = docFFecVencimiento;
    }

    public double getTcaNValorVenta() {
        return tcaNValorVenta;
    }

    public void setTcaNValorVenta(double tcaNValorVenta) {
        this.tcaNValorVenta = tcaNValorVenta;
    }

    public int getMonNCodigo() {
        return monNCodigo;
    }

    public void setMonNCodigo(int monNCodigo) {
        this.monNCodigo = monNCodigo;
    }

    public int getVenNCodigo() {
        return venNCodigo;
    }

    public void setVenNCodigo(int venNCodigo) {
        this.venNCodigo = venNCodigo;
    }

    public String getDocCGlosa() {
        return docCGlosa;
    }

    public void setDocCGlosa(String docCGlosa) {
        this.docCGlosa = docCGlosa;
    }

    public int getCliNCodigo() {
        return cliNCodigo;
    }

    public void setCliNCodigo(int cliNCodigo) {
        this.cliNCodigo = cliNCodigo;
    }

    @Override
    public String toString() {
        return "DocumentoCab{" + "ciaNCodigo=" + ciaNCodigo + ", docNCodigo=" + docNCodigo + ", serNCodigo=" + serNCodigo + ", docCNumero=" + docCNumero + ", docFFecDoc=" + docFFecDoc + ", docFFecVencimiento=" + docFFecVencimiento + ", tcaNValorVenta=" + tcaNValorVenta + ", monNCodigo=" + monNCodigo + ", venNCodigo=" + venNCodigo + ", docCGlosa=" + docCGlosa + ", cliNCodigo=" + cliNCodigo + '}';
    }

    
}
