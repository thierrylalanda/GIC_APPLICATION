
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Espace Etats De Consommations
</h3>
<ul class="breadcrumb">
    <li>

        <a href="admin?vue=accueil&action=getAll"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li class="active">
        <a href="#"><i class="icon-list"></i> Etats</a>

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


<!-- BEGIN INLINE TABS PORTLET-->
<div class="widget orange">
    <div class="widget-title">
        <h4><i class="icon-reorder"></i> Statistiques Generaux</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">


        <div class="bs-docs-example">
            <ul class="nav nav-tabs" id="myTabp">

                <li class="li-table active"><a data-toggle="tab" href='#rapport_region'>Region</a></li>
                <li class="li-table "><a data-toggle="tab" href='#rapport_direction'>Direction</a></li>
                <li class="li-table "><a data-toggle="tab" href='#rapport_site'>Site</a></li>
                <li class="li-table "><a data-toggle="tab" href='#rapport_service'>Service</a></li>
                <li class="li-table "><a data-toggle="tab" href='#rapport_mags'>Magasin Secondaire</a></li>
                <li class="li-table "><a data-toggle="tab" href='#rapport_magp'>Magasin Principal</a></li>
                <li class="li-table "><a data-toggle="tab" href='#rapport_personnel'>Personnel</a></li>

            </ul>
            <div class="tab-content" id="myTabContent">
                <div id='rapport_magp' class="tab-pane fade in">
                    <form action="parametre?action=getRepporting&vue=rien" class=""> 
                        <div class="row-fluid">
                            <div class="control-group span3">
                                <label class="control-label">Categorie</label>
                                <div class="controls ">
                                    <select name="categorie" id="" class="">
                                        <c:forEach items="${categories}" var="cate">
                                            <option value="${cate.getIdCategorieProduit()}">${cate.getTypeCategorie()}</option>
                                        </c:forEach>
                                    </select>	

                                </div>
                            </div>
                            <div class="span3">
                                <label class="control-label" for="">duree</label>
                                <div class="controls ">
                                    <div class="input-prepend">
                                        <span class="add-on"><i class="icon-calendar"></i></span>
                                        <input id=""  name="interval" type="text" class="reservation m-ctrl-medium" />
                                    </div>
                                    <div class="input-append ">

                                    </div>
                                </div>
                            </div>    

                            <div class="span3">
                                <label class="control-label" for="region">Region</label>
                                <div class="controls ">
                                    <div class="input-append ">
                                        <select class="region_magp" name="" id="">

                                            <c:forEach items="${regions}" var="co">
                                                <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                    <option value="${co.getIdRegion()}" selected>
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>
                                                <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                    <option  value="${co.getIdRegion()}">
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>

                                            </c:forEach>	
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group span3">
                                <label class="control-label">Magasin</label>
                                <div class="controls ">
                                    <select name="id_magasinP" id="" class="magasinP">

                                    </select>	

                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span1">
                                <label class="control-label">Tous</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="tous" checked/>

                                </div>

                            </div>
                            <div class="span1">

                                <label class="control-label">Magasin</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="non"/>

                                </div>
                            </div>
                            <div class="span1">
                                <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                    <span class="icon icon-search "></span>
                                </button>
                            </div>


                        </div>

                    </form>
                </div>
                <div id='rapport_mags' class="tab-pane fade in">
                    <form action="parametre?action=getRepporting&vue=rien" class=""> 
                        <div class="row-fluid">
                            <div class="control-group span3">
                                <label class="control-label">Categorie</label>
                                <div class="controls ">
                                    <select name="categorie" id="" class="">
                                        <c:forEach items="${categories}" var="cate">
                                            <option value="${cate.getIdCategorieProduit()}">${cate.getTypeCategorie()}</option>
                                        </c:forEach>
                                    </select>	

                                </div>
                            </div>
                            <div class="span3">
                                <label class="control-label" for="">duree</label>
                                <div class="controls ">
                                    <div class="input-prepend">
                                        <span class="add-on"><i class="icon-calendar"></i></span>
                                        <input id=""  name="interval" type="text" class="reservation m-ctrl-medium" />
                                    </div>
                                    <div class="input-append ">

                                    </div>
                                </div>
                            </div>    

                            <div class="span3">
                                <label class="control-label" for="region">Region</label>
                                <div class="controls ">
                                    <div class="input-append ">
                                        <select class="region_mags" name="" id="">

                                            <c:forEach items="${regions}" var="co">
                                                <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                    <option value="${co.getIdRegion()}" selected>
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>
                                                <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                    <option  value="${co.getIdRegion()}">
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>

                                            </c:forEach>	
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group span3">
                                <label class="control-label">Magasin</label>
                                <div class="controls ">
                                    <select name="id_magasin" id="" class="magasinS">

                                    </select>	

                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span1">
                                <label class="control-label">Tous</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="tous" checked/>

                                </div>

                            </div>
                            <div class="span1">

                                <label class="control-label">Magasin</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="non"/>

                                </div>
                            </div>
                            <div class="span1">
                                <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                    <span class="icon icon-search "></span>
                                </button>
                            </div>
                        </div>


                    </form>
                </div>
                <div id='rapport_region' class="tab-pane fade in active">
                    <form action="parametre?action=getRepporting&vue=rien">   
                        <div class="row-fluid">
                            <div class="control-group span3">
                                <label class="control-label">Categorie</label>
                                <div class="controls ">
                                    <select name="categorie" id="" class="">
                                        <c:forEach items="${categories}" var="cate">
                                            <option value="${cate.getIdCategorieProduit()}">${cate.getTypeCategorie()}</option>
                                        </c:forEach>
                                    </select>	

                                </div>
                            </div>
                            <div class="span3">
                                <label class="control-label" for="">duree</label>
                                <div class="controls ">
                                    <div class="input-prepend">
                                        <span class="add-on"><i class="icon-calendar"></i></span>
                                        <input id=""  name="interval" type="text" class="reservation m-ctrl-medium" />
                                    </div>
                                    <div class="input-append ">

                                    </div>
                                </div>
                            </div>    

                            <div class="span3">
                                <label class="control-label" for="region">Region</label>
                                <div class="controls ">
                                    <div class="input-append ">
                                        <select class=""name="region" id="">

                                            <c:forEach items="${regions}" var="co">
                                                <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                    <option value="${co.getIdRegion()}" selected>
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>
                                                <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                    <option  value="${co.getIdRegion()}">
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>

                                            </c:forEach>	
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="span1">
                                <label class="control-label">Tous</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="tous" checked/>

                                </div>

                            </div>
                            <div class="span1">

                                <label class="control-label">Region</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="non"/>

                                </div>
                            </div>
                            <div class="span1">
                                <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                    <span class="icon icon-search "></span>
                                </button>
                            </div>
                        </div>


                    </form>
                </div>


                <div id='rapport_direction' class="tab-pane fade in ">

                    <form action="parametre?action=getRepporting&vue=rien">   
                        <div class="row-fluid">
                            <div class="control-group span3">
                                <label class="control-label">Categorie</label>
                                <div class="controls ">
                                    <select name="categorie" id="" class="">
                                        <c:forEach items="${categories}" var="cate">
                                            <option value="${cate.getIdCategorieProduit()}">${cate.getTypeCategorie()}</option>
                                        </c:forEach>
                                    </select>	

                                </div>
                            </div>

                            <div class="span3">
                                <label class="control-label" for="">duree</label>
                                <div class="controls ">
                                    <div class="input-prepend">
                                        <span class="add-on"><i class="icon-calendar"></i></span>
                                        <input id=""  name="interval" type="text" class="reservation m-ctrl-medium" />
                                    </div>
                                    <div class="input-append ">

                                    </div>
                                </div>
                            </div>    

                            <div class="span3">
                                <label class="control-label" for="region">Region</label>
                                <div class="controls ">
                                    <div class="input-append ">
                                        <select class="region" name="" id="">

                                            <c:forEach items="${regions}" var="co">
                                                <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                    <option value="${co.getIdRegion()}" selected>
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>
                                                <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                    <option  value="${co.getIdRegion()}">
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>

                                            </c:forEach>	
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group span3">
                                <label for="" class="control-label">Direction</label>
                                <div class="controls ">
                                    <select name="direction" id="" class="input-lg direction" >




                                    </select>	

                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span1">
                                <label class="control-label">Tous</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="tous" checked/>

                                </div>

                            </div>
                            <div class="span1">

                                <label class="control-label">Direction</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="non"/>

                                </div>
                            </div>

                            <div class="span1">
                                <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                    <span class="icon icon-search "></span>
                                </button>
                            </div>
                        </div>


                    </form>
                </div>

                <div id='rapport_site' class="tab-pane fade in ">
                    <form action="parametre?action=getRepporting&vue=rien">   
                        <div class="row-fluid">
                            <div class="control-group span3">
                                <label class="control-label">Categorie</label>
                                <div class="controls ">
                                    <select name="categorie" id="" multiple="multiple"  class="cat_select">
                                        <c:forEach items="${categories}" var="cate">
                                            <option value="${cate.getIdCategorieProduit()}">${cate.getTypeCategorie()}</option>
                                        </c:forEach>
                                    </select>	

                                </div>
                            </div>
                            <div class="span3">
                                <label class="control-label" for="">duree</label>
                                <div class="controls ">
                                    <div class="input-prepend">
                                        <span class="add-on"><i class="icon-calendar"></i></span>
                                        <input id=""  name="interval" type="text" class="reservation m-ctrl-medium" />
                                    </div>
                                    <div class="input-append ">

                                    </div>
                                </div>
                            </div>    

                            <div class="span3">
                                <label class="control-label" for="region">Region</label>
                                <div class="controls ">
                                    <div class="input-append ">
                                        <select class="region_service for_end"name="" id="">

                                            <c:forEach items="${regions}" var="co">
                                                <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                    <option value="${co.getIdRegion()}" selected>
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>
                                                <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                    <option  value="${co.getIdRegion()}">
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>

                                            </c:forEach>	
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group span3">
                                <label for="stat-site" class="control-label">Site</label>
                                <div class="controls ff">
                                    <select name="site" id="" class="input-lg site_service all_select " >


                                    </select>	

                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">

                            <div class="span1">
                                <label class="control-label">Tous</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="tous" checked/>

                                </div>

                            </div>
                            <div class="span1">

                                <label class="control-label">Site</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="non"/>

                                </div>
                            </div>
                            <div class="span1">
                                <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                    <span class="icon icon-search "></span>
                                </button>
                            </div>
                        </div>


                    </form>
                </div>


                <div id='rapport_service' class="tab-pane fade in ">
                    <form action="parametre?action=getRepporting&vue=rien">   
                        <div class="row-fluid">
                            <div class="control-group span3">
                                <label class="control-label">Categorie</label>
                                <div class="controls ">
                                    <select name="categorie" multiple="multiple"  class="cat_select">
                                        <c:forEach items="${categories}" var="cate">
                                            <option value="${cate.getIdCategorieProduit()}">${cate.getTypeCategorie()}</option>
                                        </c:forEach>
                                    </select>	

                                </div>
                            </div>
                            <div class="span3">
                                <label class="control-label" for="">duree</label>
                                <div class="controls ">
                                    <div class="input-prepend">
                                        <span class="add-on"><i class="icon-calendar"></i></span>
                                        <input id=""  name="interval" type="text" class="reservation m-ctrl-medium" />
                                    </div>
                                    <div class="input-append ">

                                    </div>
                                </div>
                            </div>    

                            <div class="span3">
                                <label class="control-label" for="">Region</label>
                                <div class="controls ">
                                    <div class="input-append ">
                                        <select class="region_service" name="" id="">

                                            <c:forEach items="${regions}" var="co">
                                                <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                    <option value="${co.getIdRegion()}" selected>
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>
                                                <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                    <option  value="${co.getIdRegion()}">
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>

                                            </c:forEach>	
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group span3">
                                <label for="" class="control-label">Site</label>
                                <div class="controls">
                                    <select name="" id="" class="input-lg site_service for_end" >
                                    </select>	

                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="control-group span3">
                                <label class="control-label" for="">Service</label>
                                <div class="controls ff">
                                    <select name="service" id="" class="form-control input-lg service all_select"  >


                                    </select>	

                                </div>
                            </div>



                            <div class="span1">
                                <label class="control-label">Tous</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="tous" checked/>

                                </div>

                            </div>
                            <div class="span1">

                                <label class="control-label">Service</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="non"/>

                                </div>
                            </div>
                            <div class="span1 " style="margin-left: 0px">
                                <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                    <span class="icon icon-search "></span>
                                </button>
                            </div>

                        </div>


                    </form>
                </div>


                <div id='rapport_personnel' class="tab-pane fade in ">
                    <form action="parametre?action=getRepporting&vue=rien"> 
                        <div class="row-fluid">


                            <div class="control-group span3">
                                <label class="control-label">Categorie</label>
                                <div class="controls ">
                                    <select name="categorie" id="" class="">
                                        <c:forEach items="${categories}" var="cate">
                                            <option value="${cate.getIdCategorieProduit()}">${cate.getTypeCategorie()}</option>
                                        </c:forEach>
                                    </select>	

                                </div>
                            </div>
                            <div class="span3">
                                <label class="control-label" for="">duree</label>
                                <div class="controls ">
                                    <div class="input-prepend">
                                        <span class="add-on"><i class="icon-calendar"></i></span>
                                        <input id=""  name="interval" type="text" class="reservation m-ctrl-medium" />
                                    </div>
                                    <div class="input-append ">

                                    </div>
                                </div>
                            </div>    

                            <div class="span3">
                                <label class="control-label" for="region">Region</label>
                                <div class="controls ">
                                    <div class="input-append ">
                                        <select class="region_service" name="" id="region_service">

                                            <c:forEach items="${regions}" var="co">
                                                <c:if test="${co.getNomRegion()==region.getNomRegion()}">
                                                    <option value="${co.getIdRegion()}" selected>
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>
                                                <c:if test="${co.getNomRegion()!=region.getNomRegion()}">
                                                    <option  value="${co.getIdRegion()}">
                                                        ${co.getNomRegion()}
                                                    </option> 
                                                </c:if>

                                            </c:forEach>	
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group span3">
                                <label for="stat-site" class="control-label">Site</label>
                                <div class="controls ">
                                    <select name="" id="" class="input-lg site_service" >


                                    </select>	

                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="control-group span3">
                                <label class="control-label" for="service">Service</label>
                                <div class="controls ">
                                    <select name="" id="" class="service form-control input-lg "  >

                                    </select>	

                                </div>
                            </div>





                            <div class="control-group span3">
                                <label class="control-label" for="stat-service">personnel</label>
                                <div class="controls ">
                                    <select name="personnel" id="" class="personnels form-control input-lg "  >

                                    </select>	

                                </div>
                            </div>
                            <div class="span1">
                                <label class="control-label">Tous</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="tous" checked/>

                                </div>

                            </div>
                            <div class="span1">

                                <label class="control-label">Personnel</label>
                                <div class="controls ">
                                    <input type="radio" name="choix" value="non"/>

                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                <span class="icon icon-search "></span>
                            </button>
                        </div>

                </div>

                </form>
            </div>


        </div>




        <c:if test="${var==true}">
            <table titre="raport sur les " class="table table-hover table-responsive table-bordered table-rapport simple_print" categori="${cat.getIdCategorieProduit()}"cellspacing="0" width="100%">
                <thead >
                    <tr>
                        <th>Articles</th>

                        <th>QUANTITE</th>
                        <th>Prix Total</th>



                    </tr>
                </thead>

                <tbody class="stat-perso">


                </tbody>
                <tfoot style="color: #2fade7">

                </tfoot>
            </table>
        </c:if>



        <br>
        <a class="btn btn-inverse   hidden-print print-pdf" style="margin-left: 400px;margin-bottom: 10px;border-radius: 5px">Imprimer <i class="icon-print icon-big"></i></a>
        <br><div class="row-fluid all-table portlet-scroll-1">


        </div>

    </div>
</div>
