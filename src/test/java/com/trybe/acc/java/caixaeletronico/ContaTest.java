package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste da classe Conta")
class ContaTest {

  @Test
  @DisplayName("5 - Testa o construtor da classe conta.")
  void construtorTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    assertEquals(0, primeiraConta.retornarSaldo());
  }

  @Test
  @DisplayName("6 - Testa o método adicionar transação e retornar saldo da conta.")
  void adicionarTransacaoTestRetornarSaldoTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    primeiraConta.adicionarTransacao(10,"pagamento");
    assertEquals(10, primeiraConta.retornarSaldo());
  }

  @Test
  @DisplayName("7 - Testa o método retornar resumo está retornando uma string com os valores corretamente.")
  void retornarResumoContaTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    primeiraConta.adicionarTransacao(10,"pagamento");
    assertNotEquals(null, primeiraConta.retornarResumoConta());

  }

  @Test
  @DisplayName("8 - Testa o método retornar extrato está imprimindo os valores corretamente.")
  void retornarExtratoTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    primeiraConta.adicionarTransacao(10,"pagamento");
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    primeiraConta.retornarExtrato();

    String output = outputStream.toString();
    String expectedDate =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

    assertTrue(output.contains(expectedDate));

    System.setOut(System.out);

  }

  @Test
  @DisplayName("9 - Testa o método Getter do atributo idConta está retornando.")
  void getIdContaTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    String id = DressedBank.gerarNumeroNovaConta();
    assertEquals( id.length(), primeiraConta.getIdConta().length());
  }

  @Test
  @DisplayName("10 - Testa o método método Getter do atributo pessoaCliente está retornando.")
  void getPessoaClienteTest() {
    PessoaCliente clienteA = new PessoaCliente("Ze", "99999999999", "Sebga");
    Banco DressedBank = new Banco();
    Conta primeiraConta = new Conta("Corrente",clienteA, DressedBank);
    assertSame(clienteA, primeiraConta.getPessoaCliente());
  }
}
