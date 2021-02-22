package com.nowe.modelo;

public enum EstadoCuenta
{
	A("A"),
	C("C");
	
	private final String codigo;
	
	private EstadoCuenta(String codigo)
	{
		this.codigo = codigo;
	}
	
	public String getCodigo()
	{
		return codigo;
	}	
}
