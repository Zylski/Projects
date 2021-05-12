

/*------------------------------------------------------------------------------------------
Programmer:       Damian Zylski
Date:             04/12/2015
System:           Windows XP - Visual studio .net 2003


PROGRAM: RPG

Purpose: To create a simple rpg that involves a character wandering through several 
rooms. Edit 2021: I may have not been doing this the right way back then. I look at this now
and wonder why I coded things the way I did!
*/
//------------------------------------------------------------------------------------------

//include various libraries
#include <math.h>
#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include "StdAfx.h"
#include <malloc.h>
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <cstring>
using namespace std;

//Character data
struct Node
{
	char name[256]; //Character name
	int lvl;     //Level
	int exp;     //Amount of experience
	int hp;      //hit points
	int curhp;   //Current hp
	int mp;      //magic points
	int curMP;	 //Current MP

	int att;     //Weapon attack
	int def;     //Armour defense
	int str;     //Strength
	int vit;     //Vitality
	int inte;    //intelligence
	int spr;     //Spirit
	int agl;     //agility
	int lck;     //Luck

	int hit;     //hit percent
	int evd;     //evade percent

	char job;    //Job class
	

	struct Node * pNext;
	struct Node * pPrev; 

}chr;

struct Items //Inventory of game items
{
	int i1;    //potion
	int i2;    //ether
	int i3;
	int i4;
	int i5;
	int i6;
	int i7;
	int i8;
	int i9;
	int i10;
	int i11;
	int i12;
	int i13;
	int i14;
	int i15;   //MegaElixir
	 

} Item;

struct Equipment //Equipment: weapons and armour
{
	int e1;                  //Broad sword         d
	int e2;                  //Gunblade            d
	int e3;                  //Buster Sword        d
   	int e4;					 //Masamune            d
	int e5;					 //Mage Staff          m
	int e6;					 //Holy Staff          m
	int e7;					 //Doomsday Staff      m
	int e8;					 //Dagger              t
	int e9;					 //Ninja blade         t
	int e10;				 //Orihalcon           t
	int e11;				 //Rusty Iron armour   d
	int e12;				 //Mithril Armour      d
	int e13;                 //Genji Armour        d
	int e14;                 //Daedric Armour      d
	int e15;                 //Mage robes          m
	int e16;                 //Arcane Robes        m
	int e17;				 //Necromancer robes   m
	int e18;                 //Leather Armour      t
	int e19;                 //Brigandine          t
	int e20;                 //Assassin gear       t
	int e21;                 //Godly armor         k
	int e22;                 //Godly Blades        k

}Equip;

struct Magics //catalog of magic
{
	int m1; //Fire
	int m2; //Blizzard
	int m3; //Thunder
	int m4; //Water
	int m5;
	int m6;
	int m7;
	int m8;
	int m9;
	int m10;
	int m11;
	int m12;
	int m13;
	int m14; //meteor
	int m15; //Doomsday

}Magic;

struct Skills //Catalog of skills
{
	int s1; //Darkside
	int s2; //Steal
	int s3;
	int s4;
	int s5;
	int s6;
	int s7;
	int s8;
	int s9;
	int s10;
	int s11;
	int s12;
	int s13;
	int s14;
	int s15;
}Skill;

//*****************************************************************************************

//Program to create character
int CreateChar()
{
	int done = 0;       //determines whether character is done
	int i = 0;          //counter variable
	int points = 0 ;    //points to be allocated into core stats
	int stat = 0;       // determines which stat gets points
	char choice = 'Z';  //yes or no
	
	
		system("cls"); //clear the screen
	
					   //Display character create screen
		printf("---------------------------------CREATE CHARACTER-------------------------------\n\n\n\n");
		
		//SET CHARACTER DEFAULT STAT VALUES
		chr.lvl = 1;
		chr.exp = 0;
		chr.hp  = 300;
		chr.curhp = 300;
		chr.mp = 50;
		chr.curMP = 50;
		chr.att = 14;
		chr.def = 10;
		chr.str = 4;
		chr.vit = 4;
		chr.inte = 4;
		chr.spr = 4;
		chr.agl = 4;
		chr.lck = 4;
		chr.hit = 100;
		chr.evd = 100;

		Item.i1 = 3;    //3 potions!



		//BEGIN ROLLS FOR CORE STATS
		//ONE STAT INCREMENTED IN A LOOP EACH TIME BY 1

		_flushall();         //Flush?

		srand((int)time(0)); //this statement ensures the numbers are always random

	while(done != 1)
	{
		i = 0;

		//begin generating
		while(points != 26)
		{
			stat = rand() % 7;

			//For Strength
			if(stat == 0 || stat == 1 && chr.str != 18)
			{
				chr.str = chr.str + 1;
				points = points + 1;
			}

			//For agility
			if(stat == 2 || stat == 3 && chr.agl != 18)
			{
				chr.agl = chr.agl + 1;
				points = points + 1;
			}

			//For Intelligence
			if(stat == 4 || stat == 5 && chr.inte != 18)
			{
				chr.inte = chr.inte + 1;
				points = points + 1;
			}

			//For Luck
			if(stat == 6 && chr.lck != 18)
			{
				chr.lck = chr.lck
					+ 1;
				points = points + 1;
			}

			
			
		}

		//Apply skills, items, and bonus
		switch (chr.job) 
		{
			case 'd': //if input is d warrior
				//Equips
				Equip.e1 = 1;
				chr.att = 14;
				Equip.e11 = 1;
				chr.def = 15;
				//HP Bonus
				chr.hp = chr.hp + 100;
				chr.curhp = chr.hp;
				//Items
				//Skills
				Skill.s1 = 1;
				
				break;
			case 'm': //if input is m magician
				//Equips
				Equip.e5 = 1;
				chr.att = 1;
				Equip.e15 = 1;
				chr.def = 5;
				//MP Bonus
				chr.mp = chr.mp + 75;
				chr.curMP = chr.mp;
				//Items
				Item.i2 = 3;   //Ethers!
				//Skills
				
				break;
			case 't': //if input is t Thief
				//Equips
				Equip.e8 = 1;
				chr.att = 8;
				Equip.e18 = 1;
				chr.def = 9;
				//Agl and luck Bonus
				chr.agl = chr.agl + 20;
				chr.lck = chr.lck + 15;
				//Items
				Item.i1 = 5;
				//Skills
				
				break;
			case 'k': //if input is k kratos
				//Equip
				Equip.e21 = 1;
				Equip.e22 = 1;
				//Godly bonus
				chr.lvl = 99;
				chr.exp = 24;
				chr.hp  = 9999;
				chr.curhp = 9999;
				chr.mp = 999;
				chr.curMP = 999;
				chr.att = 100;
				chr.def = 100;
				chr.str = 255;
				chr.vit = 255;
				chr.inte = 255;
				chr.spr = 255;
				chr.agl = 255;
				chr.lck = 255;
				chr.hit = 255;
				chr.evd = 255;
				//item
				Item.i15 = 1;
				//skill
				break;
		}

		//DISPLAY CHARACTER INFO

		//Print character name
			printf("Character Name: ");

		for(i = 0; i < 255; i++)
		{
			if(chr.name[i] != NULL)
			{
				printf("%c", chr.name[i]);
			}
			
		}

			printf("\n\n");
			
			//AND THEN DISPLAY STATS
			printf("Level: %d\n", chr.lvl);
			printf("Experience: %d\n", chr.exp);
			printf("Hit Points: %d\n", chr.hp);
			printf("Magic Points: %d\n", chr.mp);
			printf("------------------------------------CORE STATS----------------------------------\n");
			printf("Strength: %d\n", chr.str);
			printf("Agility: %d\n", chr.agl);
			printf("Luck: %d\n", chr.lck);
			printf("Intelligence: %d\n", chr.inte);
			printf("--------------------------------------------------------------------------------\n");
			printf("Vitality: %d\n", chr.vit);
			printf("Spirit: %d\n", chr.spr);
			printf("Hit %: %d\n", chr.hit);
			printf("Evade %: %d\n\n", chr.evd);
			
			//Warrior
			if(chr.job == 'd')
			{
			printf("Attack: %d                                     Weapon: Broad sword\n", chr.att);
			printf("Defense: %d                                    Armour: Rusty Iron Armour\n", chr.def);
			}
			//Mage
			else if(chr.job == 'm')
			{
			printf("Attack: %d                                     Weapon: Mage Staff\n", chr.att);
			printf("Defense: %d                                    Armour: Mage Robes\n", chr.def);
			}
			//Thief
			else if(chr.job == 't')
			{
			printf("Attack: %d                                     Weapon: Dagger\n", chr.att);
			printf("Defense: %d                                    Armour: Leather Armour\n", chr.def);
			}			
			//Kratos
			else if(chr.job == 'k')
			{
			printf("Attack: %d                                     Weapon: Godly Blades\n", chr.att);
			printf("Defense: %d                                    Armour: Godly Armour\n", chr.def);
			}

			//ASK FOR REROLL
			printf("\n\nWould you like to reroll your core stats? (y or n)\n\n");

			//Get input from the user
			for(i = 0; i<1; i)            //Loop for error check
			{
				scanf(" %c", &choice);
				if(choice == 'y' || choice == 'Y')      //Yes
				{   //RESET!!!

					//SET CHARACTER DEFAULT STAT VALUES
					chr.lvl = 1;
					chr.exp = 0;
					chr.hp  = 300;
					chr.curhp = 300;
					chr.mp = 50;
					chr.curMP = 50;
					chr.att = 14;
					chr.def = 10;
					chr.str = 4;
					chr.vit = 4;
					chr.inte = 4;
					chr.spr = 4;
					chr.agl = 4;
					chr.lck = 4;
					chr.hit = 100;
					chr.evd = 100;

					points = 0;
					choice = 'z';
					i = 1;

					system("cls"); //clear the screen
	
					//DIsplay character create screen
					printf("---------------------------------CREATE CHARACTER-------------------------------\n\n\n\n");
					
				}
				else if(choice == 'n' || choice == 'N')
				{
					
					i=1;
					done=1;
					break;
				}

				else 
				{
					//Alert user of error
					printf("Input must be Y or N!\n");

					_flushall();   //Flush all input in case too many characters pressed.

					
					choice = 'z';  //Set input back to default
				}

			}
				
			}
	return 0;
}
//*****************************************************************************************
//Program to get name ...and job
char GetName()
{
	int i = 0;              //counter
	char input = 'z';       //Choice

	system("cls"); //clear the screen

	//Get the characters name
	printf("\n\n\n\n\n\n\n\n\n\n\nWhat is your character's name?\n\nENTER: ");
	_flushall();                   //flushall solves so many problems!
	fgets (chr.name, 256, stdin ); //USE THIS TO GET NAME!
	
	//Get the job here too. Why not?
	printf("\n\n\nWhat is your job class?\n\n");
	printf("Dark knight(d)         Arch Mage(m)          Thief(t)             Kratos(k)\n\n");
	//Get input from the user
			for(i = 0; i<1; i)            //Loop for error check
			{
				scanf(" %c", &chr.job);

				if(chr.job == 'd' || chr.job == 'm' || chr.job == 't' || chr.job == 'k')
				{
					i = 1;
				}

				else
				{
					printf("\nIncorrect input!\n");
				}
			}
	return 'Z';
}
//*****************************************************************************************
float Communism(){

	//     OPENING THE FILE
	//-------------------------------
	// call the file
	FILE *pInputFile;        // file pointer 
	char *theFile; // The file
	char *pArry;

	//store file in dynamic array
	theFile = (char *) malloc ( 2000 * sizeof(char) );

	

	int i = 0;                 // loop counter 
   char inputFileName[] = "hammer.txt"; 

   // Associate the actual file name with file pointer and try to open it 
   //printf("\nOpening file hammer.txt.\n\n");

   pInputFile = fopen(inputFileName, "r");
   // Verify input file was found
   if( pInputFile == NULL) {
      printf("Attempt to open file %s failed.  Exiting ... \n", inputFileName);
      exit( -1);       
   }
   
   // Read from the file into the array.  Space in front of %c is NOT important!
   while( fscanf(pInputFile, "%c", &theFile[ i]) != EOF)  {
	   
      i++;
   }

   fclose( pInputFile);   // close input file

   for(i = 0; i < 1170; i++){
		   printf("%c", theFile[i]);
   }

   printf("\n");

   free(theFile);

	return 0.1;
}
//****************************************************************************************************
//Program to display logo screen
char ZylskiSoft()
{
	//Display company logo
	system("cls");
	printf("\n\n\n\n\n\n\n");
	printf("                                   ______\n");
	printf("                                  |___  /\n");
	printf("                                     / / \n");
	printf("                                    / /  \n");
	printf("                                   / /__ \n");
	printf("                                  /_____|\n\n");
	printf("                             ZylskiSoft Presents               \n\n\n\n\n\n\n\n\n");
	system("pause");

	return 'Z';
}
//***************************************************************************************
//Program to display main menu
char MainMenu()
{
	char input = 'Z';     //The input character for the main menu
	int i = 0;            //Loop counter

	//Print the game title
	printf("                                  __________\n");
	printf("                                 |          |\n");
	printf("                                 |          |\n");
	printf("                                 |   /\\/\\   |\n");
	printf("                                 |          |\n");
	printf("                                 |  Monster |\n");
	printf("                                 |  Energy  |\n");
	printf("                                  ---------- \n");
	printf("          ____ __ __ _  __   __       _  _  __  __ _  ____ ____ ____ ____ \n");
	printf("         (  __|  |   ( \/ _\\ (  )     ( \\/ )/  \\(  ( \\/ ___|_  _|  __|  _ \\ \n");
	printf("          ) _) )(/    /    \\/ (_/\\   / \\/ (  O )    /\\___ \\ )(  ) _) )   / \n");
	printf("         (__) (__)_)__)_/\\_/\\____/   \\_)(_/\\__/\\_)__)(____/(__)(____|__\\_) \n\n\n\n\n");
	printf(" New Game (a)\n Load Game (b)\n Options (c)\n");

	
	printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	printf("                           Copyright ZylskiSoft 2015\n");

	//Get input from the user
	for(i = 0; i<1; i)            //Loop for error check
	{
	scanf(" %c", &input);
	if(input == 'a' || input == 'A')      //To start new game
	{
		
		//START NEW GAME
		GetName();

		//move on to character create screen
		CreateChar();
		return 0;
	}
	else if(input == 'b' || input == 'B') //To load game
	{
		//LOAD GAME
	}
	else if(input == 'c' || input == 'C') //To open options menu
	{
		//OPEN OPTIONS MENU
	}
	else if(input == 'z' || input == 'Z') //To open secret menu
	{
		//Communism
		Communism();
	}
	else 
	{
		//Alert user of error
		system("cls");
		printf("Input must be A, B, or C!\n\n\n");

		_flushall();   //Flush all input in case too many characters pressed.

		system("pause");
		input = 'z';  //Set input back to default

		

		system("cls");
		MainMenu();   //Load the main menu again
	}
	}
	

	return 'Z';
}
//*****************************************************************************************
//Battle Program
int Battle(const std::string & monster)
{
	//The battle program. Enemy data will be stored in a specific variable read from text file.
	//the monster variable specifies which monster's data will be used
	//Battles are turn based and the speed stat determines who may go first.
	//when either enemy or player hp reaches 0, the battle will end via if statements.

	//Monster variables
	std::string name;  //String that holds monster name data
	std::string job;   //String that holds job name data
	int lvl = 0;	   //Array that holds monster level data
	int exp = 0; 	   //Array that holds monster experience data

	int curHp = 0;   //Array that holds monster HP data
	int curMp = 0;   //Array that holds monster MP data

	int att = 0;     //Array that holds monster attack data
	int def = 0;     //Array that holds monster defence data
	int str = 0;     //Array that holds monster strength data
	int vit = 0;     //Array that holds monster vitality data
	int inte = 0;    //Array that holds monster intelligence data
	int spr = 0;     //Array that holds monster spirit data
	int agl = 0;
	int lck = 0;     //Array that holds monster luck data

	int hit = 0;     //Array that holds monster hit % data
	int evd = 0;     //Array that holds monster evade data

	//Battle variables
	int chrTurn = 0; //character turn value
	int monTurn = 0; //Monster turn value
	int damage = 0;  //Damage dealt by either character
	int i = 0;       //counter variable

	//TEMP PLAYER VALUES********************************************************************
			//SET CHARACTER DEFAULT STAT VALUES
			chr.lvl = 1;
			chr.exp = 0;
			chr.hp  = 300;
			chr.curhp = 300;
			chr.mp = 50;
			chr.curMP = 50;
			chr.att = 14;
			chr.def = 10;
			chr.str = 4;
			chr.vit = 4;
			chr.inte = 4;
			chr.spr = 4;
			chr.agl = 6;
			chr.lck = 4;
			chr.hit = 100;
			chr.evd = 100;

			Item.i1 = 3;    //3 potions!

	//BELOW IS THE MONSTER DATA READER
	
	std::ifstream fin(monster.c_str()); // opens the text file
	
		//Check if file is present and then write to variables in the function.
		if(fin.is_open) 
		{
			//If file is not found, display message
			if(!fin)
			{
				printf("\nFILE IS CORRUPT! \n\n\n");

				fin.close();  //Always close the file
				system("pause");
				exit(-1);
			}
			//read in values
			fin >> name;
			fin >> job;
			fin >> lvl;
			fin >> exp;
			fin >> curHp;
			fin >> curMp;
			fin >> att;
			fin >> def;
			fin >> str;
			fin >> vit;
			fin >> inte;
			fin >> spr;
			fin >> agl;
			fin >> lck;
			fin >> hit;
			fin >> evd;

			fin.close();  //Always close the file
		}

		//Decide turn order.

			//first check character speed
			if(chr.agl >= agl)
			{
				chrTurn = 1;
				//check for double speed
				if(chr.agl >= agl * 2)
				{
					chrTurn = 2;
				}
			
			}
			//Check for enemy speed
			else if(agl > chr.agl)
			{
				monTurn = 1;
				//check for double speed
				if(agl > chr.agl * 2)
				{
					monTurn = 2;
				}
			
			}

		//BEGIN BATTLE LOOP!
		for(i = 0; i < 2; i)
		{
			
			//Initiate battle turns depending on turn order

			//Check for player turn
			while(chrTurn > 0 )
				{
					//Perform player action
					printf("\n\nplayer turn!\\n\n");
					//Reset enemy turns
					monTurn = 1; 
					if(agl > chr.agl * 2)
					{
						monTurn = 2;
					}
					//subtract turn
					chrTurn = chrTurn - 1;
				}

			while(monTurn > 0 )
			{
				//Perform enemy action
				printf("\n\nenemy Turn!\\n\n");
				//Reset player turns
				chrTurn = 1; 
				if(chr.agl > agl * 2 )
				{
					chrTurn = 2;
				}
				//Subtract turn
				monTurn = monTurn - 1;
			}
				
			system("pause");
		}

	
	return 0;
}
//*****************************************************************************************
//MAIN
int main()
{
	//Display the title screen
	//ZylskiSoft();

	//MainMenu();

	Battle("Skeleton.txt");

	//printf("!");

	system("pause");
	return 0;
}


