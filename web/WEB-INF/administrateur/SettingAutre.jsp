<%-- 
    Document   : SettingResponsable
    Created on : 15 mai 2017, 12:04:47
    Author     : lalanda
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h3 class="page-title">
    Parametre
</h3>
<ul class="breadcrumb">
    <li>
        <a href="#">Home</a>
        <span class="divider">/</span>
    </li>
    <li class="active">
        <a href="#">Parametre</a>

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

<div class="widget green-box">
    <div class="widget-title text-capitalize">
        Autres Configurations
    </div>
    <div class="widget-body">
        <ul class="nav nav-pills">
            <li class="active"><a href="#all-fournisseurs" data-toggle="tab">Fournisseurs</a></li>
                <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                <li><a href="#societe" data-toggle="tab">Donnees de la societe</a></li>
                </c:if>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="all-fournisseurs">
                <c:import url="/WEB-INF/administrateur/settingEditFournisseur.jsp"/>

            </div>
            <div class="tab-pane fade" id="societe">

                <h4>Nouvelle societe</h4>
                <div class="row-fluid">
                    <div class="span8">
                        <form action="personnel?action=addSociete&vue=settingautre" method="post">
                            <div class="row-fluid">
                                <div class="span12">

                                    <label class="control-label">Nom</label>
                                    <input type="text" class=" span6 form-control" name="nom_societe" required placeholder="Nom de la societe"/>

                                    <label class="control-label">Adresse</label>
                                    <input type="text" class=" span6 form-control" name="adresse_societe"required placeholder="adresse de la societe"/>

                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span12 ">
                                    <label class="control-label">Tel:</label>

                                    <input type="tel" class="span6  form-control" name="phone_societe" required placeholder="Telephone"/>

                                    <label class="control-label">code postal</label>

                                    <input type="text" class="span6  form-control" name="code_postal" required placeholder="code postal"/>


                                </div>
                            </div>

                            <div class="row-fluid">
                                <button type="submit" class="btn btn-success span12 btn-large">Enregistrer</button>
                            </div>
                        </form>
                    </div>
                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                        <div class="span4">

                            <form action="fichier?action=addSociete&vue=accueil" id="fileForm" method="post" enctype="multipart/form-data">
                                <div class="control-group">
                                    <label class="control-label">nouveau logo</label>
                                    <div class="controls">
                                        <div data-provides="fileupload" class="fileupload fileupload-new">
                                            <div style="width: 200px; height: 150px;" class="fileupload-new thumbnail">
                                                <img alt="" src="">
                                            </div>
                                            <div style="max-width: 200px; max-height: 150px; line-height: 20px;" class="fileupload-preview fileupload-exists thumbnail"></div>
                                            <div>
                                                <span class="btn btn-file"><span class="fileupload-new">Select image</span>
                                                    <span class="fileupload-exists">Change</span>
                                                    <input type="file" class="default" name="file" id="file"></span>
                                                <a data-dismiss="fileupload" class="btn fileupload-exists" href="#">Remove</a>
                                            </div>
                                        </div>
                                        <span class="label label-important">NOTE!</span>
                                        <span>
                                            choisir un logo de type png
                                        </span>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <button type="submit" class="btn btn-success span6" id="submitfile">nouveau logo</button>
                                </div>
                            </form>
                        </div>
                    </c:if>
                </div>
                <br>           


            </div>

        </div>
    </div>
</div>

