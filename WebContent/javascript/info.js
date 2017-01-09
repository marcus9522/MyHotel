function info(email){
	window.location.href = "prenotazione?action=getprenotazioniuser&email="+email;  
}
function info2(email){
	window.location.href = "utente?action=getusers&email="+email;  
}
function image(){
	var a= document.getElementById(image).value;
	console.log(a);
	document.getElementById("image").src=a;
}
