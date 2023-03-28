package co.uniquindio.Banco.model;

import java.util.ArrayList;
import java.util.List;

public class Banco {

	private String nombre;
	private List<Cliente>listaClientes = new ArrayList<Cliente>();
	private List<Cuenta>listaCuentas = new ArrayList<Cuenta>();


	public Banco(String nombre)
	{
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Banco other = (Banco) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Banco [nombre=" + nombre + "]";
	}


	/**
	 * Este metodo tiene como funcion crear clientes
	 * @param nombre
	 * @param apellidos
	 * @param documento
	 * @return
	 * @throws Exception
	 */
	public String crearCliente(String nombre, String apellidos, String documento) throws Exception
	{
		String msj="El cliente ha sido registrado exitosamente";
		boolean clienteEncontrado = verificarCliente(documento);
		if(clienteEncontrado == true)
		{
			throw new Exception("El cliente ya se encuentra registrado");

		}
		else
		{
			Cliente nuevoCliente = new Cliente(nombre,apellidos,documento);
			listaClientes.add(nuevoCliente);
		}

		return msj;
	}

	/**
	 * Este metodo tiene como funcion verificar si un cliente ya esta registrado
	 * @param documento
	 * @return
	 */
	private boolean verificarCliente(String documento) {

		boolean verificado = false;
		for (Cliente cliente : listaClientes) {

			if(cliente.getIdentificacion().equals(documento))
			{
				verificado = true;
				return verificado;
			}
		}
		return verificado;
	}

	/**
	 * Este metodo tiene como funcion obtener el objeto cliente
	 * @param documento
	 * @return
	 */
	public Cliente obtenerCliente(String documento)
	{
		Cliente clienteEncontrado = null;
		for (Cliente cliente : listaClientes) {
			if(cliente.getIdentificacion().equals(documento))
			{
				clienteEncontrado = cliente;
				return clienteEncontrado;
			}
		}
		return clienteEncontrado;
	}

	/**
	 * Este metodo elimina clientes
	 * @param documento
	 * @throws Exception
	 */
	public void eliminarCliente(String documento) throws Exception
	{
		Cliente cliente = obtenerCliente(documento);
		if(cliente == null)
		{
			throw new Exception("El cliente no existe");
		}
		listaClientes.remove(cliente);
	}

	//------------------------Cuenta----------------------------------------------

	public String crearCuentaBancaria(Cliente cliente, Tipo tipo) throws Exception{
		boolean existenciaCuenta = verificarCuenta(cliente.getIdentificacion());
		if(existenciaCuenta == true){
			throw new Exception("La cuenta no se pudo crear exitosamente");
		}
		Cuenta cuenta = new Cuenta(0.0,cliente.getIdentificacion(),tipo,cliente);
		listaCuentas.add(cuenta);
		return "La cuenta ha sido registrada exitosamente";
	}

	private boolean verificarCuenta(String identificacion) {

		for (Cuenta cuenta : listaCuentas) {
			if(cuenta.getNumeroCuenta().equals(identificacion)){
				return true;
			}
		}
		return false;
	}

	public Cuenta obtenerCuenta(String numeroCuenta){
		Cuenta cuentaEncontrada = null;
		for (Cuenta cuenta : listaCuentas) {
			if(cuenta.getNumeroCuenta().equals(numeroCuenta)){
				cuentaEncontrada = cuenta;
				return cuentaEncontrada;
			}
		}
		return cuentaEncontrada;
	}

	public void eliminarCuenta(String numeroCuenta) throws Exception{
		Cuenta cuentaObtenida = obtenerCuenta(numeroCuenta);
		if(cuentaObtenida == null){
			throw new Exception("La cuenta no existe");
		}
		listaCuentas.remove(cuentaObtenida);
	}

	/**
	 * Me retorna los datos de la cuenta
	 * @param numeroCuenta
	 * @return
	 * @throws Exception
	 */
	public String darValoresCuenta(String numeroCuenta) throws Exception{
		Cuenta cuentaObtenida = obtenerCuenta(numeroCuenta);
		if(cuentaObtenida == null){
			throw new Exception("La cuenta no existe");
		}

		return cuentaObtenida.toString();
	}

	/**
	 * Esta funcion me consulta el saldo de una cuenta
	 * @param numeroCuenta
	 * @return
	 * @throws Exception
	 */
	public double consultarSaldoCuenta(String numeroCuenta) throws Exception{
		Cuenta cuenta = obtenerCuenta(numeroCuenta);
		if(cuenta == null){
			throw new Exception("La cuenta no existe");
		}
		return cuenta.getSaldo();
	}

	/**
	 * Esta funcion sirve para realizar una consignacion a una cuenta bancaria
	 * @param valorAConsignar
	 * @param numeroCuenta
	 * @return
	 * @throws Exception
	 */
	public String realizarConsignacion(double valorAConsignar, String numeroCuenta) throws Exception {
		Cuenta cuentaEncontrada = obtenerCuenta(numeroCuenta);
		if(cuentaEncontrada == null) {
			throw new Exception("El numero de cuenta ingresado no pertenece a ninguna cuenta");
		}

		double nuevoSaldo = cuentaEncontrada.getSaldo()+ valorAConsignar;
		cuentaEncontrada.setSaldo(nuevoSaldo);
		return "Se ha realizado la consignacion correctamente";
	}

	/**
	 * Esta funcion sirve para hacer el retiro de un determinado valor de la cuenta
	 * @param valorARetirar
	 * @param numeroCuenta
	 * @return
	 * @throws Exception
	 */
	public String realizarRetiroCuenta(double valorARetirar, String numeroCuenta) throws Exception {
		Cuenta cuentaEncontrada = obtenerCuenta(numeroCuenta);
		if(cuentaEncontrada == null) {
			throw new Exception("El numero de cuenta ingresado no pertenece a ninguna cuenta");
		}

		if(cuentaEncontrada.validarPosibilidadRetiro(valorARetirar)) {
			cuentaEncontrada.setSaldo(cuentaEncontrada.getSaldo() - valorARetirar);
			return "Se realizo el retiro exitosamente";
		}

		throw new Exception("La cuenta no tiene suficientes fondos para realizar el retiro");
	}

	/**
	 * Esta funcion me compara 2 cuentas y me dice cual tiene mas saldo, o si ambas tienen el mismo saldo
	 * @param cuenta
	 * @param cuenta2
	 * @return
	 */
	public String isCuentaMayorSaldo(Cuenta cuenta, Cuenta cuenta2) {

		if(cuenta.realizarComparacionCuentas(cuenta2)) {
			return "La cuenta numero 1 tiene mayor saldo";
		}

		if(cuenta.getSaldo().equals(cuenta2.getSaldo())) {
			return "El saldo de las 2 cuentas es el mismo";
		}
		return "La cuenta numero 2 tiene mayor saldo";
	}

	/**
	 * Esta funcion me realiza una consignacion de una cuenta a otra
	 * @param cuenta1
	 * @param cuenta2
	 * @param valorAConsignar
	 * @return
	 * @throws Exception
	 */
	public String realizarConsignacion(Cuenta cuenta1, Cuenta cuenta2, double valorAConsignar) throws Exception {

		if(cuenta1.realizarConsignacion(valorAConsignar, cuenta2)) {
			return "Se realizo el retiro correctamente";
		}

		throw new Exception("La consignacion no se pudo hacer");
	}

}
