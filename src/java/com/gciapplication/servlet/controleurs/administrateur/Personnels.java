/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.servlet.controleurs.administrateur;

import com.gciapplication.entity.Actions;
import com.gciapplication.entity.Butget;
import com.gciapplication.entity.CategorieProduit;
import com.gciapplication.entity.CategorieproduitMS;
import com.gciapplication.entity.CentreCout;
import com.gciapplication.entity.CommandePersonnel;
import com.gciapplication.entity.Direction;
import com.gciapplication.entity.Founisseur;
import com.gciapplication.entity.FrabriquantAppareil;
import com.gciapplication.entity.Groupes;
import com.gciapplication.entity.MagasinSecondaire;
import com.gciapplication.entity.ModelAppariel;
import com.gciapplication.entity.OperationConsommateur;
import com.gciapplication.entity.Page;
import com.gciapplication.entity.Permissions;
import com.gciapplication.entity.Region;
import com.gciapplication.entity.Responsablevalidation;
import com.gciapplication.entity.Service;
import com.gciapplication.entity.Site;
import com.gciapplication.entity.Societe;
import com.gciapplication.entity.StockproduitMS;
import com.gciapplication.entity.TypeAppareil;
import com.gciapplication.session.entity.ButgetFacadeLocal;
import com.gciapplication.session.entity.CategorieProduitFacadeLocal;
import com.gciapplication.session.entity.CategorieproduitMSFacadeLocal;
import com.gciapplication.session.entity.CentreCoutFacadeLocal;
import com.gciapplication.session.entity.CommandePersonnelFacadeLocal;
import com.gciapplication.session.entity.DirectionFacadeLocal;
import com.gciapplication.session.entity.FounisseurFacadeLocal;
import com.gciapplication.session.entity.FrabriquantAppareilFacadeLocal;
import com.gciapplication.session.entity.GroupesFacadeLocal;
import com.gciapplication.session.entity.LoginFacadeLocal;
import com.gciapplication.session.entity.MagasinSecondaireFacadeLocal;
import com.gciapplication.session.entity.ModelApparielFacadeLocal;
import com.gciapplication.session.entity.PageFacadeLocal;
import com.gciapplication.session.entity.PermissionsFacadeLocal;
import com.gciapplication.session.entity.PersonnelFacadeLocal;
import com.gciapplication.session.entity.RegionFacadeLocal;
import com.gciapplication.session.entity.ResponsablevalidationFacadeLocal;
import com.gciapplication.session.entity.ServiceFacadeLocal;
import com.gciapplication.session.entity.SiteFacadeLocal;
import com.gciapplication.session.entity.SocieteFacadeLocal;
import com.gciapplication.session.entity.StockproduitMSFacadeLocal;
import com.gciapplication.session.entity.TypeAppareilFacadeLocal;
import com.gestion.DataSource.mysql.Message;
import com.gestion.DataSource.mysql.date_du_jour;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//
@WebServlet(name = "Personnel", urlPatterns = {"/personnel"})
public class Personnels extends HttpServlet {

    @EJB
    private LoginFacadeLocal loginFacade;

    @EJB
    private FrabriquantAppareilFacadeLocal frabriquantAppareilFacade;

    @EJB
    private ModelApparielFacadeLocal modelApparielFacade;

    @EJB
    private CentreCoutFacadeLocal centreCoutFacade;

    @EJB
    private TypeAppareilFacadeLocal typeAppareilFacade;

    @EJB
    private PersonnelFacadeLocal personnelFacade;

    @EJB
    private CategorieproduitMSFacadeLocal categorieproduitMSFacade;

    @EJB
    private MagasinSecondaireFacadeLocal magasinSecondaireFacade;

    @EJB
    private CategorieProduitFacadeLocal categorieProduitFacade;

    @EJB
    private PageFacadeLocal pageFacade;

    @EJB
    private PermissionsFacadeLocal permissionsFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private FounisseurFacadeLocal founisseurFacade;

    @EJB
    private ResponsablevalidationFacadeLocal responsablevalidationFacade;

    @EJB
    private GroupesFacadeLocal groupesFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private SiteFacadeLocal siteFacade;

    @EJB
    private ButgetFacadeLocal butgetFacade;

    @EJB
    private StockproduitMSFacadeLocal stockproduitMSFacade;

    @EJB
    private CommandePersonnelFacadeLocal commandePersonnelFacade;

    @EJB
    private RegionFacadeLocal regionFacade;

    @EJB
    private DirectionFacadeLocal directionFacade;

    List<com.gciapplication.entity.Personnel> listePersonnel;
    List<CommandePersonnel> commandess = new ArrayList<>();
    List<OperationConsommateur> list = new ArrayList<>();

    com.gciapplication.entity.Personnel p, personnell;

    private final String etatC = "encour de traitement";
    int idP, idcli;
    double prixTotal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String actions = request.getParameter("action");
        String vue = request.getParameter("vue");
        List<Region> regions = regionFacade.findAll();
        request.setAttribute("regions", regions);
        // List<MagasinSecondaire> mses = magasinSecondaireFacade.findAll();
        // request.setAttribute("magasinsMS", mses);
        List<Butget> lc = butgetFacade.findAll();
        request.setAttribute("butget", lc);
        List<CategorieProduit> catProduit = categorieProduitFacade.findAll();
        request.setAttribute("type_categorie", catProduit);
        // int idMS = personnel.getMagasinSecondaireList().get(0).getIdMagasin();
        if (actions.equalsIgnoreCase("addRegion")) {
            String Region = request.getParameter("region");
            Region region = new Region();
            region.setNomRegion(Region);
            boolean repea = false;
            for (Region ser : regionFacade.findAll()) {
                if (ser.getNomRegion().equalsIgnoreCase(Region)) {
                    repea = true;
                    break;
                }
            }
            if (repea) {
                Message message = new Message("Cette Region Existe déjà");
                request.setAttribute("message", message);
            } else {
                regionFacade.create(region);
            }

            request.removeAttribute("regions");
            List<Region> regionss = regionFacade.findAll();
            request.setAttribute("regions", regionss);

        } else if (actions.equalsIgnoreCase("allcentrecout")) {

            request.setAttribute("centreCout", centreCoutFacade.findAll());

        } else if (actions.equalsIgnoreCase("addcentrecout")) {
            String Centre = request.getParameter("centrecout");
            CentreCout cc = new CentreCout();
            cc.setLibelle(Centre);
            centreCoutFacade.create(cc);
            request.setAttribute("centreCout", centreCoutFacade.findAll());

        } else if (actions.equalsIgnoreCase("editecentrecout")) {
            int Centre = Integer.parseInt(request.getParameter("id_cc"));
            CentreCout cc = centreCoutFacade.find(Centre);
            request.setAttribute("cc", cc);
            request.setAttribute("update", "oui");
            request.setAttribute("centreCout", centreCoutFacade.findAll());

        } else if (actions.equalsIgnoreCase("updatecentrecout")) {
            int Centre = Integer.parseInt(request.getParameter("id_cc"));
            String lib = request.getParameter("centrecout");
            CentreCout cc = centreCoutFacade.find(Centre);
            cc.setLibelle(lib);
            centreCoutFacade.edit(cc);
            request.setAttribute("centreCout", centreCoutFacade.findAll());

        } else if (actions.equalsIgnoreCase("deletecentrecout")) {
            int Centre = Integer.parseInt(request.getParameter("id_cc"));

            CentreCout cc = centreCoutFacade.find(Centre);
            centreCoutFacade.remove(cc);
            request.setAttribute("centreCout", centreCoutFacade.findAll());

        } else if (actions.equalsIgnoreCase("allModel")) {

            request.setAttribute("models", modelApparielFacade.findAll());

        } else if (actions.equalsIgnoreCase("editeModelAppareil")) {
            int id_model = Integer.parseInt(request.getParameter("id_model"));
            ModelAppariel ma = modelApparielFacade.find(id_model);

            request.setAttribute("model", ma);
            request.setAttribute("updateModel", "yes");
            request.setAttribute("models", modelApparielFacade.findAll());

        } else if (actions.equalsIgnoreCase("updateModelAppareil")) {
            int id_model = Integer.parseInt(request.getParameter("id_model"));
            String model = request.getParameter("model");
            ModelAppariel ma = modelApparielFacade.find(id_model);
            ma.setNomModel(model);
            modelApparielFacade.edit(ma);

            request.setAttribute("models", modelApparielFacade.findAll());

        } else if (actions.equalsIgnoreCase("addModelAppareil")) {

            String model = request.getParameter("model");
            ModelAppariel ma = new ModelAppariel();
            ma.setNomModel(model);
            modelApparielFacade.create(ma);

            request.setAttribute("models", modelApparielFacade.findAll());

        } else if (actions.equalsIgnoreCase("deleteModelAppareil")) {
            int id_model = Integer.parseInt(request.getParameter("id_model"));
            ModelAppariel ma = modelApparielFacade.find(id_model);
            modelApparielFacade.remove(ma);
            request.setAttribute("models", modelApparielFacade.findAll());

        } else if (actions.equalsIgnoreCase("allfabriquant")) {

            request.setAttribute("fabriquantappareil", frabriquantAppareilFacade.findAll());

        } else if (actions.equalsIgnoreCase("editfabriquantAppareil")) {
            int id_fabriquant = Integer.parseInt(request.getParameter("id_fabriquant"));
            FrabriquantAppareil fa = frabriquantAppareilFacade.find(id_fabriquant);
            request.setAttribute("fabriquant", fa);
            request.setAttribute("updateFabriquant", "yes");
            request.setAttribute("fabriquantappareil", frabriquantAppareilFacade.findAll());

        } else if (actions.equalsIgnoreCase("updateFabriquantAppareil")) {
            int id_fabriquant = Integer.parseInt(request.getParameter("id_fabriquant"));
            String fabriquant = request.getParameter("fabriquant");
            FrabriquantAppareil fa = frabriquantAppareilFacade.find(id_fabriquant);
            fa.setNomFabriquant(fabriquant);
            frabriquantAppareilFacade.edit(fa);
            request.setAttribute("fabriquant", fa);
            request.setAttribute("fabriquantappareil", frabriquantAppareilFacade.findAll());

        } else if (actions.equalsIgnoreCase("addFabriquantAppareil")) {

            String fabriquant = request.getParameter("fabriquant");
            FrabriquantAppareil fa = new FrabriquantAppareil();
            fa.setNomFabriquant(fabriquant);
            frabriquantAppareilFacade.create(fa);
            request.setAttribute("fabriquantappareil", frabriquantAppareilFacade.findAll());

        } else if (actions.equalsIgnoreCase("deletefabriquantAppareil")) {
            int id_fabriquant = Integer.parseInt(request.getParameter("id_fabriquant"));
            FrabriquantAppareil fa = frabriquantAppareilFacade.find(id_fabriquant);
            frabriquantAppareilFacade.remove(fa);
            request.setAttribute("fabriquantappareil", frabriquantAppareilFacade.findAll());

        } else if (actions.equalsIgnoreCase("alltype")) {

            request.setAttribute("typeappareil", typeAppareilFacade.findAll());

        } else if (actions.equalsIgnoreCase("addTypeAppareil")) {
            String nomtype = request.getParameter("type_appareil");
            TypeAppareil newtype = new TypeAppareil();
            newtype.setNom(nomtype);
            typeAppareilFacade.create(newtype);
            request.setAttribute("typeappareil", typeAppareilFacade.findAll());

        } else if (actions.equalsIgnoreCase("updateTypeAppareil")) {
            int idtype = Integer.parseInt(request.getParameter("id_type"));
            String nom = request.getParameter("type_categorie");
            TypeAppareil newtype = typeAppareilFacade.find(idtype);
            newtype.setNom(nom);
            typeAppareilFacade.edit(newtype);
            request.setAttribute("typeappareil", typeAppareilFacade.findAll());
            request.setAttribute("update", "oui");

        } else if (actions.equalsIgnoreCase("deleteTypeAppareil")) {
            int idtype = Integer.parseInt(request.getParameter("id_type"));

            TypeAppareil newtype = typeAppareilFacade.find(idtype);

            typeAppareilFacade.remove(newtype);
            request.setAttribute("typeappareil", typeAppareilFacade.findAll());

        } else if (actions.equalsIgnoreCase("editTypeAppareil")) {
            int idtype = Integer.parseInt(request.getParameter("id_type"));
            TypeAppareil newtype = typeAppareilFacade.find(idtype);

            request.setAttribute("typeappareil", typeAppareilFacade.findAll());
            request.setAttribute("typeapp", newtype);
            request.setAttribute("updatetype", "oui");

        } else if (actions.equalsIgnoreCase("allclient")) {
            int idMagS = Integer.parseInt(request.getParameter("idMagasin"));//je recupere l'id du magasin secondaire
            p = (com.gciapplication.entity.Personnel) session.getAttribute("personnel");
            listePersonnel = commandePersonnelFacade.findByEtatCommandeReturnPersonel(idMagS, etatC);

            request.setAttribute("listepersonnel", listePersonnel);

            request.setAttribute("idMagasin", idMagS);

        } else if (actions.equalsIgnoreCase("lister")) {
            //   int idMagS = Integer.parseInt(request.getParameter("idMagasin"));//je recupere l'id du magasin secondaire
            p = (com.gciapplication.entity.Personnel) session.getAttribute("personnel");
            idcli = Integer.parseInt(request.getParameter("idclient"));
            int idMS = Integer.parseInt(request.getParameter("idMagasin"));

            commandess.removeAll(commandess);
            list.removeAll(list);
            commandess = commandePersonnelFacade.findpersonnelCommande(idcli, idMS, etatC);

            prixTotal = 0;

            for (int i = 0; i < commandess.size(); i++) {

                CommandePersonnel cp = commandess.get(i);

                StockproduitMS mS = stockproduitMSFacade.findByDesignationByidMS(idMS, cp.getDesignations());

                OperationConsommateur oc = new OperationConsommateur();

                oc.setAppariel(cp.getCodeAppareil().getNumeroParck());

                oc.setCategorie(mS.getCategorie().getNomCategorie());
                oc.setCodeProduit(mS.getCodeProduit());
                oc.setDate(cp.getDate());
                oc.setDesignation(cp.getDesignations());
                oc.setPrix(mS.getPrixUnitaire());
                oc.setPrixTotal(mS.getPrixUnitaire() * cp.getQuantite());
                oc.setQuantite(cp.getQuantite());
                oc.setPersonnel(new com.gciapplication.entity.Personnel(idcli));
                oc.setMagasin(new MagasinSecondaire(idMS));
                oc.setStock(mS.getQuantite());
                oc.setOperateur(p.getNomPrenom());
                oc.setStockApres(mS.getQuantite() - oc.getQuantite());
                prixTotal += mS.getPrixUnitaire() * cp.getQuantite();

                list.add(oc);

            }

            String action = "detaill";
            request.setAttribute("action", action);
            request.setAttribute("total", prixTotal);
            String details = "detail";
            request.setAttribute("details", details);

            request.setAttribute("listepersonnel", listePersonnel);
            request.setAttribute("list", list);
            request.setAttribute("commande", commandess);

        } else if (actions.equalsIgnoreCase("mouvementMagS")) {
            int idMag = Integer.parseInt(request.getParameter("idMagasin"));
            request.setAttribute("idMagasin", idMag);

        } else if (actions.equalsIgnoreCase("editMagP")) {
            int idMag = Integer.parseInt(request.getParameter("id_magasin"));
            request.setAttribute("idMagasin", idMag);

        } else if (actions.equalsIgnoreCase("rien")) {
            List<Direction> directions = directionFacade.findAll();
            List<Direction> directionss = new ArrayList<>();
            for (Direction direction : directions) {
                direction.setServiceList(serviceFacade.findAllByIdDirection(direction.getIdDirection()));
                directionss.add(direction);
            }
            request.setAttribute("directions", directionss);

        } else if (actions.equalsIgnoreCase("EditDirection")) {
            int id_region = Integer.parseInt(request.getParameter("region"));
            int id_direction = Integer.parseInt(request.getParameter("direction"));
            String name_direction = request.getParameter("direction");
            //String name_directeur = request.getParameter("directeur");

            Region region = regionFacade.find(id_region);
            Direction direction = directionFacade.find(id_direction);

            direction.setNomDirection(name_direction);
            direction.setRegion(region);
            directionFacade.edit(direction);
            List<Direction> directions = directionFacade.findAll();

            request.setAttribute("directions", directions);

        } else if (actions.equalsIgnoreCase("addDirection")) {
            String region = request.getParameter("region");
            String nomDirection = request.getParameter("nom_direction");
            //String directeur = request.getParameter("directeur");

            Direction direction = new Direction();
            direction.setDirecteur("Aucun");
            direction.setNomDirection(nomDirection);
            direction.setRegion(new Region(Integer.parseInt(region)));
            boolean repea = false;
            for (Direction ser : directionFacade.findAll()) {
                if (ser.getNomDirection().equalsIgnoreCase(nomDirection)) {
                    repea = true;
                    break;
                }
            }
            if (repea) {
                Message message = new Message("Cette Direction Existe déja");
                request.setAttribute("message", message);
            } else {
                directionFacade.create(direction);
            }

            List<Direction> directions = directionFacade.findAll();

            request.setAttribute("directions", directions);

        } else if (actions.equalsIgnoreCase("deleteDirection")) {

            int id_direction = Integer.parseInt(request.getParameter("direction"));

            Direction direction = directionFacade.find(id_direction);

            directionFacade.remove(direction);
            List<Direction> directions = directionFacade.findAll();

            request.setAttribute("directions", directions);

        } else if (actions.equalsIgnoreCase("allsite")) {
            List<Site> sites = siteFacade.findAll();
            List<Site> sitess = new ArrayList<>();
            for (Site site : sites) {
                site.setServiceList(serviceFacade.findAllByIdSte(site.getIdSite()));
                sitess.add(site);
            }
            request.setAttribute("sites", sitess);

            List<Direction> directions = directionFacade.findAll();
            request.setAttribute("directions", directions);

        } else if (actions.equalsIgnoreCase("allservice")) {
            List<Service> services = serviceFacade.findAll();
            List<Service> servicess = new ArrayList<>();
            for (Service service : services) {
                service.setPersonnelList(personnelFacade.findAllByIdService(service.getIdService()));
                servicess.add(service);
            }
            request.setAttribute("services", servicess);
            List<Direction> directions = directionFacade.findAll();
            List<CentreCout> couts = centreCoutFacade.findAll();
            request.setAttribute("centreCout", couts);
            request.setAttribute("directions", directions);

        } else if (actions.equalsIgnoreCase("allgroupe")) {
            List<Groupes> groupess = new ArrayList<>();
            List<Groupes> groupes = groupesFacade.findAll();
            for (Groupes groupe : groupes) {
                groupe.setLoginList(loginFacade.findAllByIdGroupe(groupe.getIdGroupes()));
                groupe.setPermissionsList(permissionsFacade.findAllByIdGrpoupe(groupe.getIdGroupes()));
                groupess.add(groupe);
            }
            request.setAttribute("groupes", groupess);

        } else if (actions.equalsIgnoreCase("allRespo")) {
            List<Responsablevalidation> responsables = responsablevalidationFacade.findAll();
            request.setAttribute("respo", responsables);

        } else if (actions.equalsIgnoreCase("allfour")) {
            List<Founisseur> fournisseurs = founisseurFacade.findAll();
            request.setAttribute("fournisseurs", fournisseurs);
            List<CategorieProduit> categorieProduits = categorieProduitFacade.findAll();
            request.setAttribute("type_categorie", categorieProduits);

        } else if (actions.equalsIgnoreCase("editgroupe")) {
            int idgroupe = Integer.parseInt(request.getParameter("idgroupe"));
            Groupes groupes = groupesFacade.find(idgroupe);
            List<Page> ps = new ArrayList<>();
            List<Page> allniveau = pageFacade.findByNiveau(groupes.getNiveau());

            // List<Page> pagegroupe = new ArrayList<>();
            List<Permissions> pagespermise = permissionsFacade.findAllByIdGrpoupe(idgroupe);
            pagespermise.stream().forEach((permission) -> {
                ps.add(permission.getPage());
            });

            allniveau.removeAll(ps);
            request.setAttribute("groupe", groupes);
            request.setAttribute("allniveau", allniveau);
            request.setAttribute("pagegroupe", pagespermise);

        } else if (actions.equalsIgnoreCase("addGroupe")) {
            String region = request.getParameter("region");
            String typegroupe = request.getParameter("typeGroupe");
            String nomgroupe = request.getParameter("nomGroupe");
            String[] pagegroupe = request.getParameterValues("pagesGroupe");
            List<Permissions> l = new ArrayList<>();

            Groupes groupe = new Groupes();
            groupe.setNomGroupe(nomgroupe);
            groupe.setNiveau(Integer.parseInt(typegroupe));
            groupe.setRegion(new Region(Integer.parseInt(region)));
            groupesFacade.create(groupe);

            for (String string : pagegroupe) {
                Page page = pageFacade.find(Integer.parseInt(string));
                Permissions p = new Permissions();
                p.setAction(new Actions(1));
                p.setGroupe(groupesFacade.findLastInsert());
                p.setPage(page);
                permissionsFacade.create(p);
                l.add(p);

            }
            Groupes group = groupesFacade.findLastInsert();
            group.setPermissionsList(l);
            groupesFacade.edit(group);

            List<Groupes> groupes = groupesFacade.findAll();
            request.setAttribute("groupes", groupes);

        } else if (actions.equalsIgnoreCase("misajourGroupe")) {
            String[] pagegroupe = request.getParameterValues("pagesGroupe");
            List<Permissions> l = new ArrayList<>();
            //  date_du_jour.DeletePermissiond(Integer.parseInt(request.getParameter("idgroupe")));
            int idgroupe = Integer.parseInt(request.getParameter("idgroupe"));
            permissionsFacade.removeByIdGroupe(idgroupe);
            for (String string : pagegroupe) {
                Page page = pageFacade.find(Integer.parseInt(string));
                Permissions p = new Permissions();
                p.setAction(new Actions(1));
                p.setGroupe(groupesFacade.find(idgroupe));
                p.setPage(page);
                permissionsFacade.create(p);
                l.add(p);

            }
            Groupes group = groupesFacade.find(idgroupe);
            group.setPermissionsList(l);
            groupesFacade.edit(group);

            List<Groupes> groupes = groupesFacade.findAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("groupe", groupesFacade.find(Integer.parseInt(request.getParameter("idgroupe"))));
        } else if (actions.equalsIgnoreCase("deleteGroupe")) {
            int idgroupe = Integer.parseInt(request.getParameter("idgroupe"));
            Groupes groupe = groupesFacade.find(idgroupe);
            groupesFacade.remove(groupe);
            List<Groupes> groupes = groupesFacade.findAll();
            request.setAttribute("groupes", groupes);

        } else if (actions.equalsIgnoreCase("addFournisseur")) {
            String nomFournisseur = request.getParameter("nom_fournisseur");
            String adresse = request.getParameter("adresse_fournisseur");
            String phone = request.getParameter("phone_fournisseur");
            String mail = request.getParameter("mail");

            Founisseur fournisseur = new Founisseur();
            fournisseur.setAdresse(adresse);
            fournisseur.setNomFounisseur(nomFournisseur);
            fournisseur.setTelephone(phone);
            fournisseur.setEmail(mail);
            boolean repea = false;
            for (Founisseur ser : founisseurFacade.findAll()) {
                if (ser.getNomFounisseur().equalsIgnoreCase(nomFournisseur)) {
                    repea = true;
                    break;
                }
            }
            if (repea) {
                Message message = new Message("ce Fournisseur Existe déja");
                request.setAttribute("message", message);
            } else {
                founisseurFacade.create(fournisseur);
            }

            List<Founisseur> fournisseurs = founisseurFacade.findAll();
            request.setAttribute("fournisseurs", fournisseurs);

        } else if (actions.equalsIgnoreCase("selectFournisseur")) {
            int idFournisseur = Integer.parseInt(request.getParameter("idfournis"));
            Founisseur fournisseur = founisseurFacade.find(idFournisseur);
            List<Founisseur> fournisseurs = founisseurFacade.findAll();
            fournisseurs.remove(fournisseur);
            request.setAttribute("fournisseurs", fournisseurs);
            request.setAttribute("fournisseur", fournisseur);
            request.setAttribute("editFour", "OK");

        } else if (actions.equalsIgnoreCase("UpdateFournisseur")) {
            int idFournisseur = Integer.parseInt(request.getParameter("idfournis"));
            String nomFournisseur = request.getParameter("nom_fournisseur");
            String adresse = request.getParameter("adresse_fournisseur");
            String phone = request.getParameter("phone_fournisseur");
            String mail = request.getParameter("mail");

            Founisseur fournisseur = founisseurFacade.find(idFournisseur);
            fournisseur.setAdresse(adresse);
            fournisseur.setNomFounisseur(nomFournisseur);
            fournisseur.setTelephone(phone);
            fournisseur.setEmail(mail);
            founisseurFacade.edit(fournisseur);

            List<Founisseur> fournisseurs = founisseurFacade.findAll();
            request.setAttribute("fournisseurs", fournisseurs);

        } else if (actions.equalsIgnoreCase("deleteFournisseur")) {
            int idFournisseur = Integer.parseInt(request.getParameter("idfournis"));
            Founisseur fournisseur = founisseurFacade.find(idFournisseur);
            founisseurFacade.remove(fournisseur);

            List<Founisseur> fournisseurs = founisseurFacade.findAll();
            request.setAttribute("fournisseurs", fournisseurs);
        } else if (actions.equalsIgnoreCase("addSociete")) {
            String nom = request.getParameter("nom_societe");
            String adresse = request.getParameter("adresse_societe");
            String phone = request.getParameter("phone_societe");
            String code = request.getParameter("code_postal");

            Societe societe = new Societe();
            societe.setCodePostal(code);
            societe.setTelephone(phone);
            societe.setAdresse(adresse);
            societe.setNomSociete(nom);
            societeFacade.create(societe);

            List<Founisseur> fournisseurs = founisseurFacade.findAll();
            request.setAttribute("fournisseurs", fournisseurs);

        } else if (actions.equalsIgnoreCase("addResponsable")) {
            String Nom = request.getParameter("type_control");
            String priorite = request.getParameter("priorite");

            Responsablevalidation responsableVal = new Responsablevalidation();

            responsableVal.setDescription(Nom);
            responsableVal.setNiveau(Integer.parseInt(priorite));

            responsablevalidationFacade.create(responsableVal);

            List<Responsablevalidation> responsables = responsablevalidationFacade.findAll();
            request.setAttribute("respo", responsables);

        } else if (actions.equalsIgnoreCase("editResponsable")) {
            int idrespo = Integer.parseInt(request.getParameter("id_responsable"));
            Responsablevalidation respo = responsablevalidationFacade.find(idrespo);
            List<Responsablevalidation> responsables = responsablevalidationFacade.findAll();
            request.setAttribute("respo", responsables);
            request.setAttribute("responsable", respo);
            request.setAttribute("updaterespo", "oui");

        } else if (actions.equalsIgnoreCase("updateResponsable")) {
            int idrespo = Integer.parseInt(request.getParameter("id_responsable"));

            String Nom = request.getParameter("type_control");
            String priorite = request.getParameter("priorite");

            Responsablevalidation responsableVal = responsablevalidationFacade.find(idrespo);

            responsableVal.setDescription(Nom);
            responsableVal.setNiveau(Integer.parseInt(priorite));

            responsablevalidationFacade.edit(responsableVal);

            List<Responsablevalidation> responsables = responsablevalidationFacade.findAll();
            request.setAttribute("respo", responsables);

        } else if (actions.equalsIgnoreCase("deleteResponsable")) {
            int idrespo = Integer.parseInt(request.getParameter("id_responsable"));
            Responsablevalidation responsableVal = responsablevalidationFacade.find(idrespo);

            responsablevalidationFacade.remove(responsableVal);

            List<Responsablevalidation> responsables = responsablevalidationFacade.findAll();
            request.setAttribute("respo", responsables);

        } else if (actions.equalsIgnoreCase("addService")) {
            String nomService = request.getParameter("nom_service");
            int centre = Integer.parseInt(request.getParameter("centrecout"));
            String direction = request.getParameter("direction");
            String site = request.getParameter("site");
            Service service = new Service();
            service.setDirection(directionFacade.find(Integer.parseInt(direction)));
            service.setSite(siteFacade.find(Integer.parseInt(site)));
            service.setNomService(nomService);
            service.setCentreCout(centreCoutFacade.find(centre));
            boolean repea = false;
            for (Service ser : serviceFacade.findAll()) {
                if (ser.getNomService().equalsIgnoreCase(nomService)) {
                    repea = true;
                    break;
                }
            }
            if (repea) {
                Message message = new Message("ce Service Existe déja");
                request.setAttribute("message", message);
            } else {
                serviceFacade.create(service);
            }

            List<Service> services = serviceFacade.findAll();
            request.setAttribute("services", services);
            List<Direction> directions = directionFacade.findAll();
            request.setAttribute("directions", directions);
            request.setAttribute("centreCout", centreCoutFacade.findAll());

        } else if (actions.equalsIgnoreCase("addBudgetMagasin")) {
            String idMS = request.getParameter("id_Magasin");
            String montant = request.getParameter("montant");
            String dateAtt = request.getParameter("dateAttribution");
            String dateExp = request.getParameter("dateExpiration");
            String typebudget = request.getParameter("type_budget");

            CategorieproduitMS catmag = categorieproduitMSFacade.find(Integer.parseInt(typebudget));

            CategorieProduit cp = categorieProduitFacade.findByTypeCategorie(catmag.getNomCategorie());

            MagasinSecondaire s = magasinSecondaireFacade.find(Integer.parseInt(idMS));
            List<Butget> l = new ArrayList<>();
            Butget butget = new Butget();
            butget.setMontant(Double.parseDouble(montant));
            butget.setMontantRestant(Double.parseDouble(montant));
            butget.setMagasin(new MagasinSecondaire(Integer.parseInt(idMS)));
            butget.setTypeBudget(cp.getTypeCategorie());
            butget.setIdCategorie(cp);
            butget.setDateDatribution(date_du_jour.dateConvert(dateAtt));
            butget.setDateExpiration(date_du_jour.dateConvert(dateExp));
            butgetFacade.create(butget);
            l.add(butget);
            s.setButgetList(l);
            magasinSecondaireFacade.edit(s);
            l.clear();
            l = butgetFacade.findAll();
            request.setAttribute("butget", l);

        } else if (actions.equalsIgnoreCase("updatebudget")) {
            int idBudget = Integer.parseInt(request.getParameter("id_budget"));
            Butget newbudget = butgetFacade.find(idBudget);
            request.setAttribute("budget", newbudget);
            List<Region> allregion = regionFacade.findAll();
            request.setAttribute("regions", allregion);
            request.setAttribute("updatebudget", "oui");

        } else if (actions.equalsIgnoreCase("deletebudget")) {
            int idBudget = Integer.parseInt(request.getParameter("id_budget"));
            Butget newbudget = butgetFacade.find(idBudget);
            butgetFacade.remove(newbudget);

        } else if (actions.equalsIgnoreCase("updateBudgetMagasin")) {
            int idBudget = Integer.parseInt(request.getParameter("id_budget"));
            String idMS = request.getParameter("id_Magasin");
            String montant = request.getParameter("montant");
            String dateAtt = request.getParameter("dateAttribution");
            String dateExp = request.getParameter("dateExpiration");
            String typebudget = request.getParameter("type_budget");
            Butget newbudget = butgetFacade.find(idBudget);
            CategorieproduitMS catmag = new CategorieproduitMS();
            List<CategorieproduitMS> catmags = categorieproduitMSFacade.findCatByidMS(Integer.parseInt(idMS));
            for (CategorieproduitMS cat : catmags) {
                if (cat.getNomCategorie().equalsIgnoreCase(typebudget)) {
                    catmag = cat;
                }
            }

            CategorieProduit cp = categorieProduitFacade.findByTypeCategorie(catmag.getNomCategorie());

            MagasinSecondaire s = magasinSecondaireFacade.find(Integer.parseInt(idMS));
            List<Butget> l = new ArrayList<>();

            newbudget.setMontant(Double.parseDouble(montant));
            newbudget.setMontantRestant(Double.parseDouble(montant));
            newbudget.setMagasin(new MagasinSecondaire(Integer.parseInt(idMS)));
            newbudget.setTypeBudget(cp.getTypeCategorie());
            newbudget.setIdCategorie(cp);
            newbudget.setDateDatribution(date_du_jour.dateConvert(dateAtt));
            newbudget.setDateExpiration(date_du_jour.dateConvert(dateExp));
            butgetFacade.edit(newbudget);
            l.add(newbudget);
            s.setButgetList(l);
            magasinSecondaireFacade.edit(s);
            l.clear();
            l = butgetFacade.findAll();
            request.setAttribute("butget", l);

        } else if (actions.equalsIgnoreCase("addCategorieProduits")) {
            String categorie = request.getParameter("type_categorie");
            CategorieProduit cp = new CategorieProduit();
            cp.setTypeCategorie(categorie);

            boolean repea = false;
            for (CategorieProduit ser : categorieProduitFacade.findAll()) {
                if (ser.getTypeCategorie().equalsIgnoreCase(categorie)) {
                    repea = true;
                    break;
                }
            }
            if (repea) {
                Message message = new Message("Cette Catégorie De Produit Existe déja");
                request.setAttribute("message", message);
            } else {
                categorieProduitFacade.create(cp);
            }

            List<CategorieProduit> catProduitt = categorieProduitFacade.findAll();
            request.setAttribute("type_categorie", catProduitt);

        } else if (actions.equalsIgnoreCase("addsite")) {
            String region = request.getParameter("region");
            String nomSite = request.getParameter("nom_site");

            Site site = new Site();
            site.setNomSite(nomSite);
            site.setServiceList(site.getServiceList());
            site.setRegion(new Region(Integer.parseInt(region)));
            boolean repea = false;
            for (Site ser : siteFacade.findAll()) {
                if (ser.getNomSite().equalsIgnoreCase(nomSite)) {
                    repea = true;
                    break;
                }
            }
            if (repea) {
                Message message = new Message("Ce Site Existe déja");
                request.setAttribute("message", message);
            } else {
                siteFacade.create(site);
            }

            List<Site> sites = siteFacade.findAll();
            request.setAttribute("sites", sites);

        }
        request.setAttribute("vue", vue);
        request.getServletContext().getRequestDispatcher("/WEB-INF/administrateur/main1.jsp").forward(request, response);
    }

}
