package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().saveOrUpdate(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> findUser(String model, int series) {
      TypedQuery<User> find = sessionFactory.getCurrentSession()
              .createQuery("from User u  inner join Car c on u.car.id = c.id where c.model = :c_model and c.series = :c_series");
      find.setParameter("c_model", model);
      find.setParameter("c_series", series);
      return find.getResultList();
   }

}
