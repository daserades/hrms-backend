package project.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.CandidateService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataAccess.abstracts.CandidateDao;
import project.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService{
	
	private CandidateDao candidateDao;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao) {
		super();
		this.candidateDao = candidateDao;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "Adaylar listelendi");
	}

	@Override
	public Result register(Candidate candidate) {
		if(!isAllFieldsFilled(candidate)) {
			return new ErrorResult("alanlar Boş Olamaz.");
		}
		if(getByEmail(candidate.getEmail()) != null) {
			return new ErrorResult("Bu mail zaten mevcut");
		}
		if(getByNationalId(candidate.getNationalId()) != null) {
			return new ErrorResult("Bu tc zaten mevcut");
		}
		if(!isValidEmail(candidate)) {
			return new ErrorResult("Email formatında degil.");
		}
		this.candidateDao.save(candidate);
		return new SuccessResult("Aday Eklendi.");
	}
	
	@Override
	public Candidate getByEmail(String email) {
		return this.candidateDao.getByEmail(email);
	}

	@Override
	public Candidate getByNationalId(String nationalId) {
		return this.candidateDao.getByNationalId(nationalId);
	}
	
	private boolean isAllFieldsFilled(Candidate candidate) {
		if (!isEmailFilled(candidate) || !isBirthYearFilled(candidate) || !isFirstNameFilled(candidate) ||
				!isLastNameFilled(candidate) || !isNationalIdFilled(candidate) || !isPasswordFilled(candidate)) 
		{
			return false;
		}
		
		return true;
	}
	
	private boolean isEmailFilled(Candidate candidate) {
	     if(candidate.getEmail().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isFirstNameFilled(Candidate candidate) {
	     if(candidate.getFirstName().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isLastNameFilled(Candidate candidate) {
	     if(candidate.getLastName().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isNationalIdFilled(Candidate candidate) {
	     if(candidate.getNationalId().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isBirthYearFilled(Candidate candidate) {
	     if(candidate.getBirthYear().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isPasswordFilled(Candidate candidate) {
	     if(candidate.getPassword().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	
	private boolean isValidEmail(Candidate candidate) {
		 String regex = "^(.+)@(.+)$";
		 
	     Pattern pattern = Pattern.compile(regex);
	     
	     Matcher matcher = pattern.matcher(candidate.getEmail());
	     if(matcher.matches()) {
	    	 return true;
	     }
	     return false;
	     
	}
	
}
