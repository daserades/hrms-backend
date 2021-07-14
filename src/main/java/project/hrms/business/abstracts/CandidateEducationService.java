package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.entities.concretes.CandidateEducation;

public interface CandidateEducationService {
	DataResult<List<CandidateEducation>> getAll();
}
