
package config;


import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
   Connection con;
 /*&  String url="jdbc:mysql://localhost:3306/bd_ventas";
   String user="root";
   String pass="";*/
   public Connection Conexion(){
       try {
           Class.forName("org.gjt.mm.mysql.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost/bd_ventas","root","");
           System.out.println("se conecto");
       } catch (Exception e) {
           System.out.println(String.valueOf(e));
           System.out.println("no se conecto");
       }
       return con;
   }
}
