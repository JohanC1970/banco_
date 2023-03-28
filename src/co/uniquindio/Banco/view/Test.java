package co.uniquindio.Banco.view;

import java.util.ArrayList;
import java.util.List;

import co.uniquindio.Banco.model.*;

public class Test {

	public static void main(String args[]) throws Exception{

		List<Cliente>listaClientes = new ArrayList<Cliente>();
		List<Cuenta>listaCuentes = new ArrayList<Cuenta>();

		Banco banco = new Banco("Banco Camilo");

		Cliente cliente = new Cliente("Camilo", "Garcia", "1112728156");
		Cliente cliente1 = new Cliente("Camilo", "Garcia", "111272856");

	}
}
