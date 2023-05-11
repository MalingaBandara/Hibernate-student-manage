package bitlord.dao;

import bitlord.dao.custom.impl.StudentImpl;

public class DaoFactory {

    private static  DaoFactory daoFactory;

    private DaoFactory() {}

    public enum DaoType {
        BOOK,STUDENT,PROGRAM,LAPTOP
    }


    public static  DaoFactory getDaoFactory() {

        return daoFactory == null ? daoFactory= new DaoFactory() : daoFactory;

    }

    public <T> T getDao ( DaoType type ) {

        switch ( type ) {

            case STUDENT:
                return (T) new StudentImpl();

            case BOOK:
                return  null;

            case LAPTOP:
                return null;

            case PROGRAM:
                return null;

            default:
                return null;
        }

    }
}
