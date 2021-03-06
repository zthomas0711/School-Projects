import java.util.Scanner;
import java.util.*;

public class classifier {
	//declare public attributes
	public String answer;
	public ArrayList<String>questions=new ArrayList<String>();
	public ArrayList<String>results=new ArrayList<String>();
	public String defaultResponse="Please answer all questions with Yes or No.";
	
	//method to determine whether to use default rules or custom rules made by user
	public void setRules(String answer)
	{
		boolean flag=false;
		Scanner myScanner3= new Scanner(System.in);
		//loop until the flag is switched to true
		while(!flag)
		{
		
			//switch case, if yes populate arrays with default data and begin classification process
			//if no, enter makeNewRules method for user to populate questions and results
			//if QUIT, exit application
			//after operations are done switch flag to true to break out of loop
			switch(checkAnswer(answer))
			{
				case "YES":
					questions.add("Does it have a nucleus?");
					questions.add("Is it single-celled?");
					questions.add("Does it make its own food by photosynthesis?");
					questions.add("Is it an amphibian, reptile, fish, bird, or mammal?");
					
					results.add("Monera");
					results.add("Protists");
					results.add("Fungi");
					results.add("Plant");
					results.add("Animal");
					
					classify();
					flag=true;
					break;
					
				case "NO":
					makeNewRules();
					flag= true;
					break;
			
				case "QUIT":
					System.out.println("Hey");
					System.exit(0);
					break;
					
					//default case to ask for yes or no answers 
					//no flag switch so switch statement will keep being checked until given a yes or no
				default:
					System.out.println(defaultResponse);
					answer= myScanner3.nextLine();
					break;
			}
			
		}
		myScanner3.close();
		
	}
	
	//method to take user inputs and put them to all caps to avoid rejection of entries due to letter cases
	public String checkAnswer(String answer)
	{
		checkQuit(answer);
		return answer.toUpperCase();
	}
	
	public void checkQuit(String answer)
	{
		if(answer.toUpperCase().equals("QUIT"))
		{
			System.exit(0);
		}
	}
	
	//method for custom user to make rules
	private void makeNewRules()
	{	
		boolean flag=false;
		boolean flag2=false;
		
		Scanner myScanner1=new Scanner(System.in);
			//loop until flag is switched indicating that there are no more rules to add
			while(!flag)
			{
			//Ask for the question that needs to be asked and add it to the array
			System.out.println("Please enter your rules in the order you want them applied.");
			answer=myScanner1.nextLine();
			checkQuit(answer);
			questions.add(answer);
			
			//Ask for the result that corresponds to saying yes to the previous question just entered
			System.out.println("Please enter the result given for answering Yes to the last question.");
			answer=myScanner1.nextLine();
			checkQuit(answer);
			results.add(answer);
			
				//loop until flag2 is switched indicating a yes or no answer has been received
				while(!flag2)
				{
				//Ask if another rule needs to be added then check the answer
				System.out.println("Do you want to add another rule?");
				answer=myScanner1.nextLine();
					switch(checkAnswer(answer))
					{	//if yes, keep flag false so loop will repeat for another rule
						case "YES":
							flag2=true;
							break;
						
						//if no, switch flag to break loop and enter last rule
						case "NO":
							flag=true;
							flag2=true;
							break;
						
						case "QUIT":
							System.out.println("Hey");
							System.exit(0);
							break;
							
						//if yes or no is not given as a response, keep flag2 false so user
						//is asked continually to add another rule
						default:
							System.out.println(defaultResponse);
							flag2=false;
							break;
					}
				}
			
			}	
		System.out.println("Please enter the result in the case that all previous answers are No.");
		answer=myScanner1.nextLine();
		checkQuit(answer);
		results.add(answer);
		classify();
		myScanner1.close();
	}
	
	private void classify()
	{	
		int i=1;
		int lastQuestion=questions.size();
	
		Scanner myScanner2=new Scanner(System.in);
		String defaultMessage="Your object is a ";
			while(i<=lastQuestion)
			{
			System.out.println(questions.get(i-1));
			answer=myScanner2.nextLine();
			
				if(checkAnswer(answer).equals("YES"))
				{
					System.out.println(defaultMessage + results.get(i-1));
					i=lastQuestion+1;
				}
				
				else if(checkAnswer(answer).equals("NO"))
				{
					if(i==lastQuestion)
					{
						System.out.println(defaultMessage + results.get(i));
					}
					
					
				i++;
				}
				
				else if (checkAnswer(answer).equals("QUIT"))
				{
						System.exit(0);
				}
				
				else
				{
					System.out.println(defaultResponse);
				}
			}
		
		myScanner2.close();
	}
	
	
	public static void main(String[] args)
	{
		String response;
		classifier kingdoms= new classifier();
		System.out.println("Would you like to use pre-existing rules?");
		Scanner myScanner=new Scanner(System.in);
		
		response=myScanner.nextLine();
		
		kingdoms.setRules(response);
		
		myScanner.close();
		
	}

}
