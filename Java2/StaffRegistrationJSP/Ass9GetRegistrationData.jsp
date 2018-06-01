<!-- Ass9GetRegistrationData.jsp -->
<!--Import address class-->
<%@ page import = "Chapter38.Address" %>
<!--Create a bean for address and set the properties of it-->
<jsp:useBean id = "addressId"
             class = "Chapter38.Address" scope = "session"></jsp:useBean>
<jsp:setProperty name = "addressId" property = "*" />

<html>
    <head>
    </head>
    <body>
        <h1> Registration Using JSP</h1>
        
        <%--Set up required info--%>
        <%
        if (addressId.getId() == null ||
                addressId.getLastName() == null ||
                addressId.getFirstName()== null) {
            out.println("ID, Last Name, and First Name are required");
            return; //End the method
        }
        %>
        
        <!--Show confirmation data from the bean-->
        <p> You entered the following data</p>
        <p> ID: <%= addressId.getId() %></p>
        <p> Last name: <%= addressId.getLastName() %></p>
        <p> First name: <%= addressId.getFirstName() %></p>
        <p> MI: <%= addressId.getMi() %></p>
        <p> Address: <%= addressId.getLocation() %></p>
        <p> City: <%= addressId.getCity() %></p>
        <p> State: <%= addressId.getState() %></p>
        <p> Telephone: <%= addressId.getTelephone() %></p>
        <p> Email: <%= addressId.getEmail() %></p>
        
        
        <!-- Set the action for processing the answers -->
        <form method = "post" action = "StoreData.jsp">
            <input type = "submit" value = "Confirm">
        </form>
    </body>
</html>