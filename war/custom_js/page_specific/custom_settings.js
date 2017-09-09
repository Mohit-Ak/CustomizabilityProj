var customSettings = (function() {
	  function handleSettings() {
		  console.log("handleSettings called");
	    	$( "button#customSettingsSubmit" ).click(function() {
	    		
	    		bootbox.confirm({
	    		    message: "Are you sure you are done with the website settings and ready to proceed to browsing the website content?",
	    		    buttons: {
	    		        confirm: {
	    		            label: 'YES',
	    		            className: 'btn-success'
	    		        },
	    		        cancel: {
	    		            label: 'NO',
	    		            className: 'btn-danger'
	    		        }
	    		    },
	    		    callback: function (result) {
	    		        console.log('This was logged in the callback: ' + result);
	    				if(result){
	    					  App.blockUI({ boxed: true });
		    		   		  var el=$("#range_el").data("ionRangeSlider").result.from;
		    	    		  var ec=$("#range_ec").data("ionRangeSlider").result.from;
		    	    		  var hl=$("#range_hl").data("ionRangeSlider").result.from;
		    	    		  var hc=$("#range_hc").data("ionRangeSlider").result.from;
		    	    		  var cl=$("#range_cl").data("ionRangeSlider").result.from;
		    	    		  var cc=$("#range_cc").data("ionRangeSlider").result.from;
		    	    		  
		    	    		  if(el==0 && ec==0 && hl==0 && hc==0 && cl==0 && cc==0){
		    	    			  App.unblockUI();
		    	    			  $("#all_none_error").show()
		    	    			  $("html, body").animate({ scrollTop: 0 }, "slow");
		    	    			  return;
		    	    		  }
		    	    		  
		    	    			  data={el:el,ec:ec,hl:hl,hc:hc,cl:cl,cc:cc};
		                          $.ajax({
		                        	  method: "POST",
		                  			  url: "SaveSettings", 
		                  			  data: data,
		                  			  success: function(result){
		                              //debugger;
		                  			  console.log(result);
		                  			  if(result.isSaved==true){
		                  				  window.location.href="/Home";
		                  			  }
		                          },
		                          error: function () {
		                              console.log("error saving data in - handleSettings");
		                            }
		                          });  
		    			}
	    		    }
	    		});
	    	});
	  }
	  return {
	    init: function() {
	    	console.log("init called");
	    	handleSettings();	    }
	  };   
	})();

jQuery(document).ready(function() {
	customSettings.init();
});
