package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import Conexion.Conexion;

public class paciente {
	private static final int UNIQUE_CONSTRAINT_VIOLATED = 0;
	private String idPaciente; 
	 private String ApPaterno;
   private String ApMaterno; 
    private String Nombre; 
    private String Genero; 
    private String Telefono;
    private String Celular;
    private String idUsuario;
	
	
    public paciente(String idPaciente, String apPaterno, String apMaterno, String nombre, String genero,
			String telefono, String celular, String idUsuario) {
		super();
		this.idPaciente = idPaciente;
		ApPaterno = apPaterno;
		ApMaterno = apMaterno;
		Nombre = nombre;
		Genero = genero;
		Telefono = telefono;
		Celular = celular;
		this.idUsuario = idUsuario;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public String getApPaterno() {
		return ApPaterno;
	}

	public String getApMaterno() {
		return ApMaterno;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getGenero() {
		return Genero;
	}

	public String getTelefono() {
		return Telefono;
	}

	public String getCelular() {
		return Celular;
	}
	public String getIdUsuario() {
		return idUsuario;
	}

	public void insertar() {
    try{
    	Connection miconexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/Agenda_Medica","root","");
		Statement mst= miconexion.createStatement();
    	String datosPaciente="INSERT INTO PACIENTE VALUES("+getIdPaciente()+","+"'"+getApPaterno()+"'"+","+"'"+getApMaterno()+"'"+","+"'"
		+getNombre()+"'"+","+"'"+getGenero()+"'"+","+getTelefono()+","+getCelular()+",'"+getIdUsuario()+"')";
		mst.executeUpdate(datosPaciente);
		JOptionPane.showMessageDialog(null, "Paciente Agregado");
    	}
    catch(MySQLIntegrityConstraintViolationException ex) {
    	JOptionPane.showMessageDialog(null, "La cédula de identidad ingresada ya existe");
    }
    catch(SQLException ex) {
        if (ex.getErrorCode() == UNIQUE_CONSTRAINT_VIOLATED) {
            JOptionPane.showMessageDialog(null, "El CI del registro ya existe");
    	}
        else {
        	JOptionPane.showMessageDialog(null, "Error en la conexión, uno de los campos es incorrecto");
        }
    }
    }
    public void modificar() {
    	Connection miconexion;
		try {
			miconexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Agenda_Medica","root","");
			Statement mst= miconexion.createStatement();
			String sql="UPDATE paciente SET ApPaterno= '"+getApPaterno()+"' "+", ApMaterno='"+getApMaterno()+"' "+", Nombre='"+getNombre()+"' ,Genero= '"
					+getGenero()+"' ,Telefono= "+getTelefono()+" ,Celular= "+getCelular()+" where idPaciente="+getIdPaciente() +" and idUsuario ="
					+"'"+getIdUsuario()+"'";
			mst.executeUpdate(sql);
			miconexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
	public void eliminar() {
		try {
			PreparedStatement consulta=null;
			Conexion con=new Conexion();
			Connection c=con.getConexion();
			consulta=(PreparedStatement) c.prepareStatement("DELETE FROM paciente WHERE idPaciente="+getIdPaciente()+" and idUsuario='"+getIdUsuario()+"';");
			consulta.execute();
			c.close();
			JOptionPane.showMessageDialog(null, "Paciente eliminado");
		}
		catch(SQLException ex) {
			
		}
	}
	public boolean validar() {
		try {
			boolean t=true;
		Conexion con = new Conexion();
		String sql="SELECT idUsuario,idPaciente FROM paciente";
		Connection c=(Connection) con.getConexion();
		PreparedStatement mst=(PreparedStatement) c.prepareStatement(sql);
    	ResultSet rs=mst.executeQuery();
    	ArrayList<String>usuario=new ArrayList<String>();
    	ArrayList<String>paciente=new ArrayList<String>();
    	while(rs.next()) {
    		usuario.add(rs.getString("idUsuario"));
    		paciente.add(rs.getString("idPaciente"));
    	}
		c.close();
		for(int i=0;i<usuario.size();i++) {
			if(usuario.get(i).equals(getIdUsuario()) && paciente.get(i).equals(getIdPaciente())) {
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
	
	
    
}