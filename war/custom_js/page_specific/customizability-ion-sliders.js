var ComponentsIonSliders = function() {

    var handleBasicDemo = function() {
         fromVal_el=0;
        if(from_el!=null && from_el!=""){
        	fromVal_el=parseInt(from_el);
        	 
        }
         fromVal_ec=0;
        if(from_ec!=null && from_ec!=""){
        	fromVal_ec=parseInt(from_ec);
        	
        }
         fromVal_hl=0;
        if(from_hl!=null && from_hl!=""){
        	fromVal_hl=parseInt(from_hl);
        	 
        }
         fromVal_hc=0;
        if(from_hc!=null && from_hc!=""){
        	fromVal_hc=parseInt(from_hc);
        }
         fromVal_cl=0;
        if(from_cl!=null && from_cl!=""){
        	fromVal_cl=parseInt(from_cl);
        }
         fromVal_cc=0;
        if(from_cc!=null && from_cc!=""){
        	fromVal_cc=parseInt(from_cc);
        }
        
 
        $("#range_el").ionRangeSlider({
            grid: true,
            from: 0,
            values: [
                "None", "-",
                "Some", "-",
                "Many"
                
            ],
            //onStart: onStart_el,
            onChange: function (data) {
                console.log("onChange");
                $(".custom_settings_page .slide_wrapper_el .irs-bar-edge").attr("style","width : 9px");
                $(".custom_settings_page .slide_wrapper_el").next().hide();
            }
        });
        $("#range_ec").ionRangeSlider({
            grid: true,
            from: 0,
            values: [
				"None", "-",
				"Some", "-",
				"Many"
                
            ],
          //  onStart: onStart_ec,
            onChange: function (data) {
                console.log("onChange");
                $(".custom_settings_page .slide_wrapper_ec .irs-bar-edge").attr("style","width : 9px");
                $(".custom_settings_page .slide_wrapper_ec").next().hide();
            }
        });
        $("#range_hl").ionRangeSlider({
            grid: true,
            from: 0,
            values: [
 				"None", "-",
				"Some", "-",
				"Many"
               
            ],
         //   onStart: onStart_hl,
            onChange: function (data) {
                console.log("onChange");
                $(".custom_settings_page .slide_wrapper_hl .irs-bar-edge").attr("style","width : 9px");
                $(".custom_settings_page .slide_wrapper_hl").next().hide();
            }
        });
        $("#range_hc").ionRangeSlider({
            grid: true,
            from: 0,
            values: [
 				"None", "-",
				"Some", "-",
				"Many"
                
            ],
        //    onStart: onStart_hc,
            onChange: function (data) {
                console.log("onChange");
                $(".custom_settings_page .slide_wrapper_hc .irs-bar-edge").attr("style","width : 9px");
                $(".custom_settings_page .slide_wrapper_hc").next().hide();
            }
        });
        $("#range_cl").ionRangeSlider({
            grid: true,
            from: 0,
            values: [
 				"None", "-",
				"Some", "-",
				"Many"
               
            ],
        //    onStart: onStart_cl,
            onChange: function (data) {
                console.log("onChange");
                $(".custom_settings_page .slide_wrapper_cl .irs-bar-edge").attr("style","width : 9px");
                $(".custom_settings_page .slide_wrapper_cl").next().hide();
            }
        });
        $("#range_cc").ionRangeSlider({
            grid: true,
            from: 0,
            values: [
 				"None", "-",
				"Some", "-",
				"Many"
               
            ],
        //    onStart: onStart_cc,
            onChange: function (data) {
                console.log("onChange");
                $(".custom_settings_page .slide_wrapper_cc .irs-bar-edge").attr("style","width : 9px");
                $(".custom_settings_page .slide_wrapper_cc").next().hide();
            }
        });
    }
    var checkIfDBValuesPresent = function(){
    	 if(from_el!="" && from_ec!="" && from_hl!="" && from_hc!="" && from_cl!="" && from_cc!=""){
         	$("#range_el").data("ionRangeSlider").update({
         	    from: fromVal_el
         	});
         	$("#range_ec").data("ionRangeSlider").update({
         	    from: fromVal_ec
         	});
         	$("#range_hl").data("ionRangeSlider").update({
         	    from: fromVal_hl
         	});
         	$("#range_hc").data("ionRangeSlider").update({
         	    from: fromVal_hc
         	});
         	$("#range_cl").data("ionRangeSlider").update({
         	    from: fromVal_cl
         	});
         	$("#range_cc").data("ionRangeSlider").update({
         	    from: fromVal_cc
         	});
         	
         	$(".custom_settings_page .slide_wrapper_el .irs-bar-edge").attr("style","width : 9px");
            $(".custom_settings_page .slide_wrapper_el").next().hide();
            
            $(".custom_settings_page .slide_wrapper_ec .irs-bar-edge").attr("style","width : 9px");
            $(".custom_settings_page .slide_wrapper_ec").next().hide();
            
            $(".custom_settings_page .slide_wrapper_hl .irs-bar-edge").attr("style","width : 9px");
            $(".custom_settings_page .slide_wrapper_hl").next().hide();
            
            $(".custom_settings_page .slide_wrapper_hc .irs-bar-edge").attr("style","width : 9px");
            $(".custom_settings_page .slide_wrapper_hc").next().hide();
            
            $(".custom_settings_page .slide_wrapper_cl .irs-bar-edge").attr("style","width : 9px");
            $(".custom_settings_page .slide_wrapper_cl").next().hide();
            
            $(".custom_settings_page .slide_wrapper_cc .irs-bar-edge").attr("style","width : 9px");
            $(".custom_settings_page .slide_wrapper_cc").next().hide();
            
         }
    }
    return {
        //main function to initiate the module
        init: function() {
            handleBasicDemo();
            try{
            	$(".custom_settings_page .single").attr("style","left : 100%");
            	$(".custom_settings_page .irs-bar").attr("style","width : 0%");
            	$(".custom_settings_page .irs-bar-edge").attr("style","width : 0%");
            	checkIfDBValuesPresent();
            	
            }catch(e){
            	console.log(e);
            }
        }

    };

}();

jQuery(document).ready(function() {
    ComponentsIonSliders.init();
});