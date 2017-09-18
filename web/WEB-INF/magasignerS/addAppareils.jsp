<%-- 
    Document   : addStock
    Created on : 19 mai 2017, 07:28:36
    Author     : lalanda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="row-fluid">                  

    <h2>Ajouter un Nouvel Appariel Pour un Personnel</h2>

    <div class="widget ">
        <div class="widget-title">
            <h4><i class="icon-reorder"></i> Nouvel Appariel</h4>

        </div>
        <div class="widget-body ">
            <div class="bs-docs-example">
                <ul class="nav nav-tabs" id="myTab2">
                    <li class="active"><a href="#formulaire" data-toggle="tab">Depuis Un Formulaire</a> </li>
                </ul>

                <div class="tab-content" id="myTabContent2">
                    <div id="formulaire" class="tab-pane fade in active">

                        <form class="form-horizontal" method="POST" action="SettingPersonnal?vue=Appariels&action=AddAppariel&niveau=2">

                            <div class="row-fluid" >

                                <div class="control-group form-group span4">
                                    <label class="control-label">Numero Parck</label>

                                    <input type="text" class="form-control input-lg  span12" name="numeroParck" required/>

                                </div>

                                <div class="control-group span4">
                                    <label class="control-label"> Type Appareil</label>
                                    <select class="select span12" id="" required name="type">
                                        <c:forEach items="${typeappareil}" var="type">
                                            <option value="${type.getId()}">${type.getNom()} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="control-group span4">
                                    <label class="control-label"> Lieu</label>

                                    <select class="select span12" id="" required name="lieu">
                                        <c:forEach items="${sites}" var="site">
                                            <option value="${site.getNomSite()}">${site.getNomSite()} </option>
                                        </c:forEach>
                                    </select>

                                </div>


                            </div>
                            <div class="row-fluid" style="margin-left: 0px">
                                <div class="control-group form-group span4">
                                    <label class="control-label">Numero Serie</label>

                                    <input type="text" class=" input-lg form-control span12" required name="numeroSerie" >

                                </div>

                                <div class="control-group form-group span4">
                                    <label class="control-label">Model </label>
                                    <select class="select span12" id="" required name="model">
                                        <c:forEach items="${models}" var="model">
                                            <option >${model.getNomModel()} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="control-group form-group span4">
                                    <label class="control-label">Fabricant </label>
                                    <select class="select span12" id="" required name="fabricant">
                                        <c:forEach items="${fabriquants}" var="fabri">
                                            <option >${fabri.getNomFabriquant()} </option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>

                            <div class="row-fluid">
                                <div class="control-group form-group span4 ">
                                    <label class="control-label">Proprietaire</label>
                                    <select class="select span12 OneSelect" name="utilisateur">
                                        <option class="select"></option>
                                        <c:forEach items="${personnels}" var="perso">
                                            <option value="${perso.getIdPersonnel()}">${perso.getNomPrenom()} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="space20"></div>
                            <div class="space20"></div>


                            <div class="control-group row-fluid ">
                                <button type="submit"  class="btn btn-success btn-large btn-block btn-lg"  >Inserer</button>

                            </div>


                        </form>
                    </div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                    <div class="space20"></div>
                </div>


            </div>
        </div>
    </div>
</div>



