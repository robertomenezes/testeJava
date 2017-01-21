<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Gerenciador de Endereços</title>

<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
<style type="text/css">
	body {
	  padding: 0 2em;
	  font-family: Montserrat, sans-serif;
	  -webkit-font-smoothing: antialiased;
	  text-rendering: optimizeLegibility;
	  color: #444;
	  background: #eee;
	}
	
	h1 {
	  font-weight: normal;
	  letter-spacing: -1px;
	  color: #34495E;
	}
	
	table a:link {
		color: #666;
		font-weight: bold;
		text-decoration:none;
	}
	table a:visited {
		color: #999999;
		font-weight:bold;
		text-decoration:none;
	}
	table a:active,
	table a:hover {
		color: #bd5a35;
		text-decoration:underline;
	}
	table {
		width: 100%;
		font-family:Arial, Helvetica, sans-serif;
		color:#666;
		font-size:12px;
		text-shadow: 1px 1px 0px #fff;
		background:#eaebec;
		border:#ccc 1px solid;
	
		-moz-border-radius:3px;
		-webkit-border-radius:3px;
		border-radius:3px;
	
		-moz-box-shadow: 0 1px 2px #d1d1d1;
		-webkit-box-shadow: 0 1px 2px #d1d1d1;
		box-shadow: 0 1px 2px #d1d1d1;
	}
	table th {
		padding:21px 25px 22px 25px;
		border-top:1px solid #fafafa;
		border-bottom:1px solid #e0e0e0;
	
		background: #ededed;
		background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#ebebeb));
		background: -moz-linear-gradient(top,  #ededed,  #ebebeb);
	}
	
	table.inputs tr td{
		padding: 0px !important;
	}
	table.inputs{
		display: none;
	}
	table th:first-child{
		text-align: left;
		padding-left:20px;
	}
	table tr:first-child th:first-child{
		-moz-border-radius-topleft:3px;
		-webkit-border-top-left-radius:3px;
		border-top-left-radius:3px;
	}
	table tr:first-child th:last-child{
		-moz-border-radius-topright:3px;
		-webkit-border-top-right-radius:3px;
		border-top-right-radius:3px;
	}
	table tr{
		text-align: center;
		padding-left:20px;
	}
	table tr td:first-child{
		text-align: left;
		padding-left:20px;
		border-left: 0;
	}
	table tr td {
		padding:18px;
		border-top: 1px solid #ffffff;
		border-bottom:1px solid #e0e0e0;
		border-left: 1px solid #e0e0e0;
		
		background: #fafafa;
		background: -webkit-gradient(linear, left top, left bottom, from(#fbfbfb), to(#fafafa));
		background: -moz-linear-gradient(top,  #fbfbfb,  #fafafa);
	}
	table tr.even td{
		background: #f6f6f6;
		background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f6f6f6));
		background: -moz-linear-gradient(top,  #f8f8f8,  #f6f6f6);
	}
	table tr:last-child td{
		border-bottom:0;
	}
	table tr:last-child td:first-child{
		-moz-border-radius-bottomleft:3px;
		-webkit-border-bottom-left-radius:3px;
		border-bottom-left-radius:3px;
	}
	table tr:last-child td:last-child{
		-moz-border-radius-bottomright:3px;
		-webkit-border-bottom-right-radius:3px;
		border-bottom-right-radius:3px;
	}
	table tr:hover td{
		background: #f2f2f2;
		background: -webkit-gradient(linear, left top, left bottom, from(#f2f2f2), to(#f0f0f0));
		background: -moz-linear-gradient(top,  #f2f2f2,  #f0f0f0);	
	}
	td.button{
		padding: 0px;
	}
	#cepPesquisa{
		display:none;
	}
	#btnAtualizar{
		display:none;
	}
	.red::-webkit-input-placeholder {
		color: red;
	}

</style>
</head>
<body>
	<center><h1>Gerenciador de Enderecos</h1></center>
	<span id="message"></span>
	<br/>
	<input type="button" value="Cancelar" onclick="restExibicao();"/>
	<input type="button" value="Adicionar" onclick="exibirInputs();"/>
	<input id="btnShowPesquisar" type="button" value="Pesquisar" onclick="inputPesquisar();"/>
	<br/>
	<span id="cepPesquisa">
		<input type="text" id="inPesquisar" placeholder="Entre com o Cep" maxlength="8" onkeypress="return event.charCode >= 48 && event.charCode <= 57"/>
		<input type="button" value="Pesquisar" onclick="pesquisar();"/>
	</span>
	<table class="inputs">
		<tr>
			<td>
				<input type="hidden" id="idEdit"/>
				<input type="text" placeholder="Cep" id="cep" maxlength="8" onkeypress="return event.charCode >= 48 && event.charCode <= 57;" onblur="removeStyle(this)"/>
			</td>
			<td><input type="text" placeholder="Logradouro" id="logradouro" /></td>
			<td><input type="text" placeholder="Numero" id="numero" maxlength="8" onkeypress="return event.charCode >= 48 && event.charCode <= 57"/></td>
			<td><input type="text" placeholder="Complemento" id="complemento" /></td>
			<td><input type="text" placeholder="Bairro" id="bairro" /></td>
			<td><input type="text" placeholder="Cidade" id="cidade" onkeypress="return !(event.charCode >= 48 && event.charCode <= 57)"/></td>
			<td><input type="text" placeholder="Estado" id="estado" maxlength="2" onkeypress="return !(event.charCode >= 48 && event.charCode <= 57)"/></td>
			<td><input type="button" id="btnGravar" onclick="gravar();" value="Gravar"></td>
			<td><input type="button" id="btnAtualizar" onclick="atualizar();" value="Gravar"></td>
		</tr>
	</table>
	<table id="tabEnderecos">
		<tr>
			<th>CEP</th>
			<th>LOGRADOURO</th>
			<th>NUMERO</th>
			<th>COMPLEMENTO</th>
			<th>BAIRRO</th>
			<th>CIDADE</th>
			<th>ESTADO</th>
			<th><input type="button" onclick="listarEnderecos();" value="Pesquisar Todos"></th>
			<th><input type="button" onclick="excluirTodos();" value="Excluir Todos"></th>
		</tr>
	</table>
	
	<script type="text/javascript">
		$(document).ready(function(){
			listarEnderecos();
		})
		var dataJson = [];
		function restExibicao(){
			$("#message").html("");
			$("#cepPesquisa").hide();
			$("#btnShowPesquisar").show();
			$(".inputs").hide();
		}
		function pesquisar(){
			$("#message").html("");
			var cep = $("#inPesquisar").val();
			$.ajax({url: "/gerenciadorEnderecos/service/buscarEnderecoPorCep/"+cep,
				type: "GET",
			    success: function(data){
			    	if(!$.isEmptyObject(data)){
				    	$("#tabEnderecos td").remove();
				    	$("#dataJson").val(data);
				    	var html;
						$.each(data, function(i,e){
							html += "<tr><td>"+e.cep+"</td>";
							html += "<td>"+e.logradouro+"</td>";
							html += "<td>"+e.numero+"</td>";
							html += "<td>"+e.complemento+"</td>";
							html += "<td>"+e.bairro+"</td>";
							html += "<td>"+e.cidade+"</td>";
							html += "<td>"+e.estado+"</td>";
							html += "<td class='button'><input type='button' value='Editar' onclick='editar("+e.id+")';/></td>";
							html += "<td class='button'><input type='button' value='Excluir' onclick='excluir("+e.id+")';/></td></tr>";
					    })
						$("#tabEnderecos").append(html);
			    	} else {
			    		$("#message").html("Cep não encontrado");
			    	}
			    }
			})
		}
		
		function excluir(id){
			$("#message").html("");
			$.ajax({url: "/gerenciadorEnderecos/service/deletarPorId/"+id,
				type: "DELETE",
				success: function(data){
					if(data != ""){
						var error = $.parseJSON(data);
						$("#message").html(error.Error);
					} else {
						$("#message").html("Atualizado com sucesso");
						$(":text").val("");
						$(".inputs").hide();
						listarEnderecos();
					}
					}
				});
		}
		
		function editar(id){
			$("#message").html("");
			$("#cepPesquisa").hide();
			$("#btnShowPesquisar").show();
			$(".inputs").show();
			$("#btnGravar").hide();
			$("#btnAtualizar").show();
			var endereco = new Object();
			$.each(dataJson, function(i,e){
				if(e.id == id){
					endereco = e;
					return;
				}
			})
			$("#idEdit").val(endereco.id);
			$("#cep").val(endereco.cep);
			$("#logradouro").val(endereco.logradouro);
			$("#numero").val(endereco.numero);
			$("#complemento").val(endereco.complemento);
			$("#bairro").val(endereco.bairro);
			$("#cidade").val(endereco.cidade);
			$("#estado").val(endereco.estado);
		}
		function atualizar(){
			$("#message").html("");
			var endereco = new Object();
			endereco.id = $("#idEdit").val();
			endereco.cep = $("#cep").val();
			endereco.logradouro = $("#logradouro").val();
			endereco.numero = $("#numero").val();
			endereco.complemento = $("#complemento").val();
			endereco.bairro = $("#bairro").val();
			endereco.cidade = $("#cidade").val();
			endereco.estado = $("#estado").val();
			
			if(validarDados(endereco))
				ajaxAtualizar(endereco);
		}
		
		function ajaxAtualizar(endereco){
			$.ajax({url: "/gerenciadorEnderecos/service/atualizarEndereco/"+endereco.id,
				type: "PUT",
				data: JSON.stringify(endereco),
				contentType: "application/json",
				success: function(data){
					if(data != ""){
						var error = $.parseJSON(data);
						$("#message").html(error.Error);
					} else {
						$("#message").html("Atualizado com sucesso");
						$(":text").val("");
						$(".inputs").hide();
						listarEnderecos();
					}
					}
				});
		}
		function listarEnderecos(){
			$("#message").html("");
			$.ajax({url: "/gerenciadorEnderecos/service/listarEnderecos/",
				type: "GET",
			    success: function(data){
			    	if(!$.isEmptyObject(data)){
				    	$("#dataJson").val(data);
				    	$("#tabEnderecos tr td").remove();
						var html;
						$.each(data, function(i,e){
							dataJson.push(e);
							html += "<tr><td>"+e.cep+"</td>";
							html += "<td>"+e.logradouro+"</td>";
							html += "<td>"+e.numero+"</td>";
							html += "<td>"+e.complemento+"</td>";
							html += "<td>"+e.bairro+"</td>";
							html += "<td>"+e.cidade+"</td>";
							html += "<td>"+e.estado+"</td>";
							html += "<td class='button'><input type='button' value='Editar' onclick='editar("+e.id+")';/></td>";
							html += "<td class='button'><input type='button' value='Excluir' onclick='excluir("+e.id+")';/></td></tr>";
					    })
						$("#tabEnderecos").append(html);
			    	}
			    }
			});
		}	
		function exibirInputs(){
			$("#message").html("");
			$(":text").val("");
			$("btnGravar").show();
			$("btnAtualizar").hide();
			$("#cepPesquisa").hide();
			$("#btnShowPesquisar").show();
			$(".inputs").show();
		}
		function inputPesquisar(){
			$("#message").html("");
			$(".inputs").hide();
			$("#cepPesquisa").show();
			$("#btnShowPesquisar").hide();
		}
		function gravar(){
			$("#message").html("");
			var endereco = new Object();
			endereco.cep = $("#cep").val();
			endereco.logradouro = $("#logradouro").val();
			endereco.numero = $("#numero").val();
			endereco.complemento = $("#complemento").val();
			endereco.bairro = $("#bairro").val();
			endereco.cidade = $("#cidade").val();
			endereco.estado = $("#estado").val();
			
			if(validarDados(endereco))
				ajaxCadastrar(endereco);
		}
		
		function ajaxCadastrar(endereco){
			$.ajax({url: "/gerenciadorEnderecos/service/cadastrarEndereco/",
				type: "POST",
				data: JSON.stringify(endereco),
				contentType: "application/json",
				success: function(data){
					if(data != ""){
						var error = $.parseJSON(data);
						$("#message").html(error.Error);
					} else {
						$("#message").html("Gravado com sucesso");
						$(":text").val("");
						$(".inputs").hide();
					}
					}
				});
		}
		function validarDados(endereco){
			if(endereco.cep == null || endereco.cep == ""){
				$("#cep").addClass("red").get(0).focus();
				return false;
			} else if (endereco.cep.length < 8){
				$("#cep").attr("style","color:red").get(0).focus();
				return false;
			} else if(endereco.logradouro == null || endereco.logradouro == ""){
				$("#logradouro").addClass("red").get(0).focus();
				return false;
			} else if(endereco.numero == null || endereco.numero == ""){
				$("#numero").addClass("red").get(0).focus();
				return false;
			} else if(endereco.cidade == null || endereco.cidade == ""){
				$("#cidade").addClass("red").get(0).focus();
				return false;
			} else if(endereco.estado == null || endereco.estado == ""){
				$("#estado").addClass("red").get(0).focus();
				return false;
			}
			return true;
		}
		function removeStyle(input){
			$(input).removeAttr("style");
		}
		$(':input').on('input', function(evt) {
		  $(this).val(function (_, val) {
		    return val.toUpperCase();
		  });
		});
	</script>
</body>
</html>