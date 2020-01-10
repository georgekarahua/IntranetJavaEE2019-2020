package gr.hua.dit.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="application")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	@Column(name = "approved")
	private boolean approved;
	@Column(name = "points")
	private int points;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="form_id")
	private Form applicationForm;


	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="stud_id")
	private Student createdBy;
//	@MapsId
	public Application() {
		
	}

	public Application(int id, LocalDateTime creationDate, boolean approved, int points,
			Student createdBy, Form applicationForm) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.approved = approved;
		this.points = points;
		this.createdBy = createdBy;
		this.applicationForm = applicationForm;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Student getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Student createdBy) {
		this.createdBy = createdBy;
	}
	public Form getApplicationForm() {
		return applicationForm;
	}
	public void setApplicationForm(Form applicationForm) {
		this.applicationForm = applicationForm;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", creationDate=" + creationDate + ", approved=" + approved + ", points="
				+ points + ", applicationForm=" + applicationForm + ", createdBy=" + createdBy + "]";
	}
	
	
}
