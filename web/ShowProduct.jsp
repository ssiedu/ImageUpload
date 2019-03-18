<%@page import="java.sql.*" %>
<%
       int id=Integer.parseInt(request.getParameter("t1"));
       String sql="select pcode,pname,price from products where pcode=?";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata", "root", "root");
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String s1=rs.getString(1);
        String s2=rs.getString(2);
        String s3=rs.getString(3);
%>
<html>
    <body>
        <h3>Product-Details</h3>
        <hr>
        <pre>
            PCode   <%=s1%>
            Name    <%=s2%>
            Price   <%=s3%>
            <img width="100" height="100" src="ImageLoader?id=<%=s1%>"/>
        </pre>
        <hr>
    </body>
</html>
