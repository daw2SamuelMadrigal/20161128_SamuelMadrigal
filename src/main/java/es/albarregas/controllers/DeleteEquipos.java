package es.albarregas.controllers;

import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteEquipos", urlPatterns = {"/deleteEquipos"})
public class DeleteEquipos extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String[] ids = request.getParameterValues("id");
        
        IEquiposDAO equDAO = DAOFactory.getDAOFactory(1).getEquiposDAO();
        equDAO.deleteEquipo(ids);

        request.getRequestDispatcher("/index.jspx").forward(request, response);
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarPeticion(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarPeticion(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
