import java.io.*;
import java.util.*;


class InputOutput 
{
	

	int[][] getMatrix() throws IOException,Exception
	{
		int [][] adj;
		
		
		int lineCount=0;
		String curr;
		List<String> smat = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("test5input.txt"));
		
		while((curr=br.readLine()) != null)
		{	
			String [] temp = curr.split(" ");
			if(temp.length>1)
				  smat.add(curr);
		}

		int ssize = smat.size();
		adj = new int[ssize][ssize];

		for(String scurr : smat)
		{
			String[] numbers = scurr.split(" ");
   			for ( int i = 0 ; i < numbers.length ; i++) 
         			adj[lineCount][i] = Integer.parseInt(numbers[i]);

    		lineCount++;

		}
		
		return adj;
	}


	String[] getNames() throws Exception
	{
		String line;
		List<String> nomo = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new FileReader("social-network.txt"));
		
		while((line=br.readLine())!=null)
		{
			String [] temp = line.split(" ");
			if(temp.length==1)
					nomo.add(line);
			
		}
		
		String [] nom = new String[nomo.size()];  
		nomo.toArray(nom);
		return nom;
	}

	void showResult(String search_method, String search_path) throws IOException,Exception
	{
					 search_method= search_method+".txt";
					 BufferedWriter bw = new BufferedWriter(new FileWriter(search_method));
					 System.out.println("\n Path printed to "+search_method+" : \n"+search_path+"\n");
					 bw.write(search_path);
					 bw.close();
	}


}



class BFS
{
	String BSearch(int mat[][],String names[],String src, String tar) throws IOException,Exception
	{
		
		int nc=names.length;
		int cc=0;
		boolean visited[] = new boolean[nc];
		List<String> arr = new ArrayList<String>(); //holds nodes temporarily
		int[] parents = new int[nc];  //holds parents information
		String path=null;
		
		while(!src.equals(names[cc]))
		{
			cc++;
		}
		
		
		Queue<String> qe = new LinkedList<String>();
		qe.add(names[cc]);   //names[cc]->source person of the graph|| cc-> index for that person
		visited[cc]=true;
		
		while(qe.size()>0)    //while Queue is not empty->
		{
			String curState=(String)qe.remove();
			cc=0;  //currState index
			while(!curState.equals(names[cc]))  //get index of the popped node
			{
			cc++;
			}

			
			if(curState.equals(tar))  //check if goal state & print Path
				{

					int zz=parents[cc]; //Finding parent of Target node
					path=src;
					Stack<String> st = new Stack<String>();  //Stack holds the path
					st.push(names[cc]);  // push Target onto Stack

					while(!names[zz].equals(src)) //Pushing the parents
					{
						st.push(names[zz]);
					 	zz=parents[zz];
					 }
					 //Print Stack
					 while(!st.isEmpty())
					 {
					 	path=path+"->"+st.pop();
					 }
					return path;
				
				}


			for(int z=0; z<nc; z++) //check for non-visited nodes and expand them
			{
				if((mat[cc][z]>0) && !visited[z])
					{	
						arr.add(names[z]);
						visited[z]=true;
						parents[z]=cc;
						
					}
			}
						
			Collections.sort(arr); //Sorting the Arraylist
			
			for(String temp: arr)  //Adding Sorted Nodes into the Queue
			{
					qe.add(temp);					
			}
						
				
		}//While ends

		path="Not Found";
		return path;
	}
}



class DFS
{
	String DSearch(int mat[][],String names[],String src, String tar)
	{
		
		int nc=names.length;
		int cc=0;
		boolean visited[] = new boolean[nc];
		List<String> arr = new ArrayList<String>(); //holds nodes temporarily
		int[] parents = new int[nc];  //holds parents inforation
		String path=null;
		
		while(!src.equals(names[cc]))
		{
			cc++;
		}
		
		
		Stack<String> stk = new Stack<String>();  //Stack stk holds the DFS Nodes discovered
		stk.push(names[cc]);   //names[cc]->source person of the graph|| cc-> index for that person
		
		while(!stk.isEmpty())    //while Queue is not empty->
		{
			String curState=(String)stk.pop();
			cc=0;
			//System.out.println("CurState"+curState);
			while(!curState.equals(names[cc]))  //get index of the popped node
			{
			cc++;
			}
			

			if(curState.equals(tar))  //check if goal state & print Path
				{

					//call output function
					int zz=parents[cc]; //Finding parent of Target node
					path=src;
					Stack<String> st = new Stack<String>();  //Stack holds the path
					st.push(names[cc]);  // push Target onto Stack

					while(!names[zz].equals(src)) //Pushing the parents
					{
						st.push(names[zz]);
					 	zz=parents[zz];
					 }
					 //Print Stack
					 while(!st.isEmpty())
					 {
					 	path=path+"->"+st.pop();
					 }
					return path;

				}		
				
		if(!visited[cc])
		{	
			visited[cc]=true;   //Mark popped node as visited
			for(int z=0; z<nc; z++) //check for non-visited nodes and expand them
			{
				if((mat[cc][z]>0) && !visited[z])
					{	
						arr.add(names[z]);
						parents[z]=cc;
						
					}
			}
					
			Collections.sort(arr); //Sorting the Arraylist

			for(int p=arr.size()-1; p>=0;p--)  //Adding Sorted Nodes into the Queue
			{	
					String temp = (String) arr.remove(p);
					stk.push(temp);					
			}
		}
				
		}//While ends

		path="Not Found";
		return path;
	}
}



class UCS
{
	String USearch(int mat[][],String names[],String src, String tar)
	{
		
		int nc=names.length;
		int cc=0;  //currstate index
		boolean visited[] = new boolean[nc];
		List<String> arr = new ArrayList<String>(); //holds nodes temporarily
		int[] parents = new int[nc];  //holds parents inforation
		  
		String path=null;
		
		while(!src.equals(names[cc]))
		{
			cc++;
		}
		
		Queue<String> qe = new LinkedList<String>();  //holds the nodes to be explored
		List<Integer> pathc = new ArrayList<Integer>();   //holds the path cost

		qe.add(names[cc]);   //names[cc]->source person of the graph|| cc-> index for that person
		visited[cc]=true;
		
		while(qe.size()>0)    //while Queue is not empty->
		{
			String curState=(String)qe.remove();

			cc=0;
			while(!curState.equals(names[cc]))  //get index of the popped node
			{
			cc++;
			}

			
			if(curState.equals(tar))  //check if goal state & print Path
				{
				
					int zz=parents[cc]; //Finding parent of Target node
					path=src;
					Stack<String> st = new Stack<String>();  //Stack holds the path
					st.push(names[cc]);  // push Target onto Stack

					while(!names[zz].equals(src)) //Pushing the parents
					{
						st.push(names[zz]);
					 	zz=parents[zz];
					 }
					 //Print Stack
					 while(!st.isEmpty())
					 {
					 	path=path+"->"+st.pop();
					 }
					return path;
				}
			

			visited[cc]=true;
			for(int z=0; z<nc; z++) //check for non-visited nodes and expand them
			{
				if((mat[cc][z]>0) && !visited[z])
					{	
			
						if(!arr.contains(names[z]))
						{
							arr.add(names[z]);
							pathc.add(mat[cc][z]);
							parents[z]=cc;
						}
						else
						{
							int indx = arr.indexOf(names[z]);
							int curr_path_cost = (int)pathc.get(indx); //cost of child through one path

							int parent_index = arr.indexOf(names[cc]);
							int parent_path_cost = (int)pathc.get(parent_index); //cumulative cost 
						 	
						 	if(curr_path_cost>(parent_path_cost+mat[cc][z]))
						 	{
						 		pathc.remove(indx);
						 		pathc.add(parent_path_cost+mat[cc][z]);
						 	}

						}
						
					}
			}
				
			
			Integer [] pathcost = new Integer[pathc.size()];
			pathc.toArray(pathcost); //holds pathcost
			int ptc=pathcost.length;
			
			String [] arrnames = new String[arr.size()];
			arr.toArray(arrnames); // converting list to array
			
			//sorting the nodes to be added acc to the path cost
			for(int i=0;i<ptc;i++)
			{
				for(int j=1;j<ptc;j++)
				{
					if(pathcost[i]>pathcost[j])
					{
						if(pathcost[i]==pathcost[j])  //arrange nodes by alphabetical order if same pathcost
						{
							if((arrnames[i].compareTo(arrnames[j]))>0)
							{
								String atmp = arrnames[i];
								arrnames[i] = arrnames[j];
								arrnames[j] = atmp;
							}

						}
						String ttmp = arrnames[i];
						arrnames[i]=arrnames[j];
						arrnames[j]=ttmp;
					}
				}
			}
			
			for(String temp: arrnames)  //Adding Sorted Nodes into the Queue
			{
					qe.add(temp);	//add names[z] here				
			}
						
				
		}//While ends

		path="Not Found";
		return path;
	}
}


class Uninformed_Search
{
	public static void main(String args[]) throws IOException,Exception
	{
		int method;
		
		//Scanner sc = new Scanner(System.in);
		
		// System.out.println("Enter Search Method");
		// System.out.println("1 for BFS");
		// System.out.println("2 for DFS");
		// System.out.println("3 for UCS");
		// System.out.println("0 to Exit");
		
		
		

		//System.out.println("Enter Source Person");
		String src = "Alice";//Get Source
		//System.out.println("Enter Target Person");
		String tar = "Noah";   // Get Target

		InputOutput n = new InputOutput();
		String [] names = n.getNames();
		
		InputOutput m = new InputOutput();
		int [][]mat = m.getMatrix();
		
		InputOutput inop = new InputOutput();

		String result1,result2,result3=null;
		String search_method=null;

		
				BFS b = new BFS();
						result1 = b.BSearch(mat,names,src,tar);
						//System.out.println("\n Path: \n"+result+"\n");
						search_method = "Breadth_First_Search_Output";
						inop.showResult(search_method,result1); 
						

				DFS d = new DFS();
						result2 = d.DSearch(mat,names,src,tar);
						//System.out.println("\n Path: \n"+result+"\n");
						search_method = "Depth_First_Search_Output"; 
						inop.showResult(search_method,result2); 
						

				UCS u = new UCS();
						result3 = u.USearch(mat,names,src,tar);
						//System.out.println("\n Path: \n"+result+"\n");
						search_method = "Uniform_Cost_Search_Output"; 
						inop.showResult(search_method,result3); 
						
		

	}  //main() ends
}

