package com.ti2cc;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		DAO dao = new DAO();

		dao.conectar();

		Scanner entrada = new Scanner(System.in);

		int escolha = 0;

		Conta contas[] = dao.getContas();

		while (escolha != 5) {

			// Escolhendo a acao
			System.out.println("1. Listar");
			System.out.println("2. Inserir");
			System.out.println("3. Excluir");
			System.out.println("4. Atualizar");
			System.out.println("5. Sair");
			System.out.println("Digite o numero da escolha que deseja fazer: ");
			escolha = entrada.nextInt();

			// Clear buffer improvisado
			entrada.nextLine();

			if (escolha > 5 || escolha < 1) {
				System.out.println("Numero invalido!");
				break;
			}

			switch (escolha) {

			case 1:
				// Mostrar usuarios
				System.out.println("==== Mostrar Contas === ");
				for (int i = 0; i < contas.length; i++) {
					System.out.println(contas[i].toString());
				}
				break;

			case 2:
				// Inserir um elemento na tabela
				System.out.println("Digite o nome do dono da Conta: ");
				String dono = entrada.nextLine();

				System.out.println("Digite o Saldo da Conta: ");
				double saldo = entrada.nextDouble();

				System.out.println("Digite o Limite da Conta: ");
				double limite = entrada.nextDouble();

				Conta conta = new Conta((contas.length + 1), dono, saldo, limite);
				if (dao.inserirConta(conta) == true) {
					System.out.println("Insercao com sucesso -> " + conta.toString());
					contas = dao.getContas();
				}
				break;

			case 3:
				// Excluir usuario
				System.out.println("Digite a Conta que deseja excluir: ");
				int e = (entrada.nextInt() - 1);

				if (dao.excluirConta(contas[e].getNumero()) == true) {
					System.out.println("Excluido com sucesso -> " + contas[e].toString());
					contas = dao.getContas();
				}
				break;

			case 4:
				// Atualizar usuario
				System.out.println("Digite a Conta que deseja atualizar: ");
				int a = (entrada.nextInt() - 1);

				System.out.println("Escolha se deseja sacar(1) ou depositar(2): ");
				int escolhaA = entrada.nextInt();

				switch (escolhaA) {
				case 1:
					System.out.println("Escolha quanto deseja sacar: ");
					double saque = entrada.nextInt();

					if (contas[a].saca(saque) == false) {
						System.out.println("Valor escolhido maior que saldo disponivel");
						break;
					} else {
						if (dao.atualizarConta(contas[a]) == true) {
							System.out.println("Atualizado com sucesso -> " + contas[a].toString());
							contas = dao.getContas();
						}
						break;
					}

				case 2:
					System.out.println("Escolha quanto deseja depositar: ");
					double deposito = entrada.nextInt();

					contas[a].deposita(deposito);
					if (dao.atualizarConta(contas[a]) == true) {
						System.out.println("Atualizado com sucesso -> " + contas[a].toString());
						contas = dao.getContas();
					}
					break;
				}
				break;

			case 5:
				// Sai do programa
				System.out.println("Tchau tchau!");
				break;

			default:
				// Qualquer case diferente de 1 a 5
				System.out.println("Numero invalido!");
				break;

			}
		}
		dao.close();
		entrada.close();
	}
}