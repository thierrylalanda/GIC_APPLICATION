
    var FonctionsCRUD ={
        getAll:function(controller){
             result=new Object();
            
            
            result=$.get(controller+"?action=getAllPersonnels",function(response){
                result=response.personnel;
                
                //alert(response.personnel[2].nom_prenom);
                console.log(result);
                },
            'json');
            
                       
        },
        getOne:function(){},
        insert:function(){},
        delete:function(){},
        update:function(){}
    };
    
    function Personnel(controller){
        this.controller=controller;
        this.result=new Object();
        this.setresult=function(result){
            
               this.result=result;
          };
         
        this.getAll=function(){
            
         $.get(controller+"?action=getAllPersonnels",function(response){
               sessionStorage.setItem("personnel",response.personnel);
               console.log(response.personnel);
               },
             'json');
             
          };
          
          this.getresult=function(){
              this.result=sessionStorage.getItem("personnel");
              return sessionStorage.getItem("personnel");
          };
          
          this.delete=function(param){
              $.post(controller,param,function(data,status){
                  if (status==="success")
                      sessionStorage.setItem("status",status);
                  },"text");
              
          };
          
          
        
    };
    
  

    

