"""

Programme réalisé par GUNDUZ Maxime

Vous pouvez tester directement le code en cliquant sur le lien ci-dessous:

https://colab.research.google.com/drive/1PA7BS1ZQNm3G4V_BwESszNMgE-uYwBCC?usp=sharing

Code disponible sur mon Github: 

https://github.com/MaximeZiyaGunduz/Jeux-d-echec-/tree/main

"""

from time import time 
#from colored import bg, attr


#Piece Blanche         Definition des pions dans variables
pion="♙"         
tours="♖"
cavaliers="♘"
fous="♗"
dame="♕"
roi="♔"

#Piece Noir
pion_N="♟"                 
tours_N="♜"
cavaliers_N="♞"
fous_N="♝"
dame_N="♛"
roi_N="♚"

def remplisage(tab):      #remplassage tableau par 0
  i=0
  while i<64:
    tab.append("0 ")
    i+=1
  remplisage_pions_noir(tab)
  remplisage_pions_blanc(tab)
  return tab
tab=[]                              

def remplisage_pions_noir(tab):   #Rajout des pions noirs sur l'échequier 
   t = "♜♞♝♛♚♝♞♜"
   i = 0
   cased=8
   case=0
   while i < 8:
     tab[cased] = "♟"
     cased = cased + 1
     i = i +1
   while case < len(t):
      tab[case] = t[case]
      case = case + 1                  

def remplisage_pions_blanc(tab):  #Rajout des pions blancs sur l'échequier
   t = "♖♘♗♕♔♗♘♖"
   i = 0
   cased=48
   case=56
   p = 0
   while i < 8:
     tab[cased] = "♙"
     cased = cased + 1
     i = i +1
   while p < len(t):
      tab[case] = t[p]
      case = case + 1
      p = p + 1                   

def snum(case):            # Traduit un coordonné (B1) en chiffre
  x = ord(case[0])-96
  y = int(case[1])                 
  return num(x,y)

def num(x,y): 
  return 8*(y-1) + x-1

def symbole_piece_blanc(n):     # Traduit numero piece en symbole
  t = "0♙♖♘♗♕♔"
  return t[n]
def symbole_piece_noir(n):
  t = "0♟♜♞♝♛♚"
  return t[n]                 

def echequier(tab):    # Crée l'echequier (en chaines de caractéres)
  y = 8
  while y >= 1:
    ligne = ""
    x = 1
    while x <= 8:
      numero_case = num(x,y)
      ligne += str(tab[numero_case]) + "   " 
      x += 1 
    print(ligne)
    y -= 1                           



'''
def info_position():                         #afficher a coté de l'échiquier pour donner dire les positions existante
    
# Creation de la 1er ligne 
  chaine="   |"   #INITIALISATION DE LA CHAINE
  j=97
  while j<105:
    chaine += chr(j).rjust(4) + "   "   #j'ajoute a la chaine le nb j 
    j+=1                              #conversion en ASCII 
  print(chaine )

#Creation des trait (-) sous la 1er ligne
  chaine=""
  j=0
  while j<15:
    chaine+="----"
    j+=1
  print(chaine)

# Creation de tout ce qui reste sous les trait (-) crée  
  i=1
  while i<9:
    chaine=str(i).rjust(2)+" |"       #Creation de la colonne toute a gauche
    j=1
    caractére=97
    while j<9:
      chaine+= "  "+ str(i)+chr(caractére)+"  |"
      caractére+=1
      j+=1
    print(chaine)
    i+=1
info_position()
'''
def jeu():           #Fonction principal 
  tic = time()       
  compt = 0
  print("JEU D'ECHEQUE :")
  remplisage(tab)
  echequier(tab)
  i = 0
  while i == 0 :
    tour_blanc(compt)
    tour_noir(compt)
    compt = compt + 1
    if gagner() == True :
      tac = time() 
      print("vous avez joués pendant "+str(round((tac-tic)))+" s")
      return True                                                
  
def tour_blanc(compt):        #Jeu des Blancs
   i = 0
   while i == 0 :
    print("Au tour des blancs,")
    tab_rep = [input("Quelle joueur voulait vous jouer ?"),input("Sur qu'elle case doit t'il aller ?")]
    case_depart = tab_rep[0]
    case_arrive = tab_rep[1]
    if tab[snum(case_depart)] == "♔" and droit_du_roi(case_depart, case_arrive) == True or tab[snum(case_depart)] == "♖" and droit_du_tour(case_depart, case_arrive) == True or tab[snum(case_depart)] == "♙" and droit_du_pion(case_depart, case_arrive, compt) == True or tab[snum(case_depart)] == cavaliers and droit_du_cavalier(case_depart, case_arrive) == True and (deplacement_non_blanc(case_arrive) == True and verif_sorti(case_arrive) == True and verif_mes_pions_blanc(case_depart) == True):
      variable = tab[snum(case_depart)] 
      tab[snum(case_depart)] = "0 "
      tab[snum(case_arrive)] = variable
      echequier(tab)
      i = 1
    else :
      print("Mouvement non autorisé")         
    
def tour_noir(compt):           #Jeu des Noirs
  i = 0
  while i == 0 :
    print("Au tour des noirs,")
    tab_rep = [input("Qu'elle joueur voulait vous jouer ?"),input("Sur qu'elle case doit t'il aller ?")]
    case_depart = tab_rep[0]
    case_arrive = tab_rep[1]
    if (tab[snum(case_depart)] == "♚" and droit_du_roi(case_depart, case_arrive) == True) or (tab[snum(case_depart)] == "♜" and droit_du_tour(case_depart, case_arrive) == True) or (tab[snum(case_depart)] == "♟" and droit_du_pion(case_depart, case_arrive, compt) == True) or (tab[snum(case_depart)] == cavaliers_N and droit_du_cavalier(case_depart, case_arrive) == True) and (deplacement_non_noir(case_arrive) == True and verif_sorti(case_arrive) == True and verif_mes_pions_noir(case_depart) == True):
      variable = tab[snum(case_depart)] 
      tab[snum(case_depart)] = "0 "
      tab[snum(case_arrive)] = variable
      echequier(tab)
      i = 1
    else :
      print("Mouvement non autorisé")        

def gagner():    #Fonction gagner si roi pas dans l'échequier alors gagner
  i = 0
  capteur = 0
  while i < len(tab):
    if tab[i] == roi_N :
      capteur = capteur + 1
    if tab[i] == roi :
      capteur = capteur + 2
    i = i + 1
  if capteur == 1 :
    print("Félicitation aux noirs, vous avez gagner la partie !")
    return True
  if capteur == 2 :
    print("Félicitation aux blancs, vous avez gagner la partie !")              
    return True

# LES FONCTIONS DE VERIFICATIONS :

def deplacement_non_noir(case_arrive):      #Définit interdiction de se déplacer sur ses propres pions
  mes_pions = "♟♜♞♝♛♚"
  i = 0
  while i < len(mes_pions):
    if tab[snum(case_arrive)] == mes_pions[i] :
      return False
    i = i + 1
  return True
                                                   
def deplacement_non_blanc(case_arrive):
    mes_pions = "♙♖♘♗♕♔"
    i = 0
    while i < len(mes_pions):
      if tab[snum(case_arrive)] == mes_pions[i] :
        return False
      i = i + 1
    return True

def verif_sorti(case_arrive):    #Fonction verification pour pas sortir de échiquier
  x = case_arrive[0]
  y = int(case_arrive[1])
  if x > "h" or y > 8 :
    return False
  else :
    return True                          

def verif_mes_pions_noir(case_depart):    #Fonction verification pour pas jouer les pions de l'adeversaire
  mes_pions = "♟♜♞♝♛♚"
  i = 0
  capteur = 0
  while i < len(mes_pions):
      if tab[snum(case_depart)] == mes_pions[i] :
        capteur = 1
      i = i + 1
  if capteur == 1 :
    return True
  else:
    return False                                         
def verif_mes_pions_blanc(case_depart):
  mes_pions = "♙♖♘♗♕♔"
  i = 0
  capteur = 0
  while i < len(mes_pions):
      if tab[snum(case_depart)] == mes_pions[i] :
        capteur = 1
      i = i + 1
  if capteur == 1 :
    return True
  else:
    return False

def droit_du_roi(case_depart, case_arrive):    #définit les droits de déplacement du roi (True or False)
  if (snum(case_arrive) == snum(case_depart)+1 or snum(case_arrive) == snum(case_arrive)-1 or snum(case_arrive) == snum(case_depart)+8 or snum(case_arrive) == snum(case_depart)-8):
    return True
  else :
    return False                       
  
def droit_du_tour(case_depart, case_arrive):  #définit les droits de déplacement du tour (True or False)
  if case_arrive[0] == case_depart[0] :
    return True
  if case_arrive[1] == case_depart[1] :
    return True
  else :
    return False    

def droit_du_pion(case_depart, case_arrive, compt):   #définit les droits de déplacements du pion (True or False)
    print(case_depart[0])
    print(case_depart[1])
    if tab[snum(case_depart)] == pion :
      if case_arrive[0] == case_depart[0] and int(case_arrive[1]) == int(case_depart[1]) - 1 or (case_arrive[0] == case_depart[0] and int(case_arrive[1]) == int(case_depart[1]) - 2 and compt == 0) :
        return True
      elif case_arrive[0] == case_depart[0] and int(case_arrive[1]) == int(case_depart[1]) - 1 and compt != 0 :
        return True
      else : 
        return False
    elif tab[snum(case_depart)] == pion_N :
      if case_arrive[0] == case_depart[0] and int(case_arrive[1]) == int(case_depart[1]) + 1 or (case_arrive[0] == case_depart[0] and int(case_arrive[1]) == int(case_depart[1]) + 2 and compt == 0)  :
        return True
      elif case_arrive[0] == case_depart[0] and int(case_arrive[1]) == int(case_depart[1]) + 1 and compt != 0 :
        return True
      else : 
        return False                       
      
def droit_du_cavalier(case_depart, case_arrive):   #définit les droits de déplacements du cavalier (True or False)
  if snum(case_arrive) == snum(case_depart)+8+1 or snum(case_arrive)-8-1 == snum(case_depart)+8-1 or snum(case_arrive)-8+1  == snum(case_arrive)+2+1 or snum(case_arrive)-2+1 == snum(case_arrive)+2-1 or snum(case_arrive)-2-1:
    return True
  else :
    return False                       

    
jeu()

#En traveaux..

"""
def droit_du_fous(case_depart, case_arrive):
  i=7
  a=0
  while a < 35:
  if snum(case_arrive) == snum(case_depart)+7+i or snum(case_arrive) == snum(case_arrive)-7-i :
    return True
  else :
    return False   
    a+=7
  
 def droit_du_fous(case_depart, case_arrive): 
  if or snum(case_arrive) == snum(case_depart)+9 or snum(case_arrive) == snum(case_depart)-9 :
     return True
  else :
    return False
"""