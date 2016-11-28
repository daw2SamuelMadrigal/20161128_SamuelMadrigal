package es.albarregas.DAO;

import es.albarregas.DAOFACTORY.ConnectionFactory;
import es.albarregas.beans.Equipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class EquiposDAO implements IEquiposDAO {

    private PreparedStatement preparada = null;
    private ResultSet resultado = null;
    // logger general para toda la aplicación
    final static Logger LOGGER = Logger.getRootLogger();

    @Override
    public void addEquipo(Equipo e) {
        Connection conexion = ConnectionFactory.getConnection();

        try {
            // Creamos y ejecutamos la sentencia sql preparada
            preparada = conexion.prepareStatement("insert into equipos values(0, ?, ?)");
            preparada.setString(1, e.getMarca());
            preparada.setString(2, e.getNumSerie());
            preparada.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", ex);
        }
        
        closeConnection();
    }

    @Override
    public ArrayList<Equipo> getEquipos(String where) {
        Equipo equipo;
        ArrayList<Equipo> listado = new ArrayList();

        try {
            preparada = ConnectionFactory.getConnection().prepareStatement("SELECT * FROM EQUIPOS " + where + ";");
            resultado = preparada.executeQuery();
            while (resultado.next()) {
                equipo = new Equipo();
                equipo.setIdEquipo(resultado.getInt("idEquipo"));
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));
                listado.add(equipo);
            }
        } catch (SQLException ex) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", ex);
        }

        closeConnection();

        return listado;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();

        try {
            if (preparada != null) {
                preparada.close();
            }
        } catch (SQLException ex) {
            LOGGER.fatal("Problema al cerrar la preparada", ex);
        }
        try {
            if (resultado != null) {
                resultado.close();
            }
        } catch (SQLException ex) {
            LOGGER.fatal("Problema al cerrar el resultado", ex);
        }
    }

    @Override
    public Equipo updateEquipo(String id) {
        
        Equipo equipo = new Equipo();
        Connection conexion = ConnectionFactory.getConnection();

        try {
            // Creamos y ejecutamos la sentencia sql preparada
            String sql = "select * from equipos where idEquipo = ?";
            preparada = conexion.prepareStatement(sql);
            preparada.setString(1, id);
            resultado = preparada.executeQuery();
            resultado.next();
            equipo.setIdEquipo(resultado.getInt("idEquipo"));
            equipo.setMarca(resultado.getString("marca"));
            equipo.setNumSerie(resultado.getString("numSerie"));
        } catch (SQLException e) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", e);
        }

        closeConnection();

        return equipo;
        
    }
    
    @Override
    public void updateFinEquipo(String id, String marca, String numSerie) {
        
        Connection conexion = ConnectionFactory.getConnection();

        try {
            preparada = conexion.prepareStatement("update equipos set marca=?, numSerie=? where idEquipo=?");
            preparada.setString(1, marca);
            preparada.setString(2, numSerie);
            preparada.setString(3, id);
            preparada.executeUpdate();
        } catch (SQLException e) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", e);
        }

        closeConnection();
        
    }

    @Override
    public void deleteEquipo(String[] ids) {
        
        Connection conexion = ConnectionFactory.getConnection();

        try {
            // Creamos y ejecutamos la sentencia sql preparada
            String sql = "delete from equipos where idEquipo in(?";
            for (int i = 1; i < ids.length; i++) {
                sql = sql.concat(",?");
            }
            sql = sql.concat(")");
            preparada = conexion.prepareStatement(sql);
            for (int i = 1; i <= ids.length; i++) {
                preparada.setString(i, ids[i - 1]);
            }
            preparada.executeUpdate();
        } catch (SQLException e) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", e);
        }
        
    }

}
