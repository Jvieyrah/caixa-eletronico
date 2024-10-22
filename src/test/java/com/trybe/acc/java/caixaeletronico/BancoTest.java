package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes para a classe Banco")
class BancoTest {

  @Test
  @DisplayName("20 - Testa o gerador de número único para nova conta.")
  void gerarNumeroNovaContaTest() {
    Banco DressedBank = new Banco();
    String id = DressedBank.gerarNumeroNovaConta();
    String id2 = DressedBank.gerarNumeroNovaConta();
    assertEquals(10, id.length());
    assertNotSame(id, id2);
  }

  @Test
  @DisplayName("21 - Testa o método adicionar pessoa cliente retorna o objeto pessoa cliente.")
  void adicionarPessoaClienteTest() {
    Banco banco = new Banco();
    String nome = "personName";
    String cpf = "123.456.789-00";
    String senha = "password";

    PessoaCliente pessoaCliente = banco.adicionarPessoaCliente(nome, cpf, senha);

    assertEquals(cpf, pessoaCliente.getCpf());
    assertTrue(pessoaCliente.validarSenha(senha));

    boolean cadastradoComSucesso = banco.getPessoasClientes().stream()
        .anyMatch(c -> cpf.equals(c.getCpf()) && c.validarSenha(senha));

    assertTrue(cadastradoComSucesso);
  }

  @Test
  @DisplayName("22 - Testa o método login da pessoa cliente retorna o objeto pessoa cliente corretamente.")
  void pessoaClienteLoginTest() {
    String cpf = "123.456.789-00";
    String senha = "password";
    Banco dressedBank = new Banco();
    PessoaCliente pessoaCliente = dressedBank.adicionarPessoaCliente("Ze", cpf, senha);
    PessoaCliente retornado = dressedBank.pessoaClienteLogin(cpf, senha);
    assertSame(pessoaCliente,retornado);
  }

  @Test
  @DisplayName("23 - Testa se o método transferir fundos está transferindo corretamente.")
  void depositarTestTransferirFundosTestmostrarExtratoTest() {
    Banco dressedBank = new Banco();
    PessoaCliente pessoaClienteA = dressedBank.adicionarPessoaCliente("Ze", "99999999999", "Sebga");
    dressedBank.adicionarConta("corrente", pessoaClienteA);
    dressedBank.depositar(pessoaClienteA, 0, 10 );
    dressedBank.adicionarConta("corrente", pessoaClienteA);
    dressedBank.transferirFundos(pessoaClienteA, 0,1,10);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    dressedBank.mostrarExtrato(pessoaClienteA,1);

    String output = outputStream.toString();
    String expectedValue = "10";

    assertTrue(output.contains(expectedValue));

    System.setOut(System.out);
  }

  @Test
  @DisplayName("24 - Testa se o método sacar está funcionando corretamente.")
  void depositarTestSacarTestMostrarExtratoTest() {
    Banco dressedBank = new Banco();
    PessoaCliente pessoaClienteA = dressedBank.adicionarPessoaCliente("Ze", "99999999999", "Sebga");
    dressedBank.adicionarConta("corrente", pessoaClienteA);
    dressedBank.depositar(pessoaClienteA, 0, 10 );
    dressedBank.sacar(pessoaClienteA, 0, 5 );

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    dressedBank.mostrarExtrato(pessoaClienteA,0);

    String output = outputStream.toString();
    String expectedValue = "5";

    assertTrue(output.contains(expectedValue));

    System.setOut(System.out);
  }
}
