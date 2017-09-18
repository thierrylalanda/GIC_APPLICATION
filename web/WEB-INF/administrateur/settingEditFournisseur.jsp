<%-- 
    Document   : settingEditFournisseur
    Created on : 15 mai 2017, 16:34:33
    Author     : lalanda
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--Pill Tabs   -->
<div class="widget green-box-blog">
    <div class="widget-title text-capitalize text-info">
        Fournisseurs
    </div>
    <div class="widget-body">
        <ul class="nav nav-pills">
            <li class="active"><a href="#home-pills" data-toggle="tab">Tous Les Fournisseurs</a>
            </li>
            <li><a href="#profile-pills" data-toggle="tab">Ajouter Fournisseur</a>
            </li>

        </ul>

        <div class="tab-content">
            <div class="tab-pane fade in active" id="home-pills">
                <c:if test="${editFour == 'OK'}">
                    <div class="row-fluid">
                        <form action="personnel?action=UpdateFournisseur&vue=fournisseur&idfournis=${fournisseur.getIdFounisseur()}" method="post">
                            <div class="row-fluid">
                                <div class="control-group span4">
                                    <label class="control-label">Nom Fournisseur</label>
                                    <div class="controls ">
                                        <input type="text" class=" span12 form-control" name="nom_fournisseur" value="${fournisseur.getNomFounisseur()}" maxlength="15"/>
                                    </div>
                                </div>

                                <div class="control-group form-group span4">

                                    <label class="control-label">Adresse</label>
                                    <div class="controls ">
                                        <input type="text" class="form-control span12" name="adresse_fournisseur" value="${fournisseur.getAdresse()}"/>

                                    </div>
                                </div>

                                <div class="control-group form-group span4">

                                    <label class="control-label">Tel</label>
                                    <div class="controls ">
                                        <input type="text" class="form-control span12" name="phone_fournisseur" value="${fournisseur.getTelephone()}" maxlength="15"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="control-group form-group span4">

                                    <label class="control-label">E-Mail</label>
                                    <div class="controls ">
                                        <input type="email" class="form-control span12" name="mail" value="${fournisseur.getEmail()}"/>
                                    </div>

                                </div>
                                <div class="span4">
                                    <div class="control-group ">
                                        <button type="submit" class="btn btn-success btn-large span12 btn-lg" style="margin-top: 22px" >SAVE</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:if>
                <h4>Tous Les Fournisseurs</h4>
                <table class="table table-bordered table-hover table-striped simple_print">
                    <thead>
                        <tr >
                            <th>Nom</th>
                            <th>Adresse</th>
                            <th>E-Mail</th>
                            <th>Téléphone</th>
                            <th>Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${fournisseurs}" var="respo">
                            <tr class="produit">
                                <td >${respo.getNomFounisseur()}</td>
                                <td >${respo.getAdresse()}</td>
                                <td >${respo.getEmail()}</td>
                                <td >${respo.getTelephone()}</td>
                                <td >
                                    <div class="btn-group">
                                        <a title="" href="personnel?action=selectFournisseur&vue=fournisseur&idfournis=${respo.getIdFounisseur()}" class="btn btn-primary" >  <span class="icon"> <i class="icon-edit"></i> </span></a>  
                                        <a class="btn btn-danger delete-insert"href="#" name="personnel?action=deleteFournisseur&vue=fournisseur&idfournis=${respo.getIdFounisseur()}" data-toggle="modal" data-target="" ><span class="icon"> <i class="icon-trash"></i> </span></a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="tab-pane fade" id="profile-pills">
                <h4>nouveau Fournisseur</h4>
                <div class="row-fluid">
                    <form action="personnel?action=addFournisseur&vue=fournisseur" method="post">

                        <div class="control-group form-group span4">
                            <label class="control-label">Nom Fournisseur</label>
                            <div class="controls ">
                                <input type="text" class="  form-control span12" name="nom_fournisseur"  maxlength="15" required  placeholder="nom du fournisseur"/>
                            </div>
                        </div>
                        <div class="control-group form-group span4">
                            <label class="control-label">Adresse</label>
                            <div class="controls ">
                                <input type="text" class="form-control span12" name="adresse_fournisseur" required placeholder="Adresse"/>

                            </div>
                        </div>

                        <div class="control-group form-group span4">
                            <label class="control-label">Tel</label>
                            <div class="controls ">
                                <input type="text" class="form-control span12" name="phone_fournisseur" maxlength="15"  placeholder="Telephone"/>

                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="control-group form-group span4">
                                <label class="control-label">E-Mail</label>
                                <div class="controls ">
                                    <input type="email" class="form-control span12" name="mail"  placeholder="Adresse Mail"/>

                                </div>
                            </div>
                            <div class="span4">
                                <div class="control-group ">
                                    <button type="submit" class="btn btn-success btn-large span12 btn-lg" style="margin-top: 22px" >Enregistrer</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <br>           


            </div>

        </div>
    </div>
</div>
<!--End Pill Tabs   -->
