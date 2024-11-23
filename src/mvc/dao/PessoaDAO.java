package mvc.dao;

import mvc.model.Pessoa;

public interface PessoaDAO {
    void criarPessoa(Pessoa pessoa);
    void listarPessoas();
    void atualizarPessoa(Pessoa pessoa);
    boolean deletarPessoa(String cpf);
    Pessoa buscaPessoaLogin(String email, String senha);
    Pessoa buscaPessoa(String cpf);
    Pessoa buscaPorId(Long id);
    void buscaCerimonialistas();
    Pessoa buscaNoivos();
    void buscaConvidados();
    boolean alterarNome(String cpf, String novoNome);
    boolean alterarSexo(String cpf, String novoSexo);
    boolean alterarNascimento(String cpf, String novoNascimento);
    boolean alterarEmail(String cpf, String novoEmail);
    boolean alterarSenha(String cpf, String novaSenha);
    boolean alterarTipoUsuario(String cpf, int novoTipo);
    boolean alterarCpf(String cpf, String novaCpf);
}
