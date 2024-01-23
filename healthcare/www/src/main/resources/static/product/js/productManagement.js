let productImageRegBtn = document.querySelector('#productImageRegBtn'), // input file 대신 보여질 버튼
    hiddenInputRegFile = document.querySelector('#hiddenInputRegFile'), // 실제 input 객체(hidden 상태)
    productImageModBtn = document.querySelector('#productImageModBtn'), // input file 대신 보여질 버튼
    hiddenInputModFile = document.querySelector('#hiddenInputModFile'); // 실제 input 객체(hidden 상태)

// 상품등록 이미지 첨부 이벤트
productImageRegBtn.addEventListener('click', () => { 
    hiddenInputRegFile.click();
});
// 상품수정 이미지 첨부 이벤트
productImageModBtn.addEventListener('click', () => { 
    hiddenInputModFile.click();
});

