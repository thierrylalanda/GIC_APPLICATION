$(document).ready(function () {
    
    $.ajax({
        type: "POST",
        url: "Json",
        dataType: "json",
        success: function (data) {
            $.each(data , function(index){
                var div_data = "<option value="  + data[index].id + ">" + data[index].name +"</option>";
            $(div_data).appendTo('#ch');
           var da= $("<tr>").append("<td>"+data[index].id+"</td>"+"<td>"+data[index].name+"</td>");
            $(da).appendTo('#tab');
            } );
         $("#ii").addClass(".simpletable") ;
        }
    });
    
       
});

