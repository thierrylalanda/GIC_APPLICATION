/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.personnelsTable$('table.sortable tbody tr:odd').addClass('odd');
 *  $('table.sortable tbody tr:even').addClass('even');
 */
$(document).ready(function(){
     var personnelMat;
     var groupes;
     
     //
       $(".deletePersonnel") .click( function(e ) {
    
            e.preventDefault();
            personnelMat=$(this).attr("value");
              $("#deletePersonnel").modal("show");
               
                
        } ) ; 
        
        
        $("#updateDE") .click( function(e ) {
    
            e.preventDefault();
            //alert($(this).attr("id","saveDE").attr("id"));
            $("#formUpdate h3 .hidden").removeClass("hidden");
            $(this).addClass("hidden");
               $("#saveDE").removeClass("hidden");
                
        } ) ;
        
        $("#nexttape") .click( function(e ) {
            
            var error=false;
            $("#basic_validate .control-group .controls input").each(function(){
                if($(this).val()===''){
                    
                    error=true; 
                }
                  });
                
                
                if(error===false){
                        e.preventDefault();
                        var param=$("#basic_validate").serialize(); 
                         console.log(param);
                         $.get(
                                 "personnel?action=getGroupes&"+param,
                                 function(response){
                                     console.log(response);
                                     for (var i=0;i<response.length;i++){
                                         $("#selectgroupe").append("<option selected>"+response[i]+"</option>");
                                     }
                                 },
                                 "json");
                         $("#titleform").html("donnees de connexion");
                         $("#basic_validate").slideUp("slow");
                         $("#nexttapeform").removeClass("hidden").slideDown("slow");
                    }
          
           
            
        } ) ;
        
        $("#previous") .click( function(e ) {
    
            e.preventDefault();
           $("#titleform").html("coordonnees du personnel");
            $("#basic_validate").slideDown("slow");
            $("#nexttapeform").addClass("hidden").slideUp("slow");
            
        } ) ;
        
        $("#newgroupe").hide();
        $("#newgroupebtn") .click( function(e ) {
    
            e.preventDefault();
            $("#groupe").hide();
            $("#newgroupe").show();
            
            $(this).hide("slow");
            
            
        } ) ;
        
        
        $("#nexttapebtn") .click( function(e ) {
    
            e.preventDefault();
            $("#nexttapeform").hide("slow");
            $("#titleform").html("Attribution des droits ");
            $("#lasttapeform").removeClass("hidden").show("slow");
            var param=$("#nexttapeform").serialize(); 
            $.get(
                                 "personnel?action=getpages&"+param,
                                 function(response){
                                     console.log(response);
                                     for (var i=0;i<response.length;i++){
                                         //$("#selectpage").append("<option selected>"+response[i]+"</option>");
                                     }
                                 },
                                 "json");
            
            
        });
        
        $("#backnexttape") .click( function(e ) {
    
            e.preventDefault();
            $("#nexttapeform").show("slow");
            $("#titleform").html("donnees de connexion");
            $("#lasttapeform").addClass("hidden").show("slow");
            
        } ) ;
        
        //sauvegarde des mise a jours des donnees de l'entreprise du personnels
        $("#saveDE") .click( function(e ) {
            e.preventDefault();
            var param={};
            
            $("#formUpdate h3 input").each(function(index){
                if($(this).val()!==''){
                 var   key= $(this).attr("name").toString();
               param.put(key,$(this).val());
                //param.key=$(this).val();
                    
                }
            });
           alert(param.role);
            alert($(this).attr("name"));
        });
        
        
        //lors du click pour faire apparraitre les donnees
        $(".tr td a") .click( function(e ) {
           var id="#"+$(this).attr("href").toString();
            e.preventDefault();
            $(this).animate({"margin-left":"7px"},"slow");
                       $(".donnees").fadeOut("slow");
                $(id).slideDown("slow");
               
                
        }); 
        
        
        $(".tr td a").hover(
                function(){$(this).animate({"margin-left":"7px"},"slow");},
        function(){$(this).animate({"margin-left":"1px"},"slow");});
        
    //confirmation pour supprimer un personnel
       $("#getmessage") .click( function(e ) {
    
            e.preventDefault();
                $("#deletePersonnel").modal("hide");
                $("#formdeletePersonnel").modal("show");
                
        }); 
        
    //authentification pour supprimer un personnel
    $("#confirmDelete") .click( function(e ) {
    
            e.preventDefault();
            var param={action:"delete",idPersonnel:personnelMat,user:$("#userConfirm").val(),password:$("#passwordConfirm").val()};
            var oldpersonnel=new Personnel("personnel");
            oldpersonnel.delete(param);
            
            if(sessionStorage.getItem("status")==="success"){
                
                $("#deletePersonnel").modal("hide");
                $("#formdeletePersonnel").modal("hide");
                
                 $.gritter.add({
			title:	'confirmation',
			text:	'personnel supprimer avec success',
			sticky: false
		});	
            }
         });
    
    
    $("#addPersonnel") .click( function( ) {
    
            $("#addFersonnelForm").modal({
                keyboard: true,
                modal:true
                } ,"show") ;
        } ) ; 
     
    
         
       $('table.sortable tbody tr:odd').addClass('odd');
     $('table.sortable tbody tr:even').addClass('even');
     
       
  });

