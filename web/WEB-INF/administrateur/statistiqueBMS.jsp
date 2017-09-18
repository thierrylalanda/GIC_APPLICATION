
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set value="${idMS}" var="id_magasinMS" scope="session"></c:set>
    <div class="row-fluid">
        <div class="span12">
            <!-- BEGIN CHART PORTLET-->
            <div class="widget black">
                <div class="widget-title">

                    <h4><i class="icon-reorder"> </i> Statistique Budgetaire De Consommation du: ${magasin.getNomMagasin()}</h4>
                <span class="tools">
                    <a href="javascript:;" class="icon-chevron-down"></a>

                </span>
            </div>
            <div class="widget-body">
                <div class="space20"></div>
                <div class="space20"></div>
                <c:set  value="${['green','purple','terques','red']}" var="t"/>
                <c:set  value="${0}" var="i"/>
                <h2 class="text-center">Etat Du Budget Pour L'annee 2017 Du  : ${magasin.getNomMagasin()}</h2>
                <div class="row-fluid">

                    <c:forEach items="${magasin.getButgetList()}" var="bud">
                        <div class="span3">
                            <div class="pricing-table ${t[i]}">
                                <div class="pricing-head ">
                                    <h3> ${bud.getTypeBudget()} </h3>
                                    <c:set  value="${(i+1)%4}" var="i"/>
                                </div>
                                <ul>
                                    <li><strong>Budget </strong> <f:formatNumber value="${bud.getMontant()}"type="CURRENCY" currencySymbol="FCFA"/></li>
                                    <li><strong>Utiliser </strong> <f:formatNumber value="${bud.getMontant()-bud.getMontantRestant()}"type="CURRENCY" currencySymbol="FCFA"/> 
                                        <br><span class="badge "> ${(bud.getMontant()-bud.getMontantRestant())/bud.getMontant() *100} %</span></li>
                                        <c:if test="${bud.getMontantRestant()< 0}">
                                        <li><strong>Reste </strong> <f:formatNumber value="0"type="CURRENCY" currencySymbol="FCFA"/> </li>

                                    </c:if>
                                    <c:if test="${bud.getMontantRestant()> 0}">
                                        <li><strong>Reste </strong> <f:formatNumber value="${bud.getMontantRestant()}"type="CURRENCY" currencySymbol="FCFA"/> 
                                            <br><span class="badge "> ${(bud.getMontantRestant())/bud.getMontant() *100} % </span></li>
                                        </c:if>    
                                        <c:if test="${bud.getMontantRestant()< 0}">
                                        <li class="" style="background-color: red;"><strong>deficit </strong> <f:formatNumber value="${bud.getMontantRestant()}"type="CURRENCY" currencySymbol="FCFA"/>  </li>
                                        </c:if>
                                    <li><strong>Attribution </strong> <f:formatDate value="${bud.getDateDatribution()}" type="Date" dateStyle="MEDIUM"/></li>
                                    <li><strong>Expire le</strong> <f:formatDate value="${bud.getDateExpiration()}" type="Date" dateStyle="MEDIUM"/></li>

                                </ul>

                            </div>
                        </div>

                    </c:forEach>




                </div>
                <hr>
            </div>
        </div>
        <!-- END CHART PORTLET-->
    </div>
</div>