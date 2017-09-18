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
                        <img alt="LOGO" src="photo/${sessionScope.societe.getLogo()}" style="height: 40px; width: 160px; margin-top: -10px">
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
                                    <span class="badge badge-important size-message">0</span>
                                </a>
                                <ul class="dropdown-menu extended inbox message-list " name="${personnel.getIdPersonnel()}">
                                </ul>
                            </li>
                            <!-- END INBOX DROPDOWN -->
                            <!-- BEGIN NOTIFICATION DROPDOWN -->
                            <li class="dropdown" id="header_notification_bar">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                                    <i class="icon-bell-alt"></i>
                                    <span class="badge badge-warning nombreNotifs"></span>
                                </a>
                                <ul class="dropdown-menu extended notification" region="${personnel.getService().getSite().getRegion().getNomRegion()}" id="${sessionScope.id}">
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
                            <c:if test="${not empty sessionScope.lien61 or sessionScope.GeneralAdministrateur == 'OK'}">
                                <li class="dropdown mtop5">
                                    <a class="dropdown-toggle element" data-placement="bottom" data-toggle="tooltip" href="admin?vue=Contact&action=getAll" data-original-title="Envoyer Un Mail">
                                        <i class="icon-mail-forward"></i>
                                    </a>
                                </li>
                            </c:if>
                            <!-- END SUPPORT -->
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                                    <span class="icon-user"> ${sessionScope.personnel.getNomPrenom()} </span>
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu extended logout">
                                    <c:if test="${not empty sessionScope.lien49 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li><a href="admin?vue=profile&action=profileAdmin"><i class="icon-user"></i> Mon Profile</a></li> 
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien50 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li><a href="authentification?action=faut"><i class="icon-off"></i> Deconnexion</a></li>
                                        </c:if>                         
                                </ul>
                            </li>
                            <a class="brand" href="#" >
                                <img alt="GCI" src="images/gci.png" style="height: 40px; width: 160px; margin-top: -16px;margin-left: 10px">
                            </a>
                            <!-- END USER LOGIN DROPDOWN -->
                        </ul>
                        <!-- END TOP NAVIGATION MENU -->
                    </div>
                </div>
            </div>
            <!-- END TOP NAVIGATION BAR -->
        </div>
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
                        <div class="space15"></div>
                        <li class="sub-menu <c:if test="${vue=='accueil'}">active </c:if>">
                                <a href="admin?vue=accueil&action=getAll">
                                    <i class="icon-home"></i><span>ACCEUIL</span>
                                </a>
                            </li>

                        <c:if test="${ not empty sessionScope.lien52  or not empty sessionScope.lien53 or not empty sessionScope.lien54 or not empty sessionScope.lien55 or not empty sessionScope.lien56 or not empty sessionScope.lien58 or not empty sessionScope.lien66 or not empty sessionScope.lien31 or not empty sessionScope.lien32 or not empty sessionScope.lien30 or not empty sessionScope.lien15 or not empty sessionScope.lien16 or not empty sessionScope.lien17 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="sub-menu <c:if test="${vue=='settingdirection'  or vue == 'centre cout' or vue=='settingsite'or vue=='settingservice' or vue=='societe'  or vue=='region' or vue == 'createMagS'or vue == 'createMagP'}">active</c:if>">
                                    <a href="javascript:;">
                                        <i class="icon-cogs "></i>
                                        <span>Parametrages</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">
                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue == 'societe'}">active  </c:if>">
                                                <a href="personnel?vue=societe&action=allfour"  class=" " >
                                                    Societe</a>
                                            </li>
                                    </c:if>

                                    <c:if test="${not empty sessionScope.lien52  or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='region' }">active</c:if>">
                                                <a href="personnel?vue=region&action=rien"  class=" " >Region</a>
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.lien53  or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='settingdirection' }">active</c:if>">
                                                <a href="personnel?vue=settingdirection&action=rien"  class=" " >Direction</a>
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.lien54  or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='settingsite' }">active</c:if>">
                                                <a href="personnel?vue=settingsite&action=allsite" class=" " >Site</a>
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.lien66  or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='centre cout'}"> active</c:if>">
                                                <a class="" href="personnel?vue=centre cout&action=allcentrecout">
                                                    <span>Centre de Cout</span>
                                                </a>
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.lien55  or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='settingservice' }">active</c:if>">
                                                <a href="personnel?vue=settingservice&action=allservice"  class="">Service</a>
                                            </li>
                                    </c:if>


                                    <c:if test="${not empty sessionScope.lien15 or not empty sessionScope.lien16 or not empty sessionScope.lien17 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='createMagS' }">active</c:if>">
                                                <a href="admin?vue=createMagS&action=getAllMagS&param=yes">

                                                    <span>Magasin Secondaire</span>
                                                </a>
                                            </li>
                                    </c:if>

                                    <c:if test="${not empty sessionScope.lien31 or not empty sessionScope.lien32 or not empty sessionScope.lien30 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue == 'createMagP' }">active </c:if>">
                                                <a href="admin?vue=createMagP&action=getAllMagP&param=yes">

                                                    <span>Magasin Principal</span>
                                                </a>
                                                <!-- second-level-items -->
                                            </li>
                                    </c:if>


                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien1 or not empty sessionScope.lien10 or not empty sessionScope.lien58 or not empty sessionScope.lien56 or not empty sessionScope.lien2 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="sub-menu <c:if test="${vue=='perso'or vue=='settingresponsable' or vue == 'editePersonnel' or vue=='controlleur' or vue=='settinggroupe'}">active</c:if>">
                                    <a href="javascript:;">
                                        <i class="icon-warning-sign"></i>
                                        <span>Administration</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">

                                    <c:if test="${not empty sessionScope.lien1 or not empty sessionScope.lien2 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='perso' or vue == 'editePersonnel'}">active </c:if>">
                                                <a href="admin?vue=perso&action=getAll" class="getallpersonnel">
                                                    <i class="icon-user"></i><span>Utilisateurs</span>
                                                </a>
                                            </li>
                                    </c:if>

                                    <c:if test="${not empty sessionScope.lien10 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='controlleur'}">active  </c:if>">
                                                <a href="admin?vue=controlleur&action=controlleur">
                                                    <i class="icon-bar-chart "></i> 
                                                    <span>Controlleurs</span>
                                                </a>
                                                <!-- second-level-items -->
                                            </li>
                                    </c:if>

                                    <c:if test="${not empty sessionScope.lien58  or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='settingresponsable'}">active  </c:if>">
                                                <a href="personnel?vue=settingresponsable&action=allRespo"  class=" " ><i class="icon-chevron-sign-up"></i>Niveau Validation</a>
                                            </li>
                                    </c:if>

                                    <c:if test="${not empty sessionScope.lien56  or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue == 'settinggroupe'}">active  </c:if>">
                                                <a href="personnel?vue=settinggroupe&action=allgroupe"  class=" " >
                                                    <i class="icon-group"></i> Groupes</a>
                                            </li>
                                    </c:if>


                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien59 or sessionScope.GeneralAdministrateur == 'OK'}">

                            <li class="sub-menu <c:if test="${vue=='budget'or vue == 'fournisseur'}">active</c:if>">
                                    <a href="javascript:;">
                                        <i class="icon-windows"></i>
                                        <span>Gestions</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">

                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue == 'budget'}">active  </c:if>">
                                                <a href="personnel?vue=budget&action=allbudget"  class="">
                                                    <i class="icon-money"></i> Budgets</a>
                                            </li>
                                    </c:if>

                                    <c:if test="${not empty sessionScope.lien59  or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue == 'fournisseur'}">active  </c:if>">
                                                <a href="personnel?vue=fournisseur&action=allfour"  class=" " >
                                                    <i class="icon-user"></i>
                                                    <span>Fournisseurs</span>
                                                </a>
                                            </li>
                                    </c:if>



                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien47 or not empty sessionScope.lien15 or not empty sessionScope.lien31 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="sub-menu <c:if test="${vue=='Commande Interne' or vue=='magS'or vue=='editeMagasinS' or vue=='historique'or vue=='editeMagasinS' or vue=='historique' or vue=='comrecu'or vue=='comMag' or vue=='magP' or vue=='editeMagP' or vue =='historiqueMagP' or vue == 'comrecuMagP' or vue== 'comMagP'}">active</c:if>">
                                    <a href="javascript:;">
                                        <i class="icon-tasks"></i>
                                        <span>Traitements</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">

                                    <c:if test="${not empty sessionScope.lien47}">
                                        <li class="sub-menu <c:if test="${vue=='Commande Interne'}"> active</c:if>">
                                                <a class="" href="Commande_All_Client?vue=Commande Interne&action=redirect&magasin=Admin">
                                                    <span>Besoin Comsommable</span>
                                                </a>
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.lien15 or not empty sessionScope.lien16 or not empty sessionScope.lien17 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='magS' or vue=='editeMagasinS' or vue=='historique'or vue=='editeMagasinS' or vue=='historique' or vue=='comrecu'or vue=='comMag' }">active selected </c:if>">
                                                <a href="admin?vue=magS&action=getAllMagS">
                                                    <span>Magasins Secondaires</span>
                                                </a>
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.lien31 or not empty sessionScope.lien32 or not empty sessionScope.lien30 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='magP' or vue=='editeMagP' or vue =='historiqueMagP' or vue == 'comrecuMagP' or vue== 'comMagP' }">active </c:if>">
                                                <a href="admin?vue=magP&action=getAllMagP">
                                                    <span>Magasins Principals</span>
                                                </a>
                                                <!-- second-level-items -->
                                            </li>
                                    </c:if>


                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="sub-menu <c:if test="${vue=='Categories Produits'or vue == 'articles'}">active</c:if>">
                                    <a href="javascript:;">
                                        <i class="icon-apple"></i>
                                        <span>Consommables</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">

                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='Categories Produits' }">active</c:if>">
                                                <a href="personnel?vue=Categories Produits&action=allfour"  class=" " >Categories Produits</a>
                                            </li>
                                    </c:if>
                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue == 'articles'}">active  </c:if>">
                                                <a href="parametre?vue=articles&action=redirect"  class="">Articles</a>
                                            </li>
                                    </c:if>


                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien7 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="sub-menu <c:if test="${vue=='typeappareil'or vue == 'Fabriquant Appareil' or vue == 'Model Appareil' or vue == 'Appariels' or vue == 'EditAppariel'}">active</c:if>">
                                    <a href="javascript:;">
                                        <i class="icon-desktop"></i>
                                        <span>Parc</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">

                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='typeappareil' }">active</c:if>">
                                                <a href="personnel?vue=typeappareil&action=alltype"  class=" " >Type Appareil</a>
                                            </li>
                                    </c:if>
                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='Model Appareil' }">active</c:if>">
                                                <a href="personnel?vue=Model Appareil&action=allModel"  class=" " >Model Appareil</a>
                                            </li>
                                    </c:if>
                                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='Fabriquant Appareil' }">active</c:if>">
                                                <a href="personnel?vue=Fabriquant Appareil&action=allfabriquant"  class=" " >Fabriquant</a>
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.lien7 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue == 'Appariels' or vue == 'EditAppariel'}">active  </c:if>">
                                                <a href="admin?vue=Appariels&action=goHome">
                                                    <span>Appariels</span>
                                                </a>
                                                <!-- second-level-items -->
                                            </li>
                                    </c:if>


                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien66 or not empty sessionScope.lien67 or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="sub-menu <c:if test="${vue=='statistiques'or vue == 'rapport'}">active</c:if>">
                                    <a href="javascript:;">
                                        <i class="icon-signal"></i>
                                        <span>Outils</span>
                                        <span class="arrow"></span>
                                    </a>
                                    <ul class="sub">

                                    <c:if test="${not empty sessionScope.lien66 or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='rapport'}"> active</c:if>">
                                                <a class="" href="parametre?vue=rapport&action=redirect">
                                                    <i class="icon-dashboard"></i>
                                                    <span>Tableau de bord</span>
                                                </a>
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.lien67  or sessionScope.GeneralAdministrateur == 'OK'}">
                                        <li class="sub-menu <c:if test="${vue=='statistiques'}"> active</c:if>">
                                                <a class="" href="tableau_de_Bord?vue=statistiques&action=consommationSocietead">
                                                    <i class="icon-signal"></i>
                                                    <span>Reporting</span>
                                                </a>
                                            </li>
                                    </c:if>


                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien48  or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li>
                                <a class="" href="<c:url value="lock.jsp"></c:url>">
                                        <i class="icon-screenshot"></i>
                                        <span>Verrouillé L'Écran</span>
                                    </a>
                                </li>
                        </c:if>


                    </ul>
                </div>
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN PAGE -->  
            <div id="main-content">
                <!-- BEGIN PAGE CONTAINER-->
                <div class="container-fluid">
                    <c:if test="${not empty sessionScope.lien51 or sessionScope.GeneralAdministrateur == 'OK'}">
                        <div id="theme-change" class="hidden-phone">
                            <i class="icon-cogs"></i>
                            <span class="settings">
                                <span class="text">Theme Color:</span>
                                <span class="colors">
                                    <span class="color-yellow" data-style="yellow"></span>
                                    <span class="color-default" data-style="default"></span>
                                    <span class="color-green" data-style="green"></span>
                                    <span class="color-gray" data-style="gray"></span>
                                    <span class="color-purple" data-style="purple"></span>
                                    <span class="color-red" data-style="red"></span>
                                </span>
                            </span>
                        </div>
                    </c:if>

                    <c:if test="${vue=='accueil'}">

                        <c:import url="/WEB-INF/administrateur/Accueil.jsp"/>
                    </c:if>

                    <c:if test="${vue=='rapport'}">

                        <c:import url="/WEB-INF/administrateur/rapport.jsp"/>
                    </c:if>

                    <c:if test="${vue=='perso'}">
                        <c:import url="/WEB-INF/administrateur/personnels.jsp"/>
                    </c:if>

                    <c:if test="${vue=='consoService'}">
                        <c:import url="/WEB-INF/administrateur/EtatConsoService.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Appariels'}">
                        <c:import url="/WEB-INF/administrateur/Appariels.jsp"/>
                    </c:if>
                    <c:if test="${vue=='Fabriquant Appareil'}">
                        <c:import url="/WEB-INF/administrateur/fabriquantAppareil.jsp"/>
                    </c:if>
                    <c:if test="${vue=='Model Appareil'}">
                        <c:import url="/WEB-INF/administrateur/modelAppareil.jsp"/>
                    </c:if>

                    <c:if test="${vue=='EditAppariel'}">
                        <c:import url="/WEB-INF/administrateur/updateAppariel.jsp"/>
                    </c:if>

                    <c:if test="${vue=='editePersonnel'}">
                        <c:import url="/WEB-INF/administrateur/editePersonnel.jsp"/>
                    </c:if>
                    <c:if test="${vue=='setting'}">
                        <c:import url="/WEB-INF/administrateur/setting.jsp"/>
                    </c:if>
                    <c:if test="${vue=='magS'}">
                        <c:import url="/WEB-INF/administrateur/magasinSecondaire.jsp"/>
                    </c:if>
                    <c:if test="${vue=='createMagS'}">
                        <c:import url="/WEB-INF/administrateur/magasinSecondaire.jsp"/>
                    </c:if>
                    <c:if test="${vue=='magP'}">
                        <c:import url="/WEB-INF/administrateur/magasinPrincipal.jsp"/>
                    </c:if>
                    <c:if test="${vue=='createMagP'}">
                        <c:import url="/WEB-INF/administrateur/magasinPrincipal.jsp"/>
                    </c:if>

                    <c:if test="${vue=='controlleur'}">
                        <c:import url="/WEB-INF/administrateur/controlleur.jsp"/>
                    </c:if>

                    <c:if test="${vue=='editeMagasinS'}">
                        <c:import url="/WEB-INF/administrateur/editeMagasinS.jsp"/>
                    </c:if>


                    <c:if test="${vue=='editeMagP'}">
                        <c:import url="/WEB-INF/administrateur/editeMagasinP.jsp"/>
                    </c:if>

                    <c:if test="${vue=='historiqueMagP'}">
                        <c:import url="/WEB-INF/administrateur/historiqueMP.jsp"/>
                    </c:if>

                    <c:if test="${vue=='comrecuMagP'}">
                        <c:import url="/WEB-INF/administrateur/consulterCommandeMP.jsp"/>
                    </c:if>

                    <c:if test="${vue=='comMagP'}">
                        <c:import url="/WEB-INF/administrateur/commandeMagasinP.jsp"/>
                    </c:if>

                    <c:if test="${vue=='settingdirection'}">
                        <c:import url="/WEB-INF/administrateur/SettingDirection.jsp"/>
                    </c:if>

                    <c:if test="${vue=='settingsite'}">
                        <c:import url="/WEB-INF/administrateur/SettingSite.jsp"/>
                    </c:if>

                    <c:if test="${vue=='settingservice'}">
                        <c:import url="/WEB-INF/administrateur/SettingService.jsp"/>
                    </c:if>  

                    <c:if test="${vue=='settingresponsable'}">
                        <c:import url="/WEB-INF/administrateur/SettingResponsable.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Categories Produits'}">
                        <c:import url="/WEB-INF/administrateur/AddCategorieProduit.jsp"/>
                    </c:if>
                    <c:if test="${vue=='settingautre'}">
                        <c:import url="/WEB-INF/administrateur/SettingAutre.jsp"/>
                    </c:if> 

                    <c:if test="${vue=='settinggroupe'}">
                        <c:import url="/WEB-INF/administrateur/SettingGroupe.jsp"/>
                    </c:if>

                    <c:if test="${vue=='settingeditgroupe'}">
                        <c:import url="/WEB-INF/administrateur/settingEditGroupe.jsp"/>
                    </c:if> 

                    <c:if test="${vue=='mouvementMagS'}">
                        <c:import url="/WEB-INF/administrateur/mouvementMagS.jsp"/>
                    </c:if>

                    <c:if test="${vue=='mouvementMagP'}">
                        <c:import url="/WEB-INF/administrateur/mouvementMagP.jsp"/>
                    </c:if>
                    <c:if test="${vue=='transfertMagS'}">
                        <c:import url="/WEB-INF/administrateur/transfertRecuMagS.jsp"/>
                    </c:if>
                    <c:if test="${vue=='Contact'}">
                        <c:import url="/WEB-INF/administrateur/sendmessage.jsp"/>
                    </c:if>

                    <c:if test="${vue=='notification'}">
                        <c:import url="/WEB-INF/administrateur/notifications.jsp"/>
                    </c:if>
                    <c:if test="${vue=='region'}">
                        <c:import url="/WEB-INF/administrateur/SettingRegion.jsp"/>
                    </c:if>

                    <c:if test="${vue=='Commande Interne'}">
                        <c:import url="/WEB-INF/administrateur/CommandeClient.jsp"/>
                    </c:if>

                    <c:if test="${vue=='profile'}">
                        <c:import url="/WEB-INF/administrateur/profile.jsp"/>
                    </c:if>
                    <c:if test="${vue=='updateservice'}">
                        <c:import url="/WEB-INF/administrateur/updateService.jsp"/>
                    </c:if>

                    <c:if test="${vue=='budget'}">
                        <c:import url="/WEB-INF/administrateur/budget.jsp"/>
                    </c:if>

                    <c:if test="${vue=='fournisseur'}">
                        <c:import url="/WEB-INF/administrateur/fournisseur.jsp"/>
                    </c:if>

                    <c:if test="${vue=='societe'}">
                        <c:import url="/WEB-INF/administrateur/societe.jsp"/>
                    </c:if>

                    <c:if test="${vue=='agenda'}">
                        <c:import url="/WEB-INF/common/Agenda.jsp"/>
                    </c:if>

                    <c:if test="${vue=='articles'}">
                        <c:import url="/WEB-INF/administrateur/articles.jsp"/>
                    </c:if>

                    <c:if test="${vue=='statistiques'}">
                        <c:import url="/WEB-INF/administrateur/Allstatistiques.jsp"/>
                    </c:if>
                    <c:if test="${vue=='typeappareil'}">
                        <c:import url="/WEB-INF/administrateur/typeAppareil.jsp"/>
                    </c:if>
                    <c:if test="${vue=='centre cout'}">
                        <c:import url="/WEB-INF/administrateur/centreCout.jsp"/>
                    </c:if>

                    <c:import url="/WEB-INF/administrateur/ConfirmUser.jsp"/>
                    <c:import url="/WEB-INF/administrateur/addCategorie.jsp"/>
                    <c:import url="/WEB-INF/administrateur/deleteCategorie.jsp"/>
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