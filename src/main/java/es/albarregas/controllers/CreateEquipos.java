package es.albarregas.controllers;

import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import es.albarregas.beans.Equipo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateEquipos", urlPatterns = {"/createEquipos"})
public class CreateEquipos extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Equipo equipo = new Equipo();
        String marca = request.getParameter("marca");
        String numSerie = request.getParameter("numSerie");
        
        equipo.setMarca(marca);
        equipo.setNumSerie(numSerie);
        
        IEquiposDAO equDAO = DAOFactory.getDAOFactory(1).getEquiposDAO();
        equDAO.addEquipo(equipo);

        request.setAttribute("equipo", equipo);

        request.getRequestDispatcher("/JSPX/altaEquipos.jspx").forward(request, response);
        
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
