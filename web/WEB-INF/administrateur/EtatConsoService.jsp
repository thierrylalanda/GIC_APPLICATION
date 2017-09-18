<%-- 
    Document   : controlleur
    Created on : 18 avr. 2017, 16:50:59
    Author     : lalanda
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Espace Budget
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#"><i class="icon-group"></i> Budget</a>

    </li>

    <li class="pull-right search-wrap">
        <form action="search_result.html" class="hidden-phone">
            <div class="input-append search-input-area">
                <input class="" id="appendedInputButton" type="text">
                <button class="btn" type="button"><i class="icon-search"></i> </button>
            </div>
        </form>
    </li>
</ul>

<div class="widget orange">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Espace Budget</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">
            <c:set var="total" value="${0}"/>
            <div class="classic-search">
                <table class="table table-hover table-responsive table-bordered simple_print"cellspacing="0" width="100%">
                    <thead >
                        <tr>
                            <th>CATEGORIE</th>
                            <th>CODE</th>
                            <th>DESIGNATION</th>
                            <th>QUANTITE</th>
                            <th>PRIX UNITAIRE</th>
                            <th>PRIX TOTAL</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr  class="text-capitalize text-center text-success text-primary text-info">
                            <td colspan="8">
                                La Somme Total est de:<f:formatNumber value="${total}" type="CURRENCY" currencySymbol="FCFA"/>
                            </td>

                        </tr>
                    </tfoot>
                    <tbody id="tbody">
                        <c:forEach items="${operation}" var="com">
                            <tr class="produits">
                                <td class="categories"><c:out value="${com.getCategorie()}"/></td>
                                <td class="code"><c:out value="${com.getCodeProduit()}"/></td>
                                <td class="pt"><c:out value="${com.getDesignation()}"/></td>
                                <td class="qte"><f:formatNumber value="${com.getQuantite()}" type="NUMBER"/></td>
                                <td class="pu"><f:formatNumber value="${com.getPrix()}" type="NUMBER"/></td>
                                <td class="pt"><f:formatNumber value="${com.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA" maxFractionDigits="0"/></td>
                            </tr>
                            <c:set var="total" value="${total + com.getPrixTotal()}"/>
                        </c:forEach>

                    </tbody>

                </table>
            </div>

            <div class="space20"></div>

        </div>
    </div>
</div>


