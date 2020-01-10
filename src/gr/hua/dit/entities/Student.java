package gr.hua.dit.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User {
	
	@Column(name = "activated")
	private boolean activated;
	
	@Column(name="ranking")
	private int ranking;

	@OneToOne(mappedBy = "createdBy", cascade = CascadeType.ALL)
	private Application application;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dept_id")
	private Department department;

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student [activated=" + activated + ", ranking=" + ranking + ", application=" + application
				+ ", department=" + department + ", toString()=" + super.toString() + "]";
	}

	
}
