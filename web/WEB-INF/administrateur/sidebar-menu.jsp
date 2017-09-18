<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar-scroll">
    <div id="sidebar" class="nav-collapse collapse">

        <ul class="sidebar-menu">
            <div class="space15"></div>
            <li class="sub-menu <c:if test="${vue=='accueil'}">active </c:if>">
                    <a href="admin?vue=accueil&action=getAll">
                        <i class="icon-home"></i><span>ACCEUIL</span>
                    </a>
                </li>

            <c:if test="${ not empty sessionScope.lien52  or not empty sessionScope.lien53 or not empty sessionScope.lien54 or not empty sessionScope.lien55 or not empty sessionScope.lien56 or not empty sessionScope.lien58 or not empty sessionScope.lien59 or sessionScope.GeneralAdministrateur == 'OK'}">
                <li class="sub-menu <c:if test="${vue=='settingdirection' or vue=='settingsite'or vue=='settingservice' or vue=='societe'  or vue=='region' or vue == 'createMagS'or vue == 'createMagP'}">active</c:if>">
                        <a href="javascript:;">
                            <i class="icon-cogs "></i>
                            <span>Parametrages</span>
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub">
                        <c:if test="${not empty sessionScope.lien59  or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="sub-menu <c:if test="${vue == 'societe'}">active  </c:if>">
                                    <a href="personnel?vue=societe&action=allfour"  class=" " >
                                        <i class="icon-info"></i>
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







                        <c:if test="${not empty sessionScope.lien59  or sessionScope.GeneralAdministrateur == 'OK'}">
                            <li class="sub-menu <c:if test="${vue == 'fournisseur'}">active  </c:if>">
                                    <a href="personnel?vue=fournisseur&action=allfour"  class=" " >fournisseurs</a>
                                </li>
                        </c:if>



                    </ul>
                </li>
            </c:if>

            <li class="sub-menu <c:if test="${vue=='perso'or vue=='settingresponsable' or vue == 'editePersonnel' or vue=='controlleur' or vue=='settinggroupe'}">active</c:if>">
                    <a href="javascript:;">
                        <i class="icon-warning-sign"></i>
                        <span>Administration</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub">

                    <c:if test="${not empty sessionScope.lien1 or not empty sessionScope.lien2 or sessionScope.GeneralAdministrateur == 'OK'}">
                        <li class="sub-menu <c:if test="${vue=='perso' or vue == 'editePersonnel'}">active </c:if>">
                                <a href="admin?vue=perso&action=getAll">
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




            <li class="sub-menu <c:if test="${vue=='budget'or vue == 'fournisseur' or vue == 'centre cout'}">active</c:if>">
                    <a href="javascript:;">
                        <i class=""></i>
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
                                <a href="personnel?vue=fournisseur&action=allfour"  class=" " >fournisseurs</a>
                            </li>
                    </c:if>


                    <li class="sub-menu <c:if test="${vue=='centre cout'}"> active</c:if>">
                            <a class="" href="parametre?vue=centre cout&action=consommationSociete">
                                <i class="icon-shopping-cart"></i>
                                <span>Centre de Cout</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="sub-menu <c:if test="${vue=='Commande Interne' or vue=='magS'or vue=='editeMagasinS' or vue=='historique'or vue=='editeMagasinS' or vue=='historique' or vue=='comrecu'or vue=='comMag' or vue=='magP' or vue=='editeMagP' or vue =='historiqueMagP' or vue == 'comrecuMagP' or vue== 'comMagP'}">active</c:if>">
                    <a href="javascript:;">
                        <i class="icon-tasks"></i>
                        <span>Traitements</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub">

                    <c:if test="${not empty sessionScope.lien47  or sessionScope.GeneralAdministrateur == 'OK'}">
                        <li class="sub-menu <c:if test="${vue=='Commande Interne'}"> active</c:if>">
                                <a class="" href="Commande_All_Client?vue=Commande Interne&action=redirect&magasin=Admin">
                                    <i class="icon-shopping-cart"></i>
                                    <span>Besoin Comsommable</span>
                                </a>
                            </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.lien15 or not empty sessionScope.lien16 or not empty sessionScope.lien17 or sessionScope.GeneralAdministrateur == 'OK'}">
                        <li class="sub-menu <c:if test="${vue=='magS' or vue=='editeMagasinS' or vue=='historique'or vue=='editeMagasinS' or vue=='historique' or vue=='comrecu'or vue=='comMag' }">active selected </c:if>">
                                <a href="admin?vue=magS&action=getAllMagS">
                                    <i class="icon-shopping-cart "></i>
                                    <span>Magasins Secondaires</span>
                                </a>
                            </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.lien31 or not empty sessionScope.lien32 or not empty sessionScope.lien30 or sessionScope.GeneralAdministrateur == 'OK'}">
                        <li class="sub-menu <c:if test="${vue=='magP' or vue=='editeMagP' or vue =='historiqueMagP' or vue == 'comrecuMagP' or vue== 'comMagP' }">active </c:if>">
                                <a href="admin?vue=magP&action=getAllMagP">
                                    <i class="icon-shopping-cart "></i>
                                    <span>Magasins Principals</span>
                                </a>
                                <!-- second-level-items -->
                            </li>
                    </c:if>


                </ul>
            </li>


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

            <li class="sub-menu <c:if test="${vue=='type appareil'or vue == 'Appariels' or vue == 'EditAppariel'}">active</c:if>">
                    <a href="javascript:;">
                        <i class="icon-desktop"></i>
                        <span>Parc</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub">

                    <c:if test="${sessionScope.GeneralAdministrateur == 'OK'}">
                        <li class="sub-menu <c:if test="${vue=='type appareil' }">active</c:if>">
                                <a href="personnel?vue=Categories Produits&action=allfour"  class=" " >Type Appareil</a>
                            </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.lien7 or sessionScope.GeneralAdministrateur == 'OK'}">
                        <li class="sub-menu <c:if test="${vue == 'Appariels' or vue == 'EditAppariel'}">active  </c:if>">
                                <a href="admin?vue=Appariels&action=goHome">
                                    <i class="icon-desktop "></i> 
                                    <span>Appariels</span>
                                </a>
                                <!-- second-level-items -->
                            </li>
                    </c:if>


                </ul>
            </li>

            <li class="sub-menu <c:if test="${vue=='statistiques'or vue == ''}">active</c:if>">
                    <a href="javascript:;">
                        <i class=""></i>
                        <span>Outils</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub">

                    <c:if test="${not empty sessionScope.lien62 or not empty sessionScope.lien63 or not empty sessionScope.lien64 or not empty sessionScope.lien65  or sessionScope.GeneralAdministrateur == 'OK'}">
                        <li class="sub-menu <c:if test="${vue==''}"> active</c:if>">
                                <a class="" href="parametre?vue=statistiques&action=consommationSociete">
                                    <i class="icon-shopping-cart"></i>
                                    <span>Rapports</span>
                                </a>
                            </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.lien62 or not empty sessionScope.lien63 or not empty sessionScope.lien64 or not empty sessionScope.lien65  or sessionScope.GeneralAdministrateur == 'OK'}">
                        <li class="sub-menu <c:if test="${vue=='statistiques'}"> active</c:if>">
                                <a class="" href="parametre?vue=statistiques&action=consommationSociete">
                                    <i class="icon-shopping-cart"></i>
                                    <span>Etat General</span>
                                </a>
                            </li>
                    </c:if>


                </ul>
            </li>

            <c:if test="${not empty sessionScope.lien48  or sessionScope.GeneralAdministrateur == 'OK'}">
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





