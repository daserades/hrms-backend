package project.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> getByAdvertisementStatusTrue();

	List<JobAdvertisement> getByAdvertisementStatusTrue(Sort sort);

	List<JobAdvertisement> getByAdvertisementStatusTrueAndEmployer_Id(int employerId);

}
