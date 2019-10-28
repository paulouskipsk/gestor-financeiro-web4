$(document).ready(function(){
	$(".date-format").change(function() {
		  var data = new Date($(this).val()); //cria um objeto de data com o valor inserido no input
		  data = data.toLocaleDateString('pt-BR'); // converte em uma string de data no formato pt-BR
		  $(this).val(data); // insere o novo valor no input
	});
	
});

