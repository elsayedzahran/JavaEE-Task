package repositories;

import org.example.HibernateUtil;
import entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private Session session = null;


    public List<Product> listProducts() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "FROM Product";
            List<Product> products = session.createQuery(hql, Product.class).list();
            session.getTransaction().commit();
            return products;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

        return new ArrayList<Product>();
    }

    public void addProduct(String productName, double productPrice) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Product product = new Product();
            product.setName(productName);
            product.setPrice(productPrice);

            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean deleteProduct(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
                session.getTransaction().commit();
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }
}
