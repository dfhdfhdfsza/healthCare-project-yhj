let addBtn = document.querySelector('.add-btn'); // 작성버튼

console.log("js연결");

async function addComment(cmtData){
    try {
        const url = "/user/addComment";
        const config = {
            method: 'post',
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };
        const resp = await fetch(url,config);
        const result = await resp.json();
        return result;

    } catch (error) {
        console.log(error);
    }
}

addBtn.addEventListener('click',()=>{
    let writingContent = document.querySelector('.writingContent').value; // 내용
    let writingNumber = document.querySelector('.writingNumber').value; // 글번호
    let userName = document.querySelector('.userName').value; // 작성자
    let userNumber = document.querySelector('.userNo').value; // 유저번호
    
    if(writingContent == null){
        alert("댓글을 입력해주세요");
    }else{
        let cmtData={
            writingNo: writingNumber, // 글번호
            commentWriter: userName, // 작성자
            commentContent: writingContent, // 내용
            userNo: userNumber
        }

        addComment(cmtData).then(result=>{
            console.log(cmtData);
            window.location.reload();
        })
    }
})


let favoriteBtn = document.querySelectorAll('.favoriteBtn');


favoriteBtn.forEach(e=>{
    // 댓글 추천기능
    e.addEventListener('click',()=>{
        let commentNo = e.getAttribute('data-commentNo');
        let userNo = e.getAttribute('data-userNo');

        if(e.classList.contains("favorite-add")){
            deleteFavroite(userNo,commentNo).then(result=>{
                if(result==1){
                    alert("추천삭제 성공");
                    e.classList.remove("favorite-add");
                    window.location.reload();
                }
            })
        }else{
            favoriteComment(userNo,commentNo).then(result=>{
                if(result == 1){
                    e.classList.add("favorite-add");
                    window.location.reload();
                }
               })
        }
    })
})


async function favoriteComment(userNo,commentNo){
    try {
        const resp = await fetch("/user/favoriteAdd/"+userNo+"/"+commentNo);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}


let modify = document.querySelectorAll(".bi-pencil-fill"); // 수정버튼
let modifyInput = document.querySelector('.modify-comment-input'); // input
let background = document.querySelector('.commentModify-modal-background'); // 모달
let modify_comment_a = document.querySelector('.modify-comment-a'); // 이동경로
let hiddenInput =  document.querySelector('.modify-comment-hidden'); // 히든값

modify.forEach(e=>{
    e.addEventListener('click',()=>{
        background.style.display = "block";
        let commentNo = e.getAttribute('data-commentNo');
        let commentContent = e.getAttribute('data-commentContent');
        hiddenInput.value= commentNo;
        modifyInput.value= commentContent;
    })
})

let closeBtn = document.getElementById('closeBtn');
closeBtn.addEventListener('click',()=>{
    background.style.display = "none";
})


let favorite_commentNo = document.querySelectorAll(".favorite-commentNo");

window.onload =function favorite_addList(){
    let favoriteAddComment = document.querySelectorAll('.favorite-add-comment');
    // 내가 누른 추천 글 번호 확인  
    for(let i=0; i<favoriteAddComment.length; i++){
        
        // 글 전체
        for(let j=0; j<favoriteBtn.length; j++){
            
            if(Number(favoriteAddComment[i].innerHTML) == Number(favoriteBtn[j].getAttribute("data-commentNo"))){
                favoriteBtn[j].classList.add("favorite-add");
            }
        }
    }
}

let favoriteAdd = document.querySelectorAll('.favorite-add');



async function deleteFavroite(userNo, commentNo){
    try {
        const url = "/user/favoriteDelete/"+userNo+"/"+commentNo;
        const config={
            method: "delete"
        }
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}