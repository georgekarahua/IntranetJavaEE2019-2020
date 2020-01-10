package gr.hua.dit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "form")
public class Form {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "annual_income")
	private int annualIncome;
	@Column(name = "parental_status")
	private boolean parentalStatus;
	@Column(name = "siblings_students")
	private int siblingStudents;
	@Column(name = "residence")
	private String resdence;
	
	public Form() {
		super();
	}
	
	public Form(int id, int annualIncome, boolean parentalStatus, int siblingStudents, String resdence) {
		super();
		this.id = id;
		this.annualIncome = annualIncome;
		this.parentalStatus = parentalStatus;
		this.siblingStudents = siblingStudents;
		this.resdence = resdence;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}
	public boolean isParentalStatus() {
		return parentalStatus;
	}
	public void setParentalStatus(boolean parentalStatus) {
		this.parentalStatus = parentalStatus;
	}
	public int getSiblingStudents() {
		return siblingStudents;
	}
	public void setSiblingStudents(int siblingStudents) {
		this.siblingStudents = siblingStudents;
	}
	public String getResdence() {
		return resdence;
	}
	public void setResdence(String resdence) {
		this.resdence = resdence;
	}

	
}
