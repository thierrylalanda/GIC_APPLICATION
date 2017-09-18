function timer() {
    setTimeout(function () {
        $('.theDiv').fadeToggle(5000);
        timer();
    }, 2000);

}

$(document).ready(function () {

    var id = -1;
    var produit;
    var bupdate = 1;
    var tdqte;
    //pour creer un calendrier francais  
    $.datepicker.regional['fr'] = {
        closeText: 'Fermer',
        prevText: 'Précédent',
        nextText: 'Suivant',
        currentText: 'Aujourd\'hui',
        monthNames:
                ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct', 'Nov', 'Dec'],
        dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
        dayNamesShort: ['Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.'],
        dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
        weekHeader: 'Sem.',
        dateFormat: 'dd/mm/yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''};

    $.datepicker.setDefaults($.datepicker.regional['fr']);
    //$(".datepick1").datepicker();
    $(".datepick1").datepicker({
        firstDay: 1,
        showButtonPanel: true,
        currentText: "Today",
        closeText: "Close",
        constrainInput: false,
        defaultDate: 2,
        minDate: 0,
        maxDate: "+3M",
        changeMonth: true,
        changeYear: true,
        numberOfMonths: 1,
        beforeShowDay: onBeforeShowDay
    });



    function onBeforeShowDay(theDate) {
        // Day 0 is Sunday, 6 is Saturday
        if (theDate.getDay() === 0 || theDate.getDay() === 6)
            return [true, "", "Weekends disabled"];

        return [true, ""];
    }
    //lors du click sur un produit
    $('.produits').click(function () {
        //alert('');
        id = $(this).children('.code').html();
        produit = $(this);
        $(this).addClass('success');
        $('.bouton').removeClass('hide');
        // document.location='listeproduit?code_produit='+id;
        $('.bouton').removeClass('disabled');
        $('#btnupdate').removeClass('disabled');

    });

    // pour valider une commande 
    $('#valid').click(function () {
        id = $(this).parent('.produit').children('.code').html();

        var valider = prompt('voulez vous valider cette commande oui ou non?');
        if (valider === 'oui') {
            document.location = 'listeproduit?vue=bonE&visa=ok';

        } else
            alert(id);
        $('.modal').modal('show');


    });

    //ajout commande client
    $('#ajoutclient').click(function () {
        $('.bouton').removeClass('hide');
        var cat = $('.categorie').val();
        var code = $("#code").val();
        var qte = $("#qte").val();
        var pu = $("#pu").val();
        var pt = qte * pu;
        var nomc = $("#nomc").val();
        //alert(nomc);
        // $("#qte").val("");
        $("#pu").val("");
        // $("#tbody").appendChild();
        //$("#tbody").append('<tr class="produits"><td>'+cat+'</td><td class="code">'+code+'</td><td>'+qte+'</td><td>'+pu+'</td><td>'+pt+'</td></tr>');
        $(".cappareil").show();
        $(".appareil").addClass("hide");
        var td = ["categorie:" + cat + ",code:" + code + ",qte:" + qte];
        //document.location='listeproduit?vue=cclient&categorie='+cat+'&code='+code+'&qte='+qte;
        //var tr={"categorie":cat,"code":code,"qte":qte};
        // commande.push(td);
    });

    //lors du click sur la commande des produits
    /*
     $('.produits').click(function(){
     id=$(this).children('.code').html();
     tdqte= produit.children(".qteu");
     produit=$(this);
     $(this).addClass('success');
     $('.bouton').removeClass('disabled');
     $('#btnupdate').removeClass('disabled');
     document.location='listeproduit?code_produit='+id;
     
     });
     */

    //gestionnaire de recherche

    $(".toutp").click(function () {
        $(".tproduit").removeClass("hide");
        $(".tcategorie").addClass("hide");

    });

    $(".toutc").click(function () {
        $(".tcategorie").removeClass("hide");
        $(".tproduit").addClass("hide");
    });

    $(".tout").click(function () {
        $(".tcategorie").addClass("hide");
        $(".tproduit").addClass("hide");
    });




    $(".appareil").hide();
    $("#appareil").click(function () {
        $(".appareil").show();

    });


    //lors du click pour enregistrer une commande
    $('.save').click(function () {
        //commande.toString()
        //var a =commande.serialize();
        $('.ok').removeClass("hide");
        document.location = 'commande_client?vue=Sortir pour consommation&action=saveClient';
    });

    $('.saveClient').click(function () {
        //commande.toString()
        //var a =commande.serialize();
        // $('.ok').removeClass("hide");
        document.location = 'commande_client?vue=comperso&save=saveClient';
    });

    $('.delete').click(function () {
        // var  idd=$(this).children('.code').html();
        produit.hide();
        document.location = 'commandeSimple?vue=Commande Interne&code=' + id + '&action=delete';

    });

    $('#deleteOneCMS').click(function () {
        // var  idd=$(this).children('.code').html();
        produit.hide();
        document.location = 'commande_client?vue=Sortir pour consommation&code=' + id + '&action=delete';

    });

    $('#deleteP').click(function () {
        // var  idd=$(this).children('.code').html();
        produit.hide();
        document.location = 'Commande_All_Client?vue=CommandeClient&code=' + id + '&action=delete';

    });

    $('#deleteAdmin').click(function (e) {
        e.preventDefault();
        // var  idd=$(this).children('.code').html();
        produit.hide();
        document.location = 'Commande_All_Client?vue=Commande Interne&code=' + id + '&action=delete&niveau=5';

    });

    $('#deleteMS').click(function (e) {
        e.preventDefault();
        produit.hide();
        var idMag = $(this).attr("name");
        var niv = $(this).attr("value");
        var vue;
        if (niv === "5") {
            vue = "editeMagasinS";
        } else {
            vue = "Commande produits";
        }
        //  alert(niv)
        ;
        document.location = 'commandeMS?vue=' + vue + '&code=' + id + '&action=delete&id_magasin=' + idMag + '&niveau=' + niv;
    });

    $('#deleteMPB').click(function () {
        //alert(produit);
        produit.hide();
        document.location = 'bordereau?vue=Bordereau&code=' + id + '&action=delete';

    });

    $('#deleteMP').click(function () {
        //alert(produit);
        produit.hide();
        document.location = 'passerCommande?vue=Commande&code=' + id + '&action=delete';

    });

    $('#deleteCommamdeNonOk').click(function () {
        //alert(produit);
        produit.hide();
        document.location = 'passerCommande?vue=Commande&code=' + id + '&action=deleteCommamdeNonOk';

    });

    $('.cupdate').hide();
    //lors du click pour update une commande 
    $('.cupdate').click(function () {
        var qte = $('#qte').val();
        var code = $('#code').val();
        document.location = 'listeproduit?vue=Commande produits&qte=' + qte + '&code=' + code;

    });

    //lors du click sur le bouton edit
    $('.update').click(function () {
        //alert(produit.children('.categories').html());
        $('#categorie').val(produit.children('.categories').html()).attr({disabled: true, active: false});
        $('#qte').val(produit.children('.qte').html());
        $('#code').val(produit.children('.code').html()).attr({disabled: true});
        $('#designation').val(produit.children('.designation').html()).attr({disabled: true});
        if (bupdate === 1) {
            $('#ajoutclient').hide();
            $('.cupdate').show();
        } else {
            $('#ajoutclient').show();
        }

        //$('.cupdate').toggle();
        //document.location='listeproduit?vue=cclient&code='+id+'&action=update&qte='+produit.children('.qte').html()+'&categorie='+produit.children('.categories').html();    

    });

    //lors de l'appui sur le bouton add
    $('.addp').click(function () {
        $('#ajoutclient').show();
        $('.cupdate').hide();
        $('#code').val("").attr({disabled: false});
        $('#designation').val("").attr({disabled: false});

    });

    //lors du click sur le checkbox autres
    $('#autre').click(function () {
        $(".appareil").hide();

    });

    $('a:first-child').tooltip({placement: 'left'});
    $('a:last-child').tooltip({placement: 'bottom'});

    //valider une comande
    $('.valide').hide();
    $('.valider').click(function () {

        //$('.attente').toggle();  
        //$('.valide').toggle();  

    });

    $('.rejet').hide();
    $('.rejeter').click(function () {

        $('.attente').toggle();
        $('.rejet').toggle();

    });


    $("#dialog3").dialog({
        autoOpen: false,
        closeOnEscape: false,
        draggable: false,
        modal: true,
        height: 500,
        width: 500,
        buttons: [
            {
                text: "Close",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });


    $(".commander").click(function () {
        $("#dialog3").dialog("open");
    });

    //lors du click pour la mise a jour 
    $("#btnupdate").click(function () {
        var $p = produit;


        $.each($p.parent("#tbody"), function () {
            //$(this).append($p.parent("#tbody").children(".produits").children(".qteu").html()) ; 
            if ($(this).children(".produits").children(".code").html() === produit.children(".code").html()) {
                produit.children(".qteu").removeClass("hide");

            }

        });
        // produit.children(".qteuc").addClass("hide");
        //alert(tdqte.html());
        //tdqte.parent(".produits").children(".qteuc").addclass("hide");
        //tdqte.removeClass('hide');

        //tdqte.html(tdqte.html());
        //produit.children(".qteu").html('<input type="number" id="updateqte" class="input-mini updcateqte"/>');
        //produit.children(".qteuc").removeClass("hide");
        produit.children(".qteu").addClass("hide");
        produit.children(".qteuc").removeClass("hide");

        // <td class="hide qteuc"><input type="number" id="updateqte" class="input-mini updcateqte"/></td>

    });

    //pour obtenir automatiquement le code
    $("#code").click(function () {

        var designation = $("#designation").val();

        $.post(
                'generalconfiguration', // Un script java que l'on va créer juste après
                {
                    designation: designation,
                    categorie: $("#categorie").val(),
                    action: "code"

                },
                function (data) {
                    if (data === "error") {
                        $.gritter.add({
                            title: 'designation incorrecte : cet article n\'existe pas  pour cette categorie',
                            text: " ",
                            image: 'image/supprimer.png',
                            sticky: false
                        });
                        $("#code").val("");
                    } else {
                        $("#code").val(data);
                    }



                },
                'text'
                );

    });
    //pour obtenir automatiquement le code MP

    //pour mettre a jour une qte
    //fonction permettant montrer le input pour update  la quantite
    function hideshow(tohidebtn, toshowbtn, tohidetd, toshowtdinput) {

        $(tohidebtn).addClass("hidden");
        var tr = $(tohidebtn).parents("tr:first");
        var td = $(tohidebtn).parents("td:first");
        td.find(toshowbtn).removeClass("hidden");

        tr.find(tohidetd).addClass("hidden");
        tr.find(toshowtdinput).removeClass("hidden");
        tr.find(toshowtdinput).children("input").focus();
    }
    function saveupdate(serveur, param, tohidebtn, toshowbtn, tohidetd, toshowtd) {
        var tr = $(tohidebtn).parents("tr:first");
        var td = $(tohidebtn).parents("td:first");
        $(tohidebtn).addClass("hidden");

        if (param.quantite !== "") {
            if (parseInt(param.quantite) <= parseInt(tr.find(toshowtd).html())) {
                $.post(
                        serveur, // Un script java que l'on va créer juste après
                        param,
                        function (data) {
                            td.find(toshowbtn).removeClass("hidden");
                            tr.find(tohidetd).addClass("hidden");
                            tr.find(toshowtd).removeClass("hidden").html(data);

                        },
                        'text'
                        );
            } else {
                alert("impossible de receptionner plus que la commande");
                td.find(toshowbtn).removeClass("hidden");
                tr.find(tohidetd).addClass("hidden");
                tr.find(toshowtd).removeClass("hidden");
            }
        } else {
            td.find(toshowbtn).removeClass("hidden");
            tr.find(tohidetd).addClass("hidden");
            tr.find(toshowtd).removeClass("hidden");
        }



    }

    $(".qtec").dblclick(function () {
        var $p = produit;

        $(this).addClass("hidden");
        $(".qteu").removeClass("hidden");
        var code = produit.children(".code").html();

    });

//pour mettre a jour une qte du fournisseur dans reception du fournisseur

    $(".qteuF").click(function () {

        hideshow(this, ".saveF", ".qteu", ".qteucF");
    });


    $(".saveF").click(function () {

        var tr = $(this).parents("tr:first");
        var quantite = tr.find(".newvalCMPF").val();
        var idcommande = tr.attr("name");

        var param = {action: "receptionPartielCommandeFournisseur", commande: idcommande, quantite: quantite};

        saveupdate('bordereau', param, this, ".qteuF", ".qteucF", ".qteu");
    });

//rejeter la commande d'MP chez le controlleur deleteCMP
    $(".deleteCMP").click(function (e) {
        e.preventDefault();


        var nbr = 0;
        var allcommande = new Array();
        $("tbody .checkboxes:checked").each(function (index) {
            allcommande.push($(this).val());
            nbr++;
        });

        if (nbr !== 0) {

            var form = $(this).parents("form:first");
            form.attr("action", form.attr("action") + "&action=refusValidation&idcommande=" + allcommande);

            $(".form-hide").removeClass("hidden");
        } else {
            alert("cocher au moins un element");
            $(".form-hide").addClass("hidden");
        }
    });

    $(".saveall").click(function (e) {
        e.preventDefault();


        var nbr = 0;
        var allcommande = new Array();
        $("tbody .checkboxes:checked").each(function (index) {
            allcommande.push($(this).val());
            nbr++;
        });

        if (nbr !== 0) {
            var form = $(this).parents("form:first");
            form.attr("action", form.attr("action") + "&action=validerAll&idcommande=" + allcommande);
            form.submit();
        } else {
            alert("cocher au moins un element");
            $(".form-hide").addClass("hidden");
        }

    });

//pour les checkbox du tableau

    jQuery('#sample_3 .group-checkable').change(function () {
        var set = jQuery(this).attr("data-set");
        var checked = jQuery(this).is(":checked");
        jQuery(set).each(function () {
            if (checked) {
                $(this).attr("checked", true);
            } else {
                $(this).attr("checked", false);
            }
        });
        jQuery.uniform.update(set);
    });

    jQuery('#sample_3_wrapper .dataTables_filter input').addClass("input-small"); // modify table search input
    jQuery('#sample_3_wrapper .dataTables_length select').addClass("input-mini");
    timer();


});

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

