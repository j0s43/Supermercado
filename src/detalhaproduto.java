

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
 * Servlet implementation class detalhaproduto
 */
@WebServlet("/detalhaproduto")
public class detalhaproduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detalhaproduto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//obter objeto resposta
				PrintWriter out = response.getWriter();
				
		//HTML
		out.println("<html><head><title>Produto</title></head>");
		out.println("<boby>");
				
		//Conectar no banco de dados
		try {
			Class.forName("com.mysql.jdbc.Driver");
					
			//Criar String de conexao co o BD
			String url ="jdbc:mysql://localhost/supermercado";
			String username = "root";
			String password = "root";
				
			//Realizar a conexao com o BD
			Connection conexao = DriverManager.getConnection(url, username ,password);
					
			//SQL
			String sql = "SELECT codigoproduto, descricaoproduto, fabricanteproduto, precoproduto FROM produto WHERE idproduto = " + request.getParameter("idproduto");
					
			//Prepara o sql para o bd
			PreparedStatement ps = conexao.prepareStatement(sql);
					
			//Executar o sql
			ResultSet rs = ps.executeQuery();
			rs.first();
			out.println("<h1>Produto detalhada</h1><br />");
			{
				out.println((rs.getInt("codigoproduto")) + "___" + 
							rs.getString("descricaoproduto") + "___" + 
							rs.getString("fabricanteproduto") + "___" + 
							rs.getInt("precoproduto") );
			}
					
			//fechar o resultSet
			rs.close();
			//fechar o prepreStaemnt
			ps.close();
			//fechar conexao
			conexao.close();
					
					
		} catch (ClassNotFoundException | SQLException e) {
			//Mostra o erro
			e.printStackTrace();
		}
				
			//Fechar o html
			out.println("</boby></html>");
		}

}
