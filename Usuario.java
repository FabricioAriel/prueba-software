package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import Conexion.Conexion;
import Vistas.Historial;

public class Usuario {
		protected String idUsuario;
		protected String genero;
		protected String clave;
		protected int telefono;
		public String getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}
		public String getGenero() {
			return genero;
		}
		public void setGenero(String genero) {
			this.genero = genero;
		}
		public String getClave() {
			return clave;
		}
		public void setClave(String clave) {
			this.clave = clave;
		}
		public int getTelefono() {
			return telefono;
		}
		public void setTelefono(int telefono) {
			this.telefono = telefono;
		}
		public void agregar() {
			try {
			Conexion con = new Conexion();
			String sql="INSERT INTO medico VALUES('"+getIdUsuario()+"','"+getGenero()+"','"+getClave()+"',"+getTelefono()+")";
			Connection c=(Connection) con.getConexion();
			Statement st = (Statement) c.createStatement();
			st.executeUpdate(sql);
			c.close();
			JOptionPane.showMessageDialog(null, "Se ha registrado con éxito");
			}
			catch(MySQLIntegrityConstraintViolationException ex) {
		    	JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe");
		    }
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			
			
			
		}
		public boolean validar() {
			try {
				boolean t=true;
			Conexion con = new Conexion();
			String sql="SELECT idUsuario FROM medico";
			Connection c=(Connection) con.getConexion();
			PreparedStatement mst=(PreparedStatement) c.prepareStatement(sql);
	    	ResultSet rs=mst.executeQuery();
	    	ArrayList<String>usuario=new ArrayList<String>();
	    	while(rs.next()) {
	    		usuario.add(rs.getString("idUsuario"));
	    	}
			c.close();
			for(int i=0;i<usuario.size();i++) {
				if(usuario.get(i).equals(getIdUsuario())) {
					t=false;
					break;
				}
			}
			return t;
			}
			catch(SQLException ex) {
				return false;
			}
		}
		public void cambioclave(){
			try {
				Conexion con= new Conexion();
				Connection c= con.getConexion();
				Statement st=(Statement) c.createStatement();
				String sql="UPDATE medico SET Clave= '"+getClave()+"' WHERE idUsuario='"+getIdUsuario()+"'";
				st.executeUpdate(sql);
				c.close();
				JOptionPane.showMessageDialog(null, "Clave Cambiada");
			}
			catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al cambiar de clave");
			}
			
		}
}