package minimax;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class ReadWrite
{
    public static char[][] readBoard(String file) throws IOException
    {
        //initialize board to 's'
        char [][] board = new char[6][7];
        for(int k=0;k<6;k++)
        {
            for(int m=0;m<7;m++)
            {
                board[k][m]='e';
            }    
        }
        
        String st;
        //read board from file
        BufferedReader br = new BufferedReader(new FileReader(file));
        int jcol=0;
        while((st=br.readLine())!=null)
        {
            int row=5;
            char cols[] = st.toCharArray();            
            for(int i=0;i<=cols.length-1;i++)
            {
                board[row][jcol]=cols[i];
                row--;
            }
            jcol++; 
        }
        return board;
    }//readBoard ends 
    
    public static void printBoard(char [][] gboard) throws IOException   
    {
        
        //BufferedWriter bw = new BufferedWriter(new FileWriter(output));
        System.out.println("Board array \n");
        for(int k=0;k<6;k++)
        {
            for(int m=0;m<7;m++)
            {
                System.out.print(gboard[k][m]+" ");
                //bw.write(" "+gboard[k][m]+" ");
            }
            System.out.println();
            //bw.write("\n");
        }
        //bw.close();
    }     
    
}


class Heuristic
{
    @SuppressWarnings("empty-statement")
    
    public boolean gameDraw(char [][] board)
    {
        for(int k=0;k<6;k++)
        {
            for(int l=0;l<7;l++)
            {
                if(board[k][l]=='e')
                        return false;
            }
            
        }
        return true;
    }
    
    public int hValue(char [][] board) 
    {
        //heuristic function computation on the board state...
        int xa=0; int xb=0;
        int ya=0; int yb=0;
        // heuristic for rows...
        String reg1 = "eaa";      
        String reg2 = "aae";
        String reg3 = "eaaa";
        String reg4 = "aaae";
        String regw = "aaaa";
        
        String breg1 = "ebb";
        String breg2 = "bbe";
        String breg3 = "ebbb";
        String breg4 = "bbbe";
        String bregw = "bbbb";
        
        Pattern preg1 =  Pattern.compile(reg1);
        Pattern preg2 =  Pattern.compile(reg2);
        Pattern preg3 =  Pattern.compile(reg3);
        Pattern preg4 =  Pattern.compile(reg4);
        Pattern pregw =  Pattern.compile(regw);
        //Pattern preg6 =  Pattern.compile(reg1);
        Pattern bpreg1 =  Pattern.compile(breg1);
        Pattern bpreg2 =  Pattern.compile(breg2);
        Pattern bpreg3 =  Pattern.compile(breg3);
        Pattern bpreg4 =  Pattern.compile(breg4);
        Pattern bpregw =  Pattern.compile(bregw);

        //hVal for rows...
        //Xa,Ya ->...
        
        for(int arow=0;arow<6;arow++)
        {
            String aow = new String(board[arow]);
            Matcher m1 = preg1.matcher(aow);
            Matcher m2 = preg2.matcher(aow);
            Matcher m3 = preg3.matcher(aow);
            Matcher m4 = preg4.matcher(aow);
            
            Matcher bm1 = bpreg1.matcher(aow);
            Matcher bm2 = bpreg2.matcher(aow);
            Matcher bm3 = bpreg3.matcher(aow);
            Matcher bm4 = bpreg4.matcher(aow);
            //System.out.println(aow);
            Matcher mw = pregw.matcher(aow);
            Matcher bmw = bpregw.matcher(aow);
            
            if(mw.find())
            {
                return 1000;  //A wins..
            }
            if(bmw.find())
            {
                return (-1000); //B Wins...
            }
           
         
            while(m1.find()) 
            { //for 2 Alice
               xa++; 
            }
             while(m2.find()) 
            { //for 2 Alice
               xa++; 
            }
         
            while(m3.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(m4.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(bm1.find()) 
            { //for 2 Bill
               xb++; 
            }
         
            while(bm2.find()) 
            { //for 2 Bill
               xb++; 
            }
            
            while(bm3.find()) 
            { // for 3 Bill
               yb++; 
            }
            
            while(bm4.find()) 
            { // for 3 Bill
               yb++; 
            }
        }
       
        //Columns
        // heuristic for columns...
        int colmn=0;
        while(colmn<7)
        {
            char [] temp= new char[6];
            for(int ir=5;ir>=0;ir--)
            {
                temp[ir]=board[ir][colmn];
            }
            String bow = new String(temp); 
            
            Matcher m1 = preg1.matcher(bow);
            Matcher m2 = preg2.matcher(bow);
            Matcher m3 = preg3.matcher(bow);
            Matcher m4 = preg4.matcher(bow);
            
            Matcher bm1 = bpreg1.matcher(bow);
            Matcher bm2 = bpreg2.matcher(bow);
            Matcher bm3 = bpreg3.matcher(bow);
            Matcher bm4 = bpreg4.matcher(bow);
            
            Matcher mw = pregw.matcher(bow);
            Matcher bmw = bpregw.matcher(bow);
            
            if(mw.find())
            {
                return 1000;  //A wins..
            }
            if(bmw.find())
            {
                return (-1000); //B Wins...
            }
            
            while(m1.find()) 
            { //for 2 Alice
               xa++; 
            }
            
            while(m2.find()) 
            { //for 2 Alice
               xa++; 
            }
         
            while(m3.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(m4.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(bm1.find()) 
            { //for 2 Bill
               xb++; 
            }
         
            while(bm2.find()) 
            { //for 2 Bill
               xb++; 
            }
            
            while(bm3.find()) 
            { // for 3 Bill
               yb++; 
            }
            
            while(bm4.find()) 
            { // for 3 Bill
               yb++; 
            }
            //incr colmn
            colmn++;
        }
         
       //Diagonals
       // heuristic for diagonals...
       // for lower half diagonals...
       int drow=0;
       
       while(drow<6)
       {
           int ri,rj;
           ri=drow;
           char [] temp = new char[10];
           int ct=0;
           for(rj=0; ri<6 && rj<7 ;ri++,rj++)
           {
            temp[ct]=board[ri][rj];   
            ct++; 
           }
            String lida = new String(temp);
           
            Matcher m1 = preg1.matcher(lida);
            Matcher m2 = preg2.matcher(lida);
            Matcher m3 = preg3.matcher(lida);
            Matcher m4 = preg4.matcher(lida);
            
            Matcher bm1 = bpreg1.matcher(lida);
            Matcher bm2 = bpreg2.matcher(lida);
            Matcher bm3 = bpreg3.matcher(lida);
            Matcher bm4 = bpreg4.matcher(lida);
            
            Matcher mw = pregw.matcher(lida);
            Matcher bmw = bpregw.matcher(lida);
            
            if(mw.find())
            {
                return 1000;  //A wins..
            }
            if(bmw.find())
            {
                return (-1000); //B Wins...
            }
            
            while(m1.find()) 
            { //for 2 Alice
               xa++;
               //System.out.println("Xa in m1: "+xa);
            }
             
            while(m2.find()) 
            { //for 2 Alice
               xa++; 
               //System.out.println("Xa in m2: "+xa);
            }
         
            while(m3.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(m4.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(bm1.find()) 
            { //for 2 Bill
               xb++; 
            }
         
            while(bm2.find()) 
            { //for 2 Bill
               xb++; 
            }
            
            while(bm3.find()) 
            { // for 3 Bill
               yb++; 
            }
            
            while(bm4.find()) 
            { // for 3 Bill
               yb++; 
            }
            
           
           //incr drow
           drow++;
       }
 
       //finding heuristic for upper half diagonals...
      
        int dcol=1;
       
        while(dcol<6)
       {
           int ri,rj;
           rj=dcol;
           char [] temp = new char[10];
           int ct=0;
           for(ri=0; ri<6 && rj<7 ;ri++,rj++)
           {
            temp[ct]=board[ri][rj];   
            ct++; 
           }
           String lida = new String(temp);
            Matcher m1 = preg1.matcher(lida);
            Matcher m2 = preg2.matcher(lida);
            Matcher m3 = preg3.matcher(lida);
            Matcher m4 = preg4.matcher(lida);
            
            Matcher bm1 = bpreg1.matcher(lida);
            Matcher bm2 = bpreg2.matcher(lida);
            Matcher bm3 = bpreg3.matcher(lida);
            Matcher bm4 = bpreg4.matcher(lida);
            
            Matcher mw = pregw.matcher(lida);
            Matcher bmw = bpregw.matcher(lida);
            
            if(mw.find())
            {
                return 1000;  //A wins..
            }
            if(bmw.find())
            {
                return (-1000); //B Wins...
            }
            
            while(m1.find()) 
            { //for 2 Alice
               xa++; 
            }
             
            while(m2.find()) 
            { //for 2 Alice
               xa++; 
            }
         
            while(m3.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(m4.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(bm1.find()) 
            { //for 2 Bill
               xb++; 
            }
         
            while(bm2.find()) 
            { //for 2 Bill
               xb++; 
            }
            
            while(bm3.find()) 
            { // for 3 Bill
               yb++; 
            }
            
            while(bm4.find()) 
            { // for 3 Bill
               yb++; 
            }
            
           
           //incr dcol
           dcol++;
       }
     
      //heuristic for reverse lower diagonals...
        
       int rdrow=0;
       
       while(rdrow<6)
       {
           int ri,rj;
           ri=rdrow;
           char [] temp = new char[10];
           int ct=0;
           for(rj=6; ri<6 && rj>=0 ;ri++,rj--)
           {
            temp[ct]=board[ri][rj];   
            ct++; 
           }
           String lida = new String(temp);
           //System.out.println(lida);
            Matcher m1 = preg1.matcher(lida);
            Matcher m2 = preg2.matcher(lida);
            Matcher m3 = preg3.matcher(lida);
            Matcher m4 = preg4.matcher(lida);
            
            Matcher bm1 = bpreg1.matcher(lida);
            Matcher bm2 = bpreg2.matcher(lida);
            Matcher bm3 = bpreg3.matcher(lida);
            Matcher bm4 = bpreg4.matcher(lida);
            
            Matcher mw = pregw.matcher(lida);
            Matcher bmw = bpregw.matcher(lida);
            
            if(mw.find())
            {
                return 1000;  //A wins..
            }
            if(bmw.find())
            {
                return (-1000); //B Wins...
            }
            
            while(m1.find()) 
            { //for 2 Alice
               xa++; 
            }
             
            while(m2.find()) 
            { //for 2 Alice
               xa++; 
            }
         
            while(m3.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(m4.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(bm1.find()) 
            { //for 2 Bill
               xb++; 
            }
         
            while(bm2.find()) 
            { //for 2 Bill
               xb++; 
            }
            
            while(bm3.find()) 
            { // for 3 Bill
               yb++; 
            }
            
            while(bm4.find()) 
            { // for 3 Bill
               yb++; 
            }
            
           
           //incr rdrow
           rdrow++;
       }
       
        //heuristic for reverse upper diaognals...
        
       int rdcol=5;
       
       while(rdcol>=0)
       {
           int ri,rj;
           rj=rdcol;
           char [] temp = new char[10];
           int ct=0;
           for(ri=0; ri<6 && rj>=0 ;ri++,rj--)
           {
            temp[ct]=board[ri][rj];   
            ct++; 
           }
           String lida = new String(temp);
           //System.out.println(lida);
            Matcher m1 = preg1.matcher(lida);
            Matcher m2 = preg2.matcher(lida);
            Matcher m3 = preg3.matcher(lida);
            Matcher m4 = preg4.matcher(lida);
            
            Matcher bm1 = bpreg1.matcher(lida);
            Matcher bm2 = bpreg2.matcher(lida);
            Matcher bm3 = bpreg3.matcher(lida);
            Matcher bm4 = bpreg4.matcher(lida);
            
            Matcher mw = pregw.matcher(lida);
            Matcher bmw = bpregw.matcher(lida);
            
            if(mw.find())
            {
                return 1000;  //A wins..
            }
            if(bmw.find())
            {
                return (-1000); //B Wins...
            }
                  
            while(m1.find()) 
            { //for 2 Alice
               xa++; 
            }
             
            while(m2.find()) 
            { //for 2 Alice
               xa++; 
            }
         
            while(m3.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(m4.find()) 
            { // for 3 Alice
               ya++; 
            }
            
            while(bm1.find()) 
            { //for 2 Bill
               xb++; 
            }
         
            while(bm2.find()) 
            { //for 2 Bill
               xb++; 
            }
            
            while(bm3.find()) 
            { // for 3 Bill
               yb++; 
            }
            
            while(bm4.find()) 
            { // for 3 Bill
               yb++; 
            }
            
           
           //incr rdcol
           rdcol--;
       }
       
        //System.out.println("Diagonals Reverse Upper");
        xa-=ya;
        xb-=yb;
        
        //checking if Draw condition...
        if(gameDraw(board))
        {
            return 0;
        }
       
        int res = (xa-xb)+(5*(ya-yb));
        return res;
        
    }
}




class GameNode
{
    char [][] board = new char[6][7];
    int value;
    int level;
    
    GameNode()
    {//default constructor
        value=Integer.MIN_VALUE;
        level=0;
    }
    GameNode(char [][] init_board,int levl)
    {
        this.board=init_board;
        this.value=Integer.MIN_VALUE;
        this.level=levl;
    }
    
    void getHValue()
    { //call heuristic function to get utility value...
        Heuristic h = new Heuristic();
        this.value = h.hValue(this.board);
    }
    
    int getNodeType()
    {
        //maxNode=1
        //minNode=0
        return (level%2)==0?1:0;
    }
}

class Algorithm
{
    public boolean cutOff(GameNode g)
    {
        int lvl=g.level;
        return lvl==4;
    }
    
    public int alphaBeta(GameNode ga,int alpha,int beta) throws IOException
    {
        int nxt_lvl=ga.level+1;
       
        //generating successors...
        for(int cin=0;cin<7;cin++)
        {
            char [][] temp_board = new char[6][7];
            for(int k=0;k<6;k++)
            {
                for(int l=0;l<7;l++)
                {
                    temp_board[k][l]=ga.board[k][l];
                }
            }
            
            int rowin=5;
            while('e'!=temp_board[rowin][cin])
            {
                rowin--;
                if(rowin==-1)
                        break;
            }
            
            if(-1==rowin)
                    continue;   //curr colmn has no space..go to next colmn
            
            temp_board[rowin][cin]='a';   //put in Min player move...
            GameNode gg =new GameNode(temp_board,nxt_lvl);
            
            System.out.println("A1: "+(cin+1));
               
            alpha = Math.max(alpha,maxVal(gg,alpha,beta));
        
      }
        return alpha;
    }
    
    public int maxVal(GameNode gm, int alpha, int beta) throws IOException
    {
        if(cutOff(gm))
        {
            gm.getHValue(); //puts the heuristic value into the value var of the node..
            return gm.value;
        }
        //sucessors for current node...
       
        int nxt_lvl=gm.level+1;
        
        for(int cin=0;cin<7;cin++)
        {
            char [][] temp_board = new char[6][7];
            for(int k=0;k<6;k++)
            {
                for(int l=0;l<7;l++)
                {
                    temp_board[k][l]=gm.board[k][l];
                }
            }
            
            int rowin=5;
            while('e'!=temp_board[rowin][cin])
            {
                rowin--;
                if(rowin==-1)
                        break;
            }
            
            if(-1==rowin)
                    continue;   //curr colmn has no space..go to next colmn
            
            temp_board[rowin][cin]='b';   //put in Min player move...
            GameNode gg =new GameNode(temp_board,nxt_lvl);
            //if wining before 4 ply...
            Heuristic hh = new Heuristic();
            int heur = hh.hValue(gg.board);
            if(heur==-1000)
            {
                if(gg.level==2)
                {
                    System.out.println("|-B2: "+(cin+1)+"; h="+heur);
                }
                continue;
            }
            
            if(gg.level==2)
            {
                System.out.println("|-B2: "+(cin+1));
            }
                
            alpha = Math.max(alpha,minVal(gg,alpha,beta));

             if(gg.level==4)
            {
                System.out.println("|-|-|-B4: "+(cin+1)+"; "+"h="+gg.value);
            }
            if(alpha>=beta) // A pruning...
            {
                String prune ="";
                for(int ki=cin+1;ki<7;ki++)
                {
                    int krowin=5;
                    while('e'!=temp_board[krowin][cin])
                    {
                        krowin--;
                        if(krowin==-1)
                                break;
                    }
            
                    if(-1==krowin)
                            continue;
                    prune+=(ki+1)+", ";
                    
                }
                String pout="";
                if(gg.level==2)
                     pout+="|-B2: pruning "+prune+"; alpha="+alpha+" beta="+beta;
                if(gg.level==4)
                     pout+="|-|-|-B4: pruning "+prune+"; alpha="+alpha+" beta="+beta;
                
                System.out.println(pout);
                return beta;
            }

        }
      return alpha;   //after exploring the entire subtree...
      
  }
    
    
    
    public int minVal(GameNode gn, int alpha, int beta) throws IOException
    {
         if(cutOff(gn))
        {
            gn.getHValue(); //puts the heuristic value into the value var of the node...
            return gn.value;
        }
        
        int nxt_lvl=gn.level+1;
        
        for(int cin=0;cin<7;cin++)
        {
            char [][] temp_board = new char[6][7];
            
            for(int k=0;k<6;k++)
            {
                for(int l=0;l<7;l++)
                {
                    temp_board[k][l]=gn.board[k][l];
                }
            }
            int rowin=5;
           
            while('e'!=temp_board[rowin][cin])
            {
                rowin--;  
                if(rowin==-1)
                        break;
            }
            
            if(rowin==-1)
                    continue;   //curr colmn has no space..go to next colmn
            
            temp_board[rowin][cin]='a';  //put in Max player move...
            GameNode gg =new GameNode(temp_board,nxt_lvl);
            //if wining before 4 ply...
            Heuristic hh = new Heuristic();
            int heur = hh.hValue(gg.board);
            if(heur==1000)
            {
                if(gg.level==3)
                {
                    System.out.println("|-|-A3: "+(cin+1)+"; h="+heur);
                }
                continue;
            }
            
            if(gg.level==3)
            {
                System.out.println("|-|-A3: "+(cin+1));
            }
            
            beta = Math.min(beta,maxVal(gg,alpha,beta));
          
            if(beta<=alpha)       // B pruning...
            {
                String prune ="";
                for(int ki=cin+1;ki<7;ki++)
                {
                    int krowin=5;
                    while('e'!=temp_board[krowin][cin])
                    {
                        krowin--;
                        if(krowin==-1)
                                break;
                    }
            
                    if(-1==krowin)
                            continue;
                    prune+=(ki+1)+", ";
                    
                }
            
                if(gg.level==3)
                {
                    String pout="|-|-A3: pruning "+prune+"; alpha="+alpha+" beta="+beta;
                    System.out.println(pout);  
                }

                return alpha;  
            }        
        }
        return beta;  
   }
}




public class MiniMax 
{
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input file as arguments");
        
        
        char board[][]=ReadWrite.readBoard("hw2_input.txt"); // args[0]
        //String out="output.txt";  //args[1]
        //initialize å and ß here and pass to the maxValue function
        int alpha=-10000;
        int beta=10000;
        GameNode g = new GameNode(board,0);
        Algorithm a = new Algorithm();
        
        int resVal=a.alphaBeta(g, alpha, beta);
 
        ReadWrite.printBoard(g.board);
        System.out.println("GameVal"+resVal);
       
       
       
               
       
    }
    
}
