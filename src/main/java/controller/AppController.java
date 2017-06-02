package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*path variable no va con get
* requestmapping va con get, put y post, va el string path
* queryParam permite conjunto de parametros con signo de pregunta, nombre de la variable
* y al lado el valor, para concatenar pongo un & y agrego lo que necesite
* queryparam siempre va con get
*/
@RestController
public class AppController {

	
	@RequestMapping("/")
    public String bienvenida () {
        return "HOME PAGE by Emi";
    }
	
	@RequestMapping(value = "/location/{namelocation}", method=RequestMethod.GET)
    public String getweather (@PathVariable("namelocation") String namelocation)  {
        return namelocation+"funciona el get";
    }
}
