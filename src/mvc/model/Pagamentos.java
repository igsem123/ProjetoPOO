package mvc.model;
import java.util.Calendar;

public class Pagamentos {

        private long id;
        public static int serial = 0;
        private Pessoa pessoa;
        private Fornecedor fornecedor;
        private String descricao;
        private double valor;
        private int parcela;
        private boolean agendado;
        private Calendar dataPagamento;
        private Calendar dataCriacao;
        private Calendar dataModificacao;

        // Construtor
        public Pagamento(Pessoa pessoa, Fornecedor fornecedor, String descricao, double valor, int parcela, boolean agendado, Calendar dataPagamento) {
            this.id = (serial++);
            this.pessoa = pessoa;
            this.fornecedor = fornecedor;
            this.descricao = descricao;
            this.valor = valor;
            this.parcela = parcela;
            this.agendado = agendado;
            this.dataPagamento = dataPagamento;
            this.dataCriacao = Calendar.getInstance(); // Data atual
            this.dataModificacao = Calendar.getInstance(); // Data atual
        }

        // Getters e Setters para acessar os atributos
        public Calendar getDataPagamento() {
            return dataPagamento;
        }

        public void setDataPagamento(Calendar dataPagamento) {
            this.dataPagamento = dataPagamento;
            this.dataModificacao = Calendar.getInstance(); // Atualiza a data de modificação
        }

        // Exemplo de como verificar se o pagamento está completo
        public boolean isPagoCompleto() {
            // Implementação para verificar se todas as parcelas foram pagas
            return this.parcela == 0;
        }
    }
