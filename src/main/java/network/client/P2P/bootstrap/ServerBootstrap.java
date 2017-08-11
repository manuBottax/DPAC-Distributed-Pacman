package network.client.P2P.bootstrap;

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Created by Federica on 21/07/17.
 */
public class ServerBootstrap {

    private String ip;
    private int rmiPort;
    private Registry registry;
    private static ServerBootstrap SINGLETON = null;
    private PortRangeHandler portRangeHandler;

    private  ServerBootstrap() throws UnknownHostException {
        //this.ip = InetAddress.getLocalHost().toString();

    }



    public static ServerBootstrap getIstance(String ip) throws UnknownHostException {
        if(SINGLETON == null){
            SINGLETON = new ServerBootstrap();
            SINGLETON.init(ip);
        }
        return SINGLETON;
    }


    /**
     * this method configures server port on which
     * rmiregisty has to be launched
     */
    //private void configureRmiPort(){
        //int count = 0;
        //int maxTries = 5;
        //while (true) {
        //   try {
        //rmiPort = PortRangeHandler.getPortNumber();

        //        break;
        //    } catch (Exception ex) {
        //        //rmiPort = PortRangeHandler.getNextPortNumber();
        //        if (++count == maxTries) {
        //            throw ex;
        //        }
        //   }
        //}

   // }

    /**
     * this method creates an rmiregistry on a
     * given port
     */
    private void setUpRmiregistry() throws RemoteException{
        try {
            registry = LocateRegistry.createRegistry(rmiPort);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.setProperty("Djava.rmi.server.codebase", "out/");
        System.setProperty("Djava.rmi.server.hostname", ip);

    }

    public Registry getRegistry(){
        return this.registry;
    }

    public int getRmiPort() {
        return this.rmiPort;
    }

    private void init(String ip){
        this.ip = ip;
        this.portRangeHandler = new PortRangeHandler();
        int count = 0;
        int maxTries = 5;

        while (true) {
            //seleziono porta disponibile
            rmiPort = portRangeHandler.getFirstAvailablePortNumber();
            try {
                setUpRmiregistry(); //provo a settare il registro con porta scelta
                break;
            }catch (RemoteException ex) {
                if (++count == maxTries) { //provo per un massimo di 4 tentativi
                    break;
                }
            }
        }

    }
}