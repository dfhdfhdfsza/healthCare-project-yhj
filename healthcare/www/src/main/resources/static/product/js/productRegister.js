let productImageBtn = document.querySelector('#productImageBtn'), // input file 대신 보여질 버튼
    hiddenInputFile = document.querySelector('#hiddenInputFile'); // 실제 input 객체(hidden 상태)

productImageBtn.addEventListener('click', () => {
    hiddenInputFile.click();
});
