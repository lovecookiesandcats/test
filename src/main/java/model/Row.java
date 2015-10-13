package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the row database table.
 * 
 */
@Entity
@Table(name="row")
@NamedQuery(name="Row.findAll", query="SELECT r FROM Row r")
public class Row implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int rowid;

	private int count;

	//bi-directional many-to-one association to Ord
	@ManyToOne
	@JoinColumn(name="ord_ordid", nullable=false)
	private Ord ord;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_productid", nullable=false)
	private Product product;

	public Row() {
	}

	public int getRowid() {
		return this.rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Ord getOrd() {
		return this.ord;
	}

	public void setOrd(Ord ord) {
		this.ord = ord;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}