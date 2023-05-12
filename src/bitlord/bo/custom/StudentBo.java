package bitlord.bo.custom;

import bitlord.dto.StudentDto;

import java.sql.SQLException;

public interface StudentBo {

    public void saveStudent ( StudentDto dto ) throws SQLException, ClassNotFoundException;

}
