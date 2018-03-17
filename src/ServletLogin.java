

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//|Recupera meus parametro
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		//Obter objeto de resposta
		PrintWriter out = response.getWriter();
		
		//Começar a montar html
				out.println("<html><head><title>Login</title></head>");
				out.println("<boby>");
		
		//Conectar no banco de dados
		try {
			//referenciar o driver JDBC
			Class.forName("com.mysql.jdbc.Driver");
			
			//Criar string de conexao
			String url ="jdbc:mysql://localhost/supermercado";
			String username = "root";
			String password = "root";
			
			//Realizar conexao xom o BD
			Connection conexao = DriverManager.getConnection(url, username ,password);
			
			/*//Criando o SQL - jeito ruim
			String sql = "select nomeusuario, senhausuario from usuario " +
					"where nomeusuario = '" + usuario + "' and senhausuario = '" + senha + "'";*/
			
			//Criando o SQL - jeito melhor
			String sql = "select nomeusuario, senhausuario from usuario " +
					"where nomeusuario = ? and senhausuario = ?";
			
			//Preparar o SQL para envio ao BD
			PreparedStatement ps = conexao.prepareStatement(sql);
			//Passar o valor de usuario
			ps.setString(1, usuario);
			//Passar o valor de senha
			ps.setString(2, senha);
			
			//Executando o SQL
			ResultSet rs = ps.executeQuery();
			
			//Verificar se usuario e senha estao corretos
			if (rs.first()) {
				out.println("<h1>Login com sucesso!</h1>");
				out.println("<a href=\"listarProduto\"><h1>Lista de produto</h1></a>");
			}else {
				out.print("<h1>Login sem sucesso!</h1>");
			}
			//fechar o resultSet
			rs.close();
			//fecha o prepareStatemnt
			ps.close();
			//Fechar conectio
			conexao.close();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			//Mostra o erro
			e.printStackTrace();
		}
		
		
		//Fechar o html
		out.println("</boby></html>");
	}

}
