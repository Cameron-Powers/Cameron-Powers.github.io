def guess_maker(counter, length):
    hidden = [" ", " ", " ", " ", " ", " "]
    colours = ["red", "blue", "green", "yellow", "brown", "orange", "black", "white"]
    if length == 3:
        for x in range(3):
            import random
            pos1 = colours[random.randrange(len(colours))]
            pos2 = colours[random.randrange(len(colours))]
            pos3 = colours[random.randrange(len(colours))]
            hidden = [pos1, pos2, pos3]
    elif length == 4:
        for x in range(4):
            import random
            pos1 = colours[random.randrange(len(colours))]
            pos2 = colours[random.randrange(len(colours))]
            pos3 = colours[random.randrange(len(colours))]
            pos4 = colours[random.randrange(len(colours))]
            hidden = [pos1, pos2, pos3, pos4]
    elif length == 5:
        for x in range(5):
            import random
            pos1 = colours[random.randrange(len(colours))]
            pos2 = colours[random.randrange(len(colours))]
            pos3 = colours[random.randrange(len(colours))]
            pos4 = colours[random.randrange(len(colours))]
            pos5 = colours[random.randrange(len(colours))]
            hidden = [pos1, pos2, pos3, pos4, pos5]
    elif length == 6:
        for x in range(6):
            import random
            pos1 = colours[random.randrange(len(colours))]
            pos2 = colours[random.randrange(len(colours))]
            pos3 = colours[random.randrange(len(colours))]
            pos4 = colours[random.randrange(len(colours))]
            pos5 = colours[random.randrange(len(colours))]
            pos6 = colours[random.randrange(len(colours))]
            hidden = [pos1, pos2, pos3, pos4, pos5, pos6]

    player_guess(hidden, counter, length)


def double(counter, length):  # Make this to work with length and stuff
    position = 1
    colours = ["red", "blue", "green", "yellow", "brown", "orange", "black", "white"]
    print(" ")
    print("You will be playing mastermind with two players where you can enter your own code and the other user must crack it")
    print(" ")
    print("Colour Options are: ")
    print("red, blue, green, yellow, brown, orange, black, and white")
    hidden = ["", "", "", "", "", ""]
    for x in range(length):
        zz = 0
        while zz != 1:
            pos = input("What colour will be in position " + str(position) + ": ")
            if pos not in colours:
                print("Invalid Input")
            else:
                position += 1
                hidden[x] += pos
                zz = 1

    player_guess(hidden, counter, length)


def player_guess(hidden, counter, length):
    position = 1
    guess = ["", "", "", "", "", ""]
    colours = ["red", "blue", "green", "yellow", "brown", "orange", "black", "white"]
    if counter > 0:
        print(" ")
        print("Colour Options are: ")
        print("red, blue, green, yellow, brown, orange, black, and white")
        print("Chances remaining: " + str(counter))
        counter -= 1
        print(" ")

        for x in range(length):
            arm = 0
            while arm != 1:
                pos_guess = input("What color is in position " + str(position) + ": ").lower()
                if pos_guess not in colours:
                    print("Invalid Guess")
                else:
                    position += 1
                    guess[x] += pos_guess
                    arm = 1
        print(" ")
        checker(hidden, guess, counter, length)
    else:
        print(" ")
        print("You lose, you have not cracked the code, you are not a Mastermind :(")
        play_again()


def checker(hidden, guess, counter, length):
    a = 0
    b = 0
    c = 0
    for x in range(length):
        if hidden[x] in guess[x]:
            a += 1
            b -= 1
        if guess[x] in hidden[0:4]:
            b += 1
        else:
            c += 1
    if a != length:
        if c == 4:
                print("No Pins Outputted")
        else:
            if a > 0:
                for a in range(a):
                    print("X")
            if b > 0:
                for b in range(b):
                    print("O")

        player_guess(hidden, counter, length)
    else:
        print("Congratulations! You are a Mastermind, you have gotten the correct colours!!")
        print(" ")
        play_again()


def play_again():
    www = 0
    print(" ")
    print("Would you like to play again?")

    while www == 0:
        wow = input("Yes or No?: ").lower()
        if wow == "yes":
            game_start()
            www = 1
        elif wow == "no":
            www = 1
        else:
            print("Invalid Input")


def game_start():
    okey_dokey = 0
    okay = 0
    print("Welcome to Mastermind!!!")
    while okay != 1:
        idiot = input("Do you know how to play Mastermind? Yes or No?: ").lower()
        if idiot == "no":
            print(" ")
            print(" The game starts with you choosing single or double player, if you choose single, the computer will make a code for you. In double player, the user will input the code")
            print(" You then choose your difficulty, Easy has 16 chances to guess the code, Normal has 12, Hard has 8, and Impossible has 3")
            print(" A code is a series of four colours in a specific order. There are a total of eight colours. The objective of the player is to guess what that exact code is beofre they run out of chances")
            print(" You step by step get closer to cracking the code by what is outputted after each row is filled in. If you have a correct colour, in the correct place, a X will be outputted.")
            print(" If you have a correct colour but in the wrong spot, a O will be outputted. If four X's are outputted, you win the game.")
            print(" If you do not guess the correct code by the time you run out of chances, you will lose.")
            print(" ")
            okay = 1
        elif idiot == "yes":
            okay = 1
        else:
            print("Invalid input")

    print(" ")
    print("What difficulty would you like to play on?")
    print("Easy has 16 chances, Normal has 12, Hard has 8, and Impossible has 3")
    print(" ")

    while okey_dokey != 1:
        mode = input("What is your choice?: ").lower()
        if mode == "easy":
            counter = 16
            okey_dokey = 1
        elif mode == "normal":
            counter = 12
            okey_dokey = 1
        elif mode == "hard":
            counter = 8
            okey_dokey = 1
        elif mode == "impossible":
            counter = 3
            okey_dokey = 1
        else:
            print("Invalid Input")

    print("How long do you want the length of the code to be? ( 3 - 6 ) ")
    omfg = 0
    while omfg != 1:
        girth = input("How long would you like it to be?: ")
        if girth == "3":
            length = 3
            omfg = 1
        elif girth == "4":
            length = 4
            omfg = 1
        elif girth == "5":
            length = 5
            omfg = 1
        elif girth == "6":
            length = 6
            omfg = 1
        else:
            print("Invalid Input")

    print(" ")
    print("Would you like to play double or single player?")

    k = 0
    while k != 1:
        types = input("D for double player, S for single player: ").lower()
        if types == "d":
            double(counter, length)
            k = 1
        elif types == "s":
            guess_maker(counter, length)
            k = 1
        else:
            print("Invalid Input")

game_start()
