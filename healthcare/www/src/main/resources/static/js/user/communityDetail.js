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
       favoriteComment(userNo,commentNo).then(result=>{
        if(result == 1){
            e.classList.add("favorite-add");
        }
       })
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

