package Controller;

import Model.Catalogo;
import Model.Cliente;
import ModelDAO.CatalogoDAO;
import ModelDAO.ClienteDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/cliente"})
public class ClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        if (accion.equalsIgnoreCase("buscar")) {
            BuscarPorCodigo(request, response);
        }
    }

    protected void BuscarPorCodigo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("application/json");

        int opcion = Integer.parseInt(request.getParameter("opcion"));
        String descripcion = request.getParameter("descripcion");

        List<Cliente> lista = new ArrayList<>();

        if (opcion == 1) { // Por codigo
            lista = ClienteDAO.BuscarPorRuc(descripcion);
        } else { // Por descripcion
            lista = ClienteDAO.BuscarPorRazonSocial(descripcion);
        }

        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(lista));
        out.flush();
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
