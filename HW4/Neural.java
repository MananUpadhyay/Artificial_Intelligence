import java.io.*;
import java.util.*;

public class Neural 
{
    String outFile;

    int inputNodes=0;
    int hiddenNodes=0;
    //weigh matrices representing the 3 layers...
    double [][] weightIJ; //758x250
    double [][] weightJK; //250x10
    
    double [] actIJ; //size=784
    double [] actJK; //size=250
    double [] outFinal; //size 10
    
    double [] winToHidden; //size=250
    double [] winToOutput; // size=10
    
    List<ImageData> trainList = new ArrayList<ImageData>();
    List<ImageData> testList = new ArrayList<ImageData>();
    
    double learnRate=0.05;
    int norm=125;
    int tlabel=0;
    
    Neural()
    {
     //default constructor   
    }
    
    Neural(int inputNodes, int hiddenNodes,ArrayList<ImageData> trl,ArrayList<ImageData> tstl)
    { 
        weightIJ = new double[inputNodes][hiddenNodes];
        weightJK = new double[hiddenNodes][10];
        actIJ = new double[inputNodes];
        actJK = new double[hiddenNodes];
        trainList = trl;
        testList = tstl;
        this.inputNodes=inputNodes;
        this.hiddenNodes=hiddenNodes;
        winToHidden = new double [this.hiddenNodes];
        winToOutput = new double [10];
        outFinal = new double[10]; 
        outFile = null;
    }
    
    
    public void writeFile(StringBuffer sb)
    {
        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            String out = sb.toString();
            bw.write(out);
            bw.flush();
            bw.close();
        } 
        catch (IOException ex) 
        {
            System.out.println("Error: "+ex);
        }
    }
    
    
    public void initWeight()
    {
        Random r = new Random();
        //for wieghtIJ...
        for(int i = 0; i < weightIJ.length; i++)
        {
            for(int j = 0; j< weightIJ[0].length; j++)
            {
                double rwt = ((r.nextDouble()*0.2)-0.1);
                weightIJ[i][j]=rwt;

            }
        }
        //for wieghtJK...
        for(int i = 0; i < weightJK.length; i++)
        {
            for(int j = 0; j< weightJK[0].length; j++)
            {
                double rwt = ((r.nextDouble()*0.2)-0.1);
                weightJK[i][j]=rwt;
            }
        }
       
    }
    
    
    
    public double sigmoid(double insum)
    {
        return 1.0/(1.0+Math.exp(-insum));
    }
    
    public double ddx_sigmoid(double inerr)
    {
        double exp = Math.exp(inerr);
        return exp/((1.0+exp)*(1.0+exp));
    }
    
    
   public void backProp(ImageData im,int cnt)
    {
        //System.out.println("in backProp...");
        double [] label = new double[10];
        Arrays.fill(label, 0);
        //System.out.println("LAbel in backprop KJ: "+im.label);
        label[im.label]=1;
        
        //error for the output layer...
        double [] errorVKJ = new double[10];
        
        for(int ei=0; ei<errorVKJ.length; ei++)
        {
            errorVKJ[ei]= label[ei]-outFinal[ei];
        }
        
        //calculating del values for output layer...
        double [] delKJ = new double[10];
        for(int dei=0;dei<delKJ.length;dei++)
        {
            delKJ[dei] = errorVKJ[dei] * ddx_sigmoid(outFinal[dei]);
        }
        
        
        double [] delJI = new double[hiddenNodes];
       
        for(int m=0;m<hiddenNodes;m++)
        {
            double temp=0;
            for(int mc=0;mc<10;mc++)
            {
                temp += (delKJ[mc]*weightJK[m][mc]);
            }
            delJI[m]=(temp * ddx_sigmoid(actJK[m]));
        }
        
        //set LearnRate
        //update weights....
        for(int i=0;i<hiddenNodes;i++)
        {
            for(int j =0;j<10;j++)
            {
                weightJK[i][j]+=(delKJ[j]* actJK[i] * learnRate);
            }
        }
        
        for(int i=0;i<inputNodes;i++)
        {
            for(int j =0;j<hiddenNodes;j++)
            {
                weightIJ[i][j]+=(delJI[j]* actIJ[i] * learnRate);
            }
        }
    
      //System.out.println("backprop ends...");
    }
   
   public void exam()
   {
       //inits----------------------------
        actIJ = new double[inputNodes];
        actJK = new double[hiddenNodes];
        winToHidden = new double [this.hiddenNodes];
        winToOutput = new double [10];
       //----------------------------------
      
      int label_count=0;
      
      int count=0;
      StringBuffer sb = new StringBuffer();
      for(ImageData imgl2 : testList )
        {
                          
            ++count;    //for learnRate SA
            if(count%5000==0)
                    System.out.print(".");
            //making input layer array...
            int k=0;
            for(int i = 0; i< imgl2.data.length; i++)
            {
                for(int j = 0; j<imgl2.data.length; j++)
                {
                   actIJ[k]=(imgl2.data[i][j]/norm);
                   ++k;
                }
            }
           
            //input layer array done...

            for(int is = 0; is<hiddenNodes; is++)
            {
                 double wtsumIJ = 0;
                for(int js =0; js<inputNodes; js++)
                {
                   wtsumIJ += (actIJ[js]*weightIJ[js][is]);
                }
                winToHidden[is] = wtsumIJ;
            }
            
            //computing the output of hidden layer in actJK...
            for(int hi=0; hi<hiddenNodes; hi++)
            {
                actJK[hi]= sigmoid(winToHidden[hi]);
            }
            //output from hidden layer achieved...
            
            
            for(int is = 0; is<10; is++)
            {
                double wtsumJK =0;
                for(int js =0; js<hiddenNodes; js++)
                {
                   wtsumJK += (actJK[js]*weightJK[js][is]);
                }
               winToOutput[is]= wtsumJK;
            }
            
            
            //computing the output of output layer in outFinal...
            double [] outFinal2 = new double[10];
            for(int hi=0; hi<10; hi++)
            {
                outFinal2[hi]= sigmoid(winToOutput[hi]);
            }
            
             //finding the firing neuron..
            int max_indx=0;
            double max=outFinal2[0];
            for(int ai=1; ai<10; ai++)
            {
               if(outFinal2[ai]>max)
               {
                 max=outFinal2[ai];  
                 max_indx = ai;   
               }
                       
            }
            //for Accuracy calculation...
            if (imgl2.label==max_indx)
            {
                ++label_count;
            }
                 
           sb.append(max_indx).append("\n");
          
        }// imageData outer ends...
           //Use these line to print accuracy
           // System.out.println("Correct Predictions: "+label_count);
           // double accu = (label_count/300.0);
           // System.out.println("Accuracy:"+ accu);
           writeFile(sb);
   }
    
    

    public void learnNeural(String oFile)
    {
        outFile=oFile;   //assigning the name of output file
        initWeight();
        //printWeight();
        //get each image and apply to the input layer...
        System.out.println("Training");
        int count=0;

        for(int epoch=0;epoch<1;epoch++)
       { 
        for(ImageData imgl : trainList )
        {

            ++count;    //for learnRate SA
           if(count%5000==0)
                    System.out.print(".");
            //making input layer array...
            int k=0;

            for(int i = 0; i< imgl.data.length; i++)
            {
                for(int j = 0; j<imgl.data.length; j++)
                {
                   actIJ[k]=(imgl.data[i][j]/norm);
                   ++k;
                }
            }
            //input layer array done...
        
            //finding the weighted sum input to hidden layer...
            
            for(int is = 0; is<hiddenNodes; is++)
            {
                double wtsumIJ=0;
                for(int js =0; js<inputNodes; js++)
                {
                   wtsumIJ += (actIJ[js]*weightIJ[js][is]);
                }
                winToHidden[is] = wtsumIJ;
            }
            
            //computing the output of hidden layer in actJK...
            for(int hi=0; hi<hiddenNodes; hi++)
            {
                actJK[hi]= sigmoid(winToHidden[hi]);
            }
            //output from hidden layer achieved...
            
            
           
            for(int is = 0; is<10; is++)
            {
                double wtsumJK =0;
                for(int js =0; js<hiddenNodes; js++)
                {
                   wtsumJK += (actJK[js]*weightJK[js][is]);
                }
               winToOutput[is]= wtsumJK;
            }
            
            //computing the output of output layer in outFinal...
            for(int hi=0; hi<10; hi++)
            {
                outFinal[hi]= sigmoid(winToOutput[hi]);
            }
            
            //calling backpropogation
            backProp(imgl,count);
        }// imageData outer ends...
    }
        System.out.println();
        System.out.println("Testing");
        //calling Testing...
        exam();
    } 
    
    
   
}
