package com.careplan.careplanapp.servlets;

import com.careplan.careplanapp.UserValidationService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.Procedimentos;

//1. extends javax.servlet.http.HttpServlet
//2. @WebServlet(urlPatterns = "/login.do")
//3. doGet(HttpServletRequest request, HttpServletResponse response)
//4. How is the response created?
/**
 *
 * @author Tiago
 */
@WebServlet(urlPatterns = {"/careplanapp", "/main", "/novo", "/insert", "/select", "/update", "/delete", "/report", "/executar"  })
public class careplanhome extends HttpServlet {
    private UserValidationService service = new UserValidationService();
    DAO dao = new DAO();
    Procedimentos procedimento = new Procedimentos();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setContentType("text/html; charset=UTF-8");
        try {
            if(dao.getRowsCount() < 6){
                dao.firstInsertTable();
            }
        } catch (SQLException ex) {
            Logger.getLogger(careplanhome.class.getName()).log(Level.SEVERE, null, ex);
        }

        String action = request.getServletPath();
        try {
            if (action.equals("/main")) {
                procedimentos(request, response);
                request.getSession().removeAttribute("errorMessage");
            } else if (action.equals("/novo")) {
                ServletContext context = this.getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/views/novo.jsp");
                dispatcher.forward(request, response);                
            } else if (action.equals("/insert")){
                    adicionarProcedimento(request, response);
            } else if (action.equals("/select")) {
                    listarProcedimentos(request, response);
            } else if (action.equals("/update")) {
                    editarProcedimento(request, response);
            } else if (action.equals("/delete")) {
                    removerProcedimento(request, response);
            } else if (action.equals("/report")) {
                    gerarRelatorio(request, response);
            } else if (action.equals("/executar")) {
                    executarProcedimento(request, response);
            } else {
                    request.getRequestDispatcher("/views/home.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(careplanhome.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    protected void procedimentos(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException, SQLException {
            ArrayList<Procedimentos> lista = dao.listarProcedimentos();
            request.setAttribute("procedimentos", lista);

            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/views/caremanagement.jsp");
            dispatcher.forward(request, response);
    }


    protected void adicionarProcedimento(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
            procedimento.setProcedimento(request.getParameter("procedimento"));
            procedimento.setIdade(request.getParameter("idade"));
            procedimento.setSexo(request.getParameter("sexo"));
            procedimento.setPermitido(Boolean.parseBoolean(request.getParameter("permitido")));
            String procedimentoID = request.getParameter("procedimento");
            if("1234".equals(procedimentoID) ||
               "4567".equals(procedimentoID) ||
               "6789".equals(procedimentoID) )
            {
                dao.inserirProcedimento(procedimento);     
                response.sendRedirect("main"); 
            } else {
                request.getSession().setAttribute("errorMessage", "Procedimento não Habilitado para cadastro / execução");
                response.sendRedirect("main");
            }
    }
    

    protected void listarProcedimentos(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException, SQLException {
            procedimento.setIdcon(Integer.parseInt(request.getParameter("idcon")));
            dao.selecionarContato(procedimento);
            request.setAttribute("idcon", procedimento.getIdcon());
            request.setAttribute("procedimento", procedimento.getProcedimento());
            request.setAttribute("idade", procedimento.getIdade());
            request.setAttribute("sexo", procedimento.getSexo());
            request.setAttribute("permitido", procedimento.getPermitido());
            RequestDispatcher rd = request.getRequestDispatcher("/views/editar.jsp");
            rd.forward(request, response);
    }
    

    protected void editarProcedimento(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
            procedimento.setIdcon(Integer.parseInt(request.getParameter("idcon")));
            procedimento.setProcedimento(request.getParameter("procedimento"));
            procedimento.setIdade(request.getParameter("idade"));
            procedimento.setSexo(request.getParameter("sexo"));
            procedimento.setPermitido(Boolean.parseBoolean(request.getParameter("permitido")));
            dao.alterarProcedimento(procedimento);
            response.sendRedirect("main");
    }
    
        protected void executarProcedimento(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException, SQLException {
            procedimento.setIdcon(Integer.parseInt(request.getParameter("idcon")));
            procedimento.setPermitido(Boolean.parseBoolean(request.getParameter("permitido")));
            Boolean executou = dao.execProcedimento(procedimento);
            if(!executou){
                request.getSession().setAttribute("errorMessage", "Não Foi possivel executar o procedimento selecionado, verifique permissão. ");                  
            }            
            response.sendRedirect("main");
    }
    

    protected void removerProcedimento(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
            procedimento.setIdcon(Integer.parseInt(request.getParameter("idcon")));
            dao.deletarProcedimento(procedimento);
            response.sendRedirect("main");
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<%=request.getContextPath()%>
         request.setCharacterEncoding("UTF-8");
         response.setContentType("text/html; charset=UTF-8");
         String name = request.getParameter("name");
         String password = request.getParameter("password");
         boolean isValidUser = service.isUserValid(name, password);
        
        if (isValidUser) {
            try {
                 request.setAttribute("name", name);
                 ServletContext context = this.getServletContext();
                 ArrayList<Procedimentos> lista = dao.listarProcedimentos();

                RequestDispatcher dispatcher = context.getRequestDispatcher("/views/caremanagement.jsp");
                request.setAttribute("procedimentos", lista);
                dispatcher.forward(request, response);                                      
             } catch (SQLException ex) {
                 Logger.getLogger(careplanhome.class.getName()).log(Level.SEVERE, null, ex);
             }           
        } else {
            request.setAttribute("errorMessage", "Invalid Credentials!!");
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/views/home.jsp");
            dispatcher.forward(request, response);
        }
                        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>    

    
    protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        Document documento = new Document();
        try {
                response.setContentType("apllication/pdf");
                response.addHeader("Content-Disposition", "inline; filename=" + "procedimentos.pdf");
                PdfWriter.getInstance(documento, response.getOutputStream());
                documento.open();
                documento.add(new Paragraph("Lista de procedimentos:"));
                documento.add(new Paragraph(" "));
                PdfPTable tabela = new PdfPTable(5);
                PdfPCell col1 = new PdfPCell(new Paragraph("Procedimento"));
                PdfPCell col2 = new PdfPCell(new Paragraph("Idade"));
                PdfPCell col3 = new PdfPCell(new Paragraph("Sexo"));
                PdfPCell col4 = new PdfPCell(new Paragraph("Permitido"));
                PdfPCell col5 = new PdfPCell(new Paragraph("Executado"));
                tabela.addCell(col1);
                tabela.addCell(col2);
                tabela.addCell(col3);
                tabela.addCell(col4);
                tabela.addCell(col5);
                ArrayList<Procedimentos> lista = dao.listarProcedimentos();
                for (int i = 0; i < lista.size(); i++) {
                        tabela.addCell(lista.get(i).getProcedimento());
                        tabela.addCell(lista.get(i).getIdade());
                        tabela.addCell(lista.get(i).getSexo());
                        tabela.addCell(lista.get(i).getPermitido() == true ? "SIM" : "NÃO");
                        tabela.addCell(lista.get(i).isExecutado()== true ? "SIM" : "NÃO");
                }
                documento.add(tabela);
                documento.close();
        } catch (Exception e) {
                System.out.println(e);
                documento.close();
        }
    }
}
