package br.com.varela.erp.web.clients.controllers;

import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.varela.erp.core.repositories.ClientRepository;
import br.com.varela.erp.web.clients.dtos.ClientForm;
import br.com.varela.erp.web.clients.dtos.ClientViewModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping
    public ModelAndView index() {
        var clients = clientRepository.findAll()
            .stream()
            //.map((client) -> ClientViewModel.of(client))
            .map(ClientViewModel::of)
            .toList()
            ;
        // var clientsViewModel = new ArrayList<ClientViewModel>();

        // for (Client client : clients) {
        //     var clientViewModel = ClientViewModel.of(client);
        //     clientsViewModel.add(clientViewModel);
        // }

        var model = Map.of("clients", clients); 
        return new ModelAndView("clients/index", model);
    }

    @GetMapping("/create")
    public ModelAndView create()
    {
        var model = Map.of(
            "clientForm", new ClientForm(),
            "pageTitle", "Cadastro de cliente"
            );
        return new ModelAndView("clients/form", model);
    }

    @PostMapping("/create")
    public String create(ClientForm clientForm)
    {   
        var client = clientForm.toClient();
        System.out.println(client.getName());
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var clientForm = clientRepository.findById(id)
            .map(ClientForm::of)
            .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado."));
        
        var model = Map.of(
                "clientForm", clientForm,
                "pageTitle", "Edição de cliente"
            );
        return new ModelAndView("clients/form", model);
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ClientForm clientForm)
    {
        if (!clientRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente não encontrado.");
        }
        var client = clientForm.toClient();
        client.setId(id);
        clientRepository.save(client);
        return "redirect:/clients";

    }

    @GetMapping("/delete/{id}")
    public ModelAndView getDelete(@PathVariable Long id) {
        var clientForm = clientRepository.findById(id)
            .map(ClientForm::of)
            .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado."));
        
        var model = Map.of("clientForm", clientForm);
        return new ModelAndView("clients/delete", model);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (!clientRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente não encontrado.");
        }
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }

}   
