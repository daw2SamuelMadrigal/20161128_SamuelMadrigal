package es.albarregas.controllers;

import es.albarregas.DAO.IAlumnosDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import es.albarregas.beans.Alumno;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateAlumnos", urlPatterns = {"/createAlumnos"})
public class CreateAlumnos extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Alumno alumno = new Alumno();
        String nombre = request.getParameter("nombre");
        String grupo = request.getParameter("grupo");
        
        alumno.setNombre(nombre);
        alumno.setGrupo(grupo);
        
        IAlumnosDAO aluDAO = DAOFactory.getDAOFactory(1).getAlumnosDAO();
        aluDAO.addAlumno(alumno);

        request.setAttribute("alumno", alumno);

        request.getRequestDispatcher("/JSPX/altaAlumnos.jspx").forward(request, response);
        
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
