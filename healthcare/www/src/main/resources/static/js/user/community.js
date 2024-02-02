let xBtn = document.querySelector('.xBtn');
let registerModal = document.querySelector('.register-modal');
let openRegister = document.querySelector('.open-register');
let file = document.querySelector('.file');
let fileInput = document.querySelector('.file-input');

openRegister.addEventListener('click',()=>{
    // 글 작성 열기
    registerModal.style.display="block";
})

xBtn.addEventListener('click',()=>{
    // 글 작성 닫기 
    registerModal.style.display="none";
})

file.addEventListener('click',()=>{
    // 이미지
    fileInput.click();
})