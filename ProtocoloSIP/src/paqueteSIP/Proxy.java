package paqueteSIP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class Servidor {

	public Servidor(String puertoescuchaProxy) throws Exception {

		try {
			byte[] buf = new byte[1000];
			DatagramPacket dgp = new DatagramPacket(buf, buf.length);
			DatagramSocket sk;

			sk = new DatagramSocket(Integer.parseInt(puertoescuchaProxy));
			System.out.println("Server started");
			while (true) {
				sk.receive(dgp);
				String rcvd = new String(dgp.getData(), 0, dgp.getLength())
						+ ", from address: " + dgp.getAddress() + ", port: "
						+ dgp.getPort();
				System.out.println(rcvd);

				BufferedReader stdin = new BufferedReader(
						new InputStreamReader(System.in));
				String outMessage = stdin.readLine();
				buf = ("Server say: " + outMessage).getBytes();
				DatagramPacket out = new DatagramPacket(buf, buf.length,
						dgp.getAddress(), dgp.getPort());
				sk. send(out);
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
			System.out.println("Error, vuelva a ejecutarlo metiendo el puerto de escucha del Proxy");
		}
	}

}