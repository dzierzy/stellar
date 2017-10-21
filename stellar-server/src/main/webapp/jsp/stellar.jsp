<%@ page import="java.util.List, pl.com.sages.stellar.dto.Page, pl.com.sages.stellar.entities.Constellation"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="stylesheet" href="./css/style2.css"/>
        <link href="https://fonts.googleapis.com/css?family=Press+Start+2P" rel="stylesheet">
    </head>
    <body>
        <header>
             <h1>S t e l l a r</h1>
        </header>
        <section>
            <table>
                <tr><th>Name</th><th>Abbreviation</th></tr>

                 <%
                  Page<Constellation> pageC = (Page<Constellation>) request.getAttribute ("constellations");
                  for(Constellation c : pageC.getItems()){
                  %>
                      <tr>
                          <td><%=c.getName()%></td>
                          <td><%=c.getId()%></td>
                      </tr>
                 <% } %>
            </table>
            <div>
            <% for(int i=1; i<=pageC.getPageCount(); i++){  %>
                <span>
                    <a href="./servlet?pageNo=<%=i%>"><%=i%></a>

                </span>

            <% } %>
            </div>
        </section>

    </body>
</html>