package es.albarregas.controllers;

import es.albarregas.DAO.IAlumnosDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateFinAlumnos", urlPatterns = {"/updateFinAlumnos"})
public class UpdateFinAlumnos extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String grupo = request.getParameter("grupo");
        
        IAlumnosDAO aluDAO = DAOFactory.getDAOFactory(1).getAlumnosDAO();
        aluDAO.updateFinAlumno(id, nombre, grupo);

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
