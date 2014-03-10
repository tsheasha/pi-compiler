package automatic;
public class Token {
	
	int token;
	String lexeme;
	
	static final int EOF = -1;

	static final int NM=1;
	static final int BL=2;
	static final int ST=3;
	static final int ID=4;
	static final int AO=5;
	static final int EQ=6;
	static final int LG=7;
	static final int LA=8;
	static final int TO=9;
	static final int NO=10;
	static final int LT=11;
	static final int GE=12;
	static final int NE=13;
	static final int PO=14;
	static final int DO=15;
	static final int GT=16;
	static final int LE=17;
	static final int LO=18;
	static final int MO=19;
	static final int DI=20;
	static final int LB=21;
	static final int RB=22;
	static final int SM=23;
	static final int CL=24;
	static final int NEWLINE=25;
	static final int INDENT=26;
	static final int DEDENT=27;
	static final int MD=28;
	static final int CM=29;
	static final int ERROR=30;
	static final int WHILE=31;
	static final int IF=32;
	static final int ELSE=33;
	static final int ELIF=34;
	static final int RETURN=35;
	static final int OR=36;
	static final int AND=37;
	static final int IS=38;
	static final int IN=39;
	static final int PRINT=40;
	static final int CLASS=41;
	static final int DEF=42;
	
	public Token(int token, String lexeme)
	{
		this.token=token;
		this.lexeme=lexeme;
	}

	public int getTokenType()
	{
		return token;
	}
	public String getLexeme()
	{
		return lexeme;
	}
	//String representation of token + lexeme
	public String toString()
	{
	
		
		switch(token)
		{
		case NM:
			return "NM : "+lexeme;
		case BL:
			return "BL : "+lexeme;
		case ST:
			return "ST : "+lexeme;
		case ID:
			return "ID : "+lexeme;
		case AO:
			return "AO : "+lexeme;
		case EQ:
			return "EQ : "+lexeme;
		case LG:
			return "LG : "+lexeme;
		case LA:
			return "LA : "+lexeme;
		case TO:
			return "TO : "+lexeme;
		case NO: 
			return "NO : "+lexeme;
		case LT:
			return "LT : "+lexeme;
		case GE:
			return "GE : "+lexeme;
		case NE:
			return "NE : "+lexeme;
		case PO:
			return "PO : "+lexeme;
		case DO:
			return "DO : "+lexeme;
		case GT:
			return "GT : "+lexeme;
		case LE:
			return "LE : "+lexeme;
		case LO:
			return "LO : "+lexeme;
		case MO:
			return "MO : "+lexeme;
		case DI:
			return "DI : "+lexeme;
		case LB:
			return "LB : "+lexeme;
		case RB:
			return "RB : "+lexeme;
		case SM:
			return "SM : "+lexeme;
		case CL:
			return "CL : "+lexeme;
		case NEWLINE:
			return "NEWLINE";
		case INDENT:
			return "INDENT";
		case DEDENT:
			return "DEDENT";
		case MD:
			return "MD : "+lexeme;
		case CM:
			return "CM : "+lexeme;
		case WHILE:
			return "WHILE: " + lexeme;
		case IF:
			return "IF: " + lexeme;
		case ELSE:
			return "ELSE: " + lexeme;
		case ELIF:
			return "ELIF: " + lexeme;
		case PRINT:
			return "PRINT: " + lexeme;
		case RETURN:
			return "RETURN: " + lexeme;
		case AND:
			return "AND: " + lexeme;
		case OR:
			return "OR: " + lexeme;
		case IS:
			return "IS: " + lexeme;
		case IN:
			return "IN: " + lexeme;
		case CLASS:
			return "CLASS: " + lexeme;
		case DEF:
			return "DEF: " + lexeme;
		default:
		return "ERROR "+lexeme;
		}
	}
}
