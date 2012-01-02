package paqueteSIP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;
import java.util.Vector;

class Servidor {

	public Servidor(String puertoescuchaProxy) throws Exception {

		try {
			Vector<String> lineas = new Vector<String>(15,1);
			byte[] buf = new byte[1000];
			DatagramPacket dgp = new DatagramPacket(buf, buf.length);
			DatagramSocket sk = new DatagramSocket(Integer.parseInt(puertoescuchaProxy));
			System.out.println(">>Server started<<\n");
			while (true) {
				sk.receive(dgp);
				String rcvd = new String("--Datagram recived: from address: " + dgp.getAddress() + " & port: " + dgp.getPort() + "\n");
				System.out.println(rcvd);
				
				String value = new String(dgp.getData(), 0, dgp.getLength());
				StringTokenizer tokens = new StringTokenizer(value, "\n");

				while (tokens.hasMoreTokens()) {
					lineas.add(tokens.nextToken());
					System.out.println(lineas.lastElement());
				}
				System.out.println("\n--primera l�nea: \"" + lineas.firstElement() + "\"");
				String tokenTipoMen[] = lineas.firstElement().split(" ");
				System.out.println("--tipo mensaje: \"" + tokenTipoMen[0] + "\"");
				if (tokenTipoMen[0].equalsIgnoreCase("REGISTER")){
					System.out.println("It is a REGISTER menssage");
				}
				
				
				/*//Creamos un nuevo mensajeSIP
				MensajeSIP mensaje = new MensajeSIP();
				
				//Ponemos algunos datos de prueba seteando los valores
				mensaje.setTipoOperacion("REGISTER sip:registrar.dominio.com SIP/2.0");
				ArrayList<String> viass = new ArrayList<String>();
				viass.add("Via: SIP/2.0/UDP nacho.dominio.com:5040"); mensaje.setVias(viass);
				mensaje.setMax("Max-Forwards: 70");

				BufferedReader stdin = new BufferedReader(
						new InputStreamReader(System.in));
				String outMessage = stdin.readLine();
				buf = ("Server say: " + outMessage).getBytes();
				DatagramPacket out = new DatagramPacket(buf, buf.length,
						dgp.getAddress(), dgp.getPort());
				sk. send(out);*/
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {

			String puertoescuchaProxy = args[0];
			new Servidor(puertoescuchaProxy);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error, re-run the app with the listening port of the Proxy");
		}
	}

}