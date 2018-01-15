package com.example.cacaniquel;

import java.io.Serializable;

public class Jogador implements Serializable {
	private long lId;
	private String sNome;
	private int iRodadas;
	public long getlId() {
		return lId;
	}
	public void setlId(long lId) {
		this.lId = lId;
	}
	public String getsNome() {
		return sNome;
	}
	public void setsNome(String sNome) {
		this.sNome = sNome;
	}
	public int getiRodadas() {
		return iRodadas;
	}
	public void setiRodadas(int iRodadas) {
		this.iRodadas = iRodadas;
	}
	
	public String toString(){
		return sNome+" - "+iRodadas+" Rodadas";
	}
}
