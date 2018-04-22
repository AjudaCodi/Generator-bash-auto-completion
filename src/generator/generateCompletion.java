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

		System.out.println("#!/bin/bash\n");
		for (NodeInformation<String> i : parser.getValue()) {
			if (i.isLeaf())
				continue;
			//understandDebuggin(i);
			generateOutput(i);
		}
		System.out.println("_" + parser.getValue().iterator().next().getPath().getFirst() + " \"$@\"");
	}
	private void generateOutput(NodeInformation<String> i) {
		final String getPath = StringUtils.badJoin("_", i.getPath());
		final String getPathSpace = StringUtils.badJoin(" ", i.getPath());
		final String getQueue = creatingQueue(" ", i.getQueue());

		System.out.println(getPath + "() {");
		System.out.println("  if [ $1 -eq " + i.getPath().size() + " ]; then");

		System.out.println("    echo 'Usage:" + getPathSpace + " |" + getQueue + " |'; return 0");

		System.out.println("  fi");

		if (i.isChildrenLeaf()) {
			System.out.println("\n  case $2 in");
			for (String child : i.getQueue()) {
				if (i.thisChildHaveChildren(child))
					continue;
				System.out.println("    ('" + child + "') " + getPath + "_" + child + " $1 ${@:3} ;;" );
			}
			System.out.println("    (*) echo 'Usage:" + getPathSpace + " >" + getQueue + " <|'; return 0 ;;");
			System.out.println("  esac");
		}

		System.out.println("}\n");
	}

	private static String creatingQueue(String separator, Iterable<String> values) {
		StringBuilder out = new StringBuilder();
		Pattern regexFile = Pattern.compile("<file([^>]*)>");

		for (String i : values) {
			out.append(separator);

			Matcher m = regexFile.matcher(i);
			if ( m.find() ) {
				out.append("' *" + m.group(1) + " '");
			} else
			{
				out.append(i);
			}
		}
		return out.toString();
	}

	private void understandDebuggin(NodeInformation<String> i) {
		System.out.print(i.getPath().size() + StringUtils.badJoin("_", i.getPath()) + "\t\t");
		if (!i.isLeaf()) {
			System.out.print(StringUtils.badJoin(" ", i.getQueue()) + " ");
		}
		System.out.println();
	}

	private class Parser {
		private Pattern regexAll;
		private NodeTreeQueue<String> value;

		public NodeTreeQueue<String> getValue() {
			return this.value;
		}

		private Parser(File nameFile) {
			regexAll = Pattern.compile("(\\s*)(\\S+)");
			value = new NodeTreeQueue<String>();

			int numberLine = 0;
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
				Utils.MessageError("Error: try read file add out tree. Line: " + numberLine);
			}
		}

		private void ParseLine(String line) throws AddOutTree {
			Matcher m = regexAll.matcher(line);
			m.find();
			value.add(m.group(1).length(), m.group(2));
		}
	}
}
