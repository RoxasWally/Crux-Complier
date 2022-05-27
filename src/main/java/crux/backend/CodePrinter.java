package crux.backend;

import java.util.*;
import java.io.*;

public class CodePrinter {
  PrintStream out;
  StringBuffer stringBuffer = new StringBuffer();

  public CodePrinter(String name) {
    try {
      out = new PrintStream(name);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  public void printLabel(String s) {
    out.println(s);
  }

  public void printCode(String s) {
    out.println("    " + s);
  }

  public void bufferLabel(String s) {
    stringBuffer.append(s + "\n");
  }

  public void bufferCode(String s){
    stringBuffer.append("    ").append(s).append("\n");
  }
  public void outputBuffer() {
    out.print(stringBuffer);
    stringBuffer = new StringBuffer();
  }
  public void close() {
    out.close();
  }
}
