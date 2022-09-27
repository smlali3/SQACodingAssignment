package java_programming;

public class Main {

	public static void main(String[] args) {

		System.out.println(NameUtility.convertNameToInitials("")); 
		System.out.println(NameUtility.convertNameToInitials("Bruno Mars")); // B.M.
		System.out.println(NameUtility.convertNameToInitials("Dave M Jones")); // D.M.J.
		System.out.println(NameUtility.convertNameToInitials("MichaelSmith")); // M.
		System.out.println(NameUtility.convertNameToInitials("Dave M Jones Bruno Mars MichaelSmith")); // D.M.J.B.M.M.

	}
}
