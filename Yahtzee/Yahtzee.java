//----------------------------------------------------------------------------------------------
//Author: Vincent Pham													Last Updated: 1/8/09
//Game: Yahtzee
//Program: Sit down with a nice cup of coffee and play some Yahtzee by yourself or with friends.
//*warning: GUI not included
//----------------------------------------------------------------------------------------------

import javax.swing.JOptionPane;

public class Yahtzee
{

	public static void main (String[] args)
	{
		String numStr, playerStr, listCategory, currentTally, allTotalScore = "";
		int num, times = 13, currentPlayer, maxRoll = 3, amountReroll;
		int maxDie = 5, reroll, display, categoryNum = 16, turn, roll;
		int rerolling = 0, categoryChoice, thisDie, nextDie1, nextDie2, nextDie3, nextDie4;
		
		Die die1;
		
		do
		{
		numStr = JOptionPane.showInputDialog ("Enter the number of player (1-6): ");
		num = Integer.parseInt(numStr);
		
		}
		while (num < 1 || num > 6); //end of do loop for choosing number of players
		
		String[] player = new String[num];
		boolean[][] unused = new boolean[num][categoryNum];
		
		// boolean to make choosing category choice only once.
		for (currentPlayer = 0; currentPlayer < num; currentPlayer++)
		{
			for (turn = 0; turn < categoryNum; turn++)
			{
				unused[currentPlayer][turn] = true;
			}
		} 
		
		//loop for entering name of each player.
		for (currentPlayer = 0; currentPlayer < num; currentPlayer++)
		{
			playerStr = JOptionPane.showInputDialog ("Name of Player" + (currentPlayer + 1) + " :");
			
			player[currentPlayer] = playerStr;   
		} //end of for loop
		
		die1 = new Die();
      	
      	
		int scoring [][] = new int [num][categoryNum];//scoring aray (0-12 categories, 13 lower subtotal, 14 upper subtotal, 15 grand total)
		int[] numDie = new int[maxDie]; //array fpr die value.
		
		String[][] usedCategory = new String [num][times];
		
		//makes all checkmark initially blank
		for (turn =0; turn < 13; turn++)
			for (currentPlayer = 0; currentPlayer < num; currentPlayer++)
        		usedCategory[currentPlayer][turn] = " ";
         
        //Thirteen turns	
		for (int choices = 0; choices < times; choices++)
		{
			//player turns for loop.
			for (currentPlayer = 0; currentPlayer < num; currentPlayer++)
			{
				
				
				//rolls the die;
				for (roll = 0; roll < maxDie; roll++)
				  {
				  	numDie[roll] =  die1.roll();
				  }
			
			//calls rerollStatement method which ask user if they want to reroll any dice.	  
			numStr = rerollStatement(player, numDie, currentPlayer);
				  							   	
      		reroll = numStr.length();
			
			//goes to category choice if user does not enter anything	  								
      		if (reroll == 0)
				amountReroll = maxRoll;
				
			else
			{ 									   	
      			//reroll
      			for(amountReroll = 0; amountReroll < maxRoll; amountReroll++ )
      			{
      				//activate the rerollStatement method second time if necessary
      				if (amountReroll != 0)
      				{
      				
      			  	numStr = rerollStatement(player, numDie, currentPlayer);
      			  						   	
					reroll = numStr.length();
      				}
      				
      				//rerolls the die that was typed.
					for (turn = 0; turn < reroll; turn++)
				 	{
				  	rerolling = numStr.charAt(turn);
				  	
				    numDie = switchReroll(numDie, rerolling);//calls the Rerolling method.
				    
				 	}//end of reroll for loop

      			  	if (reroll == 0)
				    	amountReroll = maxRoll;
      			 
				 	
	                //categorychoice = Integer.parseInt(category);
				 		
				 		
				 }//end of amountReroll for loop	        
 						  	

			}//end of player for loop.
			
		
			//list categories and if it is used with a * mark and prompts for category	 											
	        listCategory = JOptionPane.showInputDialog(player[currentPlayer] + " turn                       * = category used"
	        										+ "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" 
	         										+ "\nYour final dice for this turn are: " +numDie[0]+ " " 
	                              					+ numDie[1] + " " + numDie[2] + " " + numDie[3] + " " + numDie[4]
	                              					+ "\nCategory 1: Ones" + usedCategory[currentPlayer][0]  
				 									+ "\nCategory 2: Twos" + usedCategory[currentPlayer][1]
				 									+ "\nCategory 3: Threes" + usedCategory[currentPlayer][2]
				 									+ "\nCategory 4: Fours" + usedCategory[currentPlayer][3] 
				 									+ "\nCategory 5: Fives" + usedCategory[currentPlayer][4]
				 									+ "\nCategory 6: Sixes" + usedCategory[currentPlayer][5]
				 									+ "\nCategory 7: 3 of a Kind" + usedCategory[currentPlayer][6]
				 									+ "\nCategory 8: 4 of a Kind" + usedCategory[currentPlayer][7]
				 									+ "\nCategory 9: Full House" + usedCategory[currentPlayer][8]
				 									+ "\nCategory 10: Small Straight" + usedCategory[currentPlayer][9]
				 									+ "\nCategory 11: Large Straight" + usedCategory[currentPlayer][10]
				 									+ "\nCategory 12: Yahtzee" + usedCategory[currentPlayer][11]
				 									+ "\nCategory 13: Chance" + usedCategory[currentPlayer][12]
				 									+ "\n\n Which category do you want to put your final dice in?");
	                              				
	         categoryChoice = Integer.parseInt(listCategory);
	                
	         switch (categoryChoice)
	                              	{
	                              		case 1://ones
	                              			if (unused[currentPlayer][0] == true)
	                              			{
	                              				for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					if (numDie[thisDie] == 1)
	                              						scoring[currentPlayer][0] += 1;
	                              				}
	                              				scoring[currentPlayer][13] += scoring [currentPlayer][0];//adds to upper subtotal
	                              		
	                              				usedCategory[currentPlayer][0] += "*";
	                              				unused[currentPlayer][0] = false;
	                              			}				
	                              			break;
	                              		
	                              		case 2://twos
	                              			if (unused[currentPlayer][1] == true)
	                              			{
	                              				for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					if (numDie[thisDie] == 2)
	                              						scoring[currentPlayer][1] += 2;
	                              				}
	                              				scoring[currentPlayer][13] += scoring[currentPlayer][1];
	                              				 
	                              				usedCategory[currentPlayer][1] = "*";
	                              				unused[currentPlayer][1] = false;
	                              			}
	                              			break;
	                              		
	                              		case 3://threes
	                              			if (unused[currentPlayer][2] == true)
	                              			{
	                              				for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					if (numDie[thisDie] == 3)
	                              						scoring[currentPlayer][2] += 3;
	                              				}	
	                              				scoring[currentPlayer][13] += scoring[currentPlayer][2]; 
	                              				
												usedCategory[currentPlayer][2] = "*";
												unused[currentPlayer][2] = false;
	                              			}
	                              			break;
	                              		
	                              		case 4://fours
	                              			if (unused[currentPlayer][3] == true)
	                              			{
												for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					if (numDie[thisDie] == 4)
	                              						scoring[currentPlayer][3] += 4;
	                              				}
	                              				scoring[currentPlayer][13] += scoring[currentPlayer][3];

	                              				usedCategory[currentPlayer][3] = "*";
	                              				unused[currentPlayer][3] = false;
	                              			}
	                              			break;
	                              		
	                              		case 5://fives
	                              			if (unused[currentPlayer][4] == true)
	                              			{
												for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					if (numDie[thisDie] == 5)
	                              						scoring[currentPlayer][4] += 5;
	                              				}
	                              				scoring[currentPlayer][13] += scoring[currentPlayer][4];
	 
	                              				usedCategory[currentPlayer][4] = "*";
	                              				unused[currentPlayer][4] = false;
	                              			}
	                              			break;
	                              		
	                              		case 6://sixes
	                              			if (unused[currentPlayer][5] == true)
	                              			{
	                              				for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					if (numDie[thisDie] == 6)
	                              						scoring[currentPlayer][5] += 6;

	                              				}
	                              				scoring[currentPlayer][13] += scoring[currentPlayer][5];

	                              				usedCategory[currentPlayer][5] = "*";
	                              				unused[currentPlayer][5] = false;
	                              			}
	                              			break;
	                              			
	                              		case 7:// 3 0f a Kind
	                              		  	if (unused[currentPlayer][6] == true)
	                              		  	{
	                              				scoring[currentPlayer][6] = 0;
	                              				for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					for (nextDie1 = thisDie + 1; nextDie1 < maxDie; nextDie1++)
	                              					{
	                              						for (nextDie2 = nextDie1 + 1; nextDie2 < maxDie; nextDie2++)
	                              						{	
	                              							if (numDie[thisDie] == numDie[nextDie1] &&
	                              								numDie[nextDie1] == numDie[nextDie2])
	                              							{
	                              		 					scoring[currentPlayer][6]= numDie[0] + numDie[1] + numDie[2] + numDie[3] + numDie[4];
	                              		 					}
	                              							
	                              						}
	                              					}
	                              				}
	                              				scoring[currentPlayer][14] += scoring[currentPlayer][6];//adds to lowwer subtotal

	                              		 		usedCategory[currentPlayer][6] = "*";
	                              		 		unused[currentPlayer][6] = false;
	                              		  	}
	                              			break;
	                              			
	                              		case 8:// 4 of a Kind
	                              			if (unused[currentPlayer][7] = true)
	                              			{
	                              				for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					for (nextDie1 = thisDie + 1; nextDie1 < maxDie; nextDie1++)
	                              					{
	                              				
	                              						for (nextDie2 = nextDie1 + 1; nextDie2 < maxDie; nextDie2++)
	                              						{	
	                              							for (nextDie3 = nextDie2 + 1; nextDie3 < maxDie; nextDie3++)
	                              							{
	                              								if (numDie[thisDie] == numDie[nextDie1] &&
	                              									numDie[nextDie1] == numDie[nextDie2] &&
		                              								numDie[nextDie2] == numDie[nextDie3])
		                              							{
	                              		 							scoring[currentPlayer][7]= numDie[0] + numDie[1] + numDie[2] + numDie[3] + numDie[4];
	                              		 						}
	                              							}
	                              						}
	                              					}
	                              				}
	                              				scoring[currentPlayer][14] += scoring[currentPlayer][7];
	
												usedCategory[currentPlayer][7] = "*";
												unused[currentPlayer][7] = true;
	                              			}
	                              			break;
	                              			
	                              		case 9://Full House (three of one number and two of another)
	                              			if (unused[currentPlayer][8] == true)
	                              			{	
												for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					for (nextDie1 = thisDie + 1; nextDie1 < maxDie; nextDie1++)
	                              					{
	                              				
	                              						for (nextDie2 = nextDie1 + 1; nextDie2 < maxDie; nextDie2++)
	                              						{	
	                              							for (nextDie3 = 0; nextDie3 < maxDie; nextDie3++)
	                              							{
	                              								for (nextDie4 = nextDie3 + 1; nextDie4 < maxDie; nextDie4++)
	                              								{
	                              									if (numDie[thisDie] == numDie[nextDie1] &&		
	                              										numDie[nextDie1] == numDie[nextDie2] &&
	                              										numDie[nextDie2] != numDie[nextDie3] &&
	                              										numDie[nextDie3] == numDie[nextDie4])
	                              									{
	                              								
	                              		 								scoring[currentPlayer][8]= 25;
	                              									}
	                              								}
	                              		 					}
	                              						}
	                              					}
	                              				}
	                              				scoring[currentPlayer][14] += scoring[currentPlayer][8];
 
	                              				usedCategory[currentPlayer][8] = "*";
	                              				unused[currentPlayer][8] = false;
	                              			}
	                              			break;
	                              			
	                              		case 10://Small Straight (three consecutives)
	                              			if (unused[currentPlayer][9] == true)
	                              			{
	                              				for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					for (nextDie1 = 0; nextDie1 < maxDie; nextDie1++)
	                              					{
	                              						for (nextDie2 = 0; nextDie2 < maxDie; nextDie2++)
	                              						{	
	                              							for (nextDie3 = 0; nextDie3 < maxDie; nextDie3++)
	                              							{
	                              								if (numDie[thisDie] == (numDie[nextDie1] + 1) &&		
	                              									numDie[nextDie1] == (numDie[nextDie2] + 1) &&
	                              									numDie[nextDie2] == (numDie[nextDie3] + 1)) 
	                              								{
	                              		 							scoring[currentPlayer][9]= 30;
	                              								}
	                              							}
	                              						}
	                              					}
	                              				}
	                              				scoring[currentPlayer][14] += scoring[currentPlayer][9];

	                              				usedCategory[currentPlayer][9] = "*";
	                              				unused[currentPlayer][9] = false;
	                              			}
	                              			break;
	                              		
	                              		case 11://Large Straight (four consecutives)
	                              			if (unused[currentPlayer][10] == true)
	                              			{
	                              				for (thisDie = 0; thisDie < maxDie; thisDie ++)
	                              				{
	                              					for (nextDie1 = 0; nextDie1 < maxDie; nextDie1++)
	                              					{
	                              						for (nextDie2 = 0; nextDie2 < maxDie; nextDie2++)
	                              						{	
	                              							for (nextDie3 = 0; nextDie3 < maxDie; nextDie3++)
	                              							{
	                              								for (nextDie4 = 0; nextDie4 < maxDie; nextDie4++)
	                              								{
	                              									if (numDie[thisDie] == (numDie[nextDie1] + 1) &&		
	                              										numDie[nextDie1] == (numDie[nextDie2] + 1) &&
	                              										numDie[nextDie2] == (numDie[nextDie3] + 1) &&
	                              										numDie[nextDie3] == (numDie[nextDie4] + 1) )
	                              									{
	                              		 								scoring[currentPlayer][10] = 40;
	                              									}
	                              								}
	                              							}
	                              						}
	                              					}
	                              				}
	                              				scoring[currentPlayer][14] += scoring[currentPlayer][10];

	                              				usedCategory[currentPlayer][10] = "*";
	                              				unused[currentPlayer][10] = false;
	                              			}
	                              			break;
	                              		
	                              		case 12://Yahtzee 
	                              			if (unused[currentPlayer][11] == true)
	                              			{
												if (numDie[0] == numDie[1] &&
													numDie[0] == numDie[2] &&
													numDie[0] == numDie[3] &&
													numDie[0] == numDie[4]   )
												{
													scoring[currentPlayer][11] = 50;
												}
												scoring[currentPlayer][14] += scoring[currentPlayer][11];
 
	                        	      			usedCategory[currentPlayer][11] = "*";
	                        	      			unused[currentPlayer][11] = false;
	                              			}
	                              			break;
	                              			
	                              		case 13://Chance (adds up all the value of the dice)
	                              			if (unused[currentPlayer][12] == true)
	                              			{
	                              				scoring[currentPlayer][12]= numDie[0] + numDie[1] + numDie[2] + numDie[3] + numDie[4];
	                              				scoring[currentPlayer][14] += scoring[currentPlayer][12];
 
		                              			usedCategory[currentPlayer][12] = "*";
		                              			unused[currentPlayer][12] = false;
	                              			}
	                              			break;
	                              	}//end of categoryChoice switch
	                              	
	                              	//63 bonus points for upper score if total of first six category is over or equal to 63 (only once).
	                              	if (scoring[currentPlayer][13] >= 63)
	                              	{
	                              		if (unused[currentPlayer][13] == true)
	                              		{
	                              			scoring[currentPlayer][15] += 63;
	                              			unused[currentPlayer][13] = false;
	                              		}
	                              	}
	                              	
	                              	scoring[currentPlayer][15] = scoring[currentPlayer][13] + scoring[currentPlayer][14];//calculates Grand Total
	                              	
	                              	//list grand total score for each player
	                              	for (display = 0; display < num; display++)
	                              		{
	                              			allTotalScore += player[display] + ":  " + scoring [display][15] + "\n";
	                              		}
	                              	
	                              	//List score in each category for current player.
	                              	currentTally = player[currentPlayer] + " scores" + "\n~~~~~~~~~~~~~~~~~~~~~~~" 
	                              			+ "\nOnes: " + scoring[currentPlayer][0]  
											+ "\nTwos: " + scoring[currentPlayer][1]
											+ "\nThrees: " + scoring[currentPlayer][2]
				 							+ "\nFours: " + scoring[currentPlayer][3] 
				 							+ "\nFives: " + scoring[currentPlayer][4]
				 							+ "\nSixes: " + scoring[currentPlayer][5]
				 							+ "\nTotal of Upper Half: " + scoring[currentPlayer][13]
				 							+ "\n3 of a Kind: " + scoring[currentPlayer][6]
				 							+ "\n4 of a Kind: " + scoring[currentPlayer][7]
				 							+ "\nFull House: " + scoring[currentPlayer][8]
				 							+ "\nSmall Straight: " + scoring[currentPlayer][9]
				 							+ "\nLarge Straight: " + scoring[currentPlayer][10]
				 							+ "\nYahtzee: " + scoring[currentPlayer][11]
				 							+ "\nChance: " + scoring[currentPlayer][12]
				 							+ "\n Total of Lower Half: " + scoring[currentPlayer][14]
				 							+ "\n Grand Total: " + scoring[currentPlayer][15]
				 							+ "\n\n ~~~~~~~~~~~~~~~~~~~ \nTotal score\n";
				 							
				 					JOptionPane.showMessageDialog(null,currentTally + allTotalScore) ;
				 					
				 					//does not erase after the 12th category is finished
				 					if (choices < (times - 1))
				 						allTotalScore = "";//prevent previous final score shown.
				 						
			}//end of category (currentPlayer) for loop.
			
			
		
		}//end of choice for loop.
		
		JOptionPane.showMessageDialog(null,"\t\tEND GAME: Final Results \n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + allTotalScore) ;
		
	} //end of main method
	
	//returns the value of the new dice
	public static int[] switchReroll (int[] dice, int throwDice) 
	{
        Die die1;      
        die1 = new Die();
        
		switch (throwDice) 
		{
		case '1':
	        dice[0] =  die1.roll();
	        break;
		case '2':
	        dice[1] =  die1.roll();
	        break;
		case '3':
	        dice[2] =  die1.roll();
   	        break;
		case '4':
	        dice[3] =  die1.roll();
	        break;
		case '5':
	        dice[4] =  die1.roll();
	        break;
					
   		 } //end of throwDice switch
   		 
   		 return dice;
	}//end of switchReroll method
	
	//returns statement asking user if they want to reroll and current Die value.
	public static String rerollStatement (String[] player,int[] numDie,int thisPlayer)
	{

		String statement;
		
		statement = JOptionPane.showInputDialog (player[thisPlayer] + " turn\n~~~~~~~~~~~~~~~~~~~~~~~" 
				 													+"\nDie1: " + numDie[0]
      			  									   				+ "\nDie2: " + numDie[1]
      			  									   				+ "\nDie3: " + numDie[2]
      			  									   				+ "\nDie4: " + numDie[3]
      			  									   				+ "\nDie5: " + numDie[4]
      			  									   				+ "\nWhich dice do you want to reroll"
      			  									   				);	
      	return statement;
      			  									   			
 							   			
      			  									 
	}//end of rerollStatement method
		
}