import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Literal
{
    int person = 0;
    int table = 0;
    boolean sign = true;

  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.person;
        hash = 83 * hash + this.table;
        hash = 83 * hash + (this.sign ? 1 : 0);
        return hash;
    }

    public boolean equals(Literal obj) 
    {
        return ((this.sign==obj.sign) && (this.person==obj.person) && (this.table==obj.table));
    }
    
    
    Literal(int i, int j,boolean s)
    {
        this.person = i;
        this.table = j;
        this.sign = s;
    }
    
    public boolean isPositiveLit()
    {
        return !this.sign;
    }
    
    public boolean isNegetiveLit()
    {
        return this.sign;
    }
         
}

class Satellite 
{
    List<ArrayList<Literal>> kb = new ArrayList<ArrayList<Literal>>();
    
    public int KBSize()
    {
        return kb.size();
    }
    void masterFirst(String line, int nOfPer, int nOfTab)
    {
                       //creating literals...
                       for(int i=1;i<=nOfPer;i++)
                       {
                           List<Literal> litList = new ArrayList<Literal>();
                           for(int j=1;j<=nOfTab;j++)
                           {
                               Literal pl = new Literal(i,j,true);
                               litList.add(pl);
                            }
                           kb.add((ArrayList<Literal>) litList);
                        }
                       
                       
    }
    void masterSecond(String line, int nOfPer, int nOfTab)
    {
                       if(nOfTab==2)  //2 table special case
                       {
                           for(int i=1;i<=nOfPer;i++)
                           {   
                                    List<Literal> p1 = new ArrayList<Literal>();
                                    Literal pl1 = new Literal(i,1,false);
                                    Literal pl2 = new Literal(i,2,false);
                                    p1.add(pl1);
                                    p1.add(pl2);
                                    kb.add((ArrayList<Literal>) p1);
                           }   
                       }
                       else
                       {
                           for(int i=1;i<=nOfPer;i++)
                           {
                                List<Literal> tpList = new ArrayList<Literal>();

                                for(int j=1;j<=nOfTab;j++)
                                {
                                    Literal pl = new Literal(i,j,true);
                                    tpList.add(pl);
                                 }
                                //System.out.println("tpList: "+tpList.size()+"->"+printChain((ArrayList<Literal>) tpList));
                              
                                for(int mki=0;mki<nOfTab;mki++)
                                { 
                                    List<Literal> mainList = new ArrayList<Literal>();
                                    for(int mkj=0; mkj<nOfTab;mkj++)
                                    {
                                        if(mki!=mkj)
                                        {  //System.out.println("in actual for");
                                            Literal in = tpList.get(mkj);
                                            Literal actl = new Literal(in.person,in.table,!in.sign);
                                            mainList.add(actl);
                                            //System.out.println("MianList"+mainList.size());
                                        }
                                    } 
                                    kb.add((ArrayList<Literal>) mainList);
                                }
                                
                            } //outer for ends...
                       }
    }
    
    public void friendClause(String [][] friends, int ppl, int tbl)
    {//creating friend clauses...
                   
        for(int i = 0;i<ppl;i++)
        {
            for(int j = i; j<ppl; j++)
            {
              
                if(friends[i][j].equals("1"))
                {
                    for(int k=1;k<=tbl;k++)
                    {
                        List<Literal> frndList1 = new ArrayList<Literal>();
                        List<Literal> frndList2 = new ArrayList<Literal>();
                        
                        Literal fl1t = new Literal(i+1,k,true);
                        Literal fl2t = new Literal(j+1,k,true);
                        Literal fl1f = new Literal(i+1,k,false);
                        Literal fl2f = new Literal(j+1,k,false);
                        
                        frndList1.add(fl1t);
                        frndList1.add(fl2f);
                        kb.add((ArrayList<Literal>) frndList1);
                        frndList2.add(fl1f);
                        frndList2.add(fl2t);
                        kb.add((ArrayList<Literal>) frndList2);
                    }    
                }
                
            }
        }    
    }
    
    public void enemyClause(String [][] friends, int ppl, int tbl)
    {//ceating enemy clauses...           
        
        for(int i = 0;i<ppl;i++)
        {
            for(int j = i; j<ppl; j++)
            {
                
                if(friends[i][j].equals("-1"))
                {
                    for(int k=1;k<=tbl;k++)
                    {
                        
                        List<Literal> enemList2 = new ArrayList<Literal>();
                        
                        Literal el1f = new Literal(i+1,k,false);
                        Literal el2f = new Literal(j+1,k,false);
                       
                        enemList2.add(el1f);
                        enemList2.add(el2f);
                        kb.add((ArrayList<Literal>) enemList2);
                    }    
                }
                
            }
        }    
        
    }
    
   public ArrayList<ArrayList<Literal>> createKB(String file) throws IOException
   {
           
           BufferedReader br = new BufferedReader(new FileReader(file));
           String line=null;
           String friends[][] = null;
           int nOfPer = 0;
           int nOfTab = 0;
           int lcount=0;
           int freny=0;
           while((line=br.readLine())!=null)
           {
               ++lcount;
               if(lcount==1)   //creating first constraint clauses...
               {
                   String [] fl = line.split(" ");
                   nOfTab = Integer.parseInt(fl[0]);
                   nOfPer = Integer.parseInt(fl[1]);
                   masterFirst(line,nOfPer,nOfTab);
                   masterSecond(line,nOfPer,nOfTab);
                   friends = new String[nOfPer][nOfPer];
               }   
               else
               {
                   String [] fr = line.split(" ");
                   friends[freny]=fr;
                   ++freny;
               }  
               
           }
           
           int enemy_count=0;
           for(int ki=0;ki<nOfPer;ki++)
           {
               for(int kj=0;kj<nOfPer;kj++)
               {
                   if(friends[ki][kj].equals("-1"))
                                    ++enemy_count;
               }
           }
           if(enemy_count==0)
           {
               return null;
           }
           
           friendClause(friends,nOfPer,nOfTab);
           enemyClause(friends,nOfPer,nOfTab);
          
           return (ArrayList<ArrayList<Literal>>) this.kb;
    
    }
   
   public String printChain(ArrayList<Literal> pkb)
   {
       StringBuilder out = new StringBuilder();
       if(pkb==null)
                return null;
       for(Literal l : pkb)
       {
           if(l.sign==true)
                     out.append("X").append(l.person).append(l.table);
           if(l.sign==false)
                     out.append("~").append("X").append(l.person).append(l.table);
           out.append(" ");
       }
       String sout = out.toString();
       return sout;
   }
   
   public void printKB(ArrayList<ArrayList<Literal>> know)
   {
       for (ArrayList<Literal> pkb : know)
       {
           String clause = printChain(pkb);
           System.out.println(clause);
       }
       
   }
}

class Solver
{
    Literal fLit = new Literal(1000,1000,true);
    List<Literal> fList;
    Literal onLit = new Literal(500,500,false);
    List<Literal> onList;
    Solver() {
        this.fList = new ArrayList<Literal>();
        this.fList.add(fLit);
        this.onList = new ArrayList<Literal>();
        this.onList.add(onLit);
    }
    
    
    
    public boolean factor(ArrayList<Literal> ci, ArrayList<Literal> cj)
    {
        
      int litcount=0;
      for(Literal li:ci)
      {
          Literal test = new Literal(li.person,li.table,!li.sign);
          
          for(Literal lj:cj)
          {
              if(lj.equals(test))
              {
                        ++litcount;
              }
              if(litcount>=2)
                        return true; 
          }          
      }
      return false;
    }
    
    
   public ArrayList<Literal> resolve(ArrayList<Literal> ci, ArrayList<Literal> cj)
   {
      
      ArrayList<Literal> ki = new ArrayList<Literal>(ci);
      ArrayList<Literal> kj = new ArrayList<Literal>(cj);
      List<Literal> resolvents = new ArrayList<Literal>();
      int kilen=ki.size();
      int kjlen=kj.size();
      
      if(factor(ki,kj))  //if resolving to true...
      {
       
          Literal trLit = new Literal(1000,1000,true);
          List<Literal> obList = new ArrayList<Literal>();
          obList.add(trLit);
          return (ArrayList<Literal>) obList;
      }
               
      for(Literal rl: ki)
      {
         
          Literal rtest = new Literal(rl.person,rl.table,!rl.sign);
          
          for(Literal rj:kj)
          {
              
              if(rj.equals(rtest))
              {
                  ki.remove(rl);
                  kj.remove(rj);
              
  
              if(ki.isEmpty() && kj.isEmpty()) //kb proved false...
                                return null;   
              resolvents.addAll(ki);
              resolvents.addAll(kj);
              
                            //deDuplicatiing...
                    for(int dl=0;dl<resolvents.size();dl++)
                    {
                        for(int el=0;el<resolvents.size();el++)
                        {
                            if(dl!=el)
                            {
                                if(resolvents.get(dl).equals(resolvents.get(el)))
                                {
                                    resolvents.remove(el);
                                }
                            }
                        }
                    }
                //deDup Ends--------
                    return (ArrayList<Literal>) resolvents;
              }
          }
      
      
      
           
      } //resolvent created outer  for ends...
     
          Literal nrLit = new Literal(500,500,false);
         List<Literal> noList = new ArrayList<Literal>();
          noList.add(nrLit);
          return (ArrayList<Literal>) noList;
         
       
   }
   
   
   
   public int plResolve(ArrayList<ArrayList<Literal>> kb)
   {
       int ff = 1;
       
       List<ArrayList<Literal>> newClause =  new ArrayList<ArrayList<Literal>>();
       while(true)
       {    System.out.print(".");
            for(int lkb=0; lkb<kb.size(); lkb++)
            {
                for(int rkb=ff; rkb<kb.size(); rkb++)
                {
              
                        ArrayList<Literal> ci = kb.get(lkb);
                        ArrayList<Literal> cj = kb.get(rkb);
                        
                        List<Literal> rvsol = new ArrayList<Literal>();
                        rvsol = resolve(ci,cj);
                        
                        Satellite fb = new Satellite();
                
                        
                        if(rvsol==null)  //resolution of two clauses in KB is null
                                 return 0;
                        
                        if(rvsol.get(0).equals(this.fList.get(0))) //skip clause cond...
                        {

                            continue;    
                        }
                        
                        if(rvsol.get(0).equals(this.onList.get(0)))
                        {
                            continue;
                        }
                        
                        boolean isinnew=false;
                        for(int ab=0; ab<newClause.size() ; ab++)
                        {
                            if(same((ArrayList<Literal>) rvsol,newClause.get(ab)))
                            {
                                isinnew=true;
                                break;   
                            }
                            
                        }
                        if(!isinnew)
                        {
                            newClause.add((ArrayList<Literal>) rvsol);
                        }                

                } 
 
            }//newClause created
            
            ff = kb.size();  //to start comparing at the last point of kb
            
            //deDiplicate newClause
            for(int di=0;di<newClause.size();di++)
            {
                for(int dj=0;dj<newClause.size();dj++)
                {
                    if(di!=dj)
                    {
                        if(newClause.get(di).equals(newClause.get(dj)))
                                        newClause.remove(dj);
                    }
                }
               
            }
            
            //----
            
            //Intersection of KB and NewClause...
            boolean add_flag=false;  //to check for valid stopping condition
            boolean [] sho = new boolean[newClause.size()];
            for(int nc=0; nc<newClause.size();nc++) //testing if KB contains any newClause add if Kb doenst have it
            {
                //=============
                for(int kbc=0; kbc<kb.size(); kbc++)
                {
                    if(same(newClause.get(nc),kb.get(kbc)))
                    {
                        sho[nc]=true;
                        break;
                    }
                }
            }  
                //============
                for(int lml=0; lml<sho.length;lml++)
                {
                    if(!sho[lml])
                    {
                        Satellite st= new Satellite();
                        kb.add(newClause.get(lml));
                        add_flag=true;
                    }
                
                }
                
                newClause=new ArrayList<ArrayList<Literal>>();
                
            if(!add_flag)
            {
                 return 1;
            }   
       }
  
   }

    
    public boolean same(ArrayList<Literal> mn1, ArrayList<Literal> mn2)
    {
            int boolSize=mn1.size();
            boolean [] litFlag = new boolean[boolSize];
  
            if(mn1.size()!=mn2.size())
                            return false;
            
            for(int blit=0; blit<mn1.size();blit++)  //cluase of KB passed
            {
                
                for(int klit=0; klit<mn2.size();klit++) //clause to check residency passes
                {
                    
                    if(mn1.get(blit).equals(mn2.get(klit)))
                    {
                        litFlag[blit]=true;
                        break; 
                    }         
                }   
            }
            
            for(int bi=0; bi<litFlag.length;bi++)
            {               
                if(litFlag[bi]==false)
                            return false;
            }
           return true;  
    }
       
}

class Sat
{
    public static void main(String args[]) throws IOException
    {
        //send file name...
        Satellite s = new Satellite();
        
       ArrayList<ArrayList<Literal>> know = s.createKB(args[0]);

       BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
       
       
        if(know==null)
        {
            System.out.println("Output to File: 1");
            bw.write("1");
            bw.close();
        }
        else
        {
            
            Solver sl = new Solver();
            int res=sl.plResolve(know);
            String sres = String.valueOf(res);
            System.out.println("Output to File: "+res);
            bw.write(sres);
            bw.close();

        }   
//        
    }
}
