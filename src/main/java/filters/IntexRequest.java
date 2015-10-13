package filters;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import appWeb.Index;
import model.Row;

@ManagedBean(name="indexrequest")
@RequestScoped
public class IntexRequest implements Serializable
{

private static final long serialVersionUID = -3677941890890444931L;

public IntexRequest()
{

}

@ManagedProperty("#{index}")
private Index index;

public List<model.Row> getMass()
{

if (index.getRows().size() == 0)
{
FacesContext faces = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession)faces.getExternalContext().getSession(false);
List<model.Row> rows = (List<Row>)session.getAttribute("mass");
index.setRows(rows);
index.setCurrentSubcategory(rows.get(0).getProduct().getSubcategory());
return rows;
}
else
{
return index.getRows();
}

}

public Index getIndex() {
return index;
}

public void setIndex(Index index) {
this.index = index;
}

}