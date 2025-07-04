package br.com.caelum.stella.boleto;

import br.com.caelum.stella.boleto.bancos.GeradorDeLinhaDigitavel;
import br.com.caelum.stella.boleto.exception.CriacaoBoletoException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static br.com.caelum.stella.boleto.utils.StellaStringUtils.leftPadWithZeros;

/**
 * Bean que representa os dados de um boleto.
 *
 * @author Paulo Silveira
 * @author Caue Guerra
 */
public class Boleto implements Serializable {

  private static final long serialVersionUID = 1L;

  protected BigDecimal valorBoleto = BigDecimal.ZERO;
  protected BigDecimal quantidadeMoeda;
  protected BigDecimal valorMoeda;
  protected BigDecimal valorDescontos = BigDecimal.ZERO;
  protected BigDecimal valorDeducoes = BigDecimal.ZERO;
  protected BigDecimal valorMulta = BigDecimal.ZERO;
  protected BigDecimal valorAcrescimos = BigDecimal.ZERO;

  protected String especieMoeda;
  protected int codigoEspecieMoeda;
  protected String especieDocumento;
  protected String numeroDocumento;
  protected boolean aceite;
  protected Banco banco;
  protected Datas datas;
  protected Pagador pagador;
  protected Beneficiario beneficiario;
  protected List<String> instrucoes = Collections.emptyList();
  protected List<String> descricoes = Collections.emptyList();
  protected List<String> locaisDePagamento = Collections.emptyList();
  protected String linhaDigitavel;
  protected String codigoBarras;
  protected String nossoNumeroECodDocumento;
  protected String destacarBoletoST;
  protected String cip;

  protected Boleto() {
  }

  /**
   * @return novo Boleto com valores default de especieMoeda R$,
   * código de espécie moeda 9 (real), aceite false e espécie DM
   */
  public static Boleto novoBoleto() {
    return new Boleto().comEspecieMoeda("R$")
                       .comCodigoEspecieMoeda(9)
                       .comAceite(false).comEspecieDocumento("DM");
  }

  /**
   * @return aceite do boleto que por default sempre é false
   */
  public boolean getAceite() {
    return this.aceite;
  }

  /**
   * @param aceite que será associado ao boleto
   * @return este boleto
   */
  public Boleto comAceite(boolean aceite) {
    this.aceite = aceite;
    return this;
  }

  /**
   * @return datas do boleto
   * @see Datas
   */
  public Datas getDatas() {
    return this.datas;
  }

  /**
   * @param datas que serão associadas ao boleto
   * @return este boleto
   */
  public Boleto comDatas(Datas datas) {
    this.datas = datas;
    return this;
  }

  /**
   * @return espécie do documento do boleto que por default sempre é "DV"
   */
  public String getEspecieDocumento() {
    return this.especieDocumento;
  }

  /**
   * @param especieDocumento que será associado ao boleto.
   * @return este boleto
   */
  public Boleto comEspecieDocumento(String especieDocumento) {
    this.especieDocumento = especieDocumento;
    return this;
  }

  /**
   * @return número do documento. Código informado pelo banco
   */
  public String getNumeroDoDocumento() {
    return this.numeroDocumento;
  }

  /**
   * @param numeroDocumento que será associado ao boleto
   * @return este boleto
   */
  public Boleto comNumeroDoDocumento(String numeroDocumento) {
    this.numeroDocumento = numeroDocumento;
    return this;
  }

  /**
   * @return quantidade da moeda
   */
  public BigDecimal getQuantidadeDeMoeda() {
    return this.quantidadeMoeda;
  }

  /**
   * @param quantidadeMoeda que será associada ao boleto
   * @return este boleto
   */
  public Boleto comQuantidadeMoeda(BigDecimal quantidadeMoeda) {
    this.quantidadeMoeda = quantidadeMoeda;
    return this;
  }

  /**
   * @return valor desse boleto
   */
  public BigDecimal getValorBoleto() {
    return this.valorBoleto;
  }

  /**
   * @param valor em double que após ser convertido pra String
   *              será associado ao boleto @see Boleto#comValorBoleto(String)
   * @return this
   */

  public Boleto comValorBoleto(double valor) {
    return comValorBoleto(Double.toString(valor));
  }

  /**
   * @param valor String que é convertido para BigDecimal com
   *              o Locale da JVM @see Boleto#comValorBoleto(BigDecimal)
   * @return this
   */
  public Boleto comValorBoleto(String valor) {
    return comValorBoleto(new BigDecimal(valor));

  }

  /**
   * @param valor que será associado ao boleto
   * @return este boleto
   */
  public Boleto comValorBoleto(BigDecimal valor) {
    this.valorBoleto = valor;
    return this;
  }

  /**
   * @return espécie da moeda que por default é "R$"
   */
  public String getEspecieMoeda() {
    return this.especieMoeda;
  }

  /**
   * @param especieMoeda que será associada ao boleto
   * @return este boleto
   */
  public Boleto comEspecieMoeda(String especieMoeda) {
    this.especieMoeda = especieMoeda;
    return this;
  }

  /**
   * @return código da espécie da moeda que por default é "9" (real)
   */
  public int getCodigoEspecieMoeda() {
    return this.codigoEspecieMoeda;
  }

  /**
   * @param codigoEspecieMoeda que será associado ao boleto
   * @return este boleto
   */
  public Boleto comCodigoEspecieMoeda(int codigoEspecieMoeda) {
    this.codigoEspecieMoeda = codigoEspecieMoeda;
    return this;
  }

  /**
   * @return valor da moeda
   */
  public BigDecimal getValorMoeda() {
    return this.valorMoeda;
  }

  /**
   * @param valorMoeda que será associado ao boleto
   * @return this
   */
  public Boleto comValorMoeda(BigDecimal valorMoeda) {
    this.valorMoeda = valorMoeda;
    return this;
  }

  /**
   * @return banco do boleto
   */
  public Banco getBanco() {
    return this.banco;
  }

  /**
   * @param banco que será associado ao boleto
   * @return este boleto
   */
  public Boleto comBanco(Banco banco) {
    this.banco = banco;
    return this;
  }

  /**
   * @return pagador do banco
   */
  public Pagador getPagador() {
    return this.pagador;
  }

  /**
   * @param pagador que será associado ao boleto
   * @return este boleto
   */
  public Boleto comPagador(Pagador pagador) {
    this.pagador = pagador;
    return this;
  }

  /**
   * @return sacado do banco
   * @deprecated use getPagador
   */
  @Deprecated
  public Sacado getSacado() {
    return new SacadoToPagadorMapper().toSacado(this.pagador);
  }

  /**
   * @param sacado que será associado ao boleto
   * @return este boleto
   * @deprecated use comPagador
   */
  @Deprecated
  public Boleto comSacado(Sacado sacado) {
    this.pagador = new SacadoToPagadorMapper().toPagador(sacado);
    return this;
  }

  public Beneficiario getBeneficiario() {
    return beneficiario;
  }

  /**
   * Beneficiário do boleto
   *
   * @param beneficiario beneficiário do Boleto
   * @return this este boleto.
   */
  public Boleto comBeneficiario(Beneficiario beneficiario) {
    this.beneficiario = beneficiario;
    return this;
  }


  /**
   * @return emissor do boleto
   * @deprecated use getBeneficiario
   */
  @Deprecated
  public Emissor getEmissor() {
    return new EmissorToBeneficiarioMapper().toEmissor(beneficiario);
  }

  /**
   * @param emissor que será associado ao boleto
   * @return este boleto
   * @deprecated use comBeneficiario
   */
  @Deprecated
  public Boleto comEmissor(Emissor emissor) {
    this.beneficiario = new EmissorToBeneficiarioMapper().toBeneficiario(emissor);
    return this;
  }

  /**
   * @return lista de instruções do boleto
   */
  public List<String> getInstrucoes() {
    return this.instrucoes;
  }

  /**
   * @param instrucoes que serão associadas ao boleto (limite de 5)
   * @return este boleto
   * @throws IllegalArgumentException caso tenha mais de 5 instruções
   */
  public Boleto comInstrucoes(String... instrucoes) {
    if (instrucoes.length > 5) {
      throw new IllegalArgumentException("maximo de 5 instrucoes permitidas");
    }
    this.instrucoes = Arrays.asList(instrucoes);
    return this;
  }

  /**
   * @return lista de descrições do boleto. <br>
   * Note que esse campo não aparece no boleto gerado em PNG
   */
  public List<String> getDescricoes() {
    return this.descricoes;
  }

  /**
   * @param descricoes que serão asociadas ao boleto (limite de 5)
   *                   <br> Note que esse campo não aparece no boleto gerado em PNG
   * @return este boleto
   * @throws IllegalArgumentException caso tenha mais de 5 descrições
   */
  public Boleto comDescricoes(String... descricoes) {
    if (descricoes.length > 5) {
      throw new IllegalArgumentException("maximo de 5 descricoes permitidas");
    }
    this.descricoes = Arrays.asList(descricoes);
    return this;
  }

  /**
   * @param descricao que será adicionada à lista de descricoes do boleto
   *                  <br> Note que esse campo não aparece no boleto gerado em PNG
   * @return este boleto
   * @throws IllegalArgumentException      caso a descrição seja nula
   * @throws UnsupportedOperationException caso a lista de descrições tenha 5 descrições
   */
  public Boleto comDescricao(String descricao) {
    if (descricao == null) {
      throw new IllegalArgumentException("nao e permitida descricao nula");
    }
    if (this.descricoes.size() == 5) {
      throw new UnsupportedOperationException("maximo de descricoes permitidas atingido");
    }
    this.descricoes.add(descricao);
    return this;
  }

  /**
   * @return lista de locais de pagamento do boleto
   */
  public List<String> getLocaisDePagamento() {
    return this.locaisDePagamento;
  }

  /**
   * @param locaisDePagamento que serão associados ao boleto (limite de 2 locais)
   * @return este boleto
   * @throws IllegalArgumentException tiver mais de 2 locais de pagamento
   */
  public Boleto comLocaisDePagamento(String... locaisDePagamento) {
    if (locaisDePagamento.length > 2) {
      throw new IllegalArgumentException("maximo de 2 locais de pagamento permitidos");
    }
    this.locaisDePagamento = Arrays.asList(locaisDePagamento);
    return this;
  }

  public Boleto comLocaisDePagamento(List<String> locaisDePagamento) {
    if (locaisDePagamento.size() > 2) {
      throw new IllegalArgumentException("maximo de 2 locais de pagamento permitidos");
    }
    this.locaisDePagamento = locaisDePagamento;
    return this;
  }

  /**
   * @return fator de vencimento do boleto. Utilizado para geração do código de barras
   */
  public String getFatorVencimento() {
    Calendar dataBase = Calendar.getInstance();
    dataBase.set(Calendar.DAY_OF_MONTH, 7);
    dataBase.set(Calendar.MONTH, 10 - 1);
    dataBase.set(Calendar.YEAR, 1997);
    dataBase.set(Calendar.HOUR_OF_DAY, 0);
    dataBase.set(Calendar.MINUTE, 0);
    dataBase.set(Calendar.SECOND, 0);
    dataBase.set(Calendar.MILLISECOND, 0);

    Calendar vencimentoSemHoras = Calendar.getInstance();

    vencimentoSemHoras.set(Calendar.DAY_OF_MONTH, this.datas.getVencimento().get(Calendar.DAY_OF_MONTH));
    vencimentoSemHoras.set(Calendar.MONTH, this.datas.getVencimento().get(Calendar.MONTH));
    vencimentoSemHoras.set(Calendar.YEAR, this.datas.getVencimento().get(Calendar.YEAR));
    vencimentoSemHoras.set(Calendar.HOUR_OF_DAY, 0);
    vencimentoSemHoras.set(Calendar.MINUTE, 0);
    vencimentoSemHoras.set(Calendar.SECOND, 0);
    vencimentoSemHoras.set(Calendar.MILLISECOND, 0);

    long diferencasEmMiliSegundos = vencimentoSemHoras.getTimeInMillis() - dataBase.getTimeInMillis();
    long diferencasEmDias = diferencasEmMiliSegundos / (1000 * 60 * 60 * 24);

    if (diferencasEmDias > 9999) {
      throw new CriacaoBoletoException("Data fora do formato aceito!");
    }

    return String.valueOf((int) diferencasEmDias);
  }

  /**
   * @return valor do boleto formatado (com 10 digitos)
   */
  public String getValorFormatado() {
    return String.format("%011.2f", this.getValorBoleto()).replaceAll("[^0-9]", "");
  }

  /**
   * @return número do documento formatado (com 4 digitos)
   */
  public String getNumeroDoDocumentoFormatado() {
    return leftPadWithZeros(this.numeroDocumento, 4);
  }

  /**
   * @return agencia e codigo beneficiario (conta corrente) do banco
   */
  public String getAgenciaECodigoBeneficiario() {
    return this.banco.getAgenciaECodigoBeneficiario(this.beneficiario);
  }

  /**
   * @return agencia e conta do banco
   */
  public String getAgenciaEConta() {
    return this.banco.getAgenciaEConta(this.beneficiario);
  }


  public Boleto comNossoNumeroECodDocumento(String nossoNumeroECodDocumento) {
    this.nossoNumeroECodDocumento = nossoNumeroECodDocumento;
    return this;
  }

  /**
   * @return nosso numero e codigo do documento para boleto
   */
  public String getNossoNumeroECodDocumento() {
    if (this.nossoNumeroECodDocumento == null) {
    return banco.getNossoNumeroECodigoDocumento(this);
    } else {
      return this.nossoNumeroECodDocumento;
    }
  }

  public BigDecimal getValorDescontos() {
    return valorDescontos;
  }

  public Boleto comValorDescontos(String valorDescontos) {
    this.valorDescontos = new BigDecimal(valorDescontos);
    return this;
  }

  public BigDecimal getValorDeducoes() {
    return valorDeducoes;
  }

  public Boleto comValorDeducoes(String valorDeducoes) {
    this.valorDeducoes = new BigDecimal(valorDeducoes);
    return this;
  }

  public BigDecimal getValorMulta() {
    return valorMulta;
  }

  public Boleto comValorMulta(String valorMulta) {
    this.valorMulta = new BigDecimal(valorMulta);
    return this;
  }

  public BigDecimal getValorAcrescimos() {
    return valorAcrescimos;
  }

  public Boleto comValorAcrescimos(String valorAcrescimos) {
    this.valorAcrescimos = new BigDecimal(valorAcrescimos);
    return this;
  }

  public BigDecimal getValorCobrado() {
    return valorBoleto.subtract(valorDescontos).subtract(valorDeducoes)
                      .add(valorMulta).add(valorAcrescimos);
  }

  /**
   * Valor numérico do código de barras
   *
   * @return código de barras
   */
  public String getCodigoDeBarras() {
    if (this.codigoBarras == null) {
    return banco.geraCodigoDeBarrasPara(this);
    } else {
      return this.codigoBarras;
    }
  }

  /**
   * Permite informar uma linha digitável, quando o boleto está sendo gerado como segunda via.
   * @param linhaDigitavel sobrepõe a linha digitável, para segunda via.
   * @return this boleto
   */
  public Boleto comLinhaDigitavel(String linhaDigitavel) {
    this.linhaDigitavel = linhaDigitavel;
    return this;
  }

  /**
   * Linha digitável formatada
   *
   * @return linha digitável
   */
  public String getLinhaDigitavel() {
    if (this.linhaDigitavel == null) {
      return new GeradorDeLinhaDigitavel().geraLinhaDigitavelPara(getCodigoDeBarras(), banco);
    }
    else {
      return this.linhaDigitavel;
    }
  }

  /**
   * Permite informar diretamente o código de barras, para emissão de segunda via.
   * @param codigoBarras código de barras previamente gerado
   * @return this boleto
   */
  public Boleto comCodigoBarras(String codigoBarras) {
    this.codigoBarras = codigoBarras;
    return this;
  }

  /**
   * Carteira do boleto
   *
   * @return carteira
   */
  public String getCarteira() {
    return banco.getCarteiraFormatado(beneficiario);
  }

  /**
   * Local de Pagamento
   *
   * @return local de pagamento
   */
  public String getLocalDePagamento() {
    return locaisDePagamento.isEmpty() ? "" : locaisDePagamento.get(0);
  }

  /**
   * Destaca boleto ST
   *
   * @return se sera destacado boleto ST
   */
  public String getDestacarBoletoST(){
    return this.destacarBoletoST;
  }

  /**
   * Informa se sera destacado no boleto a informaçao boleto ST
   *
   * @param destacarBoletoST true ou false
   * @return this
   */
  public Boleto comDestacarBoletoST(String destacarBoletoST){
    this.destacarBoletoST = destacarBoletoST;
    return this;
  }

  /**
   * CIP
   *
   * @return CIP
   */
  public String getCip(){
    return this.cip;
  }

  /**
   * Informaçao do CIP
   *
   * @return this
   */
  public Boleto comCip(String cip){
    this.cip = cip;
    return this;
  }
}
