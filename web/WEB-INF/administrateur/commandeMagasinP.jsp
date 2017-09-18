
<%-- 
    Document   : ShowCommandeMS
    Created on : 8 févr. 2017, 13:56:17
    Author     : messi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="row-fluid responsive"> 

    <form method="post" action="passerCommande?vue=editeMagP&action=create&id_magasinP=${magasin.getIdMagasin()}&niveau=5" class="form-inline">
        <div class="span12">
            <label for="selects" class="form-control-label">Fournisseur </label>
            <select  class=" form-control"name="fournisseur" required>
                <c:forEach items="${founisseurs}" var="c">  
                    <option >${c.getNomFounisseur()}</option>  
                </c:forEach>

            </select>               



            <label style="margin-left: 50px" for="selectt" class="form-control-label">Categorie </label>
            <select id="categorie" class=" form-control"name="categorie" required>
                <c:forEach items="${categories}" var="cc">  
                    <option >${cc.getNomCategorie()}</option>  
                </c:forEach>

            </select>


            <label style="margin-left: 50px" for="designation "class="form-control-label " >Designation</label>
            <input type="text"   class=" form-control tag  " required id="designation" name="designation"/>             
        </div>
        <div class="space20"></div>
        <div class="span12">
            <label  style="margin-left: 15px" for="code "  class="control-label">Code</label> 
            <input type="text" id="code" class=" form-control  "  required name="code" />


            <label  style="margin-left: 50px" for="quantite "  class="control-label">Quantite</label> 
            <input type="number" id="quantite" class=" form-control "  required name="quantite" />


            <button  style="margin-left: 50px" type="submit" class="btn btn-success btn-lg btn-large btn-primary pull-righ " >Envoyer</button>

        </div>
    </form>

</div>
<div class="space20"></div>
<div class="space20"></div>
<div class="row-fluid responsive">    
    <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>CATEGORIE</th>
                <th>CODE</th>
                <th>DESIGNATION</th>
                <th>QUANTITE EN STOCK</th>
                <th>QUANTITE COMMANDER</th>
                <th class="hidden">QUANTITE</th>
                <th>DATE LIVRAISON</th>
                <th>DERNIERE LIVRAISON</th>
                <th>FOURNISSEUR</th>
            </tr>
        </thead> 
        <tbody>
            <c:forEach items="${list}" var="lis">  
                <tr class="produits">

                    <td class="categories"><c:out value="${lis.getTypeProduit()}"/></td>
                    <td class="code"><c:out value="${lis.getCodeProduit()}"/></td>
                    <td class="designation"><c:out value="${lis.getDesignation()}"/></td>
                    <td class="qte"><f:formatNumber value="${lis.getQuantiteEnStock()}" type="NUMBER"/></td>
                    <td class="qteu"><f:formatNumber value="${lis.getQuantiteCommande()}" type="NUMBER"/></td>
                    <td class="qteuc hidden"><input class="newvalCMP" type='number' id='qteu'  /></td>                       
                    <td><f:formatDate value="${lis.getDate()}" type="Date" dateStyle="MEDIUM"/></td>                           
                    <td class="pt"><f:formatDate value="${lis.getDerniereLivraison()}"  type="Date" dateStyle="MEDIUM" /></td>
                    <td class="founisseur"><c:out value="${lis.getIdSA().getNomFounisseur()}"/></td>


                </tr>

            </c:forEach>

        </tbody>
    </table> 
</div>
<div class="space20"></div>
<div class="space20"></div>
<div class="space20"></div>
<c:if test="${not empty message}">
    <div class="alert alert-success  span12 text-center ">
        <strong>${message.getMessage()}</strong>
    </div>
</c:if>


<div class="row-fluid">
    <div class="span12">
        <div class="span2">

            <button  class="btn bouton btn-danger disabled btn-lg" id="deleteMP"><i class="icon-remove"></i> supprimer</button>

        </div>
        <div class="span10">
            <form method="post" action="passerCommande?vue=editeMagP&action=save&id_magasinP=${magasin.getIdMagasin()}&niveau=5" class="form-inline">

                <div class="form-group">
                    <label for="example-getting-started" class="form-control-label">Responsable Controle </label>
                    <select id="example-getting-started" multiple="multiple" class="input-mini form-control"  name="responsable" required>
                        <c:forEach items="${valideur}" var="cc">  

                            <option value="${cc.getIdPersonnel()}">${cc.getNomPrenom()}</option>  
                        </c:forEach>

                    </select>


                    <button  style="margin-left: 50px" type="submit" class="btn btn-success  btn-lg btn-large pull-righ " >Envoyer</button>
                </div>   
            </form>
        </div>
    </div>
</div>
<div class="space20"></div>
<div class="space20"></div>
