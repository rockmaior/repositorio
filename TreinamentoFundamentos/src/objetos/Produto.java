package objetos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@RequestScoped
public class Produto {
	private String dbUrl = "jdbc:mysql://localhost/teste";

	private Connection con = null;
	private String nome;
	private String usuario = "root";
	private String senha = "123456";

	private Produto selecionado;

	public Produto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> retornaProdutosString(String query) {
		try {
			List<String> nomes = new ArrayList<String>();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(dbUrl, usuario, senha);
			Statement stmt = con.createStatement();
			String sqlStmt = "SELECT nome FROM produtos ORDER BY nome";
			ResultSet rSet = stmt.executeQuery(sqlStmt);
			ResultSetMetaData rsMetaData = (ResultSetMetaData) rSet
					.getMetaData();
			while (rSet.next()) {
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					nomes.add(query + rSet.getObject(i));
				}
			}
			return nomes;
		} catch (Exception e) {
			System.out.println("Erro -> " + e.getMessage());
			return null;
		}
	}

	public String retornaId() {
		try {
			String id = new String();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(dbUrl, usuario, senha);
			Statement stmt = con.createStatement();
			String sqlStmt = "SELECT id_produto FROM produtos WHERE nome = '"
					+ getNome() + "'";
			ResultSet rSet = stmt.executeQuery(sqlStmt);
			while (rSet.next()) {
				id = rSet.getString("id_produto");
			}
			return id;
		} catch (Exception e) {
			System.out.println("Erro -> " + e.getMessage());
			return null;
		}
	}

	public void showId(ActionEvent actionEvent) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"O código do produto " + nome, "=" + retornaId()));
	}

	public Produto getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Produto selecionado) {
		this.selecionado = selecionado;
	}

}
