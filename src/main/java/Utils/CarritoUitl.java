package Utils;

import Model.DocumentoDet;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CarritoUitl {

    public void AgregarCarrito(HttpServletRequest request, DocumentoDet detalle) {
        List<DocumentoDet> lista = ObtenerSesion(request);
        lista.add(detalle);
        GuardarSesion(request, lista);
    }

    public void RemoverItemCarrito(HttpServletRequest request, int indice) {
        List<DocumentoDet> lista = ObtenerSesion(request);
        lista.remove(indice);
        GuardarSesion(request, lista);
    }

    public List<DocumentoDet> ObtenerSesion(HttpServletRequest request) {
        List<DocumentoDet> lista;

        if (request.getSession().getAttribute("carrito") == null) {
            lista = new ArrayList<>();
        } else {
            lista = (ArrayList<DocumentoDet>) request.getSession().getAttribute("carrito");
        }
        return lista;
    }

    public double SubTotal(List<DocumentoDet> lista) {
        double total = 0;
        for (DocumentoDet item : lista) {
            total += item.getSubTotal() - item.getIgvValor();
        }
        return total;
    }

    public double IGV(List<DocumentoDet> lista) {
        double total = 0;
        for (DocumentoDet item : lista) {
            total += item.getIgvValor();
        }
        return total;
    }

    public double Total(double subTotal, double igv) {
        return subTotal + igv;
    }

    public void GuardarSesion(HttpServletRequest request, List<DocumentoDet> lista) {
        request.getSession().setAttribute("carrito", lista);
    }
}
