import java.util.Scanner;
public class Chess{
	public static void main(String[]args){
		String[][] ChessBoard = new String[8][8];
		for(int i = 0; i<ChessBoard.length; i++){
			for(int j = 0; j<ChessBoard.length; j++){
				ChessBoard[i][j]=" ";
			}
		}
		Scanner input = new Scanner(System.in);
		System.out.println("Type and Enter:\n(i) for instruction,\n(s) for starting the game,\n(e) for exit:");
		String command = input.nextLine();
		if(command.equals("i")){
			System.out.println(instruction());
			System.out.println("Type and Enter (s) to start the game now or (e) to exit");
			command = input.next();
		}
		if(command.equals("e")){
			System.out.println("Thanks for your visit!");
			System.exit(0);
			
		}
		if(command.equals("s")){
		starter(ChessBoard);
		showBoard(ChessBoard);
		System.out.println("Enter row and column separated by a space to select a piece");
		
		int row1 = Integer.parseInt(input.next());
		int column1 = Integer.parseInt(input.next());
		while(BlankCheck(ChessBoard[row1][column1])){
			System.out.println("The selected row is empty, please try again");
			 row1 = Integer.parseInt(input.next());
			 column1 = Integer.parseInt(input.next());
		}
			System.out.println(ChessBoard[row1][column1]+" is selected");
			//"\nEnter row and column to move this piece"
			System.out.println("Game is building!\nSee you soon! Thanks for trying");
		
		}
	}
	public static void starter(String chessBoard[][]){
		
		String King1 = "K";
		String King2 = "k";
		String Queen1 = "Q";
		String Queen2 ="q";
		String[] Rook1 = {"R","R"};
		String[] Rook2 = {"r","r"};
		String[] Knight1 = {"N","N"};
		String[] Knight2 = {"n","n"};
		String[] Bishop1 = {"B","B"};
		String[] Bishop2 = {"b","b"};
		String[] Pawn1 = {"P","P","P","P","P","P","P","P"};
		String[] Pawn2 = {"p","p","p","p","p","p","p","p"};
		for (int i=0;i<chessBoard.length;i++){
			chessBoard[1][i]=Pawn1[i];
			chessBoard[6][i]=Pawn2[i];
		}
		for (int i=0; i<chessBoard.length;i+=7){
			for(int j=0; j<2; j++){
			chessBoard[0][i]=Rook1[j];
			chessBoard[7][i]=Rook2[j];
			}
		}
		for (int i=0; i<chessBoard.length;i+=5){
			for(int j=0; j<2; j++){
		chessBoard[0][i+1]=Knight1[j];
		chessBoard[7][i+1]=Knight2[j];
			}
		}
		for (int i=0; i<6;i+=3){
			for(int j=0; j<2; j++){
		chessBoard[0][i+2]=Bishop1[j];
		chessBoard[7][i+2]=Bishop2[j];
			}
		}
			chessBoard[0][3]=King1;
			chessBoard[7][3]=King2;
			chessBoard[0][4]=Queen1;
			chessBoard[7][4]=Queen2;
	}
	public static void showBoard(String Board[][]){
		System.out.print("  ");
		for (int k=0; k<Board.length;k++ ){
			System.out.print(" "+ k+"");
		}
		System.out.println();
		for(int i = 0; i<Board.length; i++){		
			System.out.print(i+" ");
			for(int j = 0; j<Board.length; j++){
				System.out.print("|"+Board[i][j]);
				
			}
			System.out.println("|");
		}
	}
	public static Boolean BlankCheck(String box){
		Boolean checker=false;
		if (box.equals(" ")){
			checker=true;
		}
		
		return checker;
	}
	public static String instruction(){
		String set ="Player1 is Capital\nPlayer2 is small"
				+ "\nK->king, Q->queen, B->bishop, N->knight, R->rook, P->pawn"
				+ "\nType row and column separated by space and press enter to select your piece"
				+ "\nThen Type row and column separated by space and enter to move selected piece to desired place";
		return set;
	}
	}
