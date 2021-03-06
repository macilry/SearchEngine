package com.searchEngine.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

import searhEngine.query.QueryDocument;
import searhEngine.query.QueryWiki;


public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String queryString =  new String(request.getParameter("query").getBytes( "iso-8859-1"), "UTF-8");
		//QueryWiki query = new QueryWiki(this.getServletContext().getRealPath("/index/"),20);



		QueryWiki query = new QueryWiki("/Users/amaury/Documents/index",20);

		
		request.setAttribute("result", queryString);

		 try {
			 ArrayList<QueryDocument> resDocuments = new ArrayList<QueryDocument>();
			 ArrayList<Document> documents = query.search(new String[]{"title","entities"}, queryString);
			 for (Document document : documents) {
				QueryDocument doc = new QueryDocument(document.get("id"), document.get("title"), document.get("entities"));
				resDocuments.add(doc);
			}
			 System.out.println(resDocuments.size());
			 request.setAttribute("results", resDocuments);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

}
