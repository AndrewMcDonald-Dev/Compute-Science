document.getElementById('ready').addEventListener('click', () => {
    const ids = [
        'vanilla',
        'chocolate',
        'coffee',
        "Barg's",
        'A&W',
        "Dad's",
        'IBC',
    ];
    const ans = [];
    let highest = 0;
    let lowest = 6;
    ids.forEach(function (element, index) {
        if (document.getElementById(element).checked) {
            if (index > highest) highest = index;
            if (index < lowest) lowest = index;
            ans.push(element);
        }
    });
    if (!(highest > 2) || !(lowest < 3)) {
        alert(
            'You must select at least one ice cream flavor, and you must select one root beer brand.'
        );
        throw '';
    }

    let out = `Recipe:\nIn a glass mug, add one scoop each of these ice creams - ${ans
        .slice(0, ans.length - 1)
        .join(', ')}. Fill up the mug with ${ans.slice(
        ans.length - 1,
        ans.length
    )} root beer. Enjoy!`;

    document.getElementById('output').innerHTML = out;
    document.getElementById('output').style.visibility = 'visible';
});
