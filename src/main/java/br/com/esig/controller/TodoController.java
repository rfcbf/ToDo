package br.com.esig.controller;


import br.com.esig.entity.Todo;
import br.com.esig.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.AttributedString;

@Controller
@RequestMapping("tarefas")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("tarefas", todoService.recuperar());
        return new ModelAndView("/home", model);
    }


    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("tarefa") Todo todo){
        return "/home";

    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("tarefa") Todo todo, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/home";
        }

        todoService.salvar(todo);

        attr.addFlashAttribute("mensagem", "Tarefa criada com sucesso.");
        return "redirect:/tarefas/listar";
    }

}
