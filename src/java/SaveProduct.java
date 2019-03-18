import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class SaveProduct extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String s1="",s2="",s3="";
        byte b[]=null;
        try{
            
            DiskFileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload upload=new ServletFileUpload(factory);
            List<FileItem> items=upload.parseRequest(new ServletRequestContext(request));
            
            for(FileItem item:items){
                String name=item.getFieldName();
                if(name.equals("t1")){
                    s1=item.getString();
                }else if(name.equals("t2")){
                    s2=item.getString();
                }else if(name.equals("t3")){
                    s3=item.getString();
                }else if(name.equals("f1")){
                    b=item.get();
                }
            }
            String sql="insert into products values(?,?,?,?)";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata", "root", "root");
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(s1));
            ps.setString(2, s2);
            ps.setInt(3, Integer.parseInt(s3));
            ps.setBytes(4, b);
            ps.executeUpdate();
            out.println("<h3>Data Stored</h3>");
            out.println("<h5><a href=productentry.jsp>Add-More</a></h5>");
            out.println("<h5><a href=index.jsp>Home</a></h5>");
            con.close();
            
        }catch(Exception e){
            out.println(e);
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
