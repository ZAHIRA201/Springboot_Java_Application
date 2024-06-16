package com.ensah.core.web.controllers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ensah.core.bo.Examen;
import com.ensah.core.bo.Salle;
import com.ensah.core.bo.Surveillance;
import com.ensah.core.bo.Enseignant;
import com.ensah.core.bo.CadreAdministrateur;
import com.ensah.core.services.IExamenService;
import com.ensah.core.services.ISalleService;
import com.ensah.core.services.IPersonneService;
import com.ensah.core.services.ISurveillanceService;
import com.ensah.core.utils.ExcelExporter;

@Controller
@RequestMapping("/admin/Examen")
public class ExamenController {

    @Autowired
    private IExamenService examenService;

    @Autowired
    private ISalleService salleService;

    @Autowired
    private IPersonneService personneService;

    @Autowired
    private ISurveillanceService surveillanceService;

    /**@GetMapping("/showForm")
    public String showExamenCreationForm(Model model) {
        Examen examen = new Examen();
        model.addAttribute("examen", examen);
        model.addAttribute("salles", salleService.getAllSalles());
        model.addAttribute("enseignants", personneService.getAllEnseignants());
        model.addAttribute("admins", personneService.getAllAdmins());
        return "admin/Examen/form-Examen";
    }

    @PostMapping("/addExamen")
    public String addExamen(@Valid @ModelAttribute("examen") Examen examen,
                            BindingResult result,
                            @RequestParam List<Long> salleIds,
                            @RequestParam List<Long> enseignantIds,
                            @RequestParam Long adminId,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            return "admin/Examen/form-Examen";
        }
        
        boolean created = examenService.creerExamen(examen, salleIds, enseignantIds, adminId);
        if (!created) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            model.addAttribute("error", "Échec de la création de l'examen. Vérifiez la disponibilité des salles et du personnel.");
            return "admin/Examen/form-Examen";
        }
        
        // Assign surveillance
        surveillanceService.assignSurveillance(examen, enseignantIds, salleIds);
        
        return "redirect:/admin/Examen/manageExamens";
    }

    @GetMapping("/manageExamens")
    public String listExamens(Model model) {
        List<Examen> examens = examenService.getAllExamens();
        
        // Récupérer les enseignants et admins via personneService
        List<Enseignant> enseignants = personneService.getAllEnseignants();
        List<CadreAdministrateur> admins = personneService.getAllAdmins();
        
        model.addAttribute("examens", examens);
        model.addAttribute("enseignants", enseignants);
        model.addAttribute("salles", salleService.getAllSalles());
        model.addAttribute("admins", admins);
        
        return "admin/Examen/listeExamens";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Examen examen = examenService.getExamenById(id);
        if (examen == null) {
            return "redirect:/admin/Examen/manageExamens";
        }
        model.addAttribute("examen", examen);
        model.addAttribute("salles", salleService.getAllSalles());
        model.addAttribute("enseignants", personneService.getAllEnseignants());
        model.addAttribute("admins", personneService.getAllAdmins());
        return "admin/Examen/editExamen";
    }*/

    /**@PostMapping("/edit/{id}")
    public String updateExamen(@PathVariable("id") Long id,
                               @Valid @ModelAttribute("examen") Examen examen,
                               BindingResult result,
                               @RequestParam List<Long> salleIds,
                               @RequestParam List<Long> enseignantIds,
                               @RequestParam Long adminId,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            return "admin/Examen/editExamen";
        }
        examen.setId(id);
        boolean updated = examenService.creerExamen(examen, salleIds, enseignantIds, adminId);
        if (!updated) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            model.addAttribute("error", "Échec de la mise à jour de l'examen. Vérifiez la disponibilité des salles et du personnel.");
            return "admin/Examen/editExamen";
        }

        // Mise à jour de la disponibilité des enseignants après la fin de l'examen
        Examen updatedExamen = examenService.getExamenById(id);
        if (updatedExamen != null) {
            for (Long enseignantId : enseignantIds) {
                Enseignant enseignant = (Enseignant) personneService.getPersonneById(enseignantId);
                if (enseignant != null) {
                    enseignant.setDisponible(true); // Mettez à jour la disponibilité de l'enseignant après la fin de l'examen
                    personneService.updatePersonne(enseignant); // Mettez à jour l'enseignant dans la base de données
                }
            }
        }

        // Appeler la méthode pour libérer les ressources associées à l'examen
        examenService.examenTermine(id);

        // Update surveillance details if necessary
        surveillanceService.updateSurveillance(examen, enseignantIds, salleIds);

        return "redirect:/admin/Examen/manageExamens";
    }*/
    
    @PostMapping("/edit/{id}")
    public String updateExamen(@PathVariable("id") Long id,
                               @Valid @ModelAttribute("examen") Examen examen,
                               BindingResult result,
                               @RequestParam List<Long> salleIds,
                               @RequestParam List<Long> enseignantIds,
                               @RequestParam Long adminId,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            return "admin/Examen/editExamen";
        }
        examen.setId(id);
        boolean updated = examenService.creerExamen(examen, salleIds, enseignantIds, adminId);
        if (!updated) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            model.addAttribute("error", "Échec de la mise à jour de l'examen. Vérifiez la disponibilité des salles et du personnel.");
            return "admin/Examen/editExamen";
        }

        // Supprimer les enseignants non associés à l'examen
        surveillanceService.deleteUnassignedTeachers(id, enseignantIds);

        // Mise à jour de la disponibilité des enseignants après la fin de l'examen
        for (Long enseignantId : enseignantIds) {
            // Vérifiez la disponibilité de chaque enseignant
            if (personneService.isEnseignantAvailable(enseignantId)) {
                // Mettez à jour la disponibilité de l'enseignant après la fin de l'examen
                personneService.updateEnseignantAvailability(enseignantId, false);
            }
        }

        // Appeler la méthode pour libérer les ressources associées à l'examen
        examenService.examenTermine(id);

        // Update surveillance details if necessary
        surveillanceService.updateSurveillance(examen, enseignantIds, salleIds);

        return "redirect:/admin/Examen/manageExamens";
    }

    @GetMapping("/showForm")
    public String showExamenCreationForm(Model model) {
        Examen examen = new Examen();
        model.addAttribute("examen", examen);
        model.addAttribute("salles", salleService.getAllSalles());
        model.addAttribute("enseignants", personneService.getAllEnseignants());
        model.addAttribute("admins", personneService.getAllAdmins());
        return "admin/Examen/form-Examen";
    }

    /**@PostMapping("/addExamen")
    public String addExamen(@Valid @ModelAttribute("examen") Examen examen,
                            BindingResult result,
                            @RequestParam List<Long> salleIds,
                            @RequestParam List<Long> enseignantIds,
                            @RequestParam Long adminId,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            return "admin/Examen/form-Examen";
        }

        for (Long salleId : salleIds) {
            if (!examenService.isSalleAvailable(salleId, examen)) {
                model.addAttribute("salles", salleService.getAllSalles());
                model.addAttribute("enseignants", personneService.getAllEnseignants());
                model.addAttribute("admins", personneService.getAllAdmins());
                model.addAttribute("error", "La salle " + salleService.getSalleById(salleId).getNom() + " n'est pas disponible à cette date et heure.");
                return "admin/Examen/form-Examen";
            }
        }

        boolean created = examenService.creerExamen(examen, salleIds, enseignantIds, adminId);
        if (!created) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            model.addAttribute("error", "Échec de la création de l'examen. Vérifiez la disponibilité des salles et du personnel.");
            return "admin/Examen/form-Examen";
        }
        
        // Assign surveillance
        surveillanceService.assignSurveillance(examen, enseignantIds, salleIds);
        
        return "redirect:/admin/Examen/manageExamens";
    }*/
    
    @PostMapping("/addExamen")
    public String addExamen(@Valid @ModelAttribute("examen") Examen examen,
                            BindingResult result,
                            @RequestParam List<Long> salleIds,
                            @RequestParam List<Long> enseignantIds,
                            @RequestParam Long adminId,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            return "admin/Examen/form-Examen";
        }
        
        for (Long salleId : salleIds) {
            if (!examenService.isSalleAvailable(salleId, examen)) {
                model.addAttribute("salles", salleService.getAllSalles());
                model.addAttribute("enseignants", personneService.getAllEnseignants());
                model.addAttribute("admins", personneService.getAllAdmins());
                model.addAttribute("error", "La salle " + salleService.getSalleById(salleId).getNom() + " n'est pas disponible à cette date et heure.");
                return "admin/Examen/form-Examen";
            }
        }

        for (Long enseignantId : enseignantIds) {
            List<Surveillance> existingSurveillances = surveillanceService.getSurveillanceByEnseignantId(enseignantId);
            if (!existingSurveillances.isEmpty()) {
                // Une surveillance existe déjà pour cet enseignant dans d'autres examens, vous pouvez supprimer ces surveillances
                
                // Supprimer les surveillances existantes associées à cet enseignant pour d'autres examens
                for (Surveillance existingSurveillance : existingSurveillances) {
                    // Vérifier si cette surveillance est associée à un autre examen
                    if (!existingSurveillance.getExamen().getId().equals(examen.getId())) {
                        // Supprimer cette surveillance car elle est associée à un autre examen
                        surveillanceService.deleteSurveillance(existingSurveillance.getId());
                    }
                }
            }
            
            // Ajouter la nouvelle surveillance pour cet enseignant dans le nouvel examen
            Surveillance nouvelleSurveillance = new Surveillance();
            // Initialisez les autres propriétés de la surveillance si nécessaire
            nouvelleSurveillance.setExamen(examen); // Passer l'examen actuel
            Enseignant enseignant = (Enseignant) personneService.getPersonneById(enseignantId);
            nouvelleSurveillance.setProf(enseignant);
            surveillanceService.addSurveillance(nouvelleSurveillance);
        }
        

        boolean created = examenService.creerExamen(examen, salleIds, enseignantIds, adminId);
        if (!created) {
            model.addAttribute("salles", salleService.getAllSalles());
            model.addAttribute("enseignants", personneService.getAllEnseignants());
            model.addAttribute("admins", personneService.getAllAdmins());
            model.addAttribute("error", "Échec de la création de l'examen. Vérifiez la disponibilité des salles et du personnel.");
            return "admin/Examen/form-Examen";
        }
        
        // Assign surveillance
        surveillanceService.assignSurveillance(examen, enseignantIds, salleIds);
        
        return "redirect:/admin/Examen/manageExamens";
    }

    

    @GetMapping("/manageExamens")
    public String listExamens(Model model) {
        List<Examen> examens = examenService.getAllExamens();
        
        // Récupérer les enseignants et admins via personneService
        List<Enseignant> enseignants = personneService.getAllEnseignants();
        List<CadreAdministrateur> admins = personneService.getAllAdmins();
        
        model.addAttribute("examens", examens);
        model.addAttribute("enseignants", enseignants);
        model.addAttribute("salles", salleService.getAllSalles());
        model.addAttribute("admins", admins);
        
        return "admin/Examen/listeExamens";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Examen examen = examenService.getExamenById(id);
        if (examen == null) {
            return "redirect:/admin/Examen/manageExamens";
        }
        model.addAttribute("examen", examen);
        model.addAttribute("salles", salleService.getAllSalles());
        model.addAttribute("enseignants", personneService.getAllEnseignants());
        model.addAttribute("admins", personneService.getAllAdmins());
        return "admin/Examen/editExamen";
    }

    @GetMapping("/delete/{id}")
    public String deleteExamen(@PathVariable Long id) {
        examenService.deleteExamen(id);
        return "redirect:/admin/Examen/manageExamens";
    }

    /**@GetMapping("/exportExamens")
    public void exportExamensToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=examens_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Examen> examens = examenService.getAllExamens();
        ExcelExporter excelExporter = examenService.prepareExamenExport(examens);
        excelExporter.export(response);
    }*/
}