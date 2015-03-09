/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.geniustree.intership.exampleserlet;

import java.io.IOException;//
import java.io.PrintWriter;
import java.sql.Connection;//
import java.sql.DriverManager;//
import java.sql.SQLException;
import java.sql.Statement;//
import java.util.logging.Level;//
import java.util.logging.Logger;//
import javax.servlet.ServletException;//
import javax.servlet.annotation.WebServlet;//
import javax.servlet.http.HttpServlet;//
import javax.servlet.http.HttpServletRequest;//
import javax.servlet.http.HttpServletResponse;//

/**
 *
 * @author oom
 */
@WebServlet(urlPatterns = "/my")
public class Myserlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        System.out.println(id + " " + name + " " + sex);
        PrintWriter writer = resp.getWriter();
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", null);
            Statement createStatement = connection.createStatement();
            int ex = createStatement.executeUpdate("INSERT INTO TEST (ID,NAME,SEX) VALUES ('" + id + "','" + name + "','" + sex + "')");
            
            writer.append("insert row = " + ex);
        } catch (Exception e) {
            Logger.getLogger(Myserlet.class.getName()).log(Level.SEVERE, null, e);
            writer.append(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    
                } catch (SQLException e) {
                    Logger.getLogger(Myserlet.class.getName()).log(Level.SEVERE, null, e);
                   
                }
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
