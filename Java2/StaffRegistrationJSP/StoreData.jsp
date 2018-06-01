<%-- StoreData.jsp--%>

<!--Import the address class-->
<%@ page import = "Chapter38.Address" %>
<!--Use beans to retrieve data and store it in the database-->
<jsp:useBean id = "addressId" class = "Chapter38.Address" scope = "session"></jsp:useBean>
<jsp:useBean id = "storeDataId" class = "Chapter38.StoreData" scope = "application">
</jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <%
            storeDataId.storeData(addressId);

            out.println(addressId.getFirstName() + " "
                    + addressId.getLastName()
                    + " is now registered in the database");
            out.close(); // Close stream
        %>
    </body>
</html>
