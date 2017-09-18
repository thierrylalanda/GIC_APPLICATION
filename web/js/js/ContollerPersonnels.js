/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.personnelsTable$('table.sortable tbody tr:odd').addClass('odd');
 *  $('table.sortable tbody tr:even').addClass('even');
 */
function getMany(serveur, param, idResponse) {
    $.post(
            serveur,
            param,
            function (data) {
                $(idResponse).html("");
                sessionStorage.setItem("data", data);

                for (var i = 0; i < data.length; i++) {
                    console.log(data);
                    $(idResponse).append("<option value=" + data[i][0] + ">" + data[i][1] + "</option>");
                }

            },
            "json"
            );


}

function getMany2(serveur, param, idResponse) {
    $.post(
            serveur,
            param,
            function (data) {
                $(idResponse).html("");
                sessionStorage.setItem("data", data);
                
                for (var i = 0; i < data.length; i++) {
                    console.log(data);
                   
                    $(idResponse).append("<option value=" + data[i][0] + ">" + data[i][1] + "</option>");
                }
                $("#th").html($(idResponse));
               
                 $(idResponse).attr({multiple:"multiple"});
                 $(idResponse).addClass("multiples");
                $(".multiples").multiselect({
                    enableFiltering: true,
                    maxHeight: 400,
                    dropUp: false,
                    buttonText: function (options, select) {
                        if (options.length === 0) {
                            return 'aucune selection ...';
                        } else if (options.length > 3) {
                            return 'plus de 3  selectionner!';
                        } else {
                            var labels = [];
                            options.each(function () {
                                if ($(this).attr('label') !== undefined) {
                                    labels.push($(this).attr('label'));
                                } else {
                                    labels.push($(this).html());
                                }
                            });
                            return labels.join(', ') + '';
                        }
                    }

                });
                
               
                
                

            },
            "json"
            );



}
$(document).ready(function () {
    var personnelMat;
    var groupes;
    var disabled = false;
  
    $("#basic_validate .row .control-group .controls > input").focus(function () {

        $("#basic_validate .row .control-group .controls > input").each(function (index) {
            if ($(this).val() === '') {
                disabled = true;
                //alert($(this).val());

            }
        });

        if (disabled) {

            $("#nexttape").addClass("disabled");
        } else
            $("#nexttape").removeClass("disabled");

    });
    
    //affectation d'un personnel
    $(".affectation").click(function (e) {

        e.preventDefault();

        var idaffectation = $(this).val();
        var idregion = $("#region").val();
        //alert(idaffectation);
        $(".magasin").addClass("hidden");
        $(".typecontrol").addClass("hidden");
        $("#role_personnel").html("");
        $("#magasin").html("");
        if (idaffectation === "4") {
            $(".typecontrol").removeClass("hidden");
            var param = {action: "getRole", vue: "vue", idAffectation: idaffectation, region: idregion};
            getMany("admin", param, "#role_personnel");

        }
        if (idaffectation === "3") {
            $(".magasin").removeClass("hidden");
            var param = {action: "getRole", vue: "vue", idAffectation: idaffectation, region: idregion};
            getMany("admin", param, "#magasin");

        }
        if (idaffectation === "2") {
            $(".magasin").removeClass("hidden");
            var param = {action: "getRole", vue: "vue", idAffectation: idaffectation, region: idregion};
            getMany("admin", param, "#magasin");
        }


        var param = {action: "getGroupesByNiveau", vue: "vue", idAffectation: idaffectation, region: idregion};
        getMany("admin", param, "#selectgroupe");



    });


    $("#newgroupe").addClass("hidden");

    $(".groupe").focus(function (e) {

        e.preventDefault();
        var idgroupe = $(".groupe").val();
        var param = {action: "getPages", vue: "vue", idGroupe: idgroupe};
        getMany("admin", param, ".pagesGroupe");

         

    });
    $("#fichier").click(function (e) {
        $(".adbyfichier").removeClass("hidden");
    });


    $("#formulaire").click(function (e) {
        $(".adbyfichier").addClass("hidden");
    });

    //pour obtenir les groupes d'un niveau

    $(".niveau").click(function (e) {

        e.preventDefault();
        var niveau = $(this).val();

        $.post(
                "admin",
                {action: "getGroupesByNiveau", vue: "vue", niveau: niveau},
                function (data) {
                    $("#selectgroupe").html("");
                    console.log(data);
                    if (data !== "aucun") {
                        console.log(data[2]);
                        $("#selectgroupe").append('<option class="site" value=' + data[1] + '>' + data[2] + '</option>');

                    } else
                        $("#selectgroupe").html("");



                },
                "json"
                );

    });

    $("#site_service").click(function (e) {

        e.preventDefault();
        var site = $(this).val();

        var param = {action: "getServiceBySite", vue: "vue", site: site};
        getMany("admin", param, "#service");


    });

    //pour obtenir les sites d'une region

    $(".region").click(function (e) {
       
        e.preventDefault();
        var region = $(this).val();
         $("#magasigniers").html("");
        var param = {action: "getSites", vue: "vue", region: region};
        getMany("admin", param, "#site_service");


    });


    //pour obtenir les Directions

    $("#site").click(function (e) {

        e.preventDefault();
        var site = $(this).val();
        var param = {action: "getDirections", vue: "vue", site: site};
        getMany("admin", param, "#direction");


    });


    $("#region_service").click(function (e) {
        e.preventDefault();
        var region = $(this).val();
        var param = {action: "getSites", vue: "vue", region: region};
        getMany("admin", param, "#site_service");

    });
    $("#site_service").click(function (e) {
        e.preventDefault();
        var site = $(this).val();
        var param = {action: "getDirections", vue: "vue", site: site};
        getMany("admin", param, "#direction_service");

    });
    //pour obtenir les Service

    $("#direction").click(function (e) {

        e.preventDefault();
        var direction = $(this).val();
        var param = {action: "getServices", vue: "vue", direction: direction};
        getMany("admin", param, "#service");


    });

    $("#service").click(function (e) {

        e.preventDefault();
        var service = $(this).val();
        var param = {action: "getPersonnelsByService", vue: "vue", service: service};
        getMany("admin", param, "#personnels");


    });
    // pour obtenir les personnel a partir du site   
    $("#site_service").click(function (e) {

        e.preventDefault();
        var site = $(this).val();
        var param = {action: "getMagasigniersByService", vue: "vue", site: site};
        getMany("admin", param, "#magasigniers");


    });
    
    $(".region_mag").click(function (e) {

        e.preventDefault();
        var region = $(this).val();
        var param = {action: "getMagasinsByRegion", vue: "vue", region: region};
        var param2 = {action: "getMagasinsPByRegion", vue: "vue", region: region};
        getMany("admin", param, ".magasin");
        getMany("admin", param2, ".magasinP");


    });
    
    $(".region_magP").click(function (e) {
   
        e.preventDefault();
        var region = $(this).val();
        //alert(region);
        var param2 = {action: "getMagasinsPByRegion", vue: "vue", region: region};
        
        getMany("admin", param2, ".magasinP");


    });


    //obtenir les pages a partir d'un sous groupe 

                
    $(".sousgroupes").click(function (e) {
        
        e.preventDefault();
        var sousGroupe = $(this).val();
            var param = {action: "getPagesBySousGroupe", vue: "vue", sousGroupe: sousGroupe};
        getMany2("admin", param, ".pagesGroupe");
        
       
    });
    
    //obtenir les pages a partir d'un sous groupe pour ajouter un nouveau groupe
   $(".affectation").click(function (e) {

        e.preventDefault();
        var sousGroupe = $(this).val();
      //  alert(sousGroupe);
            var param = {action: "getPagesBySousGroupe", vue: "vue", sousGroupe: sousGroupe};
        getMany2("admin", param, ".pagesGroupe");
        
       
    });



    $("#region").click(function () {

        var region = $(this).val();

        var param = {action: "getSites", vue: "vue", region: region};
        getMany("admin", param, "#site_service");
    });


    //pour obtenir les Personnels
    $("#region").click(function (e) {

        e.preventDefault();
        var region = $(this).val();

        $.post(
                "admin",
                {action: "getPersonnels", vue: "vue", region: region},
                function (data) {$('#example-getting-started').multiselect({
                    enableFiltering: true,
                    maxHeight: 400,
                    dropUp: true,
                    buttonText: function (options, select) {
                        if (options.length === 0) {
                            return 'Aucun Controleur Selectionner ...';
                        } else if (options.length > 3) {
                            return 'plus de 3 cotroleur selectionner!';
                        } else {
                            var labels = [];
                            options.each(function () {
                                if ($(this).attr('label') !== undefined) {
                                    labels.push($(this).attr('label'));
                                } else {
                                    labels.push($(this).html());
                                }
                            });
                            return labels.join(', ') + '';
                        }
                    }

                });
                    $("#magasinier").html("");
                    console.log(data);

                    for (var i = 0; i < data.length; i++) {
                        console.log(data[i]);


                        console.log(data[i][1]);
                        $("#magasinier").append('<option class="site" value=' + data[i][0] + '>' + data[i][1] + '</option>');




                    }




                },
                "json"
                );

    });






    $("#updateDE").click(function (e) {

        e.preventDefault();

        $(" .hidden").removeClass("hidden");
        $(this).addClass("hidden");
        $(".show").addClass("hidden");
        $("#saveDE").removeClass("hidden");

    });





    //sauvegarde des mise a jours des donnees de l'entreprise du personnels
    $("#saveDE").click(function (e) {
        e.preventDefault();
        var param = {};

        $(" input").each(function (index) {
            if ($(this).val() !== '') {

            }
        });

    });

    //pour afficher tous les personnels dans groupe all-members
    $("#all-members").click(function (e) {
        $("#see-all-members").toggleClass("hidden");

    });

    $("#all-pages").click(function (e) {
        $("#see-all-members").addClass("hidden");
        $("#see-all-pages").toggleClass("hidden");

    });







    //authentification pour supprimer un personnel
    $("#confirmAuth").click(function (e) {

        e.preventDefault();
        var param = {action: "delete", idPersonnel: personnelMat, user: $("#userConfirm").val(), password: $("#passwordConfirm").val()};
        var oldpersonnel = new Personnel("personnel");
        oldpersonnel.delete(param);

        if (sessionStorage.getItem("status") === "success") {

            $("#deletePersonnel").modal("hide");
            $("#formdeletePersonnel").modal("hide");

            $.gritter.add({
                title: 'confirmation',
                text: 'personnel supprimer avec success',
                sticky: false
            });
        }
    });

    $("#ajoutpersobtn").click(function (e) {

        $("#mesappareils").toggleClass("hidden");
    });

    $('.datatable').dataTable({
        "paginate": true,
        "sort": true
    });

$('#Confirmpassword').focusin(function(){
    
    if($(this).val()===""){
        $('.confirm').removeClass("has-success").removeClass("has-error");
        
    }
    $(this).keyup(function(e){
        $('.confirm').removeClass("has-success").removeClass("has-error");
       if($(this).val()===$('#password').val()){
        $('.confirm').addClass("has-success");
       
        
    }
    if($(this).val()===""){
        $('.confirm').removeClass("has-success").removeClass("has-error");
        
    }
    if($(this).val()!==$('#password').val()){$('.confirm').addClass("has-error");}
    
    });
    
    
});



    $('table.tt tbody tr:odd').addClass('odd');
    $('table.tt tbody tr:even').addClass('even');


});

