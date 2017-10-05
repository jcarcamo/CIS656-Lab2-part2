package server.service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import server.shared.PresenceService;
import server.shared.RegistrationInfo;

public class PresenceServiceImpl implements PresenceService {

	private Vector<RegistrationInfo> registeredUsers = new  Vector<>();
	
	@Override
	public boolean register(RegistrationInfo reg) throws RemoteException {
		boolean success = false;
		if(!registeredUsers.contains(reg)){
			registeredUsers.add(reg);
			success = true;
		}
		return success;
	}

	@Override
	public boolean updateRegistrationInfo(RegistrationInfo reg) throws RemoteException {
		boolean success = false;
		int regIndex = registeredUsers.indexOf(reg);
		if(regIndex > -1){
			registeredUsers.set(regIndex, reg);
			success = true;
		}
		return success;
	}

	@Override
	public void unregister(String userName) throws RemoteException {
		RegistrationInfo reg = new RegistrationInfo(userName, userName, 0, false);
		int regIndex = registeredUsers.indexOf(reg);
		if(regIndex > -1){
			registeredUsers.remove(regIndex);
		}
	}

	@Override
	public RegistrationInfo lookup(String name) throws RemoteException {
		RegistrationInfo reg = new RegistrationInfo(name, "", 0, false);
		int regIndex = registeredUsers.indexOf(reg);
		if(regIndex > -1){
			reg = registeredUsers.get(regIndex);
		}else{
			reg = null;
		}
		return reg;
		
	}

	@Override
	public Vector<RegistrationInfo> listRegisteredUsers() throws RemoteException {
		return registeredUsers;
	}
	
	public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "PresenceService";
            PresenceService presenceService = new PresenceServiceImpl();
            PresenceService stub =
                (PresenceService) UnicastRemoteObject.exportObject(presenceService, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("PresenceService bound");
        } catch (Exception e) {
            System.err.println("PresenceService exception:");
            e.printStackTrace();
        }
    }

}
