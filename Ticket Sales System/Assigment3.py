import sys
file1 = open(sys.argv[1],'r')
file2 = open('output.txt', 'w')
lines = file1.readlines()
alfabe = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
data = {}
liste = []
kategoriler = []

#In the create function, I separate the category name and create a list of coordinates belonging to that name and a key to the dictionary with that name and indicate that the seats are empty.
def create(g):
    ax = []
    dd = g[15::]
    oluştur = dd.split(" ")
    if not oluştur[0] in kategoriler:
        kategoriler.append(oluştur[0])
        düzelmişhal = oluştur[1]
        adetalan = düzelmişhal.split("x")
        seats = int(adetalan[0])*int(adetalan[1])
        xx = oluştur[0]
        if int(adetalan[0])>26:
            print("hata")
        else:
            for i in range(0,int(adetalan[1])):
                for ı in alfabe[0:int(adetalan[0])]:
                    ax.append(str(ı)+str(i))
            liste.append(ax)
            data[xx] = [['X' for i in range(int(adetalan[0]))] for j in range(int(adetalan[1]))]
            yazı = "The category "+"\'" +oluştur[0]+"\'" +" having " + str(seats) + " seats has been created"
            file2.write(yazı)
            file2.write("\n")
            print(yazı)
    else:
        yazı = "Warning: Cannot create the category for the second time. The stadium has already "+oluştur[0]
        file2.write(yazı)
        file2.write("\n")
        print(yazı)
#In the sell function, I pull the category name and keep the list containing the seat coordinates in parallel with the number in the categories list of that category on the computer, because if an unregistered seat is wanted to be sold, I will distinguish whether the seat is in the category or not.
#I evaluate it with a "for" loop by listing it over ", if an expression with "-" comes in, the computer detects it and applies the unique "if" function to it, so that it does not interfere with the category, I looped the values I listed from the 4th value.
#and if that seat is not for sale, it is for sale.
def sell(g):
    uw = []
    veriler = g[11:-1]
    oluştur = veriler.split(" ")
    for j in range(len(kategoriler)):
        if kategoriler[j] == oluştur[2]:
            for i in oluştur[3::]:
                if oluştur[1] == "student":
                    if "-" in i:
                        xab = i[1::].split("-")
                        for ı in range(len(alfabe)):
                            if alfabe[ı] == i[0]:
                                if int(xab[1]) > len(data[oluştur[2]][0]):
                                    asd ="Error: The category "+"\'"+oluştur[2]+"\'"+" has less column than the specified index "+str(i)+"!"
                                    file2.write(asd)
                                    file2.write("\n")
                                else:
                                    fgh = True
                                    for a in range(int(xab[0]), int(xab[1]) + 1):
                                        uw.append(data[oluştur[2]][int(ı)][a])
                                        geridönmeyit = "Warning: The seats " + str(i) + " cannot be sold to " + oluştur[2] + " due some of them have already been sold"
                                        if "S" in uw:
                                            print(geridönmeyit)
                                            file2.write(geridönmeyit)
                                            file2.write("\n")
                                            fgh = False
                                            break
                                            return
                                        if "T" in uw:
                                            print(geridönmeyit)
                                            file2.write(geridönmeyit)
                                            file2.write("\n")
                                            fgh = False
                                            break
                                            return
                                        if "F" in uw:
                                            print(geridönmeyit)
                                            file2.write(geridönmeyit)
                                            file2.write("\n")
                                            fgh = False
                                            break
                                            return
                                        else:
                                            data[oluştur[2]][int(ı)][a] = "S"
                                    if not "X" in data[oluştur[2]][int(ı)][a]:
                                        if fgh == True:
                                            geridönüt = "Success: " + oluştur[0] + " has bought " + str(i) + " at " + oluştur[2]
                                            print(geridönüt)
                                            file2.write(geridönüt)
                                            file2.write("\n")
                    else:
                        if i in liste[j]:
                            for ı in range(len(alfabe)):
                                if alfabe[ı] == i[0]:
                                    if data[oluştur[2]][int(ı)][int(i[1::])] == "X":
                                        data[oluştur[2]][int(ı)][int(i[1::])] = "S"
                                        cad = "Success: "+oluştur[0]+" has bought "+str(i)+" at "+oluştur[2]
                                        print(cad)
                                        file2.write(cad)
                                        file2.write("\n")
                                    else:
                                        cvd = "Warning: The seats "+str(i)+" cannot be sold to "+str(oluştur[2])+" due some of them have already been sold "
                                        print(cvd)
                                        file2.write(cvd)
                                        file2.write("\n")
                if oluştur[1] == "full":
                    if "-" in i:
                        xab = i[1::].split("-")
                        for ı in range(len(alfabe)):
                            if alfabe[ı] == i[0]:
                                if int(xab[1]) > len(data[oluştur[2]][0]):
                                    asd ="Error: The category "+"\'"+oluştur[2]+"\'"+" has less column than the specified index "+str(i)+"!"
                                    file2.write(asd)
                                    file2.write("\n")
                                else:
                                    fgh = True
                                    for a in range(int(xab[0]), int(xab[1]) + 1):
                                        uw.append(data[oluştur[2]][int(ı)][a])
                                        geridönmeyit = "Warning: The seats " + str(i) + " cannot be sold to " + oluştur[2] + " due some of them have already been sold"
                                        if "S" in uw:
                                            print(geridönmeyit)
                                            file2.write(geridönmeyit)
                                            file2.write("\n")
                                            fgh = False
                                            break
                                            return
                                        if "T" in uw:
                                            print(geridönmeyit)
                                            file2.write(geridönmeyit)
                                            file2.write("\n")
                                            fgh = False
                                            break
                                            return
                                        if "F" in uw:
                                            print(geridönmeyit)
                                            file2.write(geridönmeyit)
                                            file2.write("\n")
                                            fgh = False
                                            break
                                            return
                                        else:
                                            data[oluştur[2]][int(ı)][a] = "F"
                                    if not "X" in data[oluştur[2]][int(ı)][a]:
                                        if fgh == True:
                                            geridönüt = "Success: " + oluştur[0] + " has bought " + str(i) + " at " + oluştur[2]
                                            print(geridönüt)
                                            file2.write(geridönüt)
                                            file2.write("\n")
                    else:
                        if i in liste[j]:
                            for ı in range(len(alfabe)):
                                if alfabe[ı] == i[0]:
                                    if data[oluştur[2]][int(ı)][int(i[1::])] == "X":
                                        data[oluştur[2]][int(ı)][int(i[1::])] = "F"
                                        cad = "Success: "+oluştur[0]+" has bought "+str(i)+" at "+oluştur[2]
                                        print(cad)
                                        file2.write(cad)
                                        file2.write("\n")
                                    else:
                                        cvd = "Warning: The seats "+str(i)+" cannot be sold to "+str(oluştur[2])+" due some of them have already been sold "
                                        print(cvd)
                                        file2.write(cvd)
                                        file2.write("\n")
                if oluştur[1] == "seasons":
                    if "-" in i:
                        xab = i[1::].split("-")
                        for ı in range(len(alfabe)):
                            if alfabe[ı] == i[0]:
                                print(len(data[oluştur[2]][0]))
                                if int(xab[1]) > len(data[oluştur[2]][0]):
                                    asd ="Error: The category "+"\'"+oluştur[2]+"\'"+" has less column than the specified index "+str(i)+"!"
                                    file2.write(asd)
                                    file2.write("\n")
                                else:
                                    fgh = True
                                    for a in range(int(xab[0]), int(xab[1]) + 1):
                                        uw.append(data[oluştur[2]][int(ı)][a])
                                        geridönmeyit = "Warning: The seats " + str(i) + " cannot be sold to " + oluştur[2] + " due some of them have already been sold"
                                        if "S" in uw:
                                            print(geridönmeyit)
                                            file2.write(geridönmeyit)
                                            file2.write("\n")
                                            fgh = False
                                            break
                                            return
                                        if "T" in uw:
                                            print(geridönmeyit)
                                            file2.write(geridönmeyit)
                                            file2.write("\n")
                                            fgh = False
                                            break
                                            return
                                        if "F" in uw:
                                            print(geridönmeyit)
                                            file2.write(geridönmeyit)
                                            file2.write("\n")
                                            fgh = False
                                            break
                                            return
                                        else:
                                            data[oluştur[2]][int(ı)][a] = "T"
                                    if not "X" in data[oluştur[2]][int(ı)][a]:
                                        if fgh == True:
                                            geridönüt = "Success: " + oluştur[0] + " has bought " + str(i) + " at " + oluştur[2]
                                            print(geridönüt)
                                            file2.write(geridönüt)
                                            file2.write("\n")
                    else:
                        if i in liste[j]:
                            for ı in range(len(alfabe)):
                                if alfabe[ı] == i[0]:
                                    if data[oluştur[2]][int(ı)][int(i[1::])] == "X":
                                        data[oluştur[2]][int(ı)][int(i[1::])] = "T"
                                        cad = "Success: "+oluştur[0]+" has bought "+str(i)+" at "+oluştur[2]
                                        print(cad)
                                        file2.write(cad)
                                        file2.write("\n")
                                    else:
                                        cvd = "Warning: The seats "+str(i)+" cannot be sold to "+str(oluştur[2])+" due some of them have already been sold "
                                        print(cvd)
                                        file2.write(cvd)
                                        file2.write("\n")


#The cancel function lists the input in a similar way and detects the sold seats with the help of "dict" and cancels them depending on the input. The things I pay attention to here are to understand whether the input is in the category or not, I used 2 specific "ifs".
def cancel(g):
    ac = g[13::].split(" ")
    if int(ac[1][1::])> len(data[ac[0]][0]):
        ad = "Error: The category "+str(ac[0])+" has less column than the specified index "+str(ac[1][:-1])+"!"
        file2.write(ad)
        file2.write("\n")
        print(ad)
    else:
        for ı in range(len(alfabe)):
            if alfabe[ı] == ac[1][0]:
                if not data[ac[0]][int(ı)][int(ac[1][1::])] == "X":
                    data[ac[0]][int(ı)][int(ac[1][1::])] ="X"
                    av = "Success: The seat " +str(ac[1][:-1])+" at " +str(ac[0])+" has been canceled and now ready to sell again"
                    print(av)
                    file2.write(av)
                    file2.write("\n")
                else:
                    ag = "Error: The seat "+ str(ac[1][:-1]) +" at "+"\'"+str(ac[0])+ "\' has already been free! Nothing to cancel"
                    print(ag)
                    file2.write(ag)
                    file2.write("\n")
#The balance function detects the sold places in the sell function over "dict" and generates income.

def balance(g):
    student = 0
    full = 0
    seasons = 0
    for i in data[g[8:-1]]:
        for ı in i:
            if ı == "S":
               student+=1
            if ı == "F":
                full+=1
            if ı == "T":
                seasons+=1
    cvd = "Category report of " + str(g[8::])+"\n"+"--------------------------------\n"+"Sum of students = "+ str(student) +", Sum of full pay = "+str(full)+", Sum of season ticket="+str(seasons)+", and"+"\n"+"Revenues = "+str(10*student+20*full+250*seasons)+" Dollars"
    print(cvd)
    print("\n")
    file2.write(cvd)
    file2.write("\n")
#the table function writes the values in parallel with the letter via "dict"
def tablo(g):
    for i in range(len(data[g[13:-1]]),0,-1):
        print(alfabe[i-1],end="  ")
        file2.write(alfabe[i-1]+"  ")
        for ı in range(len(data[g[13:-1]][i-1])):
            az = str(data[g[13:-1]][i-1][ı])+"  "
            print(az,end="")
            file2.write(az)
        print("\n")
        file2.write("\n")
    print("   ",end="")
    file2.write("   ")
    for i in range(len(data[g[13:-1]][0])):
        cf = 3 - len(str(i))
        print(str(i)+" "*cf,end="")
        file2.write(str(i)+ " "*cf)
    print("\n")
    file2.write("\n")



for g in lines:
    if g.startswith("CREATECATEGORY"):
        create(g)
    if g.startswith("SELLTICKET"):
        sell(g)
    if g.startswith("BALANCE"):
        balance(g)
    if g.startswith("CANCELTICKET"):
        cancel(g)
    if g.startswith("SHOWCATEGORY"):
        tablo(g)



#  Bedirhan Gençaslan