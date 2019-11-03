$(document).ready(function(){
	hideField();
	readCookieLastServerInitialization();
	setLastServerInitialization();
	getUserRoles();
	confirmDelete();
});

function hideField(){
	$(".hide").hide();	
	$(".label-toogle").click(function() {
		  $(".hide").toggle(100);
	});
}

function hide(){
	$(".hide-msg").hide();	
	$(".hidden-msg").click(function() {
		  $(".hide-msg").toggle(100);
	});
}

function readCookieLastServerInitialization(){
	var cookie = $.cookie("lastServerInitialization");
	cookie = cookie.replace("-", " às ");
	localStorage.setItem("lastServerInitialization", cookie);
}

function setLastServerInitialization(){
	var localstorage = localStorage.getItem("lastServerInitialization");
	$('#last-server-initialization').text("Última Reinicialização do Servidor: " + localstorage);
}

function getLogAccess(){
	$.get("listar-acessos", function(resultado){
		$("#mensagem").html(resultado);
	})
}

function getUserRoles(){
	$("#user-roles").click(function(){
		$.ajax({
			type: 'GET',
			data:{ String: String },
			url: 'regras-de-usuarios',
			success: function(result){
				var roles = result.replace(/"/g, "").replace(/{/g, "").replace(/}/g, "");
				var roles = roles.split(",");
	
				var size = roles.length;
				var userRoles = "";
				for(var i=0; i< size; i++){
					var role = roles[i].split(":");
					userRoles += " Regra "+ (i+1) +" : "+ role[1]+"\n";
				}
				alert(userRoles);
			}
		});
	});
}

function confirmDelete() {	
	$('.delete').on('click', function () {
        return confirm('Confirma a exclusão deste item?');
    });
}

