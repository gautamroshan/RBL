public class Chess{
	public static void main(String[]args){
		String[][] chessBoard = new String[8][8];
		for(int i = 0; i<chessBoard.length; i++){
			for(int j = 0; j<chessBoard.length; j++){
				chessBoard[i][j]=" ";
			}
		}
		String King1,king2 ="K";
		String Queen1,Queen2 ="Q";
		String[] Rook1 = new String[2];
		String[] Rook2 = new String[2];
		String[] Knight1 = new String[2];
		String[] Knight2 = new String[2];
		String[] Bishop1 = new String[2];
		String[] Bishop2 = new String[2];
		String[] Pawn1 = new String[8];
		String[] Pawn2 = new String[8];
		
		showBoard(chessBoard);
		
		
	}
	public static void showBoard(String ChessBoard[][]){
		System.out.print("  ");
		for (int k=0; k<ChessBoard.length;k++ ){
			System.out.print(" "+ k+"");
		}
		System.out.println();
		for(int i = 0; i<ChessBoard.length; i++){		
			System.out.print(i+" ");
			for(int j = 0; j<ChessBoard.length; j++){
				System.out.print("|"+ChessBoard[i][j]);
				
			}
			System.out.println("|");
		}
	}
	}
