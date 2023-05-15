package bitlord.bo.custom;

import bitlord.dto.CreateLaptopDto;

import java.sql.SQLException;

public interface LaptopBo {

    public void saveLaptop(CreateLaptopDto dto ) throws SQLException, ClassNotFoundException ;

}
