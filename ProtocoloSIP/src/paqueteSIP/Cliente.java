package paqueteSIP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Cliente {

	public Cliente(String usuarioSIP, String puertoescuchaUA, String IPProxy, String puertoescuchaProxy) throws IOException {
		//Creamos un DatagramSocket
		InetAddress proxyAddress = InetAddress.getByName(IPProxy);
		DatagramSocket s = new DatagramSocket(Integer.parseInt(puertoescuchaUA));
		System.out.println(">>UA started<<\n");
		
		//Creamos un nuevo mensajeSIP
		MensajeSIP mensaje = new MensajeSIP();
		
		//Ponemos algunos datos de prueba seteando los valores
		mensaje.setTipoOperacion("REGISTER sip:registrar.dominio.com SIP/2.0");
		ArrayList<String> viass = new ArrayList<String>();
		viass.add("Via: SIP/2.0/UDP nacho.dominio.com:5040"); mensaje.setVias(viass);
		mensaje.setMax("Max-Forwards: 70");

		//Pasamos el mensaje a un ByteArray
		byte[] mensaByteArray = mensaje.toByteArray();
		
		//Una vez tenemos el mensaje en Bytes, creamos un nuevo DatagramPacket
		//DatagramPacket dp = new DatagramPacket(mensaByteArray, mensaByteArray.length);

		do {
			DatagramPacket out = new DatagramPacket(mensaByteArray, mensaByteArray.length, proxyAddress, Integer.parseInt(puertoescuchaProxy));
			s.send(out);

			//s.receive(dp);
			//String rcvd = "rcvd from " + dp.getAddress() + ", " + dp.getPort() + ": " + new String(dp.getData(), 0, dp.getLength());
			//System.out.println(rcvd);
		} while (false); //De momento lo hacemos solo una vez para probar

	}

	public static void main(String[] args) {
		try {
			String usuarioSIP = args[0];
			String puertoescuchaUA = args[1];
			String IPProxy = args[2];
			String puertoescuchaProxy = args[3];
			new Cliente(usuarioSIP, puertoescuchaUA, IPProxy, puertoescuchaProxy);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error, re-run the app with the following parameters in order:\n");
			System.out.println("usuarioSIP puertoescuchaUA IPProxy puertoescuchaProxy");
		}
	}
}