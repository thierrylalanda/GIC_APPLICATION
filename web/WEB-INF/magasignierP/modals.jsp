<%-- 
    Document   : modals
    Created on : 21 févr. 2017, 23:31:43
    Author     : lalanda
--%>

<div id="dialog1" class="ui-icon-circle-triangle-s bg-info" title="Valider la commande encours">
        <table class="table table-hover table-responsive simpletable">
            <thead class="text-primary">
               
                    <tr>
                    <th>CATEGORIE</th>
                    <th>CODE</th>
                    <th>DESIGNATION</th>
                    <th>QUANTITE</th>
                    <th class="hidden">QUANTITE</th>
                    <th>PRIX UNITAIRE</th>
                    <th>PRIX TOTAL</th>
                    <th>DATE LIVRAISON</th>
                </tr>
             
            </thead>
            <tbody id="tbody">
                <c:forEach items="${categorie}" var="c">

                        

                            <c:forEach items="${c.getStockproduitMSList()}"var="r">
                                    <tr class="produits">
                                <td > <c:out value="${c.getNomCategorie()}"/> </td>
                                <td class="code"><c:out value="${r.getCodeProduit()}"/></td>
                                <td><c:out value="${r.getDesignation()}"/></td>
                                <td class="qteu"><f:formatNumber value="${r.getQuantite()}"type="NUMBER"/></td>
                                <td class="qteuc hidden"><input class="newvalcm "  type='number' id='qteu'size="30" maxlength="8" placeholder="nouvelle quantite" /></td>
                                <td><f:formatNumber value="${r.getPrixUnitaire()}"type="NUMBER"/></td>
                                <td><f:formatNumber value="${r.getPrixTotal()}"type="CURRENCY" currencySymbol="FCFA"/></td>
                                <td><f:formatDate value="${r.getDateLivraison()}" type="Date" dateStyle="MEDIUM"/></td>
                            </tr>

                        </c:forEach>
                    </c:forEach>


            </tbody>
        </table> 
    </div>
                            
    <div class="text-center" id="refuser_validation" title="raison du rejet de validation">
        <div class="row ">
            <div class="col-lg-8 ">
        <form class="form-horizontal well">
            <fieldset>
                <legend> raisons du rejet</legend>
                <br/>
                
                <div class="input-group col-lg-6 ">
                    
                    <div class="form-group">
                        <div class="input-group ">
                            <label for="ami" class="radio control-label">somme trop grande </label>
                            <span class="input-group-addon">
                            <input type="radio" name="raison" class="form-control" value="ami" id="ami" />
                            </span>
                            
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="input-group ">
                            <label for="web" class=" radio control-label">date livraison trop proche  </label>
                            <span class="input-group-addon">
                            <input type="radio" class="form-control" name="raison" value="web" id="web" />
                            </span>
                            
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="input-group ">
                            <label for="hasard" class="radio control-label">pas assez de produits </label>
                            <span class="input-group-addon">
                                <input type="radio" name="raison" class="form-control" value="hasard" id="hasard"/>
                            </span>
                            
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="input-group ">
                            <label for="autre" class="radio control-label">Autre... </label>
                            <span class="input-group-addon">
                                <input type="radio" name="raison"  class="form-control" value="autre" id="autre" checked />
                            </span>
                            
                        </div>
                        
                    </div>
                    
                    
                </div>
               
                 <div class=" col-lg-6 " style="margin-left: 70px">
                     <label for="textarea" class="">Votre message :</label>
                     <textarea name="raison" class="input-xlarge form-control " id="autres" rows="3" maxlength="122"  autofocus="true"></textarea><br/>
                             <label for="textarea" class="help-block">Vous pouvez l'agrandir </label>
                     <button class="btn btn-primary text-right   btn-lg "  >Envoyer </button> <br/>
                 </div>
                
                
            </fieldset>
        </form>
            </div>
            <div class="col-lg-4 ">
                <p>rejeter ces produits atteste que vous n'etes pas en accord avec ctte commmande </p>
            </div>
       </div>  
    </div>