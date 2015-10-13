package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int productid;

	@Column(length=200)
	private String description;

	@Column(length=200)
	private String name;

	@Column(length=200)
	private String picture;

	private int price;

	private int type;
	
	private int n;
	
	private int nn;

	private int weight;

	//bi-directional many-to-one association to Subcategory
	@ManyToOne
	@JoinColumn(name="subcategory_subcategoryid", nullable=false)
	private Subcategory subcategory;

	//bi-directional many-to-one association to Row
	@OneToMany(mappedBy="product")
	private List<Row> rows;

	public Product() {
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getN() {
		return this.n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	public int getNn() {
		return this.nn;
	}

	public void setNn(int nn) {
		this.nn = nn;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Subcategory getSubcategory() {
		return this.subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public List<Row> getRows() {
		return this.rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	public Row addRow(Row row) {
		getRows().add(row);
		row.setProduct(this);

		return row;
	}

	public Row removeRow(Row row) {
		getRows().remove(row);
		row.setProduct(null);

		return row;
	}

}