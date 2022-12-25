import csv
import os
import time
from tkinter import *
import NewDisplay as nd
from cryptography.fernet import Fernet
def delete3():
    screen4.destroy()
def delete5(screen):
    screen.destroy()


def delete4():
    screen5.destroy()

def frontpage(username):
    global after_login_screen
    username = username + ".txt"
    after_login_screen = Toplevel(screen)
    after_login_screen.config(bg="white")
    after_login_screen.title("Logged in!")
    after_login_screen.geometry("550x450")
    Label(after_login_screen, text="Login Success").grid(row=0, column=3, padx=10, pady=10)
    global ob
    ob=nd.Contact(username)
    Search_item = Entry(after_login_screen)
    Search_item.grid(row=1, column=3, padx=10, pady=10)
    Search_item.focus_set()
    Search_button = Button(after_login_screen, text="Search")
    Search_button.config(command=lambda: FoundItem(Search_item.get(), after_login_screen))
    Search_button.grid(row=1, column=4, padx=10, pady=10)
    Button(after_login_screen, text="new Contact", width=10, height=1, command=lambda: add_contact(username)).grid(row=3, column=4, padx=10, pady=10)
    # dets = ob.Details('');
    # rw=5
    # if (dets != None):
    #     Label(after_login_screen, text='Name').grid(row=rw, column=0, padx=10, pady=10)
    #     Label(after_login_screen, text='Phone No.').grid(row=rw, column=2, padx=10, pady=10)
    #     Label(after_login_screen, text='Email').grid(row=rw, column=4, padx=10, pady=10)
    #     for i in dets:
    #         rw = rw + 1
    #         Label(after_login_screen, text=f'{i["Name"]}').grid(row=rw, column=0, padx=10, pady=10)
    #         Label(after_login_screen, text=f'{i["Phone"]}').grid(row=rw, column=2, padx=10, pady=10)
    #         Label(after_login_screen, text=f'{i["Email"]}').grid(row=rw, column=4, padx=10, pady=10)

def delop(DisplayDetails,rw,i):
    id=i
    Label(DisplayDetails, text=f'{id["Name"]}',bg="white").grid(row=rw, column=0, padx=10, pady=10)
    Label(DisplayDetails, text=f'{id["Phone"]}',bg="white").grid(row=rw, column=2, padx=10, pady=10)
    Label(DisplayDetails, text=f'{id["Email"]}',bg="white").grid(row=rw, column=4, padx=10, pady=10)
    Button(DisplayDetails, text="Delete", command=lambda: Delete_contact(id, DisplayDetails)).grid(row=rw, column=5,
                                                                                                  padx=10, pady=10)
    Button(DisplayDetails, text="Update",bg="white", command=lambda: Update_contact(id, DisplayDetails)).grid(row=rw, column=6,
                                                                                                  padx=10, pady=10)
def FoundItem(s,screen):
    dets=ob.Details(s)
    DisplayDetails=Toplevel(screen)
    DisplayDetails.config(bg="white")
    DisplayDetails.geometry("550x450")
    rw=0

    if(dets!=None):
        Label(DisplayDetails, text='Name',bg="white").grid(row=rw, column=0, padx=10, pady=10)
        Label(DisplayDetails, text='Phone No.',bg="white").grid(row=rw, column=2, padx=10, pady=10)
        Label(DisplayDetails, text='Email',bg="white").grid(row=rw, column=4, padx=10, pady=10)
        for i in dets:
            rw=rw+1
            delop(DisplayDetails,rw,i)
    else:
        Label(DisplayDetails, text="NotFound",bg="white").grid(row=0, column=0, padx=10, pady=10)
    Button(DisplayDetails, text="Back",bg="white", width=10, height=1, command=lambda: delete5(DisplayDetails)).grid(row=(rw+1), column=6, padx=10, pady=10)
def add_contact(user):
    global addScreen
    addScreen = Toplevel(screen)
    addScreen.config(bg="white")
    addScreen.title("Add New Contact")
    addScreen.geometry("550x450")
    f = open(user, "a")
    # Fields
    Label(addScreen, text="First name: ",bg="white").grid(row=0, column=0, padx=10, pady=10)
    Label(addScreen, text="Last name : ",bg="white").grid(row=1, column=0, padx=10, pady=10)
    Label(addScreen, text="Phone no. : ",bg="white").grid(row=2, column=0, padx=10, pady=10)
    Label(addScreen, text="Email : ",bg="white").grid(row=3, column=0, padx=10, pady=10)
    # vars
    global firstname
    global lastname
    global phno
    global email
    firstname = StringVar()
    lastname = StringVar()
    email = StringVar()
    phno = IntVar()
    phno.set(0)

    # Entries
    Entry(addScreen,bg="white", textvariable=firstname).grid(row=0, column=1, padx=10)
    Entry(addScreen,bg="white", textvariable=lastname).grid(row=1, column=1, padx=10)
    Entry(addScreen,bg="white", textvariable=phno).grid(row=2, column=1, padx=10)
    Entry(addScreen,bg="white", textvariable=email).grid(row=3, column=1, padx=10)

    # Button
    Button(addScreen, text="Save", command=lambda: add(firstname.get(),lastname.get(),phno.get(),email.get(),addScreen)).grid(row=4, column=1)
    f.close()


def add(firstname,lastname,PhoneNo,email,addScreen):
    enter={}
    enter["Name"]=firstname+" "+lastname
    enter["Phone"]=PhoneNo
    enter["Email"]=email
    ob.append(enter)
    Label(addScreen,bg="white", text="Contact Added Successfully").grid(row=5, column=1, padx=10,pady=10)
    time.sleep(1)
    delete5(addScreen)


def Delete_contact(dets,screen):
    ob.delete(dets)
    DeleteScreen = Toplevel(screen)
    Label(DeleteScreen,bg="white", text="Contact deleted Successfully").grid(row=0, column=0, padx=10,pady=10)
    time.sleep(1.5)
    DeleteScreen.destroy()
    delete5(screen)

def Update_contact(dets,screen):
    updateScreen = Toplevel(screen)
    updateScreen.config(bg="white")
    updateScreen.title("Update Contact")
    updateScreen.geometry("550x450")
    Label(updateScreen,bg="white", text="Enter contact Details").grid(row=0, column=0, padx=10,pady=10)
    Label(updateScreen,bg="white", text="First name: ").grid(row=1, column=0, padx=10, pady=10)
    Label(updateScreen,bg="white", text="Last name : ").grid(row=2, column=0, padx=10, pady=10)
    Label(updateScreen,bg="white", text="Phone no. : ").grid(row=3, column=0, padx=10, pady=10)
    Label(updateScreen,bg="white", text="Email : ").grid(row=4, column=0, padx=10, pady=10)
    # vars
    global firstname
    global lastname
    global phno
    global email
    firstname = StringVar()
    lastname = StringVar()
    email = StringVar()
    phno = IntVar()
    phno.set(0)

    # Entries
    Entry(updateScreen,bg="white", textvariable=firstname).grid(row=1, column=1, padx=10)
    Entry(updateScreen,bg="white", textvariable=lastname).grid(row=2, column=1, padx=10)
    Entry(updateScreen,bg="white", textvariable=phno).grid(row=3, column=1, padx=10)
    Entry(updateScreen,bg="white", textvariable=email).grid(row=4, column=1, padx=10)

    # Button
    Button(updateScreen, text="Save",bg="white",command=lambda: update(firstname.get(),lastname.get(), phno.get(), email.get(),dets,updateScreen,screen)).grid(
        row=5, column=1)


def update(firstname,lastname,PhoneNo,email,dets,screen,screen2):
    enter={}
    print("here")
    enter["Name"]=firstname+" "+lastname
    enter["Phone"]=PhoneNo
    enter["Email"]=email
    ob.update(dets,enter)
    Label(screen, text="Contact Upadated Successfully",bg="white").grid(row=0, column=0, padx=10, pady=10)
    time.sleep(1.5)
    delete5(screen)
    delete5(screen2)

def password_not_recognised():
    global screen4
    screen4 = Toplevel(screen)
    screen4.title("Success")
    screen4.geometry("150x100")
    Label(screen4, text="Password Error",bg="white").grid(row=0, column=0, padx=10,pady=10)
    Button(screen4, text="OK", command=delete3,bg="white").grid(row=1, column=0, padx=10,pady=10)


def user_not_found():
    global screen5
    screen5 = Toplevel(screen)
    screen5.title("Success")
    screen5.geometry("150x100")
    Label(screen5, text="User Not Found",bg="white").grid(row=0, column=0, padx=10,pady=10)
    Button(screen5, text="OK", command=delete4,bg="white").grid(row=1, column=0, padx=10,pady=10)


def register_user():
    print("working")
    username_info = username.get()
    password_info = password.get()
    if(os.path.exists('login_info.csv')==False):
        csv_reader = open('login_info.csv', 'w')
        csv_reader.close()
    csv_reader = csv.reader(open('login_info.csv', 'r'))
    for i in csv_reader:
        if username_info ==i[0]:
            dismsg=Toplevel(screen1)
            Label(dismsg,bg="white", text="Registration Unsuccesful: UserName is taken").pack()
            Button(dismsg,bg="white", text="OK", command=lambda :delete5(dismsg)).pack()
            return
    Pass = open("key.txt", "r")
    keyobj=Fernet(Pass.readline().encode())
    Pass.close()
    enpass=keyobj.encrypt(password_info.encode()).decode()
    file = open(username_info+".txt", "w")
    file.close()
    with open('login_info.csv', 'a') as file:
        file.write(f"{username_info},{enpass}\n")
    username_entry.delete(0, END)
    password_entry.delete(0, END)

    Label(screen1,bg="white", text="Registration Success", fg="green", font=("calibri", 11)).pack()
    time.sleep(1.5)
    screen1.destroy()


def login_verify():
    username1 = username_verify.get()
    password1 = password_verify.get()
    flag = 1
    username_entry1.delete(0, END)
    password_entry1.delete(0, END)
    Pass = open("key.txt", "r")
    keyobj = Fernet(Pass.readline().encode())
    Pass.close()
    with open('login_info.csv', 'r') as csv_file:
        csv_reader = csv.reader(csv_file)
        flag = 0
        for line in csv_reader:
            if(line!=[]):
                if line[0] == username1:
                    flag = 1
                    passcheck = keyobj.decrypt(line[1].encode()).decode()
                    if passcheck == password1:
                        frontpage(username1)
                        screen2.destroy()
                    else:
                        password_not_recognised()
        if flag == 0:
            user_not_found()


def register():
    global screen1
    screen1 = Toplevel(screen)
    screen1.config(bg="white")
    screen1.title("Register")
    screen1.geometry("550x450")

    global username
    global password
    global username_entry
    global password_entry
    username = StringVar()
    password = StringVar()

    Label(screen1, text="Please enter details below",bg="white").pack()
    Label(screen1, text="",bg="white").pack()
    Label(screen1, text="Username * ",bg="white").pack()

    username_entry = Entry(screen1, textvariable=username,bg="white")
    username_entry.pack()
    Label(screen1, text="Password * ",bg="white").pack()
    password_entry = Entry(screen1,bg="white", textvariable=password)
    password_entry.pack()
    Label(screen1, text="",bg="white").pack()
    Button(screen1,bg="white", text="Register", width=10, height=1, command=register_user).pack()


def login():
    global screen2
    screen2 = Toplevel(screen)
    screen2.config(bg="white")
    screen2.title("Login")
    screen2.geometry("550x450")
    Label(screen2, text="Please enter details below to login").pack()
    Label(screen2, text="").pack()

    global username_verify
    global password_verify

    username_verify = StringVar()
    password_verify = StringVar()
    global username_entry1
    global password_entry1

    Label(screen2, text="Username * ").pack()
    username_entry1 = Entry(screen2, textvariable=username_verify)
    username_entry1.pack()
    Label(screen2, text="").pack()
    Label(screen2, text="Password * ").pack()
    password_entry1 = Entry(screen2, textvariable=password_verify)
    password_entry1.pack()
    Label(screen2, text="").pack()
    Button(screen2, text="Login", width=10, height=1, command=login_verify).pack()


def main_screen():
    global screen
    screen = Tk()
    screen.config(bg="white")
    screen.geometry("550x450")
    screen.title("Contacts")
    Label(text="Contacts", bg="grey", width="300", height="2", font=("Calibri", 13)).pack()
    Label(text="").pack()
    Button(text="Login", height="2", width="30", command=login).pack()
    Label(text="").pack()
    Button(text="Register", height="2", width="30", command=register).pack()
    screen.mainloop()


main_screen()