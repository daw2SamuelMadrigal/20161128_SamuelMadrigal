package es.albarregas.controllers;

import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateFinEquipos", urlPatterns = {"/updateFinEquipos"})
public class UpdateFinEquipos extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id = request.getParameter("id");
        String marca = request.getParameter("marca");
        String numSerie = request.getParameter("numSerie");
        
        IEquiposDAO equDAO = DAOFactory.getDAOFactory(1).getEquiposDAO();
        equDAO.updateFinEquipo(id, marca, numSerie);

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
