package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Misurazioni")
public class Misurazione {
	
	public Misurazione() {}
	
	public Misurazione(int id, Date data, Paziente paziente, List<Sintomi> sintomi, float temperaturaCorporea,
			String altrePatologie, String note) {
		super();
		this.id = id;
		this.data = data;
		this.paziente = paziente;
		this.sintomi = sintomi;
		this.temperaturaCorporea = temperaturaCorporea;
		this.altrePatologie = altrePatologie;
		this.note = note;
	}
	
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private Date data;
	
	@ManyToOne
	private Paziente paziente;
	
	@ManyToMany
	private List<Sintomi> sintomi;
	
	@Column
	private float temperaturaCorporea;
	@Column
	private String altrePatologie;
	@Column
	private String note;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Paziente getPaziente() {
		return paziente;
	}
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	public List<Sintomi> getSintomi() {
		return sintomi;
	}
	public void setSintomi(List<Sintomi> sintomi) {
		this.sintomi = sintomi;
	}
	public float getTemperaturaCorporea() {
		return temperaturaCorporea;
	}
	public void setTemperaturaCorporea(float temperaturaCorporea) {
		this.temperaturaCorporea = temperaturaCorporea;
	}
	public String getAltrePatologie() {
		return altrePatologie;
	}
	public void setAltrePatologie(String altrePatologie) {
		this.altrePatologie = altrePatologie;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
	

}
