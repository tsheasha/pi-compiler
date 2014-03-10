package automatic;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java_cup.runtime.Symbol;

/*
 * Class MLA
 * 
 * Reads a specified input file, tokenize it, and writes
 * the output to a specified output file;
 * 
 * Input and output files can be given as command line
 * arguments. If no arguments are given, hard coded file
 * names will be used.
 * 
 * Output file will be automatically overwritten if exists.
 * 
 */
public class MLA {
	public static void main(String[] args) {
		String inFile = "SampleTask.in";
		String outFile = "Sample.out";

		if (args.length > 1) {
			inFile = args[0];
			outFile = args[1];
		}
		try {
		FileInputStream fis = new FileInputStream(inFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		Lexer lexer = new Lexer(dis);
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

			Symbol t;

			while ((t = lexer.next_token()) != null) {
				System.out.println(t.toString());
				writer.write(t.toString());
				writer.newLine();
				
			}

			writer.close(); 
			
			System.out.println("Done tokenizing file: " + inFile);
			System.out.println("Output written in file: " + outFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
