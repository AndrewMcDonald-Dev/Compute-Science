1. _JavaScript is known as a loosely typed language. What does that mean?_

   > Variable types are not strictly defined allowing for greater flexibility.

2. _The `text` control’s `placeholder` and `value` attributes both provide text that pre-populates the text control’s box. What is the difference in terms of what happens when the user starts to enter new text into the box?_

   > For an `input` element, the `value` attribute will populate the box with real text whereas the `placeholder` attribute places tool-tip hint in the box that is only shown when the box is empty and is not actual text.

3. _Every HTML element has `innerHTML` and `outeHTML` properties. What is the difference between the two?_

   > `outerHTML` is the content adn the tags as a whole while `innerHTML` is just the content between the opening and closing tags.

4. _Suppose a variable `x` holds "dog". After the following code gets executed, what does `x` hold?_

   _`x += "fish";`_

   > `"dogfish"`

5. _When the following code executes, what message does the dialog box display?_

   ```js
   var month = 'January';
   if (month.toLowerCase() == 'january') {
     alert('It is ' + month + '!');
   }
   ```

   > `It is January!`

6. _After the gollowing code executes, what does `x` hold?_

   ```js
   var animal = 'porcupine';
   var x = animal.lastIndexOf('p', 3);
   ```

   > `0`

7. _What is the purpose of the `z-index` CSS property?_

   > `z-index` specifies the stack order relative to other elements.

8. _When executed, what does the following code gradment display?_

   ```js
   var x = 5;
   var y = x++;
   alert("x = " + ++x ", y = " + y);
   ```

   > `x = 7, y =5`
