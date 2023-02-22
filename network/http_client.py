import http.client
con = http.client.HTTPConnection("www.example.com")
con.request("GET", "/")
resp = con.getresponse()
print("Status: {}, Status Phrase: {}".format(resp.status,resp.reason))
con.close()
