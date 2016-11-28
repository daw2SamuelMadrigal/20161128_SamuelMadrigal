package es.albarregas.controllers;

import es.albarregas.DAO.IAlumnosDAO;
import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import es.albarregas.beans.Alumno;
import es.albarregas.beans.Equipo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateEquipos", urlPatterns = {"/updateEquipos"})
public class UpdateEquipos extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Equipo equipo;
        String id = request.getParameter("id");
        
        IEquiposDAO aluDAO = DAOFactory.getDAOFactory(1).getEquiposDAO();
        equipo = aluDAO.updateEquipo(id);

        request.setAttribute("equipo", equipo);

        request.getRequestDispatcher("/JSPX/updateEquipos.jspx").forward(request, response);
        
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
