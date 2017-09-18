function convertDate(date) {

    return date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
}



$(document).ready(function () {
    var copy;
    var saveEvent = function (date, title, cop) {

        $.ajax({
            url: "agenda?action=add_event",
            data: {idPersonnel: sessionStorage.getItem("iduser"), date_event: date, type_event: title, detail_event: title},
            type: "post",
            dataType: 'json',
            success: function (data) {
                if (data[0] === "ok") {

                    cop = true;
                } else if (data[0] === "erreur") {

                    cop = false;
                    alert("choisir une date ulterieure");
                }

            }
        });
        return cop;
    };
    var getSpecialDay = function () {
        var response = new Array();
        $.post(
                "agenda",
                {action: "get_special_event", idPersonnel: sessionStorage.getItem("iduser")},
                function (data) {
                    //alert(data.length);
                    console.log(data);
                    if (data.length === 0) {
                        Calendrier([{}]);
                    } else {

                        for (var i = 0; i < data.length; i++) {
                            var agenda = {
                                title: data[i][3],
                                start: new Date(parseInt(data[i][0]), parseInt(data[i][1]) - 1, parseInt(data[i][2])),
                                end: new Date(parseInt(data[i][0]), parseInt(data[i][1]) - 1, parseInt(data[i][2])),
                                url: '#'
                            };
                            response.push(agenda);
                        }
                        //alert("ok");
                        Calendrier(response);
                        console.log(response);
                    }
                },
                "json"
                );
        return response;
    };
    getSpecialDay();
    /* initialize the external events
     -----------------------------------------------------------------*/

    $('#external-events div.external-event').each(function () {

// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
// it doesn't need to have a start or end
        var eventObject = {
            title: $.trim($(this).text()) // use the element's text as the event title
        };
        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);
        // make the event draggable using jQuery UI
        $(this).draggable({
            zIndex: 999,
            revert: true, // will cause the event to go back to its
            revertDuration: 0  //  original position after the drag
        });
    });
    /* initialize the calendar
     -----------------------------------------------------------------*/

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    function Calendrier(Specialday) {

        $('#agenda').fullCalendar({
            header: {
                left: 'prev,next, today',
                center: 'title',
                right: 'month,basicWeek,basicDay'
            },
            editable: false,
            droppable: true, // this allows things to be dropped onto the calendar !!!
            drop: function (date, allDay) { // this function is called when something is dropped

                // retrieve the dropped element's stored Event Object
                var originalEventObject = $(this).data('eventObject');
                // we need to copy it, so that multiple events don't have a reference to the same object
                var copiedEventObject = $.extend({}, originalEventObject);
                //enregistrer dans la BD
                var bol = false;
                copy = saveEvent(convertDate(date), copiedEventObject.title, bol);
                //alert(copy);
                // assign it the date that was reported
                //alert(copy);
                var dd = new Date().getTime();

                if (dd < date.getTime()) {
                    copiedEventObject.start = date;
                    copiedEventObject.allDay = allDay;
                    $('#agenda').fullCalendar('renderEvent', copiedEventObject, true);
                } else {
                    //alert("erreur");
                }
                // render the event on the calendar
                // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)

                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }

            }
            ,
            events: Specialday
        });
    }

});