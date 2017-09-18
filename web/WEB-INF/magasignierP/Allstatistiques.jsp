
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="page-title">
    Rapport Periodique des Categories d'Articles
</h3>
<ul class="breadcrumb">
    <li>
        <a href="StatistiqueMP?vue=Accueil&action=autre"><i class="icon-home"></i> Home</a>
        <span class="divider">/</span>
    </li>
    <li>
        <a href="#">${vue}</a>

    </li>

    <li class="pull-right search-wrap">
        <form action="" class="hidden-phone">
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
        <h4><i class="icon-reorder"></i> Rapport du magasin</h4>
        <span class="tools">
            <a href="javascript:;" class="icon-chevron-down"></a>
            <a href="javascript:;" class="icon-remove"></a>
        </span>
    </div>
    <div class="widget-body">


        <div class="bs-docs-example">

            <ul class="nav nav-tabs" id="myTab">
                <c:forEach items="${categories}" var="cate">
                    <li class="li-table <c:if test="${cate.getIdCategorie()==categories.get(0).getIdCategorie()}">active</c:if>"><a data-toggle="tab" href="#${cate.getIdCategorie()}">${cate.getNomCategorie()}</a></li>
                    </c:forEach>
            </ul>
            <div class="tab-content" id="myTabContent">
                <c:forEach items="${categories}" var="cat">
                    <div id="${cat.getIdCategorie()}" class="tab-pane fade in <c:if test="${cat==categories.get(0)}">active</c:if>">
                        <form action="parametre?action=getRepporting&vue=rien" class="non-region hidden-phone" id_magasin="${cat.getMagasinPrincipal().getIdMagasin()}">   


                            <div class="input-append input-prepend  search-input-area">

                                <input class="reservation" name="interval" id="appendedInputButton" placeholder="interval de temps" type="text">
                                <button class="btn btn-primary search-catego" type="submit"><i class="icon-search"></i> </button>
                            </div>


                            <div class="span3  hidden">
                                <label class="control-label" for="">categorie</label>
                                <div class="controls ">
                                    <div class="input-append ">
                                        <input type="text" class="disabled hidden" name="categorie" value="${cat.getNomCategorie()}"/>
                                    </div>
                                </div>
                            </div>
                        </form>



                        <table titre="raport sur les ${cat.getNomCategorie()}" class="table table-hover table-responsive table-bordered table-rapport " categori="${cat.getIdCategorie()}"cellspacing="0" width="100%">
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