package ajax.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@RequestScoped
public class Pessoa {
	private String nome;
	private String sobrenome;

	public Pessoa() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public void salvarPessoa(ActionEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bem-vindo " + nome + " " + sobrenome + "!"));
    }
}
