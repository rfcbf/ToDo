package br.com.esig.todo.controller;


import br.com.esig.todo.entity.Todo;
import br.com.esig.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("tarefas")
public class TodoController {



    @Autowired
    private TodoService todoService;

    @GetMapping("listar")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("tarefas", todoService.recuperar("T"));
        return new ModelAndView("listar", model);
    }

    @GetMapping("listarconcluidos")
    public ModelAndView listarconcluidos(ModelMap model){
        model.addAttribute("tarefas", todoService.recuperar("C"));
        return new ModelAndView("listar", model);
    }

    @GetMapping("listarnaoconcluidos")
    public ModelAndView listarnaoconcluidos(ModelMap model){
        model.addAttribute("tarefas", todoService.recuperar("N"));
        return new ModelAndView("listar", model);
    }

    @GetMapping("cadastro")
    public String preSalvar(@ModelAttribute("tarefa") Todo todo){
        todo.setConcluido(false);
        return "cadastro";
    }

    @PostMapping("salvar")
    public String salvar(@Valid @ModelAttribute("tarefa") Todo todo, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "cadastro";
        }

        todoService.salvar(todo);

        attr.addFlashAttribute("mensagem", "Tarefa criada com sucesso.");
        return "redirect:tarefas/listar";
    }

    @GetMapping("{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") Integer id, ModelMap model){

        Todo todo = todoService.recuperarPorId(id);
        model.addAttribute("tarefa", todo);
        return new ModelAndView("cadastro", model);

    }

    @PutMapping("salvar")
    public String atualizar(@Valid @ModelAttribute("tarefa") Todo todo, BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()) {
            return "cadastro";
        }

        todoService.atualizar(todo);

        attr.addFlashAttribute("mensagem", "Tarefa atualizada com sucesso.");
        return "redirect:tarefas/listar";
    }

    @GetMapping("{id}/remover")
    public String remover(@PathVariable("id") Integer id, RedirectAttributes attr){
        todoService.excluir(id);
        attr.addFlashAttribute("mensagem", "Tarefa excluída com sucesso.");
        return "redirect:tarefas/listar";
    }

    @GetMapping("{id}/concluir")
    public String concluir(@PathVariable("id") Integer id, RedirectAttributes attr){
        todoService.concluir(id);
        attr.addFlashAttribute("mensagem", "Tarefa concluída.");
        return "redirect:tarefas/listar";
    }

    @GetMapping("{id}/naoconcluir")
    public String naoConcluir(@PathVariable("id") Integer id, RedirectAttributes attr){
        todoService.naoConcluir(id);
        attr.addFlashAttribute("mensagem", "Tarefa não concluída.");
        return "redirect:tarefas/listar";
    }


}
