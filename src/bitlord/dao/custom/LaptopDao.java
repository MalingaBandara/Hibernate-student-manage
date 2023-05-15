package bitlord.dao.custom;

import bitlord.dao.CrudDao;
import bitlord.entity.Laptop;

public interface LaptopDao extends CrudDao<Laptop, Long > {

    public void saveLaptopWithStudentId( long studentId, Laptop laptop );

}
