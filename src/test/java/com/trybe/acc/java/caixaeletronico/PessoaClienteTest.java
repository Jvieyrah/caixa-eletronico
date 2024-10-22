package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste dos métodos da classe PessoaCliente")
class PessoaClienteTest {

  @Test
  @DisplayName("11 - Testa o construtor da classe Pessoa Cliente.")
  void construtorTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    assertTrue(clienteA.validarSenha("Sebga"));
  }

  @Test
  @DisplayName("12 - Testa o método adicionar conta e o método retornar número de contas.")
  void adicionarContaTestRetornaNumeroDeContasTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    clienteA.adicionarConta(primeiraConta);
    assertEquals(1, clienteA.retornaNumeroDeContas());
  }

  @Test
  @DisplayName("13 - Testa o método retornar saldo de uma conta específica da pessoa cliente.")
  void retornarSaldoContaEspecificaTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    primeiraConta.adicionarTransacao(10,"pagamento");
    clienteA.adicionarConta(primeiraConta);
    assertEquals(10, clienteA.retornarSaldoContaEspecifica(0));
  }


  @Test
  @DisplayName("14 - Testa o método retornar id de uma conta específica da pessoa cliente.")
  void retornarIdContaEspecificaTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    clienteA.adicionarConta(primeiraConta);
    assertEquals(primeiraConta.getIdConta(),
        clienteA.retornarIdContaEspecifica(0));
  }

  @Test
  @DisplayName("15 - Testa o método retornar o extrato de uma conta específica da pessoa cliente.")
  void retornarExtratoContaEspecificaTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    clienteA.adicionarConta(primeiraConta);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);

    clienteA.retornarExtratoContaEspecifica(0);

    String[] linha = baos.toString().split(System.lineSeparator());
    String saida = String.join(",", linha);

    assertNotEquals(null, saida);
  }

  @Test
  @DisplayName("16 - Testa o método adiciona transação de uma conta específica da pessoa cliente.")
  void adicionarTransacaoContaEspecificaTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    clienteA.adicionarConta(primeiraConta);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    clienteA.adicionarTransacaoContaEspecifica(0, 10,"pagamento");
    clienteA.retornarExtratoContaEspecifica(0);
    String[] linha = baos.toString().split(System.lineSeparator());
    String saida = String.join(",", linha);
    assertTrue(saida.contains("10"));
  }

  @Test
  @DisplayName("17 - Testa o método validar senha.")
  void validarSenhaTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    assertTrue(clienteA.validarSenha("Sebga"));
  }

  @Test
  @DisplayName("18 - Testa o método retornar resumo contas.")
  void retornarResumoContasTest() {
    Banco banco = new Banco();
    PessoaCliente pessoaCliente = new PessoaCliente("Camaragibe Silva", "433.892.200-11", "1234");
    Conta conta = new Conta("Poupança", pessoaCliente, banco);
    pessoaCliente.adicionarConta(conta);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);

    pessoaCliente.retornarResumoContas();

    String[] linha = baos.toString().split(System.lineSeparator());
    String saida = String.join(",", linha);

    assertTrue(saida.contains("0"));

  }

  @Test
  @DisplayName("19 - Testa o método Getter do atributo cpf está retornando.")
  void getCpfTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    assertEquals("99999999999", clienteA.getCpf());
  }
}
