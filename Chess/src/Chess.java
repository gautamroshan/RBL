//Things to do.
//checkmate

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
		System.out.println("Welcome to Chess! \nType and Enter:\n(i) for instruction,\n(s) for starting the game,\n(e) for exit:");
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
		
		
		int turnCounter=0;
		int currentPlayer;
		//Add turns for player1 and player2
	while(!continuer.equals("checkmate")){
		if(turnCounter%2==0)currentPlayer=1; else  currentPlayer=2;
		if(currentPlayer==1)System.out.println("Player 1"); else System.out.println("Player 2");
		System.out.println("Enter row and column separated by a space to select a piece");
		int srow = Integer.parseInt(input.next());
		int scolumn = Integer.parseInt(input.next());
		while(BlankCheck(ChessBoard[srow][scolumn])){
			System.out.println("The selection is empty, please try again");
			srow = Integer.parseInt(input.next());
			scolumn = Integer.parseInt(input.next());
			}
		if(currentPlayer==1&&isPlayer2(ChessBoard[srow][scolumn])){
			while (!isPlayer1(ChessBoard[srow][scolumn])){
				if(BlankCheck(ChessBoard[srow][scolumn]))System.out.println("The selection is empty, please try again");
				else System.out.println("You selected Player 2! Please try again and select Player1");
				srow = Integer.parseInt(input.next());
				scolumn = Integer.parseInt(input.next());
			}
		}
		else if(currentPlayer==2&&isPlayer1(ChessBoard[srow][scolumn])){
			while (!isPlayer2(ChessBoard[srow][scolumn])){
				if(BlankCheck(ChessBoard[srow][scolumn]))System.out.println("The selection is empty, please try again");
			else System.out.println("You selected Player 1! Please try again and select Player2");
				srow = Integer.parseInt(input.next());
				scolumn = Integer.parseInt(input.next());
			}
		}
		
			System.out.println(ChessBoard[srow][scolumn]+" in ("+srow+","+scolumn+")"+" is selected\nEnter row and column to move this piece");
			int drow = Integer.parseInt(input.next());
			int dcolumn = Integer.parseInt(input.next());
			while (!checkMove(ChessBoard,srow,scolumn,drow,dcolumn)){
				System.out.println("Illegal move! please try again");
				drow = Integer.parseInt(input.next());
				dcolumn = Integer.parseInt(input.next());
			}
			movePiece(ChessBoard,srow,scolumn,drow,dcolumn);
			//add undo move method
			//very Important!
			
				
			
			showBoard(ChessBoard);
			turnCounter++;
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
		boolean Check=false;
		if(legalPawn(board, srow, scolumn,  drow,  dcolumn) || legalRook(board, srow, scolumn, drow, dcolumn) || legalKnight(board, srow, scolumn, drow, dcolumn) || legalBishop(board, srow, scolumn, drow, dcolumn)||legalKing(board, srow, scolumn, drow, dcolumn)){
			Check =true;
		}
		return Check;	
	}
	
	
	
	public static boolean legalPawn(String board[][],int srow,int scolumn, int drow, int dcolumn){	
		//search for ways to shorten the code
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
		//search for ways to shorten the code
		boolean checker = false;
		if(board[srow][scolumn].equals("R")||board[srow][scolumn].equals("r") ||board[srow][scolumn].equals("Q")||board[srow][scolumn].equals("q")){
			int count=0;
			//Move R or r in -Y direction
			if((scolumn==dcolumn)&&((drow-srow)>0)){
				for(int i=srow; i<=drow; i++){
					if (board[i][dcolumn].equals(" ")){
					count++;
					}
				}
				if(isPlayer2(board[drow][dcolumn])&&isPlayer1(board[srow][scolumn])){
					count++;
				}
				else if(isPlayer1(board[drow][dcolumn])&&isPlayer2(board[srow][scolumn])){
					count++;
				}
				if ((count==drow-srow)&&(isPlayer1(board[srow][scolumn])) && (isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
				}
				else if ((count==drow-srow)&&(isPlayer2(board[srow][scolumn])) && (isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
					checker=true;
					}
				
			}
			//Move R or r in +Y direction
			if((scolumn==dcolumn)&&((drow-srow)<0)){
				for(int i=srow; i>=drow; i--){
					if (board[i][dcolumn].equals(" ")){
					count++;
					}
				}
				if(isPlayer2(board[drow][dcolumn])&&isPlayer1(board[srow][scolumn])){
					count++;
				}
				else if(isPlayer1(board[drow][dcolumn])&&isPlayer2(board[srow][scolumn])){
					count++;
				}
				if ((count ==srow-drow)&& (isPlayer1(board[srow][scolumn])) && (isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
				}
				else if ((count ==srow-drow)&&(isPlayer2(board[srow][scolumn])) && (isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
					checker=true;
					}
			}
			//Move R or r in -X direction
			if((srow==drow)&&((dcolumn-scolumn)<0)){
				for(int i=scolumn; i>=dcolumn; i--){
					if (board[drow][i].equals(" ")){
					count++;
					}
				}
				if(isPlayer2(board[drow][dcolumn])&&isPlayer1(board[srow][scolumn])){
					count++;
				}
				else if(isPlayer1(board[drow][dcolumn])&&isPlayer2(board[srow][scolumn])){
					count++;
				}
				if ((count==scolumn-dcolumn)&&(isPlayer1(board[srow][scolumn]))&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
				}
				else if ((count==scolumn-dcolumn)&&(isPlayer2(board[srow][scolumn]))&&(isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
					checker=true;
					}
			}
			//Move R or r in +X direction
			if((srow==drow)&&((dcolumn-scolumn)>0)){
				for(int i=scolumn; i<=dcolumn; i++){
					if (board[drow][i].equals(" ")){
					count++;
					}
				}
					if(isPlayer2(board[drow][dcolumn])&&isPlayer1(board[srow][scolumn])){
						count++;
					}
					else if(isPlayer1(board[drow][dcolumn])&&isPlayer2(board[srow][scolumn])){
						count++;
					}
				if ((count==dcolumn-scolumn)&&(isPlayer1(board[srow][scolumn]))&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
				}
				else if ((count==dcolumn-scolumn)&&(isPlayer2(board[srow][scolumn]))&&(isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
					checker=true;
					}
				//try this method in the first if statement to check the conditions so that you dont need to repeat it again and again
			}
			
			
		}
		return checker;
	}
	
	
	
	public static boolean legalKnight(String board[][],int srow,int scolumn,int drow,int dcolumn){
		boolean checker = false;
			if((board[srow][scolumn].equals("N")||board[srow][scolumn].equals("n"))){
				if((drow==srow+1)&&((dcolumn==scolumn+2)||(dcolumn==scolumn-2))){
					if(isPlayer1(board[srow][scolumn])&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
						checker=true;
					}
					else if(isPlayer2(board[srow][scolumn])&&(isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
						checker=true;
					}
				}
				if((drow==srow+2)&&((dcolumn==scolumn+1)||(dcolumn==scolumn-1))){
					if(isPlayer1(board[srow][scolumn])&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
						checker=true;
					}
					else if(isPlayer2(board[srow][scolumn])&&(isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
						checker=true;
					}
				}
				if((drow==srow-1)&&((dcolumn==scolumn-2)||(dcolumn==scolumn+2))){
					if(isPlayer1(board[srow][scolumn])&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
						checker=true;
					}
					else if(isPlayer2(board[srow][scolumn])&&(isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
						checker=true;
					}
				}
				if((drow==srow-2)&&((dcolumn==scolumn-1)||(dcolumn==scolumn+1))){
					if(isPlayer1(board[srow][scolumn])&&(isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
						checker=true;
					}
					else if(isPlayer2(board[srow][scolumn])&&(isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
						checker=true;
					}
				}
				
			}
		
		return checker;
		
	}
	
	
	public static boolean legalBishop(String board[][],int srow,int scolumn,int drow,int dcolumn){
		boolean checker=false;
		if(board[srow][scolumn].equals("B")||board[srow][scolumn].equals("b")||board[srow][scolumn].equals("Q")|| board[srow][scolumn].equals("q")){
			int count=0;
			if((srow>drow)&&(scolumn>dcolumn)){
				int j=scolumn;
			for(int i=srow; i>=drow;i--){
					if(board[i][j].equals(" ")){
						count++;
					}
					j--;
				}
			}
			else if((srow>drow)&&(scolumn<dcolumn)){
				int j=scolumn;
			for(int i=srow; i>=drow;i--){
				if(board[i][j].equals(" ")){
					count++;
				}
				j++;
			}
			}
			
			
			
			else if((srow<drow)&&(scolumn>dcolumn)){
				int j=scolumn;
			for(int i=srow; i<=drow;i++){
					if (board[i][j].equals(" ")){
						count++;
					}
					j--;
			}
			}
			else if((srow<drow)&&(scolumn<dcolumn)){
				int j=scolumn;
			for(int i=srow; i<=drow;i++){
					if (board[i][j].equals(" ")){
						count++;
					}
					j++;
			}
			}
			
				if(isPlayer2(board[drow][dcolumn])&&isPlayer1(board[srow][scolumn])){
					count++;
				}
				else if(isPlayer1(board[drow][dcolumn])&&isPlayer2(board[srow][scolumn])){
					count++;
				}
			if (((count==srow-drow)||(count==drow-srow)) &&(isPlayer1(board[srow][scolumn])) && (isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
			}
			else if (((count==srow-drow)||(count==drow-srow))&&(isPlayer2(board[srow][scolumn])) && (isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
				checker=true;
			}
			}	
		return checker;
		}
	
	
	
	
	public static boolean legalKing(String board[][], int srow, int scolumn, int drow, int dcolumn){
		boolean checker=false;
			if (board[srow][scolumn].equals("K")||board[srow][scolumn].equals("k")){
				for(int i=srow-1;i<=srow+1;i++){
					for(int j=scolumn-1;j<=scolumn+1;j++){
						if (drow==i && dcolumn==j){
							if ((isPlayer1(board[srow][scolumn])) && (isPlayer2(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
								checker=true;
							}
							else if ((isPlayer2(board[srow][scolumn])) && (isPlayer1(board[drow][dcolumn])||board[drow][dcolumn].equals(" "))){
								checker=true;
							}
						}
					}
				}
				
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
