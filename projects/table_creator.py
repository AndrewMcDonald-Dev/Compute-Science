# How to use
# Delete all content in output_html (maybe it would overwrite content already there unsure)
# Run code with the db running.

import mysql.connector
import re

conn = mysql.connector.connect(
    host="localhost", user="root", password="", database="map_newpaltz")

cursor = conn.cursor(buffered=True)

# create New table if not present
init_query = (
    "CREATE TABLE IF NOT EXISTS a_items(`id` INT(11) NOT NULL,`title` VARCHAR(255) NOT NULL,`coordinates` POINT NOT NULL, PRIMARY KEY (`id`))")
cursor.execute(init_query)

# pull relevant data from table
query = ("SELECT `id`, `title`, `fulltext` FROM jos_content;")
cursor.execute(query)
if cursor != None:
    for (id, title, fulltext) in cursor:
        # Parse fulltext for latitude and logitude
        s = re.search(r"center=\d\d.\d+,-\d\d.\d+", fulltext)
        if s != None:
            (lat, lng) = s.group().replace('center=', '').split(',')
            # Input id, title, and coords into new table
            insert_query = (
                f"INSERT INTO a_items(`id`, `title`, `coordinates`) VALUES({id},\"{title}\",POINT({lat}, {lng}))")
            cursor.execute(insert_query)


cursor.close()
conn.close()


# ! No idea if this works but I do know the person who decided on python version control should be hung for their crimes.
