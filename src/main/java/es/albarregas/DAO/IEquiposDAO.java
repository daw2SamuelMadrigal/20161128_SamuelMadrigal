package es.albarregas.DAO;

import es.albarregas.beans.Equipo;
import java.util.ArrayList;


public interface IEquiposDAO {
    public void addEquipo(Equipo e);
    public ArrayList<Equipo> getEquipos(String where);
    public Equipo updateEquipo(String where);
    public void updateFinEquipo(String id, String marca, String numSerie);
    public void deleteEquipo(String[] ids);
    public void closeConnection();
}
