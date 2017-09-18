
$(document).ready(function(){

	
	
	$('.click_trigger').click(function(e) {
		
		e.preventDefault();
		
		 var form='<form class="form bg_dy">'+
                               '<input type="text" class="input-large"/>' +
                               '<input type="text" class="input-large"/>' +
                               '<input type="text" class="input-large"/>' +
                         '<form>';
                 var Form=$('form.hide ');
		 Form.addClass("form bg_dy");
		if ($('#lightbox').length > 0) {
			
			$('#imgbox').html( form+ '<p><i class="icon-remove icon-white"></i></p>');
		   	
			$('#lightbox').slideDown(500);
		}
		
		else { 
			var lightbox = 
			'<div id="lightbox" style="display:none;">' +
				'<div id="imgbox">' + form +
					'<p><i class="icon-remove icon-white"></i></p>' +
				'</div>' +	
			'</div>';
				
			$('body').append(lightbox);
			$('#lightbox').slideDown(500);
		}
		
	});
	

	$('.icon-remove').live('click', function() { 
		$('#lightbox').hide(200);
	});
	
});

