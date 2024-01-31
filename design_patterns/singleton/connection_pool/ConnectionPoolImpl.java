package design_patterns.singleton.connection_pool;

import java.util.HashMap;
import java.util.Map;

public class ConnectionPoolImpl implements ConnectionPool{

    private HashMap<DatabaseConnection, Boolean> connectionHashMap = new HashMap<>();
    private static ConnectionPool connectionPool = null;
    private int poolSize;
    private int availableConnections = 0;

    private ConnectionPoolImpl(int maxConnections) {
        this.poolSize = maxConnections;
        this.initializePool();
    }

    public static ConnectionPool getInstance(int maxConnections)
    {
        if(connectionPool == null)
        {
            synchronized (ConnectionPoolImpl.class)
            {
                if(connectionPool == null)
                {
                    connectionPool = new ConnectionPoolImpl(maxConnections);
                    }
                }
        }
        return connectionPool;
    }

    public static void resetInstance()
    {
        connectionPool = null;
    }

    @Override
    public void initializePool() {
        for(int i=0; i<this.poolSize; i++)
        {
            connectionHashMap.put(new DatabaseConnection(), true);
            this.availableConnections ++;
        }
    }

    @Override
    public DatabaseConnection getConnection() {
        for(Map.Entry<DatabaseConnection, Boolean> entry: connectionHashMap.entrySet())
        {
            if(entry.getValue())
            {
                connectionHashMap.put(entry.getKey(), false);
                this.availableConnections --;
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public void releaseConnection(DatabaseConnection connection) {
        connectionHashMap.put(connection, true);
        this.availableConnections ++;
    }

    @Override
    public int getAvailableConnectionsCount() {
        return this.availableConnections;
    }

    @Override
    public int getTotalConnectionsCount() {
        return this.poolSize;
    }
}
