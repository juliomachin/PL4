
public class Yytoken {
  public int m_index;
  public String m_text;
  public String m_tktype;
  public int m_line;
  public int m_column;
  
  
  Yytoken (int index, String text, String tktype, int line, int column) {
     m_index = index;
    m_text = text;
    m_tktype=tktype;
    m_line = line;
    m_column = column;
   
  }

  public String toString() {
    return "Text   : "+m_text+
    		"\ntype:"+m_tktype+
           "\nindex : "+m_index+
           "\nline  : "+m_line+
           "\nccolumn. : "+m_column;
           
  }
  
  public void ErrorMatch(String refStrToken) {
	  System.out.println("Error leyendo token: "+m_tktype+" se esperaba "+refStrToken+" en linea "+(m_line+1)+ " columna: "+(m_column+1));
  }
  public void Error(String msg) {
	  System.out.println(msg+": error leido token: "+m_tktype+" en linea "+(m_line+1)+ " columna: "+(m_column+1));
  }
}