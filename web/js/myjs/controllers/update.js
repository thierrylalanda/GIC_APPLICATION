/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    var last = 0;
    var newname;
    $(".produits").dblclick(function () {
        if (last !== 0) {
            last.removeClass("hidden").text(last.text());
            newname.addClass("hidden");
        }
        last = $(this).find(".lastName");
        newname = $(this).find(".update");
        last.addClass("hidden");
        newname.removeClass("hidden");
        newname.find(".newval").focus();
    });


    $(".newval").keypress(function (event) {

        if (event.keyCode === 13) {
            //alert($(this).attr('link'));
            if ($(this).val() !== "") {
                $.ajax({
                    url: $(this).attr('link'),
                    data: {newval: $(this).val()},
                    type: "post",
                    dataType: 'text',
                    success: function (data) {

                        newname.addClass("hidden");
                        last.removeClass("hidden").text(data);
                        $(this).val("");
                    }
                });
            } else {
                newname.addClass("hidden");
                last.removeClass("hidden").text(last.text());
                $(this).val("");
            }


        }
    });
});

