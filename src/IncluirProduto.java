

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IncluirProduto
 */
@WebServlet("/incluirproduto")
public class IncluirProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncluirProduto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//|Recupera meus parametro
		String codigo = request.getParameter("codigo");
		String descricao = request.getParameter("descricao");
		String fabricante = request.getParameter("fabricante");
		String preco = request.getParameter("preco");
	
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
			String sql = "INSERT INTO supermercado.produto(codigoproduto, descricaoproduto, fabricanteproduto, precoproduto) VALUES(?,?,?,?)";
							
			//Prepara o sql para o bd
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, codigo);
			ps.setString(2, descricao);
			ps.setString(3, fabricante);
			ps.setString(4, preco);
							
			//Executar o sql
			ps.executeUpdate();
			out.println("<h1>Incluido com sucesso</h1><br />");
					
							
								
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
