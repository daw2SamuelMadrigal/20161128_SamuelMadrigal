package es.albarregas.DAO;

import es.albarregas.DAOFACTORY.ConnectionFactory;
import es.albarregas.beans.Alumno;
import es.albarregas.beans.Equipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class AlumnosDAO implements IAlumnosDAO {

    private PreparedStatement preparada = null;
    private ResultSet resultado = null;
    private PreparedStatement preparada2 = null;
    private ResultSet resultado2 = null;
    // logger general para toda la aplicación
    final static Logger LOGGER = Logger.getRootLogger();
    // logger destinado a llevar un registro de las diferentes operaciones exitosas
    final static Logger DESC = Logger.getLogger(AlumnosDAO.class);

    @Override
    public void addAlumno(Alumno a) {

        Connection conexion = ConnectionFactory.getConnection();

        try {
            // Creamos y ejecutamos la sentencia sql preparada
            preparada = conexion.prepareStatement("insert into alumnos values(0, ?, ?, ?)");
            preparada.setString(1, a.getNombre());
            preparada.setString(2, a.getGrupo());
            preparada.setString(3, null);
            preparada.executeUpdate();
            DESC.info("Instrucción SQL insert ejecutada");
        } catch (SQLException ex) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", ex);
        }

        closeConnection();
    }

    @Override
    public ArrayList<Alumno> getAlumnos(String where) {

        Alumno alumno;
        ArrayList<Alumno> listado = new ArrayList();
        int numEquipo;
        Equipo equipo;
        Connection conexion = ConnectionFactory.getConnection();

        try {
            preparada = conexion.prepareStatement("SELECT * FROM ALUMNOS " + where + ";");
            resultado = preparada.executeQuery();
            while (resultado.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(resultado.getInt("idAlumno"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setGrupo(resultado.getString("grupo"));
                numEquipo = resultado.getInt("idEquipo");
                try {
                    preparada2 = conexion.prepareStatement("SELECT * FROM EQUIPOS WHERE idEquipo=" + numEquipo + ";");
                    resultado2 = preparada2.executeQuery();
                    equipo = new Equipo();
                    if (resultado2.next()) {
                        equipo.setIdEquipo(resultado2.getInt("idEquipo"));
                        equipo.setMarca(resultado2.getString("marca"));
                        equipo.setNumSerie(resultado2.getString("numSerie"));
                    }

                    alumno.setEquipo(equipo);

                    DESC.info("Instrucción SQL select ejecutada");
                } catch (SQLException ex) {
                    LOGGER.fatal("Problema al ejecutar la instrucción SQL", ex);
                }

                listado.add(alumno);

                try {
                    if (preparada2 != null) {
                        preparada2.close();
                    }
                } catch (SQLException ex) {
                    LOGGER.fatal("Problema al cerrar la preparada2", ex);
                }
                try {
                    if (resultado2 != null) {
                        resultado2.close();
                    }
                } catch (SQLException ex) {
                    LOGGER.fatal("Problema al cerrar el resultado2", ex);
                }
            }
            DESC.info("Instrucción SQL select ejecutada");
        } catch (SQLException ex) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", ex);
        }

        closeConnection();

        return listado;
    }

    @Override
    public ArrayList<Alumno> getAlumnosEquipo() {
        throw new UnsupportedOperationException("Not supported yet.");
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
    public Alumno updateAlumno(String id) {

        Alumno alumno = new Alumno();
        Connection conexion = ConnectionFactory.getConnection();

        try {
            // Creamos y ejecutamos la sentencia sql preparada
            String sql = "select * from alumnos where idAlumno = ?";
            preparada = conexion.prepareStatement(sql);
            preparada.setString(1, id);
            resultado = preparada.executeQuery();
            resultado.next();
            alumno.setIdAlumno(resultado.getInt("idAlumno"));
            alumno.setNombre(resultado.getString("nombre"));
            alumno.setGrupo(resultado.getString("grupo"));
            DESC.info("Instrucción SQL delete ejecutada");
        } catch (SQLException e) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", e);
        }

        closeConnection();

        return alumno;
    }

    @Override
    public void deleteAlumno(String[] ids) {

        Connection conexion = ConnectionFactory.getConnection();

        try {
            // Creamos y ejecutamos la sentencia sql preparada
            String sql = "delete from alumnos where idAlumno in(?";
            for (int i = 1; i < ids.length; i++) {
                sql = sql.concat(",?");
            }
            sql = sql.concat(")");
            preparada = conexion.prepareStatement(sql);
            for (int i = 1; i <= ids.length; i++) {
                preparada.setString(i, ids[i - 1]);
            }
            preparada.executeUpdate();
            DESC.info("Instrucción SQL delete ejecutada");
        } catch (SQLException e) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", e);
        }

        closeConnection();
    }

    @Override
    public void updateFinAlumno(String id, String nombre, String grupo) {

        Connection conexion = ConnectionFactory.getConnection();

        try {
            preparada = conexion.prepareStatement("update alumnos set nombre=?, grupo=? where idAlumno=?");
            preparada.setString(1, nombre);
            preparada.setString(2, grupo);
            preparada.setString(3, id);
            preparada.executeUpdate();
            DESC.info("Instrucción SQL delete ejecutada");
        } catch (SQLException e) {
            LOGGER.fatal("Problema al ejecutar la instrucción SQL", e);
        }

        closeConnection();

    }

}
