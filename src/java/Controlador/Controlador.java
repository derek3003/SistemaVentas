
package Controlador;

import Modelo.Clientes;
import Modelo.ClientesDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Derek Ruiz
 */
public class Controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Clientes cl = new Clientes();
    ClientesDAO cldao = new ClientesDAO();
    Producto p = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    int idc;
    int idp;
    int ide;
    Venta v = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    String numeroserie;
    VentaDAO vdao = new VentaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
       if(menu.equals("Principal")){
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUser");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                     System.out.println("EntraAeditar");
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUser");
                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);

        }
        if (menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    List lista = cldao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");//se obtiene datos de caja de texto
                    String nom = request.getParameter("txtNombres");
                    String dir = request.getParameter("txtDir");
                    String estado = request.getParameter("txtEstado");
                    cl.setDni(dni);//se guardan en cada variable por medio de la clase clientes
                    cl.setNom(nom);
                    cl.setDir(dir);
                    cl.setEstado(estado);
                    cldao.agregar(cl);//se gurdan datos de clase clientes en la bd por medio del metodo agregar de clientesDAO
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);//para actualizar la tabla con los nuevos datos
                    break;
                    case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));//guardando el id que llega del clientes.jsp
                    Clientes c1 = cldao.listarId(ide);
                     System.out.println("EntraAeditar");
                    request.setAttribute("cliente", c1);//enviar informacion al formulario Clientes.jsp
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);//para actualizar la tabla con los nuevos datos
                    break;
                case "Actualizar":
                    String dni2 = request.getParameter("txtDni");//se obtiene datos de caja de texto
                    String nom2 = request.getParameter("txtNombres");
                    String dir2 = request.getParameter("txtDir");
                    String estado2 = request.getParameter("txtEstado");
                    cl.setDni(dni2);//se guardan en cada variable por medio de la clase clientes
                    cl.setNom(nom2);
                    cl.setDir(dir2);
                    cl.setEstado(estado2);
                    cl.setId(ide);
                    cldao.actualizar(cl);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);//para actualizar la tabla con los nuevos datos
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));//guardando el id que llega de Clientes.jsp
                    cldao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);//para actualizar la tabla con los nuevos datos
                    break;
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {                  
           switch(accion){
           case "Listar":
                    List lista = pdao.listar();
                    request.setAttribute("productos", lista);
           break;
           case "Agregar":
                    String nom = request.getParameter("txtNom");//se obtiene datos de caja de texto
                    String pre = request.getParameter("txtPre");
                    String sto = request.getParameter("txtSto");
                    String estado = request.getParameter("txtEstado");
                    p.setNom(nom);//se guardan en cada variable por medio de la clase empleado
                    p.setPre(pre);
                    p.setSto(sto);
                    p.setEstado(estado);
                    pdao.agregar(p);//se gurdan datos de clase producto en la bd por medio del metodo agregar de ProductoDAO
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//para actualizar la tabla con los nuevos datos
                    break;
                    case "Editar": ///////////////////////////////////////////////
                    ide = Integer.parseInt(request.getParameter("id"));//guardando el id que llega del producto.jsp
                    Producto p1 = pdao.listarId(ide);
                    request.setAttribute("producto", p1);//enaviar informacion al formulario Producto.jsp
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//para actualizar la tabla con los nuevos datos
                    break;
                    case "Actualizar":
                    String nom1 = request.getParameter("txtNom");//se obtiene datos de caja de texto
                    String pre1 = request.getParameter("txtPre");
                    String sto1 = request.getParameter("txtSto");
                    String estado1 = request.getParameter("txtEstado");
                    p.setNom(nom1);//se guardan en cada variable por medio de la clase empleado
                    p.setPre(pre1);
                    p.setSto(sto1);
                    p.setEstado(estado1);
                    p.setId(ide);
                    pdao.actualizar(p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//para actualizar la tabla con los nuevos datos
                    break;
                    case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));//guardando el id que llega del producto.jsp
                    pdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//para actualizar la tabla con los nuevos datos
                    break;
              }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);

        }
        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    cl.setDni(dni);
                    cl = cldao.buscar(dni);
                    request.setAttribute("c", cl);
                      request.setAttribute("nserie", numeroserie);
                    break;
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    p = pdao.listarId(id);
                    request.setAttribute("producto", p);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("c", cl);
                      request.setAttribute("nserie", numeroserie);
                    break;
                case "Agregar":
                    totalPagar = 0.0;
                    item = item + 1;
                    cod = p.getId();
                    descripcion = request.getParameter("nomproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;
                    v = new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);
                    request.setAttribute("c", cl);
                      request.setAttribute("nserie", numeroserie);

                    break;
                case "GenerarVenta":
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO ao = new ProductoDAO();
                        pr = ao.buscar(idproducto);
                        int sac = Integer.parseInt(pr.getSto()) - cantidad;
                        ao.actualizarstock(idproducto, sac);
                    }
                    v.setIdcliente(cl.getId());
                    v.setIdempleado(2);
                    v.setNumserie(numeroserie);
                    v.setFecha("2019-06-14");
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVenta(v);
                    //Guardar detalle venta
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleventas(v);
                    }
                      request.setAttribute("nserie", numeroserie);
                    break;
                default:
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "00000001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.Numeroserie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
