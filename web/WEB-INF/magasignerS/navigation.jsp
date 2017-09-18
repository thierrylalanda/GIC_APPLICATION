
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="responsive col-lg-12"  style="  padding-top: 20px">
    <div class="col-lg-3 ">
        <p>SOCIETE <c:out value="${sessionScope.societe}"/></p> 
        <p>REGION <c:out value="${sessionScope.region}"/></p>
        <p>MAGASIGNIER  <c:out value="${sessionScope.magasigner}"/></p>
        <p class="date" >${sessionScope.date}</p>
    </div>
    <c:if test="${vue=='inventaireMS'}">
        <div class="col-lg-5 pull-left">
            <fieldset> 
                <legend>recherche</legend>
                <form role="form" class="form-horizontal text-center " method="post" action="searchms?vue=listePMS">

                    <div class="col-lg-5">
                        <select  class="select input-sm form-control disabled" id="tout" name="choix" >
                            <option class="tout">tout</option>  
                            <option class="toutp">produit</option>
                            <option class="toutc">categorie</option>
                        </select>


                    </div>
                    <div class="col-lg-5 hide tproduit">

                        <input type="text"  class=" input-mini form-control input-sm tag" id="produit" name="designation" placeholder="tout les produits"/>
                    </div>
                    <div class="col-lg-5 hide tcategorie">
                        <select  class="select input-xs form-control " id="categorie" name="categorie" >
                            <c:forEach items="${categoriee}" var="cc">  
                                <option >${cc.getNomCategorie()}</option>  
                            </c:forEach>
                        </select>
                    </div>
                    <div class='col-lg-2' >
                        <button type="submit" class='btn btn-success submit-commande' name="Search" value="" id=""  data-loading-text="Chargement. . . "><span class="glyphicon glyphicon-search"></span></button>
                    </div>

                </form>
            </fieldset>
        </div>
    </c:if>
    <div class="col-lg-4 pull-right " style="margin-left:40px">

        <div id="" class="divAppearance pull-right">

            <c:if test="${etat1=='warning'}">
                <div id="progress3" class="   ui-priority-primary progress-bar-warning   col-lg-12 btn alert-link text-uppercase text-center theDiv" style=" width: 100% ;margin-bottom: 10px">
                    <a data-toggle="tooltip" title='produits en cour de rupture' href="RedirectionVue?vue=alertProduit">
                        <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em"></span> produits en cours de rupture
                    <span class="badge text-warning"> ${sessionScope.taill}</span>
                    </a> 
                </div> 
            </c:if>
            <c:if test="${etat=='danger'}">
                <div class="ui-priority-primary col-lg-12 btn alert-danger  text-uppercase  text-center ui-state-error ui-corner-all theDiv" id="theDiv" style=" text-decoration: black">
                    <a  data-toggle="tooltip" title='produits critiques' href="RedirectionVue?vue=alertProduit">
                        <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em"></span> produits critiques 
                        <span class="badge text-danger"> ${sessionScope.tail}</span>
                    </a>

                </div> 
            </c:if>
        </div>

    </div>

</div>