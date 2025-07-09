package br.com.caelum.stella.boleto.bancos;

public enum DigitoBanco {

    BANCO_DO_BRASIL("001", "9"),
    BRADESCO("237", "2"),
    CAIXA("104", "0"),
    ITAU("341", "7"),
    SANTANDER("033", "7"),
    HSBC("399", "9"),
    BANRISUL("041", "8"),
    SICREDI("748", "0"),
    SICOOB("756", "0"),
    SOFISA("637", "7"),
    BANCO_ABC("246","1");

    private final String numero;
    private final String digito;

    DigitoBanco(String numero, String digito) {
        this.numero = numero;
        this.digito = digito;
    }

    public String getDigito() {
        return digito;
    }

    public static String getDigitoPorNumero(String numero) {
        for (DigitoBanco db : DigitoBanco.values()) {
            if (db.numero.equals(numero)) {
                return db.digito;
            }
        }
        throw new IllegalArgumentException("Dígito não encontrado para banco: " + numero);
    }

    public static String getNumeroFormatadoComDigito(String numero) {
        for (DigitoBanco db : DigitoBanco.values()) {
            if (db.numero.equals(numero)) {
                return String.format("%s-%s", numero, db.digito);
            }
        }
        throw new IllegalArgumentException("Dígito não encontrado para banco: " + numero);
    }

}
