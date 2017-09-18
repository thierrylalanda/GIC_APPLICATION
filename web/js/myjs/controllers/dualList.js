/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {

    $(".selectgroupes").click(function (e) {
        e.preventDefault();
        var idGroupe = $(this).val();
        var idUser = $("#userid").attr("class");
        //alert(idGroupe);
        $.post(
                'droitsAccess',
                {"idUser": idUser, "action": "getPagesUser", "vue": "vue", "idGroupe": idGroupe},
                function (data) {

                    var allPagesselect = $("#box1View");
                    var allPagesUserselect = $("#box2View");
                    allPagesselect.html("");
                    allPagesUserselect.html("");
                    console.log(data);
                    //alert("ok");
                    // toutes les pages du groupe de l'utilisateur

                    for (var i = 0; i < data[0].length; i++) {


                        allPagesselect.append("<option  value=" + data[0][i][0] + " >" + data[0][i][1] + "</option>");

                    }

                    for (var i = 0; i < data[1].length; i++) {


                        allPagesUserselect.append("<option  value=" + data[1][i][0] + " >" + data[1][i][1] + "</option>");

                    }

                },
                'json'
                );
    });

    $("#getallpagesgroupe").click(function (e) {
        e.preventDefault();
        var idGrandGroupe = $(this).val();

        $.post(
                'droitsAccess',
                {"action": "getPagesGroupe", "vue": "vue", "idGroupe": idGrandGroupe},
                function (data) {

                    var allPagesselect = $("#box1View");
                    var allPagesUserselect = $("#box2View");
                    allPagesselect.html("");
                    allPagesUserselect.html("");
                    console.log(data);
                    //alert("ok");
                    // toutes les pages du groupe de l'utilisateur

                    for (var i = 0; i < data.length; i++) {


                        allPagesselect.append("<option  value=" + data[i][0] + " >" + data[i][1] + "</option>");

                    }

                },
                'json'
                );

    });


});