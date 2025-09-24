const acceptBtns = document.querySelectorAll("button.accept");

for (let index = 0; index < acceptBtns.length; index++){
    const element = acceptBtns[index];
    element.addEventListener('click', acceptOrder);
}

const denyBtns = document.querySelectorAll("button.deny");

for (let index = 0; index < denyBtns.length; index++){
    const element = denyBtns[index];
    element.addEventListener('click', denyOrder);
}


function acceptOrder(event){
    const btn = event.currentTarget;
    const order = btn.parentElement.parentElement.parentElement;
    order.style.display = 'none';
}

function denyOrder(event){
    const btn = event.currentTarget;
    const order = btn.parentElement.parentElement.parentElement;
    order.style.display='none';
}