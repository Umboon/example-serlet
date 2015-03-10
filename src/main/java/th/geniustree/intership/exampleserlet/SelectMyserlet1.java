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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import th.geniustree.intership.exampleserlet.model.Student;

/**
 *
 * @author oom
 */
@WebServlet(urlPatterns = "/myselect1")
public class SelectMyserlet1 extends HttpServlet {   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        List<Student> students = new ArrayList<>();        
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", null);
            Statement createStatement = connection.createStatement();
            ResultSet se = createStatement.executeQuery("SELECT * FROM TEST"); 
            while (se.next()) {
                Student s = new Student();
                s.setId(se.getInt("ID"));
                s.setName(se.getString("NAME"));
                s.setSex(se.getString("SEX"));
                s.setBod(se.getDate("BOD"));
                students.add(s);
            }
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
        req.setAttribute("students", students);
        req.getRequestDispatcher("/Jsp-ex.jspx").forward(req, resp);
    }
    
}
