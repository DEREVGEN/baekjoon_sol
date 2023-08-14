package com.backjoon.solution.problem_3613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int CPP = 1;
    static final int JAVA = 2;
    static final int NONE = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String varName = input.readLine();

        char startCh = varName.charAt(0);
        char lastCh = varName.charAt(varName.length() - 1);

        if (!Character.isLowerCase(startCh) || lastCh == '_') {
            System.out.println("Error!");
            return;
        }

        StringBuilder convertedName = new StringBuilder();
        int lang = 0;

        convertedName.append(startCh);

        int i;
        // 알파벳과 _ 만 input으로 들어옴, 1부터 시작 가능
        for (i = 1; i < varName.length(); i++) {
            char ch = varName.charAt(i);

            if (Character.isUpperCase(ch)) {
                if (lang == NONE)
                    lang = JAVA;
                else if (lang == CPP)
                    break;
                convertedName
                        .append('_')
                        .append(Character.toLowerCase(ch));

            } else if (ch == '_') {

                if (lang == NONE)
                    lang = CPP;
                else if (lang == JAVA)
                    break;

                ch = varName.charAt(++i);
                if (ch == '_' || Character.isUpperCase(ch))
                    break;
                convertedName
                        .append(Character.toUpperCase(ch));
            } else {
                convertedName.append(ch);
            }
        }

        if (i < varName.length())
            System.out.println("Error!");
        else
            System.out.println(convertedName);
    }
}
