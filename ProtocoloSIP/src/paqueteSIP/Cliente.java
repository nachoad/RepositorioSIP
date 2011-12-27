package paqueteSIP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

	public Cliente(String usuarioSIP, String puertoescuchaUA, String IPProxy, String puertoescuchaProxy) throws IOException {
		InetAddress proxyAddress = InetAddress.getByName(IPProxy);
		DatagramSocket s = new DatagramSocket(Integer.parseInt(puertoescuchaUA));
		byte[] buf = new byte[1000];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);

		while (true) {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));
			String outMessage = stdin.readLine();

			if (outMessage.equals("bye"))
				break;
			String outString = "Client say: " + outMessage;
			buf = outString.getBytes();

			DatagramPacket out = new DatagramPacket(buf, buf.length, proxyAddress, Integer.parseInt(puertoescuchaProxy));
			s.send(out);

			s.receive(dp);
			String rcvd = "rcvd from " + dp.getAddress() + ", " + dp.getPort()
					+ ": " + new String(dp.getData(), 0, dp.getLength());
			System.out.println(rcvd);
		}

	}

	public static void main(String[] args) {
		try {
			String usuarioSIP = args[0];
			String puertoescuchaUA = args[1];
			String IPProxy = args[2];
			String puertoescuchaProxy = args[3];
			new Cliente(usuarioSIP, puertoescuchaUA, IPProxy,
					puertoescuchaProxy);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out
					.println("Error, vuelva a ejecutarlo metiendo el puerto de escucha del Proxy");
		}
	}
}