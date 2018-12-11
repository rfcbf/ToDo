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

@Controller
@RequestMapping("tarefas")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("tarefas", todoService.recuperar());
        return new ModelAndView("/todo/list", model);
    }


    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("tarefa") Todo todo){
        todo.setConcluido("N");
        return "/todo/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("tarefa") Todo todo, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "/todo/cadastro";
        }

        todoService.salvar(todo);

        attr.addFlashAttribute("mensagem", "Tarefa criada com sucesso.");
        return "redirect:/tarefas/listar";
    }

}
