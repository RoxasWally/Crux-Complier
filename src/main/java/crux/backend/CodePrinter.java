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

  public void bufferCode(String s){
    stringBuffer.append("    ").append(s).append("\n");
  }

  public void close() {
    out.close();
  }
}
