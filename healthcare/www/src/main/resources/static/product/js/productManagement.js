let productImageRegBtn = document.querySelector('#productImageRegBtn'), // input file 대신 보여질 버튼
    hiddenInputRegFile = document.querySelector('#hiddenInputRegFile'), // 실제 input 객체(hidden 상태)
    productImageModBtn = document.querySelector('#productImageModBtn'), // input file 대신 보여질 버튼
    hiddenInputModFile = document.querySelector('#hiddenInputModFile'); // 실제 input 객체(hidden 상태)

productImageRegBtn.addEventListener('click', () => { // 상품등록 이미지 첨부 이벤트
    hiddenInputRegFile.click();
});

productImageModBtn.addEventListener('click', () => { // 상품수정 이미지 첨부 이벤트
    hiddenInputModFile.click();
});

let mainContainer = document.querySelector('.product-management-container');
let select = document.querySelectorAll('.select-wrap'); // 셀렉트div 객체
let arrow; // 셀렉트 화살표

select.forEach(e => e.addEventListener('click', () =>{
    arrow = e.querySelector('.fa-caret-down');
   if(arrow.classList.contains('arrowUp')){
       arrow.classList.replace('arrowUp', 'arrowDown');
   } else {
       arrow.classList.replace('arrowDown', 'arrowUp');
   }
   console.log(arrow);
}));

mainContainer.addEventListener('click', (e) =>{
    if(arrow){
        console.log(arrow);
        if(!e.target.classList.contains('select') && arrow.classList.contains('arrowUp')){
            arrow.style.transform = 'rotate(0deg)';
            arrow.classList.remove('arrowUp');
            arrow.classList.add('arrowDown');
            console.log(arrow);
        }
    }

});


let openModal = document.querySelector('#searchModalOpen'), // 검색 모달창 오픈
    modalBack = document.querySelector('.search-modal-back'), // 모달 백그라운드
    modal = document.querySelector('.search-modal'), // 모달창
    closeBtn = document.querySelector('#searchModalClose'); // x버튼

    openModal.addEventListener('click', () => {
        modalBack.classList.add('search-modal-back-on');
    });

    closeBtn.addEventListener('click', () => {
        console.log("close");
        modalBack.classList.remove('search-modal-back-on');
    });