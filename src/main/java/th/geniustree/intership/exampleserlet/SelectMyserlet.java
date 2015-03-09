/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.geniustree.intership.exampleserlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oom
 */
@WebServlet(urlPatterns = "/myselect")
public class SelectMyserlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        System.out.println(id + " " + name + " " + sex);

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", null);
            Statement createStatement = connection.createStatement();
            ResultSet se = createStatement.executeQuery("SELECT * FROM TEST");
            PrintWriter writer = resp.getWriter();
            writer.append("<html>");
            writer.append("<body>");
            writer.append("<Table Border = 1>");
            writer.append("<TH>ID</TH>");
            writer.append("<TH>NAME</TH>");
            writer.append("<TH>SEX</TH>");
        
            while (se.next()) {
                id = se.getString("id");
                name = se.getString("name");
                sex = se.getString("sex");

                writer.append("<TR>");
                
                writer.append("<TD>");
                writer.append(id);
                writer.append("</TD>");
               
                writer.append("<TD>");
                writer.append(name);
                writer.append("</TD>");
           
                writer.append("<TD>");
                writer.append(sex);
                writer.append("</TD>");
                
                writer.append("</TR>");

                //System.out.println(id + " " + name + " " + sex);
                //writer.append(id+" "+name+" "+sex+"\n");
            }
            writer.append("</Table>");
            writer.append("</body>");
            writer.append("</html>");
        } catch (Exception ex) {
            Logger.getLogger(SelectMyserlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SelectMyserlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
