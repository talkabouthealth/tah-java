package beans;

import java.util.Date;
import java.util.Set;

public class TalkerDiseaseBean {
	
	private int id;
	private int uid;
	private int stageId;
	private int typeId;
	private boolean recurrent;
	private Date symptomDate;
	private Date diagnoseDate;
	private Set<Integer> healthItems;
	
	public int getStageId() {
		return stageId;
	}
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public boolean isRecurrent() {
		return recurrent;
	}
	public void setRecurrent(boolean recurrent) {
		this.recurrent = recurrent;
	}
	public Date getSymptomDate() {
		return symptomDate;
	}
	public void setSymptomDate(Date symptomDate) {
		this.symptomDate = symptomDate;
	}
	public Date getDiagnoseDate() {
		return diagnoseDate;
	}
	public void setDiagnoseDate(Date diagnoseDate) {
		this.diagnoseDate = diagnoseDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Set<Integer> getHealthItems() {
		return healthItems;
	}
	public void setHealthItems(Set<Integer> healthItems) {
		this.healthItems = healthItems;
	}

}
