package it.polito.tdp.extflightdelays.model;

public class Tratta {
	
	private int a1;
	private int a2;
	private double distanzaMedia;
	
	public Tratta(int a1, int a2, double distanzaMedia) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.distanzaMedia = distanzaMedia;
	}

	public int getA1() {
		return a1;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public int getA2() {
		return a2;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public double getDistanzaMedia() {
		return distanzaMedia;
	}

	public void setDistanzaMedia(double distanzaMedia) {
		this.distanzaMedia = distanzaMedia;
	}

	@Override
	public String toString() {
		return "Tratta [a1=" + a1 + ", a2=" + a2 + ", distanzaMedia=" + distanzaMedia + "]";
	}

	
	
}
