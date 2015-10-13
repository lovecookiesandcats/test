package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int roleid;

	@Column(length=45)
	private String name;

	//bi-directional many-to-many association to Actor
	@ManyToMany
	@JoinTable(
		name="role_has_actor"
		, joinColumns={
			@JoinColumn(name="role_roleid", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="actor_actorid", nullable=false)
			}
		)
	private List<Actor> actors;

	public Role() {
	}

	public int getRoleid() {
		return this.roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Actor> getActors() {
		return this.actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

}