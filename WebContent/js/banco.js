/**
 * 
 */

window.onload = main;

function gestion_cdt_control()
{		
	radios = document.getElementsByName("gestion_cdt"),
			crear_inputs = [
				document.getElementsByName("cuenta")[0],
				document.getElementsByName("interes")[0],
				document.getElementsByName("monto")[0]
			],
			cerrar_inputs = [document.getElementsByName("cdt")[0]];
		
		deshabilitar(crear_inputs);
		deshabilitar(cerrar_inputs);
		
	for(i in radios)
	{
		if(typeof radios[i] == "object")
		{
				
			radios[i].addEventListener("change", permitir_escribir);
			radios[i].dispatchEvent(new Event("change"));
		}
	}
}

function deshabilitar(inputs)
{
	for(i in inputs)
	{
		inputs[i].disabled = true;
		inputs[i].style.backgroundColor = "black";
	}
}

function habilitar(inputs)
{
	for(i in inputs)
	{
		inputs[i].disabled = false;
		inputs[i].style.backgroundColor = "white";
	}
}

var permitir_escribir = function()
{
	if(this.checked)
	{
		if(this.value === "crear")
		{
			habilitar(crear_inputs);
			deshabilitar(cerrar_inputs);
		}
		else
		{
			habilitar(cerrar_inputs);
			deshabilitar(crear_inputs);
		}
	}
}

function marcar_pantalla()
{
	var cont = document.getElementsByClassName("div_volver")[0],
		nodo = document.createElement("p"),
		texto = document.createTextNode("Capa Cliente gestionada por JavaScript");
		
	nodo.appendChild(texto);
	nodo.style.fontSize = "12px";
	nodo.style.margin = "10px";
	nodo.style.textAlign = "right";
	cont.appendChild(nodo);
}

function comprobar_cookie_listados()
{
	var cookies = document.cookie.split(";"),
		li_salir = document.getElementById("li_salir"),
		regexp = new RegExp("cookie_test=.*"),
		cookie_activa = false;
	if(li_salir != null)
	{
		for(i in cookies)
		{
			if(regexp.test(cookies[i]))
			{
				cookie_activa = true;
				break;
			}	
		}
		
		var clase = cookie_activa ? "li_salir" : "li_salir oculto";
		
		li_salir.className = clase;
	}
}


function poner_spinner()
{
	var res_form = document.getElementsByClassName("res_form")[0];

	if(typeof res_form != "undefined")
	{
		res_form.innerHTML = "<img class=\"spinner\" src=\"imagenes/spinner.png\">";
	}
}


function preparar_spinner()
{
	var form = document.getElementsByTagName("form")[0];
	if(typeof form == "object")
	{
		var boton = form.getElementsByClassName("div_boton")[0].getElementsByTagName("input")[0];
		
		form.onsubmit = function(e){
			
			e.preventDefault();
			
			boton.value = "Procesando";
			boton.onclick = function(){ return false;}
			
			poner_spinner();
			
			var esto = this;
			
			// Imprescindible para que salga el spinner
			setTimeout(function(){
				esto.submit();
			}, 1000);
			
		}
	}
}

function main()
{
	var ges_crear = document.getElementsByClassName("gestion_crear")[0];
		
	if(typeof ges_crear != "undefined")
	{
		marcar_pantalla();
		gestion_cdt_control();
	}
	
	comprobar_cookie_listados();
	
	preparar_spinner();	
}





