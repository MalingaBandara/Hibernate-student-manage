package bitlord.bo.custom.impl;

import bitlord.bo.custom.StudentBo;
import bitlord.dao.DaoFactory;
import bitlord.dao.custom.StudentDao;
import bitlord.dto.StudentDto;
import bitlord.entity.Student;

import java.sql.SQLException;

public class StudentBoImpl implements StudentBo {

    private final StudentDao studentDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.STUDENT);

    @Override
    public void saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException {

        Student student = new Student();

        student.setName( dto.getName() );
        student.setContact( dto.getContact() );

        studentDao.save( student );

    }

}
