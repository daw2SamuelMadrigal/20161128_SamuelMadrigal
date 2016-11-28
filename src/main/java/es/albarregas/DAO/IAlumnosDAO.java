package es.albarregas.DAO;

import es.albarregas.beans.Alumno;
import java.util.ArrayList;

public interface IAlumnosDAO {
    public void addAlumno(Alumno a);
    public ArrayList<Alumno> getAlumnos(String where);
    public ArrayList<Alumno> getAlumnosEquipo();
    public Alumno updateAlumno(String id);
    public void updateFinAlumno(String id, String nombre, String grupo);
    public void deleteAlumno(String[] ids);
    public void closeConnection();
}
