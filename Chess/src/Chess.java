//Game is still long way to go.
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
		String continuer ="";	
	while(!continuer.equals("checkmate")){
		System.out.println("Enter row and column separated by a space to select a piece");
		int srow1 = Integer.parseInt(input.next());
		int scolumn1 = Integer.parseInt(input.next());
		while(BlankCheck(ChessBoard[srow1][scolumn1])){
			System.out.println("The selected row is empty, please try again");
			srow1 = Integer.parseInt(input.next());
			scolumn1 = Integer.parseInt(input.next());
			}
			System.out.println(ChessBoard[srow1][scolumn1]+" in ("+srow1+","+scolumn1+")"+" is selected\nEnter row and column to move this piece");
			int drow1 = Integer.parseInt(input.next());
			int dcolumn1 = Integer.parseInt(input.next());
			// check the piece
			// call the method to check eligibilty of the move
			while (!checkMove(ChessBoard,srow1,scolumn1,drow1,dcolumn1)){
				System.out.println("Illegal move! please try again");
				drow1 = Integer.parseInt(input.next());
				dcolumn1 = Integer.parseInt(input.next());
			}
			movePiece(ChessBoard,srow1,scolumn1,drow1,dcolumn1);
			//add undo move method
			//very Important!
			
				
			
			showBoard(ChessBoard);
		}
		}
	}
	public static boolean isPlayer1(String piece){
		String[] Player1={"P","R","B","K","Q","N"};
		boolean check=false;
		for (int i=0;i<Player1.length; i++){
			if(piece.equals(Player1[i])){
				check=true;
			}	
		}
		return check;
	}
	public static boolean isPlayer2(String piece){
		String[] Player2={"p","r","b","k","q","n"};
		boolean check=false;
		for (int i=0;i<Player2.length; i++){
			if(piece.equals(Player2[i])){
				check=true;
			}	
		}
		return check;
	}
	public static boolean checkMove(String board[][],int srow,int scolumn, int drow, int dcolumn){
		//This is a method for checking the validity of a move. 
		//All legal* methods are checked in this method to continue on game or to ask user to re-enter drow and dcolumn
		boolean Check=false;
		if(legalPawn(board, srow, scolumn,  drow,  dcolumn) || legalRook(board, srow, scolumn, drow, dcolumn)){
		//add other legal methods as
		//if(legalPawn(....) || legalRook(...) || legalKnight(...) || ... ... {
			Check =true;
		}
		return Check;
		
	}
	public static boolean legalPawn(String board[][],int srow,int scolumn, int drow, int dcolumn){
		
		boolean checker = false;
		if((board[srow][scolumn].equals("P"))){
			if((board[srow+1][scolumn].equals(" "))&&(drow==srow+1)&&( dcolumn==scolumn)){
				checker = true;
			}
			else if(((srow+1)==drow) && ((scolumn-1)==dcolumn)){
				if(isPlayer2(board[srow+1][scolumn-1]))checker=true;
			}
			else if(((srow+1)==drow) && ((scolumn+1)==dcolumn)  ){
				if(isPlayer2(board[srow+1][scolumn+1]))checker=true;
			}
		}
		else if((board[srow][scolumn].equals("p"))){
			if((board[srow-1][scolumn].equals(" "))&&(drow==srow-1)&&( dcolumn==scolumn)){
				checker = true;
				}
			else if(((srow-1)==drow) && ((scolumn+1)==dcolumn)  ){
				if(isPlayer1(board[srow-1][scolumn+1]))checker=true;
			}
			else if(((srow-1)==drow) && ((scolumn-1)==dcolumn)){
				if(isPlayer1(board[srow-1][scolumn-1]))checker=true;
			}
		
		}
		return checker;
	}
	public static boolean legalRook(String board[][],int srow,int scolumn,int drow,int dcolumn){
		boolean checker = false;
		if((board[srow][scolumn].equals("R"))){
			int count=0;
			//Move R in -Y direction
			if((scolumn==dcolumn)&&((drow-srow)>0)){
				for(int i=srow; i<=drow; i++){
					if (board[i][dcolumn].equals(" ")){
					count++;
					}
				}
				if ((count==drow-srow)&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
				}
			}
			//Move R in +Y direction
			if((scolumn==dcolumn)&&((drow-srow)<0)){
				for(int i=srow; i>=drow; i--){
					if (board[i][dcolumn].equals(" ")){
					count++;
					}
				}
				if ((count ==srow-drow)&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
				}
			}
			//Move R in -X direction
			if((srow==drow)&&((dcolumn-scolumn)<0)){
				for(int i=scolumn; i>=dcolumn; i--){
					if (board[drow][i].equals(" ")){
					count++;
					}
				}
				if ((count==scolumn-dcolumn)&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
				}
			}
			//Move R in +X direction
			if((srow==drow)&&((dcolumn-scolumn)>0)){
				for(int i=scolumn; i<=dcolumn; i++){
					if (board[drow][i].equals(" ")){
					count++;
					}
				}
				if ((count==dcolumn-scolumn)&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
				}
			}
			//All the movements for player2 are to be added
			
		}
		return checker;
	}
	public static void movePiece(String chessBoard[][],int srow,int scolumn, int drow, int dcolumn){
		chessBoard[drow][dcolumn]=chessBoard[srow][scolumn];
		chessBoard[srow][scolumn]=" ";
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
