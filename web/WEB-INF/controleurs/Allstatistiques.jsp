
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


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

            <ul class="nav nav-tabs" id="myTab">
                <c:forEach items="${categories}" var="cate">
                    <li class="li-table li-cat <c:if test="${cate.getIdCategorieProduit()==categories.get(0).getIdCategorieProduit()}">active</c:if>"><a data-toggle="tab" href="#${cate.getIdCategorieProduit()}">${cate.getTypeCategorie()}</a></li>
                    </c:forEach>
            </ul>
            <div class="tab-content" id="myTabContent">
                <c:forEach items="${categories}" var="cat">
                    <div id="${cat.getIdCategorieProduit()}" class="tab-pane fade in <c:if test="${cat==categories.get(0)}">active</c:if>">
                            <div class="bs-docs-example">
                                <ul class="nav nav-tabs" id="myTabp${cat.getIdCategorieProduit()}">

                                <li class="li-table active"><a data-toggle="tab" href='#rapport_region<c:out value="${cat.getIdCategorieProduit()}"></c:out>'>Region</a></li>
                                <li class="li-table "><a data-toggle="tab" href='#rapport_direction<c:out value="${cat.getIdCategorieProduit()}"></c:out>'>Direction</a></li>
                                <li class="li-table "><a data-toggle="tab" href='#rapport_site<c:out value="${cat.getIdCategorieProduit()}"></c:out>'>Site</a></li>
                                <li class="li-table "><a data-toggle="tab" href='#rapport_service<c:out value="${cat.getIdCategorieProduit()}"></c:out>'>Service</a></li>
                                <li class="li-table "><a data-toggle="tab" href='#rapport_mags<c:out value="${cat.getIdCategorieProduit()}"></c:out>'>Magasin Secondaire</a></li>
                                <li class="li-table "><a data-toggle="tab" href='#rapport_magp<c:out value="${cat.getIdCategorieProduit()}"></c:out>'>Magasin Principal</a></li>
                                <li class="li-table "><a data-toggle="tab" href='#rapport_personnel<c:out value="${cat.getIdCategorieProduit()}"></c:out>'>Personnel</a></li>

                                </ul>
                                <div class="tab-content" id="myTabContent<c:out value="${cat.getIdCategorieProduit()}"></c:out>">
                                <div id='rapport_magp<c:out value="${cat.getIdCategorieProduit()}"></c:out>' class="tab-pane fade in">
                                        <form action="parametre?action=getRepporting&vue=rien" class=""> 
                                            <div class="row-fluid">
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

                                            <div class="span1"></div>

                                            <div class="span1">
                                                <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                                    <span class="icon icon-search "></span>
                                                </button>
                                            </div>

                                            <div class="span1  hidden">
                                                <label class="control-label" for="">categorie</label>
                                                <div class="controls ">
                                                    <div class="input-append ">
                                                        <input type="text" class="disabled hidden" name="categorie" value="${cat.getTypeCategorie()}"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div id='rapport_mags<c:out value="${cat.getIdCategorieProduit()}"></c:out>' class="tab-pane fade in">
                                        <form action="parametre?action=getRepporting&vue=rien" class="">   
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


                                        <div class="span1">
                                            <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                                <span class="icon icon-search "></span>
                                            </button>
                                        </div>

                                        <div class="span1  hidden">
                                            <label class="control-label" for="">categorie</label>
                                            <div class="controls ">
                                                <div class="input-append ">
                                                    <input type="text" class="disabled hidden" name="categorie" value="${cat.getTypeCategorie()}"/>
                                                </div>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                                <div id='rapport_region<c:out value="${cat.getIdCategorieProduit()}"></c:out>' class="tab-pane fade in active">
                                        <form action="parametre?action=getRepporting&vue=rien">   
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
                                            <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                                <span class="icon icon-search "></span>
                                            </button>
                                        </div>

                                        <div class="span1  hidden">
                                            <label class="control-label" for="">categorie</label>
                                            <div class="controls ">
                                                <div class="input-append ">
                                                    <input type="text" class="disabled hidden" name="categorie" value="${cat.getTypeCategorie()}"/>
                                                </div>
                                            </div>
                                        </div>

                                    </form>
                                </div>


                                <div id='rapport_direction<c:out value="${cat.getIdCategorieProduit()}"></c:out>' class="tab-pane fade in ">

                                        <form action="parametre?action=getRepporting&vue=rien">   
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


                                        <div class="span1">
                                            <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                                <span class="icon icon-search "></span>
                                            </button>
                                        </div>

                                        <div class="span1  hidden">
                                            <label class="control-label" for="">categorie</label>
                                            <div class="controls ">
                                                <div class="input-append ">
                                                    <input type="text" class="disabled hidden" name="categorie" value="${cat.getTypeCategorie()}"/>
                                                </div>
                                            </div>
                                        </div>

                                    </form>
                                </div>

                                <div id='rapport_site<c:out value="${cat.getIdCategorieProduit()}"></c:out>' class="tab-pane fade in ">
                                        <form action="parametre?action=getRepporting&vue=rien">   
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
                                                        <select class="region_service"name="" id="">

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
                                                <select name="site" id="" class="input-lg site_service" >


                                                </select>	

                                            </div>
                                        </div>

                                        <div class="span1">
                                            <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                                <span class="icon icon-search "></span>
                                            </button>
                                        </div>

                                        <div class="span1  hidden">
                                            <label class="control-label" for="">categorie</label>
                                            <div class="controls ">
                                                <div class="input-append ">
                                                    <input type="text" class="disabled hidden" name="categorie" value="${cat.getTypeCategorie()}"/>
                                                </div>
                                            </div>
                                        </div>

                                    </form>
                                </div>


                                <div id='rapport_service<c:out value="${cat.getIdCategorieProduit()}"></c:out>' class="tab-pane fade in ">
                                        <form action="parametre?action=getRepporting&vue=rien">   
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
                                            <div class="controls ">
                                                <select name="" id="" class="input-lg site_service" >


                                                </select>	

                                            </div>
                                        </div>
                                        <div class="control-group span3">
                                            <label class="control-label" for="">Service</label>
                                            <div class="controls ">
                                                <select name="service" id="" class="form-control input-lg service"  >


                                                </select>	

                                            </div>
                                        </div>




                                        <div class="span1 " style="margin-left: 0px">
                                            <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                                <span class="icon icon-search "></span>
                                            </button>
                                        </div>

                                        <div class="span1  hidden">
                                            <label class="control-label" for="">categorie</label>
                                            <div class="controls ">
                                                <div class="input-append ">
                                                    <input type="text" class="disabled hidden" name="categorie" value="${cat.getTypeCategorie()}"/>
                                                </div>
                                            </div>
                                        </div>


                                    </form>
                                </div>


                                <div id='rapport_personnel<c:out value="${cat.getIdCategorieProduit()}"></c:out>' class="tab-pane fade in ">
                                        <form action="parametre?action=getRepporting&vue=rien">   
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
                                        <div class="control-group span3">
                                            <label class="control-label" for="service">Service</label>
                                            <div class="controls ">
                                                <select name="" id="" class="service form-control input-lg "  >

                                                </select>	

                                            </div>
                                        </div>





                                        <div class="control-group span3" style="margin-left: 0px">
                                            <label class="control-label" for="stat-service">personnel</label>
                                            <div class="controls ">
                                                <select name="personnel" id="" class="personnels form-control input-lg "  >

                                                </select>	

                                            </div>
                                        </div>
                                        <div class="span1">
                                            <button type="submit" class="btn btn-primary search-catego" style="margin-top: 25px">
                                                <span class="icon icon-search "></span>
                                            </button>
                                        </div>

                                        <div class="span1  hidden">
                                            <label class="control-label" for="">categorie</label>
                                            <div class="controls ">
                                                <div class="input-append ">
                                                    <input type="text" class="disabled hidden" name="categorie" value="${cat.getTypeCategorie()}"/>
                                                </div>
                                            </div>
                                        </div>


                                    </form>
                                </div>


                            </div>
                        </div>





                        <table titre="raport sur les " class="table table-hover table-responsive table-bordered table-rapport " categori="${cat.getIdCategorieProduit()}"cellspacing="0" width="100%">
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
                    </div>
                </c:forEach>



            </div>
        </div>
    </div>
</div>