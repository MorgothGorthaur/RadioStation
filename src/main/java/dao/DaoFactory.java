package dao;

public interface DaoFactory {
    RadioStationDao getDao(DaoType type, String fileName);
    enum DaoType {
        JSON,
        BYTE
    }
}
