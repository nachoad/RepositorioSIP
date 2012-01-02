package paqueteSIP;

import java.util.ArrayList;

public class MensajeSIP {

	private String tipoOperacion;
	private ArrayList<String> vias;
	private String max;

	public byte[] toByteArray() {
		return toString().getBytes();
	}

	public String toString() {
		return tipoOperacion + "\n" + vias + "\n" + max;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public ArrayList<String> getVias() {
		return vias;
	}

	public void setVias(ArrayList<String> vias) {
		this.vias = vias;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

}
