package generator;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import Utils.Utils;
import Utils.NodeTreeQueue;
import Utils.NodeInformation;
import Utils.StringUtils;

import Exception.AddOutTree;

public class generateCompletion {

	public static void main(String[] args) {
		if (args.length != 1)
			Utils.MessageError("Need one file name.");

		File fileRead = new File(args[0]);
		if (!fileRead.canRead())
			Utils.MessageError("Can't read file: '" + args[0] + "'.");

		new generateCompletion(fileRead); // new Parser(fileRead); ??
	}

	private generateCompletion(File fileReader) {
		Parser parser = new Parser(fileReader);
		for (NodeInformation<String> i : parser.getValue()) {
			System.out.print(StringUtils.badJoin("_", i.getPath()) + "\t\t");
			if (!i.isLeaf()) {
				System.out.print(StringUtils.badJoin(" ", i.getQueue()) + " ");
				System.out.print("'" + i.isChildrenLeaf() + "'");
			}
			System.out.println();
		}
	}

	private class Parser {
		private Pattern regexAll;
		private int numberLine;
		private NodeTreeQueue<String> value;

		public NodeTreeQueue<String> getValue() {
			return this.value;
		}

		private Parser(File nameFile) {
			regexAll = Pattern.compile("(\\s*)(\\S+)");
			value = new NodeTreeQueue<String>();
			try {
				BufferedReader b = new BufferedReader(new FileReader(nameFile));
				String readLine;
				for (numberLine = 1; (readLine = b.readLine()) != null; numberLine++) {
					ParseLine(readLine);
				}
			} catch (FileNotFoundException ex) {
				Utils.MessageError("Error: try read file Not Found Exception.");
			} catch (IOException ex) {
				Utils.MessageError("Error: try read file IO Exception.");
			} catch (AddOutTree ex) {
				Utils.MessageError("Error: try read file add out tree.");
			}
		}

		private void ParseLine(String line) throws AddOutTree {
			Matcher m = regexAll.matcher(line);
			m.find();
			value.add(m.group(1).length(), m.group(2));
		}
	}
}
