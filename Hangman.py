def game(phrase):  # function that is used to change phrase to underscores and also runs the game

    a = 0  # used for difficulty while loop exit
    d = 0  # used way at bottom of function to declare if the user has lost the game
    count = 0  # used to keep track of missed guesses/ lives
    first = ""  # for the output of phrase but as underscores
    while a != 1:  # while loop that is for the difficulty choice, if user does not make correct input, the question will be prompted again
        print(" ")  # Spreads out lines of text
        print("What difficulty would you like to play on? baby has 7 lives, wuss has 5 lives, and normal has 2")  # info to user
        difficulty = input("baby, wuss, or normal? ").lower()  # asks user for what difficulty they want to play on, lower sets string to lowercase so not capital letter issues
        if difficulty == "baby":     # this if, elif, and else statements are used to determine how many lives the user will have
            count = 7
            a = 1
        elif difficulty == "wuss":
            count = 5
            a = 1
        elif difficulty == "normal":
            count = 2
            a = 1
        else:
            print("Invalid difficulty choice made")

    for x in range(len(phrase)):  # used to output the phrase in underscores in same order as the phrase originally had
        if phrase[x] != " ":  # a letter is present, it changes to a underscore + space
            first += "_ "
        else:
            first += "  "  # not letter present become two spaces
    print("Find out what this phrase is: " + first)  # outputs phrase in underscores to user
    temp = ""
    for x in range(len(phrase)):  # this for loop is the same as the previous one except there is no spaces after the underscore
        if phrase[x] != " ":
            temp += "_"  # I removed the space as once the string is turned into a list, it looks better when outputted
        else:
            temp += " "
    print(" ")
    print("You have " + str(count) + " lives, every invalid or repeated guess takes away a life. Hit 0 lives and you lose :(")  # info about lives to user
    listedcentral = list(phrase)  # changes converted phrase to a list
    listedtemp = list(temp)  # changes converted phrase in underscores to a list
    guesses = ""  # variable for past guesses made that is shown to user

    while count > 0:  # while loop that runs while the user has 1 or more lives
        if listedcentral == listedtemp:  # if the two lists are the same, the user has guess the word correctly
            print(" ")
            print("!!!!!")
            print("You Have Gotten the Phrase")  # output to user
            print("!!!!!")
            print(" ")
            d = 1  # this keeps the "YOU HAVE LOST" phrase from printing to user
            break #ends the game
        else:
            print(" ")
            print("Letters you have guessed:" + guesses)  # past guesses string
            print("Lives remaining: " + str(count))  # lives remaining string
            print("You can guess the phrase at anytime by saying, Guess Phrase, in the letter guess prompt")
            guess = input("Guess a letter: ").lower()  # user guess input
            if guess == "guess phrase":  # if the user inputs "guess word" they have the ability to take one shot at guessing the entire phrase
                finalguess = input("What is the phrase? If you get it wrong, the game is over. Guess: ").lower()  # entire phrase guess input
                if finalguess == phrase:  # if they guess it right, it sets phrase as the same and the win page will come up
                    listedcentral = listedtemp
                else:
                    print("That is not the phrase, the phrase was: " + phrase)  # if guess is wrong, they lose the game
                    count = 0
            else:
                if guess in listedcentral and guess not in guesses:
                    for x in range(len(phrase)):  # if they guess only a letter, this if and for loop will replace the underscore with he correct letter if correct
                        if guess == listedcentral[x]:
                            listedtemp[x] = guess
                            print(listedtemp)  # prints out underscored phrase with the guessed letters
                    guesses = guesses + " " + guess  # adds guess to the previous guesses list
                else:
                    if guess in guesses:  # if they guess the same letter, this runs which informs them and then removes a life
                        print("You have already guessed this letter")
                        count -= 1
                    else:
                        print("That guess is not valid or not in the phrase")  # if they guess an incorrect letter, they lose a life
                        guesses = guesses + " " + guess
                        count -= 1
    if d != 1:  # if life's hit zero and they have not got the phrase, this runs
        print('You have ran out of lives, YOU LOSE')
        print("The phrase was:")
        print(phrase)  # tells user what the phrase was

def double():  # if the user wants to play double player, this is where they input the phrase
    letters = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", " "]
    a = 1  # above is a list of letters that is used to check if input is valid
    phrase = ""
    while a == 1:  # used to loop question incase input is not valid
        check = True
        phrase = input("What is your phrase: ").lower()

        for x in range(len(phrase)):
            if phrase[x] not in letters:  # if there are invalid characters in input, check is set to false and loop is not able to be exited
                check = False

        if check == True:  # if there are no invalid characters, loop is exited, game is run
            a = 0
        else:
            print("invalid characters in inputted phrase, enter a new phrase")  # if there are  invalid characters, runs loop again
    game(phrase)


def single():  # if the user wants to play single player, this is where they get an automatic the phrase
    print("What category would you like to play from?")
    print("A. Coding")  # user can choose category of choice
    print("B. School")
    print("C. Hockey")
    i = 1  # user to allow exit of while loop
    while i == 1:  # while loop to determine what category the user wants to get a phrase from
        question = input("Enter the letter to select which category you want to play from: ").lower()
        if question == "a":
            print("Coding Selected")
            import random  # this is used to pick a random item from the follow tuple, in a tuple, not a list, as I never want them to change
            codingtuple = ("python is fun", "javascript is great", "my keyboard is broken", "coding like the fish", "html works well with java", "mr.q is a meh coding teacher")
            phrase = (random.choice(codingtuple))  # sets phrase to what was chosen from category
            i = 2
        elif question == "b":
            print("School Selected")
            import random
            schooltuple = ("pencil in pencil case", "i love my third period teacher", "math is the worst", "school is a great place to make friends", "biology chemistry and physics")
            phrase = (random.choice(schooltuple))
            i = 2
        elif question == "c":
            print("Hockey Selected")
            import random
            hockeytuple = ('sidney crosby is amazing', "ovi sure can shoot", "holtby and price can stop a puck", 'the puck hits the ice', "what a slapshot", "hockey is better than soccer")
            phrase = (random.choice(hockeytuple))
            i = 2
        else:
            print("Answer is invalid")

        game(phrase)          # calls main game function


def start():
    aa = ""  # used to allow exit of while loop
    print("Welcome to word guesser/ hangman game!!")
    print('Would you like to play single player or double player?')
    while aa != 1:
        quest = input("S for single player, D double player: ").lower()  # this while is used to determine whether the user is wanting to play single or double player
        if quest == "s":
            print("Single player it is")
            aa = 1
            single()
        elif quest == "d":
            print("Double player it is")
            aa = 1
            double()
        else:
            print("Invalid input!")


start()  # Calls beginning of game function
