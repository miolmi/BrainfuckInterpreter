import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.sql.SQLOutput;
import java.util.Collection;
import java.util.Scanner;
import java.util.Stack;

import static java.util.Collections.reverse;

public class Brainterpreter {


    private static String src;
    private static int input;

    private static void interpreter(String src) throws IOException {

        byte[] mem = new byte[8];
        int memPtr = 0;
        int[] lookUpTbl = new int[100];
        int bracketCount = 0;

        Stack stack = new Stack();


        for (int j = 0; j < src.length(); j++) {
            if (src.charAt(j) == '[') {
                bracketCount++;
                stack.push(j);
            } else if (src.charAt(j) == ']') {
                bracketCount--;
                lookUpTbl[j] = (int) stack.pop();
                lookUpTbl[lookUpTbl[j]] = j;
            }
        }


        if (bracketCount == 0) {

            for (int i = 0; i < src.length(); i++) {
                if (mem[memPtr] < 255) {
                    mem[memPtr] = 0;
                }
                if (src.charAt(i) == '>') {
                    memPtr++;
                } else if (src.charAt(i) == '<') {
                    memPtr--;

                } else if (src.charAt(i) == '+') {
                    mem[memPtr]++;

                } else if (src.charAt(i) == '-') {
                    mem[memPtr]--;

                } else if (src.charAt(i) == '.') {
                    System.out.print(mem[memPtr]);

                } else if (src.charAt(i) == ',') {
                    mem[memPtr] += (byte) input;

                } else if (src.charAt(i) == '[') {
                    if (mem[memPtr] == 0) {
                        i = lookUpTbl[i];
                    }

                } else if (src.charAt(i) == ']') {
                    if (mem[memPtr] != 0) {
                        i = lookUpTbl[i];
                    }

                } else {
                    memPtr++;
                }
            }

        } else {
            System.out.println("Invalid Syntax");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bfnCode = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader bfnInput = new BufferedReader(new InputStreamReader(System.in));

        while (src != "stop") {


            System.out.println("Enter your \",\" input:");
            input = Integer.parseInt(bfnInput.readLine());

            System.out.println("Enter the code:");
            src = bfnCode.readLine();

            System.out.println("Output:");
            interpreter(src);

            System.out.println("");
            System.out.println("");
        }
    }
}
