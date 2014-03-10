package manual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import sem.FileInput;

/*
 * Class MP
 * 
 * Parses a specified input file and prints whether the
 * file was successfully printed or not.
 * 
 * Input file can be given as command line argument.
 * If no arguments are given, a hard coded file name
 * will be used.
 * 
 */
public class MP {
	public static BufferedWriter writer;

	public static void main(String[] args) throws SyntaxException {
		String inFile = "PiSample.in";//"testcasesm4/Sample2.in";
		String outFile = "Sample.out";
		if (args.length > 1) {
			inFile = args[0];
			outFile = args[1];
		}
		try {
			writer = new BufferedWriter(new FileWriter(outFile));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Lexer lexer = new Lexer(inFile);

		Parser parser = new Parser(lexer);

		try {
			try {
				FileInput value = parser.parse();
				System.out.println(value);
				writer.write(value.toString());
				writer.newLine();
				writer.close();

			} catch (SyntaxException e) {
				writer.write(e.getMessage());
				writer.newLine();
				writer.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
