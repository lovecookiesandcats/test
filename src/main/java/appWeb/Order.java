package appWeb;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DAO;
import mail.Sender;

@ManagedBean(name="order")
@ViewScoped
public class Order implements Serializable {

	private String fname = "";
	private String lname = "";
	private String patron = "";
	private String phone= "";
	private String address = "";
	private String index = "";
	private String email = "";
	private String day = "";
	private String city = "";
	private String person = "";
	private String time = "";
	
	@ManagedProperty(value="#{cart}")
	private Cart cart;
	
	public void setCart(Cart cart)
	{
		this.cart = cart;
	}
	
	private static final long serialVersionUID = 3323152288653736834L;

	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	private boolean b = true;
	
	private dao.DAO<model.Ord> odao = new dao.DAO<model.Ord>(model.Ord.class);
	public void CreateOrder()
	{
		if (!b) return;
		b = false;
		
		if (fname.length() > 20)
		{
			fname = "";
		}
		
		if (lname.length() > 20)
		{
			lname = "";
		}
		
		if (patron.length() > 20)
		{
			patron = "";
		}
		
		if (address.length() > 200)
		{
			address = "";
		}
		
		Pattern p = Pattern.compile("^[0-9]{6}$");  
        Matcher m = p.matcher(index.toString());  
        if (!m.matches())
        {
        	 index = "";
        }
		
        p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");  
        m = p.matcher(email.toString());
        if (!m.matches())
        {
        	 email = "";
        }
		
		if (!day.equals("Понедельник") && !day.equals("Вторник") && !day.equals("Среда") && !day.equals("Четверг") && !day.equals("Пятница") && !day.equals("Суббота") && !day.equals("Воскресение"))
		{
			day = "Неизвестный";
		}
		
		if (!time.equals("9-12") && !time.equals("13-16") && !time.equals("16-19") && !time.equals("19-21"))
		{
			time = "Неизвестно";
		}
		
		p = Pattern.compile("^8[0-9]{10}$");  
        m = p.matcher(phone.toString()); 
        
        if (!m.matches())
        {
        	 b = true;
        	 return;
        }
		
		model.Ord ord = new model.Ord();
		ord.setFirstname(fname);
		ord.setLastname(lname);
		ord.setPatron(patron);
		ord.setAddress(address);
		ord.setInd(index);
		ord.setEmail(email);
		ord.setPhone(phone);
		ord.setOrddate(new Date());
		ord.setCity(city);
		ord.setPatron(patron);
		ord.setRecdate(new Date());
		ord.setPerson(person);
		
		
		EntityManagerFactory factory = null;
		EntityManager em = null;
		try
		{
		factory = Persistence.createEntityManagerFactory("soap");
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
        em.persist(ord);
		
		for (model.Row r: cart.getRows())
		{
			r.setOrd(ord);
			em.persist(r);
		}
		
		em.getTransaction().commit();
		}
		catch (Exception e)
		{
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		finally
		{
			
			em.close();
			factory.close();
		}
		
		Sender s = new Sender();
	    	
        String content = "";
		
		for(model.Row r: cart.getRows())
		{
			if (r.getProduct().getType() == 1)
			content = content + "Артикул: (" + r.getProduct().getProductid() + ")  Масса:   (" + r.getCount() + ") гр. \n";
			else
			content = content + "Артикул: (" + r.getProduct().getProductid() + ")  Количество:   (" + r.getCount() + ") " + " \n";
		}
		
		String str = "Номер счета: " + ord.getOrdid() + " \n"
				+ "\n"
				+ ord.getFirstname() + " " + ord.getLastname() + " " + ord.getPatron() + "\n"
				+ "Номер телефона: " + ord.getPhone() + " \n"
				+ "Адрес доставки: " + ord.getAddress() + "\n"
				+ "E-Mail: " + ord.getEmail() + "\n"
				+ "Содержимое заказа: \n"
				+ "------ " + " \n"
				+ content + "\n"
				+ "------ " + " \n"
				+ "Общая сумма: " + cart.CartInfoPrice() + " руб. \n"
				+ "Общая масса: " + cart.CartInfoWeight() + " г. \n"
				+ "\n"
				+ "хочет видеть товар в " + day + "\n"
				+ "в " + time + ".\n";
		
		
		s.send("Оповещение", str);
		cart.getRows().clear();

		
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("orderisready.xhtml?oid=" + ord.getOrdid());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPatron() {
		return patron;
	}

	public void setPatron(String patron) {
		this.patron = patron;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	

}
