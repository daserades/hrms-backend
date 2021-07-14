package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.JobAdvertisementService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobAdvertisements")
@CrossOrigin
public class JobAdvertisementsController {
	
	private JobAdvertisementService jobAdvertisementService;
	
	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll(){
		return this.jobAdvertisementService.getAll();
	}
	
	@GetMapping("/getallByAdvertisementStatusTrue")
	public DataResult<List<JobAdvertisement>> getallByAdvertisementStatusTrue(){
		return this.jobAdvertisementService.getAllByAdvertisementStatusTrue();
	}
	
	@GetMapping("/getallByAdvertisementStatusTrueSortReleaseDate")
	public DataResult<List<JobAdvertisement>> getallByAdvertisementStatusTrueSortReleaseDate(){
		return this.jobAdvertisementService.getAllByAdvertisementStatusTrueSortReleaseDate();
	}
	
	@GetMapping("/getallByAdvertisementStatusTrueAndEmployerId")
	public DataResult<List<JobAdvertisement>> getAllByAdvertisementStatusTrueAndEmployer_EmployerId(@RequestParam int employerId){
		return this.jobAdvertisementService.getAllByAdvertisementStatusTrueAndEmployer_EmployerId(employerId);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {	
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
}
