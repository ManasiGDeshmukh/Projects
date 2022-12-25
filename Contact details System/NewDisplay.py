import json

class Contact:
    contactList=[]
    def __init__(self,Data):
        file = open(Data, "r")
        for line in file.readlines():
            contact = json.loads(str(line))
            self.contactList.append(contact)
        self.Data=Data
    def Details(self,s):
        newList=[]
        for i in self.contactList:
            if(s in i["Name"]):
                newList.append(i)
        return newList
    def refactor(self):
        file=open(self.Data,"w")
        if(self.contactList!=[]):
            file.write(json.dumps(self.contactList[0])+"\n")
            with open(self.Data,"a") as file:
                for i in self.contactList[1:]:
                    file.write(json.dumps(i)+"\n")
        file.close()

    def delete(self,dets):
        self.contactList.remove(dets)
        self.refactor()
    def update(self,dets,detsUpdate):
        self.contactList.remove(dets)
        self.contactList.append(detsUpdate)
        self.refactor()
    def append(self,dets):
        self.contactList.append(dets)
        self.refactor()

    def retNameList(self):
        names=[i["Name"] for i in self.contactList]
        return names
def doNoth():
    pass
