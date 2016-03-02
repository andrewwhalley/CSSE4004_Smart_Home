import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Demo._HelloDisp;
import Ice.Current;

class Server extends Ice.Application
{

	class HelloI extends _HelloDisp
	{
		
		private BufferedReader stdin;
		private String input;
		public HelloI() {
			stdin = new BufferedReader(new InputStreamReader(System.in));
		}
		
		@Override
		public void sayHello(String who, Current __current) {
			System.out.println("Hello world " + who);
		}

		@Override
		public void getInput(Current __current) {
			System.out.println("Hello, please enter something");
			System.out.println();
			try {
				input = stdin.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println("You entered: " + input);
			System.out.println();
			
		}
		
	}
	static public void main(String[] args) {
		Server s = new Server();
		int status = s.main("Server", args);
		System.exit(status);
	}
	
	@Override
	public int run(String[] args) {
		Ice.ObjectAdapter adapter = communicator().createObjectAdapterWithEndpoints(
				"Hello", "tcp -h 127.0.0.1 -p 10000");
		
		adapter.add(new HelloI(), communicator().stringToIdentity("hello"));
		adapter.activate();
		
		communicator().waitForShutdown();
		return 0;
	}
	
	
}