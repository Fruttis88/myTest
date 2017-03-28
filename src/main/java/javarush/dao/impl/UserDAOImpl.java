package javarush.dao.impl;

import javarush.dao.interfaces.UserDAO;
import javarush.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.faces.bean.ApplicationScoped;
import java.sql.Timestamp;
import java.util.List;

@Component
@ApplicationScoped
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;


    private List<User> users;

    @Transactional
    public List<User> getUsers() {
        users = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        return users;
    }

    @Transactional
    public List<User> getUsers(String searchName) {
        users = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions.ilike("name", searchName, MatchMode.ANYWHERE)).list();
        return users;
    }

    @Transactional
    public void saveUser(String name, int age, boolean isAdmin) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setIsAdmin(isAdmin);
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Transactional
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
