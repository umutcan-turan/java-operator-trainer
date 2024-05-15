package java_operator_trainer;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class JavaOperatorTrainerApp {
	public static void main(String [] args)
	{
		JavaOperatorTrainerApp.run();
	}
	
	public static void run()
	{
		Random rand = new Random();
		java.util.Scanner kb = new java.util.Scanner(System.in);
		List<Operator> operators = initializeOperatorsArray();
		
		int prevQuestionType = 0;
		System.out.println("Çıkış için 0 giriniz");
		while (true) {
			int idx1 = rand.nextInt(operators.size());
			int idx2 = Util.generateDifferentInt(idx1, rand, operators.size());
			Operator op1 = operators.get(idx1);
			Operator op2 = operators.get(idx2);
			int questionType = Util.generateDifferentInt(prevQuestionType, rand, 4);
			
			askQuestion(questionType, op1, op2);
			// TODO: Exception handling
			String input;
			
			do {
				input = kb.nextLine();
				if (StringUtil.isValidNumber(input, 10))
					break;
				IOUtil.printInvalidInput();
			} while (true);
			int answer = Integer.parseInt(input);
			if (answer == 0)
				break;
			boolean isCorrect = checkAnswer(questionType, op1, op2, answer);
			IOUtil.printResult(isCorrect);
			if (!isCorrect) {
				IOUtil.printCorrectAnswerInfo();
				displayCorrectAnswer(questionType, op1, op2);
			}
			System.out.println();
			prevQuestionType = questionType;
		}
		kb.close();
	}

	public static void askQuestion(int type, Operator op1, Operator op2)
	{
		switch (type) {
			case 0 -> PrecedenceChecker.printQuestion(op1, op2);
			case 1 -> OperandCountChecker.printQuestion(op1);
			case 2 -> AssociativityChecker.printQuestion(op1);
			case 3 -> PlacementChecker.printQuestion(op1);
		}
	}

	public static boolean checkAnswer(int type, Operator op1, Operator op2, int answer)
	{
		return switch (type) {
			case 0 -> PrecedenceChecker.isCorrectAnswer(op1, op2, answer);
			case 1 -> OperandCountChecker.isCorrectAnswer(op1, answer);
			case 2 -> AssociativityChecker.isCorrectAnswer(op1, answer);
			case 3 -> PlacementChecker.isCorrectAnswer(op1, answer);
			default -> false;
		};
	}
	
	public static void displayCorrectAnswer(int type, Operator op1, Operator op2)
	{
		switch (type) {
			case 0 -> PrecedenceChecker.printCorrectAnswer(op1, op2);
			case 1 -> OperandCountChecker.printCorrectAnswer(op1);
			case 2 -> AssociativityChecker.printCorrectAnswer(op1);
			case 3 -> PlacementChecker.printCorrectAnswer(op1);
		}
	}

	public static List<Operator> initializeOperatorsArray()
	{
		List<Operator> arr = new ArrayList<Operator>();
		
		// Based on 006-Temel-Operatorler-V2.0.pdf from Java-Jan-2024 course (by Oğuz Karan) @ csystem.org.
		
		// TODO: Fix and uncomment the operators as they are covered in the course.
		
		//                     Name               Atom    Lvl Op  Prefix  Infix  Postfix LeftToRight	
		//arr.add(new Operator("",                "()",   1,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",                ".",    1,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",                "[]",   1,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",               "new",   1,  x,  x,      x,      x,      x));
		
		arr.add(new Operator("İşaret artı",        "+",   2,  1,  true,   false,  false,  false));
		arr.add(new Operator("İşaret eksi",        "-",   2,  1,  true,   false,  false,  false));
		arr.add(new Operator("Artırma",           "++",   2,  1,  true,   false,  true,   false));
		arr.add(new Operator("Azaltma",           "--",   2,  1,  true,   false,  true,   false));
		arr.add(new Operator("Mantıksal değil",    "!",   2,  1,  true,   false,  false,  false));
		//arr.add(new Operator("",                 "~",   2,  x,  x,      x,      x,      x));
		arr.add(new Operator("Tür dönüştürme",    "()",   2,  1,  true,   false,  false,  false));
		
		arr.add(new Operator("Çarpma",             "*",   3,  2,  false,  true,   false,  true));
		arr.add(new Operator("Bölme",              "/",   3,  2,  false,  true,   false,  true));
		arr.add(new Operator("Mod alma",           "%",   3,  2,  false,  true,   false,  true));
		
		
		arr.add(new Operator("Toplama",            "+",   4,  2,  false,  true,   false,  true));
		arr.add(new Operator("Çıkarma",            "-",   4,  2,  false,  true,   false,  true));
		
		//arr.add(new Operator("",                "<<",   5,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",                ">>",   5,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",               ">>>",   5,  x,  x,      x,      x,      x));
		
		arr.add(new Operator("Büyüktür",          ">",    6,  2,  false,  true,   false,  true));
		arr.add(new Operator("Küçüktür",          "<",    6,  2,  false,  true,   false,  true));
		arr.add(new Operator("Büyük eşittir",    ">=",    6,  2,  false,  true,   false,  true));
		arr.add(new Operator("Küçük eşittir",    "<=",    6,  2,  false,  true,   false,  true));
		//arr.add(new Operator("",       "instanceof",    6,  x,  x,      x,      x,      x));
		
		arr.add(new Operator("Eşittir",          "==",    7,  2,  false,  true,   false,  true));
		arr.add(new Operator("Eşit değildir",    "!=",    7,  2,  false,  true,   false,  true));
		
		arr.add(new Operator("Bitsel ve",         "&",    8,  2,  false,  true,   false,  true));
		
		//arr.add(new Operator("",                "^",    9,  x,  x,      x,      x,      x));
		
		arr.add(new Operator("Bitsel veya",       "|",   10,  2,  false,  true,   false,  true));
		
		arr.add(new Operator("Mantıksal ve",     "&&",   11,  2,  false,  true,   false,  true));
		
		arr.add(new Operator("Mantıksal veya",   "||",   12,  2,  false,  true,   false,  true));

		arr.add(new Operator("Koşul",            "?:",   13,  3,  false,  true,   false,  false));
		
		arr.add(new Operator("Atama",             "=",   14,  2,  false,  true,   false,  false));
		arr.add(new Operator("Çarparak atama",   "*=",   14,  2,  false,  true,   false,  false));
		arr.add(new Operator("Bölerek atama",    "/=",   14,  2,  false,  true,   false,  false));
		arr.add(new Operator("Toplayarak atama", "+=",   14,  2,  false,  true,   false,  false));
		arr.add(new Operator("Çıkararak atama",  "-=",   14,  2,  false,  true,   false,  false));
		//arr.add(new Operator("",              "<<=",   14,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",              ">>=",   14,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",               "&=",   14,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",               "^=",   14,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",               "|=",   14,  x,  x,      x,      x,      x));
		//arr.add(new Operator("",             ">>>=",   14,  x,  x,      x,      x,      x));
		
		return arr;
	}
}

class PrecedenceChecker {	
	public static void printQuestion(Operator op1, Operator op2)
	{
		System.out.println("Hangi operatör daha yüksek önceliklidir?");
		System.out.printf("1) %s \"%s\"%n", op1.name, op1.atom);
		System.out.printf("2) %s \"%s\"%n", op2.name, op2.atom);
		System.out.printf("3) Eşit öncelik seviyesine sahiplerdir.%n");

	}
	
	public static boolean isCorrectAnswer(Operator op1, Operator op2, int answer)
	{
		if (op1.level == op2.level)
			return answer == 3;
		return answer == (op1.level < op2.level ? 1 : 2);
	}
	
	public static void printCorrectAnswer(Operator op1, Operator op2)
	{
		if (op1.level == op2.level)
			System.out.println("Eşit öncelik seviyesine sahiplerdir.");
		else
			System.out.printf("\"%s\"%n", op1.level < op2.level ? op1.name : op2.name);
	}
}

class OperandCountChecker {
	public static void printQuestion(Operator op1)
	{
		System.out.printf("%s \"%s\" operatörünün operand sayısı kaçtır?%n", op1.name, op1.atom);
		System.out.println("1) 1");
		System.out.println("2) 2");
		System.out.println("3) 3");
	}
	
	public static boolean isCorrectAnswer(Operator op1, int answer)
	{
		return op1.operandCount == answer;
	}
	
	public static void printCorrectAnswer(Operator op1)
	{
		System.out.printf("%d%n", op1.operandCount);
	}
}

class AssociativityChecker {
	public static void printQuestion(Operator op1)
	{
		System.out.printf("%s \"%s\" için aşağıdakilerden hangisi söylenebilir?%n", op1.name, op1.atom);
		System.out.println("1) Soldan sağa önceliklidir.");
		System.out.println("2) Sağdan sola önceliklidir.");
	}
	
	public static boolean isCorrectAnswer(Operator op1, int answer)
	{
		return op1.isLeftToRight && answer == 1 || !op1.isLeftToRight && answer == 2;
	}
	
	public static void printCorrectAnswer(Operator op1)
	{
		System.out.printf("%s önceliklidir.%n", op1.isLeftToRight ? "Soldan sağa" : "Sağdan sola");
	}
}

class PlacementChecker {
	public static void printQuestion(Operator op1)
	{
		System.out.printf("%s \"%s\" için aşağıdakilerden hangisi söylenebilir?%n", op1.name, op1.atom);
		System.out.println("1) Önek olarak kullanılabilir.");
		System.out.println("2) Araek olarak kullanılabilir.");
		System.out.println("3) Sonek olarak kullanılabilir.");
		System.out.println("4) Hem önek hem sonek olarak kullanılabilir.");
	}
	
	public static boolean isCorrectAnswer(Operator op1, int answer)
	{
		if (op1.isPrefix && op1.isPostfix)
			return answer == 4;
		return op1.isPrefix && answer == 1 || op1.isInfix && answer == 2 || op1.isPostfix && answer == 3;
	}
	
	public static void printCorrectAnswer(Operator op1)
	{
		if (op1.isPrefix && op1.isPostfix)
			System.out.println("Hem önek hem sonek olarak kullanılabilir.");
		else
			System.out.printf("%s olarak kullanılabilir.%n", op1.isPrefix ? "Önek" : (op1.isInfix ? "Araek" : "Sonek"));
	}
}

class Operator {
	String name;
	String atom;
	int level;
	int operandCount;
	boolean isPrefix;
	boolean isInfix;
	boolean isPostfix;
	boolean isLeftToRight;
	
	public Operator(String name, String atom, int level, int operandCount, boolean isPrefix, boolean isInfix, boolean isPostfix, boolean isLeftToRight)
	{
		this.name = name;
		this.atom = atom;
		this.level = level;
		this.operandCount = operandCount;
		this.isPrefix = isPrefix;
		this.isInfix = isInfix;
		this.isPostfix = isPostfix;
		this.isLeftToRight = isLeftToRight;
	}
}

class Util {
	public static int generateDifferentInt(int number, Random rand, int range)
	{
		int result;
		
		do {
			result = rand.nextInt(range);
		} while (number == result);
		return result;
	}
}

class IOUtil {
	public static void printResult(boolean isCorrect)
	{
		System.out.printf("%s%n", isCorrect ? "Doğru cevap! Bravo!" : "Bir dahaki sefere..!");
	}
	
	public static void printCorrectAnswerInfo()
	{
		System.out.print("Doğru cevap: ");
	}
	
	public static void printInvalidInput()
	{
		System.out.println("Geçersiz sayı girdiniz!");
	}
}

class StringUtil {
	public static boolean isValidNumber(String str, int base)
	{
		int i = 0;
		
		if (str.isEmpty())
			return false;
		if (str.length() > 1 && (str.charAt(i) == '-' || str.charAt(i) == '+'))
			i++;
		for (; i < str.length(); i++)
			if (Character.digit(str.charAt(i), base) == -1)
				return false;
		return true;
	}
}