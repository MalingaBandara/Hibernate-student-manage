package bitlord.bo.custom.impl;

import bitlord.bo.custom.LaptopBo;
import bitlord.dao.DaoFactory;
import bitlord.dao.custom.LaptopDao;
import bitlord.dto.CreateLaptopDto;
import bitlord.entity.Laptop;

import java.sql.SQLException;

public class LaptopBoImpl implements LaptopBo {

    LaptopDao laptopDao = DaoFactory.getInstance().getDao( DaoFactory.DaoType.LAPTOP );


    @Override
    public void saveLaptop(CreateLaptopDto dto) throws SQLException, ClassNotFoundException {

        Laptop laptop = new Laptop();
        laptop.setBrand( dto.getBrand() );

        laptopDao.saveLaptopWithStudentId( dto.getStudentId(), laptop );

    }

}
