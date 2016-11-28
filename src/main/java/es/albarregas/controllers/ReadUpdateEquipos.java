package es.albarregas.controllers;

import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import es.albarregas.beans.Equipo;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ReadUpdateEquipos", urlPatterns = {"/readUpdateEquipos"})
public class ReadUpdateEquipos extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Equipo> lista;
        IEquiposDAO equDAO = DAOFactory.getDAOFactory(1).getEquiposDAO();
        
        lista = equDAO.getEquipos("");

        request.setAttribute("lista", lista);

        request.getRequestDispatcher("/JSPX/readUpdateEquipos.jspx").forward(request, response);
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
