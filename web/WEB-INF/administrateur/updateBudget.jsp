<%-- 
    Document   : updateBudget
    Created on : 17 août 2017, 07:25:54
    Author     : Administrateur
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form role="form" action="personnel?action=updateBudgetMagasin&vue=budget&id_budget=${budget.getIdBudget()}" method="post">
    <div class="row-fluid">

        <div class="control-group form-group span4 ">
            <label class="control-label">Region</label>
            <div class="controls ">
                <select name="region" class="form-control span12" id="regiontomagasin">
                    <c:forEach items="${regions}" var="reg">
                        <c:if test="${budget.getMagasin().getSite().getRegion().getIdRegion()==reg.getIdRegion()}">
                            <option value="${reg.getIdRegion()}" class="region" selected>${budget.getMagasin().getSite().getRegion().getNomRegion()}</option>
                        </c:if>
                        <c:if test="${budget.getMagasin().getSite().getRegion().getIdRegion()!=reg.getIdRegion()}">
                            <option value="${reg.getIdRegion()}" class="region">${reg.getNomRegion()}</option>
                        </c:if>


                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group form-group span4 ">
            <label class="control-label">Magasin</label>
            <div class="controls ">
                <select name="id_Magasin" class="form-control magasinbyregion span12" id="">
                    <option value="${budget.getMagasin().getIdMagasin()}" class="region">${budget.getMagasin().getNomMagasin()}</option>
                </select>
            </div>
        </div>
        <div class="control-group form-group span4 ">
            <label class="control-label">Type de budget</label>
            <div class="controls ">
                <select name="type_budget" class="form-control span12" id="categorietoarticles">
                    <option value="${budget.getTypeBudget()}" class="region">${budget.getTypeBudget()}</option>
                </select>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="control-group form-group span4 ">
            <label class="control-label">Montant</label>
            <div class="controls ">
                <input type="number" class="  form-control span12" value="${budget.getMontant()}" name="montant" required placeholder="Montant"/>

            </div>
        </div>

        <div class="control-group form-group span4 float-label-control">
            <label class="control-label" for="">date Attribution</label>
            <div class="controls ">
                <input type="text" class="datepicker span12 form-control" name="dateAttribution" required placeholder="date Attribution" value="<f:formatDate value="${budget.getDateDatribution()}" type="DATE" dateStyle="SHORT"/>">

            </div>
        </div>
        <div class="control-group form-group span4 float-label-control">
            <label class="control-label"  for="">date expiration</label>
            <div class="controls ">
                <input type="text" class="datepicker input-lg form-control span12" name="dateExpiration" value="<f:formatDate value="${budget.getDateExpiration()}" type="DATE" dateStyle="SHORT"/>">
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="control-group form-group text-center  float-label-control">
            <div class="controls ">
                <button type="submit" class="btn btn-large btn-success span12">Update</button>
            </div>
        </div>
    </div>
</form>
