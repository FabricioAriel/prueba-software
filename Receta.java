package Clases;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Conexion.Conexion;

public class Receta {
	protected int idCita;
	protected String Receta;
	public int getIdCita() {
		return idCita;
	}
	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}
	public String getReceta() {
		return Receta;
	}
	public void setReceta(String receta) {
		Receta = receta;
	}
	public void modificar() {
		try {
			Conexion con = new Conexion();
			Connection c=(Connection) con.getConexion();
			String sql="UPDATE receta SET Receta ='"+getReceta()+"' WHERE idCita= "+getIdCita();
			Statement st=(Statement) c.createStatement();
			st.executeUpdate(sql);
			c.close();
			JOptionPane.showMessageDialog(null, "Se ha guardado la modificación");
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "No se pudo guardar la modificación");
		}
	}
	public void eliminar() {
		try {
			Conexion con = new Conexion();
			Connection c=(Connection) con.getConexion();
			String sql="DELETE FROM receta WHERE idCita= "+getIdCita();
			Statement st=(Statement) c.createStatement();
			st.executeUpdate(sql);
			c.close();
			JOptionPane.showMessageDialog(null, "Se ha eliminado la receta");
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "No se pudo elmienar la receta "+ex);
		}
	}

}