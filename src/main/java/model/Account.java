package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name="account")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int accountid;

	@Column(length=200)
	private String login;

	@Column(length=200)
	private String password;

	//bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="actor_actorid", nullable=false)
	private Actor actor;

	public Account() {
	}

	public int getAccountid() {
		return this.accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}