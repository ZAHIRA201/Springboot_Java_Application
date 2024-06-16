package com.ensah.core.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensah.core.bo.*;
import com.ensah.core.services.*;
import com.ensah.core.utils.ExcelExporter;
import com.ensah.core.web.models.*;

import java.io.IOException;
import java.text.*;
import java.util.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/admin")
public class ElementPedagogiqueController {

	@Autowired
	private IElementPedagogiqueService elementService;

	@Autowired
    private IEnseignantService enseignantService;
	
	@Autowired
	private HttpSession httpSession;
	
	
	/** Utilisé pour la journalisation */
	private Logger LOGGER = LoggerFactory.getLogger(getClass());


	public ElementPedagogiqueController() {	
	}
	
	@GetMapping(value = "showFormm")
    public String showFormm(Model model) {
		
        ElementModel pmodel = new ElementModel();
        model.addAttribute("elementModel", pmodel);
        model.addAttribute("enseignants", enseignantService.getAllEnseignants());

        List<ElementPedag> elements = elementService.getAllElementsPedagogiques1();
        List<ElementModel> modelElements = new ArrayList<>();

        for (ElementPedag element : elements) {
            ElementModel pm = new ElementModel();
            BeanUtils.copyProperties(element, pm);
            pm.setIdPersonne(element.getEnseignant().getIdPersonne()); 
            modelElements.add(pm);
        }

        model.addAttribute("elementList", modelElements);

        return "admin/addElementForm";
    }

    @RequestMapping(value = "addElement", method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute("elementModel") ElementModel element, BindingResult bindingResult,
                          Model model, HttpServletRequest rq) {

        if (bindingResult.hasErrors()) {
            return "admin/addElementForm";
        }

        ElementPedag ele = new ElementPedag();
        BeanUtils.copyProperties(element, ele);
        if (element.getIdPersonne() != null) {
            Enseignant coordonnateur = enseignantService.getEnseignantById(element.getIdPersonne());
            ele.setEnseignant(coordonnateur);
        }
        elementService.addElementPedagogique(ele);

        return "redirect:/admin/showFormm";
    }

	@RequestMapping(value = "updateEleForm/{idElement}", method = RequestMethod.GET)
	public String updateElementForm(@PathVariable("idElement") int idElement, Model model) {

		// On reoit comme paramètre l'id de la personne à mettre à jour
		ElementPedag utl = elementService.getElementPedagogiqueById(Long.valueOf(idElement));

		ElementModel pm = new ElementModel();
		BeanUtils.copyProperties(utl, pm);
		pm.setIdPersonne(utl.getEnseignant().getIdPersonne());
		model.addAttribute("elementModel", pm);
		model.addAttribute("enseignants", enseignantService.getAllEnseignants());

		return "admin/updateElementForm";
	}
	
	@RequestMapping("updateElement")
	public String updateElement(@Valid @ModelAttribute("elementModel") ElementModel element, BindingResult bindingResult,
			Model model) {

		// En cas d'erreur
		if (bindingResult.hasErrors()) {

			return "admin/updateElementForm";
		}

		ElementPedag ele = new ElementPedag();
		BeanUtils.copyProperties(element, ele);
		if (element.getIdPersonne() != null) {
            Enseignant coordonnateur = enseignantService.getEnseignantById(element.getIdPersonne());
            ele.setEnseignant(coordonnateur);
        }
		
		elementService.updateElementPedagogique(ele);
	
		model.addAttribute("msg", "Opération effectuée avec succès");

		return "admin/updateElementForm";
	}
	
	@RequestMapping(value = "deleteElement/{idElement}", method = RequestMethod.GET)
	public String delete(@PathVariable int idElement) {

		elementService.deleteElementPedagogique(Long.valueOf(idElement));

		return "redirect:/admin/manageElements";
	}
	
	
	@RequestMapping("manageElements")
	public String manageElements(Model model) {

		ElementModel pmodel = new ElementModel();
        model.addAttribute("elementModel", pmodel);

        List<ElementPedag> elements = elementService.getAllElementsPedagogiques1();
        List<ElementModel> modelElements = new ArrayList<>();

        for (ElementPedag element : elements) {
            ElementModel pm = new ElementModel();
            BeanUtils.copyProperties(element, pm);
            pm.setIdPersonne(element.getEnseignant().getIdPersonne());
            modelElements.add(pm);
        }

        model.addAttribute("elementList", modelElements);


		return "admin/listElements";
	}

	@GetMapping("exportElements")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Elements_Pedag_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<ElementPedag> elements = elementService.getAllElementsPedagogiques1();

		ExcelExporter excelExporter = elementService.prepareElementExport(elements);

		excelExporter.export(response);
		}

	/*@RequestMapping(value = "serachPerson", method = RequestMethod.GET)
	public String serachPerson(@RequestParam String cin, Model model) {

		// On reoit comme paramètre l'id de la personne à mettre à jour
		Personne utl = personService.getPersonneByCin(cin);

		if (utl == null) {

			// Initialiser le modele avec la personne
			model.addAttribute("personModel", new ArrayList<PersonModel>());
		} else {

			// On construit le modèle
			PersonModel pm = new PersonModel();

			// En fonction due type de l'utilisateur à modifier
			// Ceci va nous pemettre d'afficher un formulaire adapté
			// slon le type de la personne
			if (utl instanceof Etudiant) {
				BeanUtils.copyProperties((Etudiant) utl, pm);
				pm.setTypePerson(PersonModel.TYPE_STUDENT);
			} else if (utl instanceof Enseignant) {
				BeanUtils.copyProperties((Enseignant) utl, pm);
				pm.setTypePerson(PersonModel.TYPE_PROF);
			} else if (utl instanceof CadreAdministrateur) {
				BeanUtils.copyProperties((CadreAdministrateur) utl, pm);
				pm.setTypePerson(PersonModel.TYPE_CADRE_ADMIN);

			}
			List<PersonModel> modelPersons = new ArrayList<PersonModel>();
			modelPersons.add(pm);
			// Initialiser le modele avec la personne
			model.addAttribute("personList", modelPersons);
		}
		return "admin/listPersons";
	}**/


	
}