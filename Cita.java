package Clases;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Conexion.Conexion;

public class Cita {
		protected String idUsuario;
		protected int idCita;
		protected int idPaciente;
		protected String idTipo;
		protected String fecha;
		protected String hora;
		public String getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}
			
		public int getIdCita() {
			return idCita;
		}
		public void setIdCita(int idCita) {
			this.idCita = idCita;
		}
		public int getIdPaciente() {
			return idPaciente;
		}
		public void setIdPaciente(int idPaciente) {
			this.idPaciente = idPaciente;
		}
		public String getIdTipo() {
			return idTipo;
		}
		public void setIdTipo(String idTipo) {
			this.idTipo = idTipo;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getHora() {
			return hora;
		}
		public void setHora(String hora) {
			this.hora = hora;
		}
		public void agregar(){
			try {
			Conexion con = new Conexion();
			Connection c=(Connection) con.getConexion();
			Statement mst=c.createStatement();
	    	String sql="INSERT INTO cita (idPaciente,idTipo,Fecha,hora) VALUES("+getIdPaciente()+",'"+getIdTipo()+"',"+"'"+getFecha()+"'"+","+"'"
			+getHora()+"');";
			mst.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Cita Agregada");
	    	}
	    catch(SQLException ex) {
	    		ex.printStackTrace();
	    	}
	        			
		}
		public void modificar() {
			try {
			Conexion con = new Conexion();
			Connection c=(Connection) con.getConexion();
			Statement mst=c.createStatement();
	    	String sql="UPDATE cita SET fecha = '"+getFecha()+"', hora='"+getHora()+"' WHERE idCita= "+getIdCita()+" AND idPaciente= "+getIdPaciente();
	    	mst.executeUpdate(sql);
	    	JOptionPane.showMessageDialog(null, "Cita Reprogramada");
			}
			catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, "No se pudo modificar la cita "+ex);
			}
		
		}

		
public boolean validar() {
	try {
		boolean t=true;
		Conexion con = new Conexion();
		Connection c=(Connection) con.getConexion();
    	String sql="SELECT a.hora,a.fecha FROM cita a,paciente b,medico c where a.idPaciente = b.idPaciente and b.idUsuario=c.idUsuario"
    			+ " AND b.idUsuario= '"+getIdUsuario()+"'";
		PreparedStatement mst=(PreparedStatement) c.prepareStatement(sql);
    	ResultSet rs=mst.executeQuery();
    	ArrayList<String> hora=new ArrayList<String>();
    	ArrayList<String> fecha=new ArrayList<String>();
    	while(rs.next()) {
    		hora.add(rs.getString("a.hora"));
    		fecha.add(rs.getString("a.fecha"));
    	}
    	c.close();
    	for (int i=0;i<hora.size();i++) {
    		if(hora.get(i).equals(getHora()) && fecha.get(i).equals(getFecha())) {
    			t=false;
    			break;
    		}
    	}
    	return t;
    
    
	}
	catch(SQLException ex){
		return false;
	}
}
		
}

