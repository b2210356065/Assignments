import sys
try:
    f1=open(sys.argv[1],"r")
    f2=open(sys.argv[2],"r")
    f3= open("Battleship.out.txt","w")
    f4=open(sys.argv[3],"r")
    f5=open(sys.argv[4],"r")
    f6=open("OptionalPlayer1.txt","r")
    f7=open("OptionalPlayer2.txt","r")
    if sys.argv[1]!="Player1.txt":
        print("IOError: input file(s) Player1.txt is/are not reachable.")
        f3.write("IOError: input file(s) Player1.txt is/are not reachable."+"\n")
        raise IOError
    if sys.argv[2]!="Player2.txt":
        print("IOError: input file(s) Player2.txt is/are not reachable.")
        f3.write("IOError: input file(s) Player2.txt is/are not reachable."+"\n")
        raise IOError
    if sys.argv[3]!="Player1.in":
        print("IOError: input file(s) Player1.in is/are not reachable.")
        f3.write("IOError: input file(s) Player1.in is/are not reachable."+"\n")
        raise IOError
    if sys.argv[4]!="Player2.in":
        print("IOError: input file(s) Player2.in is/are not reachable.")
        f3.write("IOError: input file(s) Player2.in is/are not reachable."+"\n")
        raise IOError
    lines7= f7.readlines()
    lines6= f6.readlines()
    lines = f1.readlines()
    lines2 = f2.readlines()
    lines4 = f4.readlines()
    lines5 = f5.readlines()
    data1 = {}
    data2 = {}
    data5 = {}
    data4 = {}
    karaliste1={}
    karaliste2={}
    alfabe = []
    a=0
    s=0
    list2 = []
    list3 = []
    list4 = []
    list5 = []
    list6 = []
    for g in lines:
        a+=1
        list1 = []
        g = g.strip("\n")
        for c in g.split(";"):
            s += 1
            list1.append("-")
            if s%10==0:
                ff = str((s-1) // 10+1) + "," + str(10)
            else:
                ff = str((s-1) // 10+1) + "," + str(s % 10)
            if c == "C":
                list2.append(ff)
            if c == "B":
                list3.append(ff)
            if c == "P":
                list4.append(ff)
            if c == "S":
                list5.append(ff)
            if c == "D":
                list6.append(ff)

        data1[a]=list1
    karaliste1["C"]=list2
    karaliste1["S"]=list5
    karaliste1["D"]=list6

    a=0
    s=0
    list2 = []
    list3 = []
    list4 = []
    list5 = []
    list6 = []
    for g in lines2:
        a += 1
        list1 = []
        g = g.strip("\n")
        for c in g.split(";"):
            s += 1
            list1.append("-")
            if s % 10 == 0:
                ff = str((s - 1) // 10 + 1) + "," + str(10)
            else:
                ff = str((s - 1) // 10 + 1) + "," + str(s % 10)
            if c == "C":
                list2.append(ff)
            if c == "B":
                list3.append(ff)
            if c == "P":
                list4.append(ff)
            if c == "S":
                list5.append(ff)
            if c == "D":
                list6.append(ff)

        data2[a] = list1
    karaliste2["C"] = list2
    karaliste2["S"] = list5
    karaliste2["D"] = list6

    for g in lines4:
        s=1
        list = []
        a =g[:-1].split(";")
        for i in a:
            list2=[]
            list.append(i.split(",")[0]+","+str(ord(i[-1])-ord("A")+1))
        data4=list
        s+=1

    for g in lines5:
        s=1
        list = []
        a =g[:-1].split(";")
        for i in a:
            list2=[]
            list.append(i.split(",")[0]+","+str(ord(i[-1])-ord("A")+1))
        data5=list
        s+=1
    def oyna4(c,d,o,ü):
        falag=True
        d=d-1
        for j in karaliste2:
            if str(c)+","+str(d+1) in karaliste2[j]:
                karaliste2[j].remove(str(c) + "," + str(d + 1))
                if data2[c][d]=="-":
                    data2[c][d]="X"
            elif j == "P4":
                if c <= 10:
                    if d <=10:
                        if data2[c][d] == "-":
                            data2[c][d] = "O"
                    else:
                        raise AssertionError
                else:
                    raise AssertionError

        tabloyap(c, d,o,ü)

    def oyna5(c,d,o,ü):
        d=d-1
        for j in karaliste1:
            if str(c)+","+str(d+1) in karaliste1[j]:
                karaliste1[j].remove(str(c)+","+str(d+1))
                if data1[c][d]=="-":
                    data1[c][d]="X"
            elif j == "P4":
                if c <= 10:
                    if d <=10:
                        if data1[c][d] == "-":
                            data1[c][d] = "O"
                    else:
                        raise AssertionError
                else:
                    raise AssertionError

        tabloyap2(c,d,o,ü)


    def gur(a):
        sas=0
        for i in a:
            if i[0] == "B" and a[i] == []:
                sas+=1
        return sas
    def dur(a):
        sas=0
        for i in a:
            if i[0] == "P" and a[i] == []:
                sas+=1
        return sas
    def vaycanına(i):
        sas = []
        cas=[]
        for g in i:
            d=g.split(",")
            a =int(d[0][3::])
            b= ord(d[1][0])-ord("A")+1
            if g[0]=="B":
                if g[-4]=="f":
                    das=[]
                    for ı in range(4):
                        das.append(str(a)+","+str(int(b)-ı))
                    sas.append(das)
                if g[-4] == "h":
                    das=[]
                    for ı in range(4):
                        das.append(str(a)+","+str(int(b)+ı))
                    sas.append(das)
                if g[-4] == "w":
                    das=[]
                    for ı in range(0,4):
                        das.append(str(a+ı)+","+str(int(b)))
                    sas.append(das)
                if g[-4] == "u":
                    das=[]
                    for ı in range(4):
                        das.append(str(a-ı)+","+str(int(b)))
                    sas.append(das)
                karaliste1[g[0:2]]=das

            elif g[0]=="P":
                if g[-4]=="f":
                    for ı in range(2):
                        das.append(str(a)+","+str(int(b)-ı))
                    cas.append(das)
                if g[-4] == "h":
                    das=[]
                    for ı in range(2):
                        das.append(str(a)+","+str(int(b)+ı))
                    cas.append(das)
                if g[-4] == "w":
                    das=[]
                    for ı in range(2):
                        das.append(str(a+ı)+","+str(int(b)))
                    cas.append(das)
                if g[-4] == "u":
                    das=[]
                    for ı in range(2):
                        das.append(str(a-ı)+","+str(int(b)))
                    cas.append(das)
                karaliste1[g[0:2]]=das
    vaycanına(lines6)
    def vaycanına2(i):
        sas = []
        cas=[]
        for g in i:
            d=g.split(",")
            a =int(d[0][3::])
            b= ord(d[1][0])-ord("A")+1
            if g[0]=="B":
                if g[-4]=="f":
                    das=[]
                    for ı in range(4):
                        das.append(str(a)+","+str(int(b)-ı))
                    sas.append(das)
                if g[-4] == "h":
                    das=[]
                    for ı in range(4):
                        das.append(str(a)+","+str(int(b)+ı))
                    sas.append(das)
                if g[-4] == "w":
                    das=[]
                    for ı in range(0,4):
                        das.append(str(a+ı)+","+str(int(b)))
                    sas.append(das)
                if g[-4] == "u":
                    das=[]
                    for ı in range(4):
                        das.append(str(a-ı)+","+str(int(b)))
                    sas.append(das)
                karaliste2[g[0:2]]=das

            elif g[0]=="P":
                if g[-4]=="f":
                    for ı in range(2):
                        das.append(str(a)+","+str(int(b)-ı))
                    cas.append(das)
                if g[-4] == "h":
                    das=[]
                    for ı in range(2):
                        das.append(str(a)+","+str(int(b)+ı))
                    cas.append(das)
                if g[-4] == "w":
                    das=[]
                    for ı in range(2):
                        das.append(str(a+ı)+","+str(int(b)))
                    cas.append(das)
                if g[-4] == "u":
                    das=[]
                    for ı in range(2):
                        das.append(str(a-ı)+","+str(int(b)))
                    cas.append(das)
                karaliste2[g[0:2]]=das
    vaycanına2(lines7)
    def tabloyap(i,ı,o,ü):
        print("Player1\'s Move"+"\n")
        f3.write("Player1\'s Move"+"\n"+"\n")
        print("Round:"+str(10*o+ü+1),end="                                      ")
        f3.write("Round:"+str(10*o+ü)+"                                      ")
        print("Grid Size 10x10"+"\n")
        f3.write("Grid Size 10x10"+"\n"+"\n")
        print("Player1\'s Hidden board",end="       ")
        f3.write("Player1\'s Hidden board"+"       ")
        print("Player2\'s Hidden board"+"\n")
        f3.write("Player2\'s Hidden board"+"\n"+"\n")
        print("  A B C D E F G H I J",end="         ")
        f3.write("  A B C D E F G H I J"+"         ")
        print(" A B C D E F G H I J")
        f3.write("A B C D E F G H I J"+"\n")
        for i in range(1,11):
            if i ==10:
                print(str(i)+" ".join(data1[i]),"       "+str(i)+" ".join(data2[i]))
                f3.write(str(i)+" ".join(data1[i])+"       "+str(i)+" ".join(data2[i])+"\n")
            else:
                print(i,end=" ")
                print(" ".join(data1[i]),"       "+str(i)+" "+" ".join(data2[i]))
                f3.write(str(i)+" "+" ".join(data1[i])+"       "+str(i)+" "+" ".join(data2[i])+"\n")
        if len(karaliste1["C"])==0:
            print("Carrier:     "+"X",end="               ")
            f3.write("Carrier:     "+"X"+"               ")
            if len(karaliste2["C"]) == 0:
                print("Carrier:     " + "X")
                f3.write("Carrier:     "    + "X"+"\n")
            else:
                print("Carrier:     " + "-")
                f3.write("Carrier:     " + "-"+"\n")
        else:
            print("Carrier:     "+"-",end="               ")
            f3.write("Carrier:     "+"-"+"               ")
            if len(karaliste2["C"]) == 0:
                print("Carrier:     " + "X")
                f3.write("Carrier:     " + "X"+"\n")
            else:
                print("Carrier:     " + "-")
                f3.write("Carrier:     " + "-"+"\n")
        print("Battleship:  "+"X"*gur(karaliste1)+"-"*(2-gur(karaliste1)),end="              ")
        f3.write("Battleship:  "+"X"*gur(karaliste1)+"-"*(2-gur(karaliste1))+"              ")
        print("Battleship:  "+"X"*gur(karaliste2)+"-"*(2-gur(karaliste2)))
        f3.write("Battleship:  "+"X"*gur(karaliste2)+"-"*(2-gur(karaliste2))+"\n")
        if len(karaliste1["D"])==0:
            print("Destroyer:   "+"X",end="               ")
            f3.write("Destroyer:   "+"X"+"               ")
            if len(karaliste2["D"]) == 0:
                print("Destroyer:  " + "X")
                f3.write("Destroyer:   " + "X"+"\n")
            else:
                print("Destroyer:   " + "-")
                f3.write("Destroyer:   " + "-"+"\n")

        else:
            print("Destroyer:   "+"-",end="               ")
            f3.write("Destroyer:   "+"-"+"               ")
            if len(karaliste2["D"]) == 0:
                print("Destroyer:   " + "X")
                f3.write("Destroyer:    " + "X"+"\n")
            else:
                print("Destroyer:   " + "-")
                f3.write("Destroyer:   " + "-"+"\n")
        if len(karaliste1["S"])==0:
            f3.write("Submarine:   "+"X"+"               ")
            print("Submarine:   "+"X",end="               ")
            if len(karaliste2["S"]) == 0:
                print("Submarine:   " + "X")
                f3.write("Submarine:   " + "X"+"\n")
            else:
                print("Submarine:   " + "-")
                f3.write("Submarine:   " + "-"+"\n")
        else:
            print("Submarine:   "+"-",end="               ")
            f3.write("Submarine:   "+"-"+"               ")
            if len(karaliste2["S"]) == 0:
                print("Submarine:     " + "X")
                f3.write("Submarine:   " + "X"+"\n")
            else:
                print("Submarine:   " + "-")
                f3.write("Submarine:   " + "-"+"\n")
        print("Patrol Boat: "+"X"*dur(karaliste1)+"-"*(4-dur(karaliste1)),end="            ")
        f3.write("Patrol Boat: "+"X"*dur(karaliste1)+"-"*(4-dur(karaliste1))+"            ")
        print("Patrol Boat: "+"X"*dur(karaliste2)+"-"*(4-dur(karaliste2)),"\n")
        f3.write("Patrol Boat: "+"X"*dur(karaliste2)+"-"*(4-dur(karaliste2))+"\n"+"\n")
        print(str(c)+","+str(d))
        f3.write(str(c)+","+str(d)+"\n")
    def tabloyap2(i,ı,o,ü):
        print("Player1\'s Move"+"\n")
        f3.write("Player1\'s Move"+"\n"+"\n")
        print("Round:"+str(10*o+1+ü),end="                                      ")
        f3.write("Round:"+str(10*o+ü)+"                                      ")
        print("Grid Size 10x10"+"\n")
        f3.write("Grid Size 10x10"+"\n"+"\n")
        print("Player1\'s Hidden board",end="       ")
        f3.write("Player1\'s Hidden board"+"       ")
        print("Player2\'s Hidden board"+"\n")
        f3.write("Player2\'s Hidden board"+"\n"+"\n")
        print("  A B C D E F G H I J",end="         ")
        f3.write("  A B C D E F G H I J"+"         ")
        print(" A B C D E F G H I J")
        f3.write(" A B C D E F G H I J"+"\n")
        for i in range(1,11):
            if i ==10:
                print(str(i)+" ".join(data1[i]),"       "+str(i)+" ".join(data2[i]))
                f3.write(str(i)+" ".join(data1[i])+"       "+str(i)+" ".join(data2[i])+"\n")
            else:
                print(i,end=" ")
                print(" ".join(data1[i]),"       "+str(i)+" "+" ".join(data2[i]))
                f3.write(str(i)+" "+" ".join(data1[i])+"       "+str(i)+" "+" ".join(data2[i])+"\n")
        if len(karaliste1["C"])==0:
            print("Carrier:     "+"X",end="               ")
            f3.write("Carrier:     "+"X"+"               ")
            if len(karaliste2["C"]) == 0:
                print("Carrier:     " + "X")
                f3.write("Carrier:     "    + "X"+"\n")
            else:
                print("Carrier:     " + "-")
                f3.write("Carrier:     " + "-"+"\n")
        else:
            print("Carrier:     "+"-",end="               ")
            f3.write("Carrier:     "+"-"+"               ")
            if len(karaliste2["C"]) == 0:
                print("Carrier:     " + "X")
                f3.write("Carrier:     " + "X"+"\n")
            else:
                print("Carrier:     " + "-")
                f3.write("Carrier:     " + "-"+"\n")
        print("Battleship:  "+"X"*gur(karaliste1)+"-"*(2-gur(karaliste1)),end="              ")
        f3.write("Battleship:  "+"X"*gur(karaliste1)+"-"*(2-gur(karaliste1))+"              ")
        print("Battleship:  "+"X"*gur(karaliste2)+"-"*(2-gur(karaliste2)))
        f3.write("Battleship:  "+"X"*gur(karaliste2)+"-"*(2-gur(karaliste2))+"\n")
        if len(karaliste1["D"])==0:
            print("Destroyer:   "+"X",end="               ")
            f3.write("Destroyer:   "+"X"+"               ")
            if len(karaliste2["D"]) == 0:
                print("Destroyer:  " + "X")
                f3.write("Destroyer:   " + "X"+"\n")
            else:
                print("Destroyer:   " + "-")
                f3.write("Destroyer:   " + "-"+"\n")

        else:
            print("Destroyer:   "+"-",end="               ")
            f3.write("Destroyer:   "+"-"+"               ")
            if len(karaliste2["D"]) == 0:
                print("Destroyer:   " + "X")
                f3.write("Destroyer:    " + "X"+"\n")
            else:
                print("Destroyer:   " + "-")
                f3.write("Destroyer:   " + "-"+"\n")
        if len(karaliste1["S"])==0:
            f3.write("Submarine:   "+"X"+"               ")
            print("Submarine:   "+"X",end="               ")
            if len(karaliste2["S"]) == 0:
                print("Submarine:   " + "X")
                f3.write("Submarine:   " + "X"+"\n")
            else:
                print("Submarine:   " + "-")
                f3.write("Submarine:   " + "-"+"\n")
        else:
            print("Submarine:   "+"-",end="               ")
            f3.write("Submarine:   "+"-"+"               ")
            if len(karaliste2["S"]) == 0:
                print("Submarine:     " + "X")
                f3.write("Submarine:   " + "X"+"\n")
            else:
                print("Submarine:   " + "-")
                f3.write("Submarine:   " + "-"+"\n")
        print("Patrol Boat: "+"X"*dur(karaliste1)+"-"*(4-dur(karaliste1)),end="            ")
        f3.write("Patrol Boat: "+"X"*dur(karaliste1)+"-"*(4-dur(karaliste1))+"            ")
        print("Patrol Boat: "+"X"*dur(karaliste2)+"-"*(4-dur(karaliste2)),"\n")
        f3.write("Patrol Boat: "+"X"*dur(karaliste2)+"-"*(4-dur(karaliste2))+"\n"+"\n")
        print(str(c)+","+str(d))
        f3.write(str(c)+","+str(d)+"\n")
    falag = True
    for i in range(10):
        for ı in range(10):
            falag=True
            if falag==True:
                if 10*i+ı >= len(data4):
                    break
                else:
                    a = data4[10*i+ı]
                    e=a.split(",")
                    c = int(e[0])
                    d = int(e[1])
                    oyna4(c,d,i,ı)
                    b = data5[10*i+ı]
                    e=b.split(",")
                    c = int(e[0])
                    d = int(e[1])
                    oyna5(c,d,i,ı)
                    asas=0
                    for s in karaliste1:
                        if karaliste1[s]==[]:
                            asas+=1
                    afaf=0
                    for s in karaliste2:
                        if s==[]:
                            afaf+=1
                    n=0
                    a=0
                    if afaf==9:
                        n=1
                    if asas==9:
                        a=1
                    if a and n == 1:
                        print("Draw!")
                        f3.write("Draw"+"\n")
                        break

                    if n==1:
                        print("Player1 Wins!")
                        f3.write("Player1 Wins!"+"\n")
                        break
                    if a==1:
                        print("Player2 Wins!")
                        f3.write("Player2 Wins!"+"\n")
                        break
    for j in karaliste1:
        for g in karaliste1[j]:
            if karaliste1[j]!=[]:
                nn=g.split(",")
                c=int(nn[0])
                v=int(nn[1])
                data1[c][v-1]=j
                if j[0]=="B" or "P":
                    data2[c][v - 1] = j[0]
                else:
                    data2[c][v - 1] = j
    for j in karaliste2:
        for g in karaliste2[j]:
            if karaliste2[j]!=[]:
                nn=g.split(",")
                c=int(nn[0])
                v=int(nn[1])
                if j[0]=="B" or "P":
                    data2[c][v - 1] = j[0]
                else:
                    data2[c][v - 1] = j
    def tabloyap3():
        print("Final Information"+"\n")
        f3.write("Final Information"+"\n"+"\n")
        print("Grid Size 10x10"+"\n")
        f3.write("Grid Size 10x10"+"\n"+"\n")
        print("Player1\'s Hidden board",end="       ")
        f3.write("Player1\'s Hidden board"+"       ")
        print("Player2\'s Hidden board"+"\n")
        f3.write("Player2\'s Hidden board"+"\n"+"\n")
        print("  A B C D E F G H I J",end="         ")
        f3.write("  A B C D E F G H I J"+"         ")
        print(" A B C D E F G H I J")
        f3.write(" A B C D E F G H I J"+"\n")
        for i in range(1,11):
            if i ==10:
                print(str(i)+" ".join(data1[i]),"       "+str(i)+" ".join(data2[i]))
                f3.write(str(i)+" ".join(data1[i])+"       "+str(i)+" ".join(data2[i])+"\n")
            else:
                print(i,end=" ")
                print(" ".join(data1[i]),"       "+str(i)+" "+" ".join(data2[i]))
                f3.write(str(i)+" "+" ".join(data1[i])+"       "+str(i)+" "+" ".join(data2[i])+"\n")
        if len(karaliste1["C"])==0:
            print("Carrier:     "+"X",end="               ")
            f3.write("Carrier:     "+"X"+"               ")
            if len(karaliste2["C"]) == 0:
                print("Carrier:     " + "X")
                f3.write("Carrier:     "    + "X"+"\n")
            else:
                print("Carrier:     " + "-")
                f3.write("Carrier:     " + "-"+"\n")
        else:
            print("Carrier:     "+"-",end="               ")
            f3.write("Carrier:     "+"-"+"               ")
            if len(karaliste2["C"]) == 0:
                print("Carrier:     " + "X")
                f3.write("Carrier:     " + "X"+"\n")
            else:
                print("Carrier:     " + "-")
                f3.write("Carrier:     " + "-"+"\n")
        print("Battleship:  "+"X"*gur(karaliste1)+"-"*(2-gur(karaliste1)),end="              ")
        f3.write("Battleship:  "+"X"*gur(karaliste1)+"-"*(2-gur(karaliste1))+"              ")
        print("Battleship:  "+"X"*gur(karaliste2)+"-"*(2-gur(karaliste2)))
        f3.write("Battleship:  "+"X"*gur(karaliste2)+"-"*(2-gur(karaliste2))+"\n")
        if len(karaliste1["D"])==0:
            print("Destroyer:   "+"X",end="               ")
            f3.write("Destroyer:   "+"X"+"               ")
            if len(karaliste2["D"]) == 0:
                print("Destroyer:  " + "X")
                f3.write("Destroyer:   " + "X"+"\n")
            else:
                print("Destroyer:   " + "-")
                f3.write("Destroyer:   " + "-"+"\n")

        else:
            print("Destroyer:   "+"-",end="               ")
            f3.write("Destroyer:   "+"-"+"               ")
            if len(karaliste2["D"]) == 0:
                print("Destroyer:   " + "X")
                f3.write("Destroyer:    " + "X"+"\n")
            else:
                print("Destroyer:   " + "-")
                f3.write("Destroyer:   " + "-"+"\n")
        if len(karaliste1["S"])==0:
            f3.write("Submarine:   "+"X"+"               ")
            print("Submarine:   "+"X",end="               ")
            if len(karaliste2["S"]) == 0:
                print("Submarine:   " + "X")
                f3.write("Submarine:   " + "X"+"\n")
            else:
                print("Submarine:   " + "-")
                f3.write("Submarine:   " + "-"+"\n")
        else:
            print("Submarine:   "+"-",end="               ")
            f3.write("Submarine:   "+"-"+"               ")
            if len(karaliste2["S"]) == 0:
                print("Submarine:     " + "X")
                f3.write("Submarine:   " + "X"+"\n")
            else:
                print("Submarine:   " + "-")
                f3.write("Submarine:   " + "-"+"\n")
        print("Patrol Boat: "+"X"*dur(karaliste1)+"-"*(4-dur(karaliste1)),end="            ")
        f3.write("Patrol Boat: "+"X"*dur(karaliste1)+"-"*(4-dur(karaliste1))+"            ")
        print("Patrol Boat: "+"X"*dur(karaliste2)+"-"*(4-dur(karaliste2)),"\n")
        f3.write("Patrol Boat: "+"X"*dur(karaliste2)+"-"*(4-dur(karaliste2))+"\n"+"\n")
        print(str(c)+","+str(d))
        f3.write(str(c)+","+str(d)+"\n")

    tabloyap3()

except IOError:
    pass

except IndexError:
    print("IndexError: Please use correct input")
    f3.write("IndexError: Please use correct input"+"\n")
    pass
except ValueError:
    print("ValueError: Please use correct input")
    f3.write("ValueError: Please use correct input"+"\n")
    pass
except AssertionError:
    print("AssertionError: Invalid Operation.")
    f3.write("AssertionError: Invalid Operation."+"\n")
    pass
except:
    print(" kaBOOM: run for your life!")
    f3.write(" kaBOOM: run for your life!"+"\n")