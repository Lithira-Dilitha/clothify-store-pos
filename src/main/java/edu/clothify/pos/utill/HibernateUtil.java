package edu.clothify.pos.utill;

import edu.clothify.pos.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory session = createSession();

    private static SessionFactory createSession() {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(ItemEntity.class)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(OrdersEntity.class)
                .addAnnotatedClass(UserEntity.class)
                .addAnnotatedClass(OrderDetailsEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();
        return metadata.getSessionFactoryBuilder().build();

    }
    public static Session getSession(){
       return session.openSession();
    }
}
