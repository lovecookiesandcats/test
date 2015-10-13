package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ord database table.
 * 
 */
@Entity
@Table(name="ord")
@NamedQuery(name="Ord.findAll", query="SELECT o FROM Ord o")
public class Ord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int ordid;

	@Column(length=200)
	private String address;

	@Column(length=200)
	private String city;

	@Column(length=200)
	private String email;

	@Column(length=200)
	private String firstname;

	@Column(length=200)
	private String ind;

	@Column(length=200)
	private String lastname;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orddate;

	@Column(length=200)
	private String patron;

	@Column(length=200)
	private String person;

	@Column(length=200)
	private String phone;

	@Temporal(TemporalType.TIMESTAMP)
	private Date recdate;

	@Column(length=45)
	private String status;

	//bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="customer")
	private Actor actor1;

	//bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="courier")
	private Actor actor2;

	//bi-directional many-to-one association to Row
	@OneToMany(mappedBy="ord")
	private List<Row> rows;

	public Ord() {
	}

	public int getOrdid() {
		return this.ordid;
	}

	public void setOrdid(int ordid) {
		this.ordid = ordid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getInd() {
		return this.ind;
	}

	public void setInd(String ind) {
		this.ind = ind;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getOrddate() {
		return this.orddate;
	}

	public void setOrddate(Date orddate) {
		this.orddate = orddate;
	}

	public String getPatron() {
		return this.patron;
	}

	public void setPatron(String patron) {
		this.patron = patron;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRecdate() {
		return this.recdate;
	}

	public void setRecdate(Date recdate) {
		this.recdate = recdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Actor getActor1() {
		return this.actor1;
	}

	public void setActor1(Actor actor1) {
		this.actor1 = actor1;
	}

	public Actor getActor2() {
		return this.actor2;
	}

	public void setActor2(Actor actor2) {
		this.actor2 = actor2;
	}

	public List<Row> getRows() {
		return this.rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	public Row addRow(Row row) {
		getRows().add(row);
		row.setOrd(this);

		return row;
	}

	public Row removeRow(Row row) {
		getRows().remove(row);
		row.setOrd(null);

		return row;
	}

}