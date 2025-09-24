const responseBtn = document.querySelector('button#response-btn')
responseBtn.addEventListener("click", expand);

const responseEntry = document.querySelector('#response-input');

const submitBtn = document.querySelector('button#submit-btn');
submitBtn.addEventListener("click", submitResponse);

function expand(event){
    // when i click the button, remove class = 'hide'
    console.log("expanding");
    
    responseEntry.classList.remove('hide');
    responseBtn.classList.add('hide');
}

function submitResponse(event){
    console.log("submitting");
    responseEntry.classList.add('hide');
    responseBtn.classList.remove('hide')
}