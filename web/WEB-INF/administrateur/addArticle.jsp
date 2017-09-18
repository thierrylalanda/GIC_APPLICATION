<%-- 
    Document   : addArticle
    Created on : 11 août 2017, 10:04:17
    Author     : Administrateur
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<ul class="nav nav-tabs" id="myTabs">
    <li class="active"><a href="#form-articles" data-toggle="tab">depuis un formulaire</a> </li>
    <li class=""><a href="#fich-articles" data-toggle="tab">depuis un fichier</a> </li>

</ul>
<div class="tab-content" id="myTabsContent">
    <div id="form-articles" class="tab-pane fade in active">
        <h3 class="text-info">Ajouter un articles</h3>
        <div class="row-fluid">

            <form class="form-inline" method="post" role="form" action="parametre?vue=articles&action=Addarticles" >
                <div class="row-fluid">
                    <div class="control-group span4">
                        <label class="control-label"> Categorie</label>
                        <div class="controls ">
                            <select class="form-control span12" id="categorie" required name="categorie">
                                <c:if test="${not empty categories}">
                                    <c:forEach  items="${categories}" var="cat">
                                        <option value="${cat.getIdCategorieProduit()}">${cat.getTypeCategorie()}</option>
                                    </c:forEach>
                                </c:if>

                            </select>
                        </div>
                    </div>

                    <div class="control-group span4">
                        <label class="control-label">Code</label>
                        <div class="controls ">
                            <input type="text" class="form-control span12" required name="code"/>

                        </div>
                    </div>
                    <div class="control-group span4">
                        <label class="control-label"> Designation</label>
                        <div class="controls ">
                            <input type="text" class="form-control span12" required name="designation"/>
                        </div>
                    </div>

                </div>
                <div class="row-fluid">
                    <div class="control-group form-group span4">
                        <label class="control-label">Prix Unitaire</label>
                        <div class="controls ">
                            <input type="number" class=" form-control span12" name="pu" required placeholder="Prix Unitaire">
                        </div>
                    </div>
                    <div class="control-group form-group span4">
                        <label class="control-label">stock alerte</label>
                        <div class="controls ">
                            <input type="number" class=" form-control span12" name="stock_alerte" required placeholder="stock alerte">
                        </div>
                    </div>
                    <div class="span4">
                        <div class="control-group ">
                            <button type="submit" class="btn btn-success btn-large span12 btn-lg" style="margin-top: 22px" >Enregistrer</button>
                        </div>
                    </div>
                </div>

                <div class="space15"></div>

            </form>
            <c:if test="${not empty message}">
                <div class="alert alert-danger  span12 text-center ">
                    <strong>${message.getMessage()}</strong>
                </div>
            </c:if>
        </div>
        <div class="space20"></div>
        <div class="space20"></div>
        <div class="space20"></div>
    </div>


    <div id="fich-articles" class="tab-pane fade ">
        <div class="row-fluid">
            <form method="post" role="form"  id="addstockform" enctype="multipart/form-data" action="UploadFileArticles?vue=articles">
                <div class="row-fluid">

                    <div class="control-group form-group span4">
                        <label class="control-label">Insere un fichier</label>
                        <div class="controls ">
                            <input type="file" class="form-control" name="uploadFile"/>
                        </div>
                    </div> 
                </div>
                <div class="space15"></div>
                <div class="space15"></div>
                <div class="row-fluid">
                    <div class="control-group span12">
                        <button type="submit" class="btn btn-success btn-large btn-block btn-lg" id="addstockbtn" >Inserer</button>
                    </div>
                </div>
            </form>
            <div class="space15"></div>
            <div class="space15"></div>
            <p class="text-center text-info text-capitalize"><strong>Voici Un Example Du Formatage Du Fichier Excel</strong></p>
            <p class="text-danger" style="color: red">Rassurer Vous Que le Fichier Excel Que Vous avez charger est Sur cette forme:</p>
            <p class="text-danger" style="color: red">ne pas introduit la premiere ligne de l'exemple dans votre fichier excel</p>

            <img src="images/articles.png" alt="Exemple de fichier Excel"/>
        </div>
    </div>
</div>