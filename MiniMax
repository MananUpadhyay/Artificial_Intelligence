import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

class ReadWrite
{
    public static char[][] readBoard(String file) throws IOException
    {
        //initialize board to 'e'
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
    StringBuffer sb = new StringBuffer(); //holds the output tree...
    int [] move = new int[7];
    
    Algorithm()
    {
        for(int ji=0;ji<move.length;ji++)
        {
          move[ji]=Integer.MIN_VALUE;
        }
    }
    
    public void printFile(String out) throws IOException
    { //writung the output to the output file...
        String output = sb.toString();
        BufferedWriter bw = new BufferedWriter(new FileWriter(out));
        bw.write(output);
        bw.close();
        System.out.println("Output written to: "+out);
    }
    
    public void firstMove()
    { //next move predictor...
        int max = move[0];
        int max_indx=0;
        for(int mi=1;mi<move.length;mi++)
        {
            if(move[mi]>max)
            {
                //System.out.println(move[mi]);
                max=move[mi];
                max_indx=mi;
            }
        }
        int first_move = max_indx+1;
        System.out.println("first move: "+first_move);
        sb.append("first move: ").append(first_move);
    }
    
    public boolean cutOff(GameNode g)
    {
        int lvl=g.level;
        return lvl==4;
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
            
            temp_board[rowin][cin]='a';   //put in Max player move...
            GameNode gg =new GameNode(temp_board,nxt_lvl);
            //if wining before 4 ply...
            Heuristic hh = new Heuristic();
            int heur = hh.hValue(gg.board);
            if(heur==1000)
            {
                if(gg.level==1)
                {
                  alpha=Math.max(alpha,1000);
                  System.out.println("A1: "+(cin+1)+"; h="+heur);  
                  sb.append("A1: ").append(cin+1).append("; h=").append(heur).append("\n");
                }
                if(gg.level==3)
                {
                    alpha=Math.max(alpha,1000);
                    System.out.println("|-|-A3: "+(cin+1)+"; h="+heur);
                    sb.append("|-|-A3: ").append(cin+1).append("; h=").append(heur).append("\n");
                } 
                //A1|A3 pruning here...
                if(alpha>=beta) // A pruning...
                {   //printing pruned nodes...
                    boolean pflag=false;
                    String prune ="";
                    for(int ki=cin+1;ki<7;ki++)
                    {
                        int krowin=5;
                        while('e'!=gg.board[krowin][ki])
                        {
                            krowin--;
                            if(krowin==-1)
                                    break;
                        }
                        if(-1==krowin)
                                continue;

                        prune+=(ki+1)+", ";
                        pflag=true;

                    }
                    if(prune.length()>0)
                             prune = prune.substring(0,prune.length()-2);
                    String pout="";
                    if(gg.level==1)
                        pout+="A1: pruning "+prune+"; alpha="+alpha+", beta="+beta;
                    if(gg.level==3)
                        pout+="|-|-A3: pruning "+prune+"; alpha="+alpha+", beta="+beta;
                    if(pflag)
                    {
                        //if(prune.length())
                        System.out.println(pout);
                        sb.append(pout).append("\n");
                    }
                
                    return beta;
                }
                //A1|A3 pruning ends...
                 if(gg.level==1)   //saving A values for predicting the first move...
                    {              // if the game ends on the first ply...
                        move[cin] = alpha;
                    }
                continue;  //go to next column...
            }  //heur=1000 ends...  
            
            if(gg.level==1)
            {
                System.out.println("A1: "+(cin+1));
                sb.append("A1: ").append(cin+1).append("\n");
            }
            
            if(gg.level==3)
            {
                System.out.println("|-|-A3: "+(cin+1));
                sb.append("|-|-A3: ").append(cin+1).append("\n");
            }
                 
            alpha = Math.max(alpha,minVal(gg,alpha,beta));  //call Min
            
            if(alpha>=beta) // A pruning...
            {   //printing pruned nodes...
                boolean pflag=false;
                String prune ="";
                for(int ki=cin+1;ki<7;ki++)
                {
                    int krowin=5;
                    while('e'!=gg.board[krowin][ki])
                    {
                        krowin--;
                        if(krowin==-1)
                                break;
                    }
                    if(-1==krowin)
                            continue;
                    
                    prune+=(ki+1)+", ";
                    pflag=true;

                }
                if(prune.length()>0)
                           prune = prune.substring(0,prune.length()-2);
                String pout="";
                //String sprune = prune.substring(0,prune.length()-1);
                if(gg.level==1)
                     pout+="A1: pruning "+prune+"; alpha="+alpha+", beta="+beta;
                if(gg.level==3)
                     pout+="|-|-A3: pruning "+prune+"; alpha="+alpha+", beta="+beta;
                if(pflag)
                {
                    System.out.println(pout);
                    sb.append(pout).append("\n");
                }
                
                return beta;
            }
            
             if(gg.level==1)   //saving A values for predicting the first move...
             {
                move[cin] = alpha;
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
            
            temp_board[rowin][cin]='b';  //put in Min player move...
            GameNode gg =new GameNode(temp_board,nxt_lvl);
            //if wining before 4 ply...
            Heuristic hh = new Heuristic();
            int heur = hh.hValue(gg.board);
            if(heur==-1000)
            {
                if(gg.level==2)
                {
                    beta=Math.min(beta,-1000);
                    System.out.println("|-B2: "+(cin+1)+"; h="+heur);
                    sb.append("|-B2: ").append(cin+1).append("; h=").append(heur).append("\n");
                }
                
                if(gg.level==4)
                {
                    beta=Math.min(beta,-1000);
                    System.out.println("|-|-|-B4: "+(cin+1)+"; h="+heur);
                    sb.append("|-|-|-B4: ").append(cin+1).append("; h=").append(heur).append("\n");
                }
                //heur=-1000 starts here...
                    if(beta<=alpha)       // B pruning...
                    {   //printing pruned nodes... 
                        boolean pflag=false;
                        String prune ="";
                        for(int ki=cin+1;ki<7;ki++)
                        {
                            int krowin=5;
                            while('e'!=gg.board[krowin][ki])
                            {
                                krowin--;
                                if(krowin==-1)
                                        break;
                             }
            
                            if(-1==krowin)
                                   continue;
    
                            prune+=(ki+1)+", ";
                            pflag=true;
                          
                        }
                         if(prune.length()>0)
                                        prune = prune.substring(0,prune.length()-2);
                         String pout="";
                         if(gg.level==2)
                                pout="|-B2: pruning "+prune+"; alpha="+alpha+", beta="+beta;
                         if(gg.level==4)
                                pout="|-|-|-B4: pruning "+prune+"; alpha="+alpha+", beta="+beta;
                         if(pflag)
                         {
                             System.out.println(pout);
                             sb.append(pout).append("\n");
                         }  
                
                         return alpha;  
                    } 
                //heur=-1000 ends here...
                continue;
            }
            
            if(gg.level==2)
            {
                System.out.println("|-B2: "+(cin+1));
                sb.append("|-B2: ").append(cin+1).append("\n");
            }
            
            beta = Math.min(beta,maxVal(gg,alpha,beta));
            
            if(gg.level==4)
            {
                System.out.println("|-|-|-B4: "+(cin+1)+"; h="+gg.value);
                sb.append("|-|-|-B4: ").append(cin+1).append("; h=").append(gg.value).append("\n");
            }
          
            if(beta<=alpha)       // B pruning...
            {   //printing pruned nodes... 
                boolean pflag=false;
                String prune ="";
                for(int ki=cin+1;ki<7;ki++)
                {
                    int krowin=5;
                    while('e'!=gg.board[krowin][ki])
                    {
                        krowin--;
                        if(krowin==-1)
                                break;
                    }
            
                    if(-1==krowin)
                            continue;
                    
                    prune+=(ki+1)+", ";
                    pflag=true;
//                    
                }
                if(prune.length()>0)
                           prune = prune.substring(0,prune.length()-2);
                String pout="";
                if(gg.level==2)
                         pout="|-B2: pruning "+prune+"; alpha="+alpha+", beta="+beta;
                if(gg.level==4)
                         pout="|-|-|-B4: pruning "+prune+"; alpha="+alpha+", beta="+beta;
                if(pflag)
                {
                    System.out.println(pout);
                    sb.append(pout).append("\n");
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
        
        char board[][]=ReadWrite.readBoard(args[0]); // args[0]
        String out=args[1];
        //initialize å and ß here and pass to the maxValue function
        int alpha=-10000;
        int beta=10000;
        GameNode g = new GameNode(board,0); //create GameNoode object with level 0...for input board
        Algorithm a = new Algorithm();
        Heuristic h = new Heuristic();
  
        int hue = h.hValue(g.board);
        
        if(hue==1000)
                System.out.println("Alice is the Winner !!!");
        else if(hue==-1000)
                System.out.println("Bob is the Winner !!!");
        else
        {
            int resVal=a.maxVal(g, alpha, beta);
            a.firstMove(); //finding first move
            a.printFile(out);  //writing the first move to output file...
        }
    }
    
}
