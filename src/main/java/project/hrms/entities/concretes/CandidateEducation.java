package project.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidate_educations")
@EqualsAndHashCode(callSuper = false)
public class CandidateEducation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "graduation_date")
	private Date graduationDate;
	
	@Column(name = "graduation_status")
	private boolean graduationStatus;
	
	//private int candidateId;
	//private int schoolId;
	//private int departmentId;
	
	@ManyToOne()
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@ManyToOne()
	@JoinColumn(name = "school_id")
	private School school;
	
	@ManyToOne()
	@JoinColumn(name = "employer_id")
	private Department department;
	
	
}
