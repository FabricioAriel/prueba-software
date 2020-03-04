package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Conexion.Conexion;
import Vistas.LogIn;

public class DatosUsuario {
	public boolean validar() {
		String usuario=LogIn.dUs.getText();
		String clave=new String(LogIn.pass.getPassword());
		String sql= "SELECT * FROM medico WHERE idUsuario	= '"+usuario+"' AND Clave= '"+clave+"'";
		boolean t=false;
		try {
			Conexion con = new Conexion();
			Connection c=(Connection) con.getConexion();
			PreparedStatement mst;
			mst = (PreparedStatement) c.prepareStatement(sql);
			ResultSet rs = mst.executeQuery();
			if(rs.next()==true) {
				t=true;
			}
			else {
				t=false;
			}
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
		
	}
}
