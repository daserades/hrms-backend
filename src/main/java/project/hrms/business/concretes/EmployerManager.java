package project.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.EmployerService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataAccess.abstracts.EmployerDao;
import project.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "İş verenler listelendi.");
	}

	@Override
	public Result register(Employer employer) {
		if(!isAllFieldsFilled(employer)) {
			return new ErrorResult("Alanlar Boş Olamaz.");
		}
		if(!isEmailValid(employer)) {
			return new ErrorResult("Email formatında degil.");
		}
		if(!isPhoneNumberValid(employer)) {
			return new ErrorResult("telefon formatında degil.");
		}
		if(!isWebsiteValid(employer)) {
			return new ErrorResult("website formatında degil.");
		}
		if(!isWebsiteAndEmailSameFormat(employer)) {
			return new ErrorResult("website ie domain uygun degil.");
		}
		if(getByEmail(employer.getEmail()) != null) {
			return new ErrorResult("Bu mail mevcut.");
		}
		this.employerDao.save(employer);
		return new SuccessResult("İş veren eklendi.");
	}
	
	@Override
	public Employer getByEmail(String email) {
		return this.employerDao.getByEmail(email);
	}
	
	private boolean isAllFieldsFilled(Employer employer) {
		if (!isEmailFilled(employer) || !isPasswordFilled(employer) || !isCompanyNameFilled(employer) || 
				!isPhoneNumberFilled(employer) || !isWebsiteFilled(employer)) 
		{
			return false;
		}
		
		return true;
	}
	
	private boolean isEmailFilled(Employer employer) {
	     if(employer.getEmail().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isPasswordFilled(Employer employer) {
	     if(employer.getPassword().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isCompanyNameFilled(Employer employer) {
	     if(employer.getCompanyName().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isPhoneNumberFilled(Employer employer) {
	     if(employer.getPhoneNumber().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isWebsiteFilled(Employer employer) {
	     if(employer.getWebsite().length() <= 0) {
	    	 return false;
	     }
	     return true;
	}
	
	private boolean isEmailValid(Employer employer) {
		 String regex = "^(.+)@(.+)$";
		 
	     Pattern pattern = Pattern.compile(regex);
	     
	     Matcher matcher = pattern.matcher(employer.getEmail());
	     if(matcher.matches()) {
	    	 return true;
	     }
	     return false;  
	}
	
	private boolean isPhoneNumberValid(Employer employer) {
		String phoneNumberPattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
							  + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
	                          + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
	
		Pattern pattern = Pattern.compile(phoneNumberPattern);
		
		Matcher matcher = pattern.matcher(employer.getPhoneNumber());
		if(matcher.matches()) {
			return true;
		}
		return false; 
	}
	
	private boolean isWebsiteValid(Employer employer) {
		Pattern websitePattern = Pattern.compile(("^(\\/\\/)?(www\\.)?([\\w]+\\.)+[\u200C\u200B\\w]{2,63}\\/?$"));
		Matcher websiteMatcher = websitePattern.matcher(employer.getWebsite());
		return websiteMatcher.matches();
	}
	
	private boolean isWebsiteAndEmailSameFormat(Employer employer) {
		String domain = employer.getEmail().substring(employer.getEmail().indexOf("@")+1);
		return employer.getWebsite().equals(domain);
	}

}
