dataIn = open("data.txt", "r")
dataOut = open("data-out.txt", "w+")
text = dataIn.readlines()
for i in range(len(text)):
    dat = '%-4d %s' % (i+1, text[i])
    dataOut.write(dat)

dataIn.close()
dataOut.close()