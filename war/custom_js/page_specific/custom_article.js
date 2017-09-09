jQuery(document).ready(function() {
	articleDescObj=$(".blog-single-desc p")[0];
	articleContent=articleDescObj.innerText;
	articleDescObj.innerHTML=articleContent;
	//console.log("init article js");
	var logoTag=$("a#indexhegde")[0];
	logoTag.href="/Home";
});