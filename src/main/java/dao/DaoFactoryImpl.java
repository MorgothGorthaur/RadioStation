package dao;

public class DaoFactoryImpl implements DaoFactory{
    @Override
    public RadioStationDao getDao(DaoType type, String fileName) {
        return switch (type) {
            case JSON -> new JsonDaoImpl(fileName);
            case STREAM -> new StreamDaoImpl(fileName);
        };
    }
}
