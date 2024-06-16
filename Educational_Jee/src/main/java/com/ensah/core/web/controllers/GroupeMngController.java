package com.ensah.core.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ensah.core.services.IGroupeService;
import com.ensah.core.services.IPersonneService;
import com.ensah.core.utils.ExcelExporterGroupe;
import com.ensah.core.web.models.GroupeModel;
import com.ensah.core.bo.Groupe;
import com.ensah.core.bo.Enseignant;

@Controller
@RequestMapping("/admin/Groupe")
public class GroupeMngController {

    @Autowired
    private IGroupeService groupeService;

    @Autowired
    private IPersonneService personneService;

    @GetMapping("/showForm")
    public String showGroupeCreationForm(Model model) {
        GroupeModel groupeModel = new GroupeModel();
        List<Enseignant> enseignants = personneService.getAllEnseignants();
        model.addAttribute("groupeModel", groupeModel);
        model.addAttribute("enseignants", enseignants);
        return "admin/Groupe/form-Groupe";
    }
    
  

    @PostMapping("/addGroupe")
    public String addGroupe(@ModelAttribute("groupeModel") GroupeModel groupeModel) {
        Groupe groupe = new Groupe();
        groupe.setNomGroupe(groupeModel.getNomGroupe());
        groupe.setTypeGroupe(groupeModel.getTypeGroupe());
        groupe.setDescription(groupeModel.getDescription());

        if (groupeModel.getEnseignantIds() != null) {
            for (Long enseignantId : groupeModel.getEnseignantIds()) {
                Enseignant enseignant = (Enseignant) personneService.getPersonneById(enseignantId);
                if (enseignant != null) {
                    groupe.getEnseignant().add(enseignant);
                }
            }
        }

        if (groupeModel.getNouvelEnseignantId() != null && !groupeModel.getNouvelEnseignantId().isEmpty()) {
            Long nouvelEnseignantId = Long.parseLong(groupeModel.getNouvelEnseignantId());
            Enseignant nouvelEnseignant = (Enseignant) personneService.getPersonneById(nouvelEnseignantId);
            if (nouvelEnseignant != null) {
                groupe.getEnseignant().add(nouvelEnseignant);
            }
        }

        groupeService.addGroupe(groupe);
        return "redirect:/admin/Groupe/manageGroupes";
    }
    
    
    /**@PostMapping("/addGroupe")
    public String addGroupe(@ModelAttribute("groupeModel") GroupeModel groupeModel) {
        Groupe groupe = new Groupe();
        groupe.setNomGroupe(groupeModel.getNomGroupe());
        groupe.setTypeGroupe(groupeModel.getTypeGroupe());
        groupe.setDescription(groupeModel.getDescription());

        if (groupeModel.getEnseignantIds() != null) {
            for (Long enseignantId : groupeModel.getEnseignantIds()) {
                Enseignant enseignant = (Enseignant) personneService.getPersonneById(enseignantId);
                if (enseignant != null) {
                    groupe.getEnseignant().add(enseignant); 
                }
            }
        }

        if (groupeModel.getNouvelEnseignantLogin() != null && !groupeModel.getNouvelEnseignantLogin().isEmpty()) {
            String nouvelEnseignantLogin = groupeModel.getNouvelEnseignantLogin();
            groupeService.addPersonneToGroupe(groupe.getId(), nouvelEnseignantLogin);
        }

        groupeService.addGroupe(groupe);
        return "redirect:/admin/Groupe/manageGroupes";
    }*/


    
    
   



    @GetMapping("/manageGroupes")
    public String listGroupes(Model model) {
        List<Groupe> groupes = groupeService.getAllGroupes();
        model.addAttribute("groupes", groupes);
        return "admin/Groupe/listeGroupe";
    }

    /**@GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Groupe groupe = groupeService.getGroupeById(id);
        if (groupe == null) {
            return "redirect:/admin/Groupe/manageGroupes";
        }
        model.addAttribute("groupe", groupe);
        return "admin/Groupe/editGroupe";
    }

    @PostMapping("/edit/{id}")
    public String updateGroupe(@PathVariable("id") Long id, @Valid @ModelAttribute("groupe") Groupe groupe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/Groupe/editGroupe";
        }
        groupe.setId(id);
        groupeService.updateGroupe(groupe);
        return "redirect:/admin/Groupe/manageGroupes";
    }*/
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Groupe groupe = groupeService.getGroupeById(id);
        if (groupe == null) {
            return "redirect:/admin/Groupe/manageGroupes";
        }
        List<Enseignant> enseignants = personneService.getAllEnseignants();
        model.addAttribute("groupe", groupe);
        model.addAttribute("enseignants", enseignants);
        return "admin/Groupe/editGroupe";
    }

    @PostMapping("/edit/{id}")
    public String updateGroupe(@PathVariable("id") Long id, @Valid @ModelAttribute("groupe") Groupe groupe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/Groupe/editGroupe";
        }
        // Fetch the existing group from the database
        Groupe existingGroupe = groupeService.getGroupeById(id);
        if (existingGroupe == null) {
            return "redirect:/admin/Groupe/manageGroupes";
        }

        // Update the existing group with the new values
        existingGroupe.setNomGroupe(groupe.getNomGroupe());
        existingGroupe.setTypeGroupe(groupe.getTypeGroupe());
        existingGroupe.setDescription(groupe.getDescription());
        
        // Update the list of teachers
        List<Enseignant> newEnseignants = new ArrayList<>();
        if (groupe.getEnseignant() != null) {
            for (Long enseignantId : groupe.getEnseignantIds()) {
                Enseignant enseignant = (Enseignant) personneService.getPersonneById(enseignantId);
                if (enseignant != null) {
                    newEnseignants.add(enseignant);
                }
            }
        }
        existingGroupe.setEnseignant(newEnseignants);

        groupeService.updateGroupe(existingGroupe);
        return "redirect:/admin/Groupe/manageGroupes";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroupe(@PathVariable Long id, Model model) {
        groupeService.deleteGroupe(id);
        model.addAttribute("groupeModel", new GroupeModel());
        return "redirect:/admin/Groupe/manageGroupes";
    }

    @GetMapping("/exportGroupe")
    public void exportGroupesToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=groupes_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Groupe> groupes = groupeService.getAllGroupes();
        
        String[] columnNames = {"ID", "Nom du Groupe", "Type de Groupe", "Description", "Membres"};
        List<String[]> data = new ArrayList<>();

        for (Groupe groupe : groupes) {
            String membres = groupe.getEnseignant().stream()
                    .map(enseignant -> enseignant.getNom() + " " + enseignant.getPrenom())
                    .collect(Collectors.joining(", "));
            
            String[] rowData = {
                String.valueOf(groupe.getId()),
                groupe.getNomGroupe(),
                groupe.getTypeGroupe(),
                groupe.getDescription(),
                membres
            };
            data.add(rowData);
        }

        ExcelExporterGroupe excelExporter = new ExcelExporterGroupe(columnNames, data.toArray(new String[0][]), "Liste des Groupes");
        excelExporter.export(response);
    }

}