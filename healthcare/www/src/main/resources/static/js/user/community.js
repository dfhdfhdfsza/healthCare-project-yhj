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




let tags = document.querySelectorAll('.option-tag');

tags.forEach(e=>{
    e.addEventListener('click',()=>{
        let tag = encodeURIComponent(e.getAttribute("data-tag"));
        console.log(tag);
        searchTag(tag).then(result=>{
            if(result.length > 0){
                let body_div = document.querySelector('.body-div');
                body_div.innerHTML= "";
                console.log(result.length+"<<<<<<<<<<<<<<<가져온 값");
                for(let i=0; i<result.length; i++){
                    console.log(result[i]);
                    let str = `<div class="search-div-value">`;
                        str += `<div class="title-zone">`;
                        str += `<a href="/user/communityDetail?writingNo=${result[i].writingNo}">${result[i].writingTitle}</a>`; 
                        str += `</div>`;
                        str += `<div class="writer-zone">`;
                        str += `<a>${result[i].writingWriter}</a>`;
                        str += `</div>`;
                        str += `<div class="etc-zone">`;
                        str += `<span>${result[i].writingRegDate.slice(0, 10)}</span>`;
                        str += `<span>${result[i].writingReadCount}</span>`;
                        str += `</div></div>`;
                       body_div.innerHTML+=str;
                }
            }else if(result.length == 0){
                alert("해당 게시글이 존재하지 않습니다.");
            }
        })
    })
})
        

async function searchTag(tag){
    try {
        const resp = await fetch("/user/selectTag/"+tag.toString());
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}


let searchITag = document.querySelector('.search-i');

searchITag.addEventListener("click",()=>{
    let searchValue = document.querySelector('.search-input').value;
    
    searchCommunity(searchValue).then(result=>{
        
    })
})

async function searchCommunity(searchValue){
    try {
        const resp = await fetch("/user/searchCommunity/"+searchValue);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

