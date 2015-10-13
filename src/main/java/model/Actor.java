package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name="actor")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int actorid;

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

	@Column(length=200)
	private String patron;

	@Column(length=200)
	private String phone;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="actor")
	private List<Account> accounts;

	//bi-directional many-to-one association to Ord
	@OneToMany(mappedBy="actor1")
	private List<Ord> ords1;

	//bi-directional many-to-one association to Ord
	@OneToMany(mappedBy="actor2")
	private List<Ord> ords2;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="actors")
	private List<Role> roles;

	//bi-directional many-to-one association to Vkaccount
	@OneToMany(mappedBy="actor")
	private List<Vkaccount> vkaccounts;

	public Actor() {
	}

	public int getActorid() {
		return this.actorid;
	}

	public void setActorid(int actorid) {
		this.actorid = actorid;
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

	public String getPatron() {
		return this.patron;
	}

	public void setPatron(String patron) {
		this.patron = patron;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setActor(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setActor(null);

		return account;
	}

	public List<Ord> getOrds1() {
		return this.ords1;
	}

	public void setOrds1(List<Ord> ords1) {
		this.ords1 = ords1;
	}

	public Ord addOrds1(Ord ords1) {
		getOrds1().add(ords1);
		ords1.setActor1(this);

		return ords1;
	}

	public Ord removeOrds1(Ord ords1) {
		getOrds1().remove(ords1);
		ords1.setActor1(null);

		return ords1;
	}

	public List<Ord> getOrds2() {
		return this.ords2;
	}

	public void setOrds2(List<Ord> ords2) {
		this.ords2 = ords2;
	}

	public Ord addOrds2(Ord ords2) {
		getOrds2().add(ords2);
		ords2.setActor2(this);

		return ords2;
	}

	public Ord removeOrds2(Ord ords2) {
		getOrds2().remove(ords2);
		ords2.setActor2(null);

		return ords2;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Vkaccount> getVkaccounts() {
		return this.vkaccounts;
	}

	public void setVkaccounts(List<Vkaccount> vkaccounts) {
		this.vkaccounts = vkaccounts;
	}

	public Vkaccount addVkaccount(Vkaccount vkaccount) {
		getVkaccounts().add(vkaccount);
		vkaccount.setActor(this);

		return vkaccount;
	}

	public Vkaccount removeVkaccount(Vkaccount vkaccount) {
		getVkaccounts().remove(vkaccount);
		vkaccount.setActor(null);

		return vkaccount;
	}

}