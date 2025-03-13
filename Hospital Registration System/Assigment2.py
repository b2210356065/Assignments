data = []
file1 = open('doctors_aid_inputs.txt', 'r')
file2 = open('doctors_aid_outputs.txt', 'w')
lines = file1.readlines()
def okuyucu():
    file1 = open('doctors_aid_inputs.txt', 'r')
    lines = file1.readlines()
    file1.close()
    return lines
def remove(g):    
    for i in data:
        if g[7::] == i[0]:
            data.remove(i)
            cd = 'Patient ' +i[0]+ ' is removed.' + "\n"
            file2.write(cd)

    caj = "Patient " +g.split()[1]+" cannot be removed due to absence." + "\n"
    file2.write(caj)
def create(g):
    a = g[7::]
    data.append(a.split(", "))
    s = 'Patient ' + a.split(", ")[0] + ' is recorded.\n'
    file2.write(s)
def probability(g):
    for i in data:
        if g.split()[1]==i[0]:
            pay = int(i[3][0:2])/100000
            payda = pay + 1 - float(i[1])
            olasılık = (pay/payda*1000//1)/10
            cgl = "Patient "+g.split()[1]+" has a probability "+str(olasılık)+" of having breast cancer." + "\n"
            file2.write(cgl)
            return
    aksi_durum = "Probability for "+g.split()[1]+" cannot be calculated due to absence." + "\n"
    file2.write(aksi_durum)
def recommen(g):
    for i in data:
        if g.split(" ")[1] == i[0]:
            pay1 = int(i[3][0:2]) / 100000
            payda1 = pay1 + 1 - float(i[1])
            değer=(pay1 / payda1 * 1000 // 1) / 10
            if değer > float(i[5][0:4]):
                cdl = "System suggest "+i[0]+" have a treatment" + "\n"
                file2.write(cdl)
            else:
                cbl = "System suggests " + i[0] + " NOT have a treatment" + "\n"
                file2.write(cbl)
        return
    cfl = "Recommendation for "+g.split(" ")[1]+" cannot be calculated due to absence." + "\n"
    file2.write(cfl)
def tabloyap(g):
    firstline ='Patient	Diagnosis	Disease			Disease		Treatment		Treatment\n'
    secondline = 'Name	Accuracy	Name			Incidence	Name			Risk\n'
    file2.write(firstline)
    file2.write(secondline)
    for i in data:
        Name = i[0]
        Diagnosis_Accuary= i[1]
        Disease_Name = i[2]
        Disease_Incidence = i[3]
        Treatment_Name = i[4]
        Treatment_Risk = str(float(i[5][0:4])*100)+"%"

        tablo = f"{Name:<8}{Diagnosis_Accuary:<12}{Disease_Name:<16}{Disease_Incidence:<12}{Treatment_Name:<16}{Treatment_Risk}\n"
        file2.write(tablo)
def PatientSystem():
    for g in lines:
        if g.startswith("create"):
            create(g)
        if g.startswith("remove"):
            remove(g)
        if g.startswith("probability"):
            probability(g)
        if g.startswith("recommendation"):
            recommen(g)
        if g.startswith("list"):
            tabloyap(g)
PatientSystem()

#Bedirhan Gencaslan 2210356065