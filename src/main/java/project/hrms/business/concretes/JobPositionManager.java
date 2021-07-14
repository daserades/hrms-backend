package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobPositionService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataAccess.abstracts.JobPositionDao;
import project.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{
	
	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobDao) {
		super();
		this.jobPositionDao = jobDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>
		(this.jobPositionDao.findAll(), "Pozisyonlar Listelendi.");
	}

	@Override
	public Result add(JobPosition jobPosition) {
		if(jobPosition.getPositionName().length() <= 0) {
			return new ErrorResult("İş Pozisyonu Boş Olamaz.");
		}
		if(getByPositionName(jobPosition.getPositionName()) != null) {
			System.out.print(getByPositionName(jobPosition.getPositionName()));
			return new ErrorResult("Bu iş pozisyonu zaten mevcut");
		}
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult("İş pozisyonu eklendi.");
	}

	@Override
	public JobPosition getByPositionName(String positionName) {
		return this.jobPositionDao.getByPositionName(positionName);
	}


}
