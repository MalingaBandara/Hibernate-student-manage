package bitlord.dao.custom.impl;

import bitlord.dao.custom.LaptopDao;
import bitlord.entity.Laptop;
import bitlord.entity.Student;
import bitlord.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class LaptopDaoImpl  implements LaptopDao {

    @Override
    public void saveLaptopWithStudentId(long studentId, Laptop laptop) {

        try (Session session = HibernateUtil.getInstance().openSession() ) {

            session.beginTransaction();

            Query< Student > query = session.createQuery("FROM Student WHERE student_id=:sId", Student.class ); // query for get student id

            query.setParameter( "sId", studentId );

            Student student = query.uniqueResult(); // assign result

            if ( student == null ) {  // check the result is null

                throw  new RuntimeException( "Student not found" );

            }

            laptop.setStudent( student ); // add student to laptop
            session.save( laptop ); // save laptop
            session.getTransaction().commit();
        }

    }

    @Override
    public void save(Laptop laptop) throws SQLException, ClassNotFoundException {
    }

    @Override
    public void update(Laptop laptop) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Laptop find(Long aLong) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(Long aLong) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Laptop> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
