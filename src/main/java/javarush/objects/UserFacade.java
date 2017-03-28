package javarush.objects;


import javarush.dao.interfaces.UserDAO;
import javarush.entities.User;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@Component("userFacade")
@SessionScoped
public class UserFacade implements Serializable {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SearchCriteria searchCriteria;

    @Autowired
    private AddCriteria addCriteria;

    private List<User> users;

    private User selectedUser;

    public UserFacade() {
    }
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public List<User> getUsers() {
        if (users == null) {
            users = userDAO.getUsers();
        }
        return users;
    }

    public void searchUsersByName() {
        users = userDAO.getUsers(searchCriteria.getName());
    }


    public void saveUser() {
        userDAO.saveUser(addCriteria.getName(), addCriteria.getAge(), addCriteria.getIsAdmin());
        users = userDAO.getUsers();
        RequestContext.getCurrentInstance().execute("PF('dlgAddUser').hide()");
    }

    public void delete() {
        userDAO.deleteUser(selectedUser);
        users = userDAO.getUsers();
    }

    public void switchEditMode(){
        RequestContext.getCurrentInstance().execute("PF('dlgEditUser').show();");
    }

    public void edit() {
        userDAO.updateUser(selectedUser);
        users = userDAO.getUsers();
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}


