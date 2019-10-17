import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaConexionJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("-------- Prueba de Registro de Oracle JDBC ------");
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		System.out.println("¡Oracle JDBC Driver no encontrado!");
		e.printStackTrace();
		return;
		}
		System.out.println("¡Oracle JDBC Driver Registrado!");
		
		
		//antes de esto, debe ejecutar el registro del Driver
		PruebaConexionJDBC prueba = new PruebaConexionJDBC();
		try {
		prueba.conectarYConsultarBD("P09551_2_9", "P09551_2_9_20192");
		} catch (SQLException e) {
		System.out.println("Fallo al conectar! Ver consola de salida");
		System.out.println(e.getMessage());
		e.printStackTrace();
		}
		

	}
	/**
	* Se conecta a la base de datos del curso y ejecuta una consulta de prueba.
	* Luego imprime el resultado en la consola. Al final cierra la conexión.
	*
	* @param username el nombre de usuario
	* @param password la contraseña
	* @throws SQLException si ocurre un error al acceder a la BD
	*/

	public void conectarYConsultarBD(String username, String password)throws SQLException {
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//200.3.193.24:1522/ESTUD",	username,password);
		Statement stmt = con.createStatement();	//crear una sentencia
		ResultSet rs = stmt.executeQuery("SELECT codigo, nombre, promedio_acumulado, fecha_nacimiento FROM Estudiante");	//ejecuta una consulta en la base de datos y la guarda en un cursor

		//recorrer el resultado e imprimirlo
		while (rs.next()) {
			int x = rs.getInt("codigo");
			String s = rs.getString("nombre");
			int f = rs.getInt("promedio_acumulado");
			Date d = rs.getDate("fecha_nacimiento");
			System.out.println(x+","+s+","+f+","+d.toString());
			}
		
		//cerrar la conexión
		con.close();
		
		
	}
	
	
	

}

