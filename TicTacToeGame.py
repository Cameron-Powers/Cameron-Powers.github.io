import random

def playAgain():
    flag = True
    while flag:
        playAgain = input("Would you like to play again? Yes or no?: ").lower().strip()
        if playAgain == "yes":
            gameStart()
        elif playAgain == "no":
            print("Thanks for playing!")
            flag = False
        else:
            print("Invalid input")

def printBoard(board, gamePiece, computerPiece):
    print(' ')
    print("This is the current board:")
    print("     |     |     ")
    print("  " + board[0] + "  |  " + board[1] + "  |  " + board[2] + "  ")
    print("     |     |     ")
    print("-----------------")
    print("     |     |     ")
    print("  " + board[3] + "  |  " + board[4] + "  |  " + board[5] + "  ")
    print("     |     |     ")
    print("-----------------")
    print("     |     |     ")
    print("  " + board[6] + "  |  " + board[7] + "  |  " + board[8] + "  ")
    print("     |     |     ")
    checkWinner(board, gamePiece, computerPiece)

def checkWinner(board, gamePiece, computerPiece):
    flag = False
    if ((board[0] == board[1] == board [2]) and (board[0] and board[1] and board[2]) != ' '):
        print("GAME OVER, " + board[0] + "'s WIN!")
        flag = True
        
    if ((board[3] == board[4] == board [5]) and (board[3] and board[4] and board[5]) != ' '):
        print("GAME OVER, " + board[3] + "'s WIN!")
        flag = True

    if ((board[6] == board[7] == board [8]) and (board[6] and board[7] and board[8]) != ' '):
        print("GAME OVER, " + board[6] + "'s WIN!")
        flag = True

    if ((board[0] == board[3] == board [6]) and (board[0] and board[3] and board[6]) != ' '):
        print("GAME OVER, " + board[0] + "'s WIN!")
        flag = True

    if ((board[1] == board[4] == board [7]) and (board[1] and board[4] and board[7]) != ' '):
        print("GAME OVER, " + board[1] + "'s WIN!")
        flag = True

    if ((board[2] == board[5] == board [8]) and (board[2] and board[5] and board[8]) != ' '):
        print("GAME OVER, " + board[2] + "'s WIN!")
        flag = True

    if ((board[0] == board[4] == board [8]) and (board[0] and board[4] and board[8]) != ' '):
        print("GAME OVER, " + board[0] + "'s WIN!")
        flag = True

    if ((board[2] == board[4] == board [6]) and (board[2] and board[4] and board[6]) != ' '):
        print("GAME OVER, " + board[2] + "'s WIN!")
        flag = True
        
    if fullBoard(board, gamePiece, computerPiece) == True:
        print("Tie Game, all spaces filled")
        flag = True
        
    if fullBoard(board, gamePiece, computerPiece) == False:
        flag == False

    if flag == True:
        playAgain()
    elif flag == False:
        boardExample(board, gamePiece, computerPiece)
     

def computerTurn(board, gamePiece, computerPiece):
    flag = True
    while flag:
        num = random.randint(0,8)
        if board[num] == ' ':
            board[num] = computerPiece
            flag = False
                
    printBoard(board, gamePiece, computerPiece)   

def fullBoard(board, gamePiece, computerPiece):
    counter = 0
    for x in range(len(board)):
        if board[x] != ' ':
            counter += 1
    if counter == 9:
        return True
    else:
        return False

    
def boardExample(board, gamePiece, computerPiece):
        
    print(" ")
    print("     |     |     ")
    print("  1  |  2  |  3  ")
    print("     |     |     ")
    print("-----------------")
    print("     |     |     ")
    print("  4  |  5  |  6  ")
    print("     |     |     ")
    print("-----------------")
    print("     |     |     ")
    print("  7  |  8  |  9  ")
    print("     |     |     ")
    print(" ")

    flag = True
    while flag:
        position = int(input("What position would you like to place your piece?: ").strip())
        if board[position-1] == ' ':
            if(position > 0) and (position < 10):
                board[position-1] = gamePiece
                flag = False
            else:
                print("Invalid input")
        else:
            print("Space is already filled")
    computerTurn(board, gamePiece, computerPiece)


def gameStart():
    flag = True
    while flag:
        gamePiece = input("Would you like to be X's or O's?: ").upper().strip()
        if gamePiece == "X" or gamePiece == "O":
            flag = False
        else:
            print("Invalid input")

    if gamePiece == "X":
        computerPiece = "O"
    else:
        computerPiece = "X"
    
    board = [' ',' ',' ',' ',' ',' ',' ',' ',' ']

    print("")
    print("To determine who play's first, we will flip a coin")
    
    flag = True
    while flag:
        coin = input("Heads or tails?: ").lower().strip()
        if coin == "heads" or coin == "tails" or coin == "head" or coin == "tails":            
            flag = False
        else:
            print("Invalid input")

    num = random.randint(0,2)
    if num == 1 and ((coin == 'heads') or (coin == 'head')) :
          print("You won the coin toss, you pick first")
          boardExample(board, gamePiece, computerPiece)
    elif num == 2 and ((coin == 'tails') or (coin == 'tail')):
          print("You won the coin toss, you pick first")
          boardExample(board, gamePiece, computerPiece)
    else:
          print("You lost the coin toss, computer picks first")
          computerTurn(board, gamePiece, computerPiece)


####### MAIN #######
flag = True
while flag:
    welcomeMessage = input("Welcome to Tic-Tac-Toe, do you know how to play? Yes or no?: ").lower().strip()
    if welcomeMessage == "no":
        print(" ")
        print("The objective of the game is to get either 3 X's or 3 O's in a row")
        print("You can place an X or O in any empty space on the board")
        print("If the board is full and no player has 3 in a row, the game ends in a tie")
        print(" ")
        flag = False
    elif welcomeMessage == "yes":
        flag = False
    else:
        print("Invalid input")
            
gameStart()
