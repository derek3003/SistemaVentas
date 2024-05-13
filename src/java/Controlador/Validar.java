/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Derek Ruiz
 */
public class Validar extends HttpServlet {

   EmpleadoDAO edao=new EmpleadoDAO();
    Empleado em=new Empleado();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }
            
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String accion=request.getParameter("accion");
       if(accion.equalsIgnoreCase("ingresar")){
           String user=request.getParameter("txtuser");
           String pass=request.getParameter("txtpass");
           em=edao.validar(user, pass);
           if(em.getUser()!=null){
               request.setAttribute("usuario", em);
               request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
               
               
           }else{
               request.getRequestDispatcher("index.jsp").forward(request, response);
               System.out.println("No entra");
           }
       }
       else{
             request.getRequestDispatcher("index.jsp").forward(request, response);
       }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
