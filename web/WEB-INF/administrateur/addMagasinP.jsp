<%-- 
    Document   : addMagasin
    Created on : 25 avr. 2017, 21:19:35
    Author     : lalanda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form class="form-inline" method="POST" role="form" action="admin?vue=createMagP&action=addMagasinP">
    <div id="tabsleft" class="tabbable tabs-left">
        <ul style="margin-top: 25px;margin-left: 50px">
            <li><a href="#tabsleft-tab1" data-toggle="tab"><span class="strong">Etape 1</span> <span class="muted">Details Magasin</span></a></li>
        </ul>
        <div class="progress progress-info progress-striped">
            <div class="bar"></div>
        </div>
        <div  class="tab-content">

            <div class="tab-pane" id="tabsleft-tab1">
                <h3>Renseignement Des Donnees Du Magasin principal <strong>Etape 1</strong></h3>
                <hr>
                <div class="row-fluid">
                    <div class="control-group span4">
                        <label class="control-label">Region</label>
                        <div class="controls ">
                            <select name="region" id="" required class="form-control input-lg region_site" >
                                <option></option>
                                <c:forEach items="${regions}" var="region">
                                    <option value="${region.getIdRegion()}" class="region_site">${region.getNomRegion()}</option>

                                </c:forEach>
                            </select>	

                        </div>
                    </div>
                    <div class="control-group span4">
                        <label class="control-label" for="site_service">Site</label>
                        <div class="controls ">
                            <select name="site" id="site" class="form-control input-lg" required >

                            </select>	

                        </div>
                    </div>
                    <div class="control-group form-group span4">
                        <label class="control-label">Categories Produits</label>
                        <div class="controls ">
                            <select  name="type_categorie" id="articlesCateories" multiple="multiple" required class="form-control input-lg">
                                <c:forEach items="${type_categorie}" var="catego">
                                    <c:if test="${catego.getArticleList().size() > 0}">
                                        <option ><c:out value="${catego.getTypeCategorie()}"></c:out></option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="control-group form-group ">
                        <label class="control-label">Nom Magasin</label>
                        <div class="controls ">

                            <input type="text" class=" input-lg form-control magasinP span6" name="nom_magasin" required >
                        </div>
                    </div>
                </div>

                <div class="tab-pane" id="tabsleft-tab1">

                    <div class="row-fluid">
                        <div class="control-group span6">
                            <label class="control-label"> Description</label>
                            <div class="controls ">
                                <textarea class="form-control description span12" required name="description"></textarea>		
                            </div>
                        </div>
                    </div>
                </div>
                <div class="space20"></div>
                <div class="space20"></div>

            </div>
        </div>
        <hr>

        <div class="text-center span12" style="margin-left: 300px">
            <button type="submit" class="btn btn-large btn-lg btn-success span6">enregistre</button>
        </div>
    </div>

</form>



