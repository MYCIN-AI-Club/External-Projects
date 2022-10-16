usr=input("Enter username:")
i=0
a="kiet"
while(i<3):
  pss=(input("password:"))
  if pss==a:
    print("welcome",usr) 
    print("Following are the tasks you can access")
    print("1.Barcode Generator\n2.Convert text to speech\n3.Extracrt text from pdf\n4.Create your audio book\n5.Download You tube vedio")
    optn=int(input("Enter the digit to access the particular task"))
    if optn==1:
      import barcode
      from barcode.writer import ImageWriter
      num=int(input("Enter 1 to make a barcode of a 12 digit number\nEnter 2 to make a barcode of text\n"))
      if num==1:
        data=input("Enter 12 digit code:")
        a=barcode.get_barcode_class("EAN13")
        b=a(data,writer=ImageWriter())
        c=b.save("barcode")
        break
      elif num==2:
        data=input("Enter your text:")
        a=barcode.get_barcode_class("code128")
        b=a(data,writer=ImageWriter())
        c=b.save("barcode")
        break
      else:
        print("Wrong attempt\nPlease select from 1 or 2")
        break

    elif optn==2: 
      from gtts import gTTS 
      from IPython.display import Audio 
      text=input("Enter Your Text now:")
      tts = gTTS(text) 
      tts.save('1.wav')
      break
    elif optn==3:
      import PyPDF2
      print("Please add the extension '.pdf' after your file name")
      user_input = input('Enter file name: ')
      try:
          PDF_File = open(user_input, 'rb')
          PDF_Reader = PyPDF2.PdfFileReader(PDF_File)
          Text = PDF_Reader.getPage(0).extractText()
          print("Your text is here-")
          print(Text)
      except FileNotFoundError:
        print('Not found ')
      break
    elif optn==4:
      import PyPDF2
      from gtts import gTTS 
      from IPython.display import Audio 
      print("Please add the extension '.pdf' after your file name")
      user_input = input('Enter file name: ')
      try:
        PDF_File = open(user_input, 'rb')
        PDF_Reader = PyPDF2.PdfFileReader(PDF_File)
        Text = PDF_Reader.getPage(0).extractText()
        tts = gTTS(Text) 
        gtts.save('1.wav')
      except FileNotFoundError:
        print('Not found')
      break
    elif optn==5:
      from pytube import YouTube
      link = input("Enter the Video Link You wants to download: ")
      Download_Video_link = YouTube(link)
      Download_Video_link.streams.get_highest_resolution().download()
      print("Download Done")
      break
    else:
      print("Wrong Attempt\nPlease enter the digit from the following list")
  else:
    print("wrong ssword")
    i=i+1
  if i==3:
    print("You have reached maximum login limit")
    print("Please try logging in again")
