package java_operator_trainer;

import java.util.Random;

class JavaOperatorTrainerApp {
	public static void main(String [] args)
	{
		JavaOperatorTrainerApp.run();
	}
	
	public static void run()
	{
		Random rand = new Random();
		java.util.Scanner kb = new java.util.Scanner(System.in);
		Operator [] operators = initializeOperatorsArray();
		
		
		
	//	for (int i = 0; i < operators.length; i++)
	//		operators[i].debugInfo();
		PrecedenceChecker.loop(operators, rand, kb);
		kb.close();
	}

	public static Operator [] initializeOperatorsArray()
	{
		Operator [] arr = new Operator[24];
		
		arr[0] = new Operator("Aritmetik (Toplama)", "+", 4, 2, false, true, false, true);
		arr[1] = new Operator("Aritmetik (Cikarma)", "-", 4, 2, false, true, false, true);
		arr[2] = new Operator("Aritmetik (Carpma)", "*", 3, 2, false, true, false, true);
		arr[3] = new Operator("Aritmetik (Bolme)", "/", 3, 2, false, true, false, true);
		arr[4] = new Operator("Aritmetik (Mod)", "%", 3, 2, false, true, false, true);
		arr[5] = new Operator("Isaret (Arti)", "+", 2, 1, true, false, false, false);
		arr[6] = new Operator("Isaret (Eksi)", "-", 2, 1, true, false, false, false);
		arr[7] = new Operator("Artirma", "++", 2, 1, true, false, true, false);
		arr[8] = new Operator("Azaltma", "--", 2, 1, true, false, true, false);
		arr[9] = new Operator("Karsilastirma (Kucuktur)", "<", 6, 2, false, true, false, true);
		arr[10] = new Operator("Karsilastirma (Buyuktur)", ">", 6, 2, false, true, false, true);
		arr[11] = new Operator("Karsilastirma (Kucuk esittir)", "<=", 6, 2, false, true, false, true);
		arr[12] = new Operator("Karsilastirma (Buyuk esittir)", ">=", 6, 2, false, true, false, true);
		arr[13] = new Operator("Karsilastirma (Esittir)", "==", 7, 2, false, true, false, true);
		arr[14] = new Operator("Karsilastirma (Esit degildir)", "!=", 7, 2, false, true, false, true);
		arr[15] = new Operator("Mantiksal (Ve)", "&&", 11, 2, false, true, false, true);
		arr[16] = new Operator("Mantiksal (Veya)", "||", 12, 2, false, true, false, true);
		arr[17] = new Operator("Mantiksal (Degil)", "!", 2, 1, true, false, false, false);
		arr[18] = new Operator("Bitsel (Ve)", "&", 8, 1, false, true, false, true);
		arr[19] = new Operator("Bitsel (Veya)", "|", 10, 1, false, true, false, true);
		arr[20] = new Operator("Atama", "=", 14, 1, false, true, false, false);
		arr[20] = new Operator("Atama (Toplayarak atama)", "+=", 14, 1, false, true, false, false);
		arr[21] = new Operator("Atama (Cikararak atama)", "-=", 14, 1, false, true, false, false);
		arr[22] = new Operator("Atama (Carparak atama)", "*=", 14, 1, false, true, false, false);
		arr[23] = new Operator("Atama (Bolerek atama)", "/=", 14, 1, false, true, false, false);

		return arr;
	}
}

class PrecedenceChecker {
	public static void loop(Operator [] operators, Random rand, java.util.Scanner kb)
	{
		System.out.println("Cikis icin 0 giriniz");
		
		while (true) {
			int idx1 = rand.nextInt(operators.length);
			int idx2 = generateIdx2(idx1, operators.length, rand);
			Operator op1 = operators[idx1];
			Operator op2 = operators[idx2];
			int answer;
			boolean isCorrect;
			
			printQuestion(op1, op2);
			// TODO: Exception handling
			if ((answer = kb.nextInt()) == 0)
				break;
			isCorrect = isCorrectAnswer(op1, op2, answer);
			printResult(isCorrect);
			if (!isCorrect)
				printCorrectAnswer(op1, op2);
			System.out.println();
		};
	}

	public static int generateIdx2(int idx1, int range, Random rand)
	{
		int idx2;
		do {
			idx2 = rand.nextInt(range);
		} while (idx1 == idx2);
		return idx2;
	}
	
	public static void printQuestion(Operator op1, Operator op2)
	{
		System.out.println("Hangi islem oncelikli yapilir?");
		System.out.printf("1) %s \"%s\"%n", op1.name, op1.atom);
		System.out.printf("2) %s \"%s\"%n", op2.name, op2.atom);
		System.out.printf("3) Esit oncelik seviyesine sahiptirler%n");

	}
	
	public static boolean isCorrectAnswer(Operator op1, Operator op2, int answer)
	{
		if (op1.level < op2.level)
			return answer == 1;
		if (op1.level > op2.level)
			return answer == 2;
		return answer == 3;
	}
	
	public static void printCorrectAnswer(Operator op1, Operator op2)
	{
		if (op1.level < op2.level)
			System.out.printf("Dogru cevap: \"%s\"%n", op1.name);
		else if (op1.level > op2.level)
			System.out.printf("Dogru cevap: \"%s\"%n", op2.name);
		else
			System.out.println("Dogru cevap: Esit oncelik seviyesine sahiptirler");
	}
	
	public static void printResult(boolean isCorrect)
	{
		if (isCorrect)
			System.out.println("Dogru cevap! Bravo!");
		else
			System.out.println("Bir dahaki sefere..!");
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
	
	public void debugInfo()
	{
		System.out.println("Isim: " + this.name);
		System.out.println("Atom: " + this.atom);
		System.out.println("Seviye: " + this.level);
		System.out.println("Operand sayisi: " + this.operandCount);
		System.out.println("Onek: " + this.isPrefix);
		System.out.println("Araek: " + this.isInfix);
		System.out.println("Sonek: " + this.isPostfix);
		System.out.println("Soldan saga: " + this.isLeftToRight);
		System.out.println();

	}
}