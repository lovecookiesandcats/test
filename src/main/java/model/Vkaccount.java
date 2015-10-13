package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vkaccount database table.
 * 
 */
@Entity
@Table(name="vkaccount")
@NamedQuery(name="Vkaccount.findAll", query="SELECT v FROM Vkaccount v")
public class Vkaccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int vkaccountid;

	@Column(length=200)
	private String id;

	//bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="actor_actorid", nullable=false)
	private Actor actor;

	public Vkaccount() {
	}

	public int getVkaccountid() {
		return this.vkaccountid;
	}

	public void setVkaccountid(int vkaccountid) {
		this.vkaccountid = vkaccountid;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}