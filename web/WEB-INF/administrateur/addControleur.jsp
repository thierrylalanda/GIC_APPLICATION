<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="row-fluid">
    <div class="span12">
        <div class="widget box purple ">
            <div class="widget-title"style="background-color: #0044cc">
                <h4>
                    <i class="icon-reorder"></i><span> Enregistrement d'un controleur Etape 1 a  5</span>
                </h4>

            </div>
            <div class="widget-body">
                <form class="form-horizontal" action="#" method="get">
                    <div id="tabsleft" class="tabbable tabs-left">
                        <ul>

                            <li><a href="#pills-tab1" data-toggle="tab"> <span class="strong">Etape 1</span> <span class="muted">Localisation</span></a></li>
                            <li><a href="#pills-tab2" data-toggle="tab"> <span class="strong">Etape 2</span> <span class="muted">Details controleur</span></a></a></li>
                            <li><a href="#pills-tab3" data-toggle="tab"><span class="strong">Etape 3</span> <span class="muted">Securite</span></a></li>
                            <li><a href="#pills-tab4" data-toggle="tab"><span class="strong">Etape 4</span> <span class="muted">Droit D'acces</span></a></li>
                            <li><a href="#pills-tab5" data-toggle="tab"><span class="strong">Etape 5</span> <span class="muted">Confirmation</span></a></li>
                        </ul>
                        <div class="progress progress-info progress-striped">
                            <div class="bar"></div>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane" id="pills-tab1">
                                <h3>Localisation</h3>

                                <div class="control-group ">
                                    <label class="control-label">Region</label>
                                    <div class="controls">
                                        <select name="region" id="region" class="input-lg span6" >
                                            <c:forEach items="${regions}" var="region">
                                                <option value="${region.getIdRegion()}" class="region">${region.getNomRegion()}</option>

                                            </c:forEach>
                                        </select>	

                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="direction">Diretion</label>
                                    <div class="controls ">
                                        <select name="direction" id="direction" class="form-control input-lg span6" >

                                        </select>	

                                    </div>
                                </div>


                                <div class="control-group">
                                    <label class="control-label" for="site">Site</label>
                                    <div class="controls ">
                                        <select name="site" id="site" class="form-control input-lg span6" >

                                        </select>	

                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label"> Service</label>
                                    <div class="controls ">
                                        <select name="service" id="service" class="form-control input-lg span6">

                                        </select>	
                                    </div>

                                </div> 
                            </div>

                            <div class="tab-pane" id="pills-tab2">
                                <h3>Details Personel</h3>
                                <div class="control-group form-group ">
                                    <label class="control-label">Nom et Prenom</label>
                                    <div class="controls ">
                                        <input type="text" class=" input-lg form-control span6" name="name" id="required" placeholder="First name">
                                    </div>
                                </div>

                                <div class="control-group ">
                                    <label class="control-label"> Email</label>
                                    <div class="controls ">
                                        <input type="email" class=" input-lg form-control span6" name="email" id="email" required placeholder="Email">

                                    </div>
                                </div>


                                <div class="control-group ">
                                    <label class="control-label">Fonction</label>
                                    <div class="controls ">
                                        <input type="text" class=" input-lg form-control span6" name="fonction "required id="" placeholder="fontion">

                                    </div>
                                </div>

                                <div class="control-group ">
                                    <label class="control-label"> Matricule</label>
                                    <div class="controls ">
                                        <input type="text" class=" input-lg form-control span6"required name="matricule" id="" placeholder="Service">
                                    </div>
                                </div>


                                <div class="control-group ">
                                    <label class="control-label"> Tel:</label>
                                    <div class="controls ">

                                        <input type="phone" name="phone" class="input-lg form-control span6"required id="" placeholder="phone" required>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="pills-tab3">
                                <h3>Securite</h3>
                                <div class="control-group ">
                                    <label class="control-label">Nom d'utilisateur </label>
                                    <div class="controls">
                                        <input type="text" class="input-lg form-control span6" name="username" id="username" required placeholder="username" required/>
                                    </div>
                                </div>

                                <div class="control-group ">
                                    <label class="control-label">Mot de passe</label>
                                    <div class="controls">
                                        <input type="password" class="input-lg span6" name="password" id="password"required placeholder="password" required/>

                                    </div>
                                </div>



                                <div class="control-group ">
                                    <label class="control-label">confirmer le Mot de Passe</label>
                                    <div class="controls">
                                        <input type="password" class="input-lg span6" name="password" id="Confirmpassword"required placeholder="password" required/>

                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="pills-tab4">
                                <h3>Droit D'acces</h3>
                                <div class="control-group  ">
                                    <label class="control-label">Groupe</label>
                                    <div class="controls " id="groupe">

                                        <select  class="input-lg span6" id="selectgroupe"  name="groupe">
                                            <c:forEach items="${groupes}" var="page">
                                                <option value="${page.getIdGroupes()}" >${page.getNomGroupe()}</option>
                                            </c:forEach>
                                        </select>


                                    </div>

                                </div>
                                <div class="control-group  ">
                                    <label class="control-label">Pages Permises</label>
                                    <div class="controls " id="groupe">

                                        <select  class="input-lg span6" id="selectpages "  name="groupe">

                                        </select>


                                    </div>

                                </div>


                                <div class="control-group ">
                                    <label class="control-label">Niveau Access</label>
                                    <div class="controls ">
                                        <select name="affectation" id=""  type="text" class="form-control input-lg span6" >

                                            <option value="4" class="affectation">controlleur</option>  

                                        </select>	
                                    </div>
                                </div>

                                <div class="control-group  typecontrol hidden">
                                    <label class="control-label"> Type control</label>
                                    <div class="controls ">
                                        <select name="role_personnel" id="role_personnel" class="form-control input-lg span6">

                                        </select>	
                                    </div>
                                </div>

                            </div>
                            <div class="tab-pane" id="pills-tab5">
                                <h3>Confirmation Des Donnees</h3>
                                <div class="row-fluid">
                                    <div class="control-group span3">
                                        <label class="control-label">Region: Centre</label>

                                    </div>
                                    <div class="control-group span3">
                                        <label class="control-label">Direction: DSI</label>

                                    </div>
                                    <div class="control-group span3">
                                        <label class="control-label">Site: CCY2</label>

                                    </div>
                                    <div class="control-group span3">
                                        <label class="control-label">Service: Helpdesk</label>

                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="control-group span4 ">
                                        <label class="control-label ">Nom Complet: Messi Charly</label>

                                    </div>
                                    <div class="control-group span4">
                                        <label class="control-label ">Matricule: 14s19797</label>

                                    </div>
                                    <div class="control-group span4">
                                        <label class="control-label ">Fonction: Chef Service</label>

                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="control-group span6">
                                        <label class="control-label">Email:</label>
                                        <div class="controls">
                                            <span class="text">dkmosa@gmail.com</span>
                                        </div>
                                    </div>
                                    <div class="control-group span6">
                                        <label class="control-label">Phone:</label>
                                        <div class="controls">
                                            <span class="text">123456789</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="control-group span3">
                                        <label class="control-label">User Name: mcd01</label>

                                    </div>
                                    <div class="control-group span3">
                                        <label class="control-label">Password: charle22</label>

                                    </div>

                                    <div class="control-group span3">
                                        <label class="control-label">Niveau D'acces: Administrateur</label>

                                    </div>
                                    <div class="control-group span3">
                                        <label class="control-label">Groupe: Defaul Groupe For All Admin</label>

                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label"></label>
                                    <div class="controls">
                                        <label class="checkbox">
                                            <input type="checkbox" required /> Je Confirme
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <ul class="pager wizard">
                                <li class="previous first"><a href="javascript:;">First</a></li>
                                <li class="previous"><a href="javascript:;">Previous</a></li>
                                <li class="next last"><a href="javascript:;">Last</a></li>
                                <li class="next"><a href="javascript:;">Next</a></li>
                                <li class="next finish" style="display:none;"><a href="precj">Finish</a></li>

                            </ul>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
