package Clases;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Conexion.Conexion;

public class Observacion {
		protected int idCita;
		protected String Observacion;
		public int getIdCita() {
			return idCita;
		}
		public void setIdCita(int idCita) {
			this.idCita = idCita;
		}
		public String getObservacion() {
			return Observacion;
		}
		public void setObservacion(String observacion) {
			Observacion = observacion;
		}
		public void modificar() {
			try {
				Conexion con = new Conexion();
				Connection c=(Connection) con.getConexion();
				String sql="UPDATE observacion SET Observacion ='"+getObservacion()+"' WHERE idCita= "+getIdCita();
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
				String sql="DELETE FROM observacion WHERE idCita= "+getIdCita();
				Statement st=(Statement) c.createStatement();
				st.executeUpdate(sql);
				c.close();
				JOptionPane.showMessageDialog(null, "Se han eliminado las observaciones");
			}
			catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, "No se pudo eliminar las observaciones");
			}
		}
		
}