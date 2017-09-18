<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <li class="sub-menu active">
                <a class="" href="StatistiqueMP?vue=Accueil&action=autre">
                    <i class="icon-home"></i>
                    <span>Acceuil</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="javascript:;" class="">
                    <i class="icon-book"></i>
                    <span>Etats Magasin</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a class="" href="ListeProduitMP?vue=Etat de Stock&action=all">STOCKS</a></li>
                    <li><a class="" href="ListeProduitMP?vue=Etat de consommation&action=redirect">CONSOMMATIONS</a></li>
                    <li><a class="" href="passerCommande?vue=Commande&action=consulterCommandeMP">COMMANDES</a></li>
                    <li><a class="" href="CommandeRecusMP?vue=Suivi Transfert&action=ListerTransfertRejeter">TRANSFERT</a></li>
                    <li><a class="" href="passerCommande?vue=Commande&action=consulterCommandeMP_OK">COMMANDES OK</a></li>                              
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;" class="">
                    <i class="icon-cogs"></i>
                    <span>Traitements</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li ><a class="" href="passerCommande?vue=Commande&action=init">COMMANDER</a></li>
                    <li><a class="" href="CommandeRecusMP?vue=Traitement Commande&action=AllCommande">COMMANDES RECUS</a></li>
                    <li><a class="" href="bordereau?vue=Bordereau&action=redirect">BORDEREAU</a></li>                               
                </ul>
            </li>                       
            <li class="sub-menu">
                <a href="javascript:;" class="">
                    <i class="icon-tasks"></i>
                    <span>Inventaire Magasin</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a class="" href="MisaJourProduitMP?vue=Inventaire&action=all">Inventaires</a></li>
                    <li><a class="" href="MisaJourProduitMP?vue=Historique inventaire&action=historique">HISTORIQUES</a></li>
                    <li><a class="" href="ListeProduitMP?vue=Mouvement Produits&action=all_produit">MOUVEMENTS</a></li>                              
                </ul>
            </li>
            <li class="sub-menu">
                <a class="" href="Commande_All_Client?vue=Commande Interne&action=redirect&magasin=MP">
                    <i class="icon-shopping-cart"></i>
                    <span>Commande Interne</span>
                </a>
            </li>
            <li class="sub-menu">
                <a class="" href="StatistiqueMP?vue=Statistique&action=state">
                    <i class="icon-dashboard"></i>
                    <span>Statistiques</span>
                </a>
            </li>                       

            <li>
                <a class="" href="<c:url value="lock.jsp"></c:url>">
                    <i class="icon-screenshot"></i>
                    <span>Verrouillé L'Écran</span>
                </a>
            </li>
        </ul>
        <!-- END SIDEBAR MENU -->




