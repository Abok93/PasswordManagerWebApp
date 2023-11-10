//Aiden Bokrossy 
//ID: 991712275

package ca.sheridancollege.bokrossy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.bokrossy.beans.PasswordRecord;
import ca.sheridancollege.bokrossy.database.DatabaseAccess;
import ca.sheridancollege.bokrossy.utilities.RandomNumberGenerator;

@Controller
public class HomeController {
	@Autowired
	private DatabaseAccess da;

	// Handles the initial page load
	@GetMapping("/")
	public String index(Model model) {

		// Create a new PasswordRecord and set its ID
		PasswordRecord aPasswordRecord = new PasswordRecord();
		aPasswordRecord.setId(Long.valueOf(RandomNumberGenerator.generateRandomId()));

		// Add the new PasswordRecord and the list of password records to the page
		model.addAttribute("passwordRecord", aPasswordRecord);
		model.addAttribute("passwordRecordList", da.getPasswordRecordList());

		// Return the name of the page to display ("index.html")
		return "index";
	}

	// Handles viewing password records
	@GetMapping("/viewPasswordRecord")
	public String viewPasswordRecord(@RequestParam(name = "id", required = false) Long searchId, Model model) {
		List<PasswordRecord> passwordRecords;

		// Check if a specific ID was provided, retrieve records accordingly
		if (searchId != null) {
			passwordRecords = da.getPasswordRecordListById(searchId);
		} else {
			passwordRecords = da.getPasswordRecordList();
		}

		// Add the list of PasswordRecords to the page
		model.addAttribute("passwordRecordList", passwordRecords);

		// Add the list of password records to the page
		return "viewPasswordRecord";
	}

	// Handles searching for a password record
	@GetMapping("/searchPasswordRecord")
	public String searchPasswordRecord(@RequestParam(name = "searchId", required = false) Long searchId, Model model) {

		List<PasswordRecord> passwordRecords;

		// Check if a specific ID was provided in the request
		if (searchId != null) {
			passwordRecords = da.getPasswordRecordListById(searchId);
			PasswordRecord foundRecord = passwordRecords.isEmpty() ? null : passwordRecords.get(0);
			// Set to true when a search is done
			boolean searchPerformed = true;

			// Add found record and search information to the page
			model.addAttribute("foundRecord", foundRecord);
			model.addAttribute("searchPerformed", searchPerformed);
			model.addAttribute("searchId", searchId);
		} else {
			passwordRecords = da.getPasswordRecordList();
		}

		// Add the passwordRecordlList to the page
		model.addAttribute("passwordRecordList", passwordRecords);

		// Return the page to be displayed
		return "searchPasswordRecord";
	}

	// Handles adding a new record
	@PostMapping("/addRecord")
	public String addRecord(Model model, @ModelAttribute PasswordRecord passwordRecord) {
		// Insert the new record into the database
		da.insertPasswordRecord(passwordRecord);

		String success = "Record added successfully!";
		model.addAttribute("success", success);

		// Create a new PasswordRecord with a randomly generated ID
		PasswordRecord aPasswordRecord = new PasswordRecord();
		aPasswordRecord.setId(Long.valueOf(RandomNumberGenerator.generateRandomId()));

		// Add a new PasswordRecord and the list of records to the page
		model.addAttribute("passwordRecord", aPasswordRecord);
		model.addAttribute("passwordRecordList", da.getPasswordRecordList());

		// Displays "index.html"
		return "index";
	}
}