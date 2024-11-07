package Controller;

import Model.Catalogo;
import Model.DocumentoCab;
import Model.DocumentoDet;
import ModelDAO.CatalogoDAO;
import ModelDAO.DocumentoFactDAO;
import ModelDAO.MonedaDAO;
import ModelDAO.SerieDAO;
import ModelDAO.VendedorDAO;
import Utils.CarritoUitl;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FacturaServlet", urlPatterns = {"/factura"})
public class FacturaServlet extends HttpServlet {

    private CarritoUitl carritoUtil = new CarritoUitl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        if (accion.equalsIgnoreCase("registro")) {
            Registro(request, response);
        }
        if (accion.equalsIgnoreCase("agregarItemDetalle")) {
            AgregarItemDetalle(request, response);
        }
        if (accion.equalsIgnoreCase("eliminarItemDetalle")) {
            EliminarItemDetalle(request, response);
        }
        if (accion.equalsIgnoreCase("listarDetalle")) {
            ListarDetalle(request, response);
        }
        if (accion.equalsIgnoreCase("guardarFactura")) {
            GuardarFactura(request, response);
        }
    }

    protected void GuardarFactura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Map<String, Object> resultado = new HashMap<>();

        try {
            DocumentoCab documento = new DocumentoCab();
            documento.setDocFFecDoc(request.getParameter("fecha"));
            documento.setDocFFecVencimiento(request.getParameter("fechaVenc"));
            documento.setMonNCodigo(Integer.parseInt(request.getParameter("idMoneda")));
            documento.setVenNCodigo(Integer.parseInt(request.getParameter("idVendedor")));
            documento.setDocCGlosa(request.getParameter("glucosa"));
            documento.setCliNCodigo(Integer.parseInt(request.getParameter("idCliente")));
            documento.setSerNCodigo(Integer.parseInt(request.getParameter("idSerie")));
            documento.setTcaNValorVenta(Double.parseDouble(request.getParameter("tipoCambioContable")));

            List<DocumentoDet> lista = carritoUtil.ObtenerSesion(request);

            String nroFact = DocumentoFactDAO.insertarDocumentoCab(documento, lista);

            if (nroFact != null) {
                resultado.put("msg", "OK");
                resultado.put("resultado", "Facturada generada correctamente: "+ nroFact);
            } else {
                resultado.put("msg", "No se pudo generar factura");
            }

        } catch (Exception ex) {
            resultado.put("msg", ex.getMessage());
        }

        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(resultado));
        out.flush();
    }

    protected void ListarDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Map<String, Object> resultado = new HashMap<>();

        List<DocumentoDet> lista = carritoUtil.ObtenerSesion(request);
        double subTotal, igv, total;

        subTotal = carritoUtil.SubTotal(lista);
        igv = carritoUtil.IGV(lista);
        total = carritoUtil.Total(subTotal, igv);

        resultado.put("data", lista);
        resultado.put("subTotal", subTotal);
        resultado.put("igv", igv);
        resultado.put("total", total);
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(resultado));
        out.flush();
    }

    protected void AgregarItemDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Map<String, Object> resultado = new HashMap<>();

        try {
            int idCatalogo = Integer.parseInt(request.getParameter("id"));
            double precio = Double.parseDouble(request.getParameter("precio"));
            double descuento = Double.parseDouble(request.getParameter("descuento"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            Catalogo objCatalogo = CatalogoDAO.BuscarPorId(idCatalogo);

            if (objCatalogo != null) {
                DocumentoDet objDet = new DocumentoDet();
                objDet.setCantidad(cantidad);
                objDet.setValorUnitario(precio);
                objDet.setDescripcion(objCatalogo.getDescripcion());
                objDet.setCmacCodigo(objCatalogo.getCmacCodigo());
                objDet.setMcanCodigo(objCatalogo.getMcanCodigo());
                objDet.setDescuento(descuento);
                objDet.setIgvPorcentaje(18);
                objDet.calcularValorPorcentaje();
                objDet.calcularValorSubTotal();

                carritoUtil.AgregarCarrito(request, objDet);
                resultado.put("msg", "OK");
            } else {
                resultado.put("msg", "No se encontro catalogo de producto");
            }
        } catch (Exception ex) {
            resultado.put("msg", ex.getMessage());
        }

        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(resultado));
        out.flush();
    }

    protected void EliminarItemDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Map<String, Object> resultado = new HashMap<>();

        try {
            int indice = Integer.parseInt(request.getParameter("index"));

            carritoUtil.RemoverItemCarrito(request, indice);
            resultado.put("msg", "OK");
        } catch (Exception ex) {
            resultado.put("msg", ex.getMessage());
        }

        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(resultado));
        out.flush();
    }

    protected void Registro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("vendedores", VendedorDAO.Listar());
        request.setAttribute("monedas", MonedaDAO.Listar());
        request.setAttribute("series", SerieDAO.Listar());
        request.getRequestDispatcher("Views/pagNuevaFactura.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
