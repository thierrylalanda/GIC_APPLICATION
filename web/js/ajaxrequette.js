function valider($id_valideur, visa, serveur) {
    $.post(
            serveur, // serveur 
            {
                id_valideur: $id_valideur,
                visa: visa
            },
            function (data) {
            },
            'text'
            );


}

$(document).ready(function () {

    var id = -1;
    var produit;
    var bupdate = 1;
    var tdqte;
    var focus;
    var input;
    var newvalcm;
    var click = 1;
    $("#autre").click(function () {

        $("#autres").focusin(function () {

            $(this).attr({autofocus: true});

        }).removeAttr("disabled");
    });
    $("#autres").focusout(function () {

        $(this).attr({disabled: true});

    });

    $("#refuser_validation").dialog({
        autoOpen: false,
        closeOnEscape: true,
        draggable: true,
        height: 700,
        width: 900,
        modal: true

    });
    $("#dialog1").dialog({
        autoOpen: false,
        closeOnEscape: false,
        draggable: true,
        height: 500,
        width: 1000,
        scroll: false,
        modal: true,
        buttons: [
            {
                text: "Valider",
                click: function () {
                    if (confirm("voulez vous vraiment valider cette commande?")) {
                        var valideur = parseInt($("#id_valideur").html());
                        alert(valideur);
                        //valider(valideur,"ok","valideur");

                    } else {
                        $(this).dialog("close");
                    }

                }
            },
            {
                text: "rejeter",
                click: function () {
                    $(this).dialog("close");
                    $("#refuser_validation").dialog("open");
                }
            }
        ]
    });
    $('#valider').click(function () {
        //alert("");
        $("#dialog1").dialog("open");

    });
    //lorsque l'on refuse une commande 
    $('.refu').click(function () {
        var ul = $(this).parent('.ul');
        ul.addClass('hidden');
        ul.parent('.td').children('.choix').removeClass('hidden');
        ul.parent('.td').children('.choixMS').removeClass('hidden');
    });

    function commentaire($this, produit, serveur) {
        $.post(
                serveur, // serveur 
                {
                    code: produit.children(".code").html(),
                    raison: $this.val(),
                    action: "refuser"
                },
                function (data) {

                    if (!localStorage.getItem("reload")) {
                        localStorage.setItem("reload", "true");
                        location.reload();
                    } else {
                        localStorage.removeItem("reload");

                    }
                    // document.location='CommandeRecusMP?idMS='+sessionStorage.getPrototypeOf("id_magasinP")+'&action=lister&vue=commandeRecus';
                },
                'text'
                );

    }
    $("#select").click(function () {
        // alert("ok");
        var idselect = $(this).val();
        var param = {action: "chargerProduitMS", magasin: idselect};
        //document.location='Commande_All_Client?vue=Commande Personnel&action=chargerProduitMS&magasin='+idselect;
        $.ajax({
            type: "POST",
            url: "Commande_All_Client",
            dataType: "json",
            data: {action: "chargerProduitMS", magasin: idselect},
            success: function (data) {

                $('#tabb').html("");
                $.each(data, function (index) {

                    var da = $("<tr>").append("<td>" + data[index].categorie + "</td>" + "<td>" + data[index].code + "</td>" + "<td>" + data[index].designation + "</td>" + "<td>" + data[index].quantite + "</td>" + "<td>" + data[index].date + "</td>");
                    $(da).appendTo('#tabb');
                });

            }
        });

    });


    //  raison du rejet pour le magazin principal
    $(".choix").click(function () {
        //  alert("ok");
        commentaire($(this), produit, "CommandeRecusMP");
    });
    // raison du rejet  pour le magazin secondaire
    $(".choixMS").click(function () {
        //alert("ok");
        commentaire($(this), produit, "transfert");
    });


    $('.produits').hover(function () {
        $(this).addClass('success');
    }, function () {
        $(this).removeClass('success');
    });

    $('.produits').click(function () {
        if (click !== 1) {
            focus = produit.children('.qteuc');

            if (!newvalcm.hasClass('hidden') && !$(this).hasClass("success")) {

                focus.addClass("hidden");
                produit.children(".qteu").removeClass("hidden");


            }
            produit.removeClass("success");
            produit.css({"background-color": ""});

            input = produit.children(".qteuc");


        }
        click = click + 1;

        id = $(this).children('.code').html();
        produit = $(this);
        produit.css({"background-color": "lime"});
        $(this).addClass('success');



    });
    //update  qte  lors du double click 
    $(".qteu").dblclick(function () {
        var $p = produit;
        input = produit.children(".qteuc");
        produit.children(".qteu").addClass("hidden");
        produit.children(".qteuc").removeClass("hidden");
        newvalcm = input.children('.newvalcm');

    });

    $(".newval").focusout(function () {
        focus = $(this).parent('.qteuc');
        $(this).parent('.qteuc').addClass("hidden");
        produit.children(".qteu").removeClass("hidden");

    });
    $(".newval").keypress(function (event) {
        if (event.keyCode === 13) {

            var code = produit.children(".code").html();
            $.post(
                    'MisaJourProduits', // serveur MS
                    {
                        code: code,
                        qte: $(this).val(),
                        action: "update"
                    },
                    function (data) {

                        produit.children(".qteuc").addClass("hidden");
                        produit.children(".qteu").removeClass("hidden").html(data);
                        location.reload();
                    },
                    'text'
                    );

        }

    });
    $(".formRefus").hide();
    $("#refus").click(function () {

        $(".formRefus").show();
    });

    $("#accept").click(function () {

        $(".formRefus").hide();
    });

    $(".newvalccP").keypress(function (event) {
        if (event.keyCode === 13) {

            var code = produit.children(".code").html();
            $.post(
                    'Commande_All_Client', // serveur MS
                    {
                        code: code,
                        qte: $(this).val(),
                        action: "update"
                    },
                    function (data) {

                        produit.children(".qteuc").addClass("hidden");
                        produit.children(".qteu").removeClass("hidden").html(data);


                    },
                    'text'
                    );

        }

    });
    //update produit MP
    $(".newvalcm").focusout(function () {
        focus = $(this).parent('.qteuc');
        $(this).parent('.qteuc').addClass("hidden");
        produit.children(".qteu").removeClass("hidden");

    });
    $(".newvalcm").keypress(function (event) {
        if (event.keyCode === 13) {

            var code = produit.children(".code").html();
            //alert(code);
            $.post(
                    'MisaJourProduitMP', // serveur MP
                    {
                        code: code,
                        qte: $(this).val(),
                        action: "update"
                    },
                    function (data) {

                        produit.children(".qteuc").addClass("hidden");
                        produit.children(".qteu").removeClass("hidden").html(data);

                        location.reload();
                    },
                    'text'
                    );

        }

    });

    //update commande MP
    $(".newvalCMP").focusout(function () {
        focus = $(this).parent('.qteuc');
        $(this).parent('.qteuc').addClass("hidden");
        produit.children(".qteu").removeClass("hidden");

    });
    $(".newvalCMP").keypress(function (event) {
        if (event.keyCode === 13) {

            var code = produit.children(".code").html();
            //alert(code);
            $.post(
                    'passerCommande', // serveur MP
                    {
                        code: code,
                        qte: $(this).val(),
                        action: "update"
                    },
                    function (data) {

                        produit.children(".qteuc").addClass("hidden");
                        produit.children(".qteu").removeClass("hidden").html(data);


                    },
                    'text'
                    );

        }

    });

    $(".newvalcc").keypress(function (event) {
        if (event.keyCode === 13) {

            var code = produit.children(".code").html();

            $.post(
                    'commande_client', // serveur 
                    {
                        code: code,
                        qte: $(this).val(),
                        action: "update"
                    },
                    function (data) {

                        produit.children(".qteuc").addClass("hidden");
                        produit.children(".qteu").removeClass("hidden").html(data);


                    },
                    'text'
                    );

        }

    });

    $(".newvalcms").keypress(function (event) {
        if (event.keyCode === 13) {

            var code = produit.children(".code").html();

            $.post(
                    'commandeMS', // serveur 
                    {
                        code: code,
                        qte: $(this).val(),
                        action: "update"
                    },
                    function (data) {

                        produit.children(".qteuc").addClass("hidden");
                        produit.children(".qteu").removeClass("hidden").html(data);


                    },
                    'text'
                    );

        }

    });

    $(".newvaltransf").focusout(function () {
        focus = $(this).parent('.qteuc');
        $(this).parent('.qteuc').addClass("hidden");
        produit.children(".qteu").removeClass("hidden");

    });
    $(".newvaltransf").keypress(function (event) {
        if (event.keyCode === 13) {

            var code = produit.children(".code").html();
            //alert(code);
            $.post(
                    'CommandeRecusMP', // serveur MP
                    {
                        code: code,
                        qte: $(this).val(),
                        action: "setQuantiter",
                        vue: "Traitement Commande"
                    },
                    function (data) {

                        produit.children(".qteuc").addClass("hidden");
                        produit.children(".qteu").removeClass("hidden").html(data);


                    },
                    'text'
                    );

        }

    });


    $("#details").click(function () {
        if ($(".Viewdetail").hasClass("hidden")) {
            $(".Viewdetail").removeClass("hidden");
        } else
            $(".Viewdetail").addClass("hidden");


    });

});


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


