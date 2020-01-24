import pygame
import sys
import random

# SCREEN #
width = 800
height = 600
bgColor = (0,0,0)
pygame.init()
screen = pygame.display.set_mode((width,height))

# PLAYER #
playerSize = 50
playerColor = (255,0,0)
playerPos = [width/2,height-2*playerSize]

# ENEMY #
enemySize = 50
speed = 10
enemyColor = (0,0,255)
enemyPos = [random.randint(0,width-enemySize),0]
enemyList = [enemyPos]

score = 80
delayTime = 0.15
scoreColor = (255,255,0)
gameOver = False;
clock = pygame.time.Clock()
myFont = pygame.font.SysFont("monospace", 35)

def dropEnemy(enemyList):
    delay = random.random()
    print(delayTime)
    if len(enemyList) < 10 and delay < delayTime:
        xpos = random.randint(0, width-enemySize)
        ypos = 0
        enemyList.append([xpos,ypos])
    
def drawEnemy(enemyList):
    for enemyPos in enemyList:
        pygame.draw.rect(screen, enemyColor, (enemyPos[0], enemyPos[1], enemySize, enemySize))


def detectCollision(playerPos, enemyPos):
    px = playerPos[0]
    py = playerPos[1]
    ex = enemyPos[0]
    ey = enemyPos[1]
    if (ex >= px and ex < (px + playerSize)) or (px >= ex and px < (ex + enemySize)):
        if (ey >= py and ey < ( py + playerSize)) or (px >= ey and py < (ey + enemySize)):
            return True
    return False
    
def updateEnemyPos(enemyList, score):
    for idx, enemyPos in enumerate(enemyList):
        if enemyPos[1] >= 0 and enemyPos[1] < height:
            enemyPos[1] += speed
        else:
            enemyList.pop(idx)
            score +=1
    return score
            
def collisionCheck(enemyList, playerPos):
    for enemyPos in enemyList:
        if detectCollision(playerPos,enemyPos):
            return True
    return False
    
def setLevel(score,speed):
    if score < 10:
        speed = 5
    elif score < 20:
        speed = 8
    elif score < 60:
        speed = 12
    elif score < 100:
        speed = 15
    return speed

while not gameOver:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
            
        if event.type == pygame.KEYDOWN:
            x = playerPos[0]
            y = playerPos[1]
            if (event.key == pygame.K_LEFT) and (playerPos[0] > 0):
                x -= playerSize
            elif (event.key == pygame.K_RIGHT) and (playerPos[0] < 850):
                x += playerSize
            elif (event.key == pygame.K_UP) and (playerPos[1] > 0):
               y -= playerSize
            elif (event.key == pygame.K_DOWN) and (playerPos[1]< 500):
                y += playerSize
                
            playerPos = [x,y]
    screen.fill(bgColor) 
    dropEnemy(enemyList)
    drawEnemy(enemyList)
    score = updateEnemyPos(enemyList, score)
    speed = setLevel(score, speed)
    text = "Score: " + str(score)
    label = myFont.render(text, 1, scoreColor)
    screen.blit(label, (width-800, height-40))
    pygame.draw.rect(screen, playerColor,(playerPos[0], playerPos[1], playerSize, playerSize))
    if collisionCheck(enemyList, playerPos):
        gameOver = True
        break
    clock.tick(30)
    pygame.display.update()