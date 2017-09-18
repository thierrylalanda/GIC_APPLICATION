<%-- 
    Document   : json
    Created on : 8 mai 2017, 14:17:26
    Author     : messi
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
         <script src="js/produit.js" type="text/javascript"></script>
    </head>
   
    <body>
        <form name="teste" action="Json" method="POST" enctype="multipart/form-data">
            <input type="submit" value="Send" />
        </form>
        <select name="choix" id="ch" >
            
        </select>
        
        
        <table border="1" id="ii" class="table ">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                </tr>
            </thead>
            <tbody id="tab">
                
            </tbody >
        </table>

        <script src="charger.js" type="text/javascript"></script>
    </body>
</html>
