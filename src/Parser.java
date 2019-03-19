import java.io.*;

public class Parser {

	private static Lexer analizadorLexico=null;
	private  Yytoken currentToken=null;

	Parser(Lexer lex){
	analizadorLexico=lex;
	}
	
	String currentType()
	{
		return currentToken.m_tktype;
	}

//Obtener siguiente token	
	private  void nextToken()
	{
		Yytoken prevToken=currentToken;
		try {
			currentToken = analizadorLexico.yylex();
			if (currentToken==null) { System.out.println("Fin de fichero");
			currentToken= new Yytoken(0,"","EOF",prevToken.m_line,prevToken.m_column);
			
			}
		}catch (IOException e)
		{
			if (currentToken==null) { System.out.println("Fin de fichero"+e.toString());
			
			}
			else { 
				currentToken.Error("Error obteniendo el siguiente token despues de");
		
			}
		}
		prevToken=null;
	}
	
// Inicio Producciones
/*
S-->T;
T-->FT'
T'-->opFT'|
F-->(T)|num
 */
	public  void Sentencia()
	{
		System.out.println("Comienza sentencia");
	termino();
	match("PTOCOMA");
		System.out.println("Fin sentencia");
	}
	
	public  void termino()
	{
	factor();
	masterminos();
	}
	
	public  void masterminos()
	{
		if (currentType().equals("OP")) {

			 match("OP");
			 factor();
			 masterminos();
		}
		else
		{
			//Caso vacio no se hace nada
		}
	}
	public  void factor(){
		switch(currentType()) {
		case "ABREPAR" : 
						 match("ABREPAR");
						 termino();
						 match("CIERRAPAR");
						 break;
		case "NUM":
						match("NUM");
						break;
		default: 
			currentToken.Error("Validando 'factor'");

			break;
		}
	}
	
//Fin Producciones
// Metodo match para comparar tokens
	public  void match(String refStrToken)
	{
	if (currentType().equals(refStrToken))
		{
			System.out.println("Token correcto: "+currentType());
			nextToken();
			
		}
	else {
		currentToken.ErrorMatch(refStrToken);
		
	}
	
	}

	
	public static void main(String[] argv) {
		  if (argv.length == 0) {
		      System.out.println("Uso : java Parser  <inputfile)>");
		    }
		  else {
		 
		 try {
		java.io.FileInputStream stream = new java.io.FileInputStream(argv[0]);
	    java.io.Reader reader = new java.io.InputStreamReader(stream);
		Lexer lex= new Lexer(reader);
		Parser asd=new Parser(lex);
		asd.nextToken();
		do { 
		
				asd.Sentencia();
		}
		while (!asd.currentType().equals("EOF"));
		}
		catch(IOException x) {
		System.out.println("Error leyendo "+x.toString()+(analizadorLexico.yytext()));
		}
		  }
	}

}
