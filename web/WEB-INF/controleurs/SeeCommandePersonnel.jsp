<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true" %>


<!-- BEGIN INLINE TABS PORTLET-->
<div class="widget black">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i>Vos Commandes</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>

        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%" >
                <thead class="text-primary">
                    <tr>
                        <th>DESIGNATION</th>
                        <th>QUANTITE</th>
                        <th>APPARIEL</th>
                        <th>DATE</th>
                        <th>Etat</th>
                    </tr>
                </thead>
                <tbody id="tbody">
                    <c:forEach items="${commandesEncour}" var="list">  
                        <tr class="produits">
                            <td class="designation"><c:out value="${list.getDesignations()}"/></td>
                            <td class="qteu"><f:formatNumber value="${list.getQuantite()}" type="NUMBER"/></td>
                            <td class="app"><c:out value="${list.getCodeAppareil().getNumeroParck()}"/></td>
                            <td class="date"><f:formatDate value="${list.getDate()}" type="BOTH" dateStyle="MEDIUM" timeStyle="MEDIUM"/></td>
                            <td class="app"><c:out value="${list.getEtatCommande()}"/></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table> 
            <hr>
            <c:if test="${not empty reception}">
                <a href="Commande_All_Client?vue=Vos Commandes&action=ApprobationCommandePersonnel&magasin=controleur">  <button type="button" class="btn btn-success  btn-primary btn-lg btn-large " ><i class="icon-user"></i> Oui J'ais récupéré ces Articles</button></a>
            </c:if>
        </div>
        <div class="space20"></div>
    </div>
</div>