<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true" %>


<!-- BEGIN INLINE TABS PORTLET-->
<div class="widget orange">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Passer Votre Commande Ici</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>

        </span>
    </div>
    <div class="widget-body">
        <div class="bs-docs-example">

            <div class="row-fluid responsive ">    
                <form method="post" action="Commande_All_Client?vue=Commande Personnel&action=ajouter" class="form-inline" id="commandeClit">

                    <div class="form-group responsive">

                        <select id="select" class=" form-control"name="magasin" required style="margin-right: 50px">
                            <c:forEach items="${sessionScope.magasinS}" var="cc">  
                                <option value="${cc.getIdMagasin()}" >${cc.getNomMagasin()}</option>  
                            </c:forEach>

                        </select>

                        <input type="text" id="client" required name="client" value="${nom_client}" class="input-sm form-control tags "style="margin-right: 50px" placeholder="Nom du Client" <c:if test="${edit=='ok'}" ><c:out value="disabled"></c:out> </c:if>/>



                                <select id="select" class=" form-control"name="categorie"placeholder="Categorie" style="margin-right: 50px" required>
                            <c:forEach items="${sessionScope.categorie}" var="cc">  
                                <option ><c:out value="${cc.getNomCategorie()}"/></option>  
                            </c:forEach>

                        </select>

                        <input type="text"   class=" form-control  tag  " placeholder="Designation"style="margin-right: 50px" required id="designation" name="designation"/>  
                        <div class="space10"></div>
                        <input type="number" id="quantite" class=" form-control  "  placeholder="Quantite"style="margin-right: 50px" required name="quantite" />

                        <select id="select" class=" form-control" name="appariel" placeholder="Magasin"style="margin-right: 50px" required>
                            <c:forEach items="${sessionScope.personnel.getApparielList()}" var="cc">  
                                <option >${cc.getNumeroParck()}</option>  
                            </c:forEach>

                        </select>

                        <button type="submit" class="btn btn-success  btn-primary pull-righ " id="sendcc" >Envoyer</button>
                    </div>

                </form>
            </div>

            <table class="table table-hover table-responsive table-bordered simple_print" >
                <thead class="text-primary">
                    <tr>
                        <th>CATEGORIE</th>
                        <th>CODE</th>
                        <th>DESIGNATION</th>
                        <th>QUANTITE</th>
                        <th class="hidden">QUANTITE</th>
                        <th>PRIX UNITAIRE</th>
                        <th>PRIX TOTAL</th>
                        <th>APPARIEL</th>

                    </tr>
                </thead>
                <tbody id="tbody">
                    <c:forEach items="${listeoperation}" var="list">  
                        <tr class="produits">

                            <td class="categories col-lg-3" ><c:out value="${list.getCategorie()}"/></td>
                            <td class="code"><c:out value="${list.getCodeProduit()}"/></td>
                            <td class="designation"><c:out value="${list.getDesignation()}"/></td>
                            <td class="qteu"><f:formatNumber value="${list.getQuantite()}" type="NUMBER"/></td>
                            <td class="qteuc hidden"><input class="newvalcc" type='text' id='qteu'  /></td>
                            <td class="pu"><f:formatNumber value="${list.getPrix()}" type="CURRENCY"/></td>
                            <td class="pt"><f:formatNumber value="${list.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                            <c:if test="${not empty list.getAppariel()}">
                                <td class="app"><c:out value="${list.getAppariel()}"/></td>
                            </c:if>                           
                        </tr>
                    </c:forEach>

                </tbody>
            </table> 
            <hr>
            <c:if test="${not empty message.getMessage()}">
                <div class="alert alert-danger text-center "><strong><c:out value="${message.getMessage()}"/></strong></div>
                    </c:if>

            <div class="row-fluid">

                <a href="Commande_All_Client?vue=Commande Personnel&action=saveClient"data-toggle="tooltip" title='Enregistrer Votre Commande' >  <button type="submit" class="btn btn-success"><i class="icon-save"></i> Enregistrer</button></a>
                <button type="button" id="deleteP" class="btn btn-danger delete" style="margin-left: 30px"><i class="icon-remove"></i>supprimer</button>

            </div>

        </div>
        <div class="space20"></div>
        <div class="space20"></div>
        <div class="widget ">
            <div class="widget-title green"style="background-color: green">
                <h4><i class="icon-reorder"></i> Stocks Du Magasin Secondaire</h4>


            </div>
            <div class="widget-body">
                <div class="bs-docs-example">
                    <table class="table table-hover table-bordered "  cellpadding="0" cellspacing="0" >
                        <thead >
                            <tr>
                                <th>CATEGORIE</th>
                                <th>CODE</th>
                                <th>DESIGNATION</th>
                                <th>QUANTITE</th>                        
                                <th>DATE LIVRAISON</th>
                            </tr>
                        </thead>
                        <tbody id="tabb">


                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>