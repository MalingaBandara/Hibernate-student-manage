package bitlord.dao.custom.impl;

import bitlord.dao.custom.StudentDao;
import bitlord.entity.Student;
import bitlord.exceptions.NotFoundException;
import bitlord.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public void save(Student student) throws SQLException, ClassNotFoundException {

        try ( Session session = HibernateUtil.getInstance().openSession() )  {
            session.beginTransaction();
            session.save( student );

            session.getTransaction().commit();

        }


    }

    @Override
    public void update(Student student) throws SQLException, ClassNotFoundException {

        try ( Session session = HibernateUtil.getInstance().openSession() ) {

            session.beginTransaction();

            Student seletedStudent = find(student.getId());// select student with data

                    if ( seletedStudent != null ) {

                        seletedStudent.setName( student.getName() );
                        seletedStudent.setContact(student.getContact() );

                        session.update( seletedStudent );

                        session.getTransaction().commit();
                        return;

                    }
                        throw new NotFoundException( "Can't find Data" );
        }

    }

    @Override
    public Student find(Long id) throws SQLException, ClassNotFoundException {

        try ( Session session = HibernateUtil.getInstance().openSession() ) {

            Query<Student> query = session.createQuery( "FROM Student WHERE id=:provideId" , Student.class);

            query.setParameter( "provideId", id );

            return query.uniqueResult();

        }

    }

    @Override
    public void delete(Long id) throws SQLException, ClassNotFoundException {

        try ( Session session = HibernateUtil.getInstance().openSession() ) {

            session.beginTransaction();

                Query query = session.createQuery("DELETE FROM Student WHERE id=:selectedId");
                query.setParameter( "selectedId", id );

            query.executeUpdate();

        }

    }

    @Override
    public List<Student> findAll() throws SQLException, ClassNotFoundException {

        try( Session session = HibernateUtil.getInstance().openSession() ) {

            return session.createQuery("FROM Student", Student.class).list();

        }
    }

}
