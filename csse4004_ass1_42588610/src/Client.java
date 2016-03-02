import Assignment.SmartHomeInterfacePrx;
import Assignment.SmartHomeInterfacePrxHelper;
import Demo.HelloPrx;
import Demo.HelloPrxHelper;

class Client extends Ice.Application {


	static public void main(String[] args) {
		Client s = new Client();
		int status = s.main("Client", args);
		System.exit(status);
	}

	@Override
	public int run(String[] args) {
		Ice.ObjectPrx obj = communicator().stringToProxy(
				"ui:tcp -h 127.0.0.1 -p 10000");
		SmartHomeInterfacePrx hello = SmartHomeInterfacePrxHelper.uncheckedCast(obj);
//		hello.sayHello("Mad Client");
		hello.askUser();
		return 0;
	}

}
