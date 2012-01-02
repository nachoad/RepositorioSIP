package paqueteSIP;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Pruebas {
	/*
	 * This method converts a String to an array of bytes
	 */
	public void set() {
		MensajeSIP paquete = new MensajeSIP();
		paquete.setTipoOperacion("tipoOperacion");

		ArrayList<String> viass = new ArrayList<String>();
		viass.add("hola");
		viass.add("que");
		viass.add("tal");
		paquete.setVias(viass);
		paquete.setMax("Max forwards");

		byte[] theByteArray = paquete.toByteArray();
		// esto se manda por el socket

		// lo recibo en el proxy por ejemplo
		String value = new String(theByteArray);
		// en value tengo todo el flujo en forma de string

		StringTokenizer tokens = new StringTokenizer(value, "\n");

		while (tokens.hasMoreTokens()) {
			System.out.println(tokens.nextToken());
		}

		// setear valores del toquen en el paqueteRespuesta
		// accedo a la variable así
		// paquete.getTipoOperacion();
		//

	}

	public static void main(String[] args) {
		new Pruebas().set();
	}
}