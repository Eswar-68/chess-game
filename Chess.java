import java.util.*;
public class Chess{
	static int board_size=8;
	static char[][] board=new char[board_size][board_size];
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		initializeBoard();
		printBoard();
		boolean white_turn=true;
		String move;
		while(true){
			System.out.print((white_turn?"white":"black")+"'s turn:");
			move=sc.next();
			if(move.equalsIgnoreCase("exit")){
				System.out.println("Game exited.");
				break;
			}
			if(isValidMove(move,white_turn)){
				movePiece(move);
				printBoard();
				white_turn=!white_turn;
			}
			else
				System.out.println("Invalid move!");
		}
	}
	public static void initializeBoard(){
		String board_struct=
			"rnbkqbnr"+
			"pppppppp"+
			"........"+
			"........"+
			"........"+
			"........"+
			"PPPPPPPP"+
			"RNBKQBNR";
		for(int i=0;i<board_size;i++)
			for(int j=0;j<board_size;j++)
				board[i][j]=board_struct.charAt(i*board_size+j);
	}
	public static void printBoard(){
		System.out.println("  a b c d e f g h");
		for(int i=0;i<board_size;i++){
			System.out.print(8-i+" ");
			for(int j=0;j<board_size;j++){
				System.out.print(board[i][j]+" ");
			}
			System.out.println(8-i);
		}
		System.out.println("  a b c d e f g h");
	}
	public static boolean isValidMove(String move,boolean white_turn){
		if(move.length()!=4)
			return false;

		int startX=move.charAt(1)-'1';
		int startY=move.charAt(0)-'a';
		int endX=move.charAt(3)-'1';
		int endY=move.charAt(2)-'a';
		char source=board[7-startX][startY];
		char target=board[7-endX][endY];

		if(source=='.')
			return false;
		if(startX<0||startX>7||startY<0||startY>7)
			return false;
		if(endX<0||endX>7||endY<0||endY>7)
			return false;
		if(white_turn&&Character.isLowerCase(source))
			return false;
		if(!white_turn&&Character.isUpperCase(source))
			return false;
		if(Character.isUpperCase(source)&&Character.isUpperCase(target))
			return false;
		if(Character.isLowerCase(source)&&Character.isLowerCase(target))
			return false;

		return true;
	}
	public static void movePiece(String move){
		int startX=move.charAt(1)-'1';
		int startY=move.charAt(0)-'a';
		int endX=move.charAt(3)-'1';
		int endY=move.charAt(2)-'a';
		board[7-endX][endY]=board[7-startX][startY];
		board[7-startX][startY]='.';
	}
}