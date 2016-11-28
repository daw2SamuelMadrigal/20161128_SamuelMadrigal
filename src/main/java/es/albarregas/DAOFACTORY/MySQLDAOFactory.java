package es.albarregas.DAOFACTORY;

import es.albarregas.DAO.AlumnosDAO;
import es.albarregas.DAO.EquiposDAO;
import es.albarregas.DAO.IAlumnosDAO;
import es.albarregas.DAO.IEquiposDAO;

public class MySQLDAOFactory extends DAOFactory{

    @Override
    public IAlumnosDAO getAlumnosDAO() {
        return new AlumnosDAO();
    }

    @Override
    public IEquiposDAO getEquiposDAO() {
        return new EquiposDAO();
    }

}