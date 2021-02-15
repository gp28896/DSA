import java.util.Scanner;
class XnO{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		int b[][]=new int[4][4],move=0,x=-1,y=-1;
		XnO g=new XnO();
		for(int i=1;i<4;i++){
			for(int j=1;j<4;j++){b[i][j]=-1;}}
		while(!g.gameOver(b,move,x,y)){
			g.printBoard(b,move);
			x=sc.nextInt();
			y=sc.nextInt();
			if(b[x][y]!=-1){
				System.out.println("Invalid move ");
				continue;
			}
			b[x][y]=move%2;
			move++;
		}
	}
	boolean gameOver(int b[][],int move,int x, int y){
		if(move==9)return true;
		if(move==0)return false;
		
			if(b[1][1]!=-1&&b[1][1]==b[1][2]&&b[1][2]==b[1][3]){//row1
				System.out.println("player "+(b[1][1]+1) + "wins");
				return true;
			}
			if(b[1][1]!=-1&&b[1][1]==b[2][2]&&b[2][2]==b[3][3]){//diag
				System.out.println("player "+(b[1][1]+1) + "wins");
				return true;
			}
			if(b[1][1]!=-1&&b[1][1]==b[2][1]&&b[1][1]==b[3][1]){//col1
				System.out.println("player "+(b[1][1]+1) + "wins");
				return true;
			}

			if(b[2][2]!=-1&&b[1][2]==b[2][2]&&b[1][2]==b[3][2]){//col2
				System.out.println("player "+(b[1][1]+1) + "wins");
				return true;
			}
			if(b[3][3]!=-1&&b[1][3]==b[2][3]&&b[1][3]==b[3][3]){//col3
				System.out.println("player "+(b[1][1]+1) + "wins");
				return true;
			}
			if(b[1][3]!=-1&&b[1][3]==b[2][2]&&b[1][3]==b[3][1]){//rev dia
				System.out.println("player "+(b[1][1]+1) + "wins");
				return true;
			}
			if(b[2][1]!=-1&&b[2][1]==b[2][2]&&b[2][2]==b[2][3]){//row2
				System.out.println("player "+(b[1][1]+1) + "wins");
				return true;
			}
			if(b[3][1]!=-1&&b[3][1]==b[3][2]&&b[3][2]==b[3][3]){//row3
				System.out.println("player "+(b[1][1]+1) + "wins");
				return true;
			}
		
		return false;
	}
	void printBoard(int b[][],int move){
		System.out.println("tic-tac-toe\tplayer: "+(move%2+1)+"enter position to add "+ move%2);
		for(int i=1;i<4;i++){
			for(int j=1;j<4;j++){
				System.out.print(" | "+b[i][j]+" | ");
			}
                        System.out.println();
		}
	}
}