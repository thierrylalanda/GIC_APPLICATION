
<%-- 
    Document   : Listeproduit
    Created on : 19 nov. 2016, 15:53:48
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-lg-12 view ">

    <c:if test="${al=='init'}" >
        <form method="post" action="Commande_All_Client?vue=listePMP&action=init" class="form-inline" id="commandeClit">
            <div class="form-group">
                <label for="select" class="form-control-label">Magasin </label>
                <select id="select" class="input-mini form-control"name="magasin" required>
                    <c:forEach items="${sessionScope.magasinS}" var="cc">  
                        <option >${cc.getNomMagasin()}</option>  
                    </c:forEach>

                </select>
            </div>
            <div class="form-group pull-righ">
                <button type="submit" class="btn btn-success" >VALIDER</button>
            </div>
        </form>
    </c:if>
    <c:if test="${all=='tous'}" >
        <div class="row" style="padding-top:  10px;margin-top:  10px"> 

            <table class="table table-hover"  cellpadding="0" cellspacing="0" id="commandeClient"cellspacing="0" width="100%">
                <thead class=" thead-inverse">
                    <tr>
                        <th>CATEGORIE</th>
                        <th>CODE</th>
                        <th>DESIGNATION</th>
                        <th>QUANTITE</th>                        
                        <th>DATE LIVRAISON</th>
                    </tr>
                </thead>
                <tbody id="tbody">

                    <c:forEach items="${categorie}" var="c">

                        <tr class="produit">

                            <c:forEach items="${c.getStockproduitMSList()}"var="r">

                                <td scope="row"> <c:out value="${c.getNomCategorie()}"/> </td>
                                <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                <td><c:out value="${r.getDesignation()}"/></td>
                                <td><f:formatNumber value="${r.getQuantite()}" type="NUMBER"/></td>                               
                                <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                            </tr>

                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

</div>
