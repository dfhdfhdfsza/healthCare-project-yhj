let productImageRegBtn = document.querySelector('#productImageRegBtn'), // input file 대신 보여질 버튼
    hiddenInputRegFile = document.querySelector('#hiddenInputRegFile'), // 실제 input 객체(hidden 상태)
    productImageModBtn = document.querySelector('#productImageModBtn'), // input file 대신 보여질 버튼
    hiddenInputModFile = document.querySelector('#hiddenInputModFile'); // 실제 input 객체(hidden 상태)

productImageRegBtn.addEventListener('click', () => {
    hiddenInputRegFile.click();
});

productImageModBtn.addEventListener('click', () => {
    hiddenInputModFile.click();
});


let select = document.querySelector('.select'),
    arrow = document.querySelector('.fa-caret-down');

select.addEventListener('click', () => {
    console.log("arrow");
    console.log(arrow.id);
    if(arrow.id == 'arrowDown'){
        arrow.id = 'arrowUp';
    } else {
        arrow.id = 'arrowDown';
    }
})