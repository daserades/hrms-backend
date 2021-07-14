package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<List<JobAdvertisement>> getAllByAdvertisementStatusTrue();
	Result add(JobAdvertisement jobAdvertisement);
	DataResult<List<JobAdvertisement>> getAllByAdvertisementStatusTrueSortReleaseDate();
	DataResult<List<JobAdvertisement>> getAllByAdvertisementStatusTrueAndEmployer_EmployerId(int employerId);
}
