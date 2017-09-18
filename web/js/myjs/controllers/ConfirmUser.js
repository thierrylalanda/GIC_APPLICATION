/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var link;
var iduser = sessionStorage.getItem("iduser");

$(".delete").click(function (e) {
    e.preventDefault();
    var form = $("#ConfirmForm");
    link = $(this).attr("name");
    var response = confirm("voulez-vous reellement supprimer?");

    if (response) {

        document.location = link;
    }

    //var linksubmit=link+"#myModal3";
    //$(this).attr("href", "#myModal3");

    //alert(link);

});


$(".alerts").hide();
$(".confirm").click(function (e) {
    e.preventDefault();

    var form = $("#ConfirmForm");

    //alert(link);


    $.ajax({
        url: "SettingPersonnal?action=confirmedelete",
        data: $(form).serialize(),
        type: $(form).attr("method"),
        dataType: 'text',
        success: function (data) {
            alert(iduser);
            if (data === iduser) {
                form.attr("action", link);
                //alert(link);
                form.submit();

            } else {
                //alert(data);
                $(".alert").show("slow");
            }

        }
    });

    $(".disabled-delete").click(function (e) {
        e.preventDefault();
        alert("link");

    });

});
