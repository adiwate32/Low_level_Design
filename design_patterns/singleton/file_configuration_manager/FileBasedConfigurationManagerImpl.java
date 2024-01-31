package design_patterns.singleton.file_configuration_manager;

public class FileBasedConfigurationManagerImpl extends FileBasedConfigurationManager {

    private static FileBasedConfigurationManager configurationManager = null;
    private FileBasedConfigurationManagerImpl() {
        super();
    }

    @Override
    public String getConfiguration(String key) {
        // TODO Auto-generated method stub
        try{
            return this.properties.getProperty(key);
        }
        catch (Exception ex) {
            throw new UnsupportedOperationException("Unimplemented method 'getConfiguration'");
        }
    }

    @Override
    public <T> T getConfiguration(String key, Class<T> type) {
        // TODO Auto-generated method stub
        try
        {
            return this.convert(this.properties.getProperty(key), type);
        }
        catch (Exception ex)
        {
            throw new UnsupportedOperationException("Unimplemented method 'getConfiguration'");
        }
    }

    @Override
    public void setConfiguration(String key, String value) {
        // TODO Auto-generated method stub
        try
        {
            this.properties.setProperty(key, value);
        }
        catch (Exception ex)
        {
            throw new UnsupportedOperationException("Unimplemented method 'setConfiguration'");
        }
    }

    @Override
    public <T> void setConfiguration(String key, T value) {
        // TODO Auto-generated method stub
        try
        {
            if(value != null)
            {
                this.properties.setProperty(key, value.toString());
            }
            else {
                removeConfiguration(key);
            }
        }
        catch (Exception ex)
        {
            throw new UnsupportedOperationException("Unimplemented method 'setConfiguration'");
        }
    }

    @Override
    public void removeConfiguration(String key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeConfiguration'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    public static FileBasedConfigurationManager getInstance() {
        if(configurationManager == null)
        {
            synchronized (FileBasedConfigurationManagerImpl.class)
            {
                if(configurationManager == null) return new FileBasedConfigurationManagerImpl();
            }
        }
        return configurationManager;
    }

    public static void resetInstance() {
        // TODO Auto-generated method stub
        configurationManager = null;
    }

}
