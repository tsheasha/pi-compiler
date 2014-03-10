package automatic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java_cup.runtime.Symbol;
import sem.FileInput;

public class AP {
	public static BufferedWriter writer;
	public static Lexer lex;
	public static ArrayList<String> input = new ArrayList<String>();

	public static void main(String[] args) {

		String inFile = "PiSample.in";
		String outFile = "Sample.out";
		if (args.length > 1) {
			inFile = args[0];
			outFile = args[1];
		}
		try {

			writer = new BufferedWriter(new FileWriter(outFile));
			BufferedReader reader = new BufferedReader(new FileReader(inFile));
			input = getInput(reader);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			FileInputStream fis = new FileInputStream(inFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);
			lex = new Lexer(dis);
			parser parser = new parser(lex);
			Symbol res = parser.parse();
			if (res.value != null) {
				String value = ((FileInput) res.value).toString();
				System.out.println(value);
				writer.write(value);
				writer.newLine();
				writer.close();
			}
			fis.close();
			bis.close();
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<String> getInput(BufferedReader reader) {
		try {
			ArrayList<String> input = new ArrayList<String>();
			String line = reader.readLine();
			while (line != null) {
				input.add(line);
				line = reader.readLine();
			}
			return input;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<String>();

	}

	public static void warning(String warningmsg) {
		try {
			Warning warning;
			warning = new Warning(lex.getLine() + 1, lex.getChar() + 1,
					warningmsg, input.get(lex.getLine()));

			if (lex.getCurr().sym == sym.NEWLINE)
				warning = new Warning(lex.getLine() + 1, lex.getCharNo() + 1,
						warningmsg, input.get(lex.getLine()));

			writer.write(warning.toString());
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
