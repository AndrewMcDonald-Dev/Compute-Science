# How to use
# Delete all content in output_html (maybe it would overwrite content already there unsure)
# Run code with the db running.

import mysql.connector

conn = mysql.connector.connect(
    host="localhost", user="p_s23_06", password="j4bve2", database="p_s23_06_db")

cursor = conn.cursor()

query = ("SELECT 'title', 'introtext', 'fulltext' FROM jos_content;")

cursor.execute(query)

for (title, introtext, fulltext) in cursor:
    # Used for the name of the file so it can be used in a url
    simple_title = title.lower().replace(" ", "-")

    # Open new file in write mode
    f = open(f"/var/www/projects/s23-06/html/files/output_html/{simple_title}.html", "w")

    # Write in html content and db content
    f.write(
        f'<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge"><meta name="viewport" content="width=device-width, initial-scale=1.0"><link rel="stylesheet" href="style.css"><title>{title}</title></head><body>{introtext}{fulltext}</body></html>')

    # close file and do the next one
    f.close()

cursor.close()
conn.close()
