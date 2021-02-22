package com.nowe.modelo;

public class Cuenta {

	private int idcuenta;
	private double saldo;
	private String nif;
	private EstadoCuenta estado;
	
	public Cuenta() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idcuenta
	 * @param saldo
	 * @param nif
	 * @param estado
	 */
	public Cuenta(int idcuenta, double saldo, String nif, EstadoCuenta estado) {
		super();
		this.idcuenta = idcuenta;
		this.saldo = saldo;
		this.nif = nif;
		this.estado = estado;
	}

	/**
	 * @return the idcuenta
	 */
	public int getIdcuenta() {
		return idcuenta;
	}

	/**
	 * @param idcuenta the idcuenta to set
	 */
	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * @param nif the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * @return the estado
	 */
	public EstadoCuenta getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoCuenta estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [idcuenta=" + idcuenta + ", saldo=" + saldo + ", nif=" + nif + ", estado=" + estado + "]";
	}
	
	
	
}
