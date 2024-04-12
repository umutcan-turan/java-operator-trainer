package java_operator_trainer;

class JavaOperatorTrainerApp {
	public static void main(String [] args)
	{
		JavaOperatorTrainerApp.run();
	}
}

class Operator {
	String name;
	String token;
	int level;
	boolean isPrefix;
	boolean isInfix;
	boolean isPostfix;
	
	public Operator(String name, String token, int level, boolean isPrefix, boolean isInfix, boolean isPostfix)
	{
		name = name;
		token = token;
		level = level;
		isPrefix = isPrefix;
		isInfix = isInfix;
		isPostfix = isPostfix;
	}
}