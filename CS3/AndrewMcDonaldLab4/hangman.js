$(document).ready(() => {
    let count = 0;
    const maxCount = 10;
    $('.input-letter-box>input').bind({ keypress: checkInput, keyup: isEnableSubmit });
    $('.btn-submit').click(() => {
		/*Fill in the blank*/
       count++;
       submit(count, maxxCount);
    });
});

function checkInput(event) {
    let value = String.fromCharCode(event.which);
    let pattern = new RegExp(/[a-zA-Z]/i);
    let isValid = pattern.test(value);

    if (!isValid) {
        alert("Please type a letter")
    }

    return isValid;
};

function isEnableSubmit(event) {
    if (checkInput) {
        let inputArr = new Array();

        let letter1 = $('#letter1').val();
        let letter2 = $('#letter2').val();
        let letter3 = $('#letter3').val();
        let letter4 = $('#letter4').val();
        let letter5 = $('#letter5').val();

        inputArr.push(letter1, letter2, letter3, letter4, letter5);

        if(!inputArr.includes("")){
           alert("None empty value, please submit!")
        }
    }
};

function submit(count, maxCount) {

    let word = $('.word-box>p').text();

    let letter1 = $('#letter1').val();
    let letter2 = $('#letter2').val();
    let letter3 = $('#letter3').val();
    let letter4 = $('#letter4').val();
    let letter5 = $('#letter5').val();

    let res = letter1+letter2+letter3+letter4+letter5;
    

    if(count > maxCount){
        alert("Game Over");
    }

    if(res == word){
        alert("You are right!!!");
    }else{
        let wordArr = Array.from(word);
        let resArr = new Array();
        let rightLetters = "";
        let wrongLetters = "";
        resArr.push(letter1, letter2, letter3, letter4, letter5);

        for(let i = 0; i < resArr.length; i++){
            if(resArr[i] == wordArr[i]){
                rightLetters += resArr[i]+", "; 
            }else if(resArr[i] !=""){
                wrongLetters += resArr[i]+", ";
            }
        }

        if(rightLetters.length ==0 && wrongLetters.length ==0){
            alert("Please input value");
            return;
        }

        if(rightLetters.length ==0){
            alert("No Right Letters, "+"Wrong Letters are: "+wrongLetters);
            return;
        }

        if(wrongLetters.length ==0){
            alert("Right Letters are: "+rightLetters+" No Wrong Letters");
            return;
        }
        
        alert("Right Letters are: "+rightLetters+" Wrong Letters are: "+wrongLetters);
    }
}