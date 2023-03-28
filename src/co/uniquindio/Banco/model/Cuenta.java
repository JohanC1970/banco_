package co.uniquindio.Banco.model;

public class Cuenta {

	private Double saldo;
	private String numeroCuenta;
	private Tipo tipoCuenta;
	private Cliente clienteTitular;

	public Cuenta(Double saldo, String numeroCuenta, Tipo tipoCuenta, Cliente clienteTitular) {
		super();
		this.saldo = saldo;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.clienteTitular = clienteTitular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Tipo getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(Tipo tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Cliente getClienteTitular() {
		return clienteTitular;
	}

	public void setClienteTitular(Cliente clienteTitular) {
		this.clienteTitular = clienteTitular;
	}

	@Override
	public String toString() {
		return "Cuenta [saldo=" + saldo + ", numeroCuenta=" + numeroCuenta + ", tipoCuenta=" + tipoCuenta
				+ ", clienteTitular=" + clienteTitular + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteTitular == null) ? 0 : clienteTitular.hashCode());
		result = prime * result + ((numeroCuenta == null) ? 0 : numeroCuenta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		if (clienteTitular == null) {
			if (other.clienteTitular != null)
				return false;
		} else if (!clienteTitular.equals(other.clienteTitular))
			return false;
		if (numeroCuenta == null) {
			if (other.numeroCuenta != null)
				return false;
		} else if (!numeroCuenta.equals(other.numeroCuenta))
			return false;
		return true;
	}

	/**
	 * Esta funcion me compara el saldo de dos cuentas
	 * @param cuenta
	 * @return
	 */
	public boolean realizarComparacionCuentas(Cuenta cuenta) {
		return this.saldo>cuenta.getSaldo();
	}

	/**
	 * Esta funcion verifica si la cuenta tiene los fondos suficientes para hacer un retiro
	 * @param valor
	 * @return
	 */
	public boolean validarPosibilidadRetiro(double valor) {
		return this.saldo>=valor;
	}

	/**
	 * Esta funcion valida si es posible realizar una consignacion, si la consignacion es posible la hace y retorna un true
	 * @param valor
	 * @param cuenta
	 * @return
	 */
	public boolean realizarConsignacion(double valor, Cuenta cuenta) {
		if(this.validarPosibilidadRetiro(valor)) {
			this.setSaldo(saldo-valor);
			cuenta.setSaldo(saldo+valor);
			return true;
		}
		return false;
	}
}
