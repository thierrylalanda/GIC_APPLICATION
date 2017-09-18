<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <c:import url="Header.jsp"/>

        <link rel="icon" href="images/gci.png" >

    </head>
    <!-- END HEAD -->
    <!-- BEGIN BODY -->
    <body class="fixed-top">

        <div id="header" class="navbar navbar-inverse navbar-fixed-top">
            <!-- BEGIN TOP NAVIGATION BAR -->
            <div class="navbar-inner">
                <div class="container-fluid">
                    <!--BEGIN SIDEBAR TOGGLE-->
                    <div class="sidebar-toggle-box hidden-phone">
                        <div class="icon-reorder tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
                    </div>
                    <!--END SIDEBAR TOGGLE-->
                    <!-- BEGIN LOGO -->
                    <a class="brand" href="#" >
                        <img alt="Brasseries Du Cameroun" src="photo/${sessionScope.societe.getLogo()}" style="height: 40px; width: 160px; margin-top: -10px">
                    </a>
                    <!-- END LOGO -->
                    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                    <a class="btn btn-navbar collapsed" id="main_menu_trigger" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="arrow"></span>
                    </a>
                    <!-- END RESPONSIVE MENU TOGGLER -->
                    <div id="top_menu" class="nav notify-row">
                        <!-- BEGIN NOTIFICATION -->
                        <ul class="nav top-menu">
                            <!-- BEGIN SETTINGS -->
                            <!-- END SETTINGS -->
                            <!-- BEGIN INBOX DROPDOWN -->
                            <li class="dropdown" id="header_inbox_bar">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="icon-envelope-alt"></i>
                                    <span class="badge badge-important">0</span>
                                </a>
                                <ul class="dropdown-menu extended inbox">
                                    <li>
                                        <p>You have 5 new messages</p>
                                    </li>
                                    <li>
                                        <a href="#">See all messages</a>
                                    </li>
                                </ul>
                            </li>
                            <!-- END INBOX DROPDOWN -->
                            <!-- BEGIN NOTIFICATION DROPDOWN -->
                            <li class="dropdown" id="header_notification_bar">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                                    <i class="icon-bell-alt"></i>
                                    <span class="badge badge-warning nombreNotifs"></span>
                                </a>
                                <ul class="dropdown-menu extended notification" region="${sessionScope.personnel.getService().getSite().getRegion().getNomRegion()}" id="${sessionScope.personnel.getIdPersonnel()}">



                                </ul>
                            </li>
                            <!-- END NOTIFICATION DROPDOWN -->

                        </ul>
                    </div>
                    <!-- END  NOTIFICATION -->
                    <div class="top-nav ">
                        <ul class="nav pull-right top-menu" >
                            <!-- BEGIN SUPPORT -->
                            <li class="dropdown mtop5">

                                <a class="dropdown-toggle element" data-placement="bottom" data-toggle="tooltip" href="#" data-original-title="Chat">
                                    <i class="icon-comments-alt"></i>
                                </a>
                            </li>
                            <li class="dropdown mtop5">
                                <a class="dropdown-toggle element" data-placement="bottom" data-toggle="tooltip" href="RedirectionVue?vue=Contact&action=autre" data-original-title="Envoyer un Mail">
                                    <i class="icon-mail-forward"></i>
                                </a>
                            </li>
                            <!-- END SUPPORT -->
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                                    <span class="icon-user username"> ${sessionScope.personnel.getNomPrenom()} </span>
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu extended logout">
                                    <c:if test="${not empty sessionScope.ChangePasseMS or not empty sessionScope.ApparielMS or not empty sessionScope.OperationMS or not empty sessionScope.InfoMS or not empty sessionScope.CommandeCourMS}">
                                        <li><a href="RedirectionVue?vue=profile&action=autre"><i class="icon-user"></i> Mon Profile</a></li> 
                                        </c:if>
                                        <c:if test="${not empty sessionScope.DeconnexionMS}">
                                        <li><a href="authentification?action=faut"><i class="icon-off"></i> Deconnexion</a></li>
                                        </c:if>
                                </ul>
                            </li>
                            <a class="brand" href="#" >
                                <img alt="GCI" src="images/gci.png" style="height: 40px; width: 160px; margin-top: -16px;margin-left: 10px">
                            </a><!-- END USER LOGIN DROPDOWN -->
                        </ul>
                        <!-- END TOP NAVIGATION MENU -->
                    </div>

                </div>
            </div>
            <!-- END TOP NAVIGATION BAR -->
        </div>
        <!-- END HEADER -->
        <!-- BEGIN CONTAINER -->
        <div id="container" class="row-fluid">
            <!-- BEGIN SIDEBAR -->
            <div class="sidebar-scroll">
                <div id="sidebar" class="nav-collapse collapse">

                    <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
                    <div class="navbar-inverse">
                        <form class="navbar-search visible-phone">
                            <input type="text" class="search-query" placeholder="Search" />
                        </form>
                    </div>
                    <!-- END RESPONSIVE QUICK SEARCH FORM -->
                    <!-- BEGIN SIDEBAR MENU -->
                    <ul class="sidebar-menu">
                        <li class="sub-menu <c:if test="${vue=='Accueil'}"> active</c:if>">
                                <a class="" href="RedirectionVue?vue=Accueil">
                                    <i class="icon-home"></i>
                                    <span>ACCUEIL</span>
                                </a>
                            </li>
                        <c:if test="${not empty sessionScope.lien11 or not empty sessionScope.lien10 or not empty sessionScope.lien9 or not empty sessionScope.lien8}">
                            <li class="sub-menu <c:if test="${vue=='Etat de Stock' or vue=='Etat de consommation'}"> active</c:if>">
                                    <a href="javascript:;" class="">
                                        <i class="icon-book"></i>
                                        <span>Etat Magasin</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">
                                    <c:if test="${not empty sessionScope.lien8}">
                                        <li class="sub-menu <c:if test="${vue=='Etat de Stock' }"> active</c:if>"><a href="${sessionScope.lien8}">Stock Magasin</a></li>     
                                        </c:if>                            

                                    <c:if test="${not empty sessionScope.lien10}">
                                        <li class="sub-menu <c:if test="${vue=='Etat de consommation' }"> active</c:if>"><a href="${sessionScope.lien10}">Etat General Magasin</a></li>
                                        </c:if>

                                    <!--  <li><a class="" href="passerCommande?vue=Commande&action=consulterCommandeMP_OK">COMMANDES OK</a></li>  -->

                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien11 or not empty sessionScope.lien7 or not empty sessionScope.lien6 or not empty sessionScope.lien5 or not empty sessionScope.lien4}">
                            <li class="sub-menu <c:if test="${vue=='Consulter les commandes' or vue=='Commande produits' or vue=='Réception transfert' or vue=='Réception commande client'or vue=='Sortir pour consommation'}">active</c:if>">
                                    <a href="javascript:;">
                                        <i class="icon-cogs"></i>
                                        <span>Traitements</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">
                                    <c:if test="${not empty sessionScope.lien4}">
                                        <li class="sub-menu <c:if test="${vue=='Commande produits'}">active</c:if>" ><a class="" href="${sessionScope.lien4}">Passer Commande</a></li>
                                        </c:if>

                                    <c:if test="${not empty sessionScope.lien11}">
                                        <li class="sub-menu <c:if test="${vue=='Consulter les commandes'}">active</c:if>"><a href="${sessionScope.lien11}">Commandes Encours magasin principal</a></li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien5}">
                                        <li class="sub-menu <c:if test="${ vue=='Réception commande client'}">active</c:if>"><a  href="${sessionScope.lien5}">Traitement Commande </a></li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien6}">
                                        <li class="sub-menu <c:if test="${vue=='Réception transfert'}">active</c:if>"><a href="${sessionScope.lien6}">Valider Transfert Recus</a></li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien7}">
                                        <li class="sub-menu <c:if test="${vue=='Sortir pour consommation'}">active</c:if>"><a href="${sessionScope.lien7}">Sortie Consommation</a></li>
                                        </c:if>                             
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.MouvEntrerMS or not empty sessionScope.MouvSortiMS or not empty sessionScope.lien1 or not empty sessionScope.lien2}">
                            <li class="sub-menu <c:if test="${vue=='Inventaire' or vue=='Mouvement Produits' or vue=='Historique inventaire' }"> active</c:if>">
                                    <a href="javascript:;" class="">
                                        <i class="icon-tasks"></i>
                                        <span>Inventaire Magasin</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">
                                    <c:if test="${not empty sessionScope.lien1}">
                                        <li class="<c:if test="${vue=='Inventaire'}"> active</c:if>"><a class="" href="${sessionScope.lien1}">INVENTAIRES</a></li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien2}">
                                        <li class="<c:if test="${vue=='Historique inventaire' }"> active</c:if>"><a class="" href="${sessionScope.lien2}">HISTORIQUES</a></li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.MouvEntrerMS or not empty sessionScope.MouvSortiMS}">
                                        <li class="<c:if test="${vue=='Mouvement Produits'}"> active</c:if>"><a class="" href="listeproduit?vue=Mouvement Produits&action=all_produit">MOUVEMENTS</a></li>        
                                        </c:if>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lienListeAppareil or not empty sessionScope.lienAddAppareil}">
                            <li class="sub-menu <c:if test="${vue == 'Appariels' or vue == 'EditAppariel'}">active</c:if>">
                                    <a href="javascript:;">
                                        <i class="icon-desktop"></i>
                                        <span>Parc</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">
                                    <c:if test="${not empty sessionScope.lienListeAppareil or not empty sessionScope.lienAddAppareil}">
                                        <li class="sub-menu <c:if test="${vue == 'Appariels' or vue == 'EditAppariel'}">active  </c:if>">
                                                <a href="admin?vue=Appariels&action=goHome&niveau=2">
                                                    <span>Appariels</span>
                                                </a>
                                                <!-- second-level-items -->
                                            </li>
                                    </c:if>


                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.CommandeInterMS}">
                            <li class="sub-menu <c:if test="${vue=='Commande Interne' }"> active</c:if>">
                                    <a class="" href="Commande_All_Client?vue=Commande Interne&action=redirect&magasin=MS">
                                        <i class="icon-tasks"></i>
                                        <span>Commande Interne</span>
                                    </a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien9 or not empty sessionScope.BudjetMS or not empty sessionScope.StatistiqueEntrerMS or not empty sessionScope.StatistiqueSortiMS}">
                            <li class="sub-menu <c:if test="${vue == 'rapport' or vue == 'Statistique' or vue=='Bon de Sortir'}">active</c:if>">
                                    <a class="" href="javascript:;">
                                        <i class="icon-dashboard"></i>
                                        <span>Outils</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">
                                    <c:if test="${not empty sessionScope.StatistiqueEntrerMS or not empty sessionScope.StatistiqueSortiMS}">
                                        <li class="sub-menu <c:if test="${vue=='Statistique' }"> active</c:if>">
                                                <a class="" href="RedirectionVue?vue=Statistique">
                                                    <i class="icon-dashboard"></i>
                                                    <span>Tableau de Bord</span>
                                                </a>
                                            </li>                       
                                    </c:if>
                                    <c:if test="${not empty sessionScope.BudjetMS}">
                                        <li class="sub-menu <c:if test="${vue=='rapport' }"> active</c:if>">
                                            <a class="" href="RedirectionVue?&vue=rapport&id_magasin=${sessionScope.id_magasin}&niveau=2&budget=oui">
                                                <i class="icon-signal"></i>
                                                <span>Reporting</span>
                                            </a>
                                        </li>                       
                                    </c:if>

                                    <c:if test="${not empty sessionScope.lien9}">

                                        <li class="<c:if test="${vue=='Bon de Sortir' }"> active</c:if>">
                                            <a href="${sessionScope.lien9}">
                                                <i class="icon-user"></i>
                                                <span>Etat Personnels</span>
                                            </a>
                                        </li>
                                    </c:if>

                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.EcranMS}">
                            <li>
                                <a class="" href="<c:url value="lock.jsp"></c:url>">
                                        <i class="icon-screenshot"></i>
                                        <span>Verrouillé L'Écran</span>
                                    </a>
                                </li>
                        </c:if>
                    </ul>
                    <!-- END SIDEBAR MENU -->
                </div>
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN PAGE -->  
            <div id="main-content">
                <!-- BEGIN PAGE CONTAINER-->
                <div class="container-fluid">
                    <!-- BEGIN THEME CUSTOMIZER-->

                    <c:if test="${not empty sessionScope.lien4}">

                        <c:if test="${sessionScope.etat == 'danger'}">

                            <div class="hidden-phone theme-change">
                                <span class="settings">
                                    <a  href="${sessionScope.lien4}" class="btn btn-danger popovers"   data-trigger="hover" data-placement="bottom" data-content="passer une commande pour le magasin" data-original-title="stock critique" id="pulsate-regular">alert <c:out value="${sessionScope.tail}"/>  produit(s) critique(s) en stock </a>

                                </span>

                            </div>
                            <div class="space20"></div>
                        </c:if>

                        <c:if test="${sessionScope.etat1 == 'warning'}">

                            <div class="hidden-phone theme-change">
                                <span class="settings">
                                    <a  href="${sessionScope.lien4}" class="btn btn-warning popovers"   data-trigger="hover" data-placement="bottom" data-content="passer une commande pour le magasin" data-original-title="stock critique" id="pulsate-regular">alert <c:out value="${sessionScope.taill}"/>  produit(s) critique(s) en stock </a>

                                </span>

                            </div>
                            <div class="space20"></div>
                        </c:if>
                    </c:if>
                    <!-- END THEME CUSTOMIZER-->
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <!-- END PAGE TITLE & BREADCRUMB-->

                    <c:if test="${vue=='Accueil'}">
                        <c:import url="/WEB-INF/magasignerS/Accueil.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Commande produits'}">
                        <c:import url="/WEB-INF/magasignerS/CommandeMSForm.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Consulter les commandes'}">
                        <c:import url="/WEB-INF/magasignerS/ShowCommandeMS.jsp"/>
                    </c:if>

                    <c:if test="${vue=='profile'}">
                        <c:import url="/WEB-INF/magasignerS/profile.jsp"/>
                    </c:if>

                    <c:if test="${vue=='rapport'}">
                        <c:import url="/WEB-INF/magasignerS/Allstatistiques.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Réception transfert'}">
                        <c:import url="/WEB-INF/magasignerS/BonEntre.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Sortir pour consommation'}">
                        <c:import url="/WEB-INF/magasignerS/CommandeClient.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Commande Interne'}">
                        <c:import url="/WEB-INF/magasignerS/CommandeInterne.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Bon de Sortir'}">
                        <c:import url="/WEB-INF/magasignerS/BonSortie.jsp"/>
                    </c:if>

                    <c:if test="${vue=='alertProduit'}">
                        <c:import url="/WEB-INF/magasignerS/alertProduit.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Appariels'}">
                        <c:import url="/WEB-INF/magasignerS/Appariels.jsp"/>
                    </c:if>

                    <c:if test="${vue=='EditAppariel'}">
                        <c:import url="/WEB-INF/magasignerS/updateAppariels.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Réception commande client'}">
                        <c:import url="/WEB-INF/magasignerS/commandeRecus.jsp"/>
                    </c:if>


                    <c:if test="${vue=='Statistique'}">
                        <c:import url="/WEB-INF/magasignerS/statistique.jsp"/>
                    </c:if>


                    <c:if test="${vue=='Inventaire'}">
                        <c:import url="/WEB-INF/magasignerS/UpdateProduits.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Mouvement Produits'}">
                        <c:import url="/WEB-INF/magasignerS/historique_produit.jsp"/>
                    </c:if>

                    <c:if test="${deconnexion=='faut'}">
                        <c:redirect url="login.jsp"/>
                    </c:if> 

                    <c:if test="${vue=='Etat de Stock'}">
                        <c:import url="/WEB-INF/magasignerS/Listeproduit.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Historique inventaire'}">
                        <c:import url="/WEB-INF/magasignerS/InventaireMSForm.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Etat de consommation'}">
                        <c:import url="/WEB-INF/magasignerS/EtatMS.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Contact'}">
                        <c:import url="/WEB-INF/magasignerS/sendmessage.jsp"/>
                    </c:if>
                    <c:if test="${vue=='Ajouter Un Stock'}">
                        <c:import url="/WEB-INF/magasignerS/addStock.jsp"/>
                    </c:if>
                    <c:if test="${vue=='notification'}">
                        <c:import url="/WEB-INF/common/notifications.jsp"/>
                    </c:if>
                    <c:if test="${vue=='agenda'}">
                        <c:import url="/WEB-INF/common/agendaOder.jsp"/>
                    </c:if>
                </div>


            </div>

            <!-- END PAGE CONTENT-->         
        </div>
        <!-- END PAGE CONTAINER-->


        <!-- END CONTAINER -->

        <!-- BEGIN FOOTER -->
        <div id="footer" style="background-color: #000000">
            <div class="" style="margin-right: 80px">MCD GLOBAL SERVICE&nbsp;&nbsp;&nbsp;&nbsp; <a href="#"> Licenses  </a> <a class="pull-right text-success" href="#"> &copy; 2018, made with <i class="icon-heart "></i> by MCD GLOBAL SERVICE  </a></div>
        </div>
        <!-- END FOOTER -->
        <c:import url="footer.jsp"/>

    </body>
    <!-- END BODY -->
</html>