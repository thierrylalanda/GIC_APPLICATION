
<%-- 
    Document   : EtatPeriodiqueMP
    Created on : 19 févr. 2017, 14:18:13
    Author     : messi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="row-fluid">
    <div class="span12">
        <div class=" span8">

            <form method="post" action="ListeProduitMP?vue=editeMagP&action=date&id_magasinP=${magasin.getIdMagasin()}&niveau=5" class="form-inline">

                <label for="date1 "class="form-control-label " >DU</label>
                <input class="datepicker form-control"required name="date1" type="text"/> &nbsp;&nbsp;      
                <label for="date2 "class="form-control-label " >AU</label>
                <input class="datepicker form-control" name="date2"  required type="text"/>&nbsp;&nbsp;

                <button type="submit" class="btn btn-success  btn-primary  " id="sendcc" >Search</button>

            </form>
        </div>


        <a href="ListeProduitMP?vue=editeMagP&action=redirect&id_magasinP=${magasin.getIdMagasin()}&niveau=5" <button class="btn btn-warning " >Tous!</button></a>

    </div>
    <div class="span12">
        <form method="post" action="ListeProduitMP?vue=editeMagP&action=One&id_magasinP=${magasin.getIdMagasin()}&niveau=5" class="form-inline" id="commandeClit">
            <div class="form-group pull-right " style="margin-right: 50px" >           
                <select  style="height: 33px" <c:if test="${empty MS}">disabled</c:if>  class="form-control" name="magasinS" required>
                    <c:forEach items="${MS}" var="c"> 
                        <option ><c:out value="${c}"/></option>  
                    </c:forEach>
                </select>&nbsp;&nbsp;


                <button type="submit" <c:if test="${empty MS}">disabled</c:if>  class="btn btn-success  btn-primary " id="sendcc" >Search</button>
                </div>
            </form>
        </div>

    </div>
    <div class="space20"></div>
    <div class="space20"></div>

    <table class="table table-hover table-responsive table-bordered simple_print" cellspacing="0" width="100%">
        <thead class="text-primary">
            <tr>
                <th>DEMANDEUR</th>
                <th>DATE</th>
                <th>CATEGORIE</th>
                <th>CODE</th>
                <th>DESIGNATION</th>
                <th>QUANTITE</th>
                <th>PRIX UNITAIRE</th>
                <th>PRIX TOTAL</th>
            </tr>
        </thead>
        <tbody id="tbody" >
        <c:set var="to" value="${0}" scope="page"></c:set>
        <c:forEach items="${operations}" var="com">
            <tr class="produits">
                <td class="categories"><c:out value="${com.getMs().getNomMagasin()}"/></td>
                <td class="date"><f:formatDate value="${com.getDate()}" type="Date"dateStyle="MEDIUM"/></td>
                <td class="categories"><c:out value="${com.getTypeProduit()}"/></td>
                <td class="code"><c:out value="${com.getCodeProduit()}"/></td>
                <td class="pt"><c:out value="${com.getDesignation()}"/></td>
                <td class="qte"><f:formatNumber value="${com.getQuantite()}"type="NUMBER"/></td>
                <td class="pu"><f:formatNumber value="${com.getPrixUnitaire()}"type="CURRENCY"/></td>
                <td class="pt"><f:formatNumber value="${com.getPrixTotal()}"  type="CURRENCY" currencySymbol="FCFA"/></td>
                <c:set var="to" value="${to + com.getPrixTotal()}" scope="page"></c:set>
                </tr>
        </c:forEach>

    </tbody>
    <tfoot class="columns panel-footer">
        <tr  class="text-capitalize text-center text-success text-primary text-info">                        
            <td colspan="8">
                La Somme Total est de :<f:formatNumber value="${to}"  type="CURRENCY" currencySymbol="FCFA"/></td>

            </td>
        </tr>
    </tfoot>        
</table>

