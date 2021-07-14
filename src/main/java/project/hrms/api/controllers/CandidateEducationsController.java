package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.CandidateEducationService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.entities.concretes.CandidateEducation;

@RestController
@RequestMapping("/api/candidateEducations")
@CrossOrigin
public class CandidateEducationsController {
	
	private CandidateEducationService candidateEducationService;

	@Autowired
	public CandidateEducationsController(CandidateEducationService candidateEducationService) {
		super();
		this.candidateEducationService = candidateEducationService;
	}
	
	@GetMapping("/getall")
	DataResult<List<CandidateEducation>> getAll(){
		return this.candidateEducationService.getAll();
	}

}
