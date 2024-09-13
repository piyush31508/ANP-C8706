package com.map;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage  = CacheConcurrencyStrategy.READ_ONLY)
public class Answer {
	@Id
	private int aId;
	private String answer;
	@ManyToOne
	private Question q;
	
	
	@Override
	public String toString() {
        return "Answer [aId=" + aId + ", answer=" + answer + ", question=" + (q != null ? q.getQuestion() : "None") + "]";
	}
	public Question getQ() {
		return q;
	}
	public void setQ(Question q) {
		this.q = q;
	}
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Answer(int aId, String answer) {
		super();
		this.aId = aId;
		this.answer = answer;
	}
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
