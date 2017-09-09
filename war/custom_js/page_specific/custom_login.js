var customLogin = (function() {
	  function handleLogin() {
		  console.log("handleLogin called");
	    	$( "button#customLoginSubmit" ).click(function() {
	    		  App.blockUI({ boxed: true });
	    		  var userId=$("#customUserId").val();
	    		  var password=$("#customPassword").val();
	    		  if(userId!="" && password!=""){
	    			  data={userId:userId,password:password};
                      
                      $.ajax({
                    	  method: "POST",
              			  url: "UserAuthenticate", 
              			  data: data,
              			  success: function(result){
                          console.log(result);
                          if(result.isAuthenticated){
                        	  if(result.userType=="type1-tr"){
                        		  window.location.href="/Home";
                        	  }else if(result.userType=="type2-usr"){
                        		  window.location.href="/UserSettings";
                        	  }
                        	  else if(result.userType=="type3-resrch"){
                        		  window.location.href="/Home";
                        	  }else if(result.userType=="type4-usrovr"){
                        		  window.location.href="/UserSettings";
                        	  }
                        	  
                          }else{
                        	  App.unblockUI();
                        	  bootbox.alert("The credentials are invalid or you have completed the experiment.");
                          }
                      
                      },
                      error: function () {
                          console.log("error authenticating");
                        }
                      });  
	    		  }
	    	});
	  }
	  function checkTimeout() {
		  try{
			  console.log("customLogin - checkTimeout Times up");
			  var name="isTimeOut";
			  if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search)){
				  if(decodeURIComponent(name[1])=="true"){
					  bootbox.alert("Time's Up! You have been successfully logged out. Thank you for participating.");
				  }
			  }
				    
		  }catch(e){
			  console.log("customLogin - checkTimeout User Logged out");
		  }
	  }
	  return {
	    init: function() {
	    		console.log("customLogin - init called");
	    		handleLogin();
	    		checkTimeout();
        	
        	}
	  };   
	})();

jQuery(document).ready(function() {
	customLogin.init();
});
