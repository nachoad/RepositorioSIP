package paqueteSIP;

import java.util.ArrayList;

public class MensajeOKNOT {

	private String tipoOp;
	private ArrayList<String> vias;
	private String to;
	private String from;
	private String callID;
	private String cSeq;
	private String contact;
	private String expires;
	private String length;

	public byte[] toByteArray() {
		return toString().getBytes();
	}

	public String toString() {
		return tipoOp + "\n" + vias + "\n" + to + "\n" + from
				+ "\n" + callID + "\n" + cSeq + "\n" + contact + "\n" + expires
				+ "\n" + length;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}

	public ArrayList<String> getVias() {
		return vias;
	}

	public void setVias(ArrayList<String> vias) {
		this.vias = vias;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getCallID() {
		return callID;
	}

	public void setCallID(String callID) {
		this.callID = callID;
	}

	public String getcSeq() {
		return cSeq;
	}

	public void setcSeq(String cSeq) {
		this.cSeq = cSeq;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

}