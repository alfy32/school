package org.apache.ws.axis2;

import java.rmi.RemoteException;

import org.apache.ws.axis2.OperatorClassStub.Add;

public class TestClient {

	public static void main(String[] args) throws RemoteException 
	{
		OperatorClassStub stub = new OperatorClassStub();
		Add add0 = new Add();
		
		add0.setA(10);
		add0.setB(12);
		System.out.println("Add(10, 12) = " + stub.add(add0).get_return());
	}

}
