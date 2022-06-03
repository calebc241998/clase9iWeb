import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        /*
        Mapeando mi driver en Java
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); //FORMA 1 DE imprimir excepción de try-catch
        }

        String user = "root";
        String pass = "root";
        String ip = "localhost";
        String port = "3306";
        String url = "jdbc:mysql://" + ip + ":" + port + "/hr";


        String sql = "select * from employees";
        try(Connection conn = DriverManager.getConnection(url,user,pass); //guardando conexión en connection
            Statement statement = conn.createStatement(); //statement es como el "carrito" que va viajar por conn
            ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Conexión exitosa");

            //insert, udpdate, delete --> executeUpdate(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString(3);

                Employee e = new Employee();
                e.setId(id);
                e.setFirstName(firstName);
                e.setLastName(lastName);
                listaEmpleados.add(e);


            }
            for (Employee e : listaEmpleados){
                System.out.println("id: " + e.getId() + " | nombre: " + e.getFirstName() + " | apellido: " + e.getLastName());
            }


            /*
            FORMA CON WHILE PARA MOSTRAR A TODOS

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString("first_name");
                String lastName  = resultSet.getString(3);
                System.out.println("id: " + id + " | nombre: " + firstName + " | apellido: " + lastName);
            }
             */


            /*
            FORMA BÁSICA:

             //Primera fila
            resultSet.next();
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString("first_name");
            String lastName  = resultSet.getString(3);

            System.out.println("id: " + id + " | nombre: " + firstName + " | apellido: " + lastName);

           //Segunda fila
            resultSet.next();
            id = resultSet.getInt(1);
            firstName = resultSet.getString("first_name");
            lastName  = resultSet.getString(3);

            System.out.println("id: " + id + " | nombre: " + firstName + " | apellido: " + lastName);
            //Algo más bonito
             */



        } catch (SQLException e) {
           e.printStackTrace(); //FORMA 2 DE imprimir excepción de try-catch
        }
            // Cierro en el orden inverso que los abrí

            /*
            FORMA 1 DE CERRAR
            finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
             */

    }
}