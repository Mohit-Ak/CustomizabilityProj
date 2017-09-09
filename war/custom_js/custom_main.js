var customMain = (function() {
	  function recordTime() {
		  console.log("recordTime called on page load");
		  var pageStartTime = Date.now();
		  var navigateCallMadeFalg = true;
		  
		  var pageName=$(".page-content-container")[0];
		  if(pageName!=undefined && (pageName.id=="custom_home_page" || pageName.id=="custom_article_page")){
			  var timeNow = Date.now();
			  var logOutFlag=false;
			  setInterval(function(){
				  var pageEndTime = Date.now();
				  //console.log("The current total time is - ");
				  //console.log(pageEndTime-pageStartTime+experimentTime);
				  if(pageEndTime-pageStartTime+experimentTime>=300000){
					  console.log("5 minutes up");
					  if(!logOutFlag){
						  window.location.href="/UserLogout?isTimeOut=true";
						  logOutFlag=true;
					  }
					  
				  }
			  }, 1000);  
		  }
		  
		  window.onbeforeunload = function(){ 
			  console.log("window.onbeforeunload called to ajax the updated time");
			  if(navigateCallMadeFalg){
				  makeAjaxCallWhenNavigate();
				  navigateCallMadeFalg=false;
			  }
			  
	      }
		  function pageHidden(evt)
		    {
		        if (evt.persisted){
		        	console.log("pagehide event handler called.  The page was suspended and placed into the Page Cache.");
		        	if(navigateCallMadeFalg){
						  makeAjaxCallWhenNavigate();
						  navigateCallMadeFalg=false;
					  }
		        }else{
		        	console.log("pagehide event handler called for page destruction.  This is the same as the unload event.");
		        	if(navigateCallMadeFalg){
						  makeAjaxCallWhenNavigate();
						  navigateCallMadeFalg=false;
					  }
		        }
		        	
		    }
		  window.addEventListener("pagehide", pageHidden, false);
		  
		  function makeAjaxCallWhenNavigate(){
			  console.log("....makeAjaxCallWhenNavigate....");
			  var end = Date.now(); 
			  var pageName=$(".page-content-container")[0];
			  if(pageName!=undefined && pageName.id!="custom_login_page"){
				  console.log("Time stayed on this page");
				  console.log(end-pageStartTime)
				  var newTimeValue=end-pageStartTime;
				  var experimentTimeValue;
				  if(pageName!=undefined && pageName.id=="custom_settings_page"){
					  experimentTimeValue="0";
				  }else{
					  experimentTimeValue=experimentTime+newTimeValue;
				  }
				  	
				  var typeOfRequest="";
				  var articleIdLocal;
				  if(typeof(articleId) == 'undefined'){
					  articleIdLocal ="";
				  }else{
					  articleIdLocal=articleId;
				  }
				    
				  
				  if(pageName!=undefined && pageName.id=="custom_home_page"){
					  typeOfRequest="updateTimeOnHomePage";
				  }else if(pageName!=undefined && pageName.id=="custom_settings_page"){
					  typeOfRequest="updateTimeOnSettingsPage";
				  }
				  else if(pageName!=undefined && pageName.id=="custom_article_page"){
					  typeOfRequest="updateTimeOnArticlePage";
				  }
			  	  if(typeOfRequest!=""){
					  data={
							typeOfRequest:typeOfRequest,
							newTimeValue:newTimeValue,
							userId : userId,
							articleId : articleIdLocal,
							experimentTime : experimentTimeValue
							};
					  $.ajax({
		            	  method: "POST",
		      			  url: "UpdateTime", 
		      			  data: data,
		      			  async: false,
		      			  success: function(result){
		      			  console.log(result);
		      			 
		              },
		              error: function () {
		                  console.log("error in recordTime");
		                }
		              }); 
				  }
			  }
		  }
		  
	  }
	  function disableRightClick() {
		  console.log("Custom Main - disableRightClick called");
		  var loginPage=$("#custom_login_page");
		  if(loginPage.length==0){
			  document.addEventListener('contextmenu', event => event.preventDefault());  
		  }else{
			  console.log("Not disabling rightClick on LoginPage");
		  }
		    
	  }
	  
	  function disableOpenInNewTab() {
		  console.log("Custom Main - disableOpenInNewTab called");
		  var loginPage=$("#custom_login_page");
		  if(loginPage.length==0){
			  var links = document.getElementsByTagName("a");
			  for(var i=0; i<links.length; i++){
			      links[i].setAttribute("data-href", links[i].getAttribute("href"));
			      links[i].removeAttribute("href");
			      links[i].onclick = function(){
			          window.location = this.getAttribute("data-href");
			      };
			  }  
		  }else{
			  console.log("Not disabling OpenInNewTab on LoginPage");
		  }
		  
		  
	  }
	  function redirectHttps() {
		  console.log("Custom Main - redirectHttps called");
		  if (location.protocol != 'http:'){
		   location.href = 'http:' + window.location.href.substring(window.location.protocol.length);
		  }
	  }
	  
	  return {
	    init: function() {
	    	console.log("Custom Main - init called");
	    	recordTime();
	    	disableRightClick();
	    	disableOpenInNewTab();
	    	redirectHttps();
	    	}
	  };   
	})();

jQuery(document).ready(function() {
	customMain.init();
});

//var myclose = false;
//function ConfirmClose(){
//	debugger;
//    if (event.clientY < 0){
//        event.returnValue = 'You have closed the browser. Do you want to logout from your application?';
//        setTimeout('myclose=false',10);
//        myclose=true;
//    }
//}
//
//function HandleOnClose(){
//	debugger;
//    if (myclose==true){
//        //the url of your logout page which invalidate session on logout 
//        location.replace('/UserLogout') ;
//    }   
//}

