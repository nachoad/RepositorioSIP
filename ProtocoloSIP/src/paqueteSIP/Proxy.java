package paqueteSIP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

class Servidor {

	public Servidor(String puertoescuchaProxy) throws Exception {

		try {
			//Creamos un nuevo mensajeSIP 
			MensajeOKNOT mensajeResp = new MensajeOKNOT();
			Vector<String> lineas = new Vector<String>(15, 1);
			byte[] buf = new byte[1000];
			DatagramPacket dgp = new DatagramPacket(buf, buf.length);
			DatagramSocket sk = new DatagramSocket(Integer.parseInt(puertoescuchaProxy));
			System.out.println(">>Server started<<\n");
			while (true) {
				sk.receive(dgp);
				String rcvd = new String("--Datagram recived: from address: "
						+ dgp.getAddress() + " & port: " + dgp.getPort() + "\n");
				System.out.println(rcvd);

				String value = new String(dgp.getData(), 0, dgp.getLength());
				StringTokenizer tokens = new StringTokenizer(value, "\n");

				while (tokens.hasMoreTokens()) {
					lineas.add(tokens.nextToken());
					System.out.println(lineas.lastElement());
				}
				System.out.println("\n--primera linea: \""
						+ lineas.firstElement() + "\"");
				String tokenTipoMen[] = lineas.firstElement().split(" ");
				System.out.println("--tipo mensaje: \"" + tokenTipoMen[0]
						+ "\"");
				if (tokenTipoMen[0].equalsIgnoreCase("REGISTER")) {
					System.out.println("It is a REGISTER menssage");
					System.out.println("Registering a new user into the proxy server...");
					//Rellenamos los campos del mensaje respuesta que son iguales	
					ArrayList<String> vias = new ArrayList<String>();
					vias.add(lineas.elementAt(1));
					mensajeResp.setVias(vias);
					mensajeResp.setTo(lineas.elementAt(3));
					mensajeResp.setFrom(lineas.elementAt(4));
					mensajeResp.setCallID(lineas.elementAt(5));
					mensajeResp.setcSeq(lineas.elementAt(6));
					mensajeResp.setContact(lineas.elementAt(7));
					
					//Registrar usuario en una tabla
					//Llamar a clase registrar y valorar lo que devueleve con un IF-ELSE
					if (true)
					{
						//Registrado correctamente
						System.out.println("User registered");
						//Mandar mensaje OK
						System.out.println("Sending a OK menssage to UA...");
						//Construir mensjae OK
						mensajeResp.setTipoOp("SIP/2.0 200 OK");
						
					}
					else{
						//Usuario no encontrado en el dominio
						System.out.println("User not found in this domain");
						System.out.println("Sending a Not Found menssage to UA...");
						mensajeResp.setTipoOp("SIP/2.0 404 Not Found");
					}
					
				}
				else {
					System.out.println("It is NOT a REGISTER menssage");
				}

				/*
				 * //Creamos un nuevo mensajeSIP MensajeSIP mensaje = new MensajeSIP();
				 * 
				 * //Ponemos algunos datos de prueba seteando los valores
				 * mensaje.setTipoOperacion("REGISTER sip:registrar.dominio.com SIP/2.0");
				 * ArrayList<String> viass = new ArrayList<String>();
				 * viass.add("Via: SIP/2.0/UDP nacho.dominio.com:5040");
				 * mensaje.setVias(viass); mensaje.setMax("Max-Forwards: 70");
				 * 
				 * BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in)); String outMessage = stdin.readLine(); 
				 * buf = ("Server say: " + outMessage).getBytes(); 
				 * DatagramPacket out = new DatagramPacket(buf, buf.length, dgp.getAddress(),
				 * dgp.getPort()); sk. send(out);
				 */
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
			System.out
					.println("Error, re-run the app with the listening port of the Proxy");
		}
	}

}