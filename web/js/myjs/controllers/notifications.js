/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.  
 */
// charger tous les differents nouvelles notifications
function getNewNotifications(IdUser) {

    var $ul = $(".notification");
    setTimeout(function () {
        // on lance une requête AJAX
        $.post(
                'notifications',
                {"recepteur": IdUser, "action": "getAllNotificationsEntete", "vue": "vue"},
                function (data) {
                    $ul.html("");
                    $ul.attr({"id": IdUser});
                    var text = '<li>' +
                            '<p>vous avez ' + data [0] + ' notification(s) </p>' +
                            '</li>';
                    $ul.append(text);
                    $(".nombreNotifs").html(data[0]);
                    // console.log(data);


                    for (var i = 1; i < data.length; i++) {
                        var message;
                        if (data[i][3].length > 100) {
                            message = data[i][3].substring(0, 99) + " <strong>lire la suite ...</strong>";
                        }


                        var $li = '<li>' +
                                '<a href="notifications?action=getOneUserNotif&vue=notification&expediteur=' + data[i][0] + '&recepteur=' + IdUser + '" >' +
                                '<span class="label label-important"><i class="icon-bolt"></i></span>' +
                                message +
                                '<span class="small italic">34 mins</span>' +
                                '</a>' +
                                '</li>';
                        $ul.append($li);
                    }
                    var $allMessageLink = '<li>' +
                            '<a class="text-center addClass" href="notifications?action=getAllNotifications&vue=notification&recepteur=' + IdUser + '">' +
                            '<strong>Voir tous les messages</strong>' +
                            '<i class="fa fa-angle-right"></i>' +
                            '</a>' +
                            '</li>';
                    $ul.append($allMessageLink);
                },
                'json'
                );
        getNewNotifications(IdUser); // on relance la fonction
    }, 10000); // on exécute le chargement toutes les 5 secondes


}

$(function () {
    sessionStorage.setItem("iduser", $(".notification").attr("id"));
    var IdUser = sessionStorage.getItem("iduser");
    // pour le back to top
    $('body').append('<div id="toTop" class="btn btn-info"><span class="fa fa-chevron-up"></span> Back to Top</div>');
    $(window).scroll(function () {
        if ($(this).scrollTop() !== 0) {
            $('#toTop').fadeIn();
        } else {
            $('#toTop').fadeOut();
        }
    });
    $('#toTop').click(function () {
        $("html, body").animate({scrollTop: 0}, 600);
        return false;
    });


    $(".valid-ok").click(function () {
        var idNotif = $(this).attr("idNotif");
        $(this).addClass("hidden");
        $.post(
                'notifications',
                {idnotif: idNotif, vue: "vue", action: "update"},
                function (data) {
                    if (data === "ok") {
                        $(this).addClass("hidden");
                    }

                },
                'text'
                );
    });
// function pour charger les message apres 5 seconde
    $(".response").hide();
    $(".reponse-notif").click(function (e) {
        e.preventDefault();
        $(this).hide();
        $(this).parent(".form-response").children(".response").slideDown("slow");
    });
    $(".response").focusout(function () {
        $(this).hide();
        $(this).parent(".form-response").children(".reponse-notif").slideDown("slow");
    });
    getNewNotifications(IdUser);
});