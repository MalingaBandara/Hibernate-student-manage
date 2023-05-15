package bitlord.bo.custom;

import bitlord.dto.CreateLaptopDto;
import bitlord.dto.ProgramDto;

import java.sql.SQLException;

public interface ProgramBo {

    public void saveProgram(ProgramDto dto ) throws SQLException, ClassNotFoundException ;

}
