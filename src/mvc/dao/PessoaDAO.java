package mvc.dao;

import mvc.model.Pessoa;

public interface PessoaDAO {
    void criarPessoa(Pessoa pessoa);
    void listarPessoas();
    void atualizarPessoa(Pessoa pessoa);
    void deletarPessoa(String cpf);
    Pessoa buscaPessoaLogin(String email, String senha);
    Pessoa buscaPessoa(String cpf);
    Pessoa buscaPorId(Long id);
    void buscaCerimonialistas();
    void buscaNoivos();
    void buscaConvidados();
    void alterarNome(String cpf, String novoNome);
    void alterarSexo(String cpf, String novoSexo);
    void alterarNascimento(String cpf, String novoNascimento);
    void alterarEmail(String cpf, String novoEmail);
    void alterarSenha(String cpf, String novaSenha);
    void alterarTipoUsuario(String cpf, int novoTipo);
    void alterarCpf(String cpf, String novaCpf);
}
