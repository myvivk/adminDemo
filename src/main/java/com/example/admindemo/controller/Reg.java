package com.example.admindemo.controller;

import javax.naming.NamingException;
import javax.naming.Reference;
import com.sun.jndi.rmi.registry.ReferenceWrapper;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Reg {
    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        Reference reference = new Reference("Explat", "Explat", "http://127.0.0.1:80/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("obj", referenceWrapper);
        System.out.println("registry is runing....");
    }
}
