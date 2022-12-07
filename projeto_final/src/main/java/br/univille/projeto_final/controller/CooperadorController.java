package br.univille.projeto_final.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projeto_final.entity.Cooperador;
import br.univille.projeto_final.service.CooperadorService;

@Controller
@RequestMapping("/cooperadores")
public class CooperadorController {

    @Autowired
    private CooperadorService service; 
    
    @GetMapping
    public ModelAndView index(){
        var listaCooperadores = service.getAll();
        return new ModelAndView("cooperador/index", "listaCooperadores", listaCooperadores);
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(){
        var cooperador = new Cooperador();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("cooperador",cooperador);
        return new ModelAndView("cooperador/formCadastro", dados);
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id){
        var umCooperador = service.findById(id);
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("cooperador",umCooperador);
        return new ModelAndView("cooperador/formCadastro",dados);
    }

    @GetMapping("/material/{id}")
    public ModelAndView material(@PathVariable("id") long id){
        var umCooperador = service.findById(id);
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("cooperador",umCooperador);
        return new ModelAndView("material/index",dados);
    }

    @PostMapping(params = "formCadastro")
    public ModelAndView save(@Validated Cooperador cooperador,
                            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            HashMap<String,Object> dados = new HashMap<>();
            dados.put("cooperador", cooperador);
            return new ModelAndView("cooperador/formCadastro",dados);
        }
        service.save(cooperador);
        return new ModelAndView("redirect:/local");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id){
        service.delete(id);
        return new ModelAndView("redirect:/funcionarios");
    }
}